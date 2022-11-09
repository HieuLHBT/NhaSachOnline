package com.example.nhasachonline.item;

public class ItemSanPham {
    private String maSanPham;
    private String tenSanPham;
    private String hinhSanPham;
    private String tacGia;
    private String xuatXu;
    private String theLoai;
    private String namSanXuat;
    private String nhaXuatBan;
    private String nhaPhanPhoi;
    private String donVi;
    private int giaSanPham;
    private int soLuong = 1;
    private int khuyenMai;
    private int soLuongDanhGia;
    private int binhLuan;

    public ItemSanPham(String maSach, String tenSach, String theLoai, String tacGia, String nhaXuatBan, String ngayXuatBan, String giaTien, String soLuongKho, String hinhSach) {
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public void setHinhSanPham(String hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getSoLuongDanhGia() {
        return soLuongDanhGia;
    }

    public void setSoLuongDanhGia(int soLuongDanhGia) {
        this.soLuongDanhGia = soLuongDanhGia;
    }

    public int getBinhLuan() {
        return binhLuan;
    }

    public void setBinhLuan(int binhLuan) {
        this.binhLuan = binhLuan;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getNamSanXuat() {
        return namSanXuat;
    }

    public void setNamSanXuat(String namSanXuat) {
        this.namSanXuat = namSanXuat;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public String getNhaPhanPhoi() {
        return nhaPhanPhoi;
    }

    public void setNhaPhanPhoi(String nhaPhanPhoi) {
        this.nhaPhanPhoi = nhaPhanPhoi;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public int getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(int khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public ItemSanPham() {
    }

    public ItemSanPham(String maSanPham, String tenSanPham, String hinhSanPham, String tacGia, String xuatXu, String theLoai, String namSanXuat, String nhaXuatBan, String nhaPhanPhoi, String donVi, int giaSanPham, int khuyenMai, int soLuongDanhGia, int binhLuan) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.hinhSanPham = hinhSanPham;
        this.tacGia = tacGia;
        this.xuatXu = xuatXu;
        this.theLoai = theLoai;
        this.namSanXuat = namSanXuat;
        this.nhaXuatBan = nhaXuatBan;
        this.nhaPhanPhoi = nhaPhanPhoi;
        this.donVi = donVi;
        this.giaSanPham = giaSanPham;
        this.khuyenMai = khuyenMai;
        this.soLuongDanhGia = soLuongDanhGia;
        this.binhLuan = binhLuan;
    }
}
