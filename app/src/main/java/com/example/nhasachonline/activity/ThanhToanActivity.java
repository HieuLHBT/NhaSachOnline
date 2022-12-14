package com.example.nhasachonline.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.ThanhToanRecyclerViewAdapter;
import com.example.nhasachonline.data_model.DonHang;
import com.example.nhasachonline.data_model.GiamGia;
import com.example.nhasachonline.data_model.KhachHang;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.GioHang;
import com.example.nhasachonline.item.ThanhToan;
import com.example.nhasachonline.tools.SharePreferences;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class ThanhToanActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();
    private String maDonHang;
    private String maKhachHang;
    private KhachHang khachHang = new KhachHang();

    private ArrayList<String> hinhThucGiao = new ArrayList<>();
    private ArrayList<String> phuongThucThanhToan = new ArrayList<>();
    private ArrayList<ThanhToan> thanhToans = new ArrayList<>();
    private ThanhToanRecyclerViewAdapter adapter;
    private Integer phiVanChuyen = 0;
    private GiamGia giamGia = new GiamGia();
    private int tongTien = 0;

    private TextView layoutTT_tvMaDonHang, layoutTT_tvHoTen, layoutTT_tvSoDienThoai, layoutTT_tvEmail, layoutTT_tvDiaChi, layoutTT_btnTroVe, layoutTT_tvMaGiamGia, layoutTT_tvTongTien, layoutTT_tvPhiVanChuyen, layoutTT_tvGiamGia, layoutTT_tvTongTienThanhToan;
    private ImageButton layoutTT_imgbtnDiaChi;
    private Spinner layoutTT_spnHinhThucGiao, layoutTT_spnPhuongThucThanhToan;
    private Button layoutTT_btnChonMa, layoutTT_btnDatHang, layoutTT_btnHuy;
    private DecimalFormat formatter = new DecimalFormat("#,###,###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanhtoan_layout);
        maDonHang = sharePreferences.layMaDonHang(this);
        maKhachHang = "kh1";

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutTT_rvDanhSach);
        layoutTT_tvMaDonHang = findViewById(R.id.layoutTT_tvMaDonHang);
        layoutTT_tvHoTen = findViewById(R.id.layoutTT_tvHoTen);
        layoutTT_tvSoDienThoai = findViewById(R.id.layoutTT_tvSoDienThoai);
        layoutTT_tvEmail = findViewById(R.id.layoutTT_tvEmail);
        layoutTT_tvDiaChi = findViewById(R.id.layoutTT_tvDiaChi);
        layoutTT_imgbtnDiaChi = findViewById(R.id.layoutTT_imgbtnDiaChi);
        layoutTT_btnTroVe = findViewById(R.id.layoutTT_btnTroVe);
        layoutTT_spnHinhThucGiao = findViewById(R.id.layoutTT_spnHinhThucGiao);
        layoutTT_tvMaGiamGia = findViewById(R.id.layoutTT_tvMaGiamGia);
        layoutTT_btnChonMa = findViewById(R.id.layoutTT_btnChonMa);
        layoutTT_spnPhuongThucThanhToan = findViewById(R.id.layoutTT_spnPhuongThucThanhToan);
        layoutTT_tvTongTien = findViewById(R.id.layoutTT_tvTongTien);
        layoutTT_tvPhiVanChuyen = findViewById(R.id.layoutTT_tvPhiVanChuyen);
        layoutTT_tvGiamGia = findViewById(R.id.layoutTT_tvGiamGia);
        layoutTT_tvTongTienThanhToan = findViewById(R.id.layoutTT_tvTongTienThanhToan);
        layoutTT_btnDatHang = findViewById(R.id.layoutTT_btnDatHang);
        layoutTT_btnHuy = findViewById(R.id.layoutTT_btnHuy);

        // G??n d??? li???u
        layoutTT_tvMaDonHang.setText(maDonHang);
        fireBaseNhaSachOnline.hienThiKhachHang(maKhachHang, khachHang, this);
        hinhThucGiao.add("Giao ti??u chu???n 15,000 VN??");
        hinhThucGiao.add("Giao nhanh 30,000 VN??");
        ArrayAdapter arrayAdapterHinhThucGiao = new ArrayAdapter(this, R.layout.thanhtoan_spinner, hinhThucGiao);
        layoutTT_spnHinhThucGiao.setAdapter(arrayAdapterHinhThucGiao);
        phuongThucThanhToan.add("Tr???c ti???p");
        phuongThucThanhToan.add("Online");
        ArrayAdapter arrayAdapterPhuongThucThanhToan = new ArrayAdapter(this, R.layout.thanhtoan_spinner, phuongThucThanhToan);
        layoutTT_spnPhuongThucThanhToan.setAdapter(arrayAdapterPhuongThucThanhToan);

        adapter = new ThanhToanRecyclerViewAdapter(this, R.layout.thanhtoan_item, thanhToans);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
        fireBaseNhaSachOnline.hienThiItemThanhToan(maDonHang, thanhToans, adapter, this);
        fireBaseNhaSachOnline.hienThiGiamGia(maKhachHang, giamGia,this);

        layoutTT_spnHinhThucGiao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        phiVanChuyen = 15000;
                        layoutTT_tvPhiVanChuyen.setText(formatter.format(phiVanChuyen) + " VN??");
                        tongTien();
                        break;
                    case 1:
                        phiVanChuyen = 30000;
                        layoutTT_tvPhiVanChuyen.setText(formatter.format(phiVanChuyen) + " VN??");
                        tongTien();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                phiVanChuyen = 15000;
                layoutTT_tvPhiVanChuyen.setText(formatter.format(phiVanChuyen) + " VN??");
            }
        });

        layoutTT_btnChonMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (giamGia.getMaGiamGia() != null) {
                    fireBaseNhaSachOnline.xoaChonGiamGia(maKhachHang, giamGia.getMaGiamGia());
                }
                Intent intent = new Intent(ThanhToanActivity.this, MaGiamGiaActivity.class);
                intent.putExtra("tongTien", tongTien);
                ThanhToanActivity.this.startActivity(intent);
            }
        });

        layoutTT_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layoutTT_btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ThanhToanActivity.this);
                b.setTitle("C???NH B??O!");
                b.setMessage("X??c nh???n h???y thanh to??n?");
                b.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fireBaseNhaSachOnline.huyThanhToan(maKhachHang, giamGia.getMaGiamGia(),maDonHang, ThanhToanActivity.this);
                        finish();
                    }
                });
                b.setNegativeButton("Kh??ng ?????ng ??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog al = b.create();
                al.show();
            }
        });
        layoutTT_btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ThanhToanActivity.this);
                b.setTitle("TH??NG B??O!");
                b.setMessage("X??c nh???n ?????t h??ng?");
                b.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        String ngayHienTai = sdf.format(LocalDate.now());
                        DonHang donHang;
                        if (giamGia.getMaGiamGia() != null && Integer.valueOf(giamGia.getYeuCau()) < tongTien) {
                            donHang = new DonHang(maDonHang,khachHang.getDiaChi(),giamGia.getMaGiamGia(),maKhachHang,"","","",ngayHienTai, String.valueOf(phiVanChuyen));
                        } else {
                            donHang = new DonHang(maDonHang,khachHang.getDiaChi(), "",maKhachHang,"","","",ngayHienTai, String.valueOf(phiVanChuyen));
                        }
                        String ngayGiao = "";
                        if (donHang.getPhiVanChuyen().equalsIgnoreCase("15000")) {
                            int thu = LocalDate.now().plusDays(3).getDayOfWeek().getValue();
                            if (thu == 0) {
                                ngayGiao = sdf.format(LocalDate.now().plusDays(4));
                            } else {
                                ngayGiao = sdf.format(LocalDate.now().plusDays(3));
                            }
                        } else if (donHang.getPhiVanChuyen().equalsIgnoreCase("30000")) {
                            int thu = LocalDate.now().plusDays(1).getDayOfWeek().getValue();
                            if (thu == 0) {
                                ngayGiao = sdf.format(LocalDate.now().plusDays(2));
                            } else {
                                ngayGiao = sdf.format(LocalDate.now().plusDays(1));
                            }
                        }
                        donHang.setThoiGianGiao(ngayGiao);
                        fireBaseNhaSachOnline.datHang(layoutTT_spnPhuongThucThanhToan.getSelectedItem().toString() ,donHang, ThanhToanActivity.this);
                        finish();
                    }
                });
                b.setNegativeButton("Kh??ng ?????ng ??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog al = b.create();
                al.show();
            }
        });
    }

    public void hienThiKhachHang() {
        layoutTT_tvHoTen.setText(khachHang.getTenKhachHang());
        layoutTT_tvSoDienThoai.setText(khachHang.getSoDienThoai());
        layoutTT_tvEmail.setText(khachHang.getSoDienThoai());
        layoutTT_tvDiaChi.setText(khachHang.getDiaChi());
    }

    public void tongTien() {
        int sum = 0;
        for (ThanhToan thanhToan : thanhToans) {
            sum += thanhToan.getTongTien();
        }
        layoutTT_tvTongTien.setText(formatter.format(sum) + " VN??");
        tongTien = sum + phiVanChuyen;
        int tongTienThanhToan = sum + phiVanChuyen;
        if (giamGia.getMaGiamGia() != null) {
            if (tongTienThanhToan > Integer.valueOf(giamGia.getYeuCau())) {
                tongTienThanhToan = tongTienThanhToan - Integer.valueOf(giamGia.getTienGiamGia());
                layoutTT_tvMaGiamGia.setText(giamGia.getMaGiamGia());
                layoutTT_tvGiamGia.setText("-" + formatter.format(Integer.valueOf(giamGia.getTienGiamGia())) + " VN??");
            }
            else {
                layoutTT_tvMaGiamGia.setText(giamGia.getMaGiamGia());
                layoutTT_tvGiamGia.setText("Kh??ng ????? ??i???u ki???n!");
            }
        } else {
            layoutTT_tvMaGiamGia.setText("ch??a c?? m??!");
            layoutTT_tvGiamGia.setText("-" + 0 + " VN??");
        }
        layoutTT_tvTongTienThanhToan.setText(formatter.format(tongTienThanhToan) + " VN??");
    }

}
