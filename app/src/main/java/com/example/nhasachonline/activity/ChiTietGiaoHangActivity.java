package com.example.nhasachonline.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.ChiTietGiaoHangRecyclerViewAdapter;
import com.example.nhasachonline.data_model.DonHang;
import com.example.nhasachonline.data_model.KhachHang;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.ChiTietGiaoHang;
import com.example.nhasachonline.tools.SharePreferences;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietGiaoHangActivity extends AppCompatActivity {
    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();
    private String maDonHang;
    private String maKhachHang;

    private KhachHang khachHang = new KhachHang();
    private DonHang donHang = new DonHang();

    private ArrayList<ChiTietGiaoHang> chiTietGiaoHangs = new ArrayList<>();
    private ChiTietGiaoHangRecyclerViewAdapter adapter;

    private TextView layoutCTGH_txtMaDonHang;
    private TextView layoutCTGH_txtTenNVGiaoHang;
    private TextView layoutCTGH_txtThoiGianDuKienGiao;
    private TextView layoutCTGH_txtThoiGianDat;
    private TextView layoutCTGH_txtPhuongThucThanhToan;
    private TextView layoutCTGH_txtTongTienHang;
    private TextView layoutCTGH_txtPhiVanChuyen;
    private TextView layoutCTGH_txtGiamGia;
    private TextView layoutCTGH_txtTongTienThanhToan;

    private Button layoutCTGH_btnXacNhanDonHang;
    private Button layoutCTGH_btnHuyDonHang;
    private Button layoutCTGH_btnTroVe;
    DecimalFormat formatter = new DecimalFormat("#,###,###");

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.chitietgiaohang_layout);
        maDonHang = sharePreferences.layMaDonHang(this);
        maKhachHang = sharePreferences.getMaKhachHang(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutCTGH_rvChiTietGiaoHang);

        layoutCTGH_txtMaDonHang = findViewById(R.id.layoutCTGH_txtMaDonHang);
        layoutCTGH_txtTenNVGiaoHang = findViewById(R.id.layoutCTGH_txtTenNVGiaoHang);
        layoutCTGH_txtThoiGianDuKienGiao = findViewById(R.id.layoutCTGH_txtThoiGianDuKienGiao);
        layoutCTGH_txtThoiGianDat = findViewById(R.id.layoutCTGH_txtThoiGianDat);
        layoutCTGH_txtPhuongThucThanhToan = findViewById(R.id.layoutCTGH_txtPhuongThucThanhToan);
        layoutCTGH_txtTongTienHang = findViewById(R.id.layoutCTGH_txtTongTienHang);
        layoutCTGH_txtPhiVanChuyen = findViewById(R.id.layoutCTGH_txtPhiVanChuyen);
        layoutCTGH_txtGiamGia = findViewById(R.id.layoutCTGH_txtGiamGia);
        layoutCTGH_txtTongTienThanhToan = findViewById(R.id.layoutCTGH_txtTongTienThanhToan);
        layoutCTGH_btnXacNhanDonHang = findViewById(R.id.layoutCTGH_btnXacNhanDonHang);
        layoutCTGH_btnHuyDonHang = findViewById(R.id.layoutCTGH_btnHuyDonHang);
        layoutCTGH_btnTroVe = findViewById(R.id.layoutCTGH_btnTroVe);


        // Gán dữ liệu
        layoutCTGH_txtMaDonHang.setText("dh1");

        Log.d("Test", maDonHang + "");
        fireBase.hienThiDonHang("dh1", this);

        adapter = new ChiTietGiaoHangRecyclerViewAdapter(this, R.layout.chitietgiaohang_item, chiTietGiaoHangs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        fireBase.hienThiItemChiTietGiaoHang("dh1", chiTietGiaoHangs, adapter, this);

        layoutCTGH_btnXacNhanDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ChiTietGiaoHang> dsChiTietGiaoHang = new ArrayList<>();
                ThongBaoXacNhanDonHang(dsChiTietGiaoHang);
                //Toast.makeText(getApplicationContext(), "Xác nhận đơn hàng thành công!", Toast.LENGTH_SHORT).show();

            }
        });
        layoutCTGH_btnHuyDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ChiTietGiaoHang> dsChiTietGiaoHang = new ArrayList<>();
                ThongBaoHuyDonHang(dsChiTietGiaoHang);
                //Toast.makeText(getApplicationContext(), "Huỷ đơn hàng thành công!", Toast.LENGTH_SHORT).show();

            }
        });
        layoutCTGH_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void hienThiDonHang(){
        layoutCTGH_txtThoiGianDuKienGiao.setText(donHang.getThoiGianGiao());
        layoutCTGH_txtThoiGianDat.setText(donHang.getThoiGianLap());
        layoutCTGH_txtMaDonHang.setText(donHang.getMaDonHang());

    }
    public void ThongBaoXacNhanDonHang(ArrayList<ChiTietGiaoHang> chiTietGiaoHangs) {
        AlertDialog.Builder b = new AlertDialog.Builder(ChiTietGiaoHangActivity.this);
        b.setTitle("Xác nhận nhận đơn hàng");
        b.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                Intent intent = new Intent(ChiTietGiaoHangActivity.this, LichSuMuaHangActivity.class);
        //        startActivity(intent);
            }
        });
        b.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog al = b.create();
        al.show();
    }
    public void ThongBaoHuyDonHang(ArrayList<ChiTietGiaoHang> chiTietGiaoHangs) {
        AlertDialog.Builder b = new AlertDialog.Builder(ChiTietGiaoHangActivity.this);
        b.setTitle("Huỷ đơn hàng");
        b.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Intent intent = new Intent(ChiTietGiaoHangActivity.this, LichSuMuaHangActivity.class);
                //startActivity(intent);
            }
        });
        b.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog al = b.create();
        al.show();
    }

}
