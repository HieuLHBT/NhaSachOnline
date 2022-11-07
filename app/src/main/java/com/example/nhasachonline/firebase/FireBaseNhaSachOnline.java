package com.example.nhasachonline.firebase;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nhasachonline.activity.ChiTietSanPhamActivity;
import com.example.nhasachonline.activity.GioHangActivity;
import com.example.nhasachonline.activity.LichLamViecActivity;
import com.example.nhasachonline.activity.ThanhToanActivity;
import com.example.nhasachonline.adapters.ChiTietGiaoHangRecyclerViewAdapter;
import com.example.nhasachonline.adapters.DanhGiaSanPhamRecyclerViewAdapter;
import com.example.nhasachonline.adapters.GioHangRecyclerViewAdapter;
import com.example.nhasachonline.adapters.MaGiamGiaRecyclerViewAdapter;
import com.example.nhasachonline.adapters.ManHinhChinhKhachHangAdapter;
import com.example.nhasachonline.adapters.NhanVienRecyclerViewAdapter;
import com.example.nhasachonline.adapters.ThanhToanRecyclerViewAdapter;
import com.example.nhasachonline.data_model.ChamCong;
import com.example.nhasachonline.data_model.GiamGia;
import com.example.nhasachonline.data_model.DonHang;
import com.example.nhasachonline.data_model.GioHang;
import com.example.nhasachonline.data_model.KhachHang;
import com.example.nhasachonline.data_model.NhanVien;
import com.example.nhasachonline.data_model.PhanPhoi;
import com.example.nhasachonline.data_model.Sach;
import com.example.nhasachonline.data_model.TrangThaiDonHang;
import com.example.nhasachonline.data_model.VanPhongPham;
import com.example.nhasachonline.data_model.XuatKho;
import com.example.nhasachonline.item.ChiTietGiaoHang;
import com.example.nhasachonline.item.DanhGia;
import com.example.nhasachonline.item.ItemKhachHang;
import com.example.nhasachonline.item.ItemNhanVien;
import com.example.nhasachonline.item.ItemSanPham;
import com.example.nhasachonline.item.LichLamViec;
import com.example.nhasachonline.item.ThanhToan;
import com.example.nhasachonline.tools.SharePreferences;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FireBaseNhaSachOnline {
    private SharePreferences sharePreferences = new SharePreferences();

    // Hiếu Firebase
    // Giỏ hàng
    public void hienThiGioHang(String maKhachHang, ArrayList<com.example.nhasachonline.item.GioHang> gioHangItem, GioHangRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference gioHangDatabase = firebaseDatabase.getReference("GIOHANG");
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
//        gioHangDatabase.child(maKhachHang).addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                GioHang gioHang = snapshot.getValue(GioHang.class);
//                if (gioHang.getMaSanPham().contains("s")) {
//                    sanPhamDatabase.child("SACH").child(gioHang.getMaSanPham()).addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            Sach sach = snapshot.getValue(Sach.class);
//                            gioHangItem.add(new com.example.nhasachonline.item.GioHang(
//                                    sach.getMaSach(),
//                                    sach.getTenSach(),
//                                    Integer.valueOf(sach.getGiaTien()),
//                                    Integer.valueOf(sach.getKhuyenMai()),
//                                    Integer.valueOf(gioHang.getSoLuong()),
//                                    sach.getHinhSach()));
//                            adapter.notifyDataSetChanged();
//                            ((GioHangActivity) context).TongTienThanhToan();
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//                            Log.d("onCancelled", "Lỗi!" + error.getMessage());
//                        }
//                    });
//                } else if (gioHang.getMaSanPham().contains("vpp")) {
//                    sanPhamDatabase.child("VANPHONGPHAM").child(gioHang.getMaSanPham()).addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            VanPhongPham vanPhongPham = snapshot.getValue(VanPhongPham.class);
//                            gioHangItem.add(new com.example.nhasachonline.item.GioHang(
//                                    vanPhongPham.getMaVanPhongPham(),
//                                    vanPhongPham.getTenVanPhongPham(),
//                                    Integer.valueOf(vanPhongPham.getGiaTien()),
//                                    Integer.valueOf(vanPhongPham.getKhuyenMai()),
//                                    Integer.valueOf(gioHang.getSoLuong()),
//                                    vanPhongPham.getHinhVanPhongPham()));
//                            adapter.notifyDataSetChanged();
//                            ((GioHangActivity) context).TongTienThanhToan();
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//                            Log.d("onCancelled", "Lỗi!" + error.getMessage());
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                GioHang gioHangThayDoi = snapshot.getValue(GioHang.class);
//                for (com.example.nhasachonline.item.GioHang gioHang: gioHangItem) {
//                    if (gioHang.getMaSanPham().equalsIgnoreCase(gioHangThayDoi.getMaSanPham())) {
//                        gioHang.setSoLuongSanPham(Integer.valueOf(gioHangThayDoi.getSoLuong()));
//                        break;
//                    }
//                }
//                adapter.notifyDataSetChanged();
//                ((GioHangActivity) context).TongTienThanhToan();
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//                GioHang gioHangXoa = snapshot.getValue(GioHang.class);
//                for (com.example.nhasachonline.item.GioHang gioHang: gioHangItem) {
//                    if (gioHang.getMaSanPham().equalsIgnoreCase(gioHangXoa.getMaSanPham())) {
//                        gioHangItem.remove(gioHang);
//                        break;
//                    }
//                }
//                adapter.notifyDataSetChanged();
//                ((GioHangActivity) context).TongTienThanhToan();
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.d("onCancelled", "Lỗi!" + error.getMessage());
//            }
//        });
//        gioHangDatabase.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                if (maKhachHang.equalsIgnoreCase(snapshot.getKey())) {
//                    gioHangItem.clear();
//                    for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
//                        GioHang gioHang = dataSnapshot.getValue(GioHang.class);
//                        if (gioHang.getMaSanPham().contains("s")) {
//                            sanPhamDatabase.child("SACH").child(gioHang.getMaSanPham()).addListenerForSingleValueEvent(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                    Sach sach = snapshot.getValue(Sach.class);
//                                    gioHangItem.add(new com.example.nhasachonline.item.GioHang(
//                                            sach.getMaSach(),
//                                            sach.getTenSach(),
//                                            Integer.valueOf(sach.getGiaTien()),
//                                            Integer.valueOf(sach.getKhuyenMai()),
//                                            Integer.valueOf(gioHang.getSoLuong()),
//                                            sach.getHinhSach()));
//                                    adapter.notifyDataSetChanged();
//                                    ((GioHangActivity) context).TongTienThanhToan();
//                                }
//
//                                @Override
//                                public void onCancelled(@NonNull DatabaseError error) {
//                                    Log.d("onCancelled", "Lỗi!" + error.getMessage());
//                                }
//                            });
//                        } else if (gioHang.getMaSanPham().contains("vpp")) {
//                            sanPhamDatabase.child("VANPHONGPHAM").child(gioHang.getMaSanPham()).addListenerForSingleValueEvent(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                    VanPhongPham vanPhongPham = snapshot.getValue(VanPhongPham.class);
//                                    gioHangItem.add(new com.example.nhasachonline.item.GioHang(
//                                            vanPhongPham.getMaVanPhongPham(),
//                                            vanPhongPham.getTenVanPhongPham(),
//                                            Integer.valueOf(vanPhongPham.getGiaTien()),
//                                            Integer.valueOf(vanPhongPham.getKhuyenMai()),
//                                            Integer.valueOf(gioHang.getSoLuong()),
//                                            vanPhongPham.getHinhVanPhongPham()));
//                                    adapter.notifyDataSetChanged();
//                                    ((GioHangActivity) context).TongTienThanhToan();
//                                }
//
//                                @Override
//                                public void onCancelled(@NonNull DatabaseError error) {
//                                    Log.d("onCancelled", "Lỗi!" + error.getMessage());
//                                }
//                            });
//                        }
//                    }
//                }
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                //Log.d("test", snapshot.getValue() + "");
//                gioHangItem.clear();
//                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
//                    GioHang gioHang = dataSnapshot.getValue(GioHang.class);
//                    if (gioHang.getMaSanPham().contains("s")) {
//                        sanPhamDatabase.child("SACH").child(gioHang.getMaSanPham()).addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                Sach sach = snapshot.getValue(Sach.class);
//                                gioHangItem.add(new com.example.nhasachonline.item.GioHang(
//                                        sach.getMaSach(),
//                                        sach.getTenSach(),
//                                        Integer.valueOf(sach.getGiaTien()),
//                                        Integer.valueOf(sach.getKhuyenMai()),
//                                        Integer.valueOf(gioHang.getSoLuong()),
//                                        sach.getHinhSach()));
//                                adapter.notifyDataSetChanged();
//                                ((GioHangActivity) context).TongTienThanhToan();
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//                                Log.d("onCancelled", "Lỗi!" + error.getMessage());
//                            }
//                        });
//                    } else if (gioHang.getMaSanPham().contains("vpp")) {
//                        sanPhamDatabase.child("VANPHONGPHAM").child(gioHang.getMaSanPham()).addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                VanPhongPham vanPhongPham = snapshot.getValue(VanPhongPham.class);
//                                gioHangItem.add(new com.example.nhasachonline.item.GioHang(
//                                        vanPhongPham.getMaVanPhongPham(),
//                                        vanPhongPham.getTenVanPhongPham(),
//                                        Integer.valueOf(vanPhongPham.getGiaTien()),
//                                        Integer.valueOf(vanPhongPham.getKhuyenMai()),
//                                        Integer.valueOf(gioHang.getSoLuong()),
//                                        vanPhongPham.getHinhVanPhongPham()));
//                                adapter.notifyDataSetChanged();
//                                ((GioHangActivity) context).TongTienThanhToan();
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//                                Log.d("onCancelled", "Lỗi!" + error.getMessage());
//                            }
//                        });
//                    }
//                }
////                GioHang gioHangThayDoi = snapshot.getValue(GioHang.class);
////                for (com.example.nhasachonline.item.GioHang gioHang: gioHangItem) {
////                    if (gioHang.getMaSanPham().equalsIgnoreCase(gioHangThayDoi.getMaSanPham())) {
////                        gioHang.setSoLuongSanPham(Integer.valueOf(gioHangThayDoi.getSoLuong()));
////                        break;
////                    }
////                }
////                adapter.notifyDataSetChanged();
////                ((GioHangActivity) context).TongTienThanhToan();
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//                Log.d("test", "process");
////                GioHang gioHangXoa = snapshot.getValue(GioHang.class);
////                for (com.example.nhasachonline.item.GioHang gioHang: gioHangItem) {
////                    if (gioHang.getMaSanPham().equalsIgnoreCase(gioHangXoa.getMaSanPham())) {
////                        gioHangItem.remove(gioHang);
////                        break;
////                    }
////                }
////                adapter.notifyDataSetChanged();
////                ((GioHangActivity) context).TongTienThanhToan();
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.d("onCancelled", "Lỗi!" + error.getMessage());
//            }
//        });
        gioHangDatabase.child(maKhachHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                gioHangItem.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    GioHang gioHang = dataSnapshot.getValue(GioHang.class);
                    if (gioHang.getMaSanPham().contains("s")) {
                        sanPhamDatabase.child("SACH").child(gioHang.getMaSanPham()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Sach sach = snapshot.getValue(Sach.class);
                                gioHangItem.add(new com.example.nhasachonline.item.GioHang(
                                        sach.getMaSach(),
                                        sach.getTenSach(),
                                        Integer.valueOf(sach.getGiaTien()),
                                        Integer.valueOf(sach.getKhuyenMai()),
                                        Integer.valueOf(gioHang.getSoLuong()),
                                        sach.getHinhSach()));
                                adapter.notifyDataSetChanged();
                                ((GioHangActivity) context).TongTienThanhToan();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.d("onCancelled", "Lỗi!" + error.getMessage());
                            }
                        });
                    } else if (gioHang.getMaSanPham().contains("vpp")) {
                        sanPhamDatabase.child("VANPHONGPHAM").child(gioHang.getMaSanPham()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                VanPhongPham vanPhongPham = snapshot.getValue(VanPhongPham.class);
                                gioHangItem.add(new com.example.nhasachonline.item.GioHang(
                                        vanPhongPham.getMaVanPhongPham(),
                                        vanPhongPham.getTenVanPhongPham(),
                                        Integer.valueOf(vanPhongPham.getGiaTien()),
                                        Integer.valueOf(vanPhongPham.getKhuyenMai()),
                                        Integer.valueOf(gioHang.getSoLuong()),
                                        vanPhongPham.getHinhVanPhongPham()));
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

            }
        });
    }

    public void xoaSanPhamGioHang(String maKhachHang, String maSanpham, GioHangRecyclerViewAdapter adapter) {
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

    public void taoXuatKho(String maKhachHang, ArrayList<com.example.nhasachonline.item.GioHang> gioHangs, Context context, int size, GioHangRecyclerViewAdapter adapter) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference gioHangDatabase = firebaseDatabase.getReference("GIOHANG");
        DatabaseReference xuatKhoDatabase = firebaseDatabase.getReference("XUATKHO");
        xuatKhoDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> dsXuatKho = new ArrayList<>();
                for (DataSnapshot xuatKhoSnapshot : snapshot.getChildren()) {
                    dsXuatKho.add(xuatKhoSnapshot.getKey());
                }
                String[] temp = dsXuatKho.get(dsXuatKho.size() - 1).split("dh");
                String maDonHang = "dh" + (Integer.valueOf(temp[1]) + 1);
                if (gioHangs.size() == size) {
                    for (com.example.nhasachonline.item.GioHang gioHang : gioHangs) {
                        XuatKho xuatKho = new XuatKho(maDonHang, gioHang.getMaSanPham(), String.valueOf(gioHang.getSoLuongSanPham()));
                        xuatKhoDatabase.child(maDonHang).child(gioHang.getMaSanPham()).setValue(xuatKho);
                    }
                    gioHangDatabase.child(maKhachHang).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            adapter.notifyDataSetChanged();
                            ((GioHangActivity) context).TongTienThanhToan();
                        }
                    });
                } else {
                    for (com.example.nhasachonline.item.GioHang gioHang : gioHangs) {
                        XuatKho xuatKho = new XuatKho(maDonHang, gioHang.getMaSanPham(), String.valueOf(gioHang.getSoLuongSanPham()));
                        xuatKhoDatabase.child(maDonHang).child(gioHang.getMaSanPham()).setValue(xuatKho);
                        gioHangDatabase.child(maKhachHang).child(gioHang.getMaSanPham()).removeValue();
                    }
                }
                sharePreferences.themMaDonHang(context, maDonHang);
                Intent intent = new Intent(context, ThanhToanActivity.class);
                context.startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    // Thanh toán
    public void hienThiItemThanhToan(String maDonHang, ArrayList<ThanhToan> thanhToans, ThanhToanRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference xuatKhoDatabase = firebaseDatabase.getReference("XUATKHO");
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        xuatKhoDatabase.child(maDonHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                thanhToans.clear();
                for (DataSnapshot xuatKhoSnapshot : snapshot.getChildren()) {
                    XuatKho xuatKho = xuatKhoSnapshot.getValue(XuatKho.class);
                    if (xuatKho.getMaSanPham().contains("s")) {
                        sanPhamDatabase.child("SACH").child(xuatKho.getMaSanPham()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Sach sach = snapshot.getValue(Sach.class);
                                thanhToans.add(new ThanhToan(sach.getMaSach(), sach.getTenSach(), Integer.valueOf(sach.getGiaTien()), Integer.valueOf(xuatKho.getSoLuongXuat()), sach.getHinhSach(), Integer.valueOf(sach.getKhuyenMai())));
                                adapter.notifyDataSetChanged();
                                ((ThanhToanActivity) context).tongTien();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.d("onCancelled", "Lỗi!" + error.getMessage());
                            }
                        });
                    } else if (xuatKho.getMaSanPham().contains("vpp")) {
                        sanPhamDatabase.child("VANPHONGPHAM").child(xuatKho.getMaSanPham()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                VanPhongPham vanPhongPham = snapshot.getValue(VanPhongPham.class);
                                thanhToans.add(new ThanhToan(vanPhongPham.getMaVanPhongPham(), vanPhongPham.getTenVanPhongPham(), Integer.valueOf(vanPhongPham.getGiaTien()), Integer.valueOf(xuatKho.getSoLuongXuat()), vanPhongPham.getHinhVanPhongPham(), Integer.valueOf(vanPhongPham.getKhuyenMai())));
                                adapter.notifyDataSetChanged();
                                ((ThanhToanActivity) context).tongTien();
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

    public void hienThiKhachHang(String maKhachHang, KhachHang khachHang, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference xuatKhoDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        xuatKhoDatabase.child("khachhang").child(maKhachHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                KhachHang kh = snapshot.getValue(KhachHang.class);
                khachHang.setDiaChi(kh.getDiaChi());
                khachHang.setEmail(kh.getEmail());
                khachHang.setMaKhachHang(kh.getMaKhachHang());
                khachHang.setMatKhau(kh.getMatKhau());
                khachHang.setNganHang(kh.getNganHang());
                khachHang.setNguoiDung(kh.getNguoiDung());
                khachHang.setSoDienThoai(kh.getSoDienThoai());
                khachHang.setSoTaiKhoan(kh.getSoTaiKhoan());
                khachHang.setTaiKhoan(kh.getTaiKhoan());
                khachHang.setTenKhachHang(kh.getTenKhachHang());
                ((ThanhToanActivity) context).hienThiKhachHang();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    public void hienThiGiamGia(GiamGia giamGia, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference giamgiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        giamgiaDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot giamGiaDataSnapshot : snapshot.getChildren()) {
                    GiamGia giamGiaDaTa = giamGiaDataSnapshot.getValue(GiamGia.class);
                    if (giamGiaDaTa.getChon().equalsIgnoreCase("1")) {
                        giamGia.setChon(giamGiaDaTa.getChon());
                        giamGia.setHinhGiamGia(giamGiaDaTa.getHinhGiamGia());
                        giamGia.setMaGiamGia(giamGiaDaTa.getMaGiamGia());
                        giamGia.setTienGiamGia(giamGiaDaTa.getTienGiamGia());
                        giamGia.setTieuDe(giamGiaDaTa.getTieuDe());
                        giamGia.setYeuCau(giamGiaDaTa.getYeuCau());
                        ((ThanhToanActivity) context).tongTien();
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    public void xoaChonGiamGia(String maGiamGia) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference giamGiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        giamGiaDatabase.child(maGiamGia).child("chon").setValue("0");
    }

    public void chonGiamGia(String maGiamGia) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference giamGiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        giamGiaDatabase.child(maGiamGia).child("chon").setValue("1");
    }

    public void datHang(String phuongThucThanhToan, DonHang donHang, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        DatabaseReference giamGiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        DatabaseReference trangThaiDonHangDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        donHangDatabase.child(donHang.getMaDonHang()).setValue(donHang, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (donHang.getMaGiamGia() != "") {
                    giamGiaDatabase.child(donHang.getMaGiamGia()).child("chon").setValue("0").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            sharePreferences.xoaMaDonHang(context);
                            if (phuongThucThanhToan.equalsIgnoreCase("Trực tiếp")) {
                                TrangThaiDonHang trangThaiDonHang = new TrangThaiDonHang(
                                        donHang.getMaDonHang(),
                                        phuongThucThanhToan,
                                        "",
                                        "",
                                        "",
                                        "Đang xử lý",
                                        "Chờ xác nhận",
                                        "",
                                        "",
                                        "",
                                        "",
                                        "",
                                        "",
                                        "");
                                trangThaiDonHangDatabase.child(donHang.getMaDonHang()).setValue(trangThaiDonHang);
                            } else if (phuongThucThanhToan.equalsIgnoreCase("Online")) {
                                TrangThaiDonHang trangThaiDonHang = new TrangThaiDonHang(
                                        donHang.getMaDonHang(),
                                        phuongThucThanhToan,
                                        "",
                                        "",
                                        "Chờ thanh toán",
                                        "Đang xử lý",
                                        "Chờ xác nhận",
                                        "",
                                        "",
                                        "",
                                        "",
                                        "",
                                        "",
                                        "");
                                trangThaiDonHangDatabase.child(donHang.getMaDonHang()).setValue(trangThaiDonHang);
                            }
                        }
                    });
                } else {
                    sharePreferences.xoaMaDonHang(context);
                    if (phuongThucThanhToan.equalsIgnoreCase("Trực tiếp")) {
                        TrangThaiDonHang trangThaiDonHang = new TrangThaiDonHang(
                                donHang.getMaDonHang(),
                                phuongThucThanhToan,
                                "",
                                "",
                                "",
                                "Đang xử lý",
                                "Chờ xác nhận",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "");
                        trangThaiDonHangDatabase.child(donHang.getMaDonHang()).setValue(trangThaiDonHang);
                    } else if (phuongThucThanhToan.equalsIgnoreCase("Online")) {
                        TrangThaiDonHang trangThaiDonHang = new TrangThaiDonHang(
                                donHang.getMaDonHang(),
                                phuongThucThanhToan,
                                "",
                                "",
                                "Chờ thanh toán",
                                "Đang xử lý",
                                "Chờ xác nhận",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "");
                        trangThaiDonHangDatabase.child(donHang.getMaDonHang()).setValue(trangThaiDonHang);
                    }
                }
            }
        });
    }

    public void huyThanhToan(String maGiamGia, String maDonHang, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference giamGiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        DatabaseReference xuatKhoDatabase = firebaseDatabase.getReference("XUATKHO");
        xuatKhoDatabase.child(maDonHang).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (maGiamGia != null) {
                    giamGiaDatabase.child(maGiamGia).child("chon").setValue("0").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            sharePreferences.xoaMaDonHang(context);
                        }
                    });
                } else {
                    sharePreferences.xoaMaDonHang(context);
                }
            }
        });

    }

    // Giảm giá
    public void hienThiMaGiamGia(ArrayList<GiamGia> giamGias, MaGiamGiaRecyclerViewAdapter adapter) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference giamgiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        giamgiaDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot giamGiaDataSnapshot : snapshot.getChildren()) {
                    GiamGia giamGia = giamGiaDataSnapshot.getValue(GiamGia.class);
                    giamGias.add(giamGia);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    // Lịch làm việc
    public void hienThiLichLamViec(Date ngayHienTai, String maNhanVien, ArrayList<ChamCong> chamCongs, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference chamcongDatabase = firebaseDatabase.getReference("CHAMCONG");
        chamcongDatabase.child(maNhanVien).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chamCongs.clear();
                for (DataSnapshot chamCongSnapshot : snapshot.getChildren()) {
                    ChamCong chamCong = chamCongSnapshot.getValue(ChamCong.class);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String ngayThayDoi = chamCong.getNgay().replaceAll("/", "");
                    Date ngayLuuTru = null;
                    try {
                        ngayLuuTru = sdf.parse(chamCong.getNgay());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (chamCong.getTrangThaiPhanCong().equalsIgnoreCase("Đã phân công")
                            && !chamCong.getCa1().equalsIgnoreCase("")
                            && chamCong.getGioVaoCa1().equalsIgnoreCase("")
                            && !chamCong.getCa2().equalsIgnoreCase("")
                            && chamCong.getGioVaoCa2().equalsIgnoreCase("")
                            && (ngayHienTai.compareTo(ngayLuuTru)) > 0
                            && chamCong.getNghiPhep().equalsIgnoreCase("")) {
                        chamCong.setNghiKhongPhep("ca1, ca2");
                        chamcongDatabase.child(maNhanVien).child(ngayThayDoi).child("nghiKhongPhep").setValue("ca1, ca2");
                    } else if (chamCong.getTrangThaiPhanCong().equalsIgnoreCase("Đã phân công")
                            && !chamCong.getCa1().equalsIgnoreCase("")
                            && chamCong.getGioVaoCa1().equalsIgnoreCase("")
                            && (ngayHienTai.compareTo(ngayLuuTru)) > 0
                            && chamCong.getNghiPhep().equalsIgnoreCase("")) {
                        chamCong.setNghiKhongPhep("ca1");
                        chamcongDatabase.child(maNhanVien).child(ngayThayDoi).child("nghiKhongPhep").setValue("ca1");
                    } else if (chamCong.getTrangThaiPhanCong().equalsIgnoreCase("Đã phân công")
                            && !chamCong.getCa2().equalsIgnoreCase("")
                            && chamCong.getGioVaoCa2().equalsIgnoreCase("")
                            && (ngayHienTai.compareTo(ngayLuuTru)) > 0
                            && chamCong.getNghiPhep().equalsIgnoreCase("")) {
                        chamCong.setNghiKhongPhep("ca2");
                        chamcongDatabase.child(maNhanVien).child(ngayThayDoi).child("nghiKhongPhep").setValue("ca2");
                    }
                    chamCongs.add(chamCong);
                }
                ((LichLamViecActivity) context).hienThiLich();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void xacNhanNghiCoPhep(String maNhanVien, String ngayXacNhan) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference chamcongDatabase = firebaseDatabase.getReference("CHAMCONG");
        chamcongDatabase.child(maNhanVien).child(ngayXacNhan).child("trangThaiPhanCong").setValue("Chờ duyệt");
    }

    public void dangKyLamViec(String maNhanVien, String ngayDangKy, String ngayDangChon, String caDangKy) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference chamcongDatabase = firebaseDatabase.getReference("CHAMCONG");
        if (caDangKy.equalsIgnoreCase("Làm cả ngày")) {
            ChamCong chamCong = new ChamCong("4",
                    "4",
                    "",
                    "",
                    "",
                    "",
                    "",
                    maNhanVien,
                    ngayDangChon,
                    "",
                    "",
                    "",
                    "Chờ duyệt");
            chamcongDatabase.child(maNhanVien).child(ngayDangKy).setValue(chamCong);
        } else if (caDangKy.equalsIgnoreCase("Làm ca 1")) {
            ChamCong chamCong = new ChamCong("4",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    maNhanVien,
                    ngayDangChon,
                    "",
                    "",
                    "",
                    "Chờ duyệt");
            chamcongDatabase.child(maNhanVien).child(ngayDangKy).setValue(chamCong);
        } else if (caDangKy.equalsIgnoreCase("Làm ca 2")) {
            ChamCong chamCong = new ChamCong("",
                    "4",
                    "",
                    "",
                    "",
                    "",
                    "",
                    maNhanVien,
                    ngayDangChon,
                    "",
                    "",
                    "",
                    "Chờ duyệt");
            chamcongDatabase.child(maNhanVien).child(ngayDangKy).setValue(chamCong);
        }
    }

    public void huyDangKyLamViec(String maNhanVien, String ngayXacNhan) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference chamcongDatabase = firebaseDatabase.getReference("CHAMCONG");
        chamcongDatabase.child(maNhanVien).child(ngayXacNhan).removeValue();
    }


    ///////////////////////////////////////////////////////
    public void taoKhachHang(ItemKhachHang khachHang) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ngDungDatabase = firebaseDatabase.getReference("NGUOIDUNG").child("khachhang");
        ngDungDatabase.child(khachHang.getMaKhachHang()).setValue(khachHang);
    }

    public void hienThiItemChiTietGiaoHang(String maDonHang, ArrayList<ChiTietGiaoHang> chiTietGiaoHang, ChiTietGiaoHangRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference xuatKhoDatabase = firebaseDatabase.getReference("XUATKHO");
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        xuatKhoDatabase.child(maDonHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                xuatKhoDatabase.child(maDonHang).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        chiTietGiaoHang.clear();
                        for (DataSnapshot xuatKhoSnapshot : snapshot.getChildren()) {
                            //Log.d("test", xuatKhoSnapshot.getValue() + " ");
                            XuatKho xuatKho = xuatKhoSnapshot.getValue(XuatKho.class);
                            if (xuatKho.getMaSanPham().contains("s")) {
                                sanPhamDatabase.child("SACH").child(xuatKho.getMaSanPham()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        Sach sach = snapshot.getValue(Sach.class);
                                        chiTietGiaoHang.add(new ChiTietGiaoHang(sach.getMaSach(), sach.getTenSach(), Integer.valueOf(sach.getGiaTien()), Integer.valueOf(xuatKho.getSoLuongXuat()), sach.getHinhSach()));
                                        //Log.d("test", snapshot.getValue() + " ");
                                        adapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Log.d("onCancelled", "Lỗi!" + error.getMessage());
                                    }
                                });
                            } else if (xuatKho.getMaSanPham().contains("vpp")) {
                                sanPhamDatabase.child("VANPHONGPHAM").child(xuatKho.getMaSanPham()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        VanPhongPham vanPhongPham = snapshot.getValue(VanPhongPham.class);
                                        chiTietGiaoHang.add(new ChiTietGiaoHang(vanPhongPham.getMaVanPhongPham(), vanPhongPham.getTenVanPhongPham(), Integer.valueOf(vanPhongPham.getGiaTien()), Integer.valueOf(xuatKho.getSoLuongXuat()), vanPhongPham.getHinhVanPhongPham()));
                                        adapter.notifyDataSetChanged();
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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void hienThiDonHang(String maDonHang, DonHang donHang, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        donHangDatabase.child(maDonHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DonHang dh = snapshot.getValue(DonHang.class);
                donHang.setMaDonHang(dh.getMaDonHang());
                donHang.setMaGiamGia(dh.getMaKhachHang());
                donHang.setDiaChiGiao(dh.getDiaChiGiao());
                donHang.setMaNVDuyet(dh.getMaNVDuyet());
                donHang.setMaNVGiao(dh.getMaNVGiao());
                donHang.setThoiGianGiao(dh.getThoiGianGiao());
                donHang.setThoiGianLap(dh.getThoiGianLap());
                donHang.setMaKhachHang(dh.getMaKhachHang());
                Log.d("test", snapshot.getValue() + "");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    public void hienThiTenNhanVien_CTGH(String maNhanVien, NhanVien nhanVien, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nhanVienDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nhanVienDatabase.child("NHANVIEN").child(maNhanVien).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                NhanVien nv = snapshot.getValue(NhanVien.class);
                nhanVien.setHinhNhanVien(nv.getHinhNhanVien());
                nhanVien.setMaNhanVien(nv.getMaNhanVien());
                nhanVien.setTenNhanVien(nv.getTenNhanVien());
                nhanVien.setDiaChi(nv.getDiaChi());
                nhanVien.setEmail(nv.getEmail());
                nhanVien.setCmnd(nv.getCmnd());
                nhanVien.setLuong(nv.getLuong());
                nhanVien.setMatKhau(nv.getMatKhau());
                nhanVien.setTaiKhoan(nv.getTaiKhoan());
                nhanVien.setSoDienThoai(nv.getSoDienThoai());
                nhanVien.setNguoiDung(nv.getNguoiDung());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    public void hienThiItemDanhGiaSanPham(String maSanPham, String maKhachHang, ArrayList<DanhGia> danhGias, DanhGiaSanPhamRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference phanHoiDatabase = firebaseDatabase.getReference("PHANHOI");
        DatabaseReference sachDatabase = firebaseDatabase.getReference("SACH");
        DatabaseReference vanPhongPhamDatabase = firebaseDatabase.getReference("VANPHONGPHAM");
        phanHoiDatabase.child(maSanPham).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot phanHoiSnapshot : snapshot.getChildren()) {
                    PhanPhoi phanPhoi = phanHoiSnapshot.getValue(PhanPhoi.class);
                    Log.d("test", snapshot.getValue() + " ");
                    if (phanPhoi.getMaSanPham().contains("s")) {
                        sachDatabase.child(phanPhoi.getMaSanPham()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Sach sach = snapshot.getValue(Sach.class);
                                danhGias.add(new DanhGia(sach.getMaSach(), sach.getTenSach(), sach.getHinhSach()));
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.d("onCancelled", "Lỗi!" + error.getMessage());
                            }
                        });
                    } else if (phanPhoi.getMaSanPham().contains("vpp")) {
                        vanPhongPhamDatabase.child(phanPhoi.getMaSanPham()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                VanPhongPham vanPhongPham = snapshot.getValue(VanPhongPham.class);
                                danhGias.add(new DanhGia(vanPhongPham.getMaVanPhongPham(), vanPhongPham.getTenVanPhongPham(), vanPhongPham.getHinhVanPhongPham()));
                                adapter.notifyDataSetChanged();
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

    public void ghiBinhLuan(String maSanPham, String maKhachHang, String binhLuan) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference phanHoiDatabase = firebaseDatabase.getReference("PHANHOI");
        phanHoiDatabase.child(maSanPham).child(maKhachHang).child("binhLuan").setValue(binhLuan);
    }

    public void ghiDanhGia(String maSanPham, String maKhachHang, int danhGia) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference phanHoiDatabase = firebaseDatabase.getReference("PHANHOI");
        phanHoiDatabase.child(maSanPham).child(maKhachHang).child("danhGia").setValue(danhGia);
    }

/*
    public void hienThiTheoDoiDonHang(String maKhachHang, ArrayList<TheoDoiDonHang> theoDoiDonHangItem, TheoDoiDonHangRecyclerViewAdapter adapter, Context context)  {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        DatabaseReference trangThaiDatabase = firebaseDatabase.getReference("TRANGTHAI");
        DatabaseReference xuatKhoDatabase = firebaseDatabase.getReference("XUATKHO");
        DatabaseReference sachDatabase = firebaseDatabase.getReference("SACH");
        DatabaseReference vanPhongPhamDatabase = firebaseDatabase.getReference("VANPHONGPHAM");
        donHangDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<DonHang> dsDonHangKhachHang = new ArrayList<>();
                for (DataSnapshot donHangDataSnapshot : snapshot.getChildren()) {
                    DonHang donHang = donHangDataSnapshot.getValue(DonHang.class);
                    if (donHang.getMaKhachHang().equalsIgnoreCase(maKhachHang)) {
                        dsDonHangKhachHang.add(donHang);
                    }
                }
                for (DonHang donHang : dsDonHangKhachHang) {
                    ArrayList<TheoDoiDonHangSanPham> theoDoiDonHangSanPhams = new ArrayList<>();
                    xuatKhoDatabase.child(donHang.getMaDonHang()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int tongDanhGia = 0;
                            int soLuongDanhGia = 0;
                            int danhGia = 0;
                            int binhLuan = 0;
                            for (DataSnapshot phanHoiDataSnapshot : snapshot.getChildren()) {
                                PhanPhoi phanPhoi = phanHoiDataSnapshot.getValue(PhanPhoi.class);
                                if (phanPhoi.getDanhGia() != null) {
                                    tongDanhGia += Integer.valueOf(phanPhoi.getDanhGia());
                                    soLuongDanhGia++;
                                }
                                if (phanPhoi.getBinhLuan() != null) {
                                    binhLuan++;
                                }
                                danhGia = tongDanhGia / soLuongDanhGia;
                            }

                            sanPhams.add(new SanPham(sach.getMaSach(), sach.getTenSach(), sach.getHinhSach(), sach.getTacGia(), "", Integer.valueOf(sach.getGiaTien()), Integer.valueOf(sach.getSoLuongKho()), danhGia, binhLuan));
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.d("onCancelled", "Lỗi!" + error.getMessage());
                        }
                    });
                } else if (xuatKho.getMaSanPham().contains("vpp")) {
                    vanPhongPhamDatabase.child(xuatKho.getMaSanPham()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            VanPhongPham vanPhongPham = snapshot.getValue(VanPhongPham.class);
                            theoDoiDonHangSanPhams.add(new TheoDoiDonHangSanPham(vanPhongPham.getMaVanPhongPham(), Integer.valueOf(vanPhongPham.getGiaTien()), Integer.valueOf(vanPhongPham.getKhuyenMai()), Integer.valueOf(vanPhongPham.getSoLuongKho())));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.d("onCancelled", "Lỗi!" + error.getMessage());
                        }
                    });
                }
                danhGia = tongDanhGia / soLuongDanhGia;
            }

                            sanPhams.add(new

            SanPham(vanPhongPham.getMaVanPhongPham(),vanPhongPham.

            getTenVanPhongPham(),vanPhongPham.

            getHinhVanPhongPham(),"",vanPhongPham.getXuatXu(),Integer.valueOf(vanPhongPham.getGiaTien()),Integer.valueOf(vanPhongPham.getSoLuongKho()),danhGia,binhLuan));
                            adapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Log.d("onCancelled", "Lỗi!" + error.getMessage());
        }
    }
*/

    ///////////////////////////////////////////////////////

    //Tho
    // Man hinh chi tiet san pham
    public void hienThiChiTietSanPham(String maSanPham, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference phanHoiDatabase = firebaseDatabase.getReference("PHANHOI");
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        if (maSanPham.contains("s")) {
            sanPhamDatabase.child("SACH").child(maSanPham).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Sach sach = snapshot.getValue(Sach.class);
                    phanHoiDatabase.child(sach.getMaSach()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int tongDanhGia = 0;
                            int soLuongDanhGia = 0;
                            int danhGia = 0;
                            int binhLuan = 0;
                            for (DataSnapshot phanHoiDataSnapshot : snapshot.getChildren()) {
                                PhanPhoi phanPhoi = phanHoiDataSnapshot.getValue(PhanPhoi.class);
                                if (phanPhoi.getDanhGia() != null) {
                                    tongDanhGia += Integer.valueOf(phanPhoi.getDanhGia());
                                    soLuongDanhGia++;
                                }
                                if (phanPhoi.getBinhLuan() != null) {
                                    binhLuan++;
                                }
                                danhGia = tongDanhGia / soLuongDanhGia;
                            }
                            ((ChiTietSanPhamActivity) context).thongTinSanPham(sach, null, danhGia, binhLuan);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.d("onCancelled", "Lỗi!" + error.getMessage());
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("onCancelled", "Lỗi!" + error.getMessage());
                }
            });
        } else if (maSanPham.contains("vpp")) {
            sanPhamDatabase.child("VANPHONGPHAM").child(maSanPham).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    VanPhongPham vanPhongPham = snapshot.getValue(VanPhongPham.class);
                    phanHoiDatabase.child(vanPhongPham.getMaVanPhongPham()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int tongDanhGia = 0;
                            int soLuongDanhGia = 0;
                            int danhGia = 0;
                            int binhLuan = 0;
                            for (DataSnapshot phanHoiDataSnapshot : snapshot.getChildren()) {
                                PhanPhoi phanPhoi = phanHoiDataSnapshot.getValue(PhanPhoi.class);
                                if (phanPhoi.getDanhGia() != null) {
                                    tongDanhGia += Integer.valueOf(phanPhoi.getDanhGia());
                                    soLuongDanhGia++;
                                }
                                if (phanPhoi.getBinhLuan() != null) {
                                    binhLuan++;
                                }
                                danhGia = tongDanhGia / soLuongDanhGia;
                            }
                            ((ChiTietSanPhamActivity) context).thongTinSanPham(null, vanPhongPham, danhGia, binhLuan);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.d("onCancelled", "Lỗi!" + error.getMessage());
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("onCancelled", "Lỗi!" + error.getMessage());
                }
            });
        }
    }

    //Man hinh chinh khach hang
    public void hienThiManHinhChinhKhachHang(ArrayList<ItemSanPham> sanPhams, ManHinhChinhKhachHangAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference phanHoiDatabase = firebaseDatabase.getReference("PHANHOI");
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        sanPhamDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sanPhams.clear();
                sanPhamDatabase.child("SACH").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot sachDataSnapshot : snapshot.getChildren()) {
                            Sach sach = sachDataSnapshot.getValue(Sach.class);
                            phanHoiDatabase.child(sach.getMaSach()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    int tongDanhGia = 0;
                                    int soLuongDanhGia = 0;
                                    int danhGia = 0;
                                    int binhLuan = 0;
                                    for (DataSnapshot phanHoiDataSnapshot : snapshot.getChildren()) {
                                        PhanPhoi phanPhoi = phanHoiDataSnapshot.getValue(PhanPhoi.class);
                                        if (phanPhoi.getDanhGia() != null) {
                                            tongDanhGia += Integer.valueOf(phanPhoi.getDanhGia());
                                            soLuongDanhGia++;
                                        }
                                        if (phanPhoi.getBinhLuan() != null) {
                                            binhLuan++;
                                        }
                                        danhGia = tongDanhGia / soLuongDanhGia;
                                    }
                                    sanPhams.add(new ItemSanPham(sach.getMaSach(), sach.getTenSach(), sach.getHinhSach(), sach.getTacGia(), "", "", "", "", "", "", Integer.valueOf(sach.getGiaTien()), 0, danhGia, binhLuan));
                                    adapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Log.d("onCancelled", "Lỗi!" + error.getMessage());
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                sanPhamDatabase.child("VANPHONGPHAM").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot vanPhongPhamDataSnapshot : snapshot.getChildren()) {
                            VanPhongPham vanPhongPham = vanPhongPhamDataSnapshot.getValue(VanPhongPham.class);
                            phanHoiDatabase.child(vanPhongPham.getMaVanPhongPham()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    int tongDanhGia = 0;
                                    int soLuongDanhGia = 0;
                                    int danhGia = 0;
                                    int binhLuan = 0;
                                    for (DataSnapshot phanHoiDataSnapshot : snapshot.getChildren()) {
                                        PhanPhoi phanPhoi = phanHoiDataSnapshot.getValue(PhanPhoi.class);
                                        if (phanPhoi.getDanhGia() != null) {
                                            tongDanhGia += Integer.valueOf(phanPhoi.getDanhGia());
                                            soLuongDanhGia++;
                                        }
                                        if (phanPhoi.getBinhLuan() != null) {
                                            binhLuan++;
                                        }
                                        danhGia = tongDanhGia / soLuongDanhGia;
                                    }
                                    sanPhams.add(new ItemSanPham(vanPhongPham.getMaVanPhongPham(), vanPhongPham.getTenVanPhongPham(), vanPhongPham.getHinhVanPhongPham(), "", vanPhongPham.getXuatXu(), "", "", "", vanPhongPham.getNhaPhanPhoi(), vanPhongPham.getDonVi(), Integer.valueOf(vanPhongPham.getGiaTien()), 0, danhGia, binhLuan));
                                    adapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Log.d("onCancelled", "Lỗi!" + error.getMessage());
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });

    }

    //Them so luong vao gio hang
    public void themVaoGioHang(String maKhachHang, String maSanPham, String soLuong) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference gioHangDatabase = firebaseDatabase.getReference("GIOHANG");
        gioHangDatabase.child(maKhachHang).child(maSanPham).child("soLuong").setValue(soLuong);
        gioHangDatabase.child(maKhachHang).child(maSanPham).child("maKhachHang").setValue(maKhachHang);
        gioHangDatabase.child(maKhachHang).child(maSanPham).child("maSanPham").setValue(maSanPham);
    }

    // Man hien thi danh sach nhan vien
    public void hienThiManHinhChinhQuanLyNhanVien(ArrayList<ItemNhanVien> nhanViens, NhanVienRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nguoiDungDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nhanViens.clear();
                nguoiDungDatabase.child("nhanvien").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot sachDataSnapshot : snapshot.getChildren()) {
                            NhanVien nhanVien = sachDataSnapshot.getValue(NhanVien.class);
                            nhanViens.add(new ItemNhanVien(
                                    nhanVien.getNguoiDung(),
                                    nhanVien.getMaNhanVien(),
                                    nhanVien.getTenNhanVien(),
                                    nhanVien.getCmnd(),
                                    nhanVien.getDiaChi(),
                                    nhanVien.getEmail(),
                                    nhanVien.getHinhNhanVien(),
                                    nhanVien.getLuong(),
                                    nhanVien.getMatKhau(),
                                    nhanVien.getSoDienThoai(),
                                    nhanVien.getTaiKhoan()
                            ));
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("onCancelled", "Lỗi!" + error.getMessage());
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });

    }

    // Man hinh them nhan vien
    public void themNhanVien(ArrayList<ItemNhanVien> itemNhanVien , Context context){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nguoiDungDatabase.child("nhanvien").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NhanVien nhanvien = snapshot.getValue(NhanVien.class);
                itemNhanVien.add(new ItemNhanVien(
                        nhanvien.getNguoiDung(),
                        nhanvien.getMaNhanVien(),
                        nhanvien.getTenNhanVien(),
                        nhanvien.getCmnd(),
                        nhanvien.getDiaChi(),
                        nhanvien.getEmail(),
                        nhanvien.getHinhNhanVien(),
                        nhanvien.getLuong(),
                        nhanvien.getMatKhau(),
                        nhanvien.getSoDienThoai(),
                        nhanvien.getTaiKhoan()
                ));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    // Xoa nhan vien
    public void xoaNhanVien(String maNhanVien,  NhanVienRecyclerViewAdapter adapter) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference gioHangDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        gioHangDatabase.child("nhanvien").child(maNhanVien).removeValue();
    }

}
