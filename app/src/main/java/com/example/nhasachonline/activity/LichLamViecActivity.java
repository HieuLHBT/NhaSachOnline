package com.example.nhasachonline.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.LichLamViecRecyclerViewAdapter;
import com.example.nhasachonline.data_model.ChamCong;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.LichLamViec;
import com.example.nhasachonline.tools.SharePreferences;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class LichLamViecActivity extends AppCompatActivity {
    private TextView layoutLLV_btnTroVe, layoutLLV_btnSau, layoutLLV_btnTruoc, layoutLLV_tvThangNam;
    private Button layouLLV_btnXacNhanNghiCoPhep, layouLLV_btnDangKyLichLamViec, layouLLV_btnHuyDangKyLamViec;
    private RecyclerView layoutLLV_rvLich;
    private ArrayList<LichLamViec> danhSachNgay = new ArrayList<>();

    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();
    private LichLamViecRecyclerViewAdapter adapter;

    private String maNhanVien;
    private LocalDate duLieuNgayHienTai;
    private String ngayDangChon = "";
    private String thangNamDangChon;
    private int viTriNgayDangChon = -1;
    private String trangThaiDangChon;
    private String caDangKy = "";

    private ArrayList<ChamCong> chamCongs = new ArrayList<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM, yyyy");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lichlamviec_layout);

        maNhanVien = "nv1";
        duLieuNgayHienTai = LocalDate.now();
        thangNamDangChon = duLieuNgayHienTai.format(formatter);

        layoutLLV_rvLich = findViewById(R.id.layoutLLV_rvLich);
        layoutLLV_btnTroVe = findViewById(R.id.layoutLLV_btnTroVe);
        layoutLLV_btnSau = findViewById(R.id.layoutLLV_btnSau);
        layoutLLV_btnTruoc = findViewById(R.id.layoutLLV_btnTruoc);
        layoutLLV_tvThangNam = findViewById(R.id.layoutLLV_tvThangNam);
        layouLLV_btnXacNhanNghiCoPhep = findViewById(R.id.layouLLV_btnXacNhanNghiCoPhep);
        layouLLV_btnDangKyLichLamViec = findViewById(R.id.layouLLV_btnDangKyLichLamViec);
        layouLLV_btnHuyDangKyLamViec = findViewById(R.id.layouLLV_btnHuyDangKyLamViec);

        layoutLLV_btnSau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duLieuNgayHienTai = duLieuNgayHienTai.plusMonths(1);
                thangNamDangChon = duLieuNgayHienTai.format(formatter);
                hienThiLich();
            }
        });

        layoutLLV_btnTruoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duLieuNgayHienTai = duLieuNgayHienTai.minusMonths(1);
                thangNamDangChon = duLieuNgayHienTai.format(formatter);
                hienThiLich();
            }
        });

        layoutLLV_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layouLLV_btnXacNhanNghiCoPhep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ngayDangChon != "" && viTriNgayDangChon != -1) {
                    if (!trangThaiDangChon.equalsIgnoreCase("Không phép")) {
                        AlertDialog.Builder b = new AlertDialog.Builder(LichLamViecActivity.this);
                        b.setTitle("CẢNH BÁO!");
                        b.setMessage("Chỉ có thể xác nhận nghỉ có phép ở những ngày trạng thái không phép!");
                        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        AlertDialog al = b.create();
                        al.show();
                    } else {
                        AlertDialog.Builder b = new AlertDialog.Builder(LichLamViecActivity.this);
                        b.setTitle("THÔNG BÁO");
                        b.setMessage("Xác nhận nghỉ có phép?");
                        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String ngayXacNhan = ngayDangChon.replaceAll("/", "");
                                viTriNgayDangChon = -1;
                                ngayDangChon = "";
                                fireBaseNhaSachOnline.xacNhanNghiCoPhep(maNhanVien, ngayXacNhan);
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
                } else {
                    AlertDialog.Builder b = new AlertDialog.Builder(LichLamViecActivity.this);
                    b.setTitle("CẢNH BÁO!");
                    b.setMessage("Không có ngày nào được chọn!");
                    b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog al = b.create();
                    al.show();
                }
            }
        });

        layouLLV_btnDangKyLichLamViec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ngayDangChon != "" && viTriNgayDangChon != -1) {
                    if (!trangThaiDangChon.equalsIgnoreCase("Đăng ký")) {
                        AlertDialog.Builder b = new AlertDialog.Builder(LichLamViecActivity.this);
                        b.setTitle("CẢNH BÁO!");
                        b.setMessage("Vui lòng chỉ chọn ngày có trạng thái ngày đăng ký!");
                        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        AlertDialog al = b.create();
                        al.show();
                    } else {
                        AlertDialog.Builder b = new AlertDialog.Builder(LichLamViecActivity.this);
                        b.setTitle("XÁC NHẬN ĐĂNG KÝ CA LÀM VIỆC");
                        String[] ca = {"Làm ca 1", "Làm ca 2", "Làm cả ngày"};
                        caDangKy = ca[0];
                        b.setSingleChoiceItems(ca, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                caDangKy = ca[which];
                            }
                        });
                        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String ngayDangKy = ngayDangChon.replaceAll("/", "");
                                fireBaseNhaSachOnline.dangKyLamViec(maNhanVien, ngayDangKy, ngayDangChon, caDangKy);
                                viTriNgayDangChon = -1;
                                ngayDangChon = "";
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
                } else {
                    AlertDialog.Builder b = new AlertDialog.Builder(LichLamViecActivity.this);
                    b.setTitle("CẢNH BÁO!");
                    b.setMessage("Không có ngày nào được chọn!");
                    b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog al = b.create();
                    al.show();
                }
            }
        });

        layouLLV_btnHuyDangKyLamViec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ngayDangChon != "" && viTriNgayDangChon != -1) {
                    DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String ChuoiNgayHienTai = duLieuNgayHienTai.format(fm);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date ngayHienTai = null;
                    try {
                        ngayHienTai = sdf.parse(ChuoiNgayHienTai);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Date ngayDangChonHienTai = null;
                    try {
                        ngayDangChonHienTai = sdf.parse(ngayDangChon);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (!trangThaiDangChon.equalsIgnoreCase("Chờ duyệt") || ngayHienTai.compareTo(ngayDangChonHienTai) > 0) {
                        AlertDialog.Builder b = new AlertDialog.Builder(LichLamViecActivity.this);
                        b.setTitle("CẢNH BÁO!");
                        b.setMessage("Chỉ có thể hủy ngày đã đăng ký làm việc ở trạng thái chờ duyệt!");
                        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        AlertDialog al = b.create();
                        al.show();
                    } else {
                        AlertDialog.Builder b = new AlertDialog.Builder(LichLamViecActivity.this);
                        b.setTitle("THÔNG BÁO");
                        b.setMessage("Xác nhận hủy đăng ký ngày làm việc?");
                        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String ngayXacNhan = ngayDangChon.replaceAll("/", "");
                                viTriNgayDangChon = -1;
                                ngayDangChon = "";
                                fireBaseNhaSachOnline.huyDangKyLamViec(maNhanVien, ngayXacNhan);
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
                } else {
                    AlertDialog.Builder b = new AlertDialog.Builder(LichLamViecActivity.this);
                    b.setTitle("CẢNH BÁO!");
                    b.setMessage("Không có ngày nào được chọn!");
                    b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog al = b.create();
                    al.show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String ChuoiNgayHienTai = duLieuNgayHienTai.format(fm);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date ngayHienTai = null;
        try {
            ngayHienTai = sdf.parse(ChuoiNgayHienTai);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        fireBaseNhaSachOnline.hienThiLichLamViec(ngayHienTai, maNhanVien, chamCongs, this);
    }

    public void hienThiLich() {
        layoutLLV_tvThangNam.setText("Tháng " + duLieuNgayHienTai.format(formatter));
        danhSachNgay = layDanhSachNgay(duLieuNgayHienTai);

        for (ChamCong chamCong : chamCongs) {
            String[] ngayThangNam = chamCong.getNgay().split("/");
            String ngay = String.valueOf((Integer.valueOf(ngayThangNam[0])));
            String thangNam = ngayThangNam[1] + ", " + ngayThangNam[2];

            for (LichLamViec lichLamViec : danhSachNgay) {
                if (thangNam.equalsIgnoreCase(thangNamDangChon) && ngay.equalsIgnoreCase(lichLamViec.getNgay())) {
                    if (chamCong.getTrangThaiPhanCong().equalsIgnoreCase("Chờ duyệt")) {
                        lichLamViec.setTrangThai("Chờ duyệt");
                        break;
                    } else if (chamCong.getTrangThaiPhanCong().equalsIgnoreCase("Đã phân công")
                            && (chamCong.getNghiKhongPhep().equalsIgnoreCase("ca1")
                            || chamCong.getNghiKhongPhep().equalsIgnoreCase("ca2")
                            || chamCong.getNghiKhongPhep().equalsIgnoreCase("ca1, ca2"))) {
                        lichLamViec.setTrangThai("Không phép");
                        break;
                    } else if (chamCong.getTrangThaiPhanCong().equalsIgnoreCase("Đã phân công")
                            && (chamCong.getNghiPhep().equalsIgnoreCase("ca1")
                            || chamCong.getNghiPhep().equalsIgnoreCase("ca2")
                            || chamCong.getNghiPhep().equalsIgnoreCase("ca1, ca2"))) {
                        lichLamViec.setTrangThai("Có phép");
                        break;
                    } else if (chamCong.getTrangThaiPhanCong().equalsIgnoreCase("Đã phân công")) {
                        lichLamViec.setTrangThai("Làm việc");
                        break;
                    }
                }
            }
        }

        adapter = new LichLamViecRecyclerViewAdapter(LichLamViecActivity.this, R.layout.lichlamviec_item, danhSachNgay);
        GridLayoutManager layoutManager = new GridLayoutManager(LichLamViecActivity.this, 7);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        layoutLLV_rvLich.setLayoutManager(layoutManager);
        layoutLLV_rvLich.setAdapter(adapter);

        adapter.setOnItemClickListener(new LichLamViecRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
//                String message = "Ngày " + danhSachNgay.get(position).getNgay() + ", Tháng " + duLieuNgayHienTai.format(formatter);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
                String ngay = danhSachNgay.get(position).getNgay();
                if (ngay.length() == 1) {
                    ngay = "0" + ngay;
                }
                if (viTriNgayDangChon == -1) {
                    viTriNgayDangChon = position;
                    trangThaiDangChon = danhSachNgay.get(position).getTrangThai();
                    danhSachNgay.get(position).setTrangThai("Đang chọn");
                    adapter.notifyDataSetChanged();
                    ngayDangChon = ngay + "/" + duLieuNgayHienTai.format(formatter);
                } else {
                    if (viTriNgayDangChon == position) {//Tap on the same item
                        viTriNgayDangChon = -1;
                        danhSachNgay.get(position).setTrangThai(trangThaiDangChon);
                        adapter.notifyDataSetChanged();
                        ngayDangChon = "";
                    } else {// Tap on other item
                        danhSachNgay.get(viTriNgayDangChon).setTrangThai(trangThaiDangChon);
                        viTriNgayDangChon = position;
                        trangThaiDangChon = danhSachNgay.get(position).getTrangThai();
                        danhSachNgay.get(position).setTrangThai("Đang chọn");
                        adapter.notifyDataSetChanged();
                        ngayDangChon = ngay + "/" + duLieuNgayHienTai.format(formatter);
                    }
                }
            }
        });
    }

    private ArrayList<LichLamViec> layDanhSachNgay(LocalDate duLieuNgay) {
        ArrayList<LichLamViec> danhSachNgay = new ArrayList<>();
        YearMonth thangNam = YearMonth.from(duLieuNgay);
        int tongNgayTrongThang = thangNam.lengthOfMonth();
        LocalDate ngayDauTienTrongThang = duLieuNgay.withDayOfMonth(1);
        int ngayTrongTuan = ngayDauTienTrongThang.getDayOfWeek().getValue() - 1;
        int thu7 = 6;
        int chuNhat = 7;
        for (int i = 1; i <= 42; i++) {
            if (i <= ngayTrongTuan || i > tongNgayTrongThang + ngayTrongTuan) {
                danhSachNgay.add(new LichLamViec(""));
            } else {
                if (thu7 == i) {
                    thu7 += 7;
                    danhSachNgay.add(new LichLamViec(String.valueOf(i - ngayTrongTuan)));
                } else if (chuNhat == i) {
                    chuNhat += 7;
                    danhSachNgay.add(new LichLamViec(String.valueOf(i - ngayTrongTuan)));
                } else {
                    danhSachNgay.add(new LichLamViec(String.valueOf(i - ngayTrongTuan), "Đăng ký"));
                }
            }
        }
        return danhSachNgay;
    }
}
