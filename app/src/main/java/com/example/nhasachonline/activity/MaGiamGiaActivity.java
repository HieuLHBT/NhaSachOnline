package com.example.nhasachonline.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.MaGiamGiaRecyclerViewAdapter;
import com.example.nhasachonline.doituong.MaGiamGia;

import java.util.ArrayList;

public class MaGiamGiaActivity extends AppCompatActivity {
    private ArrayList<MaGiamGia> maGiamGias = new ArrayList<>();
    private MaGiamGiaRecyclerViewAdapter adapter;

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
        maGiamGias.add(new MaGiamGia("Thành viên mói", "79.000"));

        adapter = new MaGiamGiaRecyclerViewAdapter(this, R.layout.magiamgia_item, maGiamGias);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
}
