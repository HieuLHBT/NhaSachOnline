package com.example.nhasachonline.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.data_model.DonHang;
import com.example.nhasachonline.data_model.TrangThaiDonHang;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.tools.SharePreferences;

public class ThongBaoHuyDonHangActivity extends AppCompatActivity {

    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();
    private String maDonHang = "dh2";
    private DonHang donHang = new DonHang();
    private TrangThaiDonHang trangThaiDonHang = new TrangThaiDonHang();

    private TextView MHTBHDH_tvMaDonHang;
    private EditText MHTBHDH_edtNoiDungHuy;
    private Button MHTBHDH_btnHuy;
    private Button MHTBHDH_btnXacNhan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_thongbaohuy_layout_nhanvien);

        sharePreferences.themMaDonHang(this, maDonHang);
        maDonHang = sharePreferences.layMaDonHang(this);

        //Ánh xạ
        MHTBHDH_tvMaDonHang = findViewById(R.id.MHTBHDH_tvMaDonHang);
        MHTBHDH_edtNoiDungHuy = findViewById(R.id.MHTBHDH_edtNoiDungHuy);
        MHTBHDH_btnHuy = findViewById(R.id.MHTBHDH_btnHuy);
        MHTBHDH_btnXacNhan = findViewById(R.id.MHTBHDH_btnXacNhan);

        //Gán dữ liệu
        fireBase.hienThiDonHang_TBHDH(maDonHang, donHang, this);
        MHTBHDH_btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        MHTBHDH_btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fireBase.lyDoHuy(maDonHang, MHTBHDH_edtNoiDungHuy.getText().toString());
               // finish();
            }
        });
    }

    public void hienThiDonHang(){
        MHTBHDH_tvMaDonHang.setText(maDonHang);
    }
}
