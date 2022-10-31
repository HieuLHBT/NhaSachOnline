package com.example.nhasachonline.item;

public class TheoDoiDonHangSanPham {
    private String maSanPham;
    private int giaTien;
    private int khuyenMai;
    private int soLuongXuat;

    public String getMaSanPham() {
        return maSanPham;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public int getKhuyenMai() {
        return khuyenMai;
    }

    public int getSoLuongXuat() {
        return soLuongXuat;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public void setKhuyenMai(int khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public void setSoLuongXuat(int soLuongXuat) {
        this.soLuongXuat = soLuongXuat;
    }

    public TheoDoiDonHangSanPham() {
    }

    public TheoDoiDonHangSanPham(String maSanPham, int giaTien, int khuyenMai, int soLuongXuat) {
        this.maSanPham = maSanPham;
        this.giaTien = giaTien;
        this.khuyenMai = khuyenMai;
        this.soLuongXuat = soLuongXuat;
    }
}
