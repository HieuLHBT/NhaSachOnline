package com.example.nhasachonline.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.item.ItemQuanLySanPhamNV;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class QuanLySanPhamNVRecyclerViewAdapter extends RecyclerView.Adapter<QuanLySanPhamNVRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<ItemQuanLySanPhamNV> itemQuanLySanPhamNVs;

    public QuanLySanPhamNVRecyclerViewAdapter(Activity context, int resource, ArrayList<ItemQuanLySanPhamNV> itemQuanLySanPhamNVs){
        this.context = context;
        this.resource = resource;
        this.itemQuanLySanPhamNVs = itemQuanLySanPhamNVs;
    }

    @NonNull
    @Override
    public QuanLySanPhamNVRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        final int pos = position;
        ItemQuanLySanPhamNV itemQuanLySanPhamNV = itemQuanLySanPhamNVs.get(pos);
        holder.itemQLSP_NV_txtMaSanPham.setText(itemQuanLySanPhamNV.getMaSanPham());
        holder.itemQLSP_NV_txtTenSanPham.setText(itemQuanLySanPhamNV.getTenSanPham());
        holder.itemQLSP_NV_txtGiaTien.setText(formatter.format(itemQuanLySanPhamNV.getGiaTien()) + " VNĐ");
        holder.itemQLSP_NV_txtSoLuongTrongKho.setText(itemQuanLySanPhamNV.getSoLuong() + " ");
        holder.itemQLSP_NV_imgHinhSanPham.setText(itemQuanLySanPhamNV.getHinhSanPham());


    }

    @Override
    public int getItemCount() {
        return itemQuanLySanPhamNVs.size();
    }

    public int getItemViewType(int position){
        return  resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemQLSP_NV_txtMaSanPham;
        TextView itemQLSP_NV_txtTenSanPham;
        TextView itemQLSP_NV_txtGiaTien;
        TextView itemQLSP_NV_txtSoLuongTrongKho;
        TextView itemQLSP_NV_imgHinhSanPham;
        Button itemQLSP_NV_btnKiemTraDonHang;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemQLSP_NV_txtMaSanPham = itemView.findViewById(R.id.itemQLSP_NV_txtMaSanPham);
            itemQLSP_NV_txtTenSanPham = itemView.findViewById(R.id.itemQLSP_NV_txtTenSanPham);
            itemQLSP_NV_txtGiaTien = itemView.findViewById(R.id.itemQLSP_NV_txtGiaTien);
            itemQLSP_NV_txtSoLuongTrongKho = itemView.findViewById(R.id.itemQLSP_NV_txtSoLuongTrongKho);
            itemQLSP_NV_imgHinhSanPham = itemView.findViewById(R.id.itemQLSP_NV_imgHinhSanPham);
            itemQLSP_NV_btnKiemTraDonHang = itemView.findViewById(R.id.itemQLSP_NV_btnKiemTraDonHang);
        }
    }
}
