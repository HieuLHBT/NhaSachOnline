package com.example.nhasachonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.PhanHoiYKienKhachHangRecyclerViewAdapter;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.PhanHoiYKienKhachHang;
import com.example.nhasachonline.tools.SharePreferences;

import java.io.Serializable;
import java.util.ArrayList;

public class PhanHoiYKienKhachHangActivity extends AppCompatActivity {
    private TextView layoutPHYKKH_btnTroVe;
    private Spinner layoutPHYKKH_spnTrangThai;

    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();

    private PhanHoiYKienKhachHangRecyclerViewAdapter adapter;
    private ArrayList<PhanHoiYKienKhachHang> phanHoiYKienKhachHangs = new ArrayList<>();
    private ArrayList<PhanHoiYKienKhachHang> tatCa = new ArrayList<>();
    private ArrayList<PhanHoiYKienKhachHang> chuaPhanHoi = new ArrayList<>();
    private ArrayList<PhanHoiYKienKhachHang> daPhanHoi = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phanhoiykienkhachhang_layout);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutPHYKKH_rvDanhSach);
        layoutPHYKKH_btnTroVe = findViewById(R.id.layoutPHYKKH_btnTroVe);
        layoutPHYKKH_spnTrangThai = findViewById(R.id.layoutPHYKKH_spnTrangThai);

        ArrayList<String> trangThai = new ArrayList<>();
        trangThai.add("Tất cả");
        trangThai.add("Chưa phản hồi");
        trangThai.add("Đã phản hồi");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.phanhoiykienkhachhang_spinner, trangThai);
        layoutPHYKKH_spnTrangThai.setAdapter(arrayAdapter);

        adapter = new PhanHoiYKienKhachHangRecyclerViewAdapter(this, R.layout.phanhoiykienkhachhang_item, phanHoiYKienKhachHangs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        adapter.setOnItemClickListener(new PhanHoiYKienKhachHangRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                TextView itemPHYKKH_tvMucLuc = view.findViewById(R.id.itemPHYKKH_tvMucLuc);
                itemPHYKKH_tvMucLuc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(v.getContext(), itemPHYKKH_tvMucLuc);
                        popup.inflate(R.menu.phanhoiykienkhachhang_menu);
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                Intent intent = new Intent(PhanHoiYKienKhachHangActivity.this, TraLoiBinhLuanActivity.class);
                                switch (item.getItemId()) {
                                    case R.id.menuPHYKKH_itemTraLoiBinhLuan:
                                        intent.putExtra("traLoi", phanHoiYKienKhachHangs.get(position));
                                        PhanHoiYKienKhachHangActivity.this.startActivity(intent);
                                        break;
                                    case R.id.menuPHYKKH_itemSuaTraLoiBinhLuan:
                                        intent.putExtra("thayDoi", phanHoiYKienKhachHangs.get(position));
                                        PhanHoiYKienKhachHangActivity.this.startActivity(intent);
                                        break;
                                }
                                return false;
                            }
                        });
                        Menu popupMenu = popup.getMenu();
                        if (phanHoiYKienKhachHangs.get(position).getMaNhanVien().equalsIgnoreCase("")) {
                            popupMenu.findItem(R.id.menuPHYKKH_itemTraLoiBinhLuan).setVisible(true);
                            popupMenu.findItem(R.id.menuPHYKKH_itemSuaTraLoiBinhLuan).setVisible(false);
                        } else {
                            popupMenu.findItem(R.id.menuPHYKKH_itemTraLoiBinhLuan).setVisible(false);
                            popupMenu.findItem(R.id.menuPHYKKH_itemSuaTraLoiBinhLuan).setVisible(true);
                        }
                        popup.show();
                    }
                });
            }
        });

        layoutPHYKKH_spnTrangThai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        phanHoiYKienKhachHangs.clear();
                        phanHoiYKienKhachHangs.addAll(tatCa);
                        adapter.notifyDataSetChanged();
                        break;
                    case 1:
                        phanHoiYKienKhachHangs.clear();
                        phanHoiYKienKhachHangs.addAll(chuaPhanHoi);
                        adapter.notifyDataSetChanged();
                        break;
                    case 2:
                        phanHoiYKienKhachHangs.clear();
                        phanHoiYKienKhachHangs.addAll(daPhanHoi);
                        adapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        layoutPHYKKH_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBaseNhaSachOnline.hienThiPhanHoiYKien(phanHoiYKienKhachHangs, adapter, this);
    }

    public void duLieu() {
        tatCa.clear();
        tatCa.addAll(phanHoiYKienKhachHangs);
        chuaPhanHoi.clear();
        daPhanHoi.clear();
        for (PhanHoiYKienKhachHang phanHoiYKienKhachHang : phanHoiYKienKhachHangs) {
            if (phanHoiYKienKhachHang.getMaNhanVien().equalsIgnoreCase("")) {
                chuaPhanHoi.add(phanHoiYKienKhachHang);
            } else {
                daPhanHoi.add(phanHoiYKienKhachHang);
            }
        }
    }
}
