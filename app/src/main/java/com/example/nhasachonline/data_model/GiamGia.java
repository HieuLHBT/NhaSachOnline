package com.example.nhasachonline.data_model;

public class GiamGia {
    private String maKhachHang;
    private String hinhGiamGia;
    private String maGiamGia;
    private String tienGiamGia;
    private String tieuDe;
    private String yeuCau;
    private String chon;
    private Boolean kiemTra = false;

    public Boolean getKiemTra() {
        return kiemTra;
    }

    public void setKiemTra(Boolean kiemTra) {
        this.kiemTra = kiemTra;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public String getHinhGiamGia() {
        return hinhGiamGia;
    }

    public String getMaGiamGia() {
        return maGiamGia;
    }

    public String getTienGiamGia() {
        return tienGiamGia;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public String getYeuCau() {
        return yeuCau;
    }

    public String getChon() {
        return chon;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public void setHinhGiamGia(String hinhGiamGia) {
        this.hinhGiamGia = hinhGiamGia;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public void setTienGiamGia(String tienGiamGia) {
        this.tienGiamGia = tienGiamGia;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public void setYeuCau(String yeuCau) {
        this.yeuCau = yeuCau;
    }

    public void setChon(String chon) {
        this.chon = chon;
    }

    public GiamGia() {
    }

    public GiamGia(String maKhachHang, String hinhGiamGia, String maGiamGia, String tienGiamGia, String tieuDe, String yeuCau, String chon) {
        this.maKhachHang = maKhachHang;
        this.hinhGiamGia = hinhGiamGia;
        this.maGiamGia = maGiamGia;
        this.tienGiamGia = tienGiamGia;
        this.tieuDe = tieuDe;
        this.yeuCau = yeuCau;
        this.chon = chon;
    }
}
