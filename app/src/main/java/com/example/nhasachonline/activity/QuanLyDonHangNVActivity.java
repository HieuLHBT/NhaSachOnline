package com.example.nhasachonline.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.ManHinhChinhNhanVienRecyclerViewAdapter;
import com.example.nhasachonline.adapters.QuanLyDonHangNVRecyclerViewAdapter;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.ItemQuanLyDonHangNV;
import com.example.nhasachonline.tools.SharePreferences;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class QuanLyDonHangNVActivity extends AppCompatActivity {
    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    private ArrayList<ItemQuanLyDonHangNV> itemQuanLyDonHangNVS = new ArrayList<>();
    private QuanLyDonHangNVRecyclerViewAdapter adapter;
    private String maDonHang = "dh2";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlydonhang_nv_layout);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutQLDH_NV_rvQuanLyDonHangNhanVien);

        adapter = new QuanLyDonHangNVRecyclerViewAdapter(this, R.layout.quanlydonhang_nv_item, itemQuanLyDonHangNVS);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
