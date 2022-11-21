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

        // Gán dữ liệu
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
                b.setTitle("CẢNH BÁO!");
                b.setMessage("Xác nhận hủy thanh toán?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        finish();
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
            }
        });

        layoutCTTDDH_btnXacNhanThanhToanOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ChiTietTheoDoiDonHangActivity.this);
                b.setTitle("THÔNG BÁO!");
                b.setMessage("Xác nhận đặt hàng?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

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
            }
        });

        layoutCTTDDH_btnXacNhanThanhToanOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ChiTietTheoDoiDonHangActivity.this);
                b.setTitle("THÔNG BÁO");
                b.setMessage("Bạn đang xác nhận đã chuyển tiền thanh toán đơn hàng này!");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fireBaseNhaSachOnline.xacNhanDaThanhToanOnline(maDonHang, ChiTietTheoDoiDonHangActivity.this);
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
            }
        });

        layoutCTTDDH_btnXacNhanHoanTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ChiTietTheoDoiDonHangActivity.this);
                b.setTitle("THÔNG BÁO");
                b.setMessage("Bạn xác nhận đã nhận được hoàn tiền từ đơn hàng!");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fireBaseNhaSachOnline.xacNhanNhanHoannTien(maDonHang, ChiTietTheoDoiDonHangActivity.this);
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
            }
        });

        layoutCTTDDH_btnHuyDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ChiTietTheoDoiDonHangActivity.this);
                b.setTitle("CẢNH BÁO");
                b.setMessage("Bạn xác nhận hủy đơn hàng này không!");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (chiTietTheoDoiDonHang_thongTin.getTrangThaiNhanTienQL().equalsIgnoreCase("Đã xác nhận")) {
                            fireBaseNhaSachOnline.khachHangHuyDonHangOnline(maDonHang, ChiTietTheoDoiDonHangActivity.this);
                        } else {
                            fireBaseNhaSachOnline.khachHangHuyDonHang(maDonHang, ChiTietTheoDoiDonHangActivity.this);
                        }
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
            }
        });
    }

    public void thongBaoHoanTien() {
        AlertDialog.Builder b = new AlertDialog.Builder(ChiTietTheoDoiDonHangActivity.this);
        b.setTitle("THÔNG BÁO");
        b.setMessage("Đơn hàng đã hủy thành công! Vui lòng đợi xác nhận chi trả tiền thanh toán từ quản lý, bạn có thể kiểm tra trạng thái trong lịch sử hoàn tiền (Lưu ý: khi nhận được tiền, vui lòng xác nhận!)");
        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
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
        layoutCTTDDH_tvTongTien.setText(formatter.format(chiTietTheoDoiDonHang_thongTin.getTongTienHang()) + " VNĐ");
        layoutCTTDDH_tvPhiVanChuyen.setText(formatter.format(chiTietTheoDoiDonHang_thongTin.getPhiVanChuyen()) + " VNĐ");
        layoutCTTDDH_tvGiamGia.setText(formatter.format(chiTietTheoDoiDonHang_thongTin.getGiamGia()) + " VNĐ");
        layoutCTTDDH_tvTongTienThanhToan.setText(formatter.format(chiTietTheoDoiDonHang_thongTin.getTongTienThanhToan()) + " VNĐ");
        if (chiTietTheoDoiDonHang_thongTin.getTrangThaiDon().equalsIgnoreCase("Hủy") || chiTietTheoDoiDonHang_thongTin.getTrangThaiDon().equalsIgnoreCase("Thành công")) {
            layoutCTTDDH_llButton.setVisibility(View.GONE);
        } else {
            layoutCTTDDH_llButton.setVisibility(View.VISIBLE);
            if (chiTietTheoDoiDonHang_thongTin.getTrangThaiChuyenTienKH().equalsIgnoreCase("Đang xử lý")) {
                layoutCTTDDH_btnXacNhanThanhToanOnline.setVisibility(View.VISIBLE);
                layoutCTTDDH_btnHuyDonHang.setVisibility(View.GONE);
            } else {
                layoutCTTDDH_btnXacNhanThanhToanOnline.setVisibility(View.GONE);
                layoutCTTDDH_btnHuyDonHang.setVisibility(View.VISIBLE);
            }
            if (!chiTietTheoDoiDonHang_thongTin.getTrangThaiNhanTienKH().equalsIgnoreCase("") && !chiTietTheoDoiDonHang_thongTin.getTrangThaiTraTienQL().equalsIgnoreCase("") && !chiTietTheoDoiDonHang_thongTin.getTrangThaiDon().equalsIgnoreCase("Hủy")) {
                layoutCTTDDH_btnXacNhanHoanTien.setVisibility(View.VISIBLE);
                layoutCTTDDH_btnHuyDonHang.setVisibility(View.GONE);
            } else {
                layoutCTTDDH_btnXacNhanHoanTien.setVisibility(View.GONE);
                layoutCTTDDH_btnHuyDonHang.setVisibility(View.VISIBLE);
            }
        }
    }

}
