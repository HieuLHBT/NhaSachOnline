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
import com.example.nhasachonline.doituong.TheoDoiDonHang;

import java.util.ArrayList;

public class TheoDoiDonHangRecyclerViewAdapter extends RecyclerView.Adapter<TheoDoiDonHangRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int layoutID;
    private ArrayList<TheoDoiDonHang> theoDoiDonHangs;
    private OnItemClickListener onItemClickListener;
    public TheoDoiDonHangRecyclerViewAdapter(Activity context, int layoutID, ArrayList<TheoDoiDonHang> theoDoiDonHangs) {
        this.context = context;
        this.layoutID = layoutID;
        this.theoDoiDonHangs = theoDoiDonHangs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final int pos = position;
        TheoDoiDonHang theoDoiDonHang = theoDoiDonHangs.get(position);
        holder.itemTDDH_txtMaDonHang.setText(theoDoiDonHang.getMaDonHang());
        holder.itemTDDH_txtTenNVGiaoHang.setText(theoDoiDonHang.getTenNVGiaoHang());
        holder.itemTDDH_txtThoiGianDuKienGiao.setText(theoDoiDonHang.getThoiGianDuKienGiao());
        holder.itemTDDH_txtThoiGianDat.setText(theoDoiDonHang.getThoiGianDat());
        holder.itemTDDH_txtTongTienThanhToan.setText(theoDoiDonHang.getTongTienThanhToan());
        holder.itemTDDH_txtTrangThai.setText(theoDoiDonHang.getTrangThai());

        holder.onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClickListener(pos, holder.itemView);
                }
            }
        };
    }

    @Override
    public int getItemCount() {
        return theoDoiDonHangs.size();
    }

    @Override
    public int getItemViewType(int position) {
        return layoutID;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView itemTDDH_txtMaDonHang;
        TextView itemTDDH_txtTenNVGiaoHang;
        TextView itemTDDH_txtThoiGianDuKienGiao;
        TextView itemTDDH_txtThoiGianDat;
        TextView itemTDDH_txtTongTienThanhToan;
        TextView itemTDDH_txtTrangThai;
        View.OnClickListener onClickListener;
        RecyclerView layoutTDDH_lvTheoDoiDonHang;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemTDDH_txtMaDonHang = itemView.findViewById(R.id.itemTDDH_txtMaDonHang);
            itemTDDH_txtTenNVGiaoHang = itemView.findViewById(R.id.itemTDDH_txtTenNVGiaoHang);
            itemTDDH_txtThoiGianDuKienGiao = itemView.findViewById(R.id.itemTDDH_txtThoiGianDuKienGiao);
            itemTDDH_txtThoiGianDat = itemView.findViewById(R.id.itemTDDH_txtThoiGianDat);
            itemTDDH_txtTongTienThanhToan = itemView.findViewById(R.id.itemTDDH_txtTongTienThanhToan);
            itemTDDH_txtTrangThai = itemView.findViewById(R.id.itemTDDH_txtTrangThai);
            layoutTDDH_lvTheoDoiDonHang = itemView.findViewById(R.id.layoutTDDH_rvTheoDoiDonHang);

            itemTDDH_txtMaDonHang.setOnClickListener(this);
            itemTDDH_txtTenNVGiaoHang.setOnClickListener(this);
            itemTDDH_txtThoiGianDuKienGiao.setOnClickListener(this);
            itemTDDH_txtThoiGianDat.setOnClickListener(this);
            itemTDDH_txtTongTienThanhToan.setOnClickListener(this);
            itemTDDH_txtTrangThai.setOnClickListener(this);
            layoutTDDH_lvTheoDoiDonHang.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (onClickListener != null) {
                onClickListener.onClick(v);
            }
        }
    }

    public interface OnItemClickListener {
        public void onItemClickListener(int position, View view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
