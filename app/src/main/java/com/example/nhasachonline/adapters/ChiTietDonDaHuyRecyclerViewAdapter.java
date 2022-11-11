package com.example.nhasachonline.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.item.ChiTietDonDaGiao;
import com.example.nhasachonline.item.ChiTietDonDaHuy;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietDonDaHuyRecyclerViewAdapter extends RecyclerView.Adapter<ChiTietDonDaHuyRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<ChiTietDonDaHuy> chiTietDonDaHuys;

    public ChiTietDonDaHuyRecyclerViewAdapter(Activity context, int resource, ArrayList<ChiTietDonDaHuy> chiTietDonDaHuys) {
        this.context = context;
        this.resource = resource;
        this.chiTietDonDaHuys = chiTietDonDaHuys;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        final int pos = position;
        ChiTietDonDaHuy chiTietDonDaHuy = chiTietDonDaHuys.get(pos);
        holder.itemCTDDH_tvMaDonHang.setText(chiTietDonDaHuy.getMaDonHang());
        holder.itemCTDDH_tvTenKhachHang.setText(chiTietDonDaHuy.getTenKhachHang());
        holder.itemCTDDH_tvTenNhanVienDuyet.setText(chiTietDonDaHuy.getTenNhanVienDuyet());
        holder.itemCTDDH_tvTenNhanVienGiao.setText(chiTietDonDaHuy.getTenNhanVienGiao());
        holder.itemCTDDH_tvThoiGianLap.setText(chiTietDonDaHuy.getThoiGianLap());
        holder.itemCTDDH_tvPhiVanChuyen.setText(formatter.format(chiTietDonDaHuy.getPhiVanChuyen()) + " VNĐ");
        holder.itemCTDDH_tvGiamGia.setText(formatter.format(chiTietDonDaHuy.getGiamGia()) + " VNĐ");
        holder.itemCTDDH_tvDiaChiGiao.setText(chiTietDonDaHuy.getDiaChiGiao());
        holder.itemCTDDH_tvHinhThucThanhToan.setText(chiTietDonDaHuy.getHinhThucThanhToan());
        holder.itemCTDDH_tvTrangThaiDon.setText(chiTietDonDaHuy.getTrangThaiDon());
        holder.itemCTDDH_tvTongTienThanhToan.setText(formatter.format(chiTietDonDaHuy.getTongTienThanhToan()) + " VNĐ");
        holder.itemCTDDH_tvLyDoHuy.setText(chiTietDonDaHuy.getLyDoHuy());
    }

    @Override
    public int getItemCount() {
        return chiTietDonDaHuys.size();
    }

    @Override
    public int getItemViewType(int position) {
        return resource;
    }

    // ViewHolder definition
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemCTDDH_tvMaDonHang;
        TextView itemCTDDH_tvTenKhachHang;
        TextView itemCTDDH_tvTenNhanVienDuyet;
        TextView itemCTDDH_tvTenNhanVienGiao;
        TextView itemCTDDH_tvThoiGianLap;
        TextView itemCTDDH_tvPhiVanChuyen;
        TextView itemCTDDH_tvGiamGia;
        TextView itemCTDDH_tvDiaChiGiao;
        TextView itemCTDDH_tvHinhThucThanhToan;
        TextView itemCTDDH_tvTrangThaiDon;
        TextView itemCTDDH_tvTongTienThanhToan;
        TextView itemCTDDH_tvLyDoHuy;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemCTDDH_tvMaDonHang = itemView.findViewById(R.id.itemCTDDH_tvMaDonHang);
            itemCTDDH_tvTenKhachHang = itemView.findViewById(R.id.itemCTDDH_tvTenKhachHang);
            itemCTDDH_tvTenNhanVienDuyet = itemView.findViewById(R.id.itemCTDDH_tvTenNhanVienDuyet);
            itemCTDDH_tvTenNhanVienGiao = itemView.findViewById(R.id.itemCTDDH_tvTenNhanVienGiao);
            itemCTDDH_tvThoiGianLap = itemView.findViewById(R.id.itemCTDDH_tvThoiGianLap);
            itemCTDDH_tvPhiVanChuyen = itemView.findViewById(R.id.itemCTDDH_tvPhiVanChuyen);
            itemCTDDH_tvGiamGia = itemView.findViewById(R.id.itemCTDDH_tvGiamGia);
            itemCTDDH_tvDiaChiGiao = itemView.findViewById(R.id.itemCTDDH_tvDiaChiGiao);
            itemCTDDH_tvHinhThucThanhToan = itemView.findViewById(R.id.itemCTDDH_tvHinhThucThanhToan);
            itemCTDDH_tvTrangThaiDon = itemView.findViewById(R.id.itemCTDDH_tvTrangThaiDon);
            itemCTDDH_tvTongTienThanhToan = itemView.findViewById(R.id.itemCTDDH_tvTongTienThanhToan);
            itemCTDDH_tvLyDoHuy = itemView.findViewById(R.id.itemCTDDH_tvLyDoHuy);
        }
    }
}
