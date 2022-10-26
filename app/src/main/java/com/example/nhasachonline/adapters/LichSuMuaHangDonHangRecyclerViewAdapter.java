package com.example.nhasachonline.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.doituong.LichSuMuaHang_DonHang;

import java.util.ArrayList;

public class LichSuMuaHangDonHangRecyclerViewAdapter extends RecyclerView.Adapter<LichSuMuaHangDonHangRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<LichSuMuaHang_DonHang> lichSuMuaHang_donHangs;
    private LichSuMuaHangSanPhamRecyclerViewAdapter adapter;

    public LichSuMuaHangDonHangRecyclerViewAdapter(Activity context, int resource, ArrayList<LichSuMuaHang_DonHang> lichSuMuaHang_donHangs){
        this.context = context;
        this.resource = resource;
        this.lichSuMuaHang_donHangs = lichSuMuaHang_donHangs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuMuaHangDonHangRecyclerViewAdapter.MyViewHolder holder, int position) {
        final int pos = position;
        LichSuMuaHang_DonHang lichSuMuaHang_donHang = lichSuMuaHang_donHangs.get(pos);
        holder.itemLSMH_txtNgayGiao.setText(lichSuMuaHang_donHang.getNgayMuaHang());
        holder.itemLSMH_txtThangGiao.setText(lichSuMuaHang_donHang.getThangMuaHang());
        holder.itemLSMH_txtNamGiao.setText(lichSuMuaHang_donHang.getNamMuaHang());
        holder.itemLSMH_txtTrangThai.setText(lichSuMuaHang_donHang.getTrangThai());
        holder.itemLSMH_txtTongTien.setText(lichSuMuaHang_donHang.getTongTien() + "");

        adapter = new LichSuMuaHangSanPhamRecyclerViewAdapter(this.context, R.layout.lichsumuahang_sanpham_item, lichSuMuaHang_donHang.getSanPham());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        holder.lichSuMuaHangSanPhamRecyclerViewAdapter.setLayoutManager(layoutManager);
        holder.lichSuMuaHangSanPhamRecyclerViewAdapter.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return lichSuMuaHang_donHangs.size();
    }

    public int getItemViewType(int position){
        return  resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemLSMH_txtNgayGiao;
        TextView itemLSMH_txtThangGiao;
        TextView itemLSMH_txtNamGiao;
        TextView itemLSMH_txtTrangThai;
        TextView itemLSMH_txtTongTien;
        LinearLayout itemLSMH_DonHang_llCardView;
        CardView itemLSMH_DonHang;

        RecyclerView lichSuMuaHangSanPhamRecyclerViewAdapter;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemLSMH_txtNgayGiao = itemView.findViewById(R.id.itemLSMH_txtNgayGiao);
            itemLSMH_txtThangGiao = itemView.findViewById(R.id.itemLSMH_txtThangGiao);
            itemLSMH_txtNamGiao = itemView.findViewById(R.id.itemLSMH_txtNamGiao);
            itemLSMH_txtTrangThai = itemView.findViewById(R.id.itemLSMH_txtTrangThai);
            itemLSMH_txtTongTien = itemView.findViewById(R.id.itemLSMH_txtTongTien);
            itemLSMH_DonHang_llCardView = itemView.findViewById(R.id.layoutLSMH_rvLichSuMuahang);
            itemLSMH_DonHang = itemView.findViewById(R.id.itemLSMH_DonHang);
            lichSuMuaHangSanPhamRecyclerViewAdapter = itemView.findViewById(R.id.layoutLSMH_SanPham_rvLichSuMuaHang);

        }
    }
}
