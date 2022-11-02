
package com.example.nhasachonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.ManHinhChinhKhachHangAdapter;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.ItemSach;
import com.example.nhasachonline.item.ItemSanPham;
import com.example.nhasachonline.tools.SharePreferences;


import java.util.ArrayList;

public class ManHinhChinhKhachHangActivity extends AppCompatActivity {

    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();
    private String maKhachHang;

    private CardView previousItem;
    private SearchView timkiemSP;
    private ArrayList<ItemSanPham> sanPhams = new ArrayList<>();
    private ManHinhChinhKhachHangAdapter adapter;
    private Spinner layoutMHCKH_spnSanPham;
  //  private EditText
    ArrayList<String> dataSanPham = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhchinh_khachhang_layout);
        maKhachHang = sharePreferences.getKhachHang(this);


        //search
        timkiemSP = findViewById(R.id.layoutMHCKH_swTimKiem);
        timKiem();

        //Get views from layout
        layoutMHCKH_spnSanPham = findViewById(R.id.layoutMHCKH_spSanPham);
        dataSanPham.add("Tất cả");
        dataSanPham.add("Sách");
        dataSanPham.add("Văn phẩm");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_item, dataSanPham);
        layoutMHCKH_spnSanPham.setAdapter(arrayAdapter);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutMHCKH_rvDanhSach);

        adapter = new ManHinhChinhKhachHangAdapter(this, R.layout.manhinhchinh_khachhang_item, sanPhams);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        fireBase.hienThiManHinhChinhKhachHang(sanPhams,adapter,this);

        adapter.setOnItemClickListener(new ManHinhChinhKhachHangAdapter.OnItemClickListener(){
            @Override
            public void onItemClickListener(int position, View view) {
                ImageButton item_btnTVGH = view.findViewById(R.id.itemMHCKH_btnThemGioHang);
                item_btnTVGH.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ManHinhChinhKhachHangActivity.this, GioHangActivity.class);
                        intent.putExtra("maSanPham",sanPhams.get(position).getMaSanPham());
                        ManHinhChinhKhachHangActivity.this.startActivity(intent);

                    }
                });
                TextView item_TenSanPham = view.findViewById(R.id.itemMHCKH_tvTenSanPham);
                item_TenSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ManHinhChinhKhachHangActivity.this, ChiTietSanPhamActivity.class);
                        intent.putExtra("maSanPham",sanPhams.get(position).getMaSanPham());
                        ManHinhChinhKhachHangActivity.this.startActivity(intent);
                    }
                });
            }
        });

    }

    public void filterList(String newText) {
        ArrayList<ItemSanPham> fiIteredList = new ArrayList<>();
        for(ItemSanPham sanPham : sanPhams){
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

    public void timKiem(){
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
    }
}

