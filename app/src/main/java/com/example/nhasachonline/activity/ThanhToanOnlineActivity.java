package com.example.nhasachonline.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.ThanhToanRecyclerViewAdapter;
import com.example.nhasachonline.data_model.KhachHang;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.ThanhToan;
import com.example.nhasachonline.tools.SharePreferences;

import java.util.ArrayList;

public class ThanhToanOnlineActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();
    private String maDonHang;
    private String maKhachHang;
    private KhachHang khachHang = new KhachHang();
    Button layoutTTTT_btnTroVe;
    TextView layoutTTTT_tvTongTienThanhToan;
    private ArrayList<ThanhToan> thanhToans = new ArrayList<>();
    private ThanhToanRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanhtoanonline_layout);
        maDonHang = sharePreferences.layMaDonHang(this);
        maKhachHang = sharePreferences.getKhachHang(this);

        layoutTTTT_btnTroVe = findViewById(R.id.layoutThanhToanOnline_btnBack);
        layoutTTTT_tvTongTienThanhToan = findViewById(R.id.layoutTTTT_tvTienThanhToan);

        layoutTTTT_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void hienThiTongTien(){

    }
}
