package com.example.nhasachonline.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.GioHangRecyclerViewAdapter;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.GioHang;
import com.example.nhasachonline.tools.SharePreferences;

import java.util.ArrayList;

public class GioHangActivity extends AppCompatActivity {
    private String maKhacHang;
    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    private int selectedRow = -1;
    private Drawable backBackground;
    private CardView previousItem;

    private ArrayList<GioHang> gioHangs = new ArrayList<>();
    private ArrayList<com.example.nhasachonline.data_model.GioHang> gioHangsModel = new ArrayList<>();
    private GioHangRecyclerViewAdapter adapter;

    private TextView layoutGH_tvTongTienThanhToan, layoutGH_btnTroVe;
    private Button layoutGH_btnMuaHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giohang_layout);

        maKhacHang = sharePreferences.getKhachHang("nguoidung", this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutGH_rvDanhSach);
        layoutGH_tvTongTienThanhToan = findViewById(R.id.layoutGH_tvTongTienThanhToan);
        layoutGH_btnMuaHang = findViewById(R.id.layoutGH_btnMuaHang);
        layoutGH_btnTroVe = findViewById(R.id.layoutGH_btnTroVe);

        adapter = new GioHangRecyclerViewAdapter(this, R.layout.giohang_item, gioHangs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter.setOnItemClickListener(new GioHangRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClickListener(int position, View view) {
                if (selectedRow == -1) {
                    selectedRow = position;
                    // Change the item background color
                    CardView cvItem = view.findViewById(R.id.itemGH);
                    backBackground = cvItem.getBackground();
                    cvItem.setBackgroundColor(getResources().getColor(R.color.clickgiohang, getTheme()));
                    previousItem = cvItem;
                } else {
                    if (selectedRow == position) {//Tap on the same item
                        selectedRow = -1;
                        CardView cvItem = view.findViewById(R.id.itemGH);
                        cvItem.setBackground(backBackground);
                    } else {// Tap on other item
                        previousItem.setBackground(backBackground);
                        selectedRow = position;
                        CardView cvItem = view.findViewById(R.id.itemGH);
                        cvItem.setBackgroundColor(getResources().getColor(R.color.clickgiohang, getTheme()));
                        previousItem = cvItem;
                    }
                }

                ImageButton itemGH_btnTru = view.findViewById(R.id.itemGH_btnTru);
                ImageButton itemGH_btnCong = view.findViewById(R.id.itemGH_btnCong);
                TextView itemGH_tvTongTien = view.findViewById(R.id.itemGH_tvTongTien);
                itemGH_btnTru.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (gioHangs.get(position).getSoLuongSanPham() == 1) {
                            gioHangs.remove(position);
                            adapter.notifyDataSetChanged();
                            TongTienThanhToan();
                        } else if (gioHangs.get(position).getSoLuongSanPham() > 1) {
                            gioHangs.get(position).setSoLuongSanPham(gioHangs.get(position).getSoLuongSanPham() - 1);
                            gioHangs.get(position).setTongTien((gioHangs.get(position).getGiaSanPham() - (gioHangs.get(position).getGiaSanPham() * gioHangs.get(position).getKhuyenMai() / 100)) * gioHangs.get(position).getSoLuongSanPham());
                            adapter.notifyDataSetChanged();
                            TongTienThanhToan();
                        }
                    }
                });
                itemGH_btnCong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gioHangs.get(position).setSoLuongSanPham(gioHangs.get(position).getSoLuongSanPham() + 1);
                        gioHangs.get(position).setTongTien((gioHangs.get(position).getGiaSanPham() - (gioHangs.get(position).getGiaSanPham() * gioHangs.get(position).getKhuyenMai() / 100)) * gioHangs.get(position).getSoLuongSanPham());
                        adapter.notifyDataSetChanged();
                        TongTienThanhToan();
                    }
                });
                layoutGH_btnMuaHang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(GioHangActivity.this, ThanhToanActivity.class);
                        intent.putExtra("maGioHang", "muahang");
                        startActivity(intent);

                        String maGioHang = getIntent().getStringExtra("maGioHang");
                    }
                });
            }
        });

        recyclerView.setAdapter(adapter);
        fireBase.hienThiGioHang(maKhacHang, gioHangs, adapter, this);

    }

    public void TongTienThanhToan() {
        Integer sum = 0;
        for (int i = 0; i < gioHangs.size(); i++) {
            sum += gioHangs.get(i).getTongTien();
        }
        layoutGH_tvTongTienThanhToan.setText(sum + "");
    }

}
