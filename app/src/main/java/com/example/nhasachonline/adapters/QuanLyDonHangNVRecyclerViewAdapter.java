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
import com.example.nhasachonline.item.ItemQuanLyDonHangNV;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class QuanLyDonHangNVRecyclerViewAdapter extends RecyclerView.Adapter<QuanLyDonHangNVRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<ItemQuanLyDonHangNV> itemQuanLyDonHangNVs;
    private OnItemClickListener onItemClickListener;

    public QuanLyDonHangNVRecyclerViewAdapter(Activity context, int resource, ArrayList<ItemQuanLyDonHangNV> itemQuanLyDonHangNVs) {
        this.context = context;
        this.resource = resource;
        this.itemQuanLyDonHangNVs = itemQuanLyDonHangNVs;
    }

    @NonNull
    @Override
    public QuanLyDonHangNVRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        final int pos = position;
        ItemQuanLyDonHangNV itemQuanLyDonHangNV = itemQuanLyDonHangNVs.get(pos);
        holder.itemQLDH_NV_txtMaDonHang.setText(itemQuanLyDonHangNV.getMaDonHang());
        holder.itemQLDH_NV_txtTrangThaiDonHang.setText(itemQuanLyDonHangNV.getTrangThaiDonHang());
        //holder.itemQLDH_NV_txtThoiGian.setText(itemQuanLyDonHangNV.getThoiGian());
        holder.itemQLDH_NV_txtNgay.setText(itemQuanLyDonHangNV.getNgay());

        holder.onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClickListener(pos, holder.itemView);
                }
            }
        };
    }

    @Override
    public int getItemCount() {
        return itemQuanLyDonHangNVs.size();
    }

    public int getItemViewType(int position) {
        return resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemQLDH_NV_txtMaDonHang;
        TextView itemQLDH_NV_txtTrangThaiDonHang;
        TextView itemQLDH_NV_txtThoiGian;
        TextView itemQLDH_NV_txtNgay;

        Button itemQLDH_NV_btnChiTietDon;
        Button itemQLDH_NV_btnThongBaoHuy;
        Button itemQLDH_NV_btnGiaoHang;
        Button itemQLDH_NV_btnHuyDon;

        View.OnClickListener onClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemQLDH_NV_txtMaDonHang = itemView.findViewById(R.id.itemQLDH_NV_txtMaDonHang);
            itemQLDH_NV_txtTrangThaiDonHang = itemView.findViewById(R.id.itemQLDH_NV_txtTrangThaiDonHang);
            itemQLDH_NV_txtThoiGian = itemView.findViewById(R.id.itemQLDH_NV_txtThoiGian);
            itemQLDH_NV_txtNgay = itemView.findViewById(R.id.itemQLDH_NV_txtNgay);
            itemQLDH_NV_btnChiTietDon = itemView.findViewById(R.id.itemQLDH_NV_btnChiTietDon);
            itemQLDH_NV_btnThongBaoHuy = itemView.findViewById(R.id.itemQLDH_NV_btnThongBaoHuy);
            itemQLDH_NV_btnGiaoHang = itemView.findViewById(R.id.itemQLDH_NV_btnGiaoHang);
            itemQLDH_NV_btnHuyDon = itemView.findViewById(R.id.itemQLDH_NV_btnHuyDon);

            itemQLDH_NV_btnChiTietDon.setOnClickListener(this);
            itemQLDH_NV_btnThongBaoHuy.setOnClickListener(this);
            itemQLDH_NV_btnGiaoHang.setOnClickListener(this);
            itemQLDH_NV_btnHuyDon.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClickListener(int position, View view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
