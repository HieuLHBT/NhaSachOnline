package com.example.nhasachonline.data_model;

public class VanPhongPham {
    private String maVanPhongPham;
    private String donVi;
    private String giaTien;
    private String hinhVanPhongPham;
    private String khuyenMai;
    private String nhaPhanPhoi;
    private String soLuongKho;
    private String tenVanPhongPham;
    private String xuatXu;

    public String getMaVanPhongPham() {
        return maVanPhongPham;
    }

    public String getDonVi() {
        return donVi;
    }

    public String getGiaTien() {
        return giaTien;
    }

    public String getHinhVanPhongPham() {
        return hinhVanPhongPham;
    }

    public String getKhuyenMai() {
        return khuyenMai;
    }

    public String getNhaPhanPhoi() {
        return nhaPhanPhoi;
    }

    public String getSoLuongKho() {
        return soLuongKho;
    }

    public String getTenVanPhongPham() {
        return tenVanPhongPham;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setMaVanPhongPham(String maVanPhongPham) {
        this.maVanPhongPham = maVanPhongPham;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }

    public void setHinhVanPhongPham(String hinhVanPhongPham) {
        this.hinhVanPhongPham = hinhVanPhongPham;
    }

    public void setKhuyenMai(String khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public void setNhaPhanPhoi(String nhaPhanPhoi) {
        this.nhaPhanPhoi = nhaPhanPhoi;
    }

    public void setSoLuongKho(String soLuongKho) {
        this.soLuongKho = soLuongKho;
    }

    public void setTenVanPhongPham(String tenVanPhongPham) {
        this.tenVanPhongPham = tenVanPhongPham;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public VanPhongPham() {
    }

    public VanPhongPham(String maVanPhongPham, String donVi, String giaTien, String hinhVanPhongPham, String khuyenMai, String nhaPhanPhoi, String soLuongKho, String tenVanPhongPham, String xuatXu) {
        this.maVanPhongPham = maVanPhongPham;
        this.donVi = donVi;
        this.giaTien = giaTien;
        this.hinhVanPhongPham = hinhVanPhongPham;
        this.khuyenMai = khuyenMai;
        this.nhaPhanPhoi = nhaPhanPhoi;
        this.soLuongKho = soLuongKho;
        this.tenVanPhongPham = tenVanPhongPham;
        this.xuatXu = xuatXu;
    }
}
