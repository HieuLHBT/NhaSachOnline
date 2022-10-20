package com.example.nhasachonline;

public class TheoDoiDonHang {
    private String maDonHang;
    private String tenNVGiaoHang;
    private String thoiGianDuKienGiao;
    private String thoiGianDat;
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

    public String getThoiGianDuKienGiao() {
        return thoiGianDuKienGiao;
    }

    public void setThoiGianDuKienGiao(String thoiGianDuKienGiao) {
        this.thoiGianDuKienGiao = thoiGianDuKienGiao;
    }

    public String getThoiGianDat() {
        return thoiGianDat;
    }

    public void setThoiGianDat(String thoiGianDat) {
        this.thoiGianDat = thoiGianDat;
    }

    public int getTongTienThanhToan() {
        return tongTienThanhToan;
    }

    public void setTongTienThanhToan(int tongTienThanhToan) {
        this.tongTienThanhToan = tongTienThanhToan;
    }

    public TheoDoiDonHang(String maDonHang, String tenNVGiaoHang, String thoiGianDuKienGiao, String thoiGianDat, int tongTienThanhToan) {
        this.maDonHang = maDonHang;
        this.tenNVGiaoHang = tenNVGiaoHang;
        this.thoiGianDuKienGiao = thoiGianDuKienGiao;
        this.thoiGianDat = thoiGianDat;
        this.tongTienThanhToan = tongTienThanhToan;
    }

    public TheoDoiDonHang() {
    }
}
