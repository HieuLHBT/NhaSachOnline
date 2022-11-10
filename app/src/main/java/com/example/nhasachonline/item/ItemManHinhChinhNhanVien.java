package com.example.nhasachonline.item;

public class ItemManHinhChinhNhanVien {
    private String hinhNhanVien;
    private String tenNhanVien;
    private String maNhanVien;

    public String getHinhNhanVien() {
        return hinhNhanVien;
    }

    public void setHinhNhanVien(String hinhNhanVien) {
        this.hinhNhanVien = hinhNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public ItemManHinhChinhNhanVien() {
    }

    public ItemManHinhChinhNhanVien(String hinhNhanVien, String tenNhanVien, String maNhanVien) {
        this.hinhNhanVien = hinhNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.maNhanVien = maNhanVien;
    }
}
