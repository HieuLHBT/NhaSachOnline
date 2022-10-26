package com.example.nhasachonline.activity.dangnhap;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.activity.QuenMatKhauActivity;
import com.example.nhasachonline.activity.api.NguoiDungAPI;
import com.example.nhasachonline.activity.dangky.DangKyActivity;
import com.example.nhasachonline.doituong.NguoiDung;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class DangNhapActivity extends AppCompatActivity implements DangNhapContract.view {
    private RadioButton khachHang, nhanVien, quanLy;
    private EditText taiKhoan, matKhau;
    private Button btnDangNhap,btnDangKy,btnQuenMatKhau;
    private CheckBox ckNhoMatKhau;
    private DangNhapPresenter dangNhapPresenter;
    private ProgressDialog progressDialog;
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap_layout);
        init();
    }
    private void init() {
        taiKhoan = findViewById(R.id.layoutDN_edtTaiKhoan);
        matKhau = findViewById(R.id.layoutDN_edtNhapMatKhau);
        btnDangNhap = findViewById(R.id.layoutDN_bntDangNhap);
        btnDangKy = findViewById(R.id.layoutDN_btnDangKy);
        dangNhapPresenter = new DangNhapPresenter(this);

        btnDangNhap.setOnClickListener(v -> HandlingLogin());
        btnDangKy.setOnClickListener(v -> startActivity(new Intent(DangNhapActivity.this, DangKyActivity.class)));
        btnQuenMatKhau.setOnClickListener(view -> startActivity(new Intent(DangNhapActivity.this, QuenMatKhauActivity.class)));
    }
    private void HandlingLogin() {
        if(NguoiDungAPI.getInstance()!=null && NguoiDungAPI.getInstance().isLocker()) ;
        String TaiKhoan = taiKhoan.getText().toString();
        String MatKhau = matKhau.getText().toString();
        if (!TextUtils.isEmpty(TaiKhoan) && !TextUtils.isEmpty(MatKhau)) {
            dangNhapPresenter.DangNhap(TaiKhoan, MatKhau);
        }
    }
    @Override
    public void loginThanhCong() {
        progressDialog.dismiss();
    }
    public static void locked (Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        builder.setMessage("Tài khoản của bạn đã bị khóa muốn biết chi tiết xin liên hệ ******");
        builder.setPositiveButton("Ok", (dialog, which) -> {
            dialog.dismiss();
            mAuth.signOut();
        });
        builder.show();
    }
    @Override
    public void loginThatBai(String error) {
        progressDialog.dismiss();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void tienHanhDangNhap() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang chờ xử lý");
        progressDialog.show();
    }


}
