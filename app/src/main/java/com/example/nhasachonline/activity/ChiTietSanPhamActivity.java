package com.example.nhasachonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.data_model.Sach;
import com.example.nhasachonline.data_model.VanPhongPham;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.SanPham;
import com.example.nhasachonline.tools.SharePreferences;

import java.util.ArrayList;

public class ChiTietSanPhamActivity extends AppCompatActivity {

    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    private String maSanPham;


    LinearLayout llSach, llVanPhongPham;
    ImageView anhSanPham, anh1Sao, anh2Sao, anh3Sao, anh4Sao, anh5Sao;
    TextView tenSach, tacGia, theLoai, ngayXuatBan, nhaXB, gia, giaKM, khuyenMai, soLuong, slBinhLuan, layout_btnTroVe, xuatXu, nhaPhanPhoi, donVi, tenVanPhongPham;
    Button btnThemVaoGH;
    ImageButton imageButtonThemSL,imageButtonGiamSL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietsanpham_layout);
        maSanPham = getIntent().getStringExtra("maSanPham");

        anhSanPham = findViewById(R.id.CTSP_imgAnhSanPham);
        anh1Sao = findViewById(R.id.CTSP_img1Sao);
        anh2Sao = findViewById(R.id.CTSP_img2Sao);
        anh3Sao = findViewById(R.id.CTSP_img3Sao);
        anh4Sao = findViewById(R.id.CTSP_img4Sao);
        anh5Sao = findViewById(R.id.CTSP_img5Sao);
        btnThemVaoGH = findViewById(R.id.CTSP_btnThemGioHang);
        imageButtonThemSL = findViewById(R.id.CTSP_btnCong);
        imageButtonGiamSL = findViewById(R.id.CTSP_btnTru);
        tenSach = findViewById(R.id.CTSP_tvTenSanPham);
        tacGia = findViewById(R.id.CTSP_tvTacGia);
        theLoai = findViewById(R.id.CTSP_tvTheLoai);
        ngayXuatBan = findViewById(R.id.CTSP_tvngayXuatBan);
        nhaXB = findViewById(R.id.CTSP_tvNhaXuatBan);
        gia = findViewById(R.id.CTSP_tvGia);
        giaKM = findViewById(R.id.CTSP_tvGiaKM);
        khuyenMai = findViewById(R.id.CTSP_tvKhuyenMai);
        soLuong = findViewById(R.id.CTSP_tvSoLuong);
        slBinhLuan = findViewById(R.id.CTSP_tvDanhGia);
        tenVanPhongPham = findViewById(R.id.CTVPP_tvTenSanPham);
        xuatXu = findViewById(R.id.CTVPP_tvXuatxu);
        nhaPhanPhoi = findViewById(R.id.CTVPP_tvNhaPhoi);
        donVi = findViewById(R.id.CTVPP_tvDonVi);
        llSach = findViewById(R.id.itemCTSP_llSach);
        llVanPhongPham = findViewById(R.id.itemCTSP_llVanPhongPham);
        layout_btnTroVe = findViewById(R.id.itemCTSP_tvTroVe);

        //Gan du lieu


        btnThemVaoGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(intent1);
            }
        });
        layout_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             finish();
            }
        });

        //fireBase.hienThiChiTiet(maSanPham,this);

    }

    public void thongTinSanPham(Sach sach, VanPhongPham vanPhongPham, int danhGia, int binhLuan) {
        if(sach != null && vanPhongPham == null){
            tenSach.setText(sach.getTenSach());
            tacGia.setText(sach.getTacGia());
            theLoai.setText(sach.getTheLoai());
            ngayXuatBan.setText(sach.getNgayXuatBan());
            nhaXB.setText(sach.getNhaXuatBan());
            gia.setText(sach.getGiaTien());
            int giaTien = Integer.valueOf(sach.getGiaTien());
            int tien = giaTien - (giaTien * Integer.valueOf(sach.getKhuyenMai()) /100);
            khuyenMai.setText(sach.getKhuyenMai() + "%");
            giaKM.setText(giaTien);
            soLuong.setText("1");
            llSach.setVisibility(View.VISIBLE);
            llVanPhongPham.setVisibility(View.GONE);
        }
        else if(sach == null && vanPhongPham != null){
            tenVanPhongPham.setText(vanPhongPham.getTenVanPhongPham());
            nhaPhanPhoi.setText(vanPhongPham.getNhaPhanPhoi());
            xuatXu.setText(vanPhongPham.getXuatXu());
            donVi.setText(vanPhongPham.getDonVi());
            gia.setText(vanPhongPham.getGiaTien() + "VNĐ");
            int giaTien = Integer.valueOf(vanPhongPham.getGiaTien());
            int tien = giaTien - (giaTien * Integer.valueOf(vanPhongPham.getKhuyenMai()) /100);
            khuyenMai.setText(vanPhongPham.getKhuyenMai() + "%");
            giaKM.setText(giaTien + "VNĐ");
            soLuong.setText("1");
            llSach.setVisibility(View.GONE);
            llVanPhongPham.setVisibility(View.VISIBLE);
        }

        switch (danhGia){
            case 0:
                anh1Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                anh2Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                anh3Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                anh4Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                anh5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 1:
                anh1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                anh2Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                anh3Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                anh4Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                anh5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;

            case 2:
                anh1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                anh2Sao.setImageResource(R.drawable.ic_baseline_star_24);
                anh3Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                anh4Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                anh5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 3:
                anh1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                anh2Sao.setImageResource(R.drawable.ic_baseline_star_24);
                anh3Sao.setImageResource(R.drawable.ic_baseline_star_24);
                anh4Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                anh5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 4:
                anh1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                anh2Sao.setImageResource(R.drawable.ic_baseline_star_24);
                anh3Sao.setImageResource(R.drawable.ic_baseline_star_24);
                anh4Sao.setImageResource(R.drawable.ic_baseline_star_24);
                anh5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 5:
                anh1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                anh2Sao.setImageResource(R.drawable.ic_baseline_star_24);
                anh3Sao.setImageResource(R.drawable.ic_baseline_star_24);
                anh4Sao.setImageResource(R.drawable.ic_baseline_star_24);
                anh5Sao.setImageResource(R.drawable.ic_baseline_star_24);
                break;
        }

        slBinhLuan.setText(binhLuan + "");
    }



}
