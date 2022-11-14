package com.example.nhasachonline.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.data_model.ChamCong;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.BangLuong;
import com.example.nhasachonline.tools.SharePreferences;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class BangLuongActivity extends AppCompatActivity {
    private TextView layoutBL_btnTroVe, layoutBL_tvMaNhanVien, layoutBL_tvTenNhanVien, layoutBL_tvTongGioLam, layoutBL_tvDiTre, layoutBL_tvTangCa, layoutBL_tvSoCaNghiCoPhep, layoutBL_tvSoCaNghiKhongPhep, layoutBL_tvTongDonDaGiao, layoutBL_tvLuongCanBan, layoutBL_tvTongLuong, layoutBL_tvThuongChuyenCan;
    private Spinner layoutBL_spnThangNam;

    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();

    private ArrayList<ChamCong> chamCongs = new ArrayList<>();
    private ArrayList<String> thangNamLamViec = new ArrayList<>();
    private BangLuong bangLuong = new BangLuong();
    private String thangNam = "";
    private Date ngayHienTai = null;

    private DecimalFormat formatter = new DecimalFormat("#,###,###");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bangluong_layout);

        bangLuong.setMaNhanVien("nv1");

        DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String ChuoiNgayHienTai = LocalDate.now().format(fm);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            ngayHienTai = sdf.parse(ChuoiNgayHienTai);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        layoutBL_btnTroVe = findViewById(R.id.layoutBL_btnTroVe);
        layoutBL_spnThangNam = findViewById(R.id.layoutBL_spnThangNam);
        layoutBL_tvMaNhanVien = findViewById(R.id.layoutBL_tvMaNhanVien);
        layoutBL_tvTenNhanVien = findViewById(R.id.layoutBL_tvTenNhanVien);
        layoutBL_tvTongGioLam = findViewById(R.id.layoutBL_tvTongGioLam);
        layoutBL_tvDiTre = findViewById(R.id.layoutBL_tvDiTre);
        layoutBL_tvTangCa = findViewById(R.id.layoutBL_tvTangCa);
        layoutBL_tvSoCaNghiCoPhep = findViewById(R.id.layoutBL_tvSoCaNghiCoPhep);
        layoutBL_tvSoCaNghiKhongPhep = findViewById(R.id.layoutBL_tvSoCaNghiKhongPhep);
        layoutBL_tvTongDonDaGiao = findViewById(R.id.layoutBL_tvTongDonDaGiao);
        layoutBL_tvLuongCanBan = findViewById(R.id.layoutBL_tvLuongCanBan);
        layoutBL_tvThuongChuyenCan = findViewById(R.id.layoutBL_tvThuongChuyenCan);
        layoutBL_tvTongLuong = findViewById(R.id.layoutBL_tvTongLuong);

        layoutBL_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBaseNhaSachOnline.hienThiThangNam(bangLuong.getMaNhanVien(), chamCongs, this);
    }

    public void hienThiThangNam() {
        thangNamLamViec.clear();
        thangNamLamViec.add(chamCongs.get(0).getNgay().substring(3, 10));
        for (int i = 0; i < chamCongs.size(); i++) {
            String thangNamChamCong = chamCongs.get(i).getNgay().substring(3, 10);
            int check = 0;
            for (String thangNam : thangNamLamViec) {
                if (thangNam.equalsIgnoreCase(thangNamChamCong)) {
                    check++;
                }
            }
            if (check == 0) {
                thangNamLamViec.add(thangNamChamCong);
            }
        }
        thangNam = thangNamLamViec.get(0);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.bangchamcong_spinner, thangNamLamViec);
        layoutBL_spnThangNam.setAdapter(arrayAdapter);

        layoutBL_spnThangNam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                thangNam = thangNamLamViec.get(position);
                fireBaseNhaSachOnline.hienThiBangLuong(ngayHienTai, thangNam, bangLuong, BangLuongActivity.this);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                thangNam = thangNamLamViec.get(0);
                fireBaseNhaSachOnline.hienThiBangLuong(ngayHienTai, thangNam, bangLuong, BangLuongActivity.this);
            }
        });
    }

    public void hienThiBangLuong() {
        layoutBL_tvMaNhanVien.setText(bangLuong.getMaNhanVien());
        layoutBL_tvTenNhanVien.setText(bangLuong.getTenNhanVien());
        layoutBL_tvTongGioLam.setText(bangLuong.getTongGioLam() + " giờ");
        layoutBL_tvTangCa.setText(bangLuong.getTangCa() + " phút");
        layoutBL_tvDiTre.setText(bangLuong.getDiTre() + " phút");
        layoutBL_tvSoCaNghiCoPhep.setText(bangLuong.getSoCaNghiCoPhep() + " ca");
        layoutBL_tvSoCaNghiKhongPhep.setText(bangLuong.getSoCaNghiKhongPhep() + " ca");
        layoutBL_tvTongDonDaGiao.setText(String.valueOf(bangLuong.getTongDonDaGiao()));
        layoutBL_tvLuongCanBan.setText(formatter.format(bangLuong.getLuongCanBan()) + " VNĐ");
        layoutBL_tvThuongChuyenCan.setText(formatter.format(bangLuong.getThuongChuyenCan()) + " VNĐ");
        layoutBL_tvTongLuong.setText(formatter.format(bangLuong.getTongLuong()) + " VNĐ");
    }
}
