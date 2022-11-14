package com.example.nhasachonline.adapters;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.item.ItemQuanLySanPhamNV;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class QuanLySanPhamNVRecyclerViewAdapter extends RecyclerView.Adapter<QuanLySanPhamNVRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<ItemQuanLySanPhamNV> itemQuanLySanPhamNVs;
    private OnItemClickListener onItemClickListener;

    public QuanLySanPhamNVRecyclerViewAdapter(Activity context, int resource, ArrayList<ItemQuanLySanPhamNV> itemQuanLySanPhamNVs){
        this.context = context;
        this.resource = resource;
        this.itemQuanLySanPhamNVs = itemQuanLySanPhamNVs;
    }

    public void setFilteredList(ArrayList<ItemQuanLySanPhamNV> filteredList){
        this.itemQuanLySanPhamNVs = filteredList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public QuanLySanPhamNVRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        final int pos = position;
        ItemQuanLySanPhamNV itemQuanLySanPhamNV = itemQuanLySanPhamNVs.get(pos);
        holder.itemQLSP_NV_txtMaSanPham.setText(itemQuanLySanPhamNV.getMaSanPham());
        holder.itemQLSP_NV_txtTenSanPham.setText(itemQuanLySanPhamNV.getTenSanPham());
        holder.itemQLSP_NV_txtGiaTien.setText(formatter.format(itemQuanLySanPhamNV.getGiaTien()) + " VNĐ");
        holder.itemQLSP_NV_txtSoLuongTrongKho.setText(itemQuanLySanPhamNV.getSoLuong() + " ");

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(itemQuanLySanPhamNV.getHinhSanPham());
        try {
            File file = null;
            if (itemQuanLySanPhamNV.getHinhSanPham().contains("png")) {
                file = File.createTempFile(itemQuanLySanPhamNV.getHinhSanPham().substring(0,itemQuanLySanPhamNV.getHinhSanPham().length()-4), "png");
            } else if (itemQuanLySanPhamNV.getHinhSanPham().contains("jpg")) {
                file = File.createTempFile(itemQuanLySanPhamNV.getHinhSanPham().substring(0,itemQuanLySanPhamNV.getHinhSanPham().length()-4), "jpg");
            }
            final File fileHinh = file;
            ((StorageReference) storageReference).getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    holder.itemQLSP_NV_imgHinhSanPham.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("onCancelled", "Lỗi!" + e.getMessage());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        return itemQuanLySanPhamNVs.size();
    }

    public int getItemViewType(int position){
        return  resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView itemQLSP_NV_txtMaSanPham;
        TextView itemQLSP_NV_txtTenSanPham;
        TextView itemQLSP_NV_txtGiaTien;
        TextView itemQLSP_NV_txtSoLuongTrongKho;
        ImageView itemQLSP_NV_imgHinhSanPham;
        Button itemQLSP_NV_btnKiemTraDonHang;
        LinearLayout itemQLSP_NV_llCardView;
        CardView itemQLSP_NV;
        View.OnClickListener onClickListener;


        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemQLSP_NV_txtMaSanPham = itemView.findViewById(R.id.itemQLSP_NV_txtMaSanPham);
            itemQLSP_NV_txtTenSanPham = itemView.findViewById(R.id.itemQLSP_NV_txtTenSanPham);
            itemQLSP_NV_txtGiaTien = itemView.findViewById(R.id.itemQLSP_NV_txtGiaTien);
            itemQLSP_NV_txtSoLuongTrongKho = itemView.findViewById(R.id.itemQLSP_NV_txtSoLuongTrongKho);
            itemQLSP_NV_imgHinhSanPham = itemView.findViewById(R.id.itemQLSP_NV_imgHinhSanPham);
            itemQLSP_NV_btnKiemTraDonHang = itemView.findViewById(R.id.itemQLSP_NV_btnKiemTraDonHang);
            itemQLSP_NV_llCardView = itemView.findViewById(R.id.itemQLSP_NV_llCardView);
            itemQLSP_NV = itemView.findViewById(R.id.itemQLSP_NV);

            itemQLSP_NV_btnKiemTraDonHang.setOnClickListener(this);
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
