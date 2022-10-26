package com.example.nhasachonline.doituong;

public class NguoiDung {
    private String iD;
    private String tenTaiKhoan;
    private String email;
    private String nganHang;
    private String soTaiKhoan;
    private String matKhau;
    private String hoTen;
    private String sDT;
    private int loaiNguoiDung;
    private static int khachHang = 1 ;
    private static int nhanVien = 2;

    public NguoiDung() {
    }

    public NguoiDung(String iD, String tenTaiKhoan, String email, String nganHang, String soTaiKhoan, String matKhau, String hoTen, String sDT, int loaiNguoiDung) {
        this.iD = iD;
        this.tenTaiKhoan = tenTaiKhoan;
        this.email = email;
        this.nganHang = nganHang;
        this.soTaiKhoan = soTaiKhoan;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.sDT = sDT;
        this.loaiNguoiDung = loaiNguoiDung;
    }

    public NguoiDung(String uid, String taiKhoan, String email, String hoTen, String sdt, String matKhau) {
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNganHang() {
        return nganHang;
    }

    public void setNganHang(String nganHang) {
        this.nganHang = nganHang;
    }

    public String getSoTaiKhoan() {
        return soTaiKhoan;
    }

    public void setSoTaiKhoan(String soTaiKhoan) {
        this.soTaiKhoan = soTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public int getLoaiNguoiDung() {
        return loaiNguoiDung;
    }

    public void setLoaiNguoiDung(int loaiNguoiDung) {
        this.loaiNguoiDung = loaiNguoiDung;
    }

    public static int getKhachHang() {
        return khachHang;
    }

    public static void setKhachHang(int khachHang) {
        NguoiDung.khachHang = khachHang;
    }

    public static int getNhanVien() {
        return nhanVien;
    }

    public static void setNhanVien(int nhanVien) {
        NguoiDung.nhanVien = nhanVien;
    }



    @Override
    public String toString() {
        return "NguoiDung{" +
                "iD='" + iD + '\'' +
                ", tenTaiKhoan='" + tenTaiKhoan + '\'' +
                ", email='" + email + '\'' +
                ", nganHang='" + nganHang + '\'' +
                ", soTaiKhoan='" + soTaiKhoan + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", sDT='" + sDT + '\'' +
                ", loaiNguoiDung=" + loaiNguoiDung +
                '}';
    }
}
