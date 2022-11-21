package com.example.nhasachonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.HeThong;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class QuenMatKhauXacNhanOTPActivity extends AppCompatActivity {
    private EditText layoutQMKXNOTP_edtOTP;
    private Button layoutQMKXNOTP_btnXacNhanOTP;
    private TextView layoutQMKXNOTP_btnTroVe, layoutQMKXNOTP_tvGuiLaiOTP;

    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private String id;
    private String taiKhoan;
    private String soDienThoai;

    private HeThong heThong = new HeThong();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quenmatkhau_xacnhanotp_layout);

        id = getIntent().getStringExtra("id");
        heThong.setVertificationID(getIntent().getStringExtra("id"));
        taiKhoan = getIntent().getStringExtra("taiKhoan");
        soDienThoai = getIntent().getStringExtra("soDienThoai");

        layoutQMKXNOTP_btnTroVe  = findViewById(R.id.layoutQMKXNOTP_btnTroVe);
        layoutQMKXNOTP_edtOTP   = findViewById(R.id.layoutQMKXNOTP_edtOTP);
        layoutQMKXNOTP_tvGuiLaiOTP  = findViewById(R.id.layoutQMKXNOTP_tvGuiLaiOTP);
        layoutQMKXNOTP_btnXacNhanOTP  = findViewById(R.id.layoutQMKXNOTP_btnXacNhanOTP);

        layoutQMKXNOTP_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layoutQMKXNOTP_btnXacNhanOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maOTP = layoutQMKXNOTP_edtOTP.getText().toString().trim();
                fireBaseNhaSachOnline.xacNhanOTP(id, maOTP, taiKhoan, QuenMatKhauXacNhanOTPActivity.this);
            }
        });

        layoutQMKXNOTP_tvGuiLaiOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fireBaseNhaSachOnline.guiLaiMaOTP(heThong, taiKhoan, soDienThoai, QuenMatKhauXacNhanOTPActivity.this);
            }
        });
    }
}
