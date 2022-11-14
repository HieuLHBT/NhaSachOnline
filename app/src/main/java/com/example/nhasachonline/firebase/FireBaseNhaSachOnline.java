package com.example.nhasachonline.firebase;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nhasachonline.activity.BangChamCongActivity;
import com.example.nhasachonline.activity.ChiTietDonHangNVActivity;
import com.example.nhasachonline.activity.ChiTietGiaoHangActivity;
import com.example.nhasachonline.activity.ChiTietSanPhamActivity;
import com.example.nhasachonline.activity.GioHangActivity;
import com.example.nhasachonline.activity.LichLamViecActivity;
import com.example.nhasachonline.activity.ManHinhChinhQuanLyActivity;
import com.example.nhasachonline.activity.PhanHoiYKienKhachHangActivity;
import com.example.nhasachonline.activity.SuaNhanVienActivity;
import com.example.nhasachonline.activity.SuaSanPhamSachActivity;
import com.example.nhasachonline.activity.SuaSanPhamVPPActivity;
import com.example.nhasachonline.activity.ThemNhanVienActivity;
import com.example.nhasachonline.activity.MaGiamGiaActivity;
import com.example.nhasachonline.activity.ManHinhChinhNhanVienActivity;
import com.example.nhasachonline.activity.SuaNhanVienActivity;
import com.example.nhasachonline.activity.ThanhToanActivity;
import com.example.nhasachonline.activity.ThongBaoHuyDonHangActivity;
import com.example.nhasachonline.activity.ThongTinGiaoHangNVActivity;
import com.example.nhasachonline.activity.TraLoiBinhLuanActivity;
import com.example.nhasachonline.adapters.ChiTietDonDaGiaoRecyclerViewAdapter;
import com.example.nhasachonline.adapters.ChiTietDonDaHuyRecyclerViewAdapter;
import com.example.nhasachonline.adapters.ChiTietDonDaNhanRecyclerViewAdapter;
import com.example.nhasachonline.adapters.ChiTietDonHangNVRecyclerViewAdapter;
import com.example.nhasachonline.adapters.ChiTietGiaoHangRecyclerViewAdapter;
import com.example.nhasachonline.adapters.DanhGiaSanPhamRecyclerViewAdapter;
import com.example.nhasachonline.adapters.GioHangRecyclerViewAdapter;
import com.example.nhasachonline.adapters.MHNVXacNhanDonHangItemRycyclerViewAdapter;
import com.example.nhasachonline.adapters.MaGiamGiaRecyclerViewAdapter;
import com.example.nhasachonline.adapters.ManHinhChinhKhachHangAdapter;
import com.example.nhasachonline.adapters.ManHinhChinhNhanVienRecyclerViewAdapter;
import com.example.nhasachonline.adapters.NhanVienRecyclerViewAdapter;
import com.example.nhasachonline.adapters.PhanHoiYKienKhachHangRecyclerViewAdapter;
import com.example.nhasachonline.adapters.QuanLyDonHangNVRecyclerViewAdapter;
import com.example.nhasachonline.adapters.QuanLySanPhamNVRecyclerViewAdapter;
import com.example.nhasachonline.adapters.SanPhamRecyclerViewAdapter;
import com.example.nhasachonline.adapters.ThanhToanRecyclerViewAdapter;
import com.example.nhasachonline.data_model.ChamCong;
import com.example.nhasachonline.data_model.GiamGia;
import com.example.nhasachonline.data_model.DonHang;
import com.example.nhasachonline.data_model.GioHang;
import com.example.nhasachonline.data_model.KhachHang;
import com.example.nhasachonline.data_model.NhanVien;
import com.example.nhasachonline.data_model.PhanHoi;
import com.example.nhasachonline.data_model.QuanLy;
import com.example.nhasachonline.data_model.Sach;
import com.example.nhasachonline.data_model.TrangThaiDonHang;
import com.example.nhasachonline.data_model.VanPhongPham;
import com.example.nhasachonline.data_model.XuatKho;
import com.example.nhasachonline.item.BangChamCong;
import com.example.nhasachonline.item.BangLuong;
import com.example.nhasachonline.item.ChiTietDonDaGiao;
import com.example.nhasachonline.item.ChiTietDonDaHuy;
import com.example.nhasachonline.item.ChiTietDonDaNhan;
import com.example.nhasachonline.item.ChiTietGiaoHang;
import com.example.nhasachonline.item.DanhGia;
import com.example.nhasachonline.item.ItemChiTietDonHangNV;
import com.example.nhasachonline.item.ItemKhachHang;
import com.example.nhasachonline.item.ItemManHinhChinhNhanVien;
import com.example.nhasachonline.item.ItemNhanVien;
import com.example.nhasachonline.item.ItemQuanLyDonHangNV;
import com.example.nhasachonline.item.ItemQuanLySanPhamNV;
import com.example.nhasachonline.item.ItemSanPham;
import com.example.nhasachonline.item.PhanHoiYKienKhachHang;
import com.example.nhasachonline.item.SanPhamTinhTongTien;
import com.example.nhasachonline.item.ThanhToan;
import com.example.nhasachonline.item.TheoDoiDonHang;
import com.example.nhasachonline.tools.SharePreferences;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
        gioHangDatabase.child(maKhachHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<GioHang> gioHangs = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    GioHang gioHang = dataSnapshot.getValue(GioHang.class);
                    gioHangs.add(gioHang);
                }
                sanPhamDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        gioHangItem.clear();
                        for (GioHang gioHang : gioHangs) {
                            if (gioHang.getMaSanPham().contains("s")) {
                                Sach sach = snapshot.child("SACH").child(gioHang.getMaSanPham()).getValue(Sach.class);
                                gioHangItem.add(new com.example.nhasachonline.item.GioHang(
                                        sach.getMaSach(),
                                        sach.getTenSach(),
                                        Integer.valueOf(sach.getGiaTien()),
                                        Integer.valueOf(sach.getKhuyenMai()),
                                        Integer.valueOf(gioHang.getSoLuong()),
                                        sach.getHinhSach()));
                            } else if (gioHang.getMaSanPham().contains("vpp")) {
                                VanPhongPham vanPhongPham = snapshot.child("VANPHONGPHAM").child(gioHang.getMaSanPham()).getValue(VanPhongPham.class);
                                gioHangItem.add(new com.example.nhasachonline.item.GioHang(
                                        vanPhongPham.getMaVanPhongPham(),
                                        vanPhongPham.getTenVanPhongPham(),
                                        Integer.valueOf(vanPhongPham.getGiaTien()),
                                        Integer.valueOf(vanPhongPham.getKhuyenMai()),
                                        Integer.valueOf(gioHang.getSoLuong()),
                                        vanPhongPham.getHinhVanPhongPham()));
                            }
                        }
                        Log.d("test", gioHangItem.size() + "");
                        adapter.notifyDataSetChanged();
                        ((GioHangActivity) context).TongTienThanhToan();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
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

    public void hienThiGiamGia(String maKhachHang, GiamGia giamGia, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference giamgiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        giamgiaDatabase.child(maKhachHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot giamGiaDataSnapshot : snapshot.getChildren()) {
                    GiamGia giamGiaDaTa = giamGiaDataSnapshot.getValue(GiamGia.class);
                    if (giamGiaDaTa.getChon().equalsIgnoreCase("1")) {
                        giamGia.setMaKhachHang(giamGiaDaTa.getMaKhachHang());
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

    public void xoaChonGiamGia(String maKhachHang, String maGiamGia) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference giamGiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        giamGiaDatabase.child(maKhachHang).child(maGiamGia).child("chon").setValue("0");
    }

    public void chonGiamGia(String maKhachHang, String maGiamGia) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference giamGiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        giamGiaDatabase.child(maKhachHang).child(maGiamGia).child("chon").setValue("1");
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
                    giamGiaDatabase.child(donHang.getMaKhachHang()).child(donHang.getMaGiamGia()).child("chon").setValue("0").addOnCompleteListener(new OnCompleteListener<Void>() {
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

    public void huyThanhToan(String maKhachHang, String maGiamGia, String maDonHang, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference giamGiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        DatabaseReference xuatKhoDatabase = firebaseDatabase.getReference("XUATKHO");
        xuatKhoDatabase.child(maDonHang).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (maGiamGia != null) {
                    giamGiaDatabase.child(maKhachHang).child(maGiamGia).child("chon").setValue("0").addOnCompleteListener(new OnCompleteListener<Void>() {
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
    public void hienThiMaGiamGia(Integer tongTien, String maKhachHang, ArrayList<GiamGia> giamGias, MaGiamGiaRecyclerViewAdapter adapter) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference giamgiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        giamgiaDatabase.child(maKhachHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                giamGias.clear();
                for (DataSnapshot giamGiaDataSnapshot : snapshot.getChildren()) {
                    GiamGia giamGia = giamGiaDataSnapshot.getValue(GiamGia.class);
                    if (Integer.valueOf(giamGia.getYeuCau()) > tongTien) {
                        giamGia.setKiemTra(true);
                    }
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

    // Bảng chấm công
    public void hienThiBangChamCong(String maNhanVien, ArrayList<ChamCong> chamCongs, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference chamCongDatabase = firebaseDatabase.getReference("CHAMCONG");
        chamCongDatabase.child(maNhanVien).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chamCongs.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ChamCong chamCong = dataSnapshot.getValue(ChamCong.class);
                    if (chamCong.getTrangThaiPhanCong().equalsIgnoreCase("Đã phân công")
                            && (!chamCong.getCa1().equalsIgnoreCase("") && !chamCong.getGioVaoCa1().equalsIgnoreCase("") && !chamCong.getGioRaCa1().equalsIgnoreCase("")
                            || !chamCong.getCa2().equalsIgnoreCase("") && !chamCong.getGioVaoCa2().equalsIgnoreCase("") && !chamCong.getGioRaCa2().equalsIgnoreCase(""))) {
                        chamCongs.add(chamCong);
                    }
                }
                ((BangChamCongActivity) context).hienThiNgay();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void hienThiDon(String maNhanVien, String ngay, BangChamCong bangChamCong, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        DatabaseReference trangThaiDonHangDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        donHangDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bangChamCong.setSoDonDaNhan(0);
                bangChamCong.setSoDonDaGiao(0);
                bangChamCong.setSoDonDaHuy(0);
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DonHang donHang = dataSnapshot.getValue(DonHang.class);
                    trangThaiDonHangDatabase.child(donHang.getMaDonHang()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            TrangThaiDonHang trangThaiDonHang = snapshot.getValue(TrangThaiDonHang.class);
                            if (donHang.getMaNVDuyet().equalsIgnoreCase(maNhanVien) && donHang.getThoiGianLap().equalsIgnoreCase(ngay)) {
                                bangChamCong.setSoDonDaNhan(bangChamCong.getSoDonDaNhan() + 1);
                                if (trangThaiDonHang.getTrangThaiDon().equalsIgnoreCase("Hủy") && !donHang.getMaNVGiao().equalsIgnoreCase(maNhanVien)) {
                                    bangChamCong.setSoDonDaHuy(bangChamCong.getSoDonDaHuy() + 1);
                                }
                            }
                            if (donHang.getMaNVGiao().equalsIgnoreCase(maNhanVien) && donHang.getThoiGianGiao().equalsIgnoreCase(ngay)) {
                                if (trangThaiDonHang.getTrangThaiDon().equalsIgnoreCase("Thành công")) {
                                    bangChamCong.setSoDonDaGiao(bangChamCong.getSoDonDaGiao() + 1);
                                } else if (trangThaiDonHang.getTrangThaiDon().equalsIgnoreCase("Hủy")) {
                                    bangChamCong.setSoDonDaHuy(bangChamCong.getSoDonDaHuy() + 1);
                                }
                            }
                            ((BangChamCongActivity) context).hienThiDon();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    // Chi tiết đơn đã nhận
    public void hienThiDonDaNhan(String maNhanVien, String ngay, ArrayList<ChiTietDonDaNhan> chiTietDonDaNhans, ChiTietDonDaNhanRecyclerViewAdapter adapter) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        DatabaseReference trangThaiDonHangDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        DatabaseReference giamGiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        DatabaseReference xuatKhoDatabase = firebaseDatabase.getReference("XUATKHO");
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        donHangDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chiTietDonDaNhans.clear();
                for (DataSnapshot donHangDataSnapshot : snapshot.getChildren()) {
                    DonHang donHang = donHangDataSnapshot.getValue(DonHang.class);
                    if (donHang.getMaNVDuyet().equalsIgnoreCase(maNhanVien) && donHang.getThoiGianLap().equalsIgnoreCase(ngay)) {
                        ArrayList<String> ten = new ArrayList<>();

                        nguoiDungDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot nguoiDungDataSnapshot : snapshot.child("khachhang").getChildren()) {
                                    if (nguoiDungDataSnapshot.getKey().equalsIgnoreCase(donHang.getMaKhachHang())) {
                                        KhachHang khachHang = nguoiDungDataSnapshot.getValue(KhachHang.class);
                                        ten.add(khachHang.getTenKhachHang());
                                        break;
                                    }
                                }
                                for (DataSnapshot nguoiDungDataSnapshot : snapshot.child("nhanvien").getChildren()) {
                                    if (nguoiDungDataSnapshot.getKey().equalsIgnoreCase(donHang.getMaNVDuyet())) {
                                        NhanVien nhanVien = nguoiDungDataSnapshot.getValue(NhanVien.class);
                                        ten.add(nhanVien.getTenNhanVien());
                                        break;
                                    }
                                }
                                if (donHang.getMaNVDuyet().equalsIgnoreCase(donHang.getMaNVGiao())) {
                                    ten.add(ten.get(1));
                                } else {
                                    for (DataSnapshot nguoiDungDataSnapshot : snapshot.child("nhanvien").getChildren()) {
                                        if (nguoiDungDataSnapshot.getKey().equalsIgnoreCase(donHang.getMaNVGiao())) {
                                            NhanVien nhanVien = nguoiDungDataSnapshot.getValue(NhanVien.class);
                                            ten.add(nhanVien.getTenNhanVien());
                                            break;
                                        }
                                    }
                                }

                                giamGiaDatabase.child(donHang.getMaKhachHang()).child(donHang.getMaGiamGia()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        GiamGia giamGia = snapshot.getValue(GiamGia.class);

                                        trangThaiDonHangDatabase.child(donHang.getMaDonHang()).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                TrangThaiDonHang trangThaiDonHang = snapshot.getValue(TrangThaiDonHang.class);

                                                xuatKhoDatabase.child(donHang.getMaDonHang()).addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        ArrayList<SanPhamTinhTongTien> sanPhamTinhTongTiens = new ArrayList<>();
                                                        ArrayList<XuatKho> xuatKhos = new ArrayList<>();
                                                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                                            XuatKho xuatKho = dataSnapshot.getValue(XuatKho.class);
                                                            xuatKhos.add(xuatKho);
                                                        }

                                                        sanPhamDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                for (XuatKho xuatKho : xuatKhos) {
                                                                    if (xuatKho.getMaSanPham().contains("s")) {
                                                                        Sach sach = snapshot.child("SACH").child(xuatKho.getMaSanPham()).getValue(Sach.class);
                                                                        sanPhamTinhTongTiens.add(new SanPhamTinhTongTien(
                                                                                sach.getMaSach(),
                                                                                Integer.valueOf(sach.getGiaTien()),
                                                                                Integer.valueOf(sach.getKhuyenMai()),
                                                                                Integer.valueOf(xuatKho.getSoLuongXuat())));
                                                                    } else if (xuatKho.getMaSanPham().contains("vpp")) {
                                                                        VanPhongPham vanPhongPham = snapshot.child("VANPHONGPHAM").child(xuatKho.getMaSanPham()).getValue(VanPhongPham.class);
                                                                        sanPhamTinhTongTiens.add(new SanPhamTinhTongTien(
                                                                                vanPhongPham.getMaVanPhongPham(),
                                                                                Integer.valueOf(vanPhongPham.getGiaTien()),
                                                                                Integer.valueOf(vanPhongPham.getKhuyenMai()),
                                                                                Integer.valueOf(xuatKho.getSoLuongXuat())));
                                                                    }
                                                                }
                                                                int tongTien = 0;
                                                                for (SanPhamTinhTongTien sanPhamTinhTongTien : sanPhamTinhTongTiens) {
                                                                    tongTien += sanPhamTinhTongTien.getTongTien();
                                                                }
                                                                if (tongTien != 0) {
                                                                    tongTien = tongTien + Integer.valueOf(donHang.getPhiVanChuyen()) - Integer.valueOf(giamGia.getTienGiamGia());
                                                                }
                                                                ChiTietDonDaNhan chiTietDonDaNhan = new ChiTietDonDaNhan(
                                                                        donHang.getMaDonHang(),
                                                                        ten.get(0),
                                                                        ten.get(1),
                                                                        ten.get(2),
                                                                        donHang.getThoiGianLap(),
                                                                        Integer.valueOf(donHang.getPhiVanChuyen()),
                                                                        Integer.valueOf(giamGia.getTienGiamGia()),
                                                                        donHang.getDiaChiGiao(),
                                                                        trangThaiDonHang.getKieuThanhToan(),
                                                                        trangThaiDonHang.getTrangThaiDon(),
                                                                        Integer.valueOf(tongTien));
                                                                chiTietDonDaNhans.add(chiTietDonDaNhan);
                                                                adapter.notifyDataSetChanged();
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {
                                                            }
                                                        });
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                    }
                                                });
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
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

    // Chi tiết đơn đã giao
    public void hienThiDonDaGiao(String maNhanVien, String ngay, ArrayList<ChiTietDonDaGiao> chiTietDonDaGiaos, ChiTietDonDaGiaoRecyclerViewAdapter adapter) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        DatabaseReference trangThaiDonHangDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        DatabaseReference giamGiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        DatabaseReference xuatKhoDatabase = firebaseDatabase.getReference("XUATKHO");
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        donHangDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chiTietDonDaGiaos.clear();
                for (DataSnapshot donHangDataSnapshot : snapshot.getChildren()) {
                    DonHang donHang = donHangDataSnapshot.getValue(DonHang.class);
                    if (donHang.getMaNVGiao().equalsIgnoreCase(maNhanVien) && donHang.getThoiGianGiao().equalsIgnoreCase(ngay)) {
                        ArrayList<String> ten = new ArrayList<>();

                        nguoiDungDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot nguoiDungDataSnapshot : snapshot.child("khachhang").getChildren()) {
                                    if (nguoiDungDataSnapshot.getKey().equalsIgnoreCase(donHang.getMaKhachHang())) {
                                        KhachHang khachHang = nguoiDungDataSnapshot.getValue(KhachHang.class);
                                        ten.add(khachHang.getTenKhachHang());
                                        break;
                                    }
                                }
                                for (DataSnapshot nguoiDungDataSnapshot : snapshot.child("nhanvien").getChildren()) {
                                    if (nguoiDungDataSnapshot.getKey().equalsIgnoreCase(donHang.getMaNVDuyet())) {
                                        NhanVien nhanVien = nguoiDungDataSnapshot.getValue(NhanVien.class);
                                        ten.add(nhanVien.getTenNhanVien());
                                        break;
                                    }
                                }
                                if (donHang.getMaNVDuyet().equalsIgnoreCase(donHang.getMaNVGiao())) {
                                    ten.add(ten.get(1));
                                } else {
                                    for (DataSnapshot nguoiDungDataSnapshot : snapshot.child("nhanvien").getChildren()) {
                                        if (nguoiDungDataSnapshot.getKey().equalsIgnoreCase(donHang.getMaNVGiao())) {
                                            NhanVien nhanVien = nguoiDungDataSnapshot.getValue(NhanVien.class);
                                            ten.add(nhanVien.getTenNhanVien());
                                            break;
                                        }
                                    }
                                }

                                giamGiaDatabase.child(donHang.getMaKhachHang()).child(donHang.getMaGiamGia()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        GiamGia giamGia = snapshot.getValue(GiamGia.class);

                                        trangThaiDonHangDatabase.child(donHang.getMaDonHang()).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                TrangThaiDonHang trangThaiDonHang = snapshot.getValue(TrangThaiDonHang.class);

                                                xuatKhoDatabase.child(donHang.getMaDonHang()).addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        ArrayList<SanPhamTinhTongTien> sanPhamTinhTongTiens = new ArrayList<>();
                                                        ArrayList<XuatKho> xuatKhos = new ArrayList<>();
                                                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                                            XuatKho xuatKho = dataSnapshot.getValue(XuatKho.class);
                                                            xuatKhos.add(xuatKho);
                                                        }

                                                        sanPhamDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                for (XuatKho xuatKho : xuatKhos) {
                                                                    if (xuatKho.getMaSanPham().contains("s")) {
                                                                        Sach sach = snapshot.child("SACH").child(xuatKho.getMaSanPham()).getValue(Sach.class);
                                                                        sanPhamTinhTongTiens.add(new SanPhamTinhTongTien(
                                                                                sach.getMaSach(),
                                                                                Integer.valueOf(sach.getGiaTien()),
                                                                                Integer.valueOf(sach.getKhuyenMai()),
                                                                                Integer.valueOf(xuatKho.getSoLuongXuat())));
                                                                    } else if (xuatKho.getMaSanPham().contains("vpp")) {
                                                                        VanPhongPham vanPhongPham = snapshot.child("VANPHONGPHAM").child(xuatKho.getMaSanPham()).getValue(VanPhongPham.class);
                                                                        sanPhamTinhTongTiens.add(new SanPhamTinhTongTien(
                                                                                vanPhongPham.getMaVanPhongPham(),
                                                                                Integer.valueOf(vanPhongPham.getGiaTien()),
                                                                                Integer.valueOf(vanPhongPham.getKhuyenMai()),
                                                                                Integer.valueOf(xuatKho.getSoLuongXuat())));
                                                                    }
                                                                }
                                                                int tongTien = 0;
                                                                for (SanPhamTinhTongTien sanPhamTinhTongTien : sanPhamTinhTongTiens) {
                                                                    tongTien += sanPhamTinhTongTien.getTongTien();
                                                                }
                                                                if (tongTien != 0) {
                                                                    tongTien = tongTien + Integer.valueOf(donHang.getPhiVanChuyen()) - Integer.valueOf(giamGia.getTienGiamGia());
                                                                }
                                                                ChiTietDonDaGiao chiTietDonDaGiao = new ChiTietDonDaGiao(
                                                                        donHang.getMaDonHang(),
                                                                        ten.get(0),
                                                                        ten.get(1),
                                                                        ten.get(2),
                                                                        donHang.getThoiGianLap(),
                                                                        donHang.getThoiGianGiao(),
                                                                        Integer.valueOf(donHang.getPhiVanChuyen()),
                                                                        Integer.valueOf(giamGia.getTienGiamGia()),
                                                                        donHang.getDiaChiGiao(),
                                                                        trangThaiDonHang.getKieuThanhToan(),
                                                                        trangThaiDonHang.getTrangThaiDon(),
                                                                        Integer.valueOf(tongTien));
                                                                chiTietDonDaGiaos.add(chiTietDonDaGiao);
                                                                adapter.notifyDataSetChanged();
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {
                                                            }
                                                        });
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                    }
                                                });
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
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

    // Chi tiết đơn đã hủy
    public void hienThiDonDaHuy(String maNhanVien, String ngay, ArrayList<ChiTietDonDaHuy> chiTietDonDaHuys, ChiTietDonDaHuyRecyclerViewAdapter adapter) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        DatabaseReference trangThaiDonHangDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        DatabaseReference giamGiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        DatabaseReference xuatKhoDatabase = firebaseDatabase.getReference("XUATKHO");
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        donHangDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chiTietDonDaHuys.clear();
                for (DataSnapshot donHangDataSnapshot : snapshot.getChildren()) {
                    DonHang donHang = donHangDataSnapshot.getValue(DonHang.class);
                    if ((donHang.getMaNVGiao().equalsIgnoreCase(maNhanVien) || donHang.getMaNVDuyet().equalsIgnoreCase(maNhanVien)) && donHang.getThoiGianGiao().equalsIgnoreCase(ngay)) {
                        ArrayList<String> ten = new ArrayList<>();

                        nguoiDungDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot nguoiDungDataSnapshot : snapshot.child("khachhang").getChildren()) {
                                    if (nguoiDungDataSnapshot.getKey().equalsIgnoreCase(donHang.getMaKhachHang())) {
                                        KhachHang khachHang = nguoiDungDataSnapshot.getValue(KhachHang.class);
                                        ten.add(khachHang.getTenKhachHang());
                                        break;
                                    }
                                }
                                for (DataSnapshot nguoiDungDataSnapshot : snapshot.child("nhanvien").getChildren()) {
                                    if (nguoiDungDataSnapshot.getKey().equalsIgnoreCase(donHang.getMaNVDuyet())) {
                                        NhanVien nhanVien = nguoiDungDataSnapshot.getValue(NhanVien.class);
                                        ten.add(nhanVien.getTenNhanVien());
                                        break;
                                    }
                                }
                                if (donHang.getMaNVDuyet().equalsIgnoreCase(donHang.getMaNVGiao())) {
                                    ten.add(ten.get(1));
                                } else {
                                    for (DataSnapshot nguoiDungDataSnapshot : snapshot.child("nhanvien").getChildren()) {
                                        if (nguoiDungDataSnapshot.getKey().equalsIgnoreCase(donHang.getMaNVGiao())) {
                                            NhanVien nhanVien = nguoiDungDataSnapshot.getValue(NhanVien.class);
                                            ten.add(nhanVien.getTenNhanVien());
                                            break;
                                        }
                                    }
                                }

                                giamGiaDatabase.child(donHang.getMaKhachHang()).child(donHang.getMaGiamGia()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        GiamGia giamGia = snapshot.getValue(GiamGia.class);

                                        trangThaiDonHangDatabase.child(donHang.getMaDonHang()).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                TrangThaiDonHang trangThaiDonHang = snapshot.getValue(TrangThaiDonHang.class);

                                                xuatKhoDatabase.child(donHang.getMaDonHang()).addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        ArrayList<SanPhamTinhTongTien> sanPhamTinhTongTiens = new ArrayList<>();
                                                        ArrayList<XuatKho> xuatKhos = new ArrayList<>();
                                                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                                            XuatKho xuatKho = dataSnapshot.getValue(XuatKho.class);
                                                            xuatKhos.add(xuatKho);
                                                        }

                                                        sanPhamDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (trangThaiDonHang.getTrangThaiDon().equalsIgnoreCase("Hủy")) {
                                                                    for (XuatKho xuatKho : xuatKhos) {
                                                                        if (xuatKho.getMaSanPham().contains("s")) {
                                                                            Sach sach = snapshot.child("SACH").child(xuatKho.getMaSanPham()).getValue(Sach.class);
                                                                            sanPhamTinhTongTiens.add(new SanPhamTinhTongTien(
                                                                                    sach.getMaSach(),
                                                                                    Integer.valueOf(sach.getGiaTien()),
                                                                                    Integer.valueOf(sach.getKhuyenMai()),
                                                                                    Integer.valueOf(xuatKho.getSoLuongXuat())));
                                                                        } else if (xuatKho.getMaSanPham().contains("vpp")) {
                                                                            VanPhongPham vanPhongPham = snapshot.child("VANPHONGPHAM").child(xuatKho.getMaSanPham()).getValue(VanPhongPham.class);
                                                                            sanPhamTinhTongTiens.add(new SanPhamTinhTongTien(
                                                                                    vanPhongPham.getMaVanPhongPham(),
                                                                                    Integer.valueOf(vanPhongPham.getGiaTien()),
                                                                                    Integer.valueOf(vanPhongPham.getKhuyenMai()),
                                                                                    Integer.valueOf(xuatKho.getSoLuongXuat())));
                                                                        }
                                                                    }
                                                                    int tongTien = 0;
                                                                    for (SanPhamTinhTongTien sanPhamTinhTongTien : sanPhamTinhTongTiens) {
                                                                        tongTien += sanPhamTinhTongTien.getTongTien();
                                                                    }
                                                                    if (tongTien != 0) {
                                                                        tongTien = tongTien + Integer.valueOf(donHang.getPhiVanChuyen()) - Integer.valueOf(giamGia.getTienGiamGia());
                                                                    }
                                                                    ChiTietDonDaHuy chiTietDonDaHuy = new ChiTietDonDaHuy(
                                                                            donHang.getMaDonHang(),
                                                                            ten.get(0),
                                                                            ten.get(1),
                                                                            ten.get(2),
                                                                            donHang.getThoiGianLap(),
                                                                            Integer.valueOf(donHang.getPhiVanChuyen()),
                                                                            Integer.valueOf(giamGia.getTienGiamGia()),
                                                                            donHang.getDiaChiGiao(),
                                                                            trangThaiDonHang.getKieuThanhToan(),
                                                                            trangThaiDonHang.getTrangThaiDon(),
                                                                            Integer.valueOf(tongTien),
                                                                            trangThaiDonHang.getLyDoHuy());
                                                                    chiTietDonDaHuys.add(chiTietDonDaHuy);
                                                                    adapter.notifyDataSetChanged();
                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {
                                                            }
                                                        });
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                    }
                                                });
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
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

    // Bảng lương
    public void hienThiThangNam(String maNhanVien, ArrayList<ChamCong> chamCongs, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference chamCongDatabase = firebaseDatabase.getReference("CHAMCONG");
        chamCongDatabase.child(maNhanVien).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chamCongs.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ChamCong chamCong = dataSnapshot.getValue(ChamCong.class);
                    chamCongs.add(chamCong);
                }
                ((BangLuongActivity) context).hienThiThangNam();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void hienThiBangLuong(Date ngayHienTai, String thangNam, BangLuong bangLuong, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference chamCongDatabase = firebaseDatabase.getReference("CHAMCONG");
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        DatabaseReference trangThaiDonHangDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        chamCongDatabase.child(bangLuong.getMaNhanVien()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ChamCong> chamCongs = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ChamCong chamCong = dataSnapshot.getValue(ChamCong.class);
                    if (chamCong.getNgay().indexOf(thangNam) > 0) {
                        chamCongs.add(chamCong);
                    }
                }
                nguoiDungDatabase.child("nhanvien").child(bangLuong.getMaNhanVien()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        NhanVien nhanVien = snapshot.getValue(NhanVien.class);
                        bangLuong.setTenNhanVien(nhanVien.getTenNhanVien());
                        int tongGioLam = 0;
                        int tangCa = 0;
                        int diTre = 0;
                        int nghiCoPhep = 0;
                        int nghiKhongPhep = 0;
                        for (ChamCong chamCong : chamCongs) {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            Date ngayLuuTru = null;
                            try {
                                ngayLuuTru = sdf.parse(chamCong.getNgay());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if ((ngayHienTai.compareTo(ngayLuuTru)) > 0) {
                                if (!chamCong.getCa1().equalsIgnoreCase("") && !chamCong.getCa2().equalsIgnoreCase("")) {
                                    if (chamCong.getNghiPhep().equalsIgnoreCase("")
                                            && chamCong.getNghiKhongPhep().equalsIgnoreCase("")
                                            && !chamCong.getGioVaoCa1().equalsIgnoreCase("")
                                            && !chamCong.getGioRaCa1().equalsIgnoreCase("")
                                            && !chamCong.getGioVaoCa2().equalsIgnoreCase("")
                                            && !chamCong.getGioRaCa2().equalsIgnoreCase("")) {
                                        tongGioLam += Integer.valueOf(chamCong.getCa1()) + Integer.valueOf(chamCong.getCa2());
                                    } else if (chamCong.getNghiPhep().equalsIgnoreCase("ca1")
                                            && !chamCong.getGioVaoCa2().equalsIgnoreCase("")
                                            && !chamCong.getGioRaCa2().equalsIgnoreCase("")) {
                                        tongGioLam += Integer.valueOf(chamCong.getCa2());
                                    } else if (chamCong.getNghiPhep().equalsIgnoreCase("ca2")
                                            && !chamCong.getGioVaoCa1().equalsIgnoreCase("")
                                            && !chamCong.getGioRaCa1().equalsIgnoreCase("")) {
                                        tongGioLam += Integer.valueOf(chamCong.getCa1());
                                    } else if (chamCong.getNghiKhongPhep().equalsIgnoreCase("ca1")
                                            && !chamCong.getGioVaoCa2().equalsIgnoreCase("")
                                            && !chamCong.getGioRaCa2().equalsIgnoreCase("")) {
                                        tongGioLam += Integer.valueOf(chamCong.getCa2());
                                    } else if (chamCong.getNghiKhongPhep().equalsIgnoreCase("ca2")
                                            && !chamCong.getGioVaoCa1().equalsIgnoreCase("")
                                            && !chamCong.getGioRaCa1().equalsIgnoreCase("")) {
                                        tongGioLam += Integer.valueOf(chamCong.getCa1());
                                    }
                                } else if (!chamCong.getCa1().equalsIgnoreCase("")) {
                                    if (chamCong.getNghiPhep().equalsIgnoreCase("")
                                            && chamCong.getNghiKhongPhep().equalsIgnoreCase("")
                                            && !chamCong.getGioVaoCa1().equalsIgnoreCase("")
                                            && !chamCong.getGioRaCa1().equalsIgnoreCase("")) {
                                        tongGioLam += Integer.valueOf(chamCong.getCa1());
                                    }
                                } else if (!chamCong.getCa2().equalsIgnoreCase("")) {
                                    if (chamCong.getNghiPhep().equalsIgnoreCase("")
                                            && chamCong.getNghiKhongPhep().equalsIgnoreCase("")
                                            && !chamCong.getGioVaoCa2().equalsIgnoreCase("")
                                            && !chamCong.getGioRaCa2().equalsIgnoreCase("")) {
                                        tongGioLam += Integer.valueOf(chamCong.getCa2());
                                    }
                                }
                            }
                            if (!chamCong.getGioTangCa().equalsIgnoreCase("")) {
                                tangCa += Integer.valueOf(chamCong.getGioTangCa());
                            }
                            if (!chamCong.getThoiGianTre().equalsIgnoreCase("")) {
                                diTre += Integer.valueOf(chamCong.getThoiGianTre());
                            }
                            if (chamCong.getNghiPhep().equalsIgnoreCase("ca1, ca2")) {
                                nghiCoPhep += 2;
                            } else if (chamCong.getNghiPhep().equalsIgnoreCase("ca1")) {
                                nghiCoPhep++;
                            } else if (chamCong.getNghiPhep().equalsIgnoreCase("ca2")) {
                                nghiCoPhep++;
                            }
                            if (chamCong.getNghiKhongPhep().equalsIgnoreCase("ca1, ca2")) {
                                nghiKhongPhep += 2;
                            } else if (chamCong.getNghiKhongPhep().equalsIgnoreCase("ca1")) {
                                nghiKhongPhep++;
                            } else if (chamCong.getNghiKhongPhep().equalsIgnoreCase("ca2")) {
                                nghiKhongPhep++;
                            }
                        }
                        bangLuong.setTongGioLam(tongGioLam);
                        bangLuong.setTangCa(tangCa);
                        bangLuong.setDiTre(diTre);
                        bangLuong.setSoCaNghiCoPhep(nghiCoPhep);
                        bangLuong.setSoCaNghiKhongPhep(nghiKhongPhep);
                        bangLuong.setLuongCanBan(Integer.valueOf(nhanVien.getLuong()));

                        donHangDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                ArrayList<DonHang> donHangs = new ArrayList<>();
                                for (DataSnapshot donHangDataSnapshot : snapshot.getChildren()) {
                                    DonHang donHang = donHangDataSnapshot.getValue(DonHang.class);
                                    donHangs.add(donHang);
                                }

                                trangThaiDonHangDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        int tongDonDaGiao = 0;
                                        for (DonHang donHang : donHangs) {
                                            TrangThaiDonHang trangThaiDonHang = snapshot.child(donHang.getMaDonHang()).getValue(TrangThaiDonHang.class);
                                            if (donHang.getMaNVGiao().equalsIgnoreCase(bangLuong.getMaNhanVien()) && donHang.getThoiGianGiao().indexOf(thangNam) > 0) {
                                                if (trangThaiDonHang.getTrangThaiDon().equalsIgnoreCase("Thành công")) {
                                                    tongDonDaGiao++;
                                                }
                                            }
                                        }
                                        bangLuong.setTongDonDaGiao(tongDonDaGiao);
                                        int tongLuong = bangLuong.getTongGioLam() * bangLuong.getLuongCanBan() / 128;
                                        int luongTheoGio = bangLuong.getLuongCanBan() / 128;
                                        if (bangLuong.getTangCa() != 0) {
                                            int gioTangCa = bangLuong.getTangCa() / 60;
                                            if (gioTangCa != 0) {
                                                tongLuong += gioTangCa * luongTheoGio;
                                            }
                                        }
                                        if (bangLuong.getDiTre() != 0) {
                                            int gioDiTre = bangLuong.getDiTre() / 60;
                                            if (gioDiTre != 0) {
                                                if (gioDiTre >= 4) {
                                                    tongLuong -= luongTheoGio * gioDiTre;
                                                } else {
                                                    int trungBinhDiTreMoiNgay = bangLuong.getDiTre() / (bangLuong.getTongGioLam() / 8);
                                                    if (trungBinhDiTreMoiNgay >= 5) {
                                                        tongLuong -= 100000;
                                                    } else if (trungBinhDiTreMoiNgay >= 10) {
                                                        tongLuong -= 150000;
                                                    } else if (trungBinhDiTreMoiNgay >= 15) {
                                                        tongLuong -= 200000;
                                                    }
                                                }
                                            }
                                        }
                                        if (bangLuong.getSoCaNghiKhongPhep() == 0) {
                                            if (bangLuong.getTongGioLam() >= 112 && bangLuong.getSoCaNghiCoPhep() <= 4) {
                                                bangLuong.setThuongChuyenCan(1000000);
                                            } else if (bangLuong.getTongGioLam() >= 112 && bangLuong.getSoCaNghiCoPhep() <= 6) {
                                                bangLuong.setThuongChuyenCan(500000);
                                            }
                                        }
                                        if (bangLuong.getTongDonDaGiao() >= 1000) {
                                            tongLuong += 1500000;
                                        } else if (bangLuong.getTongDonDaGiao() >= 800) {
                                            tongLuong += 1000000;
                                        } else if (bangLuong.getTongDonDaGiao() >= 500) {
                                            tongLuong += 500000;
                                        }
                                        tongLuong += bangLuong.getThuongChuyenCan();
                                        bangLuong.setTongLuong(tongLuong);
                                        ((BangLuongActivity) context).hienThiBangLuong();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    // Phản hồi ý kiến khách hàng
    public void hienThiPhanHoiYKien(ArrayList<PhanHoiYKienKhachHang> phanHoiYKienKhachHangs, PhanHoiYKienKhachHangRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference phanHoiDatabase = firebaseDatabase.getReference("PHANHOI");
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        phanHoiDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<PhanHoi>  phanHois = new ArrayList<>();
                for (DataSnapshot sanPhamDataSnapshot: snapshot.getChildren()) {
                    for (DataSnapshot phanHoiDataSnapshot: sanPhamDataSnapshot.getChildren()) {
                        PhanHoi phanHoi = phanHoiDataSnapshot.getValue(PhanHoi.class);
                        phanHois.add(phanHoi);
                    }
                }
                sanPhamDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        phanHoiYKienKhachHangs.clear();
                        for (PhanHoi phanHoi : phanHois) {
                            PhanHoiYKienKhachHang phanHoiYKienKhachHang = new PhanHoiYKienKhachHang();
                            phanHoiYKienKhachHang.setMaDonHang(phanHoi.getMaDonHang());
                            phanHoiYKienKhachHang.setDanhGia(phanHoi.getDanhGia());
                            phanHoiYKienKhachHang.setThoiGianBinhLuan(phanHoi.getNgayBinhLuan());
                            phanHoiYKienKhachHang.setMaKhachHang(phanHoi.getMaKhachHang());
                            phanHoiYKienKhachHang.setNoiDungBinhLuan(phanHoi.getBinhLuan());
                            phanHoiYKienKhachHang.setMaNhanVien(phanHoi.getMaNhanVien());
                            phanHoiYKienKhachHang.setNoiDungTraLoi(phanHoi.getTraLoi());
                            if (phanHoi.getMaSanPham().contains("s")) {
                                Sach sach = snapshot.child("SACH").child(phanHoi.getMaSanPham()).getValue(Sach.class);
                                phanHoiYKienKhachHang.setMaSanPham(sach.getMaSach());
                                phanHoiYKienKhachHang.setTenSanPham(sach.getTenSach());
                            } else if (phanHoi.getMaSanPham().contains("vpp")) {
                                VanPhongPham vanPhongPham = snapshot.child("VANPHONGPHAM").child(phanHoi.getMaSanPham()).getValue(VanPhongPham.class);
                                phanHoiYKienKhachHang.setMaSanPham(vanPhongPham.getMaVanPhongPham());
                                phanHoiYKienKhachHang.setTenSanPham(vanPhongPham.getTenVanPhongPham());
                            }
                            nguoiDungDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    KhachHang khachHang = snapshot.child("khachhang").child(phanHoi.getMaKhachHang()).getValue(KhachHang.class);
                                    phanHoiYKienKhachHang.setTenKhachHang(khachHang.getTenKhachHang());
                                    NhanVien nhanVien  = snapshot.child("nhanvien").child(phanHoi.getMaNhanVien()).getValue(NhanVien.class);
                                    phanHoiYKienKhachHang.setTenNhanVien(nhanVien.getTenNhanVien());
                                    phanHoiYKienKhachHangs.add(phanHoiYKienKhachHang);
                                    adapter.notifyDataSetChanged();
                                    ((PhanHoiYKienKhachHangActivity)context).duLieu();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
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
            }
        });
    }

    // Trả lời bình luận
    public void traLoiBinhLuan(String maNhanVien, String noiDungThayDoi, PhanHoiYKienKhachHang phanHoiYKienKhachHang, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference phanHoiDatabase = firebaseDatabase.getReference("PHANHOI");
        phanHoiDatabase.child(phanHoiYKienKhachHang.getMaSanPham()).child(phanHoiYKienKhachHang.getMaKhachHang()).child("maNhanVien").setValue(maNhanVien, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                phanHoiDatabase.child(phanHoiYKienKhachHang.getMaSanPham()).child(phanHoiYKienKhachHang.getMaKhachHang()).child("traLoi").setValue(noiDungThayDoi, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        ((TraLoiBinhLuanActivity)context).finish();
                    }
                });
            }
        });

    }

    // Màn hình chính quản lý
    public void hienThiThongTinQuanLy(String maQuanLy, QuanLy quanLy, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nguoiDungDatabase.child("quanly").child(maQuanLy).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                QuanLy ql = snapshot.getValue(QuanLy.class);
                quanLy.setTenQuanLy(ql.getTenQuanLy());
                quanLy.setMaQuanLy(ql.getMaQuanLy());
                quanLy.setNguoiDung(ql.getNguoiDung());
                quanLy.setHinhQuanLy(ql.getHinhQuanLy());
                ((ManHinhChinhQuanLyActivity)context).hienThiQuanLy();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    ///////////////////////////////////////////////////////

    //Tin
    public void taoKhachHang(ItemKhachHang khachHang) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ngDungDatabase = firebaseDatabase.getReference("NGUOIDUNG").child("khachhang");
        ngDungDatabase.child(khachHang.getMaKhachHang()).setValue(khachHang);
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

    // Theo doi don hang
    public void hienThiTheoDoiDonHang (String maNhanVien, NhanVien nhanVien, ArrayList<TheoDoiDonHang> theoDoiDonHangs, Context context){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference trangThaiDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        DatabaseReference nhanVienDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        donHangDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                DonHang donHang = snapshot.getValue(DonHang.class);
                nhanVienDatabase.child("nhanvien").child(maNhanVien).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        NhanVien nv = snapshot.getValue(NhanVien.class);
                        trangThaiDatabase.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Log.d("Test", snapshot.getValue() + " ");
                                TrangThaiDonHang trangThaiDonHang = snapshot.getValue(TrangThaiDonHang.class);
                                theoDoiDonHangs.add(new TheoDoiDonHang(donHang.getMaDonHang(), nhanVien.getTenNhanVien(), donHang.getThoiGianLap(), donHang.getThoiGianGiao(),null, trangThaiDonHang.getTrangThaiDon()));
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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    //Quan ly san pham
    public void hienThiQuanLySanPham(ArrayList<ItemQuanLySanPhamNV> itemQuanLySanPhamNVS, QuanLySanPhamNVRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        sanPhamDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sanPhamDatabase.child("SACH").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot sachDataSnapshot : snapshot.getChildren()) {
                            Sach sach = sachDataSnapshot.getValue(Sach.class);
                            itemQuanLySanPhamNVS.add(new ItemQuanLySanPhamNV(sach.getHinhSach(), sach.getMaSach(), sach.getTenSach(), Integer.valueOf(sach.getGiaTien()), Integer.valueOf(sach.getSoLuongKho())));
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("onCancelled", "Lỗi!" + error.getMessage());
                    }
                });
                sanPhamDatabase.child("VANPHONGPHAM").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot vanPhongPhamDataSnapshot : snapshot.getChildren()) {
                            VanPhongPham vanPhongPham = vanPhongPhamDataSnapshot.getValue(VanPhongPham.class);
                            itemQuanLySanPhamNVS.add(new ItemQuanLySanPhamNV(vanPhongPham.getHinhVanPhongPham(), vanPhongPham.getMaVanPhongPham(), vanPhongPham.getTenVanPhongPham(), Integer.valueOf(vanPhongPham.getGiaTien()), Integer.valueOf(vanPhongPham.getSoLuongKho())));
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

    //Quan ly don hang
    public void hienThiQuanLyDonHang(String maNhanVien, ArrayList<ItemQuanLyDonHangNV> itemQuanLyDonHangNVS, QuanLyDonHangNVRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference trangThaiDonHangDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        donHangDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DonHang donHang = dataSnapshot.getValue(DonHang.class);
                    if (donHang.getMaNVGiao().equalsIgnoreCase(maNhanVien)) {
                        trangThaiDonHangDatabase.child(donHang.getMaDonHang()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                TrangThaiDonHang trangThaiDonHang = snapshot.getValue(TrangThaiDonHang.class);
                                itemQuanLyDonHangNVS.add(new ItemQuanLyDonHangNV(donHang.getMaDonHang(), trangThaiDonHang.getTrangThaiGiaoHangNV(), ""));
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

    //Chi tiet don hang
    public void hienThiChiTietDonHang(String maDonHang, ArrayList<ItemChiTietDonHangNV> itemChiTietDonHangNVS, ChiTietDonHangNVRecyclerViewAdapter adapter, Context context){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        DatabaseReference xuatKhoDatabase = firebaseDatabase.getReference("XUATKHO");
        xuatKhoDatabase.child(maDonHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                xuatKhoDatabase.child(maDonHang).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        itemChiTietDonHangNVS.clear();
                        for (DataSnapshot xuatKhoSnapshot : snapshot.getChildren()) {
                            XuatKho xuatKho = xuatKhoSnapshot.getValue(XuatKho.class);
                            if (xuatKho.getMaSanPham().contains("s")) {
                                sanPhamDatabase.child("SACH").child(xuatKho.getMaSanPham()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        Sach sach = snapshot.getValue(Sach.class);
                                        itemChiTietDonHangNVS.add(new ItemChiTietDonHangNV(sach.getMaSach(), sach.getTenSach(), Integer.valueOf(xuatKho.getSoLuongXuat()), Integer.valueOf(sach.getGiaTien()), "sach.getHinhSach()"));
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
                                        itemChiTietDonHangNVS.add(new ItemChiTietDonHangNV(vanPhongPham.getMaVanPhongPham(), vanPhongPham.getTenVanPhongPham(), Integer.valueOf(xuatKho.getSoLuongXuat()), Integer.valueOf(vanPhongPham.getGiaTien()), vanPhongPham.getHinhVanPhongPham()));
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
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    public void hienThiPhuongThucThanhToanCTDH(String maDonHang, TrangThaiDonHang trangThaiDonHang, Context context){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference trangThaiDonHangDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        trangThaiDonHangDatabase.child(maDonHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TrangThaiDonHang tt = snapshot.getValue(TrangThaiDonHang.class);
                trangThaiDonHang.setMaDonHang(tt.getMaDonHang());
                trangThaiDonHang.setKieuThanhToan(tt.getKieuThanhToan());
                trangThaiDonHang.setLyDoHuy(tt.getLyDoHuy());
                trangThaiDonHang.setTrangThaiChuyenHangQL(tt.getTrangThaiChuyenHangQL());
                trangThaiDonHang.setTrangThaiChuyenTienKH(tt.getTrangThaiChuyenTienKH());
                trangThaiDonHang.setTrangThaiDon(tt.getTrangThaiDon());
                trangThaiDonHang.setTrangThaiDuyetNV(tt.getTrangThaiDuyetNV());
                trangThaiDonHang.setTrangThaiGiaoHangKH(tt.getTrangThaiGiaoHangKH());
                trangThaiDonHang.setTrangThaiGiaoHangNV(tt.getTrangThaiGiaoHangNV());
                trangThaiDonHang.setTrangThaiNhanHangNV(tt.getTrangThaiNhanHangNV());
                trangThaiDonHang.setTrangThaiNhanTienKH(tt.getTrangThaiNhanTienKH());
                trangThaiDonHang.setTrangThaiNhanTienQL(tt.getTrangThaiNhanTienQL());
                trangThaiDonHang.setTrangThaiPhanCongQL(tt.getTrangThaiPhanCongQL());
                trangThaiDonHang.setTrangThaiTraTienQL(tt.getTrangThaiTraTienQL());
                ((ChiTietDonHangNVActivity) context).hienThiTrangThaiCTDH();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    public void hienThiGiamGiaCTDH(String maKhachHang, String maGiamGia, GiamGia giamGia, Context context){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference maGiamGiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        maGiamGiaDatabase.child(maKhachHang).child(maGiamGia).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("test", snapshot.getValue() + " ");
                    GiamGia gg = snapshot.getValue(GiamGia.class);
                    giamGia.setMaGiamGia(gg.getMaGiamGia());
                    giamGia.setHinhGiamGia(gg.getHinhGiamGia());
                    giamGia.setMaKhachHang(gg.getMaKhachHang());
                    giamGia.setTienGiamGia(gg.getTienGiamGia());
                    giamGia.setTieuDe(gg.getTieuDe());
                    giamGia.setYeuCau(gg.getYeuCau());
                    giamGia.setChon(gg.getChon());
                    giamGia.setKiemTra(gg.getKiemTra());
                    ((ChiTietDonHangNVActivity) context).hienThiTienGiamGiaCTDH();
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    public void hienThiDonHangCTDH(String maDonHang, DonHang donHang, Context context){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        donHangDatabase.child(maDonHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DonHang dh = snapshot.getValue(DonHang.class);
                donHang.setMaDonHang(dh.getMaDonHang());
                donHang.setMaGiamGia(dh.getMaGiamGia());
                donHang.setDiaChiGiao(dh.getDiaChiGiao());
                donHang.setMaNVDuyet(dh.getMaNVDuyet());
                donHang.setMaNVGiao(dh.getMaNVGiao());
                donHang.setThoiGianGiao(dh.getThoiGianGiao());
                donHang.setThoiGianLap(dh.getThoiGianLap());
                donHang.setMaKhachHang(dh.getMaKhachHang());
                donHang.setPhiVanChuyen(dh.getPhiVanChuyen());
                ((ChiTietDonHangNVActivity) context).hienThiDonHang();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    //Thong tin giao hang
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
                            XuatKho xuatKho = xuatKhoSnapshot.getValue(XuatKho.class);
                            if (xuatKho.getMaSanPham().contains("s")) {
                                sanPhamDatabase.child("SACH").child(xuatKho.getMaSanPham()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        Sach sach = snapshot.getValue(Sach.class);
                                        chiTietGiaoHang.add(new ChiTietGiaoHang(sach.getMaSach(), sach.getTenSach(), Integer.valueOf(sach.getGiaTien()), Integer.valueOf(xuatKho.getSoLuongXuat()), sach.getHinhSach()));
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
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    public void hienThiKhachHang_TTGH(String maKhachHang, String maDonHang, KhachHang khachHang, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference khachHangDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        khachHangDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("test", snapshot.getValue() + " ");
                KhachHang kh = snapshot.getValue(KhachHang.class);
                khachHang.setNguoiDung(kh.getNguoiDung());
                khachHang.setMaKhachHang(kh.getMaKhachHang());
                khachHang.setDiaChi(kh.getDiaChi());
                khachHang.setEmail(kh.getEmail());
                khachHang.setMatKhau(kh.getMatKhau());
                khachHang.setNganHang(kh.getNganHang());
                khachHang.setSoDienThoai(kh.getSoDienThoai());
                khachHang.setSoTaiKhoan(kh.getSoTaiKhoan());
                khachHang.setTaiKhoan(kh.getTaiKhoan());
                khachHang.setTenKhachHang(kh.getTenKhachHang());
                ((ThongTinGiaoHangNVActivity) context).hienThiKhachHang();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    public void hienThiDonHang_TTGH(String maDonHang, DonHang donHang, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        donHangDatabase.child(maDonHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DonHang dh = snapshot.getValue(DonHang.class);
                donHang.setMaDonHang(dh.getMaDonHang());
                donHang.setMaGiamGia(dh.getMaGiamGia());
                donHang.setDiaChiGiao(dh.getDiaChiGiao());
                donHang.setMaNVDuyet(dh.getMaNVDuyet());
                donHang.setMaNVGiao(dh.getMaNVGiao());
                donHang.setThoiGianGiao(dh.getThoiGianGiao());
                donHang.setThoiGianLap(dh.getThoiGianLap());
                donHang.setMaKhachHang(dh.getMaKhachHang());
                donHang.setPhiVanChuyen(dh.getPhiVanChuyen());
                ((ThongTinGiaoHangNVActivity) context).hienThiDonHang();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    public void hienThiTinhTrang_TTGH(String maDonHang, TrangThaiDonHang trangThaiDonHang, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference trangThaiDonHangDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        trangThaiDonHangDatabase.child(maDonHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TrangThaiDonHang tt = snapshot.getValue(TrangThaiDonHang.class);
                trangThaiDonHang.setMaDonHang(tt.getMaDonHang());
                trangThaiDonHang.setKieuThanhToan(tt.getKieuThanhToan());
                trangThaiDonHang.setLyDoHuy(tt.getLyDoHuy());
                trangThaiDonHang.setTrangThaiChuyenHangQL(tt.getTrangThaiChuyenHangQL());
                trangThaiDonHang.setTrangThaiChuyenTienKH(tt.getTrangThaiChuyenTienKH());
                trangThaiDonHang.setTrangThaiDon(tt.getTrangThaiDon());
                trangThaiDonHang.setTrangThaiDuyetNV(tt.getTrangThaiDuyetNV());
                trangThaiDonHang.setTrangThaiGiaoHangKH(tt.getTrangThaiGiaoHangKH());
                trangThaiDonHang.setTrangThaiGiaoHangNV(tt.getTrangThaiGiaoHangNV());
                trangThaiDonHang.setTrangThaiNhanHangNV(tt.getTrangThaiNhanHangNV());
                trangThaiDonHang.setTrangThaiNhanTienKH(tt.getTrangThaiNhanTienKH());
                trangThaiDonHang.setTrangThaiNhanTienQL(tt.getTrangThaiNhanTienQL());
                trangThaiDonHang.setTrangThaiPhanCongQL(tt.getTrangThaiPhanCongQL());
                trangThaiDonHang.setTrangThaiTraTienQL(tt.getTrangThaiTraTienQL());
                ((ThongTinGiaoHangNVActivity) context).hienThiTinhTrang();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    //Chi tiet giao hang
    public void hienThiTenNhanVien_CTGH(String maNhanVien, NhanVien nhanVien, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nhanVienDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nhanVienDatabase.child("nhanvien").child(maNhanVien).addValueEventListener(new ValueEventListener() {
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
                ((ChiTietGiaoHangActivity) context).hienThiTenNhanVien_CTGH();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    public void hienThiPhuongThucThanhToan_CTGH(String maDonHang, TrangThaiDonHang trangThaiDonHang, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference trangThaiDonHangDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        trangThaiDonHangDatabase.child(maDonHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TrangThaiDonHang tt = snapshot.getValue(TrangThaiDonHang.class);
                trangThaiDonHang.setMaDonHang(tt.getMaDonHang());
                trangThaiDonHang.setKieuThanhToan(tt.getKieuThanhToan());
                trangThaiDonHang.setLyDoHuy(tt.getLyDoHuy());
                trangThaiDonHang.setTrangThaiChuyenHangQL(tt.getTrangThaiChuyenHangQL());
                trangThaiDonHang.setTrangThaiChuyenTienKH(tt.getTrangThaiChuyenTienKH());
                trangThaiDonHang.setTrangThaiDon(tt.getTrangThaiDon());
                trangThaiDonHang.setTrangThaiDuyetNV(tt.getTrangThaiDuyetNV());
                trangThaiDonHang.setTrangThaiGiaoHangKH(tt.getTrangThaiGiaoHangKH());
                trangThaiDonHang.setTrangThaiGiaoHangNV(tt.getTrangThaiGiaoHangNV());
                trangThaiDonHang.setTrangThaiNhanHangNV(tt.getTrangThaiNhanHangNV());
                trangThaiDonHang.setTrangThaiNhanTienKH(tt.getTrangThaiNhanTienKH());
                trangThaiDonHang.setTrangThaiNhanTienQL(tt.getTrangThaiNhanTienQL());
                trangThaiDonHang.setTrangThaiPhanCongQL(tt.getTrangThaiPhanCongQL());
                trangThaiDonHang.setTrangThaiTraTienQL(tt.getTrangThaiTraTienQL());
                ((ChiTietGiaoHangActivity) context).hienThiTrangThai_CTGH();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    public void hienThiMaGiamGia_CTGH(String maGiamGia, String maKhachHang, GiamGia giamGia, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference maGiamGiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        maGiamGiaDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                GiamGia gg = snapshot.getValue(GiamGia.class);
                giamGia.setMaGiamGia(gg.getMaGiamGia());
                giamGia.setHinhGiamGia(gg.getHinhGiamGia());
                giamGia.setMaKhachHang(gg.getMaKhachHang());
                giamGia.setTienGiamGia(gg.getTienGiamGia());
                giamGia.setTieuDe(gg.getTieuDe());
                giamGia.setYeuCau(gg.getYeuCau());
                giamGia.setChon(gg.getChon());
                giamGia.setKiemTra(gg.getKiemTra());
                ((ChiTietGiaoHangActivity) context).hienThiTienGiamGia_CTGH();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
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
                donHang.setMaGiamGia(dh.getMaGiamGia());
                donHang.setDiaChiGiao(dh.getDiaChiGiao());
                donHang.setMaNVDuyet(dh.getMaNVDuyet());
                donHang.setMaNVGiao(dh.getMaNVGiao());
                donHang.setThoiGianGiao(dh.getThoiGianGiao());
                donHang.setThoiGianLap(dh.getThoiGianLap());
                donHang.setMaKhachHang(dh.getMaKhachHang());
                donHang.setPhiVanChuyen(dh.getPhiVanChuyen());
                ((ChiTietGiaoHangActivity) context).hienThiDonHang();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    //Man hinh chinh nhan vien
    public void hienThiManHinhChinhNhanVien(String maNhanVien, NhanVien nhanVien, Context context){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nhanVienDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nhanVienDatabase.child("nhanvien").child(maNhanVien).addValueEventListener(new ValueEventListener() {
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
                ((ManHinhChinhNhanVienActivity)context).hienThiManHinhChinhNhanVien();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
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
    // Màn hình hiển thị chi tiết sản phẩm
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
                                PhanHoi phanPhoi = phanHoiDataSnapshot.getValue(PhanHoi.class);
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
                                PhanHoi phanPhoi = phanHoiDataSnapshot.getValue(PhanHoi.class);
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

    //Màn hình chính khách hàng
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
                                        PhanHoi phanPhoi = phanHoiDataSnapshot.getValue(PhanHoi.class);
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
                                        PhanHoi phanPhoi = phanHoiDataSnapshot.getValue(PhanHoi.class);
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

    //Thêm số lượng vào giỏ hàng
    public void themVaoGioHang(String maKhachHang, String maSanPham, String soLuong) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference gioHangDatabase = firebaseDatabase.getReference("GIOHANG");
        gioHangDatabase.child(maKhachHang).child(maSanPham).child("soLuong").setValue(soLuong);
        gioHangDatabase.child(maKhachHang).child(maSanPham).child("maKhachHang").setValue(maKhachHang);
        gioHangDatabase.child(maKhachHang).child(maSanPham).child("maSanPham").setValue(maSanPham);
    }

    // Màn hình hiển thị danh sách nhân viên
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

    public void hienThiManHinhChinhQuanLySanPham(ArrayList<ItemSanPham> sanPhams, SanPhamRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nguoiDungDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sanPhams.clear();
                nguoiDungDatabase.child("nhanvien").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot sachDataSnapshot : snapshot.getChildren()) {
                            Sach sach = sachDataSnapshot.getValue(Sach.class);
                            sanPhams.add(new ItemSanPham(
                                    sach.getMaSach(),
                                    sach.getTenSach(),
                                    sach.getTheLoai(),
                                    sach.getTacGia(),
                                    sach.getNhaXuatBan(),
                                    sach.getNgayXuatBan(),
                                    sach.getGiaTien(),
                                    sach.getSoLuongKho(),
                                    sach.getHinhSach()
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

    // Thêm nhân viên
    public void themNhanVien(String maNhanVien, String hinhNhanVien, String tenNhanVien, String cmnd, String diaChi, String email, String luongCoBan, String matKhau, String nguoiDung, String soDienThoai, String taiKhoan) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("maNhanVien").setValue(maNhanVien);
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("hinhNhanVien").setValue(hinhNhanVien);
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("tenNhanVien").setValue(tenNhanVien);
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("cmnd").setValue(cmnd);
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("diaChi").setValue(diaChi);
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("email").setValue(email);
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("luong").setValue(luongCoBan);
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("matKhau").setValue(matKhau);
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("matKhau").setValue(nguoiDung);
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("soDienThoai").setValue(soDienThoai);
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("taiKhoan").setValue(taiKhoan);
    }

    // Xóa nhân viên
    public void xoaNhanVien(String maNhanVien, NhanVienRecyclerViewAdapter adapter) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).removeValue();
    }

    // Sửa Nhân Viên
    public void suaNhanVien(Context context, String maNhanVien, String hinhNhanVien, String tenNhanVien, String cmnd, String diaChi, String email, String luongCoBan, String matKhau, String nguoiDung, String soDienThoai, String taiKhoan) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                NhanVien nhanVien = snapshot.getValue(NhanVien.class);
                nguoiDungDatabase.child(nhanVien.getMaNhanVien()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ((SuaNhanVienActivity) context).thongTinNhanVien(nhanVien);
                        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("hinhNhanVien").setValue(hinhNhanVien);
                        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("tenNhanVien").setValue(tenNhanVien);
                        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("cmnd").setValue(cmnd);
                        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("diaChi").setValue(diaChi);
                        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("email").setValue(email);
                        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("luong").setValue(luongCoBan);
                        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("matKhau").setValue(matKhau);
                        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("matKhau").setValue(nguoiDung);
                        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("soDienThoai").setValue(soDienThoai);
                        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("taiKhoan").setValue(taiKhoan);
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

    // Thông báo hủy đơn hàng
    public void hienThiDonHang_TBHDH(String maDonHang, DonHang donHang, Context context){
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
                donHang.setPhiVanChuyen(dh.getPhiVanChuyen());
                ((ThongBaoHuyDonHangActivity) context).hienThiDonHang();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }
    public void lyDoHuy(String maDonHang, String lyDoHuy){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference trangThiDonHangDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        trangThiDonHangDatabase.child(maDonHang).child("lyDoHuy").setValue(lyDoHuy);
    }

    //Xác nhận đơn hàng
    public void hienThiDonHangXacNhan(String maDonHang ){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference xuatKhoDatabase = firebaseDatabase.getReference("XUATKHO");
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        DatabaseReference trangThaiDonHangDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        donHangDatabase.child(maDonHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot donHangDataSnapshot : snapshot.getChildren()) {
                    DonHang donHang = donHangDataSnapshot.getValue(DonHang.class);
                    trangThaiDonHangDatabase.child(donHang.getMaDonHang()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            TrangThaiDonHang trangThaiDonHang = snapshot.getValue(TrangThaiDonHang.class);
                            xuatKhoDatabase.child(donHang.getMaDonHang()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    ArrayList<SanPhamTinhTongTien> sanPhamTinhTongTiens = new ArrayList<>();
                                    ArrayList<XuatKho> xuatKhos = new ArrayList<>();
                                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                        XuatKho xuatKho = dataSnapshot.getValue(XuatKho.class);
                                        xuatKhos.add(xuatKho);
                                        sanPhamDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (trangThaiDonHang.getTrangThaiDon().equalsIgnoreCase("Hủy")) {
                                                    for (XuatKho xuatKho : xuatKhos) {
                                                        if (xuatKho.getMaSanPham().contains("s")) {
                                                            Sach sach = snapshot.child("SACH").child(xuatKho.getMaSanPham()).getValue(Sach.class);
                                                            sanPhamTinhTongTiens.add(new SanPhamTinhTongTien(
                                                                    sach.getMaSach(),
                                                                    Integer.valueOf(sach.getGiaTien()),
                                                                    Integer.valueOf(sach.getKhuyenMai()),
                                                                    Integer.valueOf(xuatKho.getSoLuongXuat())));
                                                        } else if (xuatKho.getMaSanPham().contains("vpp")) {
                                                            VanPhongPham vanPhongPham = snapshot.child("VANPHONGPHAM").child(xuatKho.getMaSanPham()).getValue(VanPhongPham.class);
                                                            sanPhamTinhTongTiens.add(new SanPhamTinhTongTien(
                                                                    vanPhongPham.getMaVanPhongPham(),
                                                                    Integer.valueOf(vanPhongPham.getGiaTien()),
                                                                    Integer.valueOf(vanPhongPham.getKhuyenMai()),
                                                                    Integer.valueOf(xuatKho.getSoLuongXuat())));
                                                        }
                                                    }
                                                    int tongTien = 0;
                                                    for (SanPhamTinhTongTien sanPhamTinhTongTien : sanPhamTinhTongTiens) {
                                                        tongTien += sanPhamTinhTongTien.getTongTien();
                                                    }
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
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
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
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

    public void xacNhanNhanHangNV(String maDonHang, String trangThaiNhanHangNV){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference trangThaiDonHangDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        trangThaiDonHangDatabase.child(maDonHang).child("trangThaiNhanHangNV").setValue(trangThaiNhanHangNV);
    }

    ////////////////////////////////////////////////////////////////////////////////

    //trieu
    //xoa San Pham
    public void xoaSanPham(String maSanPham, SanPhamRecyclerViewAdapter adapter) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference gioHangDatabase = firebaseDatabase.getReference("SANPHAM");
        gioHangDatabase.child("SACH").child(maSanPham).removeValue();
        gioHangDatabase.child("VANPHONGPHAM").child(maSanPham).removeValue();
    }

    //THEM SACH
    public void themSanPham_Sach(String maSach, String hinhSach, String tenSach, String theLoai, String tacGia, String nhaXuatBan
            , String ngayXuatBan, String giaTien, String soLuongKho) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("SANPHAM");
        nguoiDungDatabase.child("SACH").child(maSach).child("maSach").setValue(maSach);
        nguoiDungDatabase.child("SACH").child(maSach).child("hinhSach").setValue(hinhSach);
        nguoiDungDatabase.child("SACH").child(maSach).child("tenSach").setValue(tenSach);
        nguoiDungDatabase.child("SACH").child(maSach).child("theLoao").setValue(theLoai);
        nguoiDungDatabase.child("SACH").child(maSach).child("tacGia").setValue(tacGia);
        nguoiDungDatabase.child("SACH").child(maSach).child("nhaXuatBan").setValue(nhaXuatBan);
        nguoiDungDatabase.child("SACH").child(maSach).child("ngayXuatBan").setValue(ngayXuatBan);
        nguoiDungDatabase.child("SACH").child(maSach).child("giaTien").setValue(giaTien);
        nguoiDungDatabase.child("SACH").child(maSach).child("soLongKho").setValue(soLuongKho);
    }

    // THÊM VPPP
    public void themSanPham_vpp(String maVanPhongPHam, String hinhVanPhongPham, String tenVanPhongPham
            , String nhaPhanPhoi, String xuatXu, String donVi
            , String giaTien, String soLuongKho) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("SANPHAM");
        nguoiDungDatabase.child("VANPHONGPHAM").child(maVanPhongPHam).child("maVanPhongPHam").setValue(maVanPhongPHam);
        nguoiDungDatabase.child("VANPHONGPHAM").child(maVanPhongPHam).child("hinhSach").setValue(hinhVanPhongPham);
        nguoiDungDatabase.child("VANPHONGPHAM").child(maVanPhongPHam).child("tenSach").setValue(tenVanPhongPham);
        nguoiDungDatabase.child("VANPHONGPHAM").child(maVanPhongPHam).child("theLoao").setValue(nhaPhanPhoi);
        nguoiDungDatabase.child("VANPHONGPHAM").child(maVanPhongPHam).child("tacGia").setValue(xuatXu);
        nguoiDungDatabase.child("VANPHONGPHAM").child(maVanPhongPHam).child("nhaXuatBan").setValue(donVi);
        nguoiDungDatabase.child("VANPHONGPHAM").child(maVanPhongPHam).child("giaTien").setValue(giaTien);
        nguoiDungDatabase.child("VANPHONGPHAM").child(maVanPhongPHam).child("soLongKho").setValue(soLuongKho);
    }
    public void suaSach(Context context, String maSach, String hinhSach, String tenSach, String theLoai, String tacGia, String nhaXuatBan, String ngayXuatBan, String giaTien, String soLuongKho) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("SANPHAM");
        nguoiDungDatabase.child("SACH").child(maSach).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Sach sach = snapshot.getValue(Sach.class);
                nguoiDungDatabase.child(sach.getMaSach()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ((SuaSanPhamSachActivity) context).thongTinSanPham(sach);
                        nguoiDungDatabase.child("SACH").child(maSach).child("hinhSach").setValue(hinhSach);
                        nguoiDungDatabase.child("SACH").child(maSach).child("tenSach").setValue(tenSach);
                        nguoiDungDatabase.child("SACH").child(maSach).child("theLoai").setValue(theLoai);
                        nguoiDungDatabase.child("SACH").child(maSach).child("tacGia").setValue(tacGia);
                        nguoiDungDatabase.child("SACH").child(maSach).child("nhaXuatBan").setValue(nhaXuatBan);
                        nguoiDungDatabase.child("SACH").child(maSach).child("ngayXuatBan").setValue(ngayXuatBan);
                        nguoiDungDatabase.child("SACH").child(maSach).child("giaTien").setValue(giaTien);
                        nguoiDungDatabase.child("SACH").child(maSach).child("soLuongKho").setValue(soLuongKho);
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
    public void suaVPP(Context context, String maVanPhongPham, String hinhVanPhongPham, String tenVanPhongPham, String nhaPhanPhoi, String xuatXu, String donVi, String giaTien, String soLuongKho) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("SANPHAM");
        nguoiDungDatabase.child("VPP").child(maVanPhongPham).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                VanPhongPham vanPhongPham = snapshot.getValue(VanPhongPham.class);
                nguoiDungDatabase.child(vanPhongPham.getMaVanPhongPham()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ((SuaSanPhamVPPActivity) context).thongTinSanPhamVPP(vanPhongPham);
                        nguoiDungDatabase.child("VPP").child(maVanPhongPham).child("hinhVanPhongPham").setValue(hinhVanPhongPham);
                        nguoiDungDatabase.child("VPP").child(maVanPhongPham).child("tenVanPhongPham").setValue(tenVanPhongPham);
                        nguoiDungDatabase.child("VPP").child(maVanPhongPham).child("nhaPhanPhoi").setValue(nhaPhanPhoi);
                        nguoiDungDatabase.child("VPP").child(maVanPhongPham).child("xuatXu").setValue(xuatXu);
                        nguoiDungDatabase.child("VPP").child(maVanPhongPham).child("donVi").setValue(donVi);
                        nguoiDungDatabase.child("VPP").child(maVanPhongPham).child("giaTien").setValue(giaTien);
                        nguoiDungDatabase.child("VPP").child(maVanPhongPham).child("soLuongKho").setValue(soLuongKho);
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
