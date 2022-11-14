package com.example.nhasachonline.item;

public class ManHinhChinhQuanLy_ThongKeDon {
    private Integer donThanhCong = 0;
    private Integer donDaHuy = 0;
    private Integer donDangXyLy = 0;
    private Integer soNguoiLamCa1 = 0;
    private Integer soNguoiLamCa2 = 0;

    public Integer getDonThanhCong() {
        return donThanhCong;
    }

    public Integer getDonDaHuy() {
        return donDaHuy;
    }

    public Integer getDonDangXyLy() {
        return donDangXyLy;
    }

    public Integer getSoNguoiLamCa1() {
        return soNguoiLamCa1;
    }

    public Integer getSoNguoiLamCa2() {
        return soNguoiLamCa2;
    }

    public void setDonThanhCong(Integer donThanhCong) {
        this.donThanhCong = donThanhCong;
    }

    public void setDonDaHuy(Integer donDaHuy) {
        this.donDaHuy = donDaHuy;
    }

    public void setDonDangXyLy(Integer donDangXyLy) {
        this.donDangXyLy = donDangXyLy;
    }

    public void setSoNguoiLamCa1(Integer soNguoiLamCa1) {
        this.soNguoiLamCa1 = soNguoiLamCa1;
    }

    public void setSoNguoiLamCa2(Integer soNguoiLamCa2) {
        this.soNguoiLamCa2 = soNguoiLamCa2;
    }

    public ManHinhChinhQuanLy_ThongKeDon() {
    }

    public ManHinhChinhQuanLy_ThongKeDon(Integer donThanhCong, Integer donDaHuy, Integer donDangXyLy, Integer soNguoiLamCa1, Integer soNguoiLamCa2) {
        this.donThanhCong = donThanhCong;
        this.donDaHuy = donDaHuy;
        this.donDangXyLy = donDangXyLy;
        this.soNguoiLamCa1 = soNguoiLamCa1;
        this.soNguoiLamCa2 = soNguoiLamCa2;
    }
}
