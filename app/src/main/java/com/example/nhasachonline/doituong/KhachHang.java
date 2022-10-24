package com.example.nhasachonline.doituong;

import android.widget.Spinner;

public class KhachHang {
    private String hoTen;
    private Spinner gioiTinh;
    private String ngaySinh;
    private String Email;
    private String SDT;
    private String tenNganHang;
    private String sTKNganHang;
    private String diaChi;

    public KhachHang() {
    }

    public KhachHang(String hoTen, Spinner gioiTinh, String ngaySinh, String email, String SDT, String tenNganHang, String sTKNganHang, String diaChi) {
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.Email = email;
        this.SDT = SDT;
        this.tenNganHang = tenNganHang;
        this.sTKNganHang = sTKNganHang;
        this.diaChi = diaChi;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Spinner getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Spinner gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getTenNganHang() {
        return tenNganHang;
    }

    public void setTenNganHang(String tenNganHang) {
        this.tenNganHang = tenNganHang;
    }

    public String getsTKNganHang() {
        return sTKNganHang;
    }

    public void setsTKNganHang(String sTKNganHang) {
        this.sTKNganHang = sTKNganHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "hoTen='" + hoTen + '\'' +
                ", gioiTinh=" + gioiTinh +
                ", ngaySinh='" + ngaySinh + '\'' +
                ", Email='" + Email + '\'' +
                ", SDT='" + SDT + '\'' +
                ", tenNganHang='" + tenNganHang + '\'' +
                ", sTKNganHang='" + sTKNganHang + '\'' +
                ", diaChi='" + diaChi + '\'' +
                '}';
    }
}
