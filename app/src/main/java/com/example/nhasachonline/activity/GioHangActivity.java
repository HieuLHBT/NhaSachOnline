package com.example.nhasachonline.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.adapters.GioHangRecyclerViewAdapter;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.GioHang;
import com.example.nhasachonline.tools.SharePreferences;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangActivity extends AppCompatActivity {
    private SharePreferences sharePreferences = new SharePreferences();
    private String maKhacHang;
    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();

    private Drawable backBackground;

    private ArrayList<GioHang> gioHangs = new ArrayList<>();
    private GioHangRecyclerViewAdapter adapter;

    private TextView layoutGH_tvTongTienThanhToan, layoutGH_btnTroVe;
    private Button layoutGH_btnMuaHang, layoutGH_btnChonHet, layoutGH_btnBoChon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giohang_layout);
        maKhacHang = sharePreferences.getKhachHang("nguoidung", this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutGH_rvDanhSach);
        layoutGH_tvTongTienThanhToan = findViewById(R.id.layoutGH_tvTongTienThanhToan);
        layoutGH_btnMuaHang = findViewById(R.id.layoutGH_btnMuaHang);
        layoutGH_btnTroVe = findViewById(R.id.layoutGH_btnTroVe);
        layoutGH_btnChonHet = findViewById(R.id.layoutGH_btnChonHet);
        layoutGH_btnBoChon = findViewById(R.id.layoutGH_btnBoChon);

        adapter = new GioHangRecyclerViewAdapter(this, R.layout.giohang_item, gioHangs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        fireBaseNhaSachOnline.hienThiGioHang(maKhacHang, gioHangs, adapter, this);

        adapter.setOnItemClickListener(new GioHangRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                CardView cvItem = view.findViewById(R.id.itemGH);
                if (gioHangs.get(position).getCheck() == 0) {
                    backBackground = cvItem.getBackground();
                    cvItem.setBackgroundColor(getResources().getColor(R.color.clickgiohang, getTheme()));
                    gioHangs.get(position).setCheck(1);
                    layoutGH_btnBoChon.setVisibility(View.VISIBLE);
                    layoutGH_btnChonHet.setVisibility(View.VISIBLE);
                } else if (gioHangs.get(position).getCheck() == 1) {
                    cvItem.setBackground(backBackground);
                    gioHangs.get(position).setCheck(0);
                    int sum = 0;
                    for (GioHang gioHang : gioHangs) {
                        if (gioHang.getCheck() == 1) {
                            sum++;
                        }
                    }
                    if (sum == 0) {
                        layoutGH_btnBoChon.setVisibility(View.GONE);
                        layoutGH_btnChonHet.setVisibility(View.GONE);
                    }
                }

                ImageButton itemGH_btnTru = view.findViewById(R.id.itemGH_btnTru);
                ImageButton itemGH_btnCong = view.findViewById(R.id.itemGH_btnCong);
                itemGH_btnTru.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (gioHangs.get(position).getSoLuongSanPham() == 1) {
                            fireBaseNhaSachOnline.xoaSanPhamGioHang(maKhacHang, gioHangs.get(position).getMaSanPham());
                        } else if (gioHangs.get(position).getSoLuongSanPham() > 1) {
                            fireBaseNhaSachOnline.truSoLuongGioHang(maKhacHang, gioHangs.get(position).getMaSanPham(), gioHangs.get(position).getSoLuongSanPham());
                        }
                    }
                });
                itemGH_btnCong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fireBaseNhaSachOnline.congSoLuongGioHang(maKhacHang, gioHangs.get(position).getMaSanPham(), gioHangs.get(position).getSoLuongSanPham());
                    }
                });
            }
        });

        layoutGH_btnChonHet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (GioHang gioHang : gioHangs) {
                    if (gioHang.getCheck() == 0) {
                        gioHang.setCheck(1);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

        layoutGH_btnBoChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (GioHang gioHang : gioHangs) {
                    if (gioHang.getCheck() == 1) {
                        gioHang.setCheck(0);
                    }
                }
                adapter.notifyDataSetChanged();
                layoutGH_btnBoChon.setVisibility(View.GONE);
                layoutGH_btnChonHet.setVisibility(View.GONE);
            }
        });

        layoutGH_btnMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<GioHang> dsGioHang = new ArrayList<>();
                for (int i = 0; i < gioHangs.size(); i++) {
                    if (gioHangs.get(i).getCheck() == 1) {
                        dsGioHang.add(gioHangs.get(i));
                    }
                }
                if (dsGioHang.size() == 0) {
                    ThongBaoMuaHang(gioHangs);
                } else {
                    ThongBaoMuaHang(dsGioHang);
                }
            }
        });

        layoutGH_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void ThongBaoMuaHang(ArrayList<GioHang> gioHangs) {
        AlertDialog.Builder b = new AlertDialog.Builder(GioHangActivity.this);
        b.setTitle("Xác nhận");
        b.setMessage("Xác nhận thanh toán các sản phẩm?");
        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(GioHangActivity.this, ThanhToanActivity.class);
//                intent.putExtra("thanhToan", gioHangs);
                startActivity(intent);
            }
        });
        b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog al = b.create();
        al.show();
    }

    public void TongTienThanhToan() {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        Integer sum = 0;
        for (GioHang gioHang : gioHangs) {
            sum += gioHang.getTongTien();
        }
        layoutGH_tvTongTienThanhToan.setText(formatter.format(sum));
    }

}
