package com.example.nhasachonline.firebase;

import android.content.Context;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.nhasachonline.activity.ChiTietSachActivity;
import com.example.nhasachonline.activity.GioHangActivity;
import com.example.nhasachonline.activity.ManHinhChinhKhachHangActivity;
import com.example.nhasachonline.adapters.GioHangRecyclerViewAdapter;
import com.example.nhasachonline.adapters.ManHinhChinhKhachHangAdapter;
import com.example.nhasachonline.data_model.GioHang;
import com.example.nhasachonline.data_model.PhanPhoi;
import com.example.nhasachonline.data_model.Sach;
import com.example.nhasachonline.item.SanPham;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.ChildEventListener;
import com.example.nhasachonline.data_model.VanPhongPham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FireBaseNhaSachOnline {
    public void hienThiManHinhChinhKhachHang(ArrayList<com.example.nhasachonline.item.SanPham> sanPhams, ManHinhChinhKhachHangAdapter adapter, Context context){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference phanHoiDatabase = firebaseDatabase.getReference("PHANHOI");
        DatabaseReference sachDatabase = firebaseDatabase.getReference("SACH");
        DatabaseReference vanPhongPhamDatabase = firebaseDatabase.getReference("VANPHONGPHAM");
        sachDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot sachDataSnapshot: snapshot.getChildren()){
                    Sach sach = sachDataSnapshot.getValue(Sach.class);
                    for (SanPham sanPham: sanPhams){
                        if(sanPham.getMaSanPham() == sach.getMaSach()){
                            sanPhams.remove(sanPham);
                        }
                    }
                    phanHoiDatabase.child(sach.getMaSach()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int tongDanhGia = 0;
                            int soLuongDanhGia = 0;
                            int danhGia = 0;
                            int binhLuan = 0;
                            for (DataSnapshot phanHoiDataSnapshot: snapshot.getChildren()) {
                                PhanPhoi phanPhoi = phanHoiDataSnapshot.getValue(PhanPhoi.class);
                                if(phanPhoi.getDanhGia()!= null){
                                    tongDanhGia += Integer.valueOf(phanPhoi.getDanhGia());
                                    soLuongDanhGia++;
                                }
                                if(phanPhoi.getBinhLuan() != null){
                                    binhLuan++;
                                }
                                danhGia = tongDanhGia / soLuongDanhGia;
                            }

                            sanPhams.add(new SanPham(sach.getMaSach(),sach.getTenSach(),sach.getHinhSach(),sach.getTacGia(),"",Integer.valueOf(sach.getGiaTien()),Integer.valueOf(sach.getSoLuongKho()),danhGia,binhLuan));
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.d("onCancelled", "Lỗi!!!" + error.toString());
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!!!" + error.toString());
            }
        });
        vanPhongPhamDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot vanPhongPhamDataSnapshot: snapshot.getChildren()){
                    VanPhongPham vanPhongPham = vanPhongPhamDataSnapshot.getValue(VanPhongPham.class);
                    for (SanPham sanPham: sanPhams){
                        if(sanPham.getMaSanPham() == vanPhongPham.getMaVanPhongPham()){
                            sanPhams.remove(sanPham);
                        }
                    }
                    phanHoiDatabase.child(vanPhongPham.getMaVanPhongPham()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int tongDanhGia = 0;
                            int soLuongDanhGia = 0;
                            int danhGia = 0;
                            int binhLuan = 0;
                            for (DataSnapshot phanHoiDataSnapshot: snapshot.getChildren()) {
                                PhanPhoi phanPhoi = phanHoiDataSnapshot.getValue(PhanPhoi.class);
                                if(phanPhoi.getDanhGia()!= null){
                                    tongDanhGia += Integer.valueOf(phanPhoi.getDanhGia());
                                    soLuongDanhGia++;
                                }
                                if(phanPhoi.getBinhLuan() != null){
                                    binhLuan++;
                                }
                                danhGia = tongDanhGia / soLuongDanhGia;
                            }

                            sanPhams.add(new SanPham(vanPhongPham.getMaVanPhongPham(),vanPhongPham.getTenVanPhongPham(),vanPhongPham.getHinhVanPhongPham(),"",vanPhongPham.getXuatXu(),Integer.valueOf(vanPhongPham.getGiaTien()),Integer.valueOf(vanPhongPham.getSoLuongKho()),danhGia,binhLuan));
                            adapter.notifyDataSetChanged();
                            // ((ManHinhChinhKhachHangActivity)context).filterList();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.d("onCancelled", "Lỗi!!!" + error.toString());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!!!" + error.toString());
            }
        });

    }

    public void hienThiGioHang(String maKhachHang, ArrayList<com.example.nhasachonline.item.GioHang> gioHangItem, GioHangRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference gioHangDatabase = firebaseDatabase.getReference("GIOHANG");
        ArrayList<GioHang> gioHangDataModel = new ArrayList<>();
        gioHangDatabase.child(maKhachHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    for (DataSnapshot gioHangSnapshot  : snapshot.getChildren()) {
                        gioHangDataModel.add(gioHangSnapshot .getValue(GioHang.class));
                    }
                    for (int i = 0; i < gioHangDataModel.size(); i++) {
                        if (gioHangDataModel.get(i).getMaSanPham().contains("s")) {
                            getSachDatabase(i ,gioHangDataModel, gioHangItem, adapter, context);
                        } else if (gioHangDataModel.get(i).getMaSanPham().contains("vpp")) {
                            getVanPhongPhamDatabase(i ,gioHangDataModel, gioHangItem, adapter, context);
                        }
                    }

                }
                else {
                    Log.d("onDataChange", "Không có dữ liệu!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    private void getSachDatabase(int i, ArrayList<GioHang> gioHangDataModel, ArrayList<com.example.nhasachonline.item.GioHang> gioHangItem, GioHangRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference sachDatabase = firebaseDatabase.getReference("SACH");
        sachDatabase.child(gioHangDataModel.get(i).getMaSanPham()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    Sach sach = snapshot.getValue(Sach.class);
                    gioHangItem.add(new com.example.nhasachonline.item.GioHang(sach.getTenSach(),Integer.valueOf(sach.getGiaTien()),Integer.valueOf(sach.getKhuyenMai()),Integer.valueOf(gioHangDataModel.get(i).getSoLuong())));
//                    Log.d("onDataChange",  gioHangItem.size() + "");
                    adapter.notifyDataSetChanged();
                    ((GioHangActivity)context).TongTienThanhToan();
                }
                else {
                    Log.d("onDataChange", "Không có dữ liệu!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getVanPhongPhamDatabase(int i, ArrayList<GioHang> gioHangDataModel, ArrayList<com.example.nhasachonline.item.GioHang> gioHangItem, GioHangRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference vanPhongPhamDatabase = firebaseDatabase.getReference("VANPHONGPHAM");
        vanPhongPhamDatabase.child(gioHangDataModel.get(i).getMaSanPham()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    VanPhongPham vanPhongPham = snapshot.getValue(VanPhongPham.class);
                    gioHangItem.add(new com.example.nhasachonline.item.GioHang(vanPhongPham.getTenVanPhongPham(),Integer.valueOf(vanPhongPham.getGiaTien()),Integer.valueOf(vanPhongPham.getKhuyenMai()),Integer.valueOf(gioHangDataModel.get(i).getSoLuong())));
//                    Log.d("onDataChange", gioHangItem.size()  + "");
                    adapter.notifyDataSetChanged();
                    ((GioHangActivity)context).TongTienThanhToan();
                }
                else {
                    Log.d("onDataChange", "Không có dữ liệu!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
