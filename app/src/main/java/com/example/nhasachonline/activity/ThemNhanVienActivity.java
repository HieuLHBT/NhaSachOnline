package com.example.nhasachonline.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.ItemNhanVien;
import com.example.nhasachonline.tools.SharePreferences;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class ThemNhanVienActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();

    ArrayList<ItemNhanVien> nhanViens = new ArrayList<>();

    EditText MHTNV_edtMaNhanVien, MHTNV_edtTenNhanVien, MHTNV_edtChucVu, MHTNV_edtTaiKhoan, MHTNV_edtMatKhau, MHTNV_edtEmail, MHTNV_edtDiaChi, MHTNV_edtSoDienThoai, MHTNV_edtCMND, MHTNV_edtLuongCoBan;
    ImageView MHTNV_imgHinhNhanVien;
    Button MHTNV_btnLamMoi, MHTNV_btnThemNhanVien;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_themnhanvien_layout);

        MHTNV_edtMaNhanVien = findViewById(R.id.MHTNV_edtMaNhanVien);
        MHTNV_edtTenNhanVien = findViewById(R.id.MHTNV_edtTenNhanVien);
        MHTNV_edtChucVu = findViewById(R.id.MHTNV_edtChucVu);
        MHTNV_edtTaiKhoan = findViewById(R.id.MHTNV_edtTaiKhoanNhanVien);
        MHTNV_edtMatKhau = findViewById(R.id.MHTNV_edtMatKhauNhanVien);
        MHTNV_edtEmail = findViewById(R.id.MHTNV_edtEmailNhanVien);
        MHTNV_edtDiaChi = findViewById(R.id.MHTNV_edtDiaChiNhanVien);
        MHTNV_edtSoDienThoai = findViewById(R.id.MHTNV_edtSoDienThoaiNhanVien);
        MHTNV_edtCMND = findViewById(R.id.MHTNV_edtCMNDNhanVien);
        MHTNV_edtLuongCoBan = findViewById(R.id.MHTNV_edtLuongNhanVien);
        MHTNV_imgHinhNhanVien = findViewById(R.id.MHTNV_imgAnhNhanVien);
        MHTNV_btnLamMoi = findViewById(R.id.MHTNV_btnNhapMoi);
        MHTNV_btnThemNhanVien = findViewById(R.id.MHTNV_btnThemNhanVien);

        MHTNV_btnThemNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ThemNhanVienActivity.this);
                b.setTitle("CẢNH BÁO");
                b.setMessage("Bạn có muốn thêm nhân viên vào cty không?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fireBase.themNhanVien(nhanViens, ThemNhanVienActivity.this);
                    }
                });
                b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog al = b.create();
                al.show();
                Intent intent1 = new Intent(ThemNhanVienActivity.this, ManHinhQuanLyNhanVienActivity.class);
                startActivity(intent1);
            }
        });

        MHTNV_btnLamMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nhanViens.clear();
            }
        });

    }
}
