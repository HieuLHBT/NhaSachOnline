package com.example.nhasachonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.ManHinhChinhNhanVienRecyclerViewAdapter;
import com.example.nhasachonline.adapters.QuanLyDonHangNVRecyclerViewAdapter;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.ItemQuanLyDonHangNV;
import com.example.nhasachonline.tools.SharePreferences;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class QuanLyDonHangNVActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    private ArrayList<ItemQuanLyDonHangNV> itemQuanLyDonHangNVS = new ArrayList<>();
    private QuanLyDonHangNVRecyclerViewAdapter adapter;

    private String maDonHang;
    private String maNhanVien;

    private Spinner layoutQLDH_NV_spnTinhTrang;
    private Button layoutQLDH_NV_btnTroVe;

    ArrayList<String> dataTinhTrang = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlydonhang_nv_layout);

        maNhanVien = getIntent().getStringExtra("maNhanVien");
        maDonHang = getIntent().getStringExtra("maDonHang");

        // Spinner
        layoutQLDH_NV_spnTinhTrang = findViewById(R.id.layoutQLDH_NV_spnTinhTrang);
        dataTinhTrang.add("Tất cả");
        dataTinhTrang.add("Đã xác nhận");
        dataTinhTrang.add("Chưa xác nhận");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_item, dataTinhTrang);
        layoutQLDH_NV_spnTinhTrang.setAdapter(arrayAdapter);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutQLDH_NV_rvQuanLyDonHangNhanVien);
        adapter = new QuanLyDonHangNVRecyclerViewAdapter(this, R.layout.quanlydonhang_nv_item, itemQuanLyDonHangNVS);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        layoutQLDH_NV_btnTroVe = findViewById(R.id.layoutQLDH_NV_btnTroVe);
        layoutQLDH_NV_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fireBase.hienThiQuanLyDonHang(maNhanVien, itemQuanLyDonHangNVS, adapter, this);

        adapter.setOnItemClickListener(new QuanLyDonHangNVRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                Button itemQLDH_NV_btnChiTietDon = view.findViewById(R.id.itemQLDH_NV_btnChiTietDon);
                itemQLDH_NV_btnChiTietDon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(QuanLyDonHangNVActivity.this, ChiTietDonHangNVActivity.class);
                        intent.putExtra("maDonHang", itemQuanLyDonHangNVS.get(position).getMaDonHang());
                        QuanLyDonHangNVActivity.this.startActivity(intent);
                    }
                });

                Button itemQLDH_NV_btnThongBaoHuy = view.findViewById(R.id.itemQLDH_NV_btnThongBaoHuy);
                itemQLDH_NV_btnThongBaoHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Intent intent = new Intent(QuanLyDonHangNVActivity.this, ThongBaoHuyActivity.class);
                        //intent.putExtra("maNhanVien", maNhanVien);
                        //QuanLyDonHangNVActivity.this.startActivity(intent);
                    }
                });

                Button itemQLDH_NV_btnGiaoHang = view.findViewById(R.id.itemQLDH_NV_btnGiaoHang);
                itemQLDH_NV_btnGiaoHang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(QuanLyDonHangNVActivity.this, ThongTinGiaoHangNVActivity.class);
                        intent.putExtra("maDonHang", itemQuanLyDonHangNVS.get(position).getMaDonHang());
                        QuanLyDonHangNVActivity.this.startActivity(intent);
                    }
                });

                Button itemQLDH_NV_btnHuyDon = view.findViewById(R.id.itemQLDH_NV_btnHuyDon);
                itemQLDH_NV_btnHuyDon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Intent intent = new Intent(QuanLyDonHangNVActivity.this, ChiTietDonHangNVActivity.class);
                        //intent.putExtra("maNhanVien", maNhanVien);
                        //QuanLyDonHangNVActivity.this.startActivity(intent);
                    }
                });
            }
        });
    }
}
