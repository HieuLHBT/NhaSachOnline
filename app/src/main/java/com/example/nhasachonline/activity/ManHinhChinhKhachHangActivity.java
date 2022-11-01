
package com.example.nhasachonline.activity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.Sach;
import com.example.nhasachonline.item.SanPham;
import com.example.nhasachonline.tools.SharePreferences;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ManHinhChinhKhachHangActivity extends AppCompatActivity {

    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    private CardView previousItem;
    private SearchView timkiemSP;
    private ArrayList<SanPham> sanPhams = new ArrayList<>();
    private ArrayList<Sach> sachs = new ArrayList<>();
    private ArrayList<com.example.nhasachonline.data_model.Sach> sachModel = new ArrayList<>();
    private ManHinhChinhKhachHangAdapter adapter;
    private Spinner spSanPham;
  //  private EditText
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

        adapter = new ManHinhChinhKhachHangAdapter(this, R.layout.manhinhchinh_khachhang_item, sanPhams);
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

                    }
                });


            }
        });

        recyclerView.setAdapter(adapter);

        //fireBase.hienThiManHinhChinhKhachHang(sanPhams,adapter,this);

    }

    public void filterList(String newText) {
        ArrayList<SanPham> fiIteredList = new ArrayList<>();
        for(SanPham sanPham : sanPhams){
            if(sanPham.getTenSanPham().toLowerCase().contains(newText.toLowerCase())){
                fiIteredList.add(sanPham);
            }
        }

        if(fiIteredList.isEmpty()){
            Toast.makeText(this,"Không có dữ liệu",Toast.LENGTH_SHORT).show();
        }else {
            adapter.setFilteredList(fiIteredList);
        }
    }
}

