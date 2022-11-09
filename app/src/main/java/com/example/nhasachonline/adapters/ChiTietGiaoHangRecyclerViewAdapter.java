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
import com.example.nhasachonline.item.ChiTietGiaoHang;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
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
    public ChiTietGiaoHangRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        final int pos = position;
        ChiTietGiaoHang chiTietGiaoHang = chiTietGiaoHangs.get(pos);
        holder.itemCTGH_txtTenSanPham.setText(chiTietGiaoHang.getTenSanPham());
        holder.itemCTGH_txtGiaTien.setText(formatter.format(chiTietGiaoHang.getGiaSanPham()) + " VNĐ");
        holder.itemCTGH_txtSoLuong.setText("(sl: " + chiTietGiaoHang.getSoLuong() + ")");
        holder.itemCTGH_txtTongTien.setText(formatter.format(chiTietGiaoHang.getTongTien()) + " VNĐ");

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(chiTietGiaoHang.getHinhSanPham());
        try {
            File file = null;
            if (chiTietGiaoHang.getHinhSanPham().contains("png")) {
                file = File.createTempFile(chiTietGiaoHang.getHinhSanPham().substring(0,chiTietGiaoHang.getHinhSanPham().length()-4), "png");
            } else if (chiTietGiaoHang.getHinhSanPham().contains("jpg")) {
                file = File.createTempFile(chiTietGiaoHang.getHinhSanPham().substring(0,chiTietGiaoHang.getHinhSanPham().length()-4), "jpg");
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
        return chiTietGiaoHangs.size();
    }

    public int getItemViewType(int position){
        return  resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemCTGH_txtTenSanPham;
        TextView itemCTGH_txtGiaTien;
        TextView itemCTGH_txtSoLuong;
        ImageView itemCTGH_imgHinhSanPham;
        TextView itemCTGH_txtTongTien;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemCTGH_txtTenSanPham = itemView.findViewById(R.id.itemCTGH_txtTenSanPham);
            itemCTGH_txtGiaTien = itemView.findViewById(R.id.itemCTGH_txtGiaTien);
            itemCTGH_txtSoLuong = itemView.findViewById(R.id.itemCTGH_txtSoLuong);
            itemCTGH_imgHinhSanPham = itemView.findViewById(R.id.itemCTGH_imgHinhSanPham);
            itemCTGH_txtTongTien = itemView.findViewById(R.id.itemCTGH_txtTongTien);
        }
    }

}
