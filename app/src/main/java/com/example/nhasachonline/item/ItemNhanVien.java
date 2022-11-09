package com.example.nhasachonline.item;

public class ItemNhanVien {
    private String nguoiDung;
    private String maNhanVien;
    private String tenNhanVien;
    private String cmnd;
    private String diaChi;
    private String email;
    private String hinhNhanVien;
    private String luong;
    private String matKhau;
    private String soDienThoai;
    private String taiKhoan;

    public String getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(String nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHinhNhanVien() {
        return hinhNhanVien;
    }

    public void setHinhNhanVien(String hinhNhanVien) {
        this.hinhNhanVien = hinhNhanVien;
    }

    public String getLuong() {
        return luong;
    }

    public void setLuong(String luong) {
        this.luong = luong;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public ItemNhanVien() {
    }

    public ItemNhanVien(String nguoiDung, String maNhanVien, String tenNhanVien, String cmnd, String diaChi, String email, String hinhNhanVien, String luong, String matKhau, String soDienThoai, String taiKhoan) {
        this.nguoiDung = nguoiDung;
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.cmnd = cmnd;
        this.diaChi = diaChi;
        this.email = email;
        this.hinhNhanVien = hinhNhanVien;
        this.luong = luong;
        this.matKhau = matKhau;
        this.soDienThoai = soDienThoai;
        this.taiKhoan = taiKhoan;
    }
}
