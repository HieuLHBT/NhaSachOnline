package com.example.nhasachonline.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
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
import com.example.nhasachonline.tools.SharePreferences;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietSanPhamActivity extends AppCompatActivity {

    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();
    private String maSanPham;
    private String maKhachHang;

    LinearLayout llSach, llVanPhongPham;
    ImageView anhSanPham, anhVPP,anh1Sao, anh2Sao, anh3Sao, anh4Sao, anh5Sao;
    TextView tenSach, tacGia, theLoai, ngayXuatBan, nhaXB, giaSach, giaKMSach, khuyenMaiSach, soLuong, slBinhLuan, layout_btnTroVe, xuatXu, nhaPhanPhoi, donVi, tenVanPhongPham, giaVPP, khuyenMaiVPP, giaKMVanPhongPham;
    Button btnThemVaoGH;
    ImageButton imageButtonThemSL,imageButtonGiamSL;
    int soLuongMua = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietsanpham_layout);
        maSanPham = getIntent().getStringExtra("maSanPham");
        maKhachHang = "kh1";

        anhSanPham = findViewById(R.id.CTSP_imgAnhSanPham);
        anhVPP = findViewById(R.id.CTVPP_imgAnhSanPham);
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
        giaSach = findViewById(R.id.CTSP_tvGia);
        giaVPP = findViewById(R.id.CTVPP_tvGia);
        giaKMSach = findViewById(R.id.CTSP_tvGiaKM);
        giaKMVanPhongPham = findViewById(R.id.CTVPP_tvGiaKM);
        khuyenMaiSach = findViewById(R.id.CTSP_tvKhuyenMai);
        khuyenMaiVPP = findViewById(R.id.CTVPP_tvKhuyenMai);
        soLuong = findViewById(R.id.CTSP_tvSoLuong);
        slBinhLuan = findViewById(R.id.CTSP_tvDanhGia);
        tenVanPhongPham = findViewById(R.id.CTVPP_tvTenSanPham);
        xuatXu = findViewById(R.id.CTVPP_tvXuatxu);
        nhaPhanPhoi = findViewById(R.id.CTVPP_tvNhaPhoi);
        donVi = findViewById(R.id.CTVPP_tvDonVi);
        llSach = findViewById(R.id.itemCTSP_llSach);
        llVanPhongPham = findViewById(R.id.itemCTSP_llVanPhongPham);
        layout_btnTroVe = findViewById(R.id.itemCTSP_tvTroVe);

        imageButtonThemSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soLuong.setText(String.valueOf(++soLuongMua));
            }
        });
        imageButtonGiamSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soLuong.setText(String.valueOf(--soLuongMua));
            }
        });
        btnThemVaoGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fireBase.themVaoGioHang(maKhachHang,maSanPham,String.valueOf(soLuongMua));
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

        fireBase.hienThiChiTietSanPham(maSanPham,this);

    }

    public void thongTinSanPham(Sach sach, VanPhongPham vanPhongPham, int danhGia, int binhLuan) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        if(sach != null && vanPhongPham == null){
            tenSach.setText(sach.getTenSach());
            tacGia.setText(sach.getTacGia());
            theLoai.setText(sach.getTheLoai());
            ngayXuatBan.setText(sach.getNgayXuatBan());
            nhaXB.setText(sach.getNhaXuatBan());
            giaSach.setText(sach.getGiaTien());
            int giaTien = Integer.valueOf(sach.getGiaTien());
            int tien = giaTien - (giaTien * Integer.valueOf(sach.getKhuyenMai()) /100);
            khuyenMaiSach.setText(sach.getKhuyenMai() + "%");
            giaKMSach.setText(formatter.format(tien) + " VNĐ");
            StorageReference storageReference = FirebaseStorage.getInstance().getReference(sach.getHinhSach());
            try {
                File file = null;
                if (sach.getHinhSach().contains("png")) {
                    file = File.createTempFile(sach.getHinhSach().substring(0,sach.getHinhSach().length()-4), "png");
                } else if (sach.getHinhSach().contains("jpg")) {
                    file = File.createTempFile(sach.getHinhSach().substring(0,sach.getHinhSach().length()-4), "jpg");
                }
                final File fileHinh = file;
                ((StorageReference) storageReference).getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        anhSanPham.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("onCancelled", "Lỗi!" + e.getMessage());
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            llSach.setVisibility(View.VISIBLE);
            llVanPhongPham.setVisibility(View.GONE);
        }
        else if(sach == null && vanPhongPham != null){
            tenVanPhongPham.setText(vanPhongPham.getTenVanPhongPham());
            nhaPhanPhoi.setText(vanPhongPham.getNhaPhanPhoi());
            xuatXu.setText(vanPhongPham.getXuatXu());
            donVi.setText(vanPhongPham.getDonVi());
            giaVPP.setText(vanPhongPham.getGiaTien() + " VNĐ");
            int giaTien = Integer.valueOf(vanPhongPham.getGiaTien());
            int tien = giaTien - (giaTien * Integer.valueOf(vanPhongPham.getKhuyenMai()) /100);
            khuyenMaiVPP.setText(vanPhongPham.getKhuyenMai() + "%");
            giaKMVanPhongPham.setText(formatter.format(tien)+ " VNĐ");

            StorageReference storageReference = FirebaseStorage.getInstance().getReference(vanPhongPham.getHinhVanPhongPham());
            try {
                File file = null;
                if (vanPhongPham.getHinhVanPhongPham().contains("png")) {
                    file = File.createTempFile(vanPhongPham.getHinhVanPhongPham().substring(0,vanPhongPham.getHinhVanPhongPham().length()-4), "png");
                } else if (vanPhongPham.getHinhVanPhongPham().contains("jpg")) {
                    file = File.createTempFile(vanPhongPham.getHinhVanPhongPham().substring(0,vanPhongPham.getHinhVanPhongPham().length()-4), "jpg");
                }
                final File fileHinh = file;
                ((StorageReference) storageReference).getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        anhVPP.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("onCancelled", "Lỗi!" + e.getMessage());
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            llSach.setVisibility(View.GONE);
            llVanPhongPham.setVisibility(View.VISIBLE);
        }
        soLuong.setText(String.valueOf(soLuongMua));
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
