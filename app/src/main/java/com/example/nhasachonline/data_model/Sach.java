package com.example.nhasachonline.data_model;

public class Sach {
    private String maSach;
    private int giaTien;
    private String hinhSach;
    private int khuyenMai;
    private String ngayXuatBan;
    private String nhaXuatBan;
    private int soLuongKho;
    private String tacGia;
    private String tenSach;
    private String theLoai;

    public String getMaSach() {
        return maSach;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public String getHinhSach() {
        return hinhSach;
    }

    public int getKhuyenMai() {
        return khuyenMai;
    }

    public String getNgayXuatBan() {
        return ngayXuatBan;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public int getSoLuongKho() {
        return soLuongKho;
    }

    public String getTacGia() {
        return tacGia;
    }

    public String getTenSach() {
        return tenSach;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public void setHinhSach(String hinhSach) {
        this.hinhSach = hinhSach;
    }

    public void setKhuyenMai(int khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public void setNgayXuatBan(String ngayXuatBan) {
        this.ngayXuatBan = ngayXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public void setSoLuongKho(int soLuongKho) {
        this.soLuongKho = soLuongKho;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public Sach() {
    }

    public Sach(String maSach, int giaTien, String hinhSach, int khuyenMai, String ngayXuatBan, String nhaXuatBan, int soLuongKho, String tacGia, String tenSach, String theLoai) {
        this.maSach = maSach;
        this.giaTien = giaTien;
        this.hinhSach = hinhSach;
        this.khuyenMai = khuyenMai;
        this.ngayXuatBan = ngayXuatBan;
        this.nhaXuatBan = nhaXuatBan;
        this.soLuongKho = soLuongKho;
        this.tacGia = tacGia;
        this.tenSach = tenSach;
        this.theLoai = theLoai;
    }
}
