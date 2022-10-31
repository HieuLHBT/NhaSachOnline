package com.example.nhasachonline.item;

public class SanPham {
    private String maSanPham;
    private String tenSanPham;
    private String hinhSanPham;
    private String tacGia;
    private String xuatXu;
    private int giaSanPham;
    private int soLuong;
    private int soLuongDanhGia;
    private int binhLuan;

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public void setHinhSanPham(String hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
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

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(String giaSanPham) {
        this.giaSanPham = giaSanPham;
        this.tacGia = tacGia;
        this.xuatXuat = xuatXuat;
        this.soLuong = soLuong;
        this.danhGia = danhGia;
        this.binhLuan = binhLuan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getSoLuongDanhGia() {
        return soLuongDanhGia;
    }

    public void setSoLuongDanhGia(int soLuongDanhGia) {
        this.soLuongDanhGia = soLuongDanhGia;
    }

    public int getBinhLuan() {
        return binhLuan;
    }

    public void setBinhLuan(int binhLuan) {
        this.binhLuan = binhLuan;
    }

    public SanPham() {
    }

    public SanPham(String maSanPham, String tenSanPham, String hinhSanPham, String tacGia, String xuatXu, int giaSanPham, int soLuong, int soLuongDanhGia, int binhLuan) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.hinhSanPham = hinhSanPham;
        this.tacGia = tacGia;
        this.xuatXu = xuatXu;
        this.giaSanPham = giaSanPham;
        this.soLuong = soLuong;
        this.soLuongDanhGia = soLuongDanhGia;
        this.binhLuan = binhLuan;
    }
}
