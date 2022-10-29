package com.example.nhasachonline.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.ThanhToanRecyclerViewAdapter;
import com.example.nhasachonline.item.ThanhToan;

import java.util.ArrayList;

public class ThanhToanActivity extends AppCompatActivity {
    private ArrayList<ThanhToan> thanhToans = new ArrayList<>();
    private ThanhToanRecyclerViewAdapter adapter;

    private TextView layoutTT_tvMaDonHang, layoutTT_tvHoTen, layoutTT_tvSoDienThoai, layoutTT_tvEmail, layoutTT_tvDiaChi, layoutTT_btnTroVe, layoutTT_tvMaGiamGia, layoutTT_tvTongTien, layoutTT_tvPhiVanChuyen, layoutTT_tvGiamGia, layoutTT_tvTongTienThanhToan;
    private ImageButton layoutTT_imgbtnDiaChi;
    private Spinner layoutTT_spnHinhThucGiao, layoutTT_spnPhuongThucThanhToan;
    private Button layoutTT_btnChonMa, layoutTT_btnDatHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanhtoan_layout);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutTT_rvDanhSach);
        layoutTT_tvMaDonHang = findViewById(R.id.layoutTT_tvMaDonHang);
        layoutTT_tvHoTen = findViewById(R.id.layoutTT_tvHoTen);
        layoutTT_tvSoDienThoai = findViewById(R.id.layoutTT_tvSoDienThoai);
        layoutTT_tvEmail = findViewById(R.id.layoutTT_tvEmail);
        layoutTT_tvDiaChi = findViewById(R.id.layoutTT_tvDiaChi);
        layoutTT_imgbtnDiaChi = findViewById(R.id.layoutTT_imgbtnDiaChi);
        layoutTT_btnTroVe = findViewById(R.id.layoutTT_btnTroVe);
        layoutTT_spnHinhThucGiao = findViewById(R.id.layoutTT_spnHinhThucGiao);
        layoutTT_tvMaGiamGia = findViewById(R.id.layoutTT_tvMaGiamGia);
        layoutTT_btnChonMa = findViewById(R.id.layoutTT_btnChonMa);
        layoutTT_spnPhuongThucThanhToan = findViewById(R.id.layoutTT_spnPhuongThucThanhToan);
        layoutTT_tvTongTien = findViewById(R.id.layoutTT_tvTongTien);
        layoutTT_tvPhiVanChuyen = findViewById(R.id.layoutTT_tvPhiVanChuyen);
        layoutTT_tvGiamGia = findViewById(R.id.layoutTT_tvGiamGia);
        layoutTT_tvTongTienThanhToan = findViewById(R.id.layoutTT_tvTongTienThanhToan);
        layoutTT_btnDatHang = findViewById(R.id.layoutTT_btnDatHang);

        thanhToans.add(new ThanhToan("Nanh Trắng", 60000, 1, "sanpham1"));
        thanhToans.add(new ThanhToan("Kính Vạn Hoa", 50000, 2, "sanpham2"));

        adapter = new ThanhToanRecyclerViewAdapter(this, R.layout.thanhtoan_item, thanhToans);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        layoutTT_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView.setAdapter(adapter);

    }
}
