package com.example.nhasachonline.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;

public class ChiTietSanPhamActivity extends AppCompatActivity {

    ImageView anhSanPham, anh1Sao, anh2Sao, anh3Sao, anh4Sao, anh5Sao;
    TextView tenSP, tacGia, theLoai, namSX, nhaXB, gia, giaKM, khuyenMai, soLuong, danhGia;
    Button btnThemVaoGH;
    ImageButton imageButtonThemSL,imageButtonGiamSL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietsanpham_layout);
        setControll();
        setEvent();
    }

    private void setEvent() {
    }

    private void setControll() {
        anhSanPham = findViewById(R.id.CTSP_imgAnhSanPham);
        anh1Sao = findViewById(R.id.CTSP_img1Sao);
        anh2Sao = findViewById(R.id.CTSP_img2Sao);
        anh3Sao = findViewById(R.id.CTSP_img3Sao);
        anh4Sao = findViewById(R.id.CTSP_img4Sao);
        anh5Sao = findViewById(R.id.CTSP_img5Sao);
        btnThemVaoGH = findViewById(R.id.CTSP_btnThemGioHang);
        imageButtonThemSL = findViewById(R.id.CTSP_btnCong);
        imageButtonGiamSL = findViewById(R.id.CTSP_btnTru);
        tenSP = findViewById(R.id.CTSP_tvTenSanPham);
        tacGia = findViewById(R.id.CTSP_tvTacGia);
        theLoai = findViewById(R.id.CTSP_tvTheLoai);
        namSX = findViewById(R.id.CTSP_tvNamSanXuat);
        nhaXB = findViewById(R.id.CTSP_tvNhaXuatBan);
        gia = findViewById(R.id.CTSP_tvGia);
        giaKM = findViewById(R.id.CTSP_tvGiaKM);
        khuyenMai = findViewById(R.id.CTSP_tvKhuyenMai);
        soLuong = findViewById(R.id.CTSP_tvSoLuong);
        danhGia = findViewById(R.id.CTSP_tvDanhGia);
    }
}