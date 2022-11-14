package com.example.nhasachonline.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.GioHangRecyclerViewAdapter;
import com.example.nhasachonline.adapters.TheoDoiDonHangRecyclerViewAdapter;
import com.example.nhasachonline.data_model.DonHang;
import com.example.nhasachonline.data_model.NhanVien;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.TheoDoiDonHang;
import com.example.nhasachonline.tools.SharePreferences;

import java.util.ArrayList;

public class TheoDoiDonHangActivity extends AppCompatActivity {
    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    private String maKhachHang = "kh1";
    private String maDonHang;
    private String maNhanVien = "nv1";
    private int selectedRow = -1;

    private ArrayList<TheoDoiDonHang> theoDoiDonHangs = new ArrayList<>();
    private ArrayList<DonHang> donHangsModel = new ArrayList<>();
    private TheoDoiDonHangRecyclerViewAdapter adapter;

    private NhanVien nhanVien = new NhanVien();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theodoidonhang_layout);

        maKhachHang = sharePreferences.getKhachHang(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutTDDH_rvTheoDoiDonHang);

        adapter = new TheoDoiDonHangRecyclerViewAdapter(this, R.layout.theodoidonhang_item, theoDoiDonHangs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        fireBase.hienThiTheoDoiDonHang(maNhanVien ,nhanVien, theoDoiDonHangs, this);

        adapter.setOnItemClickListener(new TheoDoiDonHangRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                Button itemTDDH_btnXemChiTiet = view.findViewById(R.id.itemTDDH_btnXemChiTiet);

                itemTDDH_btnXemChiTiet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Chuyển sang màn hình Xem Chi Tiết!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        recyclerView.setAdapter(adapter);
//        fireBase.hienThiTheoDoiDonHang(maKhachHang, theoDoiDonHangs, adapter, this);
    }
}
