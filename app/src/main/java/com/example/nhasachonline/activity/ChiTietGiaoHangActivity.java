package com.example.nhasachonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

    private TextView itemCTGH_txtMaDonHang;
    private TextView itemCTGH_txtTenNVGiaoHang;
    private TextView itemCTGH_txtNgayDuKienGiao;
    private TextView itemCTGH_txtThangDuKienGiao;
    private TextView itemCTGH_txtNamDuKienGiao;
    private TextView itemCTGH_txtNgayDat;
    private TextView itemCTGH_txtThangDat;
    private TextView itemCTGH_txtNamDat;
    private TextView layoutCTGH_txtPhuongThucThanhToan;
    private TextView layoutCTGH_txtTongTienHang;
    private TextView layoutCTGH_txtPhiVanChuyen;
    private TextView layoutCTGH_txtGiamGia;
    private TextView layoutCTGH_txtTongTienThanhToan;

    private Button layoutCTGH_btnXacNhanDonHang;
    private Button layoutCTGH_btnHuyDonHang;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.chitietgiaohang_layout);

        itemCTGH_txtMaDonHang = findViewById(R.id.itemCTGH_txtMaDonHang);
        itemCTGH_txtTenNVGiaoHang = findViewById(R.id.itemCTGH_txtTenNVGiaoHang);
        itemCTGH_txtNgayDuKienGiao = findViewById(R.id.itemCTGH_txtNgayDuKienGiao);
        itemCTGH_txtThangDuKienGiao = findViewById(R.id.itemCTGH_txtThangDuKienGiao);
        itemCTGH_txtNamDuKienGiao = findViewById(R.id.itemCTGH_txtNamDuKienGiao);
        itemCTGH_txtNgayDat = findViewById(R.id.itemCTGH_txtNgayDat);
        itemCTGH_txtThangDat = findViewById(R.id.itemCTGH_txtThangDat);
        itemCTGH_txtNamDat = findViewById(R.id.itemCTGH_txtNamDat);
        layoutCTGH_txtPhuongThucThanhToan = findViewById(R.id.layoutCTGH_txtPhuongThucThanhToan);
        layoutCTGH_txtTongTienHang = findViewById(R.id.layoutCTGH_txtTongTienHang);
        layoutCTGH_txtPhiVanChuyen = findViewById(R.id.layoutCTGH_txtPhiVanChuyen);
        layoutCTGH_txtGiamGia = findViewById(R.id.layoutCTGH_txtGiamGia);
        layoutCTGH_txtTongTienThanhToan = findViewById(R.id.layoutCTGH_txtTongTienThanhToan);
        layoutCTGH_btnXacNhanDonHang = findViewById(R.id.layoutCTGH_btnXacNhanDonHang);
        layoutCTGH_btnHuyDonHang = findViewById(R.id.layoutCTGH_btnHuyDonHang);

        itemCTGH_txtMaDonHang.setText("DH05");
        itemCTGH_txtTenNVGiaoHang.setText("Trần Bảo Tín");
        itemCTGH_txtNgayDuKienGiao.setText("15");
        itemCTGH_txtThangDuKienGiao.setText("09");
        itemCTGH_txtNamDuKienGiao.setText("2022");
        itemCTGH_txtNgayDat.setText("10");
        itemCTGH_txtThangDat.setText("09");
        itemCTGH_txtNamDat.setText("2022");
        layoutCTGH_txtPhuongThucThanhToan.setText("Online");
        layoutCTGH_txtTongTienHang.setText("250000");
        layoutCTGH_txtPhiVanChuyen.setText("30000");
        layoutCTGH_txtGiamGia.setText("-20000");
        layoutCTGH_txtTongTienThanhToan.setText("260000");

        layoutCTGH_btnXacNhanDonHang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                Intent mhlsmh = new Intent(ChiTietGiaoHangActivity.this, LichSuMuaHangActivity.class);
//                startActivity(mhlsmh);
                Toast.makeText(getApplicationContext(), "Xác nhận đơn hàng thành công!", Toast.LENGTH_SHORT).show();

            }
        });
        layoutCTGH_btnHuyDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent mhlsmh = new Intent(ChiTietGiaoHangActivity.this, LichSuMuaHangActivity.class);
//                startActivity(mhlsmh);
                Toast.makeText(getApplicationContext(), "Huỷ đơn hàng thành công!", Toast.LENGTH_SHORT).show();

            }
        });

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
