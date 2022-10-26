package com.example.nhasachonline.doituong;

public class LichSuMuaHang_SanPham {
    private String tenSanPham;
    private int giaSanPham;
    //private String hinhSanPham;


    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

//    public String getHinhSanPham() {
//        return hinhSanPham;
//    }
//
//    public void setHinhSanPham(String hinhSanPham) {
//        this.hinhSanPham = hinhSanPham;
//    }

    public LichSuMuaHang_SanPham() {
    }

    public LichSuMuaHang_SanPham(String tenSanPham, int giaSanPham) {
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
    }
}
