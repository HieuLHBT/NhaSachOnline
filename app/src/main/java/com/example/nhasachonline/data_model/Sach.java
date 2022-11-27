package com.example.nhasachonline.data_model;

import java.io.IOException;
import java.text.ParseException;

public class Sach {
    private String maSach;
    private String giaTien;
    private String hinhSach;
    private String khuyenMai;
    private String ngayXuatBan;
    private String nhaXuatBan;
    private String soLuongKho;
    private String soLuongDanhGia;
    private String tacGia;
    private String tenSach;
    private String theLoai;
    private String anhDanhGia;

    public Sach() {
    }

    public Sach(String maSach, String giaTien, String hinhSach, String khuyenMai, String ngayXuatBan, String nhaXuatBan, String soLuongKho, String soLuongDanhGia, String tacGia, String tenSach, String theLoai, String anhDanhGia) {
        this.maSach = maSach;
        this.giaTien = giaTien;
        this.hinhSach = hinhSach;
        this.khuyenMai = khuyenMai;
        this.ngayXuatBan = ngayXuatBan;
        this.nhaXuatBan = nhaXuatBan;
        this.soLuongKho = soLuongKho;
        this.soLuongDanhGia = soLuongDanhGia;
        this.tacGia = tacGia;
        this.tenSach = tenSach;
        this.theLoai = theLoai;
        this.anhDanhGia = anhDanhGia;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }

    public String getHinhSach() {
        return hinhSach;
    }

    public void setHinhSach(String hinhSach) {
        this.hinhSach = hinhSach;
    }

    public String getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(String khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public String getNgayXuatBan() {
        return ngayXuatBan;
    }

    public void setNgayXuatBan(String ngayXuatBan) {
        this.ngayXuatBan = ngayXuatBan;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }


    public String getSoLuongKho() {
        return soLuongKho;
    }

    public void setSoLuongKho(String soLuongKho) {
        this.soLuongKho = soLuongKho;
    }

    public String getSoLuongDanhGia() {
        return soLuongDanhGia;
    }

    public void setSoLuongDanhGia(String soLuongDanhGia) {
        this.soLuongDanhGia = soLuongDanhGia;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getAnhDanhGia() {
        return anhDanhGia;
    }

    public void setAnhDanhGia(String anhDanhGia) {
        this.anhDanhGia = anhDanhGia;
    }

    public int stringToInt(String value){
        if(value == null){
            return  0;
        }
        try {
            return Integer.parseInt(value);
        }catch (NullPointerException  e){
            return  0;
        }
    }
}