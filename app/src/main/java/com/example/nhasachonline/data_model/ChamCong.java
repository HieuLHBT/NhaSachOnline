package com.example.nhasachonline.data_model;

public class ChamCong {
    private String maChamCong;
    private int ca1;
    private int ca2;
    private String gioRaCa1;
    private String gioRaCa2;
    private int gioTangCa;
    private String gioVaoCa1;
    private String gioVaoCa2;
    private String maNhanVien;
    private String ngay;
    private int nghiKhongPhep;
    private int nghiPhep;
    private String thoiGianTre;
    private String trangThaiPhanCong;

    public String getMaChamCong() {
        return maChamCong;
    }

    public int getCa1() {
        return ca1;
    }

    public int getCa2() {
        return ca2;
    }

    public String getGioRaCa1() {
        return gioRaCa1;
    }

    public String getGioRaCa2() {
        return gioRaCa2;
    }

    public int getGioTangCa() {
        return gioTangCa;
    }

    public String getGioVaoCa1() {
        return gioVaoCa1;
    }

    public String getGioVaoCa2() {
        return gioVaoCa2;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getNgay() {
        return ngay;
    }

    public int getNghiKhongPhep() {
        return nghiKhongPhep;
    }

    public int getNghiPhep() {
        return nghiPhep;
    }

    public String getThoiGianTre() {
        return thoiGianTre;
    }

    public String getTrangThaiPhanCong() {
        return trangThaiPhanCong;
    }

    public void setMaChamCong(String maChamCong) {
        this.maChamCong = maChamCong;
    }

    public void setCa1(int ca1) {
        this.ca1 = ca1;
    }

    public void setCa2(int ca2) {
        this.ca2 = ca2;
    }

    public void setGioRaCa1(String gioRaCa1) {
        this.gioRaCa1 = gioRaCa1;
    }

    public void setGioRaCa2(String gioRaCa2) {
        this.gioRaCa2 = gioRaCa2;
    }

    public void setGioTangCa(int gioTangCa) {
        this.gioTangCa = gioTangCa;
    }

    public void setGioVaoCa1(String gioVaoCa1) {
        this.gioVaoCa1 = gioVaoCa1;
    }

    public void setGioVaoCa2(String gioVaoCa2) {
        this.gioVaoCa2 = gioVaoCa2;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public void setNghiKhongPhep(int nghiKhongPhep) {
        this.nghiKhongPhep = nghiKhongPhep;
    }

    public void setNghiPhep(int nghiPhep) {
        this.nghiPhep = nghiPhep;
    }

    public void setThoiGianTre(String thoiGianTre) {
        this.thoiGianTre = thoiGianTre;
    }

    public void setTrangThaiPhanCong(String trangThaiPhanCong) {
        this.trangThaiPhanCong = trangThaiPhanCong;
    }

    public ChamCong() {
    }

    public ChamCong(String maChamCong, int ca1, int ca2, String gioRaCa1, String gioRaCa2, int gioTangCa, String gioVaoCa1, String gioVaoCa2, String maNhanVien, String ngay, int nghiKhongPhep, int nghiPhep, String thoiGianTre, String trangThaiPhanCong) {
        this.maChamCong = maChamCong;
        this.ca1 = ca1;
        this.ca2 = ca2;
        this.gioRaCa1 = gioRaCa1;
        this.gioRaCa2 = gioRaCa2;
        this.gioTangCa = gioTangCa;
        this.gioVaoCa1 = gioVaoCa1;
        this.gioVaoCa2 = gioVaoCa2;
        this.maNhanVien = maNhanVien;
        this.ngay = ngay;
        this.nghiKhongPhep = nghiKhongPhep;
        this.nghiPhep = nghiPhep;
        this.thoiGianTre = thoiGianTre;
        this.trangThaiPhanCong = trangThaiPhanCong;
    }
}
