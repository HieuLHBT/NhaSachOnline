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
import com.example.nhasachonline.item.ChiTietDonDaNhan;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietDonDaGiaoRecyclerViewAdapter extends RecyclerView.Adapter<ChiTietDonDaGiaoRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<ChiTietDonDaGiao> chiTietDonDaGiaoss;

    public ChiTietDonDaGiaoRecyclerViewAdapter(Activity context, int resource, ArrayList<ChiTietDonDaGiao> chiTietDonDaGiaoss) {
        this.context = context;
        this.resource = resource;
        this.chiTietDonDaGiaoss = chiTietDonDaGiaoss;
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
        ChiTietDonDaGiao chiTietDonDaGiao = chiTietDonDaGiaoss.get(pos);
        holder.itemCTDDG_tvMaDonHang.setText(chiTietDonDaGiao.getMaDonHang());
        holder.itemCTDDG_tvTenKhachHang.setText(chiTietDonDaGiao.getTenKhachHang());
        holder.itemCTDDG_tvTenNhanVienDuyet.setText(chiTietDonDaGiao.getTenNhanVienDuyet());
        holder.itemCTDDG_tvTenNhanVienGiao.setText(chiTietDonDaGiao.getTenNhanVienGiao());
        holder.itemCTDDG_tvThoiGianLap.setText(chiTietDonDaGiao.getThoiGianLap());
        holder.itemCTDDG_tvThoiGianGiao.setText(chiTietDonDaGiao.getThoiGianGiao());
        holder.itemCTDDG_tvPhiVanChuyen.setText(formatter.format(chiTietDonDaGiao.getPhiVanChuyen()) + " VNĐ");
        holder.itemCTDDG_tvGiamGia.setText(formatter.format(chiTietDonDaGiao.getGiamGia()) + " VNĐ");
        holder.itemCTDDG_tvDiaChiGiao.setText(chiTietDonDaGiao.getDiaChiGiao());
        holder.itemCTDDG_tvHinhThucThanhToan.setText(chiTietDonDaGiao.getHinhThucThanhToan());
        holder.itemCTDDG_tvTrangThaiDon.setText(chiTietDonDaGiao.getTrangThaiDon());
        holder.itemCTDDG_tvTongTienThanhToan.setText(formatter.format(chiTietDonDaGiao.getTongTienThanhToan()) + " VNĐ");
    }

    @Override
    public int getItemCount() {
        return chiTietDonDaGiaoss.size();
    }

    @Override
    public int getItemViewType(int position) {
        return resource;
    }

    // ViewHolder definition
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemCTDDG_tvMaDonHang;
        TextView itemCTDDG_tvTenKhachHang;
        TextView itemCTDDG_tvTenNhanVienDuyet;
        TextView itemCTDDG_tvTenNhanVienGiao;
        TextView itemCTDDG_tvThoiGianLap;
        TextView itemCTDDG_tvThoiGianGiao;
        TextView itemCTDDG_tvPhiVanChuyen;
        TextView itemCTDDG_tvGiamGia;
        TextView itemCTDDG_tvDiaChiGiao;
        TextView itemCTDDG_tvHinhThucThanhToan;
        TextView itemCTDDG_tvTrangThaiDon;
        TextView itemCTDDG_tvTongTienThanhToan;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemCTDDG_tvMaDonHang = itemView.findViewById(R.id.itemCTDDG_tvMaDonHang);
            itemCTDDG_tvTenKhachHang = itemView.findViewById(R.id.itemCTDDG_tvTenKhachHang);
            itemCTDDG_tvTenNhanVienDuyet = itemView.findViewById(R.id.itemCTDDG_tvTenNhanVienDuyet);
            itemCTDDG_tvTenNhanVienGiao = itemView.findViewById(R.id.itemCTDDG_tvTenNhanVienGiao);
            itemCTDDG_tvThoiGianLap = itemView.findViewById(R.id.itemCTDDG_tvThoiGianLap);
            itemCTDDG_tvThoiGianGiao = itemView.findViewById(R.id.itemCTDDG_tvThoiGianGiao);
            itemCTDDG_tvPhiVanChuyen = itemView.findViewById(R.id.itemCTDDG_tvPhiVanChuyen);
            itemCTDDG_tvGiamGia = itemView.findViewById(R.id.itemCTDDG_tvGiamGia);
            itemCTDDG_tvDiaChiGiao = itemView.findViewById(R.id.itemCTDDG_tvDiaChiGiao);
            itemCTDDG_tvHinhThucThanhToan = itemView.findViewById(R.id.itemCTDDG_tvHinhThucThanhToan);
            itemCTDDG_tvTrangThaiDon = itemView.findViewById(R.id.itemCTDDG_tvTrangThaiDon);
            itemCTDDG_tvTongTienThanhToan = itemView.findViewById(R.id.itemCTDDG_tvTongTienThanhToan);
        }
    }
}
