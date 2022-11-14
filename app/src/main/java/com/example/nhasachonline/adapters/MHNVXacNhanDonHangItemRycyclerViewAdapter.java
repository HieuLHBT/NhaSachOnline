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
import com.example.nhasachonline.item.ItemXacNhanDonHangNV;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class MHNVXacNhanDonHangItemRycyclerViewAdapter extends RecyclerView.Adapter<MHNVXacNhanDonHangItemRycyclerViewAdapter.MyViewHolder>{
    private Activity context;
    private int resource;
    private ArrayList<ItemXacNhanDonHangNV> itemXacNhanDonHangs;

    public MHNVXacNhanDonHangItemRycyclerViewAdapter(Activity context, int resource, ArrayList<ItemXacNhanDonHangNV> itemXacNhanDonHangs) {
        this.context = context;
        this.resource = resource;
        this.itemXacNhanDonHangs = itemXacNhanDonHangs;
    }

    @NonNull
    @Override
    public MHNVXacNhanDonHangItemRycyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MHNVXacNhanDonHangItemRycyclerViewAdapter.MyViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        final int pos = position;
        ItemXacNhanDonHangNV itemXacNhanDonHangNV = itemXacNhanDonHangs.get(pos);
        holder.itemMHNV_XNDH_tvTenSanPham.setText(itemXacNhanDonHangNV.getTenSanPham());
        holder.itemMHNV_XNDH_tvGiaTien.setText(formatter.format(itemXacNhanDonHangNV.getDonGia()) + " VNĐ");
        holder.itemMHNV_XNDH_tvSoLuong.setText(itemXacNhanDonHangNV.getSoLuong() + "");
        StorageReference storageReference = FirebaseStorage.getInstance().getReference(itemXacNhanDonHangNV.getAnhSanPham());
        try {
            File file = null;
            if (itemXacNhanDonHangNV.getAnhSanPham().contains("png")) {
                file = File.createTempFile(itemXacNhanDonHangNV.getAnhSanPham().substring(0, itemXacNhanDonHangNV.getAnhSanPham().length() - 4), "png");
            } else if (itemXacNhanDonHangNV.getAnhSanPham().contains("jpg")) {
                file = File.createTempFile(itemXacNhanDonHangNV.getAnhSanPham().substring(0, itemXacNhanDonHangNV.getAnhSanPham().length() - 4), "jpg");
            }
            final File fileHinh = file;
            storageReference.getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    holder.itemMHNV_XNDH_tvAnhSanPham.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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
        return itemXacNhanDonHangs.size();
    }

    @Override
    public int getItemViewType(int position) {
        return resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemMHNV_XNDH_tvTenSanPham;
        TextView itemMHNV_XNDH_tvSoLuong;
        TextView itemMHNV_XNDH_tvGiaTien;
        ImageView itemMHNV_XNDH_tvAnhSanPham;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemMHNV_XNDH_tvTenSanPham = itemView.findViewById(R.id.itemMHNV_XNDH_tvTenSanPham);
            itemMHNV_XNDH_tvSoLuong = itemView.findViewById(R.id.itemMHNV_XNDH_tvSoLuong);
            itemMHNV_XNDH_tvGiaTien = itemView.findViewById(R.id.itemMHNV_XNDH_tvDonGia);
            itemMHNV_XNDH_tvAnhSanPham = itemView.findViewById(R.id.itemMHQLNV_anhSanPham);
        }
    }


}
