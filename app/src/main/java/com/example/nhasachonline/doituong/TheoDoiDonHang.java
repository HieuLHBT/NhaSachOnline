package com.example.nhasachonline.doituong;

import java.sql.Timestamp;

public class TheoDoiDonHang {
    private String maDonHang;
    private String tenNVGiaoHang;
    private Timestamp thoiGianDuKienGiao;
    private Timestamp thoiGianDat;
    private int tongTienThanhToan;

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getTenNVGiaoHang() {
        return tenNVGiaoHang;
    }

    public void setTenNVGiaoHang(String tenNVGiaoHang) {
        this.tenNVGiaoHang = tenNVGiaoHang;
    }

    public Timestamp getThoiGianDuKienGiao() {
        return thoiGianDuKienGiao;
    }

    public void setThoiGianDuKienGiao(Timestamp thoiGianDuKienGiao) {
        this.thoiGianDuKienGiao = thoiGianDuKienGiao;
    }

    public Timestamp getThoiGianDat() {
        return thoiGianDat;
    }

    public void setThoiGianDat(Timestamp thoiGianDat) {
        this.thoiGianDat = thoiGianDat;
    }

    public int getTongTienThanhToan() {
        return tongTienThanhToan;
    }

    public void setTongTienThanhToan(int tongTienThanhToan) {
        this.tongTienThanhToan = tongTienThanhToan;
    }

    public TheoDoiDonHang(String maDonHang, String tenNVGiaoHang, Timestamp thoiGianDuKienGiao, Timestamp thoiGianDat, int tongTienThanhToan) {
        this.maDonHang = maDonHang;
        this.tenNVGiaoHang = tenNVGiaoHang;
        this.thoiGianDuKienGiao = thoiGianDuKienGiao;
        this.thoiGianDat = thoiGianDat;
        this.tongTienThanhToan = tongTienThanhToan;
    }

    public TheoDoiDonHang() {
    }
}
