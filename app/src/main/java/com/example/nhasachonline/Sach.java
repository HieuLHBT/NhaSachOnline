package com.example.nhasachonline;

public class Sach {
    private int ID;
    private String tenSanPham;
    private String tacGia;
    private double gia;
    private double khuyenMai;
    private double soLuong;
    private String danhGia;

    public Sach(int ID,String tenSanPham, String tacGia, double gia, double khuyenMai, double soLuong, String danhGia) {
        this.ID = ID;
        this.tenSanPham = tenSanPham;
        this.tacGia = tacGia;
        this.gia = gia;
        this.khuyenMai = khuyenMai;
        this.soLuong = soLuong;
        this.danhGia = danhGia;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
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

    public String getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(String danhGia) {
        this.danhGia = danhGia;
    }
}
