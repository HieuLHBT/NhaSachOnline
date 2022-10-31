/*package com.example.nhasachonline.activity.dangky;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.nhasachonline.activity.dangnhap.DangNhapContract;
import com.example.nhasachonline.doituong.NguoiDung;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class DangKyPresenter implements DangKyContract.Presenter {
    private DangKyContract.View view;
    private FirebaseAuth mauth;
    private FirebaseFirestore mFirebase;
    private String TAG = "SIMPLE_TAG";
    public DangKyPresenter(DangKyContract.View view){
        this.view = view;
        this.mauth = FirebaseAuth.getInstance();
        this.mFirebase = FirebaseFirestore.getInstance();
    }


    @Override
    public void DangKy( String TaiKhoan, String Email, String HoTen, String SDT, String MatKhau) {
        view.TienHanhDangKy();
        mFirebase.collection("khachhang").whereEqualTo("kh1",TaiKhoan)
                .whereEqualTo("taikhoan",TaiKhoan).get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        if (task.getResult().isEmpty()){
                            mauth.createUserWithEmailAndPassword(TaiKhoan,MatKhau)
                                    .addOnCompleteListener(task1 -> {
                                        if (task.isSuccessful()){
                                            String id = task1.getResult().getUser().getUid();
                                            mFirebase.collection("khachang")

                                                    .add(new NguoiDung(id, TaiKhoan, Email, HoTen, SDT, MatKhau))
                                                    .addOnSuccessListener(documentReference -> Log.d(TAG, "DangKy: thanhcong"))
                                                    .addOnFailureListener(e -> Log.d(TAG, "DangKy: thatbai"));
                                            view.DangkyThanhCong();
                                        }else {
                                                view.DangKyThatBai("dang ky that bai");
                                        }
                                    }).addOnFailureListener(e -> view.DangKyThatBai("dang ky that bai"));
                        }
                    }
                });
    }
}
*/