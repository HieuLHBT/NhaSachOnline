package com.example.nhasachonline.data_model;

public class XuatKho {
    private String maDonHang;
    private String maSanPham;
    private int soLuongXuat;

    public String getMaDonHang() {
        return maDonHang;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public int getSoLuongXuat() {
        return soLuongXuat;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setSoLuongXuat(int soLuongXuat) {
        this.soLuongXuat = soLuongXuat;
    }

    public XuatKho() {
    }

    public XuatKho(String maDonHang, String maSanPham, int soLuongXuat) {
        this.maDonHang = maDonHang;
        this.maSanPham = maSanPham;
        this.soLuongXuat = soLuongXuat;
    }
}
