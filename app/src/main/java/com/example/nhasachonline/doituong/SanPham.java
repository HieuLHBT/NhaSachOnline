package com.example.nhasachonline.doituong;

public class SanPham {
    private String id;
    private String imgAnhSanPham;
    private String tenSanPham;
    private String giaSanPham;

    public SanPham() {
    }

    public SanPham(String id, String imgAnhSanPham, String tenSanPham, String giaSanPham) {
        this.id = id;
        this.imgAnhSanPham = imgAnhSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgAnhSanPham() {
        return imgAnhSanPham;
    }

    public void setImgAnhSanPham(String imgAnhSanPham) {
        this.imgAnhSanPham = imgAnhSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(String giaSanPham) {
        this.giaSanPham = giaSanPham;
    }
}
