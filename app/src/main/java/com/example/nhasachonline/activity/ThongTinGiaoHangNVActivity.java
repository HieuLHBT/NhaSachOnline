package com.example.nhasachonline.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.data_model.DonHang;
import com.example.nhasachonline.data_model.KhachHang;
import com.example.nhasachonline.data_model.TrangThaiDonHang;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.tools.SharePreferences;

public class ThongTinGiaoHangNVActivity extends AppCompatActivity {
    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    private String maDonHang;
    private String maKhachHang;


    private DonHang donHang = new DonHang();
    private KhachHang khachHang = new KhachHang();
    private TrangThaiDonHang trangThaiDonHang = new TrangThaiDonHang();

    private TextView layoutTTGH_NV_txtTinhTrang;
    private TextView layoutTTGH_NV_txtMaDonHang;
    private TextView layoutTTGH_NV_txtTenKhachHang;
    private TextView layoutTTGH_NV_txtSDTKhachHang;
    private TextView layoutTTGH_NV_txtHinhThucThanhToan;
    private TextView layoutTTGH_NV_txtDiaChiNhanHang;

    private Button layoutCTDH_NV_btnDinhViDonHang;
    private Button layoutTTGH_NV_btnTroVe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongtingiaohang_nv_layout);

        maDonHang = getIntent().getStringExtra("maDonHang");
        maKhachHang = sharePreferences.getKhachHang(this);

        layoutTTGH_NV_txtTinhTrang = findViewById(R.id.layoutTTGH_NV_txtTinhTrang);
        layoutTTGH_NV_txtMaDonHang = findViewById(R.id.layoutTTGH_NV_txtMaDonHang);
        layoutTTGH_NV_txtTenKhachHang = findViewById(R.id.layoutTTGH_NV_txtTenKhachHang);
        layoutTTGH_NV_txtSDTKhachHang = findViewById(R.id.layoutTTGH_NV_txtSDTKhachHang);
        layoutTTGH_NV_txtHinhThucThanhToan = findViewById(R.id.layoutTTGH_NV_txtHinhThucThanhToan);
        layoutTTGH_NV_txtDiaChiNhanHang = findViewById(R.id.layoutTTGH_NV_txtDiaChiNhanHang);
        layoutCTDH_NV_btnDinhViDonHang = findViewById(R.id.layoutCTDH_NV_btnDinhViDonHang);
        layoutTTGH_NV_btnTroVe = findViewById(R.id.layoutTTGH_NV_btnTroVe);

        //Gan du lieu
        fireBase.hienThiKhachHang_TTGH(maKhachHang, khachHang, this);
        fireBase.hienThiDonHang_TTGH(maDonHang, maKhachHang, donHang, this);
        fireBase.hienThiTinhTrang_TTGH(maDonHang, trangThaiDonHang, this);

        layoutCTDH_NV_btnDinhViDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Chuyển sang màn hình định vị đơn hàng!", Toast.LENGTH_SHORT).show();

            }
        });

        layoutTTGH_NV_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void hienThiKhachHang(){
        layoutTTGH_NV_txtTenKhachHang.setText(khachHang.getTenKhachHang());
        layoutTTGH_NV_txtSDTKhachHang.setText(khachHang.getSoDienThoai());
    }

    public void hienThiDonHang(){
        layoutTTGH_NV_txtDiaChiNhanHang.setText("   " + donHang.getDiaChiGiao());
        layoutTTGH_NV_txtMaDonHang.setText(maDonHang);
    }

    public void hienThiTinhTrang(){
        layoutTTGH_NV_txtTinhTrang.setText(trangThaiDonHang.getTrangThaiGiaoHangNV());
        layoutTTGH_NV_txtHinhThucThanhToan.setText(trangThaiDonHang.getKieuThanhToan());
    }
}
