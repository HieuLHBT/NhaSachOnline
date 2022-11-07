package com.example.nhasachonline.item;

public class LichLamViec {
    private String ngay;
    private String trangThai = "";

    public String getNgay() {
        return ngay;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public LichLamViec() {
    }

    public LichLamViec(String ngay) {
        this.ngay = ngay;
    }

    public LichLamViec(String ngay, String trangThai) {
        this.ngay = ngay;
        this.trangThai = trangThai;
    }
}
