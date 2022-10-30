package com.example.nhasachonline.data_model;

public class GioHang {
    private String maSanPham;
    private String maKhachHang;
    private String soLuong;

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public GioHang() {
    }

    public GioHang(String maSanPham, String maKhachHang, String soLuong) {
        this.maSanPham = maSanPham;
        this.maKhachHang = maKhachHang;
        this.soLuong = soLuong;
    }
}
