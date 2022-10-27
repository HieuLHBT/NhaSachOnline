/*
package com.example.nhasachonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.ManHinhChinhKhachHangAdapter;
import com.example.nhasachonline.item.Sach;

import java.util.ArrayList;

public class ManHinhChinhKhachHangActivity extends AppCompatActivity {

    private CardView previousItem;
    private SearchView timkiemSP;

    private ArrayList<Sach> books = new ArrayList<>();
    private ManHinhChinhKhachHangAdapter adapter;

    private Spinner spSanPham;
    ArrayList<String> dataSanPham = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhchinh_khachhang_layout);

        //search
        timkiemSP = findViewById(R.id.layoutMHCKH_swTimKiem);
        timkiemSP.clearFocus();
        timkiemSP.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        //Get views from layout
        spSanPham = findViewById(R.id.layoutMHCKH_spSanPham);
        dataSanPham.add("Tất cả");
        dataSanPham.add("Sách");
        dataSanPham.add("Văn phòng phẩm");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_item, dataSanPham);
        spSanPham.setAdapter(arrayAdapter);

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
                        Intent mhct = new Intent(ManHinhChinhKhachHangActivity.this, ChiTietSanPhamActivity.class);
                        startActivity(mhct);
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        });

        recyclerView.setAdapter(adapter);

    }

    private void filterList(String newText) {
        ArrayList<Sach> fiIteredList = new ArrayList<>();
        for(Sach sach : books){
            if(sach.getTenSach().toLowerCase().contains(newText.toLowerCase())){
                fiIteredList.add(sach);
            }
        }

        if(fiIteredList.isEmpty()){
            Toast.makeText(this,"Không có dữ liệu",Toast.LENGTH_SHORT).show();
        }else {
            adapter.setFilteredList(fiIteredList);
        }
    }
}
*/
