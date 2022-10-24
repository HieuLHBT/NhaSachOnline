package com.example.nhasachonline.doituong;

public class ChiTietGiaoHang {
    private String tenSanPham;
    private int giaSanPham;
    private int soLuong;
    private String hinhSanPham;

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public void setHinhSanPham(String hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }

    public ChiTietGiaoHang() {
    }

    public ChiTietGiaoHang(String tenSanPham, int giaSanPham, int soLuong, String hinhSanPham) {
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.soLuong = soLuong;
        this.hinhSanPham = hinhSanPham;
    }
}
