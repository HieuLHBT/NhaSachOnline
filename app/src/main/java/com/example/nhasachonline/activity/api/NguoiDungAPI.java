package com.example.nhasachonline.activity.api;

import android.app.Application;

public class NguoiDungAPI extends Application {
    private String id, name , email, taiKhoan, matKhau;
    private int loaiNguoiDung;
    private Double point;
    private boolean isLocker;
    private static NguoiDungAPI instance;

    public static NguoiDungAPI getInstance() {
        if (instance == null)
            instance = new NguoiDungAPI();
        return instance;
    }
    public static void setInstance(NguoiDungAPI instance) {
        NguoiDungAPI.instance = instance;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getLoaiNguoiDung() {
        return loaiNguoiDung;
    }

    public void setLoaiNguoiDung(int loaiNguoiDung) {
        this.loaiNguoiDung = loaiNguoiDung;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public boolean isLocker() {
        return isLocker;
    }

    public void setLocker(boolean locker) {
        isLocker = locker;
    }

}
