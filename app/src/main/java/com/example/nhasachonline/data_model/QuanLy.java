package com.example.nhasachonline.data_model;

public class QuanLy {
    private String nguoiDung;
    private String maQuanLy;
    private String cmnd;
    private String diaChi;
    private String email;
    private String hinhQuanLy;
    private String luong;
    private String matKhau;
    private String soDienThoai;
    private String taiKhoan;
    private String tenQuanLy;

    public String getNguoiDung() {
        return nguoiDung;
    }

    public String getMaQuanLy() {
        return maQuanLy;
    }

    public String getCmnd() {
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

    public String getLuong() {
        return luong;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public String getTenQuanLy() {
        return tenQuanLy;
    }

    public void setNguoiDung(String nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public void setMaQuanLy(String maQuanLy) {
        this.maQuanLy = maQuanLy;
    }

    public void setCmnd(String cmnd) {
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

    public void setLuong(String luong) {
        this.luong = luong;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setSoDienThoai(String soDienThoai) {
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

    public QuanLy(String nguoiDung, String maQuanLy, String cmnd, String diaChi, String email, String hinhQuanLy, String luong, String matKhau, String soDienThoai, String taiKhoan, String tenQuanLy) {
        this.nguoiDung = nguoiDung;
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
