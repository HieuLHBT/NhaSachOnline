package com.example.nhasachonline.doituong;

public class Sach {
    private int ID;
    private String anhSach;
    private String tenSach;
    private String tacGia;
    private String theLoai;
    private String namSanXuat;
    private String nhaXuatBan;
    private double gia;
    private double khuyenMai;
    private double soLuong;
    private double soLuongdanhGia;
    private String anhDanhGia;

    public Sach() {
    }

    public Sach(int ID, String anhSach, String tenSach, String tacGia, String theLoai, String namSanXuat, String nhaXuatBan, double gia, double khuyenMai, double soLuong, double soLuongdanhGia, String anhDanhGia) {
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

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public double getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(double khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public double getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(double soLuong) {
        this.soLuong = soLuong;
    }

    public double getsoLuongdanhGia() {
        return soLuongdanhGia;
    }

    public void setDanhGia(double soLuongdanhGia) {
        this.soLuongdanhGia = soLuongdanhGia;
    }

    public String getAnhDanhGia() {
        return anhDanhGia;
    }

    public void setAnhDanhGia(String anhDanhGia) {
        this.anhDanhGia = anhDanhGia;
    }
}
