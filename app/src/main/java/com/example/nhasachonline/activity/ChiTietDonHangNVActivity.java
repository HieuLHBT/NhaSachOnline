package com.example.nhasachonline.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.ChiTietDonHangNVRecyclerViewAdapter;
import com.example.nhasachonline.data_model.DonHang;
import com.example.nhasachonline.data_model.GiamGia;
import com.example.nhasachonline.data_model.TrangThaiDonHang;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.ItemChiTietDonHangNV;
import com.example.nhasachonline.tools.SharePreferences;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietDonHangNVActivity extends AppCompatActivity {
    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    private ChiTietDonHangNVRecyclerViewAdapter adapter;
    private ArrayList<ItemChiTietDonHangNV> itemChiTietDonHangNVs = new ArrayList<>();

    private DecimalFormat formatter = new DecimalFormat("#,###,###");

    private String maDonHang;
    private String maGiamGia = "gg2";
    private String maKhachHang;

    private TrangThaiDonHang trangThaiDonHang = new TrangThaiDonHang();
    private GiamGia giamGia = new GiamGia();
    private DonHang donHang = new DonHang();

    private TextView layoutCTDH_NV_txtPhuongThucThanhToan;
    private TextView layoutCTDH_NV_txtDiaChiNhanHang;
    private TextView layoutCTDH_NV_txtGiamGia;
    private TextView layoutCTDH_NV_txtPhiVanChuyen;
    private TextView layoutCTDH_NV_txtTongTienThanhToan;

    private Button layoutCTDH_NV_btnTroVe;
    private Button layoutCTDH_NV_btnKiemTraKho;
    private Button layoutCTDH_NV_btnXacNhan;
    private Button layoutCTDH_NV_btnHuyDonHang;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietdonhang_nv_layout);

        maDonHang = getIntent().getStringExtra("maDonHang");
        //maKhachHang =  sharePreferences.getKhachHang(this);

        layoutCTDH_NV_txtPhuongThucThanhToan = findViewById(R.id.layoutCTDH_NV_txtPhuongThucThanhToan);
        layoutCTDH_NV_txtDiaChiNhanHang = findViewById(R.id.layoutCTDH_NV_txtDiaChiNhanHang);
        layoutCTDH_NV_txtGiamGia = findViewById(R.id.layoutCTDH_NV_txtGiamGia);
        layoutCTDH_NV_txtPhiVanChuyen = findViewById(R.id.layoutCTDH_NV_txtPhiVanChuyen);
        layoutCTDH_NV_txtTongTienThanhToan = findViewById(R.id.layoutCTDH_NV_txtTongTienThanhToan);
        layoutCTDH_NV_btnTroVe = findViewById(R.id.layoutCTDH_NV_btnTroVe);
        layoutCTDH_NV_btnKiemTraKho = findViewById(R.id.layoutCTDH_NV_btnKiemTraKho);
        layoutCTDH_NV_btnXacNhan = findViewById(R.id.layoutCTDH_NV_btnXacNhan);
        layoutCTDH_NV_btnHuyDonHang = findViewById(R.id.layoutCTDH_NV_btnHuyDonHang);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutCTDH_NV_rvChiTietDonHangNV);
        adapter = new ChiTietDonHangNVRecyclerViewAdapter(this, R.layout.chitietdonhang_nv_item, itemChiTietDonHangNVs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        // Gan du lieu
        fireBase.hienThiPhuongThucThanhToanCTDH(maDonHang, trangThaiDonHang, this);
        //fireBase.hienThiGiamGiaCTDH(maKhachHang, maGiamGia, giamGia, this);
        fireBase.hienThiDonHangCTDH(maDonHang, donHang,this);


        layoutCTDH_NV_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fireBase.hienThiChiTietDonHang(maDonHang, itemChiTietDonHangNVs, adapter, this);

    }
    public void hienThiTrangThaiCTDH(){
        layoutCTDH_NV_txtPhuongThucThanhToan.setText("  " + trangThaiDonHang.getKieuThanhToan());
    }
    public void hienThiTienGiamGiaCTDH(){
        layoutCTDH_NV_txtGiamGia.setText("  " + giamGia.getTienGiamGia());
    }

    public void hienThiDonHang(){
        layoutCTDH_NV_txtDiaChiNhanHang.setText(" " + donHang.getDiaChiGiao());
        layoutCTDH_NV_txtPhiVanChuyen.setText("  " + donHang.getPhiVanChuyen());
        layoutCTDH_NV_txtGiamGia.setText("  " + donHang.getMaGiamGia());
    }

    public void kiemTraKho(){
        
    }
}
