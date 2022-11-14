package com.example.nhasachonline.adapters;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.item.ItemChiTietDonHangNV;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class ChiTietDonHangNVRecyclerViewAdapter extends RecyclerView.Adapter<ChiTietDonHangNVRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<ItemChiTietDonHangNV> itemChiTietDonHangNVS;

    public ChiTietDonHangNVRecyclerViewAdapter(Activity context, int resource, ArrayList<ItemChiTietDonHangNV> itemChiTietDonHangNVS){
        this.context = context;
        this.resource = resource;
        this.itemChiTietDonHangNVS = itemChiTietDonHangNVS;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiTietDonHangNVRecyclerViewAdapter.MyViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        final int pos = position;
        ItemChiTietDonHangNV itemChiTietDonHangNV = itemChiTietDonHangNVS.get(pos);
        holder.itemCTDH_NV_txtTenSanPham.setText(itemChiTietDonHangNV.getTenSanPham());
        holder.itemCTDH_NV_txtSoLuong.setText(itemChiTietDonHangNV.getSoLuong() + " ");
        holder.itemCTDH_NV_txtDonGia.setText(formatter.format(itemChiTietDonHangNV.getDonGia()) + " VNĐ");
        holder.itemCTDH_NV_txtTongTien.setText(formatter.format(itemChiTietDonHangNV.getTongTien()) + " VNĐ");

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(itemChiTietDonHangNV.getHinhSanPham());
        try {
            File file = null;
            if (itemChiTietDonHangNV.getHinhSanPham().contains("png")) {
                file = File.createTempFile(itemChiTietDonHangNV.getHinhSanPham().substring(0,itemChiTietDonHangNV.getHinhSanPham().length()-4), "png");
            } else if (itemChiTietDonHangNV.getHinhSanPham().contains("jpg")) {
                file = File.createTempFile(itemChiTietDonHangNV.getHinhSanPham().substring(0,itemChiTietDonHangNV.getHinhSanPham().length()-4), "jpg");
            }
            final File fileHinh = file;
            ((StorageReference) storageReference).getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    holder.itemCTDH_NV_imgHinhSanPham.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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
    }

    @Override
    public int getItemCount() {
        return itemChiTietDonHangNVS.size();
    }

    public int getItemViewType(int position){
        return  resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemCTDH_NV_txtTenSanPham;
        TextView itemCTDH_NV_txtSoLuong;
        TextView itemCTDH_NV_txtDonGia;
        TextView itemCTDH_NV_txtTongTien;
        ImageView itemCTDH_NV_imgHinhSanPham;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemCTDH_NV_txtTenSanPham = itemView.findViewById(R.id.itemCTDH_NV_txtTenSanPham);
            itemCTDH_NV_txtSoLuong = itemView.findViewById(R.id.itemCTDH_NV_txtSoLuong);
            itemCTDH_NV_txtDonGia = itemView.findViewById(R.id.itemCTDH_NV_txtDonGia);
            itemCTDH_NV_txtTongTien = itemView.findViewById(R.id.itemCTDH_NV_txtTongTien);
            itemCTDH_NV_imgHinhSanPham = itemView.findViewById(R.id.itemCTDH_NV_imgHinhSanPham);
        }
    }
}