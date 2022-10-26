package com.example.nhasachonline.item;

public class ThanhToan {
    private String tenSanPham;
    private int giaSanPham;
    private int soLuong;
    private String hinhSanPham;

    public String getTenSanPham() {
        return tenSanPham;
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

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
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

    public ThanhToan(String tenSanPham, int giaSanPham, int soLuong, String hinhSanPham) {
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.soLuong = soLuong;
        this.hinhSanPham = hinhSanPham;
    }
}
