package com.example.nhasachonline.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.item.LichSuMuaHang_SanPham;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class LichSuMuaHangSanPhamRecyclerViewAdapter extends RecyclerView.Adapter<LichSuMuaHangSanPhamRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<LichSuMuaHang_SanPham> lichSuMuaHang_sanPhams = new ArrayList<>();

    public LichSuMuaHangSanPhamRecyclerViewAdapter(Activity context, int resource, ArrayList<LichSuMuaHang_SanPham> lichSuMuaHang_sanPhams){
        this.context = context;
        this.resource = resource;
        this.lichSuMuaHang_sanPhams = lichSuMuaHang_sanPhams;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuMuaHangSanPhamRecyclerViewAdapter.MyViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        final int pos = position;
        LichSuMuaHang_SanPham lichSuMuaHang_sanPham = lichSuMuaHang_sanPhams.get(pos);
        holder.itemLSMH_txtTenSanPham.setText(lichSuMuaHang_sanPham.getTenSanPham());
        holder.itemLSMH_txtGiaTien.setText(formatter.format(lichSuMuaHang_sanPham.getGiaSanPham()));
//        holder.itemLSMH_imgHinhSanPham.setText(lichSuMuaHang_sanPham.get());

    }

    @Override
    public int getItemCount() {
        return lichSuMuaHang_sanPhams.size();
    }

    public int getItemViewType(int position){
        return  resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemLSMH_txtTenSanPham;
        TextView itemLSMH_txtGiaTien;
        //TextView itemLSMH_imgHinhSanPham;

        LinearLayout itemLSMH_SanPham_llCardView;
        CardView itemLSMH_SanPham;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemLSMH_txtTenSanPham = itemView.findViewById(R.id.itemLSMH_txtTenSanPham);
            itemLSMH_txtGiaTien = itemView.findViewById(R.id.itemLSMH_txtGiaTien);
//            itemLSMH_imgHinhSanPham = itemView.findViewById(R.id.itemLSMH_imgHinhSanPham);
            itemLSMH_SanPham_llCardView = itemView.findViewById(R.id.layoutLSMH_SanPham_rvLichSuMuaHang);
            itemLSMH_SanPham = itemView.findViewById(R.id.itemLSMH_SanPham);
        }
    }
}
