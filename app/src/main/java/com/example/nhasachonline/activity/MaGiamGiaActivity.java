package com.example.nhasachonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.MaGiamGiaRecyclerViewAdapter;
import com.example.nhasachonline.adapters.TheoDoiDonHangRecyclerViewAdapter;
import com.example.nhasachonline.item.ChiTietGiaoHang;
import com.example.nhasachonline.item.MaGiamGia;

import java.util.ArrayList;

public class MaGiamGiaActivity extends AppCompatActivity {
    private ArrayList<MaGiamGia> maGiamGias = new ArrayList<>();
    private MaGiamGiaRecyclerViewAdapter adapter;

    private Button layoutMGG_btnTroVe;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.magiamgia_layout);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutMGG_rvMaGiamGia);
        maGiamGias.add(new MaGiamGia("Giá trị đơn hàng trên 500k", "99.000"));
        maGiamGias.add(new MaGiamGia("Giá trị đơn hàng trên 200k", "79.000"));
        maGiamGias.add(new MaGiamGia("Giá trị đơn hàng trên 100k", "59.000"));
        maGiamGias.add(new MaGiamGia("Giá trị đơn hàng trên 400k", "89.000"));
        maGiamGias.add(new MaGiamGia("Giá trị đơn hàng trên 600k", "129.000"));
        maGiamGias.add(new MaGiamGia("Giá trị đơn hàng trên 800k", "189.000"));
        maGiamGias.add(new MaGiamGia("Giá trị đơn hàng trên 1000k", "259.000"));
        maGiamGias.add(new MaGiamGia("Thành viên mơi", "79.000"));

        layoutMGG_btnTroVe = findViewById(R.id.layoutMGG_btnTroVe);

        adapter = new MaGiamGiaRecyclerViewAdapter(this, R.layout.magiamgia_item, maGiamGias);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        layoutMGG_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent mhmgg = new Intent(MaGiamGiaActivity.this, ThanhToanActivity.class);
                //startActivity(mhmgg);
                finish();
            }
        });

    }
}
