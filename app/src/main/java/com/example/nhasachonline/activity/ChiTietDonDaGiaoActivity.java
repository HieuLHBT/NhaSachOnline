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
import com.example.nhasachonline.adapters.ChiTietDonDaNhanRecyclerViewAdapter;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.ChiTietDonDaGiao;
import com.example.nhasachonline.item.ChiTietDonDaNhan;
import com.example.nhasachonline.tools.SharePreferences;

import java.util.ArrayList;

public class ChiTietDonDaGiaoActivity extends AppCompatActivity {
    TextView layoutCTDDG_tvNgay, layoutCTDDG_btnTroVe;

    FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();

    private ChiTietDonDaGiaoRecyclerViewAdapter adapter;
    private ArrayList<ChiTietDonDaGiao> chiTietDonDaGiaos = new ArrayList<>();
    private String maNhanVien = "";
    private String ngay = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietdondagiao_layout);

        maNhanVien = "nv1";
        ngay = getIntent().getStringExtra("ngay");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutCTDDG_rvDanhSach);
        layoutCTDDG_tvNgay = findViewById(R.id.layoutCTDDG_tvNgay);
        layoutCTDDG_btnTroVe = findViewById(R.id.layoutCTDDG_btnTroVe);

        layoutCTDDG_tvNgay.setText(ngay);

        adapter = new ChiTietDonDaGiaoRecyclerViewAdapter(this, R.layout.chitietdondagiao_item, chiTietDonDaGiaos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);

        layoutCTDDG_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBaseNhaSachOnline.hienThiDonDaGiao(maNhanVien, ngay, chiTietDonDaGiaos, adapter);
    }
}
