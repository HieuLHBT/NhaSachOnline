package com.example.nhasachonline.item;

public class BangLuong {
    private String maNhanVien;
    private String tenNhanVien;
    private Integer tongGioLam = 0;
    private Integer diTre = 0;
    private Integer tangCa = 0;
    private Integer soCaNghiCoPhep = 0;
    private Integer soCaNghiKhongPhep = 0;
    private Integer tongDonDaGiao = 0;
    private Integer luongCanBan = 0;
    private Integer thuongChuyenCan = 0;
    private Integer tongLuong = 0;

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public Integer getTongGioLam() {
        return tongGioLam;
    }

    public Integer getDiTre() {
        return diTre;
    }

    public Integer getTangCa() {
        return tangCa;
    }

    public Integer getSoCaNghiCoPhep() {
        return soCaNghiCoPhep;
    }

    public Integer getSoCaNghiKhongPhep() {
        return soCaNghiKhongPhep;
    }

    public Integer getTongDonDaGiao() {
        return tongDonDaGiao;
    }

    public Integer getLuongCanBan() {
        return luongCanBan;
    }

    public Integer getThuongChuyenCan() {
        return thuongChuyenCan;
    }

    public Integer getTongLuong() {
        return tongLuong;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public void setTongGioLam(Integer tongGioLam) {
        this.tongGioLam = tongGioLam;
    }

    public void setDiTre(Integer diTre) {
        this.diTre = diTre;
    }

    public void setTangCa(Integer tangCa) {
        this.tangCa = tangCa;
    }

    public void setSoCaNghiCoPhep(Integer soCaNghiCoPhep) {
        this.soCaNghiCoPhep = soCaNghiCoPhep;
    }

    public void setSoCaNghiKhongPhep(Integer soCaNghiKhongPhep) {
        this.soCaNghiKhongPhep = soCaNghiKhongPhep;
    }

    public void setTongDonDaGiao(Integer tongDonDaGiao) {
        this.tongDonDaGiao = tongDonDaGiao;
    }

    public void setLuongCanBan(Integer luongCanBan) {
        this.luongCanBan = luongCanBan;
    }

    public void setThuongChuyenCan(Integer thuongChuyenCan) {
        this.thuongChuyenCan = thuongChuyenCan;
    }

    public void setTongLuong(Integer tongLuong) {
        this.tongLuong = tongLuong;
    }

    public BangLuong() {
    }

    public BangLuong(String maNhanVien, String tenNhanVien, Integer tongGioLam, Integer diTre, Integer tangCa, Integer soCaNghiCoPhep, Integer soCaNghiKhongPhep, Integer tongDonDaGiao, Integer luongCanBan, Integer thuongChuyenCan, Integer tongLuong) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.tongGioLam = tongGioLam;
        this.diTre = diTre;
        this.tangCa = tangCa;
        this.soCaNghiCoPhep = soCaNghiCoPhep;
        this.soCaNghiKhongPhep = soCaNghiKhongPhep;
        this.tongDonDaGiao = tongDonDaGiao;
        this.luongCanBan = luongCanBan;
        this.thuongChuyenCan = thuongChuyenCan;
        this.tongLuong = tongLuong;
    }
}
