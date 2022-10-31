package com.example.nhasachonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.ThanhToanRecyclerViewAdapter;
import com.example.nhasachonline.data_model.KhachHang;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.GioHang;
import com.example.nhasachonline.item.ThanhToan;
import com.example.nhasachonline.tools.SharePreferences;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ThanhToanActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();
    private String maDonHang;
    private String maKhachHang;
    private KhachHang khachHang = new KhachHang();

    private ArrayList<String> hinhThucGiao = new ArrayList<>();
    private ArrayList<String> phuongThucThanhToan = new ArrayList<>();
    private ArrayList<ThanhToan> thanhToans = new ArrayList<>();
    private ThanhToanRecyclerViewAdapter adapter;
    private Integer phiVanChuyen = 0;
    private Integer giamGia = 0;

    private TextView layoutTT_tvMaDonHang, layoutTT_tvHoTen, layoutTT_tvSoDienThoai, layoutTT_tvEmail, layoutTT_tvDiaChi, layoutTT_btnTroVe, layoutTT_tvMaGiamGia, layoutTT_tvTongTien, layoutTT_tvPhiVanChuyen, layoutTT_tvGiamGia, layoutTT_tvTongTienThanhToan;
    private ImageButton layoutTT_imgbtnDiaChi;
    private Spinner layoutTT_spnHinhThucGiao, layoutTT_spnPhuongThucThanhToan;
    private Button layoutTT_btnChonMa, layoutTT_btnDatHang;
    DecimalFormat formatter = new DecimalFormat("#,###,###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanhtoan_layout);
        maDonHang = sharePreferences.layMaDonHang(this);
        maKhachHang = sharePreferences.getKhachHang("nguoidung", this);

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

        // Gán dữ liệu
        layoutTT_tvMaDonHang.setText(maDonHang);
        fireBaseNhaSachOnline.hienThiKhachHang(maKhachHang, khachHang, this);
        hinhThucGiao.add("Giao tiêu chuẩn 15,000 VNĐ");
        hinhThucGiao.add("Giao nhanh 30,000 VNĐ");
        ArrayAdapter arrayAdapterHinhThucGiao = new ArrayAdapter(this, R.layout.thanhtoan_spinner, hinhThucGiao);
        layoutTT_spnHinhThucGiao.setAdapter(arrayAdapterHinhThucGiao);
        phuongThucThanhToan.add("Trực tiếp");
        phuongThucThanhToan.add("Online");
        ArrayAdapter arrayAdapterPhuongThucThanhToan = new ArrayAdapter(this, R.layout.thanhtoan_spinner, phuongThucThanhToan);
        layoutTT_spnPhuongThucThanhToan.setAdapter(arrayAdapterPhuongThucThanhToan);

        adapter = new ThanhToanRecyclerViewAdapter(this, R.layout.thanhtoan_item, thanhToans);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
        fireBaseNhaSachOnline.hienThiItemThanhToan(maDonHang, thanhToans, adapter, this);

        layoutTT_spnHinhThucGiao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        phiVanChuyen = 15000;
                        layoutTT_tvPhiVanChuyen.setText(formatter.format(phiVanChuyen) + " VNĐ");
                        break;
                    case 1:
                        phiVanChuyen = 30000;
                        layoutTT_tvPhiVanChuyen.setText(formatter.format(phiVanChuyen) + " VNĐ");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                phiVanChuyen = 15000;
                layoutTT_tvPhiVanChuyen.setText(formatter.format(phiVanChuyen) + " VNĐ");
            }
        });

        giamGia = 10000;
        layoutTT_tvGiamGia.setText("-" +formatter.format(giamGia) + " VNĐ");
        layoutTT_btnChonMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ThanhToanActivity.this, MaGiamGiaActivity.class);
//                startActivity(intent);

            }
        });

        layoutTT_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void hienThiKhachHang() {
        layoutTT_tvHoTen.setText(khachHang.getTenKhachHang());
        layoutTT_tvSoDienThoai.setText(khachHang.getSoDienThoai());
        layoutTT_tvEmail.setText(khachHang.getSoDienThoai());
        layoutTT_tvDiaChi.setText(khachHang.getDiaChi());
    }

    public void tongTien() {
        Integer sum = 0;
        for (ThanhToan thanhToan : thanhToans) {
            sum += thanhToan.getTongTien();
        }
        layoutTT_tvTongTien.setText(formatter.format(sum) + " VNĐ");
        Integer tongTienThanhToan = sum + phiVanChuyen - giamGia;
        layoutTT_tvTongTienThanhToan.setText(formatter.format(tongTienThanhToan) + " VNĐ");
    }

}
