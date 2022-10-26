package com.example.nhasachonline.activity;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.TheoDoiDonHangRecyclerViewAdapter;
import com.example.nhasachonline.item.TheoDoiDonHang;

import java.util.ArrayList;

public class TheoDoiDonHangActivity extends AppCompatActivity {

    private int selectedRow = -1;

    private ArrayList<TheoDoiDonHang> theoDoiDonHangs = new ArrayList<>();
    private TheoDoiDonHangRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theodoidonhang_layout);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutTDDH_rvTheoDoiDonHang);

        theoDoiDonHangs.add(new TheoDoiDonHang("DH01", "Nguyen Van Tho", "30/", "09/",  "2022", "11/", "10/", "2022", 130000, "Đang chờ xác nhận"));
        theoDoiDonHangs.add(new TheoDoiDonHang("DH02", "Tran Bao Tin", "27/", "09/",  "2022", "14/", "10/", "2022", 120000, "Đang chờ xác nhận"));
        theoDoiDonHangs.add(new TheoDoiDonHang("DH03", "Nguyen Van Tho", "29/", "09/",  "2022", "15/", "9/", "2022", 150000, "Đã xác nhận"));

        adapter = new TheoDoiDonHangRecyclerViewAdapter(this, R.layout.theodoidonhang_item, theoDoiDonHangs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(adapter);
    }
}
