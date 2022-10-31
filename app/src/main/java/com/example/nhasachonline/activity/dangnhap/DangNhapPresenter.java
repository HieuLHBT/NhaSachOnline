/*
package com.example.nhasachonline.activity.dangnhap;

import com.example.nhasachonline.activity.dangky.DangKyPresenter;
import com.google.firebase.auth.FirebaseAuth;

public class DangNhapPresenter implements DangNhapContract.Presenter{
    private DangNhapContract.view view;
    private FirebaseAuth mAuth;
    public DangNhapPresenter (DangNhapContract.view view){
        this.view= view;
        this.mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void DangNhap(String TaiKhoan, String MatKhau) {
        view.tienHanhDangNhap();
        mAuth.signInWithEmailAndPassword(TaiKhoan, MatKhau)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        view.loginThanhCong();
                    }
                    else{
                        view.loginThatBai("Dang Nhap That Bai");
                    }
                }).addOnFailureListener(e -> view.loginThatBai("Dang Nhap That Bai"));
    }
}
*/