package com.example.nhasachonline.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.item.ItemChiTietDonHangNV;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietDonHangNVRecyclerViewAdapter extends RecyclerView.Adapter<ChiTietDonHangNVRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<ItemChiTietDonHangNV> itemChiTietDonHangNVs;

    public ChiTietDonHangNVRecyclerViewAdapter(Activity context, int resource, ArrayList<ItemChiTietDonHangNV> itemChiTietDonHangNVs){
        this.context = context;
        this.resource = resource;
        this.itemChiTietDonHangNVs = itemChiTietDonHangNVs;
    }

    @NonNull
    @Override
    public ChiTietDonHangNVRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        final int pos = position;
        ItemChiTietDonHangNV itemChiTietDonHangNV = itemChiTietDonHangNVs.get(pos);
        holder.itemCTDH_NV_txtTenSanPham.setText(itemChiTietDonHangNV.getTenSanPham());
        holder.itemCTDH_NV_txtSoLuong.setText(itemChiTietDonHangNV.getSoLuong() + " ");
        holder.itemCTDH_NV_txtDonGia.setText(formatter.format(itemChiTietDonHangNV.getDonGia()) + " VNĐ");
        holder.itemCTDH_NV_txtTongTien.setText(formatter.format(itemChiTietDonHangNV.getTongTien()) + " VNĐ");
        holder.itemCTDH_NV_imgHinhSanPham.setText(itemChiTietDonHangNV.getHinhSanPham());
    }

    @Override
    public int getItemCount() {
        return itemChiTietDonHangNVs.size();
    }

    public int getItemViewType(int position){
        return  resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemCTDH_NV_txtTenSanPham;
        TextView itemCTDH_NV_txtSoLuong;
        TextView itemCTDH_NV_txtDonGia;
        TextView itemCTDH_NV_txtTongTien;
        TextView itemCTDH_NV_imgHinhSanPham;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemCTDH_NV_txtTenSanPham = itemView.findViewById(R.id.itemCTDH_NV_txtTenSanPham);
            itemCTDH_NV_txtSoLuong = itemView.findViewById(R.id.itemCTDH_NV_txtSoLuong);
            itemCTDH_NV_txtDonGia = itemView.findViewById(R.id.itemCTDH_NV_txtDonGia);
            itemCTDH_NV_txtTongTien = itemView.findViewById(R.id.itemCTDH_NV_txtTongTien);
            itemCTDH_NV_imgHinhSanPham = itemView.findViewById(R.id.itemCTDH_NV_imgHinhSanPham);
        }
    }
}
