package com.example.nhasachonline.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;

public class QuenMatKhauCapLaiMatKhauActivity extends AppCompatActivity {
    private TextView layoutQMKCLMK_btnTroVe;
    private Button layoutQMKCLMK_btnThayDoiMatKhau;
    private EditText layoutQMKCLMK_edtNhapMatKhau, layoutQMKCLMK_edtNhapLaiMatKhau;

    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();

    private String taiKhoan;
    private String soDienThoai;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quenmatkhau_caplaimatkhau_layout);

        taiKhoan = getIntent().getStringExtra("taiKhoan");
        soDienThoai = getIntent().getStringExtra("soDienThoai");

        layoutQMKCLMK_btnTroVe = findViewById(R.id.layoutQMKCLMK_btnTroVe);
        layoutQMKCLMK_btnThayDoiMatKhau = findViewById(R.id.layoutQMKCLMK_btnThayDoiMatKhau);
        layoutQMKCLMK_edtNhapMatKhau = findViewById(R.id.layoutQMKCLMK_edtNhapMatKhau);
        layoutQMKCLMK_edtNhapLaiMatKhau = findViewById(R.id.layoutQMKCLMK_edtNhapLaiMatKhau);

        layoutQMKCLMK_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layoutQMKCLMK_btnThayDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String matKhau = layoutQMKCLMK_edtNhapMatKhau.getText().toString();
                String matKhauNhapLai = layoutQMKCLMK_edtNhapLaiMatKhau.getText().toString();
                if (!matKhauNhapLai.equals(matKhau)) {
                    layoutQMKCLMK_edtNhapLaiMatKhau.setError("Mật khẩu nhập lại không giống mật khẩu trên");
                    return;
                }
                fireBaseNhaSachOnline.thayDoiMatKhau(taiKhoan, matKhau, soDienThoai, QuenMatKhauCapLaiMatKhauActivity.this);
            }
        });
    }
}
