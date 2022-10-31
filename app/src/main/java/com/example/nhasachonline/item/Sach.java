package com.example.nhasachonline.item;

public class Sach {
    private String maSach;
    private String anhSach;
    private String tenSach;
    private String tacGia;
    private String theLoai;
    private String namSanXuat;
    private String nhaXuatBan;
    private int giaTien;
    private int soLuongKho;
    private int soLuongdanhGia;
    private int binhLuan;

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getAnhSach() {
        return anhSach;
    }

    public void setAnhSach(String anhSach) {
        this.anhSach = anhSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getNamSanXuat() {
        return namSanXuat;
    }

    public void setNamSanXuat(String namSanXuat) {
        this.namSanXuat = namSanXuat;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public int getsoLuongKho() {
        return soLuongKho;
    }

    public void setSoLuongKho(int soLuongKho) {
        this.soLuongKho = soLuongKho;
    }

    public int getSoLuongdanhGia() {
        return soLuongdanhGia;
    }

    public void setSoLuongdanhGia(int soLuongdanhGia) {
        this.soLuongdanhGia = soLuongdanhGia;
    }

    public int getBinhLuan() {
        return binhLuan;
    }

    public void setBinhLuan(int binhLuan) {
        this.binhLuan = binhLuan;
    }

    public Sach() {
    }

    public Sach(String maSach, String anhSach, String tenSach, String tacGia, String theLoai, String namSanXuat, String nhaXuatBan, int giaTien, int soLuongKho, int soLuongdanhGia, int binhLuan) {
        this.maSach = maSach;
        this.anhSach = anhSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.namSanXuat = namSanXuat;
        this.nhaXuatBan = nhaXuatBan;
        this.giaTien = giaTien;
        this.soLuongKho = soLuongKho;
        this.soLuongdanhGia = soLuongdanhGia;
        this.binhLuan = binhLuan;
    }
}
