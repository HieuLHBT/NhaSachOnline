package com.example.nhasachonline.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.TheoDoiDonHangRecyclerViewAdapter;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.TheoDoiDonHang;
import com.example.nhasachonline.tools.SharePreferences;

import java.util.ArrayList;

public class TheoDoiDonHangActivity extends AppCompatActivity {
    private Spinner layoutTDDH_spnTrangThai;

    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();

    private String maKhachHang;

    private ArrayList<TheoDoiDonHang> theoDoiDonHangs = new ArrayList<>();
    private ArrayList<TheoDoiDonHang> donHangCanXacNhan = new ArrayList<>();
    private ArrayList<TheoDoiDonHang> donHangHoanTien = new ArrayList<>();
    private ArrayList<TheoDoiDonHang> tatCa = new ArrayList<>();
    private TheoDoiDonHangRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theodoidonhang_layout);

        maKhachHang = "kh1";

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutTDDH_rvTheoDoiDonHang);
        layoutTDDH_spnTrangThai = findViewById(R.id.layoutTDDH_spnTrangThai);

        ArrayList<String> trangThai = new ArrayList<>();
        trangThai.add("Tất cả đơn hàng");
        trangThai.add("Đơn hàng cần xác nhận");
        trangThai.add("Đơn hàng hoàn tiền");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.theodoidonhang_spinner, trangThai);
        layoutTDDH_spnTrangThai.setAdapter(arrayAdapter);

        adapter = new TheoDoiDonHangRecyclerViewAdapter(this, R.layout.theodoidonhang_item, theoDoiDonHangs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new TheoDoiDonHangRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                Button itemTDDH_btnChiTiet = view.findViewById(R.id.itemTDDH_btnChiTiet);
                itemTDDH_btnChiTiet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(TheoDoiDonHangActivity.this, ChiTietTheoDoiDonHangActivity.class);
                        intent.putExtra("maDonHang", theoDoiDonHangs.get(position).getMaDonHang());
                        startActivity(intent);
                    }
                });
            }
        });

        layoutTDDH_spnTrangThai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        theoDoiDonHangs.clear();
                        theoDoiDonHangs.addAll(tatCa);
                        adapter.notifyDataSetChanged();
                        break;
                    case 1:
                        theoDoiDonHangs.clear();
                        theoDoiDonHangs.addAll(donHangCanXacNhan);
                        adapter.notifyDataSetChanged();
                        break;
                    case 2:
                        theoDoiDonHangs.clear();
                        theoDoiDonHangs.addAll(donHangHoanTien);
                        adapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBaseNhaSachOnline.hienThiTheoDoiDonHang(maKhachHang, theoDoiDonHangs, adapter, this);
    }

    public void donHangCanXacNhan() {
        tatCa.clear();
        donHangCanXacNhan.clear();
        donHangHoanTien.clear();
        for (TheoDoiDonHang theoDoiDonHang : theoDoiDonHangs) {
            if (theoDoiDonHang.getTrangThaiChuyenTienKH().equalsIgnoreCase("Đang xử lý") && theoDoiDonHang.getHinhThucThanhToan().equalsIgnoreCase("Online")) {
                donHangCanXacNhan.add(theoDoiDonHang);
            } else if (!theoDoiDonHang.getTrangThaiNhanTienKH().equalsIgnoreCase("") && !theoDoiDonHang.getTrangThaiTraTienQL().equalsIgnoreCase("") && !theoDoiDonHang.getTrangThai().equalsIgnoreCase("Hủy")) {
                donHangHoanTien.add(theoDoiDonHang);
            } else {
                tatCa.add(theoDoiDonHang);
            }
        }
        if (donHangCanXacNhan.size() != 0) {
            layoutTDDH_spnTrangThai.setSelection(1);
            theoDoiDonHangs.clear();
            theoDoiDonHangs.addAll(donHangCanXacNhan);
            adapter.notifyDataSetChanged();
        } else if (donHangHoanTien.size() != 0) {
            layoutTDDH_spnTrangThai.setSelection(2);
            theoDoiDonHangs.clear();
            theoDoiDonHangs.addAll(donHangHoanTien);
            adapter.notifyDataSetChanged();
        } else {
            layoutTDDH_spnTrangThai.setSelection(0);
            theoDoiDonHangs.clear();
            theoDoiDonHangs.addAll(tatCa);
            adapter.notifyDataSetChanged();
        }
    }
}
