package com.example.nhasachonline.item;

public class SanPham {
    private String maSanPham;
    private String giaTien;
    private String khuyenMai;

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getGiaTien() {
        return giaTien;
    }

    public String getKhuyenMai() {
        return khuyenMai;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }

    public void setKhuyenMai(String khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public SanPham() {
    }

    public SanPham(String maSanPham, String giaTien, String khuyenMai) {
        this.maSanPham = maSanPham;
        this.giaTien = giaTien;
        this.khuyenMai = khuyenMai;
    }
}
