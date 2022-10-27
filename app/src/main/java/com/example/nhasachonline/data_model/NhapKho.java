package com.example.nhasachonline.data_model;

public class NhapKho {
    private String maNhapKho;
    private String maNhanVien;
    private String maSanPham;
    private String ngayNhapKho;
    private String soLuongNhap;

    public String getMaNhapKho() {
        return maNhapKho;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getNgayNhapKho() {
        return ngayNhapKho;
    }

    public String getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setMaNhapKho(String maNhapKho) {
        this.maNhapKho = maNhapKho;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setNgayNhapKho(String ngayNhapKho) {
        this.ngayNhapKho = ngayNhapKho;
    }

    public void setSoLuongNhap(String soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }

    public NhapKho() {
    }

    public NhapKho(String maNhapKho, String maNhanVien, String maSanPham, String ngayNhapKho, String soLuongNhap) {
        this.maNhapKho = maNhapKho;
        this.maNhanVien = maNhanVien;
        this.maSanPham = maSanPham;
        this.ngayNhapKho = ngayNhapKho;
        this.soLuongNhap = soLuongNhap;
    }
}
