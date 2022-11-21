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
import com.example.nhasachonline.item.ChiTietTheoDoiDonHang;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietTheoDoiDonHangRecyclerViewAdapter extends RecyclerView.Adapter<ChiTietTheoDoiDonHangRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<ChiTietTheoDoiDonHang> chiTietTheoDoiDonHangs;

    public ChiTietTheoDoiDonHangRecyclerViewAdapter(Activity context, int resource, ArrayList<ChiTietTheoDoiDonHang> chiTietTheoDoiDonHangs) {
        this.context = context;
        this.resource = resource;
        this.chiTietTheoDoiDonHangs = chiTietTheoDoiDonHangs;
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
        ChiTietTheoDoiDonHang chiTietTheoDoiDonHang = chiTietTheoDoiDonHangs.get(pos);
        holder.itemCTGH_tvTenSanPham.setText(chiTietTheoDoiDonHang.getTenSanPham());
        holder.itemCTGH_tvGiaTien.setText(formatter.format(chiTietTheoDoiDonHang.getGiaSanPham()) + " VNĐ");
        holder.itemCTGH_tvTongTien.setText(formatter.format(chiTietTheoDoiDonHang.getTongTien()) + " VNĐ");
        holder.itemCTGH_tvSoLuong.setText("(sl: " + chiTietTheoDoiDonHang.getSoLuong() + ")");

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(chiTietTheoDoiDonHang.getHinhSanPham());
        try {
            File file = null;
            if (chiTietTheoDoiDonHang.getHinhSanPham().contains("png")) {
                file = File.createTempFile(chiTietTheoDoiDonHang.getHinhSanPham().substring(0, chiTietTheoDoiDonHang.getHinhSanPham().length()-4), "png");
            } else if (chiTietTheoDoiDonHang.getHinhSanPham().contains("jpg")) {
                file = File.createTempFile(chiTietTheoDoiDonHang.getHinhSanPham().substring(0, chiTietTheoDoiDonHang.getHinhSanPham().length()-4), "jpg");
            }
            final File fileHinh = file;
            storageReference.getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    holder.itemCTGH_imgHinhSanPham.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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
        return chiTietTheoDoiDonHangs.size();
    }

    @Override
    public int getItemViewType(int position) {
        return resource;
    }

    // ViewHolder definition
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemCTGH_tvTenSanPham;
        TextView itemCTGH_tvGiaTien;
        TextView itemCTGH_tvSoLuong;
        TextView itemCTGH_tvTongTien;
        ImageView itemCTGH_imgHinhSanPham;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemCTGH_tvTenSanPham = itemView.findViewById(R.id.itemCTGH_tvTenSanPham);
            itemCTGH_tvGiaTien = itemView.findViewById(R.id.itemCTGH_tvGiaTien);
            itemCTGH_tvSoLuong = itemView.findViewById(R.id.itemCTGH_tvSoLuong);
            itemCTGH_tvTongTien = itemView.findViewById(R.id.itemCTGH_tvTongTien);
            itemCTGH_imgHinhSanPham = itemView.findViewById(R.id.itemCTGH_imgHinhSanPham);
        }
    }
}
