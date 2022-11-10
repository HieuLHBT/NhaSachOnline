package com.example.nhasachonline.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.data_model.ChamCong;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.BangChamCong;
import com.example.nhasachonline.tools.SharePreferences;

import java.util.ArrayList;

public class BangChamCongActivity extends AppCompatActivity {
    private TextView layoutBCC_btnTroVe, layoutBCC_tvGioVaoCa1, layoutBCC_tvGioRaCa1, layoutBCC_tvGioVaoCa2, layoutBCC_tvGioRaCa2, layoutBCC_tvSoDonDaNhan, layoutBCC_tvSoDonDaGiao, layoutBCC_tvSoDonDaHuy, layoutBCC_tvDiTre, layoutBCC_tvTangCa;
    private Button layoutBCC_btnBangLuong, layoutBCC_btnChiTietDonNhan, layoutBCC_btnChiTietDonGiao, layoutBCC_btnChiTietDonHuy;
    private Spinner layoutBCC_spnNgay;

    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();

    private ArrayList<ChamCong> chamCongs = new ArrayList<>();
    private ArrayList<String> ngayLamViec = new ArrayList<>();
    private BangChamCong bangChamCong = new BangChamCong();
    private String maNhanVien = "";
    private String ngay = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bangchamcong_layout);

        maNhanVien = "nv1";

        layoutBCC_btnTroVe = findViewById(R.id.layoutBCC_btnTroVe);
        layoutBCC_spnNgay = findViewById(R.id.layoutBCC_spnNgay);
        layoutBCC_btnBangLuong = findViewById(R.id.layoutBCC_btnBangLuong);
        layoutBCC_tvGioVaoCa1 = findViewById(R.id.layoutBCC_tvGioVaoCa1);
        layoutBCC_tvGioRaCa1 = findViewById(R.id.layoutBCC_tvGioRaCa1);
        layoutBCC_tvGioVaoCa2 = findViewById(R.id.layoutBCC_tvGioVaoCa2);
        layoutBCC_tvGioRaCa2 = findViewById(R.id.layoutBCC_tvGioRaCa2);
        layoutBCC_tvDiTre = findViewById(R.id.layoutBCC_tvDiTre);
        layoutBCC_tvTangCa = findViewById(R.id.layoutBCC_tvTangCa);
        layoutBCC_tvSoDonDaNhan = findViewById(R.id.layoutBCC_tvSoDonDaNhan);
        layoutBCC_btnChiTietDonNhan = findViewById(R.id.layoutBCC_btnChiTietDonNhan);
        layoutBCC_tvSoDonDaGiao = findViewById(R.id.layoutBCC_tvSoDonDaGiao);
        layoutBCC_btnChiTietDonGiao = findViewById(R.id.layoutBCC_btnChiTietDonGiao);
        layoutBCC_tvSoDonDaHuy = findViewById(R.id.layoutBCC_tvSoDonDaHuy);
        layoutBCC_btnChiTietDonHuy = findViewById(R.id.layoutBCC_btnChiTietDonHuy);

        layoutBCC_btnChiTietDonNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutBCC_tvSoDonDaNhan.getText().toString().equalsIgnoreCase("0")) {
                    AlertDialog.Builder b = new AlertDialog.Builder(BangChamCongActivity.this);
                    b.setTitle("CẢNH BÁO");
                    b.setMessage("Không có chi tiết đơn hàng nào!");
                    b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog al = b.create();
                    al.show();
                } else {
                    Intent intent = new Intent(BangChamCongActivity.this, ChiTietDonDaNhanActivity.class);
                    intent.putExtra("ngay", ngay);
                    startActivity(intent);
                }
            }
        });

        layoutBCC_btnChiTietDonGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutBCC_tvSoDonDaGiao.getText().toString().equalsIgnoreCase("0")) {
                    AlertDialog.Builder b = new AlertDialog.Builder(BangChamCongActivity.this);
                    b.setTitle("CẢNH BÁO");
                    b.setMessage("Không có chi tiết đơn hàng nào!");
                    b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog al = b.create();
                    al.show();
                } else {
                    Intent intent = new Intent(BangChamCongActivity.this, ChiTietDonDaGiaoActivity.class);
                    intent.putExtra("ngay", ngay);
                    startActivity(intent);
                }
            }
        });

        layoutBCC_btnChiTietDonHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutBCC_tvSoDonDaHuy.getText().toString().equalsIgnoreCase("0")) {
                    AlertDialog.Builder b = new AlertDialog.Builder(BangChamCongActivity.this);
                    b.setTitle("CẢNH BÁO");
                    b.setMessage("Không có chi tiết đơn hàng nào!");
                    b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog al = b.create();
                    al.show();
                } else {
                    Intent intent = new Intent(BangChamCongActivity.this, ChiTietDonDaHuyActivity.class);
                    intent.putExtra("ngay", ngay);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBaseNhaSachOnline.hienThiBangChamCong(maNhanVien, chamCongs, this);
    }

    public void hienThiNgay() {
        ngayLamViec.clear();
        for (ChamCong chamCong : chamCongs) {
            ngayLamViec.add(chamCong.getNgay());
        }

        ngay = ngayLamViec.get(0);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.bangchamcong_spinner, ngayLamViec);
        layoutBCC_spnNgay.setAdapter(arrayAdapter);

        layoutBCC_spnNgay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ngay = ngayLamViec.get(position);
                hienThiThongTinCa(ngayLamViec.get(position));
                fireBaseNhaSachOnline.hienThiDon(maNhanVien, ngayLamViec.get(position), bangChamCong, BangChamCongActivity.this);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ngay = ngayLamViec.get(0);
                hienThiThongTinCa(ngayLamViec.get(0));
                fireBaseNhaSachOnline.hienThiDon(maNhanVien, ngayLamViec.get(0), bangChamCong, BangChamCongActivity.this);
            }
        });
    }

    private void hienThiThongTinCa(String ngay) {
        for (ChamCong chamCong : chamCongs) {
            if (chamCong.getNgay().equalsIgnoreCase(ngay)) {
                if (!chamCong.getCa1().equalsIgnoreCase("")) {
                    layoutBCC_tvGioVaoCa1.setText(chamCong.getGioVaoCa1());
                    layoutBCC_tvGioRaCa1.setText(chamCong.getGioRaCa1());
                } else {
                    layoutBCC_tvGioVaoCa1.setText("không làm");
                    layoutBCC_tvGioRaCa1.setText("không làm");
                }
                if (!chamCong.getCa2().equalsIgnoreCase("")) {
                    layoutBCC_tvGioVaoCa2.setText(chamCong.getGioVaoCa2());
                    layoutBCC_tvGioRaCa2.setText(chamCong.getGioRaCa2());
                } else {
                    layoutBCC_tvGioVaoCa2.setText("không làm");
                    layoutBCC_tvGioRaCa2.setText("không làm");
                }
                if (!chamCong.getThoiGianTre().equalsIgnoreCase("")) {
                    layoutBCC_tvDiTre.setText(chamCong.getThoiGianTre() + " phút");
                } else {
                    layoutBCC_tvDiTre.setText("0 phút");
                }
                if (!chamCong.getGioTangCa().equalsIgnoreCase("")) {
                    layoutBCC_tvTangCa.setText(chamCong.getGioTangCa() + " phút");
                } else {
                    layoutBCC_tvTangCa.setText("0 phút");
                }
                break;
            }
        }
    }

    public void hienThiDon() {
        layoutBCC_tvSoDonDaNhan.setText(bangChamCong.getSoDonDaNhan() + "");
        layoutBCC_tvSoDonDaGiao.setText(bangChamCong.getSoDonDaGiao() + "");
        layoutBCC_tvSoDonDaHuy.setText(bangChamCong.getSoDonDaHuy() + "");
    }

}
