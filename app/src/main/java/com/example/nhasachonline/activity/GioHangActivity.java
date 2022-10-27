package com.example.nhasachonline.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
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
import com.example.nhasachonline.item.Sach;

import java.util.ArrayList;

public class GioHangActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    private int selectedRow = -1;
    private Drawable backBackground;
    private CardView previousItem;

    private ArrayList<GioHang> gioHangs = new ArrayList<>();
    private ArrayList<com.example.nhasachonline.data_model.GioHang> gioHangsModel = new ArrayList<>();
    private GioHangRecyclerViewAdapter adapter;

    private TextView layoutGH_tvTongTienThanhToan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giohang_layout);



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutGH_rvDanhSach);
        layoutGH_tvTongTienThanhToan = findViewById(R.id.layoutGH_tvTongTienThanhToan);

        gioHangs.add(new GioHang("Sach 1", 30000, 10, 3));
        gioHangs.add(new GioHang("Sach 2", 60000, 5, 1));
        gioHangs.add(new GioHang("Sach 3", 20000, 30, 2));

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
//                itemGH_btnCong.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        fireBase.ghiSach(GioHangActivity.this, adapter, gioHangsModel);
//                    }
//                });
            }
        });

        recyclerView.setAdapter(adapter);


        TongTienThanhToan();

    }

    public void TongTienThanhToan() {
        Integer sum = 0;
        for (int i = 0; i < gioHangs.size(); i++) {
            sum += gioHangs.get(i).getTongTien();
        }
        layoutGH_tvTongTienThanhToan.setText(sum + "");
    }

}
