package com.example.nhasachonline.data_model;

public class GioHang {
    private String maKhachHang;
    private String maSanPham;
    private int soLuong;

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public int getSoluong() {
        return soLuong;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setSoluong(int soluong) {
        this.soLuong = soluong;
    }

    public GioHang() {
    }

    public GioHang(String maKhachHang, String maSanPham, int soluong) {
        this.maKhachHang = maKhachHang;
        this.maSanPham = maSanPham;
        this.soLuong = soluong;
    }
}
