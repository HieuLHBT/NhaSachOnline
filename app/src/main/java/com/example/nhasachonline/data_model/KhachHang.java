package com.example.nhasachonline.data_model;

public class KhachHang {
    private String nguoiDung;
    private String maKhachHang;
    private String diaChi;
    private String email;
    private String matKhau;
    private String nganHang;
    private String soDienThoai;
    private String soTaiKhoan;
    private String taiKhoan;
    private String tenKhachHang;

    public String getNguoiDung() {
        return nguoiDung;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getEmail() {
        return email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getNganHang() {
        return nganHang;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getSoTaiKhoan() {
        return soTaiKhoan;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setNguoiDung(String nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setNganHang(String nganHang) {
        this.nganHang = nganHang;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setSoTaiKhoan(String soTaiKhoan) {
        this.soTaiKhoan = soTaiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public KhachHang() {
    }

    public KhachHang(String nguoiDung, String maKhachHang, String diaChi, String email, String matKhau, String nganHang, String soDienThoai, String soTaiKhoan, String taiKhoan, String tenKhachHang) {
        this.nguoiDung = nguoiDung;
        this.maKhachHang = maKhachHang;
        this.diaChi = diaChi;
        this.email = email;
        this.matKhau = matKhau;
        this.nganHang = nganHang;
        this.soDienThoai = soDienThoai;
        this.soTaiKhoan = soTaiKhoan;
        this.taiKhoan = taiKhoan;
        this.tenKhachHang = tenKhachHang;
    }
}
