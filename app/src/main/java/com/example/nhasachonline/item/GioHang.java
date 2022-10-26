package com.example.nhasachonline.item;

public class GioHang {
    private String tenSanPham;
    private int giaSanPham;
    private int khuyenMai;
    private int soLuongSanPham;
    private int tongTien;

    public String getTenSanPham() {
        return tenSanPham;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public int getKhuyenMai() {
        return khuyenMai;
    }

    public int getSoLuongSanPham() {
        return soLuongSanPham;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public void setKhuyenMai(int khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public void setSoLuongSanPham(int soLuongSanPham) {
        this.soLuongSanPham = soLuongSanPham;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public GioHang() {
    }

    public GioHang(String tenSanPham, int giaSanPham, int khuyenMai, int soLuongSanPham) {
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.khuyenMai = khuyenMai;
        this.soLuongSanPham = soLuongSanPham;
        this.tongTien = (giaSanPham - (giaSanPham * khuyenMai / 100)) * soLuongSanPham;
    }
}
