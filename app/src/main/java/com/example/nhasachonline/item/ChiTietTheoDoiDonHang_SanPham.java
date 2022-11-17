package com.example.nhasachonline.item;

public class ChiTietTheoDoiDonHang_SanPham {
    private String maSanPham;
    private String tenSanPham;
    private String giaTien;
    private String khuyenMai;
    private String hinhSanPham;

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public String getGiaTien() {
        return giaTien;
    }

    public String getKhuyenMai() {
        return khuyenMai;
    }

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }

    public void setKhuyenMai(String khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public void setHinhSanPham(String hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }

    public ChiTietTheoDoiDonHang_SanPham() {
    }

    public ChiTietTheoDoiDonHang_SanPham(String maSanPham, String tenSanPham, String giaTien, String khuyenMai, String hinhSanPham) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.giaTien = giaTien;
        this.khuyenMai = khuyenMai;
        this.hinhSanPham = hinhSanPham;
    }
}
