package com.example.nhasachonline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.nhasachonline.adapters.ManHinhChinhKhachHangAdapter;

import java.util.ArrayList;

public class NhaSachOnlineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giohang_layout);
    }

}