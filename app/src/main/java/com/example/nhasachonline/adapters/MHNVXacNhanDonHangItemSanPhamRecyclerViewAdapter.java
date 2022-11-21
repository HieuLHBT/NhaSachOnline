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
import com.example.nhasachonline.item.ItemXacNhanDonHangNVSanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MHNVXacNhanDonHangItemSanPhamRecyclerViewAdapter extends RecyclerView.Adapter<MHNVXacNhanDonHangItemSanPhamRecyclerViewAdapter.MyViewHolder> {

    private Activity context;
    private int resource;
    private ArrayList<ItemXacNhanDonHangNVSanPham> itemXacNhanDonHangSanPham;

    public MHNVXacNhanDonHangItemSanPhamRecyclerViewAdapter(Activity context, int resource, ArrayList<ItemXacNhanDonHangNVSanPham> itemXacNhanDonHangSanPham) {
        this.context = context;
        this.resource = resource;
        this.itemXacNhanDonHangSanPham = itemXacNhanDonHangSanPham;

    }

    public void setItemXacNhanDonHangSanPham(ArrayList<ItemXacNhanDonHangNVSanPham> itemXacNhanDonHangSanPham) {
        this.itemXacNhanDonHangSanPham = itemXacNhanDonHangSanPham;
    }

    public ArrayList<ItemXacNhanDonHangNVSanPham> getItemXacNhanDonHangSanPham() {
        return itemXacNhanDonHangSanPham;
    }

    @NonNull
    @Override
    public MHNVXacNhanDonHangItemSanPhamRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MHNVXacNhanDonHangItemSanPhamRecyclerViewAdapter.MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MHNVXacNhanDonHangItemSanPhamRecyclerViewAdapter.MyViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        final int pos = position;
        ItemXacNhanDonHangNVSanPham itemXacNhanDonHangNVSanPham = itemXacNhanDonHangSanPham.get(pos);
        holder.itemMHNV_XNDH_tvTenSanPham.setText(itemXacNhanDonHangNVSanPham.getTenSanPham());
        holder.itemMHNV_XNDH_tvGiaTien.setText(formatter.format(itemXacNhanDonHangNVSanPham.getDonGia()) + " VNĐ");
        holder.itemMHNV_XNDH_tvSoLuong.setText(itemXacNhanDonHangNVSanPham.getSoLuong() + "");
        StorageReference storageReference = FirebaseStorage.getInstance().getReference(itemXacNhanDonHangNVSanPham.getAnhSanPham());
        try {
            File file = null;
            if (itemXacNhanDonHangNVSanPham.getAnhSanPham().contains("png")) {
                file = File.createTempFile(itemXacNhanDonHangNVSanPham.getAnhSanPham().substring(0, itemXacNhanDonHangNVSanPham.getAnhSanPham().length() - 4), "png");
            } else if (itemXacNhanDonHangNVSanPham.getAnhSanPham().contains("jpg")) {
                file = File.createTempFile(itemXacNhanDonHangNVSanPham.getAnhSanPham().substring(0, itemXacNhanDonHangNVSanPham.getAnhSanPham().length() - 4), "jpg");
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
        return 0;
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
