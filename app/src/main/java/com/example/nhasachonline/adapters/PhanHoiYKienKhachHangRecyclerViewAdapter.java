package com.example.nhasachonline.adapters;

import android.app.Activity;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.activity.PhanHoiYKienKhachHangActivity;
import com.example.nhasachonline.item.PhanHoiYKienKhachHang;

import java.util.ArrayList;

public class PhanHoiYKienKhachHangRecyclerViewAdapter extends RecyclerView.Adapter<PhanHoiYKienKhachHangRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<PhanHoiYKienKhachHang> phanHoiYKienKhachHangs;
    private OnItemClickListener onItemClickListener;
    private int chieuDaiMacDinh = 0;

    public PhanHoiYKienKhachHangRecyclerViewAdapter(Activity context, int resource, ArrayList<PhanHoiYKienKhachHang> phanHoiYKienKhachHangs) {
        this.context = context;
        this.resource = resource;
        this.phanHoiYKienKhachHangs = phanHoiYKienKhachHangs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        chieuDaiMacDinh = parent.getHeight();
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final int pos = position;
        PhanHoiYKienKhachHang phanHoiYKienKhachHang = phanHoiYKienKhachHangs.get(pos);
        holder.itemPHYKKH_tvMaSanPham.setText(phanHoiYKienKhachHang.getMaSanPham());
        holder.itemPHYKKH_tvTenSanPham.setText(phanHoiYKienKhachHang.getTenSanPham());
        holder.itemPHYKKH_tvMaDonHang.setText(phanHoiYKienKhachHang.getMaDonHang());
        holder.itemPHYKKH_tvTenKhachHang.setText(phanHoiYKienKhachHang.getTenKhachHang());
        holder.itemPHYKKH_tvDanhGia.setText(phanHoiYKienKhachHang.getDanhGia() + " sao");
        holder.itemPHYKKH_tvThoiGianBinhLuan.setText(phanHoiYKienKhachHang.getThoiGianBinhLuan());
        holder.itemPHYKKH_tvNoiDungBinhLuan.setText(phanHoiYKienKhachHang.getNoiDungBinhLuan());
        holder.itemPHYKKH_tvMaNhanVien.setText(phanHoiYKienKhachHang.getMaNhanVien());
        holder.itemPHYKKH_tvTenNhanVien.setText(phanHoiYKienKhachHang.getTenNhanVien());
        holder.itemPHYKKH_tvNoiDungTraLoi.setText(phanHoiYKienKhachHang.getNoiDungTraLoi());

        if (phanHoiYKienKhachHang.getMaNhanVien().equalsIgnoreCase("")) {
            holder.itemPHYKKH_llNhanVien.setVisibility(View.GONE);
            ViewGroup.LayoutParams layoutParams = holder.itemPHYKKH.getLayoutParams();
            layoutParams.height = 1100;
        } else {
            holder.itemPHYKKH_llNhanVien.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams layoutParams = holder.itemPHYKKH.getLayoutParams();
            layoutParams.height = chieuDaiMacDinh;
        }

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
        return phanHoiYKienKhachHangs.size();
    }

    @Override
    public int getItemViewType(int position) {
        return resource;
    }

    // ViewHolder definition
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemPHYKKH_tvMaSanPham;
        TextView itemPHYKKH_tvTenSanPham;
        TextView itemPHYKKH_tvMaDonHang;
        TextView itemPHYKKH_tvTenKhachHang;
        TextView itemPHYKKH_tvDanhGia;
        TextView itemPHYKKH_tvThoiGianBinhLuan;
        TextView itemPHYKKH_tvNoiDungBinhLuan;
        TextView itemPHYKKH_tvMaNhanVien;
        TextView itemPHYKKH_tvTenNhanVien;
        TextView itemPHYKKH_tvNoiDungTraLoi;
        TextView itemPHYKKH_tvMucLuc;
        View.OnClickListener onClickListener;
        LinearLayout itemPHYKKH_llNhanVien;
        CardView itemPHYKKH;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemPHYKKH_tvMaSanPham = itemView.findViewById(R.id.itemPHYKKH_tvMaSanPham);
            itemPHYKKH_tvTenSanPham = itemView.findViewById(R.id.itemPHYKKH_tvTenSanPham);
            itemPHYKKH_tvMaDonHang = itemView.findViewById(R.id.itemPHYKKH_tvMaDonHang);
            itemPHYKKH_tvTenKhachHang = itemView.findViewById(R.id.itemPHYKKH_tvTenKhachHang);
            itemPHYKKH_tvDanhGia = itemView.findViewById(R.id.itemPHYKKH_tvDanhGia);
            itemPHYKKH_tvThoiGianBinhLuan = itemView.findViewById(R.id.itemPHYKKH_tvThoiGianBinhLuan);
            itemPHYKKH_tvNoiDungBinhLuan = itemView.findViewById(R.id.itemPHYKKH_tvNoiDungBinhLuan);
            itemPHYKKH_tvMaNhanVien = itemView.findViewById(R.id.itemPHYKKH_tvMaNhanVien);
            itemPHYKKH_tvTenNhanVien = itemView.findViewById(R.id.itemPHYKKH_tvTenNhanVien);
            itemPHYKKH_tvNoiDungTraLoi = itemView.findViewById(R.id.itemPHYKKH_tvNoiDungTraLoi);
            itemPHYKKH_tvMucLuc = itemView.findViewById(R.id.itemPHYKKH_tvMucLuc);
            itemPHYKKH_llNhanVien = itemView.findViewById(R.id.itemPHYKKH_llNhanVien);
            itemPHYKKH = itemView.findViewById(R.id.itemPHYKKH);

            // Set event processing
            itemPHYKKH_tvMucLuc.setOnClickListener(this);
        }

        @Override
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

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
