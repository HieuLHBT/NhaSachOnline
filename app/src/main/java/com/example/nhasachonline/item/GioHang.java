package com.example.nhasachonline.item;

import android.graphics.Bitmap;

public class GioHang {
    private String maSanPham;
    private String tenSanPham;
    private int giaSanPham;
    private int khuyenMai;
    private int soLuongSanPham;
    private int tongTien;
    private int check = 0;
    private String hinhSanpham;
    private Bitmap hinh;

    public Bitmap getHinh() {
        return hinh;
    }

    public void setHinh(Bitmap hinh) {
        this.hinh = hinh;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

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

    public int getCheck() {
        return check;
    }

    public String getHinhSanpham() {
        return hinhSanpham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
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

    public void setCheck(int check) {
        this.check = check;
    }

    public void setHinhSanpham(String hinhSanpham) {
        this.hinhSanpham = hinhSanpham;
    }

    public GioHang() {
    }

    public GioHang(String maSanPham, String tenSanPham, int giaSanPham, int khuyenMai, int soLuongSanPham, String hinhSanpham) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.khuyenMai = khuyenMai;
        this.soLuongSanPham = soLuongSanPham;
        this.hinhSanpham = hinhSanpham;
        this.tongTien = (giaSanPham - (giaSanPham * khuyenMai / 100)) * soLuongSanPham;
    }
}
