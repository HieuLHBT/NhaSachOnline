package com.example.nhasachonline.item;

public class ThanhToan {
    private String maSanPhan;
    private String tenSanPhan;
    private int giaSanPham;
    private int soLuong;
    private String hinhSanPham;
    private int tongTien;

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getMaSanPhan() {
        return maSanPhan;
    }

    public String getTenSanPhan() {
        return tenSanPhan;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public void setMaSanPhan(String maSanPhan) {
        this.maSanPhan = maSanPhan;
    }

    public void setTenSanPhan(String tenSanPhan) {
        this.tenSanPhan = tenSanPhan;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setHinhSanPham(String hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }

    public ThanhToan() {
    }

    public ThanhToan(String maSanPhan, String tenSanPhan, int giaSanPham, int soLuong, String hinhSanPham) {
        this.maSanPhan = maSanPhan;
        this.tenSanPhan = tenSanPhan;
        this.giaSanPham = giaSanPham;
        this.soLuong = soLuong;
        this.hinhSanPham = hinhSanPham;
        this.tongTien = soLuong * giaSanPham;
    }
}
