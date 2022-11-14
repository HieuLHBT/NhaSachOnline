package com.example.nhasachonline.item;

public class ItemXacNhanDonHangNV {
    private String maDonHang;
    private String maSanPham;
    private String tenSanPham;
    private String anhSanPham;
    private int soLuong;
    private int donGia;

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
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

    public String getAnhSanPham() {
        return anhSanPham;
    }

    public void setAnhSanPham(String anhSanPham) {
        this.anhSanPham = anhSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public ItemXacNhanDonHangNV() {
    }

    public ItemXacNhanDonHangNV(String maDonHang, String maSanPham, String tenSanPham, String anhSanPham, int soLuong, int donGia) {
        this.maDonHang = maDonHang;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.anhSanPham = anhSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }
}
