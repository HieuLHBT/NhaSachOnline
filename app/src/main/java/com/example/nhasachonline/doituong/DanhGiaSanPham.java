package com.example.nhasachonline.doituong;

public class DanhGiaSanPham {
    private String tenSanPham;
    private String hinhSanPham;

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public void setHinhSanPham(String hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }

    public DanhGiaSanPham() {
    }

    public DanhGiaSanPham(String tenSanPham, String hinhSanPham) {
        this.tenSanPham = tenSanPham;
        this.hinhSanPham = hinhSanPham;
    }
}
