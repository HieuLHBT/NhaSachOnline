package com.example.nhasachonline.activity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.data_model.ChamCong;
import com.example.nhasachonline.data_model.QuanLy;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.LichLamViec;
import com.example.nhasachonline.item.ManHinhChinhQuanLy_ThongKeDoanhSo;
import com.example.nhasachonline.item.ManHinhChinhQuanLy_ThongKeDon;
import com.example.nhasachonline.tools.SharePreferences;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ManHinhChinhQuanLyActivity extends AppCompatActivity {
    private PieChart layoutMHCQL_pcBieuDoDonHang;
    private BarChart layoutMHCQL_bcBieuDoDoanhSo;
    private TextView layoutMHCQL_tvHoTen, layoutMHCQL_tvMaQuanLy, layoutMHCQL_tvChucVu, layoutMHCQL_tvSoNguoiCa1, layoutMHCQL_tvSoNguoiCa2;
    private Spinner layoutMHCQL_spnNgay;
    private ImageView layoutMHCQL_imgHinh;

    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();

    private ManHinhChinhQuanLy_ThongKeDon thongKeDon = new ManHinhChinhQuanLy_ThongKeDon();
    private String maQuanLy;
    private QuanLy quanLy = new QuanLy();
    private LocalDate duLieuNgayHienTai;
    private String ngayHienThi;
    private ArrayList<String> tuanHienTai = new ArrayList<>();
    private ManHinhChinhQuanLy_ThongKeDoanhSo thongKeDoanhSo = new ManHinhChinhQuanLy_ThongKeDoanhSo();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhchinh_quanly_layout);

        maQuanLy = "ql1";
        duLieuNgayHienTai = LocalDate.now();

        layoutMHCQL_pcBieuDoDonHang = findViewById(R.id.layoutMHCQL_pcBieuDoDonHang);
        layoutMHCQL_bcBieuDoDoanhSo = findViewById(R.id.layoutMHCQL_bcBieuDoDoanhSo);
        layoutMHCQL_tvHoTen = findViewById(R.id.layoutMHCQL_tvHoTen);
        layoutMHCQL_tvMaQuanLy = findViewById(R.id.layoutMHCQL_tvMaQuanLy);
        layoutMHCQL_tvChucVu = findViewById(R.id.layoutMHCQL_tvChucVu);
        layoutMHCQL_spnNgay = findViewById(R.id.layoutMHCQL_spnNgay);
        layoutMHCQL_imgHinh = findViewById(R.id.layoutMHCQL_imgHinh);
        layoutMHCQL_tvSoNguoiCa1 = findViewById(R.id.layoutMHCQL_tvSoNguoiCa1);
        layoutMHCQL_tvSoNguoiCa2 = findViewById(R.id.layoutMHCQL_tvSoNguoiCa2);

        hienThiNgay();

        // Thiet lap PieChart
        layoutMHCQL_pcBieuDoDonHang.setEntryLabelTextSize(0);
        layoutMHCQL_pcBieuDoDonHang.setHoleRadius(35f);
        layoutMHCQL_pcBieuDoDonHang.setTransparentCircleRadius(40f);
        layoutMHCQL_pcBieuDoDonHang.setDrawEntryLabels(true);
        layoutMHCQL_pcBieuDoDonHang.setDrawHoleEnabled(true);
        layoutMHCQL_pcBieuDoDonHang.setCenterTextSize(15);
        layoutMHCQL_pcBieuDoDonHang.getDescription().setEnabled(false);
        Legend legend = layoutMHCQL_pcBieuDoDonHang.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDrawInside(true);
        legend.setEnabled(true);

        // Thiet lap BarChart
        layoutMHCQL_bcBieuDoDoanhSo.getDescription().setText("Đơn vị: triệu đồng (VNĐ)");
        layoutMHCQL_bcBieuDoDoanhSo.getDescription().setPosition(230, 20);
        layoutMHCQL_bcBieuDoDoanhSo.getLegend().setEnabled(false);
        layoutMHCQL_bcBieuDoDoanhSo.getAxisRight().setEnabled(false);
        layoutMHCQL_bcBieuDoDoanhSo.setDrawGridBackground(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBaseNhaSachOnline.hienThiThongTinQuanLy(maQuanLy, quanLy, this);
    }

    public void hienThiQuanLy() {
        layoutMHCQL_tvHoTen.setText(quanLy.getTenQuanLy());
        layoutMHCQL_tvMaQuanLy.setText(quanLy.getMaQuanLy());
        if (quanLy.getNguoiDung().equalsIgnoreCase("quanly")) {
            layoutMHCQL_tvChucVu.setText("Quản lý");
        }
        StorageReference storageReference = FirebaseStorage.getInstance().getReference(quanLy.getHinhQuanLy());
        try {
            File file = null;
            if (quanLy.getHinhQuanLy().contains("png")) {
                file = File.createTempFile(quanLy.getHinhQuanLy().substring(0, quanLy.getHinhQuanLy().length() - 4), "png");
            } else if (quanLy.getHinhQuanLy().contains("jpg")) {
                file = File.createTempFile(quanLy.getHinhQuanLy().substring(0, quanLy.getHinhQuanLy().length() - 4), "jpg");
            }
            final File fileHinh = file;
            storageReference.getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    layoutMHCQL_imgHinh.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("onCancelled", "Lỗi!" + e.getMessage());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hienThiNgay() {
        tuanHienTai.add("");
        tuanHienTai.add("");
        tuanHienTai.add("");
        tuanHienTai.add("");
        tuanHienTai.add("");
        tuanHienTai.add("");
        tuanHienTai.add("");

        int thu = duLieuNgayHienTai.getDayOfWeek().getValue() - 1;
        DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ngayHienThi = duLieuNgayHienTai.format(fm);
        String ngay;
        switch (thu) {
            case 0:
                ngay = duLieuNgayHienTai.format(fm);
                tuanHienTai.set(0, ngay);
                ngay = duLieuNgayHienTai.plusDays(1).format(fm);
                tuanHienTai.set(1, ngay);
                ngay = duLieuNgayHienTai.plusDays(2).format(fm);
                tuanHienTai.set(2, ngay);
                ngay = duLieuNgayHienTai.plusDays(3).format(fm);
                tuanHienTai.set(3, ngay);
                ngay = duLieuNgayHienTai.plusDays(4).format(fm);
                tuanHienTai.set(4, ngay);
                ngay = duLieuNgayHienTai.plusDays(5).format(fm);
                tuanHienTai.set(5, ngay);
                ngay = duLieuNgayHienTai.plusDays(6).format(fm);
                tuanHienTai.set(6, ngay);
                break;
            case 1:
                ngay = duLieuNgayHienTai.minusDays(1).format(fm);
                tuanHienTai.set(0, ngay);
                ngay = duLieuNgayHienTai.format(fm);
                tuanHienTai.set(1, ngay);
                ngay = duLieuNgayHienTai.plusDays(1).format(fm);
                tuanHienTai.set(2, ngay);
                ngay = duLieuNgayHienTai.plusDays(2).format(fm);
                tuanHienTai.set(3, ngay);
                ngay = duLieuNgayHienTai.plusDays(3).format(fm);
                tuanHienTai.set(4, ngay);
                ngay = duLieuNgayHienTai.plusDays(4).format(fm);
                tuanHienTai.set(5, ngay);
                ngay = duLieuNgayHienTai.plusDays(5).format(fm);
                tuanHienTai.set(6, ngay);
                break;
            case 2:
                ngay = duLieuNgayHienTai.minusDays(2).format(fm);
                tuanHienTai.set(0, ngay);
                ngay = duLieuNgayHienTai.minusDays(1).format(fm);
                tuanHienTai.set(1, ngay);
                ngay = duLieuNgayHienTai.format(fm);
                tuanHienTai.set(2, ngay);
                ngay = duLieuNgayHienTai.plusDays(1).format(fm);
                tuanHienTai.set(3, ngay);
                ngay = duLieuNgayHienTai.plusDays(2).format(fm);
                tuanHienTai.set(4, ngay);
                ngay = duLieuNgayHienTai.plusDays(3).format(fm);
                tuanHienTai.set(5, ngay);
                ngay = duLieuNgayHienTai.plusDays(4).format(fm);
                tuanHienTai.set(5, ngay);
                break;
            case 3:
                ngay = duLieuNgayHienTai.minusDays(3).format(fm);
                tuanHienTai.set(0, ngay);
                ngay = duLieuNgayHienTai.minusDays(2).format(fm);
                tuanHienTai.set(1, ngay);
                ngay = duLieuNgayHienTai.minusDays(1).format(fm);
                tuanHienTai.set(2, ngay);
                ngay = duLieuNgayHienTai.format(fm);
                tuanHienTai.set(3, ngay);
                ngay = duLieuNgayHienTai.plusDays(1).format(fm);
                tuanHienTai.set(4, ngay);
                ngay = duLieuNgayHienTai.plusDays(2).format(fm);
                tuanHienTai.set(5, ngay);
                ngay = duLieuNgayHienTai.plusDays(3).format(fm);
                tuanHienTai.set(5, ngay);
                break;
            case 4:
                ngay = duLieuNgayHienTai.minusDays(4).format(fm);
                tuanHienTai.set(0, ngay);
                ngay = duLieuNgayHienTai.minusDays(3).format(fm);
                tuanHienTai.set(1, ngay);
                ngay = duLieuNgayHienTai.minusDays(2).format(fm);
                tuanHienTai.set(2, ngay);
                ngay = duLieuNgayHienTai.minusDays(1).format(fm);
                tuanHienTai.set(3, ngay);
                ngay = duLieuNgayHienTai.format(fm);
                tuanHienTai.set(4, ngay);
                ngay = duLieuNgayHienTai.plusDays(1).format(fm);
                tuanHienTai.set(5, ngay);
                ngay = duLieuNgayHienTai.plusDays(2).format(fm);
                tuanHienTai.set(5, ngay);
                break;
            case 5:
                ngay = duLieuNgayHienTai.minusDays(5).format(fm);
                tuanHienTai.set(0, ngay);
                ngay = duLieuNgayHienTai.minusDays(4).format(fm);
                tuanHienTai.set(1, ngay);
                ngay = duLieuNgayHienTai.minusDays(3).format(fm);
                tuanHienTai.set(2, ngay);
                ngay = duLieuNgayHienTai.minusDays(2).format(fm);
                tuanHienTai.set(3, ngay);
                ngay = duLieuNgayHienTai.minusDays(1).format(fm);
                tuanHienTai.set(4, ngay);
                ngay = duLieuNgayHienTai.format(fm);
                tuanHienTai.set(5, ngay);
                ngay = duLieuNgayHienTai.plusDays(1).format(fm);
                tuanHienTai.set(5, ngay);
                break;
            case 6:
                ngay = duLieuNgayHienTai.minusDays(6).format(fm);
                tuanHienTai.set(0, ngay);
                ngay = duLieuNgayHienTai.minusDays(5).format(fm);
                tuanHienTai.set(1, ngay);
                ngay = duLieuNgayHienTai.minusDays(4).format(fm);
                tuanHienTai.set(2, ngay);
                ngay = duLieuNgayHienTai.minusDays(3).format(fm);
                tuanHienTai.set(3, ngay);
                ngay = duLieuNgayHienTai.minusDays(2).format(fm);
                tuanHienTai.set(4, ngay);
                ngay = duLieuNgayHienTai.minusDays(1).format(fm);
                tuanHienTai.set(5, ngay);
                ngay = duLieuNgayHienTai.format(fm);
                tuanHienTai.set(5, ngay);
                break;
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, tuanHienTai);
        layoutMHCQL_spnNgay.setAdapter(arrayAdapter);

        for (int i = 0; i < tuanHienTai.size(); i++) {
            if (tuanHienTai.get(i).equalsIgnoreCase(ngayHienThi)) {
                layoutMHCQL_spnNgay.setSelection(i);
                break;
            }
        }

        layoutMHCQL_spnNgay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ngayHienThi = tuanHienTai.get(position);
                fireBaseNhaSachOnline.hienThiManHinhChinh(ngayHienThi, thongKeDon, ManHinhChinhQuanLyActivity.this);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        fireBaseNhaSachOnline.hienThiThongKeDoanhSo(tuanHienTai, thongKeDoanhSo, this);
    }

    public void thielapDuLieuPieChart() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();
        if (thongKeDon.getDonThanhCong() != 0) {
            entries.add(new PieEntry(thongKeDon.getDonThanhCong(), "Thành công"));
            colors.add(Color.BLUE);
        }
        if (thongKeDon.getDonDaHuy() != 0) {
            entries.add(new PieEntry(thongKeDon.getDonDaHuy(), "Hủy"));
            colors.add(Color.RED);
        }
        if (thongKeDon.getDonDangXyLy() != 0) {
            entries.add(new PieEntry(thongKeDon.getDonDangXyLy(), "Chờ xử lý"));
            colors.add(Color.GRAY);
        }

        PieDataSet dataSet = new PieDataSet(entries, "THÔNG TIN ĐƠN");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                float f = Float.valueOf(super.getFormattedValue(value));
                return (int) f + " đơn";
            }
        });
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.WHITE);
        layoutMHCQL_pcBieuDoDonHang.setData(data);
        layoutMHCQL_pcBieuDoDonHang.invalidate();
        layoutMHCQL_pcBieuDoDonHang.animateY(1400, Easing.EaseInOutQuad);

        if (entries.size() != 0) {
            layoutMHCQL_pcBieuDoDonHang.setCenterText("THỐNG KÊ ĐƠN HÀNG");
        } else {
            layoutMHCQL_pcBieuDoDonHang.setCenterText("HIỆN TẠI KHÔNG CÓ ĐƠN HÀNG NÀO");
        }


        layoutMHCQL_tvSoNguoiCa1.setText(thongKeDon.getSoNguoiLamCa1() + " người");
        layoutMHCQL_tvSoNguoiCa2.setText(thongKeDon.getSoNguoiLamCa2() + " người");
    }

    public void thielapDuLieuBarChart() {
        ArrayList<BarEntry> entries2 = new ArrayList<>();
        entries2.add(new BarEntry(1, thongKeDoanhSo.getThuHai()));
        ArrayList<BarEntry> entries3 = new ArrayList<>();
        entries3.add(new BarEntry(2, thongKeDoanhSo.getThuBa()));
        ArrayList<BarEntry> entries4 = new ArrayList<>();
        entries4.add(new BarEntry(3, thongKeDoanhSo.getThuTu()));
        ArrayList<BarEntry> entries5 = new ArrayList<>();
        entries5.add(new BarEntry(4, thongKeDoanhSo.getThuNam()));
        ArrayList<BarEntry> entries6 = new ArrayList<>();
        entries6.add(new BarEntry(5, thongKeDoanhSo.getThuSau()));
        ArrayList<BarEntry> entries7 = new ArrayList<>();
        entries7.add(new BarEntry(6, thongKeDoanhSo.getThuBay()));
        ArrayList<BarEntry> entriesCN = new ArrayList<>();
        entriesCN.add(new BarEntry(7, thongKeDoanhSo.getChuNhat()));

        List<IBarDataSet> bars = new ArrayList<IBarDataSet>();
        BarDataSet dataset2 = new BarDataSet(entries2, "Thứ 2");
        dataset2.setColor(Color.RED);
        bars.add(dataset2);
        BarDataSet dataset3 = new BarDataSet(entries3, "Thứ 3");
        dataset3.setColor(Color.BLUE);
        bars.add(dataset3);
        BarDataSet dataset4 = new BarDataSet(entries4, "Thứ 4");
        dataset4.setColor(Color.GREEN);
        bars.add(dataset4);
        BarDataSet dataset5 = new BarDataSet(entries5, "Thứ 5");
        dataset5.setColor(Color.GRAY);
        bars.add(dataset5);
        BarDataSet dataset6 = new BarDataSet(entries6, "Thứ 6");
        dataset6.setColor(Color.BLACK);
        bars.add(dataset6);
        BarDataSet dataset7 = new BarDataSet(entries7, "Thứ 7");
        dataset7.setColor(Color.CYAN);
        bars.add(dataset7);
        BarDataSet datasetCN = new BarDataSet(entriesCN, "Chủ nhật");
        datasetCN.setColor(Color.YELLOW);
        bars.add(datasetCN);
        BarData data = new BarData(bars);
        data.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return ((float) Math.round(value * 100) / 100) + "";
            }
        });
        com.github.mikephil.charting.components.XAxis xAxis = layoutMHCQL_bcBieuDoDoanhSo.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                switch (((int) value)) {
                    case 1:
                        return "Thứ 2";
                    case 2:
                        return "Thứ 3";
                    case 3:
                        return "Thứ 4";
                    case 4:
                        return "Thứ 5";
                    case 5:
                        return "Thứ 6";
                    case 6:
                        return "Thứ 7";
                    case 7:
                        return "Chủ nhật";
                }
                return super.getFormattedValue(value);
            }
        });
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.BLACK);
        layoutMHCQL_bcBieuDoDoanhSo.setData(data);
        layoutMHCQL_bcBieuDoDoanhSo.invalidate();
        layoutMHCQL_bcBieuDoDoanhSo.animateY(1400, Easing.EaseInOutQuad);
    }

}
