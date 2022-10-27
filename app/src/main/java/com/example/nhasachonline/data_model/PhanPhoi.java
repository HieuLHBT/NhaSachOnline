package com.example.nhasachonline.data_model;

public class PhanPhoi {
    private String maSanPham;
    private String maKhachHang;
    private String maDonHang;
    private String binhLuan;
    private int danhGia;
    private String ngayBinhLuan;
    private String maNhanVien;
    private String traLoi;

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public String getBinhLuan() {
        return binhLuan;
    }

    public int getDanhGia() {
        return danhGia;
    }

    public String getNgayBinhLuan() {
        return ngayBinhLuan;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getTraLoi() {
        return traLoi;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public void setBinhLuan(String binhLuan) {
        this.binhLuan = binhLuan;
    }

    public void setDanhGia(int danhGia) {
        this.danhGia = danhGia;
    }

    public void setNgayBinhLuan(String ngayBinhLuan) {
        this.ngayBinhLuan = ngayBinhLuan;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setTraLoi(String traLoi) {
        this.traLoi = traLoi;
    }

    public PhanPhoi() {
    }

    public PhanPhoi(String maSanPham, String maKhachHang, String maDonHang, String binhLuan, int danhGia, String ngayBinhLuan, String maNhanVien, String traLoi) {
        this.maSanPham = maSanPham;
        this.maKhachHang = maKhachHang;
        this.maDonHang = maDonHang;
        this.binhLuan = binhLuan;
        this.danhGia = danhGia;
        this.ngayBinhLuan = ngayBinhLuan;
        this.maNhanVien = maNhanVien;
        this.traLoi = traLoi;
    }
}
