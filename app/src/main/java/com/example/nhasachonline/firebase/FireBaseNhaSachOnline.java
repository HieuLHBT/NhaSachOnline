package com.example.nhasachonline.firebase;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nhasachonline.activity.GioHangActivity;
import com.example.nhasachonline.activity.ThanhToanActivity;
import com.example.nhasachonline.adapters.GioHangRecyclerViewAdapter;
import com.example.nhasachonline.adapters.ThanhToanRecyclerViewAdapter;
import com.example.nhasachonline.adapters.TheoDoiDonHangRecyclerViewAdapter;
import com.example.nhasachonline.data_model.DonHang;
import com.example.nhasachonline.data_model.GioHang;
import com.example.nhasachonline.data_model.KhachHang;
import com.example.nhasachonline.data_model.NhanVien;
import com.example.nhasachonline.data_model.Sach;
import com.example.nhasachonline.data_model.TrangThaiDonHang;
import com.example.nhasachonline.data_model.VanPhongPham;
import com.example.nhasachonline.data_model.XuatKho;
import com.example.nhasachonline.item.ChiTietGiaoHang;
import com.example.nhasachonline.item.ThanhToan;
import com.example.nhasachonline.item.TheoDoiDonHang;
import com.example.nhasachonline.item.TheoDoiDonHangSanPham;
import com.example.nhasachonline.tools.SharePreferences;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FireBaseNhaSachOnline {
    private SharePreferences sharePreferences = new SharePreferences();

    public void xoaSanPhamGioHang(String maKhachHang, String maSanpham, GioHangRecyclerViewAdapter adapter) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference gioHangDatabase = firebaseDatabase.getReference("GIOHANG");
        gioHangDatabase.child(maKhachHang).child(maSanpham).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                adapter.notifyDataSetChanged();
            }
        });
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
                        gioHangDatabase.child(maKhachHang).child(gioHang.getMaSanPham()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                adapter.notifyDataSetChanged();
                                ((GioHangActivity) context).TongTienThanhToan();
                            }
                        });
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

    public void hienThiItemThanhToan(String maDonHang, ArrayList<ThanhToan> thanhToan, ThanhToanRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference xuatKhoDatabase = firebaseDatabase.getReference("XUATKHO");
        DatabaseReference sachDatabase = firebaseDatabase.getReference("SACH");
        DatabaseReference vanPhongPhamDatabase = firebaseDatabase.getReference("VANPHONGPHAM");
        xuatKhoDatabase.child(maDonHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                thanhToan.clear();
                for (DataSnapshot xuatKhoSnapshot : snapshot.getChildren()) {
                    XuatKho xuatKho = xuatKhoSnapshot.getValue(XuatKho.class);
                    if (xuatKho.getMaSanPham().contains("s")) {
                        sachDatabase.child(xuatKho.getMaSanPham()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Sach sach = snapshot.getValue(Sach.class);
                                thanhToan.add(new ThanhToan(sach.getMaSach(), sach.getTenSach(), Integer.valueOf(sach.getGiaTien()), Integer.valueOf(xuatKho.getSoLuongXuat()), sach.getHinhSach()));
                                adapter.notifyDataSetChanged();
                                ((ThanhToanActivity) context).tongTien();
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
                                thanhToan.add(new ThanhToan(vanPhongPham.getMaVanPhongPham(), vanPhongPham.getTenVanPhongPham(), Integer.valueOf(vanPhongPham.getGiaTien()), Integer.valueOf(xuatKho.getSoLuongXuat()), vanPhongPham.getHinhVanPhongPham()));
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
                Log.d("test", kh.getDiaChi() + "");
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

/*
    public void hienThiTheoDoiDonHang(String maKhachHang, ArrayList<TheoDoiDonHang> theoDoiDonHangItem, TheoDoiDonHangRecyclerViewAdapter adapter, Context context) {
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
                for (DonHang donHang: dsDonHangKhachHang) {
                    ArrayList<TheoDoiDonHangSanPham> theoDoiDonHangSanPhams = new ArrayList<>();
                    xuatKhoDatabase.child(donHang.getMaDonHang()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot xuatKhoDataSnapshot : snapshot.getChildren()) {
                                XuatKho xuatKho = xuatKhoDataSnapshot.getValue(XuatKho.class);
                                if (xuatKho.getMaSanPham().contains("s")) {
                                    sachDatabase.child(xuatKho.getMaSanPham()).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            Sach sach = snapshot.getValue(Sach.class);
                                            theoDoiDonHangSanPhams.add(new TheoDoiDonHangSanPham(sach.getMaSach(), Integer.valueOf(sach.getGiaTien()), Integer.valueOf(sach.getKhuyenMai()),Integer.valueOf(sach.getSoLuongKho())));
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
                                            theoDoiDonHangSanPhams.add(new TheoDoiDonHangSanPham(vanPhongPham.getMaVanPhongPham(), Integer.valueOf(vanPhongPham.getGiaTien()), Integer.valueOf(vanPhongPham.getKhuyenMai()),Integer.valueOf(vanPhongPham.getSoLuongKho())));
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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }
*/
}
