package com.example.nhasachonline.activity;

import android.widget.Spinner;

public interface DangKyContract {
    interface Presenter{
        void DangKy(String TaiKhoan, String Email , String HoTen, String SDT, String MatKhau);
    }
    interface View {
        void DangkyThanhCong();
        void DangKyThatBai( String Error);
        void TienHanhDangKy();
    }
}
