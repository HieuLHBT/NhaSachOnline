package com.example.nhasachonline.activity.dangnhap;

public interface DangNhapContract {
    interface Presenter {
        void DangNhap(String TaiKhoan , String MatKhau);
    }
    interface view{
        void loginThanhCong();
        void loginThatBai(String error);
        void tienHanhDangNhap();
    }
}
