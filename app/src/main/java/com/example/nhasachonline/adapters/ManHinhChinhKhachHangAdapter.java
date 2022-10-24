package com.example.nhasachonline.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.doituong.Sach;

import java.util.ArrayList;


public class ManHinhChinhKhachHangAdapter extends RecyclerView.Adapter<ManHinhChinhKhachHangAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<Sach> books;
    private GioHangRecyclerViewAdapter.OnItemClickListener onItemClickListener;

    public ManHinhChinhKhachHangAdapter(Activity context, int resource, ArrayList<Sach> books) {
        this.context = context;
        this.resource = resource;
        this.books = books;
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
        Sach book = books.get(pos);
        holder.itemMHCKH_tvTenSanPham.setText(book.getTenSach());
        holder.itemMHCKH_tvGia.setText(book.getGia() + "");
        holder.itemMHCKH_tvSoLuong.setText(book.getSoLuong() + "");
        holder.itemMHCKH_tvDanhGia.setText(book.getsoLuongdanhGia() + "");


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
        return books.size();
    }

    @Override
    public int getItemViewType(int position) {
        return resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemMHCKH_tvTenSanPham;
        TextView itemMHCKH_tvGia;
        TextView itemMHCKH_tvDanhGia;
        TextView itemMHCKH_tvSoLuong;
        ImageView itemMHCKH_imgHinhSanPham;
        ImageView itemMHCKH_img1Sao;
        ImageView itemMHCKH_img2Sao;
        ImageView itemMHCKH_img3Sao;
        ImageView itemMHCKH_img4Sao;
        ImageView itemMHCKH_img5Sao;
        ImageButton itemMHCKH_btnThemVaoGioHang;
        View.OnClickListener onClickListener;
        LinearLayout itemMHCKH_llCardView;
        CardView itemMHCKH;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemMHCKH_tvTenSanPham = itemView.findViewById(R.id.itemMHCKH_tvTenSanPham);
            itemMHCKH_tvGia = itemView.findViewById(R.id.itemMHCKH_tvGiaTien);
            itemMHCKH_tvDanhGia = itemView.findViewById(R.id.itemMHCKH_tvSLDanhGia);
            itemMHCKH_tvSoLuong = itemView.findViewById(R.id.itemMHCKH_tvSoLuong);
            itemMHCKH_imgHinhSanPham = itemView.findViewById(R.id.itemMHCKH_imgAnhSanPham);
            itemMHCKH_img1Sao = itemView.findViewById(R.id.itemMHCKH_img1Sao);
            itemMHCKH_img2Sao = itemView.findViewById(R.id.itemMHCKH_img2Sao);
            itemMHCKH_img3Sao = itemView.findViewById(R.id.itemMHCKH_img3Sao);
            itemMHCKH_img4Sao = itemView.findViewById(R.id.itemMHCKH_img4Sao);
            itemMHCKH_img5Sao = itemView.findViewById(R.id.itemMHCKH_img5Sao);
            itemMHCKH_btnThemVaoGioHang = itemView.findViewById(R.id.itemMHCKH_btnThemGioHang);
            itemMHCKH_llCardView = itemView.findViewById(R.id.itemMHCKH_llCardView);
            itemMHCKH = itemView.findViewById(R.id.itemManHinhChinhKH);

            // Set event processing
            itemMHCKH_btnThemVaoGioHang.setOnClickListener(this);
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

    public void setOnItemClickListener(GioHangRecyclerViewAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
