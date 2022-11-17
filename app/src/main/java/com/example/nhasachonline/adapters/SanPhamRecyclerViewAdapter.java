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
import com.example.nhasachonline.item.ItemNhanVien;
import com.example.nhasachonline.item.ItemSanPham;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamRecyclerViewAdapter extends RecyclerView.Adapter<SanPhamRecyclerViewAdapter.MyViewHolder> {

    private Activity context;
    private int resource;
    private ArrayList<ItemSanPham> sanPhams;
    private SanPhamRecyclerViewAdapter.OnItemClickListener onItemClickListener;

    public SanPhamRecyclerViewAdapter(Activity context, int resource, ArrayList<ItemSanPham> sanPhams) {
        this.context = context;
        this.resource = resource;
        this.sanPhams = sanPhams;
    }
    public void setFilteredList1(ArrayList<ItemSanPham> filteredList1){
        this.sanPhams = filteredList1;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamRecyclerViewAdapter.MyViewHolder holder, int position) {

        DecimalFormat formatter = new DecimalFormat("#,###,###");
        final int pos = position;
        ItemSanPham sanPham = sanPhams.get(pos);
        holder.itemMHQLSP_tvMaSach.setText(sanPham.getMaSanPham());
        holder.itemMHQLSP_tvTenSach.setText(sanPham.getTenSanPham());
        holder.itemMHQLSP_tvTheLoaiSach.setText(sanPham.getTheLoai());
        holder.itemMHQLSP_tvTacGia.setText(sanPham.getTacGia());
        holder.itemMHQLSP_tvNhaXuatBan.setText(sanPham.getNhaXuatBan());
        holder.itemMHQLSP_tvNgayXuatBan.setText(sanPham.getNamSanXuat());
        holder.itemMHQLSP_tvGiaTien.setText(sanPham.getGiaSanPham());
        holder.itemMHQLSP_tvSoLuongKho.setText(sanPham.getSoLuong());


        // Event processing
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
        return sanPhams.size();
    }

    @Override
    public int getItemViewType(int position) {
        return resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemMHQLSP_tvMaSach;
        TextView itemMHQLSP_tvTenSach;
        TextView itemMHQLSP_tvTheLoaiSach;
        TextView itemMHQLSP_tvTacGia;
        TextView itemMHQLSP_tvNhaXuatBan;
        TextView itemMHQLSP_tvNgayXuatBan;
        TextView itemMHQLSP_tvGiaTien;
        TextView itemMHQLSP_tvSoLuongKho;
        ImageView itemMHQLSP_anhSanPham;
        View.OnClickListener onClickListener;
        CardView itemMHQLSP;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemMHQLSP_tvMaSach = itemView.findViewById(R.id.itemMHQLSP_tvMaSach);
            itemMHQLSP_tvTenSach = itemView.findViewById(R.id.itemMHQLSP_tvTenSach);
            itemMHQLSP_tvTheLoaiSach = itemView.findViewById(R.id.itemMHQLSP_tvTheLoaiSach);
            itemMHQLSP_tvTacGia = itemView.findViewById(R.id.itemMHQLSP_tvTacGia);
            itemMHQLSP_tvNhaXuatBan = itemView.findViewById(R.id.itemMHQLSP_tvNhaXuatBan);
            itemMHQLSP_tvNgayXuatBan = itemView.findViewById(R.id.itemMHQLSP_tvNgayXuatBan);
            itemMHQLSP_tvGiaTien = itemView.findViewById(R.id.itemMHQLSP_tvGiaTien);
            itemMHQLSP_tvSoLuongKho = itemView.findViewById(R.id.itemMHQLSP_tvSoLuongKho);

            itemMHQLSP_anhSanPham = itemView.findViewById(R.id.itemMHQLSP_anhSanPham);
            itemMHQLSP = itemView.findViewById(R.id.itemMHQLSP);


            // Set event processing

        }

        public void onClick(View view) {
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    // Interface for event processing
    public interface OnItemClickListener {
        void onItemClickListener(int position, View view);
    }

    public void setOnItemClickListener(SanPhamRecyclerViewAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
