package com.example.nhasachonline.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.ManHinhChinhKhachHangAdapter;
import com.example.nhasachonline.doituong.Sach;

import java.util.ArrayList;

public class ManHinhChinhKhachHangActivity extends AppCompatActivity {

    private CardView previousItem;
    private Spinner spSanPham;

    private ArrayList<Sach> books = new ArrayList<>();
    private ManHinhChinhKhachHangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhchinh_khachhang_layout);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutMHCKH_rvDanhSach);

        books.add(new Sach(1,null,"Sach demo 1",null,null,null,null,25000,0,1,1,null));
        books.add(new Sach(2,null,"Sach demo 2",null,null,null,null,25000,0,1,1,null));
        books.add(new Sach(3,null,"Sach demo 3",null,null,null,null,25000,0,1,1,null));

        adapter = new ManHinhChinhKhachHangAdapter(this, R.layout.manhinhchinh_khachhang_item, books);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter.setOnItemClickListener(new ManHinhChinhKhachHangAdapter.OnItemClickListener(){
            @Override
            public void onItemClickListener(int position, View view) {
                ImageButton item_btnTVGH = view.findViewById(R.id.itemMHCKH_btnThemGioHang);
                item_btnTVGH.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        adapter.notifyDataSetChanged();
                    }
                });

            }
        });

    }
}
