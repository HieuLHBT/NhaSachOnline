package com.example.nhasachonline.firebase;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nhasachonline.activity.GioHangActivity;
import com.example.nhasachonline.adapters.GioHangRecyclerViewAdapter;
import com.example.nhasachonline.adapters.TheoDoiDonHangRecyclerViewAdapter;
import com.example.nhasachonline.data_model.DonHang;
import com.example.nhasachonline.data_model.GioHang;
import com.example.nhasachonline.data_model.Sach;
import com.example.nhasachonline.data_model.VanPhongPham;
import com.example.nhasachonline.item.TheoDoiDonHang;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FireBaseNhaSachOnline {

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
                    } else if (gioHang.getMaSanPham().contains("vpp")) {
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

    public void hienThiTheoDoiDonHang(String maDonHang, ArrayList<TheoDoiDonHang> theoDoiDonHangItem, TheoDoiDonHangRecyclerViewAdapter adapter, Context context){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        donHangDatabase.child(maDonHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               theoDoiDonHangItem.clear();
               ArrayList<TheoDoiDonHang> dsTheoDoiDonHang = new ArrayList<>();
               for(DataSnapshot donHangSnapshot : snapshot.getChildren()){
                   TheoDoiDonHang theoDoiDonHang = donHangSnapshot.getValue(TheoDoiDonHang.class);
                   theoDoiDonHangItem.add(new TheoDoiDonHang(theoDoiDonHang.getMaDonHang(), theoDoiDonHang.getTenNVGiaoHang(), theoDoiDonHang.getThoiGianDuKienGiao(), theoDoiDonHang.getThoiGianDat(), Integer.valueOf(theoDoiDonHang.getTongTienThanhToan()), theoDoiDonHang.getTrangThai()));
               }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());

            }
        });
    }

    /*private void getDonHangDatabase(int i, ArrayList<DonHang> donHangDataModel, ArrayList<TheoDoiDonHang> theoDoiDonHangItem, TheoDoiDonHangRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        donHangDatabase.child(donHangDataModel.get(i).getMaDonHang()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.getValue() != null) {
                    TheoDoiDonHang theoDoiDonHang = snapshot.getValue(TheoDoiDonHang.class);
                    theoDoiDonHangItem.add(new TheoDoiDonHang(theoDoiDonHang.getMaDonHang(), theoDoiDonHang.getTenNVGiaoHang(), donHangDataModel.get(i).getThoiGianGiao(), donHangDataModel.get(i).getThoiGianLap(), Integer.valueOf(theoDoiDonHang.getTongTienThanhToan()), theoDoiDonHang.getTrangThai()));
                    ((GioHangActivity) context).TongTienThanhToan();
                } else {
                    Log.d("onDataChange", "Không có dữ liệu!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    */
}
