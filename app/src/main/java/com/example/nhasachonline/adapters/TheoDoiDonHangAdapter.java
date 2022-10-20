package com.example.nhasachonline.adapters;

import android.app.Activity;
import android.app.Person;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.doituong.TheoDoiDonHang;

import java.util.ArrayList;

public class TheoDoiDonHangAdapter extends RecyclerView.Adapter<TheoDoiDonHangAdapter.MyViewHolder> {
    private Activity context;
    private int layoutID;
    private ArrayList<TheoDoiDonHang> theoDoiDonHangs;
    private OnItemClickListener onItemClickListener;
    public TheoDoiDonHangAdapter(Activity context, int layoutID, ArrayList<TheoDoiDonHang> theoDoiDonHangs) {
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
        Button itemTDDH_btnXemChiTiet;
        View.OnClickListener onClickListener;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemTDDH_txtMaDonHang = itemView.findViewById(R.id.itemTDDH_txtMaDonHang);
            itemTDDH_txtTenNVGiaoHang = itemView.findViewById(R.id.itemTDDH_txtTenNVGiaoHang);
            itemTDDH_txtThoiGianDuKienGiao = itemView.findViewById(R.id.itemTDDH_txtThoiGianDuKienGiao);
            itemTDDH_txtThoiGianDat = itemView.findViewById(R.id.itemTDDH_txtThoiGianDat);
            itemTDDH_txtTongTienThanhToan = itemView.findViewById(R.id.itemTDDH_txtTongTienThanhToan);

            itemTDDH_txtMaDonHang.setOnClickListener(this);
            itemTDDH_txtTenNVGiaoHang.setOnClickListener(this);
            itemTDDH_txtThoiGianDuKienGiao.setOnClickListener(this);
            itemTDDH_txtThoiGianDat.setOnClickListener(this);
            itemTDDH_txtTongTienThanhToan.setOnClickListener(this);

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
}
