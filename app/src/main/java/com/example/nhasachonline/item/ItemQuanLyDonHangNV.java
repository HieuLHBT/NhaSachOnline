package com.example.nhasachonline.item;

public class ItemQuanLyDonHangNV {
    private String maDonHang;
    private String trangThaiDonHang;
    private String ngay;

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getTrangThaiDonHang() {
        return trangThaiDonHang;
    }

    public void setTrangThaiDonHang(String trangThaiDonHang) {
        this.trangThaiDonHang = trangThaiDonHang;
    }

    public ItemQuanLyDonHangNV() {
    }

    public ItemQuanLyDonHangNV(String maDonHang, String trangThaiDonHang, String ngay) {
        this.maDonHang = maDonHang;
        this.trangThaiDonHang = trangThaiDonHang;
        this.ngay = ngay;
    }
}
