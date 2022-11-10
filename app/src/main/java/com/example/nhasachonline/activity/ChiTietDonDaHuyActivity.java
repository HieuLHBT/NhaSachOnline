package com.example.nhasachonline.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.ChiTietDonDaGiaoRecyclerViewAdapter;
import com.example.nhasachonline.adapters.ChiTietDonDaHuyRecyclerViewAdapter;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.ChiTietDonDaGiao;
import com.example.nhasachonline.item.ChiTietDonDaHuy;
import com.example.nhasachonline.tools.SharePreferences;

import java.util.ArrayList;

public class ChiTietDonDaHuyActivity extends AppCompatActivity {
    TextView layoutCTDDH_tvNgay, layoutCTDDH_btnTroVe;

    FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();

    private ChiTietDonDaHuyRecyclerViewAdapter adapter;
    private ArrayList<ChiTietDonDaHuy> chiTietDonDaHuys = new ArrayList<>();
    private String maNhanVien = "";
    private String ngay = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietdondahuy_layout);

        maNhanVien = "nv1";
        ngay = getIntent().getStringExtra("ngay");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutCTDDH_rvDanhSach);
        layoutCTDDH_tvNgay = findViewById(R.id.layoutCTDDH_tvNgay);
        layoutCTDDH_btnTroVe = findViewById(R.id.layoutCTDDH_btnTroVe);

        layoutCTDDH_tvNgay.setText(ngay);

        adapter = new ChiTietDonDaHuyRecyclerViewAdapter(this, R.layout.chitietdondahuy_item, chiTietDonDaHuys);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);

        layoutCTDDH_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBaseNhaSachOnline.hienThiDonDaHuy(maNhanVien, ngay, chiTietDonDaHuys, adapter);
    }
}
