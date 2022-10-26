package com.example.nhasachonline.doituong;

public class TheoDoiDonHang {
    private String maDonHang;
    private String tenNVGiaoHang;
    private String ngayDuKienGiao;
    private String thangDuKienGiao;
    private String namDuKienGiao;
    private String ngayDat;
    private String thangDat;
    private String namDat;
    private int tongTienThanhToan;
    private String trangThai;

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

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

    public String getNgayDuKienGiao() {
        return ngayDuKienGiao;
    }

    public void setNgayDuKienGiao(String ngayDuKienGiao) {
        this.ngayDuKienGiao = ngayDuKienGiao;
    }

    public String getThangDuKienGiao() {
        return thangDuKienGiao;
    }

    public void setThangDuKienGiao(String thangDuKienGiao) {
        this.thangDuKienGiao = thangDuKienGiao;
    }

    public String getNamDuKienGiao() {
        return namDuKienGiao;
    }

    public void setNamDuKienGiao(String namDuKienGiao) {
        this.namDuKienGiao = namDuKienGiao;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getThangDat() {
        return thangDat;
    }

    public void setThangDat(String thangDat) {
        this.thangDat = thangDat;
    }

    public String getNamDat() {
        return namDat;
    }

    public void setNamDat(String namDat) {
        this.namDat = namDat;
    }

    public int getTongTienThanhToan() {
        return tongTienThanhToan;
    }

    public void setTongTienThanhToan(int tongTienThanhToan) {
        this.tongTienThanhToan = tongTienThanhToan;
    }


    public TheoDoiDonHang(String maDonHang, String tenNVGiaoHang, String ngayDuKienGiao, String thangDuKienGiao, String namDuKienGiao, String ngayDat, String thangDat, String namDat, int tongTienThanhToan, String trangThai) {
        this.maDonHang = maDonHang;
        this.tenNVGiaoHang = tenNVGiaoHang;
        this.ngayDuKienGiao = ngayDuKienGiao;
        this.thangDuKienGiao = thangDuKienGiao;
        this.namDuKienGiao = namDuKienGiao;
        this.ngayDat = ngayDat;
        this.thangDat = thangDat;
        this.namDat = namDat;
        this.tongTienThanhToan = tongTienThanhToan;
        this.trangThai = trangThai;
    }

    public TheoDoiDonHang() {
    }
}
