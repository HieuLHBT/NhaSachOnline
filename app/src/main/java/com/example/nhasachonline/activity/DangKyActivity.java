/*
package com.example.nhasachonline.activity;

import androidx.appcompat.app.AlertDialog;

import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.nhasachonline.R;
// import com.google.firebase.auth.FirebaseAuth;

public class DangKyActivity extends AppCompatActivity implements DangKyContract.View {
    private EditText taiKhoan , eMail, matKhau,
            nhapLaiMatKhau, hoTen, ngaySinh, sDT, diaChi;
    private Spinner gioiTinh;
    private Button btnTaoTaiKhoan;
    private ImageButton btnBack;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private static final String TAG = "simple_test";
    private DangKyPresenter presenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.dangky_layout);
        init();
    }
    private void init(){
    presenter = new DangKyPresenter(this);
    taiKhoan = findViewById(R.id.layoutDK_edtNhapTaiKhoan);
    eMail = findViewById(R.id.layoutDK_edtEmail);
    matKhau = findViewById(R.id.layoutDK_edtNhapMatKhau);
    nhapLaiMatKhau = findViewById(R.id.layoutDK_edtNhapLaiMatKhau);
    hoTen = findViewById(R.id.layoutDK_edtHoTen);
    ngaySinh = findViewById(R.id.layoutDK_edtNgaySinh);
    gioiTinh = findViewById(R.id.layoutDK_spGioiTinh);
    sDT = findViewById(R.id.layoutDK_edtSDT);
    diaChi = findViewById(R.id.layoutDK_edtDiaChi);
    btnBack = findViewById(R.id.layoutDK_btnBack);
    btnTaoTaiKhoan = findViewById(R.id.layoutDK_btnTaoTaiKhoan);
    btnBack.setOnClickListener(view -> finish());
    btnTaoTaiKhoan.setOnClickListener(view -> {
        startActivity(new Intent(this, DangNhapActivity.class));
    });

    }
    @Override
    public void DangkyThanhCong() {
        progressDialog.dismiss();
    }

    @Override
    public void DangKyThatBai(String Error) {
        progressDialog.dismiss();
        Notification(Error);
    }

    @Override
    public void TienHanhDangKy() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang chờ xử lý");
        progressDialog.show();
    }
    public void Notification(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage(text);
        builder.setPositiveButton("Ok", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();
    }
}*/
