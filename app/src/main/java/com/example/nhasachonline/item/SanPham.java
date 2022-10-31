package com.example.nhasachonline.item;

public class SanPham {
    private String maSanPham;
    private String hinhSanPham;
    private String tenSanPham;
    private int giaSanPham;
    private String tacGia;
    private String xuatXuat;
    private int soLuong;
    private int danhGia;
    private int binhLuan;

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public String getTacGia() {
        return tacGia;
    }

    public String getXuatXuat() {
        return xuatXuat;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getDanhGia() {
        return danhGia;
    }

    public int getBinhLuan() {
        return binhLuan;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setHinhSanPham(String hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public void setXuatXuat(String xuatXuat) {
        this.xuatXuat = xuatXuat;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setDanhGia(int danhGia) {
        this.danhGia = danhGia;
    }

    public void setBinhLuan(int binhLuan) {
        this.binhLuan = binhLuan;
    }

    public SanPham() {
    }

    public SanPham(String maSanPham, String hinhSanPham, String tenSanPham, int giaSanPham, String tacGia, String xuatXuat, int soLuong, int danhGia, int binhLuan) {
        this.maSanPham = maSanPham;
        this.hinhSanPham = hinhSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.tacGia = tacGia;
        this.xuatXuat = xuatXuat;
        this.soLuong = soLuong;
        this.danhGia = danhGia;
        this.binhLuan = binhLuan;
    }
}
