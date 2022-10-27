package com.example.nhasachonline.data_model;

public class QuanLy {
    private String maQuanLy;
    private int cmnd;
    private String diaChi;
    private String email;
    private String hinhQuanLy;
    private int luong;
    private String matKhau;
    private int soDienThoai;
    private String taiKhoan;
    private String tenQuanLy;

    public String getMaQuanLy() {
        return maQuanLy;
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

    public String getHinhQuanLy() {
        return hinhQuanLy;
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

    public String getTenQuanLy() {
        return tenQuanLy;
    }

    public void setMaQuanLy(String maQuanLy) {
        this.maQuanLy = maQuanLy;
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

    public void setHinhQuanLy(String hinhQuanLy) {
        this.hinhQuanLy = hinhQuanLy;
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

    public void setTenQuanLy(String tenQuanLy) {
        this.tenQuanLy = tenQuanLy;
    }

    public QuanLy() {
    }

    public QuanLy(String maQuanLy, int cmnd, String diaChi, String email, String hinhQuanLy, int luong, String matKhau, int soDienThoai, String taiKhoan, String tenQuanLy) {
        this.maQuanLy = maQuanLy;
        this.cmnd = cmnd;
        this.diaChi = diaChi;
        this.email = email;
        this.hinhQuanLy = hinhQuanLy;
        this.luong = luong;
        this.matKhau = matKhau;
        this.soDienThoai = soDienThoai;
        this.taiKhoan = taiKhoan;
        this.tenQuanLy = tenQuanLy;
    }
}
