package com.example.nhasachonline.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.doituong.TheoDoiDonHang;

import java.util.ArrayList;

public class TheoDoiDonHangRecyclerViewAdapter extends RecyclerView.Adapter<TheoDoiDonHangRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<TheoDoiDonHang> theoDoiDonHangs;
    private OnItemClickListener onItemClickListener;

    public TheoDoiDonHangRecyclerViewAdapter(Activity context, int resource, ArrayList<TheoDoiDonHang> theoDoiDonHangs) {
        this.context = context;
        this.resource = resource;
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
        TheoDoiDonHang theoDoiDonHang = theoDoiDonHangs.get(pos);
        holder.itemTDDH_txtMaDonHang.setText(theoDoiDonHang.getMaDonHang());
        holder.itemTDDH_txtTenNVGiaoHang.setText(theoDoiDonHang.getTenNVGiaoHang());
        holder.itemTDDH_txtThoiGianDuKienGiao.setText(theoDoiDonHang.getThoiGianDuKienGiao());
        holder.itemTDDH_txtThoiGianDat.setText(theoDoiDonHang.getThoiGianDat());
        holder.itemTDDH_txtTongTienThanhToan.setText(theoDoiDonHang.getTongTienThanhToan());
        holder.itemTDDH_txtTrangThai.setText(theoDoiDonHang.getTrangThai());

        // Event processing
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
        return resource;
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
        LinearLayout itemTDDH_llCardView;
        CardView itemTDDH;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemTDDH_txtMaDonHang = itemView.findViewById(R.id.itemTDDH_txtMaDonHang);
            itemTDDH_txtTenNVGiaoHang = itemView.findViewById(R.id.itemTDDH_txtTenNVGiaoHang);
            itemTDDH_txtThoiGianDuKienGiao = itemView.findViewById(R.id.itemTDDH_txtThoiGianDuKienGiao);
            itemTDDH_txtThoiGianDat = itemView.findViewById(R.id.itemTDDH_txtThoiGianDat);
            itemTDDH_txtTongTienThanhToan = itemView.findViewById(R.id.itemTDDH_txtTongTienThanhToan);
            itemTDDH_txtTrangThai = itemView.findViewById(R.id.itemTDDH_txtTrangThai);
            itemTDDH_btnXemChiTiet = itemView.findViewById(R.id.itemTDDH_btnXemChiTiet);
            itemTDDH_llCardView = itemView.findViewById(R.id.itemTDDH_llCardView);
            itemTDDH = itemView.findViewById(R.id.itemTDDH);

            // Set event processing
            itemTDDH.setOnClickListener(this);
            itemTDDH_btnXemChiTiet.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onClickListener != null) {
                onClickListener.onClick(v);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClickListener(int position, View view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
