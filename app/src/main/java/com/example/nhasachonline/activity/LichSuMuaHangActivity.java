package com.example.nhasachonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.LichSuMuaHangDonHangRecyclerViewAdapter;
import com.example.nhasachonline.item.LichSuMuaHang_DonHang;
import com.example.nhasachonline.item.LichSuMuaHang_SanPham;

import java.util.ArrayList;

public class LichSuMuaHangActivity extends AppCompatActivity {
    private ArrayList<LichSuMuaHang_DonHang> lichSuMuaHang_donHangs = new ArrayList<>();
    private ArrayList<LichSuMuaHang_SanPham> lichSuMuaHang_sanPhams = new ArrayList<>();
    private ArrayList<LichSuMuaHang_SanPham> lichSuMuaHang_sanPhams1 = new ArrayList<>();
    private ArrayList<LichSuMuaHang_SanPham> lichSuMuaHang_sanPhams2 = new ArrayList<>();
    private LichSuMuaHangDonHangRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.lichsumuahang_layout);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutLSMH_rvLichSuMuahang);

        lichSuMuaHang_sanPhams.add(new LichSuMuaHang_SanPham("Truyện Conan tập 1", 20000));
        lichSuMuaHang_sanPhams.add(new LichSuMuaHang_SanPham("Truyện Conan tập 2", 40000));
        lichSuMuaHang_sanPhams.add(new LichSuMuaHang_SanPham("Truyện Conan tập 3", 30000));

        lichSuMuaHang_sanPhams1.add(new LichSuMuaHang_SanPham("Truyện Conan tập 1", 20000));
        lichSuMuaHang_sanPhams1.add(new LichSuMuaHang_SanPham("Truyện Conan tập 2", 40000));
        lichSuMuaHang_sanPhams1.add(new LichSuMuaHang_SanPham("Truyện Conan tập 3", 30000));

        lichSuMuaHang_sanPhams2.add(new LichSuMuaHang_SanPham("Truyện Conan tập 1", 20000));
        lichSuMuaHang_sanPhams2.add(new LichSuMuaHang_SanPham("Truyện Conan tập 2", 40000));
        lichSuMuaHang_sanPhams2.add(new LichSuMuaHang_SanPham("Truyện Conan tập 3", 30000));

        lichSuMuaHang_donHangs.add(new LichSuMuaHang_DonHang("27", "09", "2022", "Đã hoàn thành", 90000, lichSuMuaHang_sanPhams));
        lichSuMuaHang_donHangs.add(new LichSuMuaHang_DonHang("29", "09", "2022", "Đã hoàn thành", 90000, lichSuMuaHang_sanPhams1));
        lichSuMuaHang_donHangs.add(new LichSuMuaHang_DonHang("30", "09", "2022", "Đã hoàn thành", 90000, lichSuMuaHang_sanPhams2));

        adapter = new LichSuMuaHangDonHangRecyclerViewAdapter(this, R.layout.lichsumuahang_donhang_item, lichSuMuaHang_donHangs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter.setOnItemClickListener(new LichSuMuaHangDonHangRecyclerViewAdapter.OnItemClickListener(){
            @Override
            public void onItemClickListener(int position, View view) {
                Button itemLSMH_btnDanhGia = view.findViewById(R.id.itemLSMH_btnDanhGia);
                Button itemLSMH_btnMuaLai = view.findViewById(R.id.itemLSMH_btnMuaLai);

                itemLSMH_btnDanhGia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent mhlsmh = new Intent(LichSuMuaHangActivity.this, DanhGiaSanPhamActivity.class);
                        startActivity(mhlsmh);
                    }
                });

                itemLSMH_btnMuaLai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent mhlsmh = new Intent(LichSuMuaHangActivity.this, ThanhToanActivity.class);
                        startActivity(mhlsmh);
                    }
                });
            }
        });

        recyclerView.setAdapter(adapter);
    }
}
