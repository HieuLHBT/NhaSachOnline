package com.example.nhasachonline.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.ChiTietTheoDoiDonHangRecyclerViewAdapter;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.ChiTietTheoDoiDonHang;
import com.example.nhasachonline.item.ChiTietTheoDoiDonHang_ThongTin;
import com.example.nhasachonline.tools.SharePreferences;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietTheoDoiDonHangActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();
    private String maDonHang;
    private String maKhachHang;

    private ArrayList<ChiTietTheoDoiDonHang> chiTietTheoDoiDonHangs = new ArrayList<>();
    private ChiTietTheoDoiDonHangRecyclerViewAdapter adapter;

    private TextView layoutCTTDDH_btnTroVe, layoutCTTDDH_tvMaDonHang, layoutCTTDDH_tvTenNhanVienGiao, layoutCTTDDH_tvThoiGianLap, layoutCTTDDH_tvThoiGianGiao, layoutCTTDDH_tvHinhThucGiao, layoutCTTDDH_tvPhuongThucThanhToan, layoutCTTDDH_tvTongTien, layoutCTTDDH_tvPhiVanChuyen, layoutCTTDDH_tvGiamGia, layoutCTTDDH_tvTongTienThanhToan;
    private Button layoutCTTDDH_btnXacNhanThanhToanOnline, layoutCTTDDH_btnHuyDonHang, layoutCTTDDH_btnXacNhanHoanTien;
    private LinearLayout layoutCTTDDH_llButton;
    private DecimalFormat formatter = new DecimalFormat("#,###,###");

    private ChiTietTheoDoiDonHang_ThongTin chiTietTheoDoiDonHang_thongTin = new ChiTietTheoDoiDonHang_ThongTin();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitiettheodoidonhang_layout);

        maDonHang = getIntent().getStringExtra("maDonHang");
        maKhachHang = "kh1";

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutCTTDDH_rvDanhSach);
        layoutCTTDDH_btnTroVe = findViewById(R.id.layoutCTTDDH_btnTroVe);
        layoutCTTDDH_btnXacNhanThanhToanOnline = findViewById(R.id.layoutCTTDDH_btnXacNhanThanhToanOnline);
        layoutCTTDDH_btnHuyDonHang = findViewById(R.id.layoutCTTDDH_btnHuyDonHang);
        layoutCTTDDH_btnXacNhanHoanTien = findViewById(R.id.layoutCTTDDH_btnXacNhanHoanTien);
        layoutCTTDDH_tvMaDonHang = findViewById(R.id.layoutCTTDDH_tvMaDonHang);
        layoutCTTDDH_tvTenNhanVienGiao = findViewById(R.id.layoutCTTDDH_tvTenNhanVienGiao);
        layoutCTTDDH_tvThoiGianLap = findViewById(R.id.layoutCTTDDH_tvThoiGianLap);
        layoutCTTDDH_tvThoiGianGiao = findViewById(R.id.layoutCTTDDH_tvThoiGianGiao);
        layoutCTTDDH_tvHinhThucGiao = findViewById(R.id.layoutCTTDDH_tvHinhThucGiao);
        layoutCTTDDH_tvPhuongThucThanhToan = findViewById(R.id.layoutCTTDDH_tvPhuongThucThanhToan);
        layoutCTTDDH_tvTongTien = findViewById(R.id.layoutCTTDDH_tvTongTien);
        layoutCTTDDH_tvPhiVanChuyen = findViewById(R.id.layoutCTTDDH_tvPhiVanChuyen);
        layoutCTTDDH_tvGiamGia = findViewById(R.id.layoutCTTDDH_tvGiamGia);
        layoutCTTDDH_tvTongTienThanhToan = findViewById(R.id.layoutCTTDDH_tvTongTienThanhToan);
        layoutCTTDDH_llButton = findViewById(R.id.layoutCTTDDH_llButton);

        // G??n d??? li???u
        layoutCTTDDH_tvMaDonHang.setText(maDonHang);

        adapter = new ChiTietTheoDoiDonHangRecyclerViewAdapter(this, R.layout.chitiettheodoidonhang_item, chiTietTheoDoiDonHangs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);

        layoutCTTDDH_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layoutCTTDDH_btnHuyDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ChiTietTheoDoiDonHangActivity.this);
                b.setTitle("C???NH B??O!");
                b.setMessage("X??c nh???n h???y thanh to??n?");
                b.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

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

        layoutCTTDDH_btnXacNhanThanhToanOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ChiTietTheoDoiDonHangActivity.this);
                b.setTitle("TH??NG B??O!");
                b.setMessage("X??c nh???n ?????t h??ng?");
                b.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

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

        layoutCTTDDH_btnXacNhanThanhToanOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ChiTietTheoDoiDonHangActivity.this);
                b.setTitle("TH??NG B??O");
                b.setMessage("B???n ??ang x??c nh???n ???? chuy???n ti???n thanh to??n ????n h??ng n??y!");
                b.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fireBaseNhaSachOnline.xacNhanDaThanhToanOnline(maDonHang, ChiTietTheoDoiDonHangActivity.this);
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

        layoutCTTDDH_btnXacNhanHoanTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ChiTietTheoDoiDonHangActivity.this);
                b.setTitle("TH??NG B??O");
                b.setMessage("B???n x??c nh???n ???? nh???n ???????c ho??n ti???n t??? ????n h??ng!");
                b.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fireBaseNhaSachOnline.xacNhanNhanHoannTien(maDonHang, ChiTietTheoDoiDonHangActivity.this);
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

        layoutCTTDDH_btnHuyDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ChiTietTheoDoiDonHangActivity.this);
                b.setTitle("C???NH B??O");
                b.setMessage("B???n x??c nh???n h???y ????n h??ng n??y kh??ng!");
                b.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (chiTietTheoDoiDonHang_thongTin.getTrangThaiNhanTienQL().equalsIgnoreCase("???? x??c nh???n")) {
                            fireBaseNhaSachOnline.khachHangHuyDonHangOnline(maDonHang, ChiTietTheoDoiDonHangActivity.this);
                        } else {
                            fireBaseNhaSachOnline.khachHangHuyDonHang(maDonHang, ChiTietTheoDoiDonHangActivity.this);
                        }
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

    public void thongBaoHoanTien() {
        AlertDialog.Builder b = new AlertDialog.Builder(ChiTietTheoDoiDonHangActivity.this);
        b.setTitle("TH??NG B??O");
        b.setMessage("????n h??ng ???? h???y th??nh c??ng! Vui l??ng ?????i x??c nh???n chi tr??? ti???n thanh to??n t??? qu???n l??, b???n c?? th??? ki???m tra tr???ng th??i trong l???ch s??? ho??n ti???n (L??u ??: khi nh???n ???????c ti???n, vui l??ng x??c nh???n!)");
        b.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog al = b.create();
        al.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBaseNhaSachOnline.hienThiThongTinChiTietTheoDoiDonDang(maKhachHang, maDonHang, chiTietTheoDoiDonHangs, chiTietTheoDoiDonHang_thongTin, adapter, this);
    }

    public void hienThi() {
        layoutCTTDDH_tvTenNhanVienGiao.setText(chiTietTheoDoiDonHang_thongTin.getTenNhanVienGiaoHang());
        layoutCTTDDH_tvThoiGianLap.setText(chiTietTheoDoiDonHang_thongTin.getThoiGianLap());
        layoutCTTDDH_tvThoiGianGiao.setText(chiTietTheoDoiDonHang_thongTin.getThoiGianGiao());
        layoutCTTDDH_tvHinhThucGiao.setText(chiTietTheoDoiDonHang_thongTin.getHinhThucGiao());
        layoutCTTDDH_tvPhuongThucThanhToan.setText(chiTietTheoDoiDonHang_thongTin.getPhuongThucThanhToan());
        layoutCTTDDH_tvTongTien.setText(formatter.format(chiTietTheoDoiDonHang_thongTin.getTongTienHang()) + " VN??");
        layoutCTTDDH_tvPhiVanChuyen.setText(formatter.format(chiTietTheoDoiDonHang_thongTin.getPhiVanChuyen()) + " VN??");
        layoutCTTDDH_tvGiamGia.setText(formatter.format(chiTietTheoDoiDonHang_thongTin.getGiamGia()) + " VN??");
        layoutCTTDDH_tvTongTienThanhToan.setText(formatter.format(chiTietTheoDoiDonHang_thongTin.getTongTienThanhToan()) + " VN??");
        if (chiTietTheoDoiDonHang_thongTin.getTrangThaiDon().equalsIgnoreCase("H???y") || chiTietTheoDoiDonHang_thongTin.getTrangThaiDon().equalsIgnoreCase("Th??nh c??ng")) {
            layoutCTTDDH_llButton.setVisibility(View.GONE);
        } else {
            layoutCTTDDH_llButton.setVisibility(View.VISIBLE);
            if (chiTietTheoDoiDonHang_thongTin.getTrangThaiChuyenTienKH().equalsIgnoreCase("??ang x??? l??")) {
                layoutCTTDDH_btnXacNhanThanhToanOnline.setVisibility(View.VISIBLE);
                layoutCTTDDH_btnHuyDonHang.setVisibility(View.GONE);
            } else {
                layoutCTTDDH_btnXacNhanThanhToanOnline.setVisibility(View.GONE);
                layoutCTTDDH_btnHuyDonHang.setVisibility(View.VISIBLE);
            }
            if (!chiTietTheoDoiDonHang_thongTin.getTrangThaiNhanTienKH().equalsIgnoreCase("") && !chiTietTheoDoiDonHang_thongTin.getTrangThaiTraTienQL().equalsIgnoreCase("") && !chiTietTheoDoiDonHang_thongTin.getTrangThaiDon().equalsIgnoreCase("H???y")) {
                layoutCTTDDH_btnXacNhanHoanTien.setVisibility(View.VISIBLE);
                layoutCTTDDH_btnHuyDonHang.setVisibility(View.GONE);
            } else {
                layoutCTTDDH_btnXacNhanHoanTien.setVisibility(View.GONE);
                layoutCTTDDH_btnHuyDonHang.setVisibility(View.VISIBLE);
            }
        }
    }

}
