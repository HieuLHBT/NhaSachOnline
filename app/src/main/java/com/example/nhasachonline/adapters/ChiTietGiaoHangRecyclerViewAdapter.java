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
import com.example.nhasachonline.doituong.ChiTietGiaoHang;

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
        /* TextView itemCTGH_imgHinhSanPham;
        *TextView itemCTGH_txtMaDonHang;
         TextView itemCTGH_txtTenNhanVienGiaoHang;
         TextView itemCTGH_txtNgayDuKienGiao;
         TextView itemCTGH_txtThangDuKienGiao;
         TextView itemCTGH_txtNamDuKienGiao;
         TextView itemCTGH_txtNgayDatHang;
         TextView itemCTGH_txtThangDatHang;
         TextView itemCTGH_txtNamDatHang;
         TextView itemCTGH_txtPhuongThucThanhToan;
         TextView itemCTGH_txtTongTienHang;
         TextView itemCTGH_txtPhiVanChuyen;
         TextView itemCTGH_txtGiamGia;
         TextView itemCTGH_txtTongTienThanhToan;*/
        LinearLayout itemCTGH_llCardView;
        CardView itemCTGH;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemCTGH_txtTenSanPham = itemView.findViewById(R.id.itemCTGH_txtTenSanPham);
            itemCTGH_txtGiaTien = itemView.findViewById(R.id.itemCTGH_txtGiaTien);
            itemCTGH_txtSoLuong = itemView.findViewById(R.id.itemCTGH_txtSoLuong);
          /*  itemCTGH_imgHinhSanPham = itemView.findViewById(R.id.itemCTGH_imgHinhSanPham);
            itemCTGH_txtMaDonHang = itemView.findViewById(R.id.itemCTGH_txtMaDonHang);
            itemCTGH_txtTenNhanVienGiaoHang = itemView.findViewById(R.id.itemCTGH_txtTenNhanVienGiaoHang);
            itemCTGH_txtNgayDuKienGiao = itemView.findViewById(R.id.itemCTGH_txtNgayDuKienGiao);
            itemCTGH_txtThangDuKienGiao = itemView.findViewById(R.id.itemCTGH_txtThangDuKienGiao);
            itemCTGH_txtNamDuKienGiao = itemView.findViewById(R.id.itemCTGH_txtNamDuKienGiao);
            itemCTGH_txtNgayDatHang = itemView.findViewById(R.id.itemCTGH_txtNgayDatHang);
            itemCTGH_txtThangDatHang = itemView.findViewById(R.id.itemCTGH_txtThangDatHang);
            itemCTGH_txtNamDatHang = itemView.findViewById(R.id.itemCTGH_txtNamDatHang);
            itemCTGH_txtPhuongThucThanhToan = itemView.findViewById(R.id.itemCTGH_txtPhuongThucThanhToan);
            itemCTGH_txtTongTienHang = itemView.findViewById(R.id.itemCTGH_txtTongTienHang);
            itemCTGH_txtPhiVanChuyen = itemView.findViewById(R.id.itemCTGH_txtPhiVanChuyen);
            itemCTGH_txtGiamGia = itemView.findViewById(R.id.itemCTGH_txtGiamGia);
            itemCTGH_txtTongTienThanhToan = itemView.findViewById(R.id.itemCTGH_txtTongTienThanhToan);
            itemCTGH_txtPhiVanChuyen = itemView.findViewById(R.id.itemCTGH_txtPhiVanChuyen);

      */
            itemCTGH_llCardView = itemView.findViewById(R.id.itemCTGH_llCardView);
            itemCTGH = itemView.findViewById(R.id.itemCTGH);
        }
    }
}
