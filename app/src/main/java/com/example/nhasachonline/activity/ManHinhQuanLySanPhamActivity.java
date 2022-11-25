package com.example.nhasachonline.activity;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.MergeAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.SachRecyclerViewAdapter;
import com.example.nhasachonline.adapters.VPPRecyclerViewAdapter;
import com.example.nhasachonline.data_model.TrangThaiDonHang;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.ItemSach;
import com.example.nhasachonline.item.ItemVanPhongPham;
import com.example.nhasachonline.data_model.Sach;
import com.google.firebase.database.core.operation.Merge;

import java.util.ArrayList;

public class ManHinhQuanLySanPhamActivity extends AppCompatActivity {
    FireBaseNhaSachOnline firebase = new FireBaseNhaSachOnline();
    private ArrayList<ItemSach> saches = new ArrayList<>();
    private ArrayList<ItemVanPhongPham> vanPhongPhams = new ArrayList<>();
    private ArrayList<String> loaiSP = new ArrayList<>();
    private SearchView timKiemSP;
    private String maSP;
    private SachRecyclerViewAdapter adapter;
    private MergeAdapter mainAdapter;
    private VPPRecyclerViewAdapter adapter2;
    private TextView layoutMHQLSP_btnBack, layoutMHQLSP_btnThemSanPham;
    private Spinner layoutMHQLSP_spLoaiSanPham;
    private RecyclerView layoutMHQLSP_rvDanhSachSanPham;
    private Button itemMHQLSP_btnXoa, itemMHQLSP_btnSua, itemMHQLSP_btnNhapKho;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        System.out.print("+++++++");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_quanly_sanpham_layout);
        timKiemSP = findViewById(R.id.layoutMHQLSP_swTimKiem);
        layoutMHQLSP_spLoaiSanPham = findViewById(R.id.layoutMHQLSP_spLoaiSanPham);
        timKiem();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutMHQLSP_rvDanhSachSanPham);
        adapter = new SachRecyclerViewAdapter(this,
                R.layout.manhinh_quanly_sach_item, saches);
        adapter2 = new VPPRecyclerViewAdapter(this, R.layout.manhinh_quanly_vpp_item, vanPhongPhams);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mainAdapter = new MergeAdapter(adapter,adapter2);
        recyclerView.setAdapter(mainAdapter);
        loaiSP.add("Sách");
        loaiSP.add("Văn Phòng Phẩm");
        //lọc
        ArrayAdapter arrayAdapterLoaiSP = new ArrayAdapter(this,R.layout.loai_sp_spiner,loaiSP);
        layoutMHQLSP_spLoaiSanPham.setAdapter(arrayAdapterLoaiSP);
        layoutMHQLSP_spLoaiSanPham = findViewById(R.id.layoutMHQLSP_spLoaiSanPham);
        layoutMHQLSP_spLoaiSanPham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ArrayList<ItemVanPhongPham> fiIteredList2 = new ArrayList<>();
                for (ItemVanPhongPham vanPhongPham : vanPhongPhams) {

                        fiIteredList2.add(vanPhongPham);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ArrayList<ItemSach> fiIteredList = new ArrayList<>();
                for (ItemSach sach : saches) {
                    fiIteredList.add(sach);
                }
            }
        });
//        recyclerView.setAdapter(adapter2);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
                //itemTouchHelper.startDrag(viewHolder);
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                AlertDialog.Builder b = new AlertDialog.Builder(ManHinhQuanLySanPhamActivity.this);
                b.setTitle("CẢNH BÁO");
                b.setMessage("Bạn có muốn xóa Sản Phẩm không?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mainAdapter.notifyDataSetChanged();
                        dialogInterface.cancel();
                    }
                });
                AlertDialog al = b.create();
                al.show();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

        layoutMHQLSP_btnThemSanPham = findViewById(R.id.layoutMHQLSP_btnThemSanPham);
        layoutMHQLSP_btnThemSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: haha");
                Intent intent = new Intent(ManHinhQuanLySanPhamActivity.this, ThemSanPhamSachActivity.class);
                ManHinhQuanLySanPhamActivity.this.startActivity(intent);
            }
        });
        layoutMHQLSP_btnBack = findViewById(R.id. layoutMHQLSP_btnBack);
        layoutMHQLSP_btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
//        firebase.hienThiManHinhChinhQuanLyVPP(vanPhongPhams, adapter2, this);
        firebase.hienThiManHinhQuanLySanPham(vanPhongPhams,adapter2,saches,adapter,this);
//        firebase.hienThiManHinhChinhQuanLySach( saches,adapter,this);
        adapter2.setOnItemClickListener(new VPPRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
//                layoutMHQLSP_btnBack = view.findViewById(R.id.MHQLNV_tvTroVe);
//                layoutMHQLSP_btnBack.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        finish();
//                    }
//                });



//                itemMHQLSP_btnSua = view.findViewById(R.id.itemMHQLSP1);
//                itemMHQLSP_btnSua.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Log.d(TAG, "onClick: haha");
//                        Intent intent = new Intent(ManHinhQuanLySanPhamActivity.this, SuaSanPhamSachActivity.class);
//                        intent.putExtra(maSP, saches.get(position).getMaSach());
//                        ManHinhQuanLySanPhamActivity.this.startActivity(intent);
//                    }
//                });
            }
        });
    }

    public void filterList2(String newText) {
        ArrayList<ItemVanPhongPham> fiIteredList2 = new ArrayList<>();
        for (ItemVanPhongPham vanPhongPham : vanPhongPhams) {
            if (vanPhongPham.getTenVanPhongPham().toLowerCase().contains(newText.toLowerCase()) || vanPhongPham.getMaVanPhongPham().toLowerCase().contains(newText.toLowerCase())) {
                fiIteredList2.add(vanPhongPham);
            }
        }
        if (fiIteredList2.isEmpty()) {
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        } else {
            adapter2.setFilteredList2(fiIteredList2);
        }
    }
    public void filterList(String newText) {
        ArrayList<ItemSach> fiIteredList = new ArrayList<>();
        for (ItemSach sach : saches) {
            if (sach.getTenSach().toLowerCase().contains(newText.toLowerCase()) || sach.getMaSach().toLowerCase().contains(newText.toLowerCase())) {
                fiIteredList.add(sach);
            }
        }
        if (fiIteredList.isEmpty()) {
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setFilteredList1(fiIteredList);
        }
    }

    public void timKiem() {
        timKiemSP.clearFocus();
        timKiemSP.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList2(newText);
                filterList(newText);
                return true;
            }
        });
    }

//
}
