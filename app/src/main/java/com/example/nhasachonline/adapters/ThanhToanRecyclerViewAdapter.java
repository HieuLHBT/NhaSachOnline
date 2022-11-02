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
import com.example.nhasachonline.item.ThanhToan;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ThanhToanRecyclerViewAdapter extends RecyclerView.Adapter<ThanhToanRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<ThanhToan> thanhToans;

    public ThanhToanRecyclerViewAdapter(Activity context, int resource, ArrayList<ThanhToan> thanhToans) {
        this.context = context;
        this.resource = resource;
        this.thanhToans = thanhToans;
    }

    @NonNull
    @Override
    public ThanhToanRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new ThanhToanRecyclerViewAdapter.MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        final int pos = position;
        ThanhToan thanhToan = thanhToans.get(pos);
        holder.itemTT_tvTenSanPham.setText(thanhToan.getTenSanPhan());
        holder.itemTT_tvGiaTien.setText(formatter.format(thanhToan.getGiaSanPham()) + " VNĐ");
        holder.itemTT_tvTongTien.setText(formatter.format(thanhToan.getTongTien()) + " VNĐ");
        holder.itemTT_tvSoLuong.setText("(sl: " + thanhToan.getSoLuong() + ")");

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(thanhToan.getHinhSanPham());
        try {
            File file = null;
            if (thanhToan.getHinhSanPham().contains("png")) {
                file = File.createTempFile(thanhToan.getHinhSanPham().substring(0,thanhToan.getHinhSanPham().length()-4), "png");
            } else if (thanhToan.getHinhSanPham().contains("jpg")) {
                file = File.createTempFile(thanhToan.getHinhSanPham().substring(0,thanhToan.getHinhSanPham().length()-4), "jpg");
            }
            final File fileHinh = file;
            storageReference.getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    holder.itemTT_imgHinhSanPham.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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
        return thanhToans.size();
    }

    @Override
    public int getItemViewType(int position) {
        return resource;
    }

    // ViewHolder definition
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemTT_tvTenSanPham;
        TextView itemTT_tvGiaTien;
        TextView itemTT_tvSoLuong;
        TextView itemTT_tvTongTien;
        ImageView itemTT_imgHinhSanPham;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTT_tvTenSanPham = itemView.findViewById(R.id.itemTT_tvTenSanPham);
            itemTT_tvGiaTien = itemView.findViewById(R.id.itemTT_tvGiaTien);
            itemTT_tvSoLuong = itemView.findViewById(R.id.itemTT_tvSoLuong);
            itemTT_tvTongTien = itemView.findViewById(R.id.itemTT_tvTongTien);
            itemTT_imgHinhSanPham = itemView.findViewById(R.id.itemTT_imgHinhSanPham);
        }
    }
}
