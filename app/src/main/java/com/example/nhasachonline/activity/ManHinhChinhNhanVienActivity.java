package com.example.nhasachonline.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.data_model.NhanVien;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.tools.SharePreferences;

public class ManHinhChinhNhanVienActivity extends AppCompatActivity {
    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();
    private String maNhanVien;
    private NhanVien nhanVien = new NhanVien();

    private TextView layoutMHCNV_txtTenNhanVien;
    private TextView layoutMHCNV_txtMaNhanVien;
    private ImageView layoutMHCNV_imgHinhNhanVien;
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
        sharePreferences.setKhachHng(this, maNhanVien);
        maNhanVien = sharePreferences.getKhachHang(this);

        layoutMHCNV_txtTenNhanVien = findViewById(R.id.layoutMHCNV_txtTenNhanVien);
        layoutMHCNV_txtMaNhanVien = findViewById(R.id.layoutMHCNV_txtMaNhanVien);
        layoutMHCNV_imgHinhNhanVien = findViewById(R.id.layoutMHCNV_imgHinhNhanVien);
        layoutMHCNV_btnCheckin = findViewById(R.id.layoutMHCNV_btnCheckin);
        layoutMHCNV_btnCheckout = findViewById(R.id.layoutMHCNV_btnCheckout);
        layoutMHCNV_btnQuanLyDonHang = findViewById(R.id.layoutMHCNV_btnQuanLyDonHang);
        layoutMHCNV_btnQuanLySanPham = findViewById(R.id.layoutMHCNV_btnQuanLySanPham);
        layoutMHCNV_btnLichLamViec = findViewById(R.id.layoutMHCNV_btnLichLamViec);
        layoutMHCNV_btnBangCong = findViewById(R.id.layoutMHCNV_btnBangCong);
        layoutMHCNV_btnPhanHoiKhachHang = findViewById(R.id.layoutMHCNV_btnPhanHoiKhachHang);

        fireBase.hienThiManHinhChinhNhanVien("nv1", nhanVien, this);
    }

    public void hienThiManHinhChinhNhanVien(){
        layoutMHCNV_txtTenNhanVien.setText(nhanVien.getTenNhanVien());
        layoutMHCNV_txtMaNhanVien.setText(nhanVien.getMaNhanVien());
    }
}
