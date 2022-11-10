package com.example.nhasachonline.activity;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.ManHinhChinhNhanVienRecyclerViewAdapter;
import com.example.nhasachonline.data_model.NhanVien;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.ChiTietGiaoHang;
import com.example.nhasachonline.item.ItemManHinhChinhNhanVien;
import com.example.nhasachonline.tools.SharePreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ManHinhChinhNhanVienActivity extends AppCompatActivity {
    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();
    private String maNhanVien = "nv1";
    private String ngay;
    private String thoiGian;

    private ManHinhChinhNhanVienRecyclerViewAdapter adapter;
    private ArrayList<ItemManHinhChinhNhanVien> itemManHinhChinhNhanViens = new ArrayList<>();
    private NhanVien nhanVien = new NhanVien();

    private TextView layoutMHCNV_txtNgay;
    private Button layoutMHCNV_btnCheckin;
    private Button layoutMHCNV_btnCheckout;
    private Button layoutMHCNV_btnQuanLyDonHang;
    private Button layoutMHCNV_btnQuanLySanPham;
    private Button layoutMHCNV_btnLichLamViec;
    private Button layoutMHCNV_btnBangCong;
    private Button layoutMHCNV_btnPhanHoiKhachHang;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhchinh_nhanvien_layout);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutMHCNV_NV_rvManHinhChinhNhanVien);

        layoutMHCNV_txtNgay = findViewById(R.id.layoutMHCNV_txtNgay);
        layoutMHCNV_btnCheckin = findViewById(R.id.layoutMHCNV_btnCheckin);
        layoutMHCNV_btnCheckout = findViewById(R.id.layoutMHCNV_btnCheckout);
        layoutMHCNV_btnQuanLyDonHang = findViewById(R.id.layoutMHCNV_btnQuanLyDonHang);
        layoutMHCNV_btnQuanLySanPham = findViewById(R.id.layoutMHCNV_btnQuanLySanPham);
        layoutMHCNV_btnLichLamViec = findViewById(R.id.layoutMHCNV_btnLichLamViec);
        layoutMHCNV_btnBangCong = findViewById(R.id.layoutMHCNV_btnBangCong);
        layoutMHCNV_btnPhanHoiKhachHang = findViewById(R.id.layoutMHCNV_btnPhanHoiKhachHang);

        adapter = new ManHinhChinhNhanVienRecyclerViewAdapter(this, R.layout.manhinhchinh_nhanvien_item, itemManHinhChinhNhanViens);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        SimpleDateFormat sdfDay = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat sdfTime = new SimpleDateFormat("hh.mm");
        ngay = sdfDay.format(new Date());
        thoiGian = sdfTime.format(new Date());
        layoutMHCNV_txtNgay.setText(ngay);

        fireBase.hienThiManHinhChinhNhanVien(maNhanVien, itemManHinhChinhNhanViens, adapter, this);
        layoutMHCNV_btnCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ItemManHinhChinhNhanVien> mhcnv = new ArrayList<>();
                ThongBaoXacNhanCheckin(mhcnv);
            }
        });
        layoutMHCNV_btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ItemManHinhChinhNhanVien> mhcnv = new ArrayList<>();
                ThongBaoXacNhanCheckout(mhcnv);
            }
        });
        layoutMHCNV_btnQuanLyDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinhChinhNhanVienActivity.this, QuanLyDonHangNVActivity.class);
                intent.putExtra("maNhanVien", maNhanVien);
                ManHinhChinhNhanVienActivity.this.startActivity(intent);
            }
        });
        layoutMHCNV_btnQuanLySanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(ManHinhChinhNhanVienActivity.this, QuanLySanPhamNVActivity.class);
                //intent.putExtra("maNhanVien", maNhanVien);
                //ManHinhChinhNhanVienActivity.this.startActivity(intent);
            }
        });
        layoutMHCNV_btnLichLamViec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinhChinhNhanVienActivity.this, LichLamViecActivity.class);
                intent.putExtra("maNhanVien", maNhanVien);
                ManHinhChinhNhanVienActivity.this.startActivity(intent);
            }
        });
        layoutMHCNV_btnBangCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinhChinhNhanVienActivity.this, BangChamCongActivity.class);
                intent.putExtra("maNhanVien", maNhanVien);
                ManHinhChinhNhanVienActivity.this.startActivity(intent);
            }
        });
        layoutMHCNV_btnPhanHoiKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(ManHinhChinhNhanVienActivity.this, PhanHoiBinhLuanActivity.class);
                //intent.putExtra("maNhanVien", maNhanVien);
                //ManHinhChinhNhanVienActivity.this.startActivity(intent);
            }
        });
    }

    public void ThongBaoXacNhanCheckin(ArrayList<ItemManHinhChinhNhanVien> manHinhChinhNhanViens) {
        AlertDialog.Builder b = new AlertDialog.Builder(ManHinhChinhNhanVienActivity.this);
        b.setTitle("Xác nhận checkin");
        b.setMessage("Ngày checkin: " + ngay + " Thời gian checkin: " + thoiGian);
        b.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(ManHinhChinhNhanVienActivity.this, ManHinhChinhNhanVienActivity.class);
                startActivity(intent);
            }
        });
        b.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog al = b.create();
        al.show();
    }
    public void ThongBaoXacNhanCheckout(ArrayList<ItemManHinhChinhNhanVien> manHinhChinhNhanViens) {
        AlertDialog.Builder b = new AlertDialog.Builder(ManHinhChinhNhanVienActivity.this);
        b.setTitle("Xác nhận checkout");
        b.setMessage("Ngày checkout: " + ngay + " Thời gian checkout: " + thoiGian);
        b.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(ManHinhChinhNhanVienActivity.this, ManHinhChinhNhanVienActivity.class);
                startActivity(intent);
            }
        });
        b.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog al = b.create();
        al.show();
    }
}
