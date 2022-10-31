package com.example.nhasachonline.item;

public class VanPhongPham {
    private String maVanPhongPham;
    private String anhVanPhongPham;
    private String tenVanPhongPham;
    private String nhaPhanPhoi;
    private String xuatXu;
    private String donVi;
    private int giaTien;
    private int soLuongKho;
    private int SoLuongDanhGia;

    public String getMaVanPhongPham() {
        return maVanPhongPham;
    }

    public void setMaVanPhongPham(String maVanPhongPham) {
        this.maVanPhongPham = maVanPhongPham;
    }

    public String getAnhVanPhongPham() {
        return anhVanPhongPham;
    }

    public void setAnhVanPhongPham(String anhVanPhongPham) {
        this.anhVanPhongPham = anhVanPhongPham;
    }

    public String getTenVanPhongPham() {
        return tenVanPhongPham;
    }

    public void setTenVanPhongPham(String tenVanPhongPham) {
        this.tenVanPhongPham = tenVanPhongPham;
    }

    public String getNhaPhanPhoi() {
        return nhaPhanPhoi;
    }

    public void setNhaPhanPhoi(String nhaPhanPhoi) {
        this.nhaPhanPhoi = nhaPhanPhoi;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public int getSoLuongKho() {
        return soLuongKho;
    }

    public void setSoLuongKho(int soLuongKho) {
        this.soLuongKho = soLuongKho;
    }

    public int getSoLuongDanhGia() {
        return SoLuongDanhGia;
    }

    public void setSoLuongDanhGia(int soLuongDanhGia) {
        SoLuongDanhGia = soLuongDanhGia;
    }

    public VanPhongPham() {
    }

    public VanPhongPham(String maVanPhongPham, String anhVanPhongPham, String tenVanPhongPham, String nhaPhanPhoi, String xuatXu, String donVi, int giaTien, int soLuongKho, int soLuongDanhGia) {
        this.maVanPhongPham = maVanPhongPham;
        this.anhVanPhongPham = anhVanPhongPham;
        this.tenVanPhongPham = tenVanPhongPham;
        this.nhaPhanPhoi = nhaPhanPhoi;
        this.xuatXu = xuatXu;
        this.donVi = donVi;
        this.giaTien = giaTien;
        this.soLuongKho = soLuongKho;
        SoLuongDanhGia = soLuongDanhGia;
    }
}
