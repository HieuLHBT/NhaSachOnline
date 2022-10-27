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
import com.example.nhasachonline.item.ChiTietGiaoHang;

import java.util.ArrayList;

public class ChiTietGiaoHangRecyclerViewAdapter extends RecyclerView.Adapter<ChiTietGiaoHangRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<ChiTietGiaoHang> chiTietGiaoHangs;
    private OnItemClickListener onItemClickListener;

    public ChiTietGiaoHangRecyclerViewAdapter(Activity context, int resource, ArrayList<ChiTietGiaoHang> chiTietGiaoHangs){
        this.context = context;
        this.resource = resource;
        this.chiTietGiaoHangs = chiTietGiaoHangs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiTietGiaoHangRecyclerViewAdapter.MyViewHolder holder, int position) {
        final int pos = position;
        ChiTietGiaoHang chiTietGiaoHang = chiTietGiaoHangs.get(pos);
        holder.itemCTGH_txtTenSanPham.setText(chiTietGiaoHang.getTenSanPham());
        holder.itemCTGH_txtGiaTien.setText(chiTietGiaoHang.getGiaSanPham());
        holder.itemCTGH_txtSoLuong.setText(chiTietGiaoHang.getSoLuong() + "");
        //holder.itemCTGH_imgHinhSanPham.setText(chiTietGiaoHang.getHinhSanPham());
    }

    @Override
    public int getItemCount() {
        return chiTietGiaoHangs.size();
    }

    public int getItemViewType(int position){
        return  resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemCTGH_txtTenSanPham;
        TextView itemCTGH_txtGiaTien;
        TextView itemCTGH_txtSoLuong;
        /* TextView itemCTGH_imgHinhSanPham;*/

        LinearLayout itemCTGH_llCardView;
        CardView itemCTGH;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemCTGH_txtTenSanPham = itemView.findViewById(R.id.itemCTGH_txtTenSanPham);
            itemCTGH_txtGiaTien = itemView.findViewById(R.id.itemCTGH_txtGiaTien);
            itemCTGH_txtSoLuong = itemView.findViewById(R.id.itemCTGH_txtSoLuong);
//            itemCTGH_imgHinhSanPham = itemView.findViewById(R.id.itemCTGH_imgHinhSanPham);

            itemCTGH_llCardView = itemView.findViewById(R.id.itemCTGH_llCardView);
            itemCTGH = itemView.findViewById(R.id.itemCTGH);
        }
    }

    // Interface for event processing
    public interface OnItemClickListener {
        void onItemClickListener(int position, View view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
