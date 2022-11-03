package com.example.nhasachonline.item;

import android.widget.Spinner;

public class ItemKhachHang {
//    private int iD;
    public String maKhachHang;
    public String matKhau;
    public String taiKhoan;
    public String hoTen;
    public Spinner gioiTinh;
    public String ngaySinh;
    public String Email;
    public String SDT;
    public String tenNganHang;
    public String sTKNganHang;
    public String diaChi;

    public ItemKhachHang(){

    }
    public ItemKhachHang(String maKhachHang, String matKhau, String taiKhoan, String hoTen, Spinner gioiTinh, String ngaySinh, String email, String SDT, String tenNganHang, String sTKNganHang, String diaChi) {
        this.maKhachHang = maKhachHang;
        this.matKhau = matKhau;
        this.taiKhoan = taiKhoan;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        Email = email;
        this.SDT = SDT;
        this.tenNganHang = tenNganHang;
        this.sTKNganHang = sTKNganHang;
        this.diaChi = diaChi;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
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
                "maKhachHang='" + maKhachHang + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", taiKhoan='" + taiKhoan + '\'' +
                ", hoTen='" + hoTen + '\'' +
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
