//package com.example.nhasachonline.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.nhasachonline.R;
//import com.example.nhasachonline.adapters.DanhGiaSanPhamRecyclerViewAdapter;
//import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
//import com.example.nhasachonline.item.DanhGia;
//import com.example.nhasachonline.tools.SharePreferences;
//
//import java.util.ArrayList;
//
//public class DanhGiaSanPhamActivity extends AppCompatActivity {
//    private SharePreferences sharePreferences = new SharePreferences();
//    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();
//
//    private ArrayList<DanhGia> danhGias = new ArrayList<>();
//    private DanhGiaSanPhamRecyclerViewAdapter adapter;
//
//    private String maSanPham;
//    private String maKhachHang;
//    private String maDonHang;
//
//    private ImageButton itemDGSP_btn1Sao;
//    private ImageButton itemDGSP_btn2Sao;
//    private ImageButton itemDGSP_btn3Sao;
//    private ImageButton itemDGSP_btn4Sao;
//    private ImageButton itemDGSP_btn5Sao;
//    private Button itemDGSP_btnDanhGia;
//    private Button layoutDGSP_btnTroVe;
//    private EditText layoutDGSP_edtBinhLuan;
//
//
//    @Override
//    protected void onCreate(Bundle saveInstanceState) {
//        super.onCreate(saveInstanceState);
//        setContentView(R.layout.danhgiasanpham_layout);
//        maSanPham = sharePreferences.getMaSanPham(this);
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutDGSP_rvDanhGiaSanPham);
//
//        itemDGSP_btn1Sao = findViewById(R.id.itemDGSP_btn1Sao);
//        itemDGSP_btn2Sao = findViewById(R.id.itemDGSP_btn2Sao);
//        itemDGSP_btn3Sao = findViewById(R.id.itemDGSP_btn3Sao);
//        itemDGSP_btn4Sao = findViewById(R.id.itemDGSP_btn4Sao);
//        itemDGSP_btn5Sao = findViewById(R.id.itemDGSP_btn5Sao);
//        itemDGSP_btnDanhGia = findViewById(R.id.itemDGSP_btnDanhGia);
//        layoutDGSP_btnTroVe = findViewById(R.id.layoutDGSP_btnTroVe);
//        layoutDGSP_edtBinhLuan = findViewById(R.id.layoutDGSP_edtBinhLuan);
//
//        adapter = new DanhGiaSanPhamRecyclerViewAdapter(this, R.layout.danhgiasanpham_item, danhGias);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(RecyclerView.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);
//
//        recyclerView.setAdapter(adapter);
//        fireBase.hienThiItemDanhGiaSanPham("s1", "kh1", danhGias, adapter, this);
//
//
//        itemDGSP_btn1Sao.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "1 sao!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        itemDGSP_btn2Sao.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "2 sao!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        itemDGSP_btn3Sao.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "3 sao!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        itemDGSP_btn4Sao.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "4 sao!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        itemDGSP_btn5Sao.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "5 sao!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        itemDGSP_btnDanhGia.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                //Intent mhckh = new Intent(DanhGiaSanPhamActivity.this, ManHinhChinhKhachHangAdapter.class);
//                //startActivity(mhckh);
//                //Toast.makeText(getApplicationContext(), "Đánh giá thành công!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        layoutDGSP_btnTroVe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//
//
//
//    }
//}
