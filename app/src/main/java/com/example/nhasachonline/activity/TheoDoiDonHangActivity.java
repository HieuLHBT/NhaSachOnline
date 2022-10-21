package com.example.nhasachonline.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.TheoDoiDonHangRecyclerViewAdapter;
import com.example.nhasachonline.doituong.TheoDoiDonHang;

import java.util.ArrayList;

public class TheoDoiDonHangActivity extends AppCompatActivity {

    private int selectedRow = -1;
    private CardView previousItem;
    private Drawable backBackground;

    private ArrayList<TheoDoiDonHang> theoDoiDonHangs = new ArrayList<>();
    private TheoDoiDonHangRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theodoidonhang_layout);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutTDDH_rvTheoDoiDonHang);

        theoDoiDonHangs.add(new TheoDoiDonHang("DH01", "Nguyen Van Tho", "30/09/2022", "10/10/2022", 150000, "Dang cho xac nhan"));
        theoDoiDonHangs.add(new TheoDoiDonHang("DH02", "Tran Bao Tin", "30/09/2022", "10/10/2022", 150000, "Dang cho xac nhan"));
        theoDoiDonHangs.add(new TheoDoiDonHang("DH03", "Nguyen Van Tho", "30/09/2022", "10/10/2022", 150000, "Dang cho xac nhan"));

        adapter = new TheoDoiDonHangRecyclerViewAdapter(this, R.layout.theodoidonhang_item, theoDoiDonHangs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter.setOnItemClickListener(new TheoDoiDonHangRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                if (selectedRow == -1){
                    selectedRow = position;
                    CardView cvItem = view.findViewById(R.id.itemTDDH);
                    backBackground = cvItem.getBackground();

                    cvItem.setBackgroundColor(getResources().getColor(R.color.teal_700,getTheme()));
                    previousItem = cvItem;
                }
                else{
                    if (selectedRow == position)// Tap on the same item
                    {
                        selectedRow = -1;
                        CardView cvItem = view.findViewById(R.id.itemTDDH);
                        cvItem.setBackground(backBackground);
                    }else// Tap on other item
                    {
                        previousItem.setBackground(backBackground);
                        selectedRow = position;
                        CardView cvItem = view.findViewById(R.id.itemTDDH);

                        cvItem.setBackgroundColor(getResources().getColor(R.color.teal_700,getTheme()));
                        previousItem = cvItem;
                    }
                }

                Button itemTDDH_btnXemChiTiet = view.findViewById(R.id.itemTDDH_btnXemChiTiet);
                itemTDDH_btnXemChiTiet.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });

        recyclerView.setAdapter(adapter);
    }
}
