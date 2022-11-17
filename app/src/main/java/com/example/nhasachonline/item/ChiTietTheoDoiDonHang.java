package com.example.nhasachonline.item;

public class ChiTietTheoDoiDonHang {
    private String maSanPham;
    private String tenSanPham;
    private String hinhSanPham;
    private int giaSanPham;
    private int soLuong;
    private int tongTien;
    private int khuyenMai;

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getTongTien() {
        return tongTien;
    }

    public int getKhuyenMai() {
        return khuyenMai;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public void setHinhSanPham(String hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public void setKhuyenMai(int khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public ChiTietTheoDoiDonHang() {
    }

    public ChiTietTheoDoiDonHang(String maSanPham, String tenSanPham, String hinhSanPham, int giaSanPham, int soLuong, int khuyenMai) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.hinhSanPham = hinhSanPham;
        this.giaSanPham = giaSanPham;
        this.soLuong = soLuong;
        this.khuyenMai = khuyenMai;
        this.tongTien = (giaSanPham - (giaSanPham * khuyenMai / 100)) * soLuong;
    }
}
