package com.example.nhasachonline.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.DanhGiaSanPhamRecyclerViewAdapter;
import com.example.nhasachonline.doituong.DanhGiaSanPham;

import java.util.ArrayList;

public class DanhGiaSanPhamActivity extends AppCompatActivity {
    private ArrayList<DanhGiaSanPham> danhGiaSanPhams = new ArrayList<>();
    private DanhGiaSanPhamRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.danhgiasanpham_layout);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutDGSP_rvDanhGiaSanPham);
        danhGiaSanPhams.add(new DanhGiaSanPham("Truyện Conan tập 1"));

        adapter = new DanhGiaSanPhamRecyclerViewAdapter(this, R.layout.danhgiasanpham_item, danhGiaSanPhams);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
}
