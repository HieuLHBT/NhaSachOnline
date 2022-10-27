package com.example.nhasachonline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.nhasachonline.adapters.ManHinhChinhKhachHangAdapter;
import com.example.nhasachonline.data_model.Sach;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.GioHang;

import java.util.ArrayList;

public class NhaSachOnlineActivity extends AppCompatActivity {
    FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giohang_layout);
        ArrayList<GioHang> gioHangs = new ArrayList<>();
//        fireBaseNhaSachOnline.hienThiGioHang("kh1", gioHangs);
    }

}