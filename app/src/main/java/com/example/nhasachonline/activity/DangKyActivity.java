package com.example.nhasachonline.activity;

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
import com.google.firebase.auth.FirebaseAuth;

public class DangKyActivity extends AppCompatActivity {
    private EditText taiKhoan , eMail, matKhau,
            nhapLaiMatKhau, hoTen, ngaySinh, sDT, diaChi;
    private Spinner gioiTinh;
    private Button btnTaoTaiKhoan;
    private ImageButton btnBack;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky_layout);
        //get firebase auth instance
        auth = FirebaseAuth.getInstance();
        btnBack = (ImageButton) findViewById(R.id.layoutDK_btnBack);
        btnTaoTaiKhoan = (Button) findViewById(R.id.layoutDK_btnTaoTaiKhoan);
        taiKhoan = (EditText) findViewById(R.id.layoutDK_edtNhapTaiKhoan);
        matKhau = (EditText) findViewById(R.id.layoutDK_edtNhapMatKhau);
        nhapLaiMatKhau = (EditText) findViewById(R.id.layoutDK_edtNhapLaiMatKhau);
        hoTen = (EditText) findViewById(R.id.layoutDK_edtHoTen);
        ngaySinh = (EditText) findViewById(R.id.layoutDK_edtNgaySinh);
        gioiTinh = (Spinner) findViewById(R.id.layoutDK_spGioiTinh);
        sDT = (EditText) findViewById(R.id.layoutDK_edtSDT);
        diaChi = (EditText) findViewById(R.id.layoutDK_edtDiaChi);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnTaoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TaiKhoan = taiKhoan.getText().toString().trim();
                String MatKhau = matKhau.getText().toString().trim();
                String NhapLaiMatKhau = nhapLaiMatKhau.getText().toString().trim();
                String HoTen = hoTen.getText().toString().trim();
                String NgaySinh = ngaySinh.getText().toString().trim();
                Spinner GioiTinh = (Spinner) gioiTinh.getOnItemClickListener();
                String SDT = sDT.getText().toString().trim();
                String DiaChi = diaChi.getText().toString().trim();
                if (TextUtils.isEmpty(TaiKhoan)){
                    Toast.makeText(getApplicationContext(), "Nhap Tai Khoan!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(MatKhau)){
                    Toast.makeText(getApplicationContext(), "Nhap Tai Khoan!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(NhapLaiMatKhau)){
                    Toast.makeText(getApplicationContext(), "Nhap Tai Khoan!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(HoTen)){
                    Toast.makeText(getApplicationContext(), "Nhap Tai Khoan!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(NgaySinh)){
                    Toast.makeText(getApplicationContext(), "Nhap Tai Khoan!", Toast.LENGTH_SHORT).show();
                    return;
                }
//                if (TextUtils.isEmpty(GioiTinh)){
//                    Toast.makeText(getApplicationContext(), "Nhap Tai Khoan!", Toast.LENGTH_SHORT).show();
//                    return;
//                }

            }
        });
    }
}