package com.example.nhasachonline.item;

public class ItemQuanLySanPhamNV {
    private String hinhSanPham;
    private String maSanPham;
    private String tenSanPham;
    private int giaTien;
    private int soLuong;

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public void setHinhSanPham(String hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public ItemQuanLySanPhamNV() {
    }

    public ItemQuanLySanPhamNV(String hinhSanPham, String maSanPham, String tenSanPham, int giaTien, int soLuong) {
        this.hinhSanPham = hinhSanPham;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.giaTien = giaTien;
        this.soLuong = soLuong;
    }
}
