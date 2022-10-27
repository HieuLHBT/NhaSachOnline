package com.example.nhasachonline.data_model;

public class NhanVien {
    private String maNhanVien;
    private int cmnd;
    private String diaChi;
    private String email;
    private String hinhNhanVien;
    private int luong;
    private String matKhau;
    private int soDienThoai;
    private String taiKhoan;
    private String tenNhanVien;

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public int getCmnd() {
        return cmnd;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getEmail() {
        return email;
    }

    public String getHinhNhanVien() {
        return hinhNhanVien;
    }

    public int getLuong() {
        return luong;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public int getSoDienThoai() {
        return soDienThoai;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setCmnd(int cmnd) {
        this.cmnd = cmnd;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHinhNhanVien(String hinhNhanVien) {
        this.hinhNhanVien = hinhNhanVien;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setSoDienThoai(int soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public NhanVien() {
    }

    public NhanVien(String maNhanVien, int cmnd, String diaChi, String email, String hinhNhanVien, int luong, String matKhau, int soDienThoai, String taiKhoan, String tenNhanVien) {
        this.maNhanVien = maNhanVien;
        this.cmnd = cmnd;
        this.diaChi = diaChi;
        this.email = email;
        this.hinhNhanVien = hinhNhanVien;
        this.luong = luong;
        this.matKhau = matKhau;
        this.soDienThoai = soDienThoai;
        this.taiKhoan = taiKhoan;
        this.tenNhanVien = tenNhanVien;
    }
}
