package com.example.nhasachonline.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.ChiTietDonDaNhanRecyclerViewAdapter;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.ChiTietDonDaNhan;
import com.example.nhasachonline.tools.SharePreferences;

import java.util.ArrayList;

public class ChiTietDonDaNhanActivity extends AppCompatActivity {
    TextView layoutCTDDN_tvNgay, layoutCTDDN_btnTroVe;

    FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();

    private ChiTietDonDaNhanRecyclerViewAdapter adapter;
    private ArrayList<ChiTietDonDaNhan> chiTietDonDaNhans = new ArrayList<>();
    private String maNhanVien = "";
    private String ngay = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietdondanhan_layout);

        maNhanVien = "nv1";
        ngay = getIntent().getStringExtra("ngay");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutCTDDN_rvDanhSach);
        layoutCTDDN_tvNgay = findViewById(R.id.layoutCTDDN_tvNgay);
        layoutCTDDN_btnTroVe = findViewById(R.id.layoutCTDDN_btnTroVe);

        layoutCTDDN_tvNgay.setText(ngay);

        adapter = new ChiTietDonDaNhanRecyclerViewAdapter(this, R.layout.chitietdondanhan_item, chiTietDonDaNhans);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);

        layoutCTDDN_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBaseNhaSachOnline.hienThiDonDaNhan(maNhanVien, ngay, chiTietDonDaNhans, adapter);
    }
}
