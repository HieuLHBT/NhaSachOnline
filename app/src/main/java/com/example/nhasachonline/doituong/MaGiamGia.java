package com.example.nhasachonline.doituong;

public class MaGiamGia {
    private String tieuDeMaGiamGia;
    private String tienGiamGia;
   // private String hinhMaGiamGia;

    public String getTieuDeMaGiamGia() {
        return tieuDeMaGiamGia;
    }

    public void setTieuDeMaGiamGia(String tieuDeMaGiamGia) {
        this.tieuDeMaGiamGia = tieuDeMaGiamGia;
    }

    public String getTienGiamGia() {
        return tienGiamGia;
    }

    public void setTienGiamGia(String tienGiamGia) {
        this.tienGiamGia = tienGiamGia;
    }

//    public String getHinhMaGiamGia() {
//        return hinhMaGiamGia;
//    }
//
//    public void setHinhMaGiamGia(String hinhMaGiamGia) {
//        this.hinhMaGiamGia = hinhMaGiamGia;
//    }

    public MaGiamGia() {
    }

    public MaGiamGia(String tieuDeMaGiamGia, String tienGiamGia) {
        this.tieuDeMaGiamGia = tieuDeMaGiamGia;
        this.tienGiamGia = tienGiamGia;
        //this.hinhMaGiamGia = hinhMaGiamGia;
    }
}
