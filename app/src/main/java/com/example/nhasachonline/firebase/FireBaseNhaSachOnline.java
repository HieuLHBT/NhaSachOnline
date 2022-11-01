package com.example.nhasachonline.firebase;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.nhasachonline.activity.GioHangActivity;
import com.example.nhasachonline.adapters.GioHangRecyclerViewAdapter;
import com.example.nhasachonline.data_model.GioHang;
import com.example.nhasachonline.data_model.Sach;
import com.example.nhasachonline.data_model.VanPhongPham;
import com.example.nhasachonline.item.KhachHang;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FireBaseNhaSachOnline {



    ///ko can dung ma khach hang nua ma dung FirebaseAuth.getUser().uuid thay the

    public void taoKhachHang(KhachHang khachHang){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ngDungDatabase = firebaseDatabase.getReference("NGUOIDUNG").child("khachhang");
        ngDungDatabase.child(khachHang.getMaKH()).setValue(khachHang);
    }



    public void xoaSanPhamGioHang(String maKhachHang, String maSanpham) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference gioHangDatabase = firebaseDatabase.getReference("GIOHANG");
        gioHangDatabase.child(maKhachHang).child(maSanpham).removeValue();
    }

    public void congSoLuongGioHang(String maKhachHang, String maSanPham, int soLuong) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference gioHangDatabase = firebaseDatabase.getReference("GIOHANG");
        gioHangDatabase.child(maKhachHang).child(maSanPham).child("soLuong").setValue((++soLuong) + "");
    }

    public void truSoLuongGioHang(String maKhachHang, String maSanPham, int soLuong) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference gioHangDatabase = firebaseDatabase.getReference("GIOHANG");
        gioHangDatabase.child(maKhachHang).child(maSanPham).child("soLuong").setValue((--soLuong) + "");
    }

    public void hienThiGioHang(String maKhachHang, ArrayList<com.example.nhasachonline.item.GioHang> gioHangItem, GioHangRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference gioHangDatabase = firebaseDatabase.getReference("GIOHANG");
        DatabaseReference sachDatabase = firebaseDatabase.getReference("SACH");
        DatabaseReference vanPhongPhamDatabase = firebaseDatabase.getReference("VANPHONGPHAM");
        gioHangDatabase.child(maKhachHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                gioHangItem.clear();
                ArrayList<com.example.nhasachonline.item.GioHang> dsGioHang = new ArrayList<>();
                for (DataSnapshot gioHangSnapshot : snapshot.getChildren()) {
                    GioHang gioHang = gioHangSnapshot.getValue(GioHang.class);
                    if (gioHang.getMaSanPham().contains("s")) {
                        sachDatabase.child(gioHang.getMaSanPham()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Sach sach = snapshot.getValue(Sach.class);
                                gioHangItem.add(new com.example.nhasachonline.item.GioHang(sach.getMaSach(), sach.getTenSach(), Integer.valueOf(sach.getGiaTien()), Integer.valueOf(sach.getKhuyenMai()), Integer.valueOf(gioHang.getSoLuong()), sach.getHinhSach()));
                                adapter.notifyDataSetChanged();
                                ((GioHangActivity) context).TongTienThanhToan();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.d("onCancelled", "Lỗi!" + error.getMessage());
                            }
                        });
                    }
                    else if (gioHang.getMaSanPham().contains("vpp")) {
                        vanPhongPhamDatabase.child(gioHang.getMaSanPham()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                VanPhongPham vanPhongPham = snapshot.getValue(VanPhongPham.class);
                                gioHangItem.add(new com.example.nhasachonline.item.GioHang(vanPhongPham.getMaVanPhongPham(), vanPhongPham.getTenVanPhongPham(), Integer.valueOf(vanPhongPham.getGiaTien()), Integer.valueOf(vanPhongPham.getKhuyenMai()), Integer.valueOf(gioHang.getSoLuong()), vanPhongPham.getHinhVanPhongPham()));
                                adapter.notifyDataSetChanged();
                                ((GioHangActivity) context).TongTienThanhToan();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.d("onCancelled", "Lỗi!" + error.getMessage());
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }
}
