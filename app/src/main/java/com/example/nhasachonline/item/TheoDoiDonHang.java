package com.example.nhasachonline.item;

import java.util.ArrayList;

public class TheoDoiDonHang {
    private String maDonHang;
    private String tenNVGiaoHang;
    private String thoiGianDat;
    private String thoiGianDuKienGiao;
    private int tongTienThanhToan;
    private ArrayList<TheoDoiDonHangSanPham> theoDoiDonHangSanPhams;
    private String trangThai;

    public String getMaDonHang() {
        return maDonHang;
    }

    public String getTenNVGiaoHang() {
        return tenNVGiaoHang;
    }

    public String getThoiGianDat() {
        return thoiGianDat;
    }

    public String getThoiGianDuKienGiao() {
        return thoiGianDuKienGiao;
    }

    public int getTongTienThanhToan() {
        return tongTienThanhToan;
    }

    public ArrayList<TheoDoiDonHangSanPham> getTheoDoiDonHangSanPhams() {
        return theoDoiDonHangSanPhams;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public void setTenNVGiaoHang(String tenNVGiaoHang) {
        this.tenNVGiaoHang = tenNVGiaoHang;
    }

    public void setThoiGianDat(String thoiGianDat) {
        this.thoiGianDat = thoiGianDat;
    }

    public void setThoiGianDuKienGiao(String thoiGianDuKienGiao) {
        this.thoiGianDuKienGiao = thoiGianDuKienGiao;
    }

    public void setTongTienThanhToan(int tongTienThanhToan) {
        this.tongTienThanhToan = tongTienThanhToan;
    }

    public void setTheoDoiDonHangSanPhams(ArrayList<TheoDoiDonHangSanPham> theoDoiDonHangSanPhams) {
        this.theoDoiDonHangSanPhams = theoDoiDonHangSanPhams;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public TheoDoiDonHang() {
    }

    public TheoDoiDonHang(String maDonHang, String tenNVGiaoHang, String thoiGianDat, String thoiGianDuKienGiao, ArrayList<TheoDoiDonHangSanPham> theoDoiDonHangSanPhams, String trangThai) {
        this.maDonHang = maDonHang;
        this.tenNVGiaoHang = tenNVGiaoHang;
        this.thoiGianDat = thoiGianDat;
        this.thoiGianDuKienGiao = thoiGianDuKienGiao;
        this.theoDoiDonHangSanPhams = theoDoiDonHangSanPhams;
        this.trangThai = trangThai;
        for (TheoDoiDonHangSanPham sanPham: this.theoDoiDonHangSanPhams) {
            this.tongTienThanhToan = (sanPham.getGiaTien() - (sanPham.getGiaTien() * sanPham.getKhuyenMai() / 100)) * sanPham.getSoLuongXuat();
        }
    }
}
