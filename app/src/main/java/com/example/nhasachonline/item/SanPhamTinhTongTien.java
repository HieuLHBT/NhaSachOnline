package com.example.nhasachonline.item;

public class SanPhamTinhTongTien {
    private String maSanPham;
    private int giaTien;
    private int khuyenMai;
    private int soLuongMua;
    private int tongTien;

    public String getMaSanPham() {
        return maSanPham;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public int getKhuyenMai() {
        return khuyenMai;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public void setKhuyenMai(int khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public SanPhamTinhTongTien() {
    }

    public SanPhamTinhTongTien(String maSanPham, int giaTien, int khuyenMai, int soLuongMua) {
        this.maSanPham = maSanPham;
        this.giaTien = giaTien;
        this.khuyenMai = khuyenMai;
        this.soLuongMua = soLuongMua;
        this.tongTien = (giaTien - (giaTien * khuyenMai / 100)) * soLuongMua;
    }
}
