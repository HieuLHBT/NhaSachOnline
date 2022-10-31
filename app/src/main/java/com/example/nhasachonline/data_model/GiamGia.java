package com.example.nhasachonline.data_model;

public class GiamGia {
    private String hinhGiamGia;
    private String maGiamGia;
    private String tienGiamGia;
    private String tieuDe;
    private String yeuCau;
    private String chon;

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

    public GiamGia(String hinhGiamGia, String maGiamGia, String tienGiamGia, String tieuDe, String yeuCau, String chon) {
        this.hinhGiamGia = hinhGiamGia;
        this.maGiamGia = maGiamGia;
        this.tienGiamGia = tienGiamGia;
        this.tieuDe = tieuDe;
        this.yeuCau = yeuCau;
        this.chon = chon;
    }
}
