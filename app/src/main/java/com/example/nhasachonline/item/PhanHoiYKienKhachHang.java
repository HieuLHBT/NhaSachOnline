package com.example.nhasachonline.item;

import java.io.Serializable;

public class PhanHoiYKienKhachHang implements Serializable {
    private String maSanPham;
    private String tenSanPham;
    private String maDonHang;
    private String maKhachHang;
    private String tenKhachHang;
    private String danhGia;
    private String thoiGianBinhLuan;
    private String noiDungBinhLuan;
    private String maNhanVien;
    private String tenNhanVien;
    private String noiDungTraLoi;

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public String getDanhGia() {
        return danhGia;
    }

    public String getThoiGianBinhLuan() {
        return thoiGianBinhLuan;
    }

    public String getNoiDungBinhLuan() {
        return noiDungBinhLuan;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public String getNoiDungTraLoi() {
        return noiDungTraLoi;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public void setDanhGia(String danhGia) {
        this.danhGia = danhGia;
    }

    public void setThoiGianBinhLuan(String thoiGianBinhLuan) {
        this.thoiGianBinhLuan = thoiGianBinhLuan;
    }

    public void setNoiDungBinhLuan(String noiDungBinhLuan) {
        this.noiDungBinhLuan = noiDungBinhLuan;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public void setNoiDungTraLoi(String noiDungTraLoi) {
        this.noiDungTraLoi = noiDungTraLoi;
    }

    public PhanHoiYKienKhachHang() {
    }

    public PhanHoiYKienKhachHang(String maSanPham, String tenSanPham, String maDonHang, String maKhachHang, String tenKhachHang, String danhGia, String thoiGianBinhLuan, String noiDungBinhLuan, String maNhanVien, String tenNhanVien, String noiDungTraLoi) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.maDonHang = maDonHang;
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.danhGia = danhGia;
        this.thoiGianBinhLuan = thoiGianBinhLuan;
        this.noiDungBinhLuan = noiDungBinhLuan;
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.noiDungTraLoi = noiDungTraLoi;
    }
}
