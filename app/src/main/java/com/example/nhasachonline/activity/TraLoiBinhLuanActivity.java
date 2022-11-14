package com.example.nhasachonline.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.PhanHoiYKienKhachHang;
import com.example.nhasachonline.tools.SharePreferences;

public class TraLoiBinhLuanActivity extends AppCompatActivity {
    private TextView layoutTLBL_btnTroVe, layoutTLBL_tvMaSanPham, layoutTLBL_tvTenSanPham, layoutTLBL_tvMaDonHang, layoutTLBL_tvTenKhachHang, layoutTLBL_tvDanhGia, layoutTLBL_tvThoiGianBinhLuan, layoutTLBL_tvNoiDungBinhLuan, layoutTLBL_tvMaNhanVien, layoutTLBL_tvTenNhanVien;
    private EditText layoutTLBL_edtNoiDungTraLoi;
    private Button layoutTLBL_btnXacNhan;
    private LinearLayout layoutTLBL_llMaNhanVien, layoutTLBL_llTenNhanVien;

    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();

    private PhanHoiYKienKhachHang traLoi = new PhanHoiYKienKhachHang();
    private PhanHoiYKienKhachHang thayDoi = new PhanHoiYKienKhachHang();

    private String maNhanVien;
    private String noiDung = "";
    private String noiDungThayDoi = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traloibinhluan_layout);

        maNhanVien = "nv1";

        layoutTLBL_btnTroVe = findViewById(R.id.layoutTLBL_btnTroVe);
        layoutTLBL_btnXacNhan = findViewById(R.id.layoutTLBL_btnXacNhan);
        layoutTLBL_tvMaSanPham = findViewById(R.id.layoutTLBL_tvMaSanPham);
        layoutTLBL_tvTenSanPham = findViewById(R.id.layoutTLBL_tvTenSanPham);
        layoutTLBL_tvMaDonHang = findViewById(R.id.layoutTLBL_tvMaDonHang);
        layoutTLBL_tvTenKhachHang = findViewById(R.id.layoutTLBL_tvTenKhachHang);
        layoutTLBL_tvDanhGia = findViewById(R.id.layoutTLBL_tvDanhGia);
        layoutTLBL_tvThoiGianBinhLuan = findViewById(R.id.layoutTLBL_tvThoiGianBinhLuan);
        layoutTLBL_tvNoiDungBinhLuan = findViewById(R.id.layoutTLBL_tvNoiDungBinhLuan);
        layoutTLBL_tvMaNhanVien = findViewById(R.id.layoutTLBL_tvMaNhanVien);
        layoutTLBL_tvTenNhanVien = findViewById(R.id.layoutTLBL_tvTenNhanVien);
        layoutTLBL_edtNoiDungTraLoi = findViewById(R.id.layoutTLBL_edtNoiDungTraLoi);
        layoutTLBL_llMaNhanVien = findViewById(R.id.layoutTLBL_llMaNhanVien);
        layoutTLBL_llTenNhanVien = findViewById(R.id.layoutTLBL_llTenNhanVien);

        traLoi = (PhanHoiYKienKhachHang) getIntent().getSerializableExtra("traLoi");
        thayDoi = (PhanHoiYKienKhachHang) getIntent().getSerializableExtra("thayDoi");
        if (traLoi != null) {
            hienThi(traLoi);
            layoutTLBL_llMaNhanVien.setVisibility(View.GONE);
            layoutTLBL_llTenNhanVien.setVisibility(View.GONE);
        } else if (thayDoi != null) {
            noiDung = thayDoi.getNoiDungTraLoi();
            noiDungThayDoi = thayDoi.getNoiDungTraLoi();
            hienThi(thayDoi);
            layoutTLBL_llMaNhanVien.setVisibility(View.VISIBLE);
            layoutTLBL_llTenNhanVien.setVisibility(View.VISIBLE);
        }

        layoutTLBL_edtNoiDungTraLoi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count != 0) {
                    if (s.charAt(0) == ' ') {
                        AlertDialog.Builder b = new AlertDialog.Builder(TraLoiBinhLuanActivity.this);
                        b.setTitle("CẢNH BÁO!");
                        b.setMessage("Không được có ký tự trắng đầu tiên!");
                        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                                layoutTLBL_edtNoiDungTraLoi.setBackgroundColor(TraLoiBinhLuanActivity.this.getResources().getColor(R.color.red, TraLoiBinhLuanActivity.this.getTheme()));
                            }
                        });
                        AlertDialog al = b.create();
                        al.show();
                    } else {
                        layoutTLBL_edtNoiDungTraLoi.setBackgroundColor(TraLoiBinhLuanActivity.this.getResources().getColor(R.color.white, TraLoiBinhLuanActivity.this.getTheme()));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                noiDungThayDoi = String.valueOf(s);
            }
        });

        layoutTLBL_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layoutTLBL_btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noiDungThayDoi.equalsIgnoreCase("")) {
                    AlertDialog.Builder b = new AlertDialog.Builder(TraLoiBinhLuanActivity.this);
                    b.setTitle("CẢNH BÁO!");
                    b.setMessage("Không được bỏ trống nội dung trả lời!");
                    b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog al = b.create();
                    al.show();
                } else if (noiDung.equalsIgnoreCase(noiDungThayDoi)) {
                    AlertDialog.Builder b = new AlertDialog.Builder(TraLoiBinhLuanActivity.this);
                    b.setTitle("CẢNH BÁO!");
                    b.setMessage("Nội dung trả lời thay đổi mới được cập nhật!");
                    b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog al = b.create();
                    al.show();
                } else if (!noiDung.equalsIgnoreCase(noiDungThayDoi) && !noiDungThayDoi.equalsIgnoreCase("")) {
                    if (traLoi != null) {
                        fireBaseNhaSachOnline.traLoiBinhLuan(maNhanVien, noiDungThayDoi, traLoi, TraLoiBinhLuanActivity.this);
                    } else if (thayDoi != null) {
                        fireBaseNhaSachOnline.traLoiBinhLuan(maNhanVien, noiDungThayDoi, thayDoi, TraLoiBinhLuanActivity.this);
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void hienThi(PhanHoiYKienKhachHang phanHoiYKienKhachHang) {
        layoutTLBL_tvMaSanPham.setText(String.valueOf(phanHoiYKienKhachHang.getMaSanPham()));
        layoutTLBL_tvTenSanPham.setText(phanHoiYKienKhachHang.getTenSanPham());
        layoutTLBL_tvMaDonHang.setText(phanHoiYKienKhachHang.getMaDonHang());
        layoutTLBL_tvTenKhachHang.setText(phanHoiYKienKhachHang.getTenKhachHang());
        layoutTLBL_tvDanhGia.setText(phanHoiYKienKhachHang.getDanhGia() + " sao");
        layoutTLBL_tvThoiGianBinhLuan.setText(phanHoiYKienKhachHang.getThoiGianBinhLuan());
        layoutTLBL_tvNoiDungBinhLuan.setText(phanHoiYKienKhachHang.getNoiDungBinhLuan());
        layoutTLBL_tvMaNhanVien.setText(phanHoiYKienKhachHang.getMaNhanVien());
        layoutTLBL_tvTenNhanVien.setText(phanHoiYKienKhachHang.getTenNhanVien());
        layoutTLBL_edtNoiDungTraLoi.setText(phanHoiYKienKhachHang.getNoiDungTraLoi());
    }
}
