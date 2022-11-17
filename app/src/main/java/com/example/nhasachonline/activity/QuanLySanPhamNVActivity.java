package com.example.nhasachonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.QuanLySanPhamNVRecyclerViewAdapter;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.ItemQuanLySanPhamNV;
import com.example.nhasachonline.item.ItemSanPham;
import com.example.nhasachonline.tools.SharePreferences;

import java.util.ArrayList;

public class QuanLySanPhamNVActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    private String maSanPham;
    private String maNhanVien;
    private QuanLySanPhamNVRecyclerViewAdapter adapter;
    private ArrayList<ItemQuanLySanPhamNV> itemQuanLySanPhamNVS = new ArrayList<>();

    private Spinner layoutQLSP_NV_spnSanPham;
    private SearchView timKiemSP;
    private Button layoutQLSP_NV_btnTroVe;

    ArrayList<String> dataSanPham = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlysanpham_nv_layout);

        // Search
        timKiemSP = findViewById(R.id.layoutQLSP_NV_svTimKiem);
        timKiem();

        layoutQLSP_NV_spnSanPham = findViewById(R.id.layoutQLSP_NV_spnSanPham);
        dataSanPham.add("Tất cả");
        dataSanPham.add("Sách");
        dataSanPham.add("Văn phòng phẩm");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_item, dataSanPham);
        layoutQLSP_NV_spnSanPham.setAdapter(arrayAdapter);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutQLSP_NV_rvQuanLySanPhamNhanVien);
        adapter = new QuanLySanPhamNVRecyclerViewAdapter(this, R.layout.quanlysanpham_nv_item, itemQuanLySanPhamNVS);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        layoutQLSP_NV_btnTroVe = findViewById(R.id.layoutQLSP_NV_btnTroVe);
        layoutQLSP_NV_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter.setOnItemClickListener(new QuanLySanPhamNVRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                Button itemQLSP_NV_btnKiemTraDonHang = view.findViewById(R.id.itemQLSP_NV_btnKiemTraDonHang);
                itemQLSP_NV_btnKiemTraDonHang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(QuanLySanPhamNVActivity.this, QuanLyDonHangNVActivity.class);
                        intent.putExtra("maNhanVien", maNhanVien);
                        QuanLySanPhamNVActivity.this.startActivity(intent);
                    }
                });
            }
        });

        layoutQLSP_NV_spnSanPham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        fireBase.hienThiQuanLySanPham(itemQuanLySanPhamNVS, adapter, QuanLySanPhamNVActivity.this);
                        adapter.notifyDataSetChanged();
                        break;
                    case 1:
                        fireBase.hienThiSach(itemQuanLySanPhamNVS, adapter, QuanLySanPhamNVActivity.this);
                        adapter.notifyDataSetChanged();
                        break;
                    case 2:
                        fireBase.hienThiVanPhongPham(itemQuanLySanPhamNVS, adapter, QuanLySanPhamNVActivity.this);
                        adapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void filterList(String newText) {
        ArrayList<ItemQuanLySanPhamNV> fiIteredList = new ArrayList<>();
        for (ItemQuanLySanPhamNV itemQuanLySanPhamNV : itemQuanLySanPhamNVS) {
            if (itemQuanLySanPhamNV.getTenSanPham().toLowerCase().contains(newText.toLowerCase())) {
                fiIteredList.add(itemQuanLySanPhamNV);
            }
        }
        if (fiIteredList.isEmpty()) {
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setFilteredList(fiIteredList);
        }
    }

    public void timKiem() {
        timKiemSP.clearFocus();
        timKiemSP.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBase.hienThiQuanLySanPham(itemQuanLySanPhamNVS, adapter, this);
    }
}
