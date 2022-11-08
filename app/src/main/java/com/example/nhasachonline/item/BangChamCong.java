package com.example.nhasachonline.item;

public class BangChamCong {
    private int soDonDaNhan = 0;
    private int soDonDaGiao = 0;
    private int soDonDaHuy = 0;

    public int getSoDonDaNhan() {
        return soDonDaNhan;
    }

    public int getSoDonDaGiao() {
        return soDonDaGiao;
    }

    public int getSoDonDaHuy() {
        return soDonDaHuy;
    }

    public void setSoDonDaNhan(int soDonDaNhan) {
        this.soDonDaNhan = soDonDaNhan;
    }

    public void setSoDonDaGiao(int soDonDaGiao) {
        this.soDonDaGiao = soDonDaGiao;
    }

    public void setSoDonDaHuy(int soDonDaHuy) {
        this.soDonDaHuy = soDonDaHuy;
    }

    public BangChamCong() {
    }

    public BangChamCong(int soDonDaNhan, int soDonDaGiao, int soDonDaHuy) {
        this.soDonDaNhan = soDonDaNhan;
        this.soDonDaGiao = soDonDaGiao;
        this.soDonDaHuy = soDonDaHuy;
    }
}
