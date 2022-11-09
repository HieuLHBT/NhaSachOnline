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

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietGiaoHangRecyclerViewAdapter extends RecyclerView.Adapter<ChiTietGiaoHangRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<ChiTietGiaoHang> chiTietGiaoHangs;

    public ChiTietGiaoHangRecyclerViewAdapter(Activity context, int resource, ArrayList<ChiTietGiaoHang> chiTietGiaoHangs){
        this.context = context;
        this.resource = resource;
        this.chiTietGiaoHangs = chiTietGiaoHangs;
    }

    @NonNull
    @Override
    public ChiTietGiaoHangRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        final int pos = position;
        ChiTietGiaoHang chiTietGiaoHang = chiTietGiaoHangs.get(pos);
        holder.itemCTGH_txtTenSanPham.setText(chiTietGiaoHang.getTenSanPham());
        holder.itemCTGH_txtGiaTien.setText(formatter.format(chiTietGiaoHang.getGiaSanPham()) + " VNĐ");
        holder.itemCTGH_txtSoLuong.setText("(sl: " + chiTietGiaoHang.getSoLuong() + ")");
        //holder.itemCTGH_imgHinhSanPham.setText(chiTietGiaoHang.getHinhSanPham());
        holder.itemCTGH_txtTongTien.setText(formatter.format(chiTietGiaoHang.getTongTien()) + " VNĐ");
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
        //TextView itemCTGH_imgHinhSanPham;
        TextView itemCTGH_txtTongTien;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemCTGH_txtTenSanPham = itemView.findViewById(R.id.itemCTGH_txtTenSanPham);
            itemCTGH_txtGiaTien = itemView.findViewById(R.id.itemCTGH_txtGiaTien);
            itemCTGH_txtSoLuong = itemView.findViewById(R.id.itemCTGH_txtSoLuong);
            //itemCTGH_imgHinhSanPham = itemView.findViewById(R.id.itemCTGH_imgHinhSanPham);
            itemCTGH_txtTongTien = itemView.findViewById(R.id.itemCTGH_txtTongTien);
        }
    }

}
