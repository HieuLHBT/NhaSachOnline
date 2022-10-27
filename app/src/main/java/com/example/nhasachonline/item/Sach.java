package com.example.nhasachonline.item;

public class Sach {
    private int ID;
    private String anhSach;
    private String tenSach;
    private String tacGia;
    private String theLoai;
    private String namSanXuat;
    private String nhaXuatBan;
    private int gia;
    private int khuyenMai;
    private int soLuong;
    private int soLuongdanhGia;
    private String anhDanhGia;

    public Sach() {
    }

    public Sach(int ID, String anhSach, String tenSach, String tacGia, String theLoai, String namSanXuat, String nhaXuatBan, int gia, int khuyenMai, int soLuong, int soLuongdanhGia, String anhDanhGia) {
        this.ID = ID;
        this.anhSach = anhSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.namSanXuat = namSanXuat;
        this.nhaXuatBan = nhaXuatBan;
        this.gia = gia;
        this.khuyenMai = khuyenMai;
        this.soLuong = soLuong;
        this.soLuongdanhGia = soLuongdanhGia;
        this.anhDanhGia = anhDanhGia;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(int khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getsoLuongdanhGia() {
        return soLuongdanhGia;
    }

    public void setDanhGia(int soLuongdanhGia) {
        this.soLuongdanhGia = soLuongdanhGia;
    }

    public String getAnhDanhGia() {
        return anhDanhGia;
    }

    public void setAnhDanhGia(String anhDanhGia) {
        this.anhDanhGia = anhDanhGia;
    }
}
