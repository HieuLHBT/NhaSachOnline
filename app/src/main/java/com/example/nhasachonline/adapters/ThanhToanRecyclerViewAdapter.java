package com.example.nhasachonline.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.doituong.ThanhToan;

import java.util.ArrayList;

public class ThanhToanRecyclerViewAdapter extends RecyclerView.Adapter<ThanhToanRecyclerViewAdapter.MyViewHolder>{
    private Activity context;
    private int resource;
    private ArrayList<ThanhToan> thanhToans;

    public ThanhToanRecyclerViewAdapter(Activity context, int resource, ArrayList<ThanhToan> thanhToans) {
        this.context = context;
        this.resource = resource;
        this.thanhToans = thanhToans;
    }

    @NonNull
    @Override
    public ThanhToanRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new ThanhToanRecyclerViewAdapter.MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final int pos = position;
        ThanhToan thanhToan = thanhToans.get(pos);
        holder.itemTT_tvTenSanPham.setText(thanhToan.getTenSanPham());
        holder.itemTT_tvGiaTien.setText(thanhToan.getGiaSanPham() + "");
        holder.itemTT_tvSoLuong.setText(thanhToan.getSoLuong() + "");
    }

    @Override
    public int getItemCount() {
        return thanhToans.size();
    }

    @Override
    public int getItemViewType(int position) {
        return resource;
    }

    // ViewHolder definition
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemTT_tvTenSanPham;
        TextView itemTT_tvGiaTien;
        TextView itemTT_tvSoLuong;
        ImageView itemTT_imgHinhSanPham;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTT_tvTenSanPham = itemView.findViewById(R.id.itemTT_tvTenSanPham);
            itemTT_tvGiaTien = itemView.findViewById(R.id.itemTT_tvGiaTien);
            itemTT_tvSoLuong = itemView.findViewById(R.id.itemTT_tvSoLuong);
            itemTT_imgHinhSanPham = itemView.findViewById(R.id.itemTT_imgHinhSanPham);

        }
    }
}
