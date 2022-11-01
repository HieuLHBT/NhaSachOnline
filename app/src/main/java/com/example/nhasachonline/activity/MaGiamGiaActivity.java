package com.example.nhasachonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.MaGiamGiaRecyclerViewAdapter;
import com.example.nhasachonline.data_model.GiamGia;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;

import java.util.ArrayList;

public class MaGiamGiaActivity extends AppCompatActivity {
    private ArrayList<GiamGia> giamGias = new ArrayList<>();
    private MaGiamGiaRecyclerViewAdapter adapter;
    private TextView layoutMGG_tvTroVe;
    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.magiamgia_layout);
        layoutMGG_tvTroVe = findViewById(R.id.layoutMGG_tvTroVe);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutMGG_rvMaGiamGia);

        adapter = new MaGiamGiaRecyclerViewAdapter(this, R.layout.magiamgia_item, giamGias);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        //fireBaseNhaSachOnline.hienThiMaGiamGia(giamGias, adapter);

        adapter.setOnItemClickListener(new MaGiamGiaRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                //fireBaseNhaSachOnline.chonGiamGia(giamGias.get(position).getMaGiamGia());
                Intent intent = new Intent(MaGiamGiaActivity.this, ThanhToanActivity.class);
                finish();
            }
        });

        layoutMGG_tvTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
