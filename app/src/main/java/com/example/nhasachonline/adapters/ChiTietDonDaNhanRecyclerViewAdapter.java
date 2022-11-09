package com.example.nhasachonline.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.item.ChiTietDonDaNhan;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietDonDaNhanRecyclerViewAdapter extends RecyclerView.Adapter<ChiTietDonDaNhanRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<ChiTietDonDaNhan> chiTietDonDaNhans;

    public ChiTietDonDaNhanRecyclerViewAdapter(Activity context, int resource, ArrayList<ChiTietDonDaNhan> chiTietDonDaNhans) {
        this.context = context;
        this.resource = resource;
        this.chiTietDonDaNhans = chiTietDonDaNhans;
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
        ChiTietDonDaNhan chiTietDonDaNhan = chiTietDonDaNhans.get(pos);
        holder.itemCTDDN_tvMaDonHang.setText(chiTietDonDaNhan.getMaDonHang());
        holder.itemCTDDN_tvTenKhachHang.setText(chiTietDonDaNhan.getTenKhachHang());
        holder.itemCTDDN_tvTenNhanVienDuyet.setText(chiTietDonDaNhan.getTenNhanVienDuyet());
        holder.itemCTDDN_tvTenNhanVienGiao.setText(chiTietDonDaNhan.getTenNhanVienGiao());
        holder.itemCTDDN_tvThoiGianLap.setText(chiTietDonDaNhan.getThoiGianLap());
        holder.itemCTDDN_tvPhiVanChuyen.setText(formatter.format(chiTietDonDaNhan.getPhiVanChuyen()) + " VNĐ");
        holder.itemCTDDN_tvGiamGia.setText(formatter.format(chiTietDonDaNhan.getGiamGia()) + " VNĐ");
        holder.itemCTDDN_tvDiaChiGiao.setText(chiTietDonDaNhan.getDiaChiGiao());
        holder.itemCTDDN_tvHinhThucThanhToan.setText(chiTietDonDaNhan.getHinhThucThanhToan());
        holder.itemCTDDN_tvTrangThaiDon.setText(chiTietDonDaNhan.getTrangThaiDon());
        holder.itemCTDDN_tvTongTienThanhToan.setText(formatter.format(chiTietDonDaNhan.getTongTienThanhToan()) + " VNĐ");
    }

    @Override
    public int getItemCount() {
        return chiTietDonDaNhans.size();
    }

    @Override
    public int getItemViewType(int position) {
        return resource;
    }

    // ViewHolder definition
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemCTDDN_tvMaDonHang;
        TextView itemCTDDN_tvTenKhachHang;
        TextView itemCTDDN_tvTenNhanVienDuyet;
        TextView itemCTDDN_tvTenNhanVienGiao;
        TextView itemCTDDN_tvThoiGianLap;
        TextView itemCTDDN_tvPhiVanChuyen;
        TextView itemCTDDN_tvGiamGia;
        TextView itemCTDDN_tvDiaChiGiao;
        TextView itemCTDDN_tvHinhThucThanhToan;
        TextView itemCTDDN_tvTrangThaiDon;
        TextView itemCTDDN_tvTongTienThanhToan;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemCTDDN_tvMaDonHang = itemView.findViewById(R.id.itemCTDDN_tvMaDonHang);
            itemCTDDN_tvTenKhachHang = itemView.findViewById(R.id.itemCTDDN_tvTenKhachHang);
            itemCTDDN_tvTenNhanVienDuyet = itemView.findViewById(R.id.itemCTDDN_tvTenNhanVienDuyet);
            itemCTDDN_tvTenNhanVienGiao = itemView.findViewById(R.id.itemCTDDN_tvTenNhanVienGiao);
            itemCTDDN_tvThoiGianLap = itemView.findViewById(R.id.itemCTDDN_tvThoiGianLap);
            itemCTDDN_tvPhiVanChuyen = itemView.findViewById(R.id.itemCTDDN_tvPhiVanChuyen);
            itemCTDDN_tvGiamGia = itemView.findViewById(R.id.itemCTDDN_tvGiamGia);
            itemCTDDN_tvDiaChiGiao = itemView.findViewById(R.id.itemCTDDN_tvDiaChiGiao);
            itemCTDDN_tvHinhThucThanhToan = itemView.findViewById(R.id.itemCTDDN_tvHinhThucThanhToan);
            itemCTDDN_tvTrangThaiDon = itemView.findViewById(R.id.itemCTDDN_tvTrangThaiDon);
            itemCTDDN_tvTongTienThanhToan = itemView.findViewById(R.id.itemCTDDN_tvTongTienThanhToan);
        }
    }
}
