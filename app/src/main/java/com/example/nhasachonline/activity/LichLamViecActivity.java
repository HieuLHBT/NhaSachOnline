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
                    if (!trangThaiDangChon.equalsIgnoreCase("Kh??ng ph??p")) {
                        AlertDialog.Builder b = new AlertDialog.Builder(LichLamViecActivity.this);
                        b.setTitle("C???NH B??O!");
                        b.setMessage("Ch??? c?? th??? x??c nh???n ngh??? c?? ph??p ??? nh???ng ng??y tr???ng th??i kh??ng ph??p!");
                        b.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        AlertDialog al = b.create();
                        al.show();
                    } else {
                        AlertDialog.Builder b = new AlertDialog.Builder(LichLamViecActivity.this);
                        b.setTitle("TH??NG B??O");
                        b.setMessage("X??c nh???n ngh??? c?? ph??p?");
                        b.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String ngayXacNhan = ngayDangChon.replaceAll("/", "");
                                viTriNgayDangChon = -1;
                                ngayDangChon = "";
                                fireBaseNhaSachOnline.xacNhanNghiCoPhep(maNhanVien, ngayXacNhan);
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
                } else {
                    AlertDialog.Builder b = new AlertDialog.Builder(LichLamViecActivity.this);
                    b.setTitle("C???NH B??O!");
                    b.setMessage("Kh??ng c?? ng??y n??o ???????c ch???n!");
                    b.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
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
                    if (trangThaiDangChon.equalsIgnoreCase("????ng k??") && ngayHienTai.compareTo(ngayDangChonHienTai) <= 0) {
                        AlertDialog.Builder b = new AlertDialog.Builder(LichLamViecActivity.this);
                        b.setTitle("X??C NH???N ????NG K?? CA L??M VI???C");
                        String[] ca = {"L??m ca 1", "L??m ca 2", "L??m c??? ng??y"};
                        caDangKy = ca[0];
                        b.setSingleChoiceItems(ca, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                caDangKy = ca[which];
                            }
                        });
                        b.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String ngayDangKy = ngayDangChon.replaceAll("/", "");
                                fireBaseNhaSachOnline.dangKyLamViec(maNhanVien, ngayDangKy, ngayDangChon, caDangKy);
                                viTriNgayDangChon = -1;
                                ngayDangChon = "";
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
                    } else {
                        AlertDialog.Builder b = new AlertDialog.Builder(LichLamViecActivity.this);
                        b.setTitle("C???NH B??O!");
                        b.setMessage("Kh??ng ???????c ????ng k?? nh???ng ng??y tr?????c, vui l??ng ch??? ????ng k?? ng??y hi???n t???i ho???c ng??y sau c?? tr???ng th??i ????ng k??!");
                        b.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
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
                    b.setTitle("C???NH B??O!");
                    b.setMessage("Kh??ng c?? ng??y n??o ???????c ch???n!");
                    b.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
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
                    if (!trangThaiDangChon.equalsIgnoreCase("Ch??? duy???t") || ngayHienTai.compareTo(ngayDangChonHienTai) > 0) {
                        AlertDialog.Builder b = new AlertDialog.Builder(LichLamViecActivity.this);
                        b.setTitle("C???NH B??O!");
                        b.setMessage("Ch??? c?? th??? h???y ng??y ???? ????ng k?? l??m vi???c ??? tr???ng th??i ch??? duy???t!");
                        b.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        AlertDialog al = b.create();
                        al.show();
                    } else {
                        AlertDialog.Builder b = new AlertDialog.Builder(LichLamViecActivity.this);
                        b.setTitle("TH??NG B??O");
                        b.setMessage("X??c nh???n h???y ????ng k?? ng??y l??m vi???c?");
                        b.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String ngayXacNhan = ngayDangChon.replaceAll("/", "");
                                viTriNgayDangChon = -1;
                                ngayDangChon = "";
                                fireBaseNhaSachOnline.huyDangKyLamViec(maNhanVien, ngayXacNhan);
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
                } else {
                    AlertDialog.Builder b = new AlertDialog.Builder(LichLamViecActivity.this);
                    b.setTitle("C???NH B??O!");
                    b.setMessage("Kh??ng c?? ng??y n??o ???????c ch???n!");
                    b.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
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
        layoutLLV_tvThangNam.setText("Th??ng " + duLieuNgayHienTai.format(formatter));
        danhSachNgay = layDanhSachNgay(duLieuNgayHienTai);

        for (ChamCong chamCong : chamCongs) {
            String[] ngayThangNam = chamCong.getNgay().split("/");
            String ngay = String.valueOf((Integer.valueOf(ngayThangNam[0])));
            String thangNam = ngayThangNam[1] + ", " + ngayThangNam[2];

            for (LichLamViec lichLamViec : danhSachNgay) {
                if (thangNam.equalsIgnoreCase(thangNamDangChon) && ngay.equalsIgnoreCase(lichLamViec.getNgay())) {
                    if (chamCong.getTrangThaiPhanCong().equalsIgnoreCase("Ch??? duy???t")) {
                        lichLamViec.setTrangThai("Ch??? duy???t");
                        break;
                    } else if (chamCong.getTrangThaiPhanCong().equalsIgnoreCase("???? ph??n c??ng")
                            && (chamCong.getNghiKhongPhep().equalsIgnoreCase("ca1")
                            || chamCong.getNghiKhongPhep().equalsIgnoreCase("ca2")
                            || chamCong.getNghiKhongPhep().equalsIgnoreCase("ca1, ca2"))) {
                        lichLamViec.setTrangThai("Kh??ng ph??p");
                        break;
                    } else if (chamCong.getTrangThaiPhanCong().equalsIgnoreCase("???? ph??n c??ng")
                            && (chamCong.getNghiPhep().equalsIgnoreCase("ca1")
                            || chamCong.getNghiPhep().equalsIgnoreCase("ca2")
                            || chamCong.getNghiPhep().equalsIgnoreCase("ca1, ca2"))) {
                        lichLamViec.setTrangThai("C?? ph??p");
                        break;
                    } else if (chamCong.getTrangThaiPhanCong().equalsIgnoreCase("???? ph??n c??ng")) {
                        lichLamViec.setTrangThai("L??m vi???c");
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
//                String message = "Ng??y " + danhSachNgay.get(position).getNgay() + ", Th??ng " + duLieuNgayHienTai.format(formatter);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
                String ngay = danhSachNgay.get(position).getNgay();
                if (ngay.length() == 1) {
                    ngay = "0" + ngay;
                }
                if (viTriNgayDangChon == -1) {
                    viTriNgayDangChon = position;
                    trangThaiDangChon = danhSachNgay.get(position).getTrangThai();
                    danhSachNgay.get(position).setTrangThai("??ang ch???n");
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
                        danhSachNgay.get(position).setTrangThai("??ang ch???n");
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
        int chuNhat = 7;
        for (int i = 1; i <= 42; i++) {
            if (i <= ngayTrongTuan || i > tongNgayTrongThang + ngayTrongTuan) {
                danhSachNgay.add(new LichLamViec(""));
            } else {
                if (chuNhat == i) {
                    chuNhat += 7;
                    danhSachNgay.add(new LichLamViec(String.valueOf(i - ngayTrongTuan)));
                } else {
                    danhSachNgay.add(new LichLamViec(String.valueOf(i - ngayTrongTuan), "????ng k??"));
                }
            }
        }
        return danhSachNgay;
    }
}
