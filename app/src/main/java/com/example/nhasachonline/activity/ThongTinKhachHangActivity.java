package com.example.nhasachonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.data_model.KhachHang;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.tools.SharePreferences;

public class ThongTinKhachHangActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();
    private String maKhachHang = "kh1";
    private KhachHang khachHang = new KhachHang();

    TextView layoutTTKH_tvHoTen, layoutTTKH_tvEmail, layoutTTKH_tvSoDienThoai, layoutTTKH_tvTenNganHang,
            layoutTTKH_tvSTKNganHang,layoutTTKH_tvDiaChi;
    Button layoutTTKH_btnDoiMatKhau, layoutTTKH_btnThayDoiThongTin;

    public ThongTinKhachHangActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongtincanhan_layout);
        sharePreferences.setKhachHang1(this,maKhachHang);
        maKhachHang = sharePreferences.getKhachHang1(this);

        // Ánh xạ
        layoutTTKH_tvHoTen = findViewById(R.id.layoutTTKH_tvHoTen);
        layoutTTKH_tvEmail = findViewById(R.id.layoutTTKH_tvEmail);
        layoutTTKH_tvSoDienThoai = findViewById(R.id.layoutTTKH_tvSoDienThoai);
        layoutTTKH_tvTenNganHang = findViewById(R.id.layoutTTKH_tvTenNganHang);
        layoutTTKH_tvSTKNganHang = findViewById(R.id.layoutTTKH_tvSTKNganHang);
        layoutTTKH_tvDiaChi = findViewById(R.id.layoutTTKH_tvDiaChi);
        layoutTTKH_btnDoiMatKhau = findViewById(R.id.layoutTTKH_btnDoiMatKhau);
        layoutTTKH_btnThayDoiThongTin = findViewById(R.id.layoutTTKH_btnThayDoiThongTin);

        //Hiển thị dữ liệu

        layoutTTKH_btnThayDoiThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongTinKhachHangActivity.this, ThayDoiThongTinKhachHangActivity.class);
                intent.putExtra(maKhachHang ,khachHang.getMaKhachHang());
                ThongTinKhachHangActivity.this.startActivity(intent);
            }
        });
        fireBase.hienThiThongTinKhachHang(maKhachHang, khachHang,this);

    }

    public void thongTinCaNhan(){
        layoutTTKH_tvHoTen.setText(khachHang.getTenKhachHang());
        layoutTTKH_tvEmail.setText(khachHang.getEmail());
        layoutTTKH_tvSoDienThoai.setText(khachHang.getSoDienThoai());
        layoutTTKH_tvTenNganHang.setText(khachHang.getNganHang());
        layoutTTKH_tvSTKNganHang.setText(khachHang.getSoTaiKhoan());
        layoutTTKH_tvDiaChi.setText(khachHang.getDiaChi());
    }
}
