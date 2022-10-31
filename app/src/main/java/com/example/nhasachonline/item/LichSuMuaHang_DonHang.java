package com.example.nhasachonline.item;

import java.util.ArrayList;

public class LichSuMuaHang_DonHang {
    private String ngayMuaHang;
    private String thangMuaHang;
    private String namMuaHang;
    private String trangThai;
    private int tongTien;
    private ArrayList<LichSuMuaHang_SanPham> sanPham;

    public String getNgayMuaHang() {
        return ngayMuaHang;
    }

    public void setNgayMuaHang(String ngayMuaHang) {
        this.ngayMuaHang = ngayMuaHang;
    }

    public String getThangMuaHang() {
        return thangMuaHang;
    }

    public void setThangMuaHang(String thangMuaHang) {
        this.thangMuaHang = thangMuaHang;
    }

    public String getNamMuaHang() {
        return namMuaHang;
    }

    public void setNamMuaHang(String namMuaHang) {
        this.namMuaHang = namMuaHang;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public ArrayList<LichSuMuaHang_SanPham> getSanPham() {
        return sanPham;
    }

    public void setSanPham(ArrayList<LichSuMuaHang_SanPham> sanPham) {
        this.sanPham = sanPham;
    }

    public LichSuMuaHang_DonHang() {
    }

    public LichSuMuaHang_DonHang(String ngayMuaHang, String thangMuaHang, String namMuaHang, String trangThai, int tongTien , ArrayList<LichSuMuaHang_SanPham> sanPham) {
        this.ngayMuaHang = ngayMuaHang;
        this.thangMuaHang = thangMuaHang;
        this.namMuaHang = namMuaHang;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
        this.sanPham = sanPham;
    }
}
