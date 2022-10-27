package com.example.nhasachonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.data_model.VanPhongPham;

import java.util.ArrayList;

public class ChiTietVanPPActivity extends AppCompatActivity {
    ImageView anhSanPham, anh1Sao, anh2Sao, anh3Sao, anh4Sao, anh5Sao;
    TextView tenSP, xuatXu, donVi, nhaPhanPhoi, gia, giaKM, khuyenMai, soLuong, danhGia;
    Button btnThemVaoGH;
    ImageButton imageButtonThemSL,imageButtonGiamSL;
    private ArrayList<VanPhongPham> vpps = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietvanphonpham_layout);

        anhSanPham = findViewById(R.id.CTVPP_imgAnhSanPham);
        anh1Sao = findViewById(R.id.CTVPP_img1Sao);
        anh2Sao = findViewById(R.id.CTVPP_img2Sao);
        anh3Sao = findViewById(R.id.CTVPP_img3Sao);
        anh4Sao = findViewById(R.id.CTVPP_img4Sao);
        anh5Sao = findViewById(R.id.CTVPP_img5Sao);
        btnThemVaoGH = findViewById(R.id.CTVPP_btnThemGioHang);
        imageButtonThemSL = findViewById(R.id.CTVPP_btnCong);
        imageButtonGiamSL = findViewById(R.id.CTVPP_btnTru);
        tenSP = findViewById(R.id.CTVPP_tvTenSanPham);
        xuatXu = findViewById(R.id.CTVPP_tvXuatxu);
        donVi = findViewById(R.id.CTVPP_tvDonVi);
        nhaPhanPhoi = findViewById(R.id.CTVPP_tvNhaPhoi);
        gia = findViewById(R.id.CTVPP_tvGia);
        giaKM = findViewById(R.id.CTVPP_tvGiaKM);
        khuyenMai = findViewById(R.id.CTVPP_tvKhuyenMai);
        soLuong = findViewById(R.id.CTVPP_tvSoLuong);
        danhGia = findViewById(R.id.CTVPP_tvDanhGia);

        btnThemVaoGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ChiTietVanPPActivity.this, GioHangActivity.class);
                startActivity(intent1);
            }
        });

        imageButtonThemSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        imageButtonGiamSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
