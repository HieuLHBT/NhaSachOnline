package com.example.nhasachonline.item;

public class ItemChiTietDonHangNV {
    private String tenSanPham;
    private int soLuong;
    private int donGia;
    private int tongTien;
    private String hinhSanPham;

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public void setHinhSanPham(String hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
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

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public ItemChiTietDonHangNV() {
    }

    public ItemChiTietDonHangNV(String tenSanPham, int soLuong, int donGia, int tongTien, String hinhSanPham) {
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tongTien = tongTien;
        this.hinhSanPham = hinhSanPham;
    }
}
