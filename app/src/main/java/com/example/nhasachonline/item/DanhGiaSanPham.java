package com.example.nhasachonline.item;

public class DanhGiaSanPham {
    private String id;
    private String idSanPham;
    private String imgAnhSanPham;
    private String gia;
    private String soLuong;
    private String soLuongDanhGia;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getImgAnhSanPham() {
        return imgAnhSanPham;
    }

    public void setImgAnhSanPham(String imgAnhSanPham) {
        this.imgAnhSanPham = imgAnhSanPham;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getSoLuongDanhGia() {
        return soLuongDanhGia;
    }

    public void setSoLuongDanhGia(String soLuongDanhGia) {
        this.soLuongDanhGia = soLuongDanhGia;
    }

    public DanhGiaSanPham() {
    }

    public DanhGiaSanPham(String id, String idSanPham, String imgAnhSanPham, String gia, String soLuong, String soLuongDanhGia) {
        this.id = id;
        this.idSanPham = idSanPham;
        this.imgAnhSanPham = imgAnhSanPham;
        this.gia = gia;
        this.soLuong = soLuong;
        this.soLuongDanhGia = soLuongDanhGia;
    }
}
