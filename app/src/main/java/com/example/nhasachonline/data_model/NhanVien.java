package com.example.nhasachonline.data_model;

public class NhanVien {
    private String nguoiDung;
    private String maNhanVien;
    private String cmnd;
    private String diaChi;
    private String email;
    private String hinhNhanVien;
    private String luong;
    private String matKhau;
    private String soDienThoai;
    private String taiKhoan;
    private String tenNhanVien;

    public String getNguoiDung() {
        return nguoiDung;
    }

    public String getMaNhanVien() {
        return maNhanVien;
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

    public String getHinhNhanVien() {
        return hinhNhanVien;
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

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setNguoiDung(String nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
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

    public void setHinhNhanVien(String hinhNhanVien) {
        this.hinhNhanVien = hinhNhanVien;
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

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public NhanVien() {
    }

    public NhanVien(String nguoiDung, String maNhanVien, String cmnd, String diaChi, String email, String hinhNhanVien, String luong, String matKhau, String soDienThoai, String taiKhoan, String tenNhanVien) {
        this.nguoiDung = nguoiDung;
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
