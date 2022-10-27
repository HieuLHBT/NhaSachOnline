package com.example.nhasachonline.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.ChiTietGiaoHangRecyclerViewAdapter;
import com.example.nhasachonline.item.ChiTietGiaoHang;

import java.util.ArrayList;

public class ChiTietGiaoHangActivity extends AppCompatActivity {
    private ArrayList<ChiTietGiaoHang> chiTietGiaoHangs = new ArrayList<>();
    private ChiTietGiaoHangRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.chitietgiaohang_layout);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutCTGH_rvChiTietGiaoHang);
        chiTietGiaoHangs.add(new ChiTietGiaoHang("Truyện Conan tập 1", "20.000", 3));
        chiTietGiaoHangs.add(new ChiTietGiaoHang("Truyện Conan tập 2", "10.000", 2));
        chiTietGiaoHangs.add(new ChiTietGiaoHang("Truyện Conan tập 3", "40.000", 3));

        adapter = new ChiTietGiaoHangRecyclerViewAdapter(this, R.layout.chitietgiaohang_item, chiTietGiaoHangs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(adapter);
    }
}
