package com.example.nhasachonline.item;

import java.util.ArrayList;

public class TheoDoiDonHang {
    private String maDonHang;
    private String tenNhanVien;
    private String ngayGiao;
    private String ngayLap;
    private int tongTien;
    private String trangThai;
    private String trangThaiChuyenTienKH;
    private String hinhThucThanhToan;

    public String getMaDonHang() {
        return maDonHang;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public String getNgayGiao() {
        return ngayGiao;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public int getTongTien() {
        return tongTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public String getTrangThaiChuyenTienKH() {
        return trangThaiChuyenTienKH;
    }

    public String getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public void setNgayGiao(String ngayGiao) {
        this.ngayGiao = ngayGiao;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void setTrangThaiChuyenTienKH(String trangThaiChuyenTienKH) {
        this.trangThaiChuyenTienKH = trangThaiChuyenTienKH;
    }

    public void setHinhThucThanhToan(String hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public TheoDoiDonHang() {
    }

    public TheoDoiDonHang(String maDonHang, String tenNhanVien, String ngayGiao, String ngayLap, int tongTien, String trangThai, String trangThaiChuyenTienKH, String hinhThucThanhToan) {
        this.maDonHang = maDonHang;
        this.tenNhanVien = tenNhanVien;
        this.ngayGiao = ngayGiao;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.trangThaiChuyenTienKH = trangThaiChuyenTienKH;
        this.hinhThucThanhToan = hinhThucThanhToan;
    }
}
