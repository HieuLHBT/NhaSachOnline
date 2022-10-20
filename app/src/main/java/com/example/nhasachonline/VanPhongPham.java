package com.example.nhasachonline;

public class VanPhongPham {
    private int ID;
    private String anhVanPhongPham;
    private String tenVanPhongPham;
    private String nhaPhanPhoi;
    private String xuatXu;
    private String nhaXuatBan;
    private String donVi;
    private double gia;
    private double khuyenMai;
    private double soLuong;
    private String danhGia;
    private String anhDanhGia;

    public VanPhongPham() {
    }

    public VanPhongPham(int ID, String anhVanPhongPham, String tenVanPhongPham, String nhaPhanPhoi, String xuatXu, String nhaXuatBan, String donVi, double gia, double khuyenMai, double soLuong, String danhGia, String anhDanhGia) {
        this.ID = ID;
        this.anhVanPhongPham = anhVanPhongPham;
        this.tenVanPhongPham = tenVanPhongPham;
        this.nhaPhanPhoi = nhaPhanPhoi;
        this.xuatXu = xuatXu;
        this.nhaXuatBan = nhaXuatBan;
        this.donVi = donVi;
        this.gia = gia;
        this.khuyenMai = khuyenMai;
        this.soLuong = soLuong;
        this.danhGia = danhGia;
        this.anhDanhGia = anhDanhGia;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAnhVanPhongPham() {
        return anhVanPhongPham;
    }

    public void setAnhVanPhongPham(String anhVanPhongPham) {
        this.anhVanPhongPham = anhVanPhongPham;
    }

    public String getTenVanPhongPham() {
        return tenVanPhongPham;
    }

    public void setTenVanPhongPham(String tenVanPhongPham) {
        this.tenVanPhongPham = tenVanPhongPham;
    }

    public String getNhaPhanPhoi() {
        return nhaPhanPhoi;
    }

    public void setNhaPhanPhoi(String nhaPhanPhoi) {
        this.nhaPhanPhoi = nhaPhanPhoi;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
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

    public String getAnhDanhGia() {
        return anhDanhGia;
    }

    public void setAnhDanhGia(String anhDanhGia) {
        this.anhDanhGia = anhDanhGia;
    }
}
