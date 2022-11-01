
package com.example.nhasachonline.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.TheoDoiDonHangRecyclerViewAdapter;
import com.example.nhasachonline.data_model.DonHang;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.TheoDoiDonHang;
import com.example.nhasachonline.tools.SharePreferences;

import java.util.ArrayList;

public class TheoDoiDonHangActivity extends AppCompatActivity {
    private String maKhachHang;
    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    private int selectedRow = -1;

    private ArrayList<TheoDoiDonHang> theoDoiDonHangs = new ArrayList<>();
    private ArrayList<DonHang> donHangsModel = new ArrayList<>();
    private TheoDoiDonHangRecyclerViewAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theodoidonhang_layout);
        maKhachHang = sharePreferences.getKhachHang("nguoidung", this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutTDDH_rvTheoDoiDonHang);

      /*  theoDoiDonHangs.add(new TheoDoiDonHang("DH01", "Nguyen Van Tho", "30/09/2022", "11/10/2022", 130000, "Đang chờ xác nhận"));
        theoDoiDonHangs.add(new TheoDoiDonHang("DH02", "Tran Bao Tin", "27/09/2022", "14/10/2022", 120000, "Đang chờ xác nhận"));
        theoDoiDonHangs.add(new TheoDoiDonHang("DH03", "Nguyen Van Tho", "29/09/2022", "15/10/2022", 150000, "Đã xác nhận"));
*/
        adapter = new TheoDoiDonHangRecyclerViewAdapter(this, R.layout.theodoidonhang_item, theoDoiDonHangs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        //fireBase.hienThiTheoDoiDonHang(maKhachHang,theoDoiDonHangs, adapter, this);

        adapter.setOnItemClickListener(new TheoDoiDonHangRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                Button itemTDDH_btnXemChiTiet = view.findViewById(R.id.itemTDDH_btnXemChiTiet);

                itemTDDH_btnXemChiTiet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Intent mhctgh = new Intent(TheoDoiDonHangActivity.this, ChiTietGiaoHangActivity.class);
                        //startActivity(mhctgh);
                    }
                });
            }
        });
        recyclerView.setAdapter(adapter);
//        fireBase.hienThiTheoDoiDonHang(maKhachHang, theoDoiDonHangs, adapter, this);
    }
}
