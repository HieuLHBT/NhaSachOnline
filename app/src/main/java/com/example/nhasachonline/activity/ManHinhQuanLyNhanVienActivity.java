package com.example.nhasachonline.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.NhanVienRecyclerViewAdapter;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;

import com.example.nhasachonline.item.ItemNhanVien;
import com.example.nhasachonline.tools.SharePreferences;

import java.util.ArrayList;

public class ManHinhQuanLyNhanVienActivity extends AppCompatActivity {
    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();
    private String maNhanVien;

    private SearchView timkiemSP;
    private ArrayList<ItemNhanVien> nhanViens = new ArrayList<>();
    private NhanVienRecyclerViewAdapter adapter;
    private Spinner layout_spnNhanVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_quanly_nhanvien_layout);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutMHQLNV_rvDanhSachNhanVien);
        adapter = new NhanVienRecyclerViewAdapter(this, R.layout.manhinh_quanly_nhanvien_item, nhanViens);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
                //itemTouchHelper.startDrag(viewHolder);
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                AlertDialog.Builder b = new AlertDialog.Builder(ManHinhQuanLyNhanVienActivity.this);
                b.setTitle("CẢNH BÁO");
                b.setMessage("Bạn có muốn xóa nhân viên ra khỏi cty không?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fireBase.xoaNhanVien(nhanViens.get(position).getMaNhanVien(), adapter);
                    }
                });
                b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.notifyDataSetChanged();
                        dialogInterface.cancel();
                    }
                });
                AlertDialog al = b.create();
                al.show();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

        fireBase.hienThiManHinhChinhQuanLyNhanVien(nhanViens,adapter,this);

        adapter.setOnItemClickListener(new NhanVienRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                ImageButton item_btnTroVe = view.findViewById(R.id.layoutMHQLNV_btnTroVe);
                item_btnTroVe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

                ImageButton item_btnThemNhanVien = view.findViewById(R.id.layoutMHQLNV_btnThemNhanVien);
                item_btnThemNhanVien.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(ManHinhQuanLyNhanVienActivity.this, ThemNhanVienActivity.class);
                        ManHinhQuanLyNhanVienActivity.this.startActivity(intent1);
                    }
                });

                CardView itemMHQLNV = view.findViewById(R.id.itemMHQLNV);
                itemMHQLNV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ManHinhQuanLyNhanVienActivity.this, SuaNhanVienActivity.class);
                        ManHinhQuanLyNhanVienActivity.this.startActivity(intent);
                    }
                });
            }
        });
    }

}
