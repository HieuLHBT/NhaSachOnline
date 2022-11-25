package com.example.nhasachonline.adapters;

import static android.service.controls.ControlsProviderService.TAG;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.activity.ManHinhQuanLySanPhamActivity;
import com.example.nhasachonline.activity.SuaSanPhamSachActivity;
import com.example.nhasachonline.item.ItemSach;
import com.example.nhasachonline.item.ItemSanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class SachRecyclerViewAdapter extends RecyclerView.Adapter<SachRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<ItemSach> saches;
    private SachRecyclerViewAdapter.OnItemClickListener onItemClickListener;

    public SachRecyclerViewAdapter(Activity context, int resource, ArrayList<ItemSach> saches) {
        this.context = context;
        this.resource = resource;
        this.saches= saches;
    }
    public void setFilteredList1(ArrayList<ItemSach> filteredList1){
        this.saches = filteredList1;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull SachRecyclerViewAdapter.MyViewHolder holder, int position) {


        DecimalFormat formatter = new DecimalFormat("#,###,###");
        final int pos = position;
        ItemSach sach = saches.get(pos);
//        StorageReference mImageRef = FirebaseStorage.getInstance().getReference(sach.getAnhSach());
//        ImageView imageView = holder.itemMHQLSP_anhSanPham;
//        final long ONE_MEGABYTE = 1024 * 1024;
//
        holder.itemMHQLSP_tvMaSach.setText(sach.getMaSach());
        holder.itemMHQLSP_tvTenSach.setText(sach.getTenSach());
        holder.itemMHQLSP_tvTheLoaiSach.setText(sach.getTheLoai());
        holder.itemMHQLSP_tvTacGia.setText(sach.getTacGia());
        holder.itemMHQLSP_tvNhaXuatBan.setText(sach.getNhaXuatBan());
        holder.itemMHQLSP_tvNgayXuatBan.setText(sach.getNamSanXuat());
        holder.itemMHQLSP_tvGiaTien.setText(String.valueOf(sach.getGiaTien()));
        holder.itemMHQLSP_tvSoLuongKho.setText(String.valueOf(sach.getSoLuongKho()));
        holder.itemMHQLSP_btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: haha");
                Intent intent = new Intent(context, SuaSanPhamSachActivity.class);
//                intent.putExtra("sach", sach.getMaSach());
                intent.putExtra("sach", sach);
                context.startActivity(intent);
            }
        });
        loadImage(sach,holder);

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
    private  void loadImage(ItemSach sach,SachRecyclerViewAdapter.MyViewHolder holder){
        try {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference(sach.getAnhSach());
            File file = null;
            if (sach.getAnhSach().contains("png")) {
                file = File.createTempFile(sach.getAnhSach().substring(0,sach.getAnhSach().length()-4), "png");
            } else if (sach.getAnhSach().contains("jpg")) {
                file = File.createTempFile(sach.getAnhSach().substring(0,sach.getAnhSach().length()-4), "jpg");
            }
            final File fileHinh = file;
            ((StorageReference) storageReference).getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    holder.itemMHQLSP_anhSanPham.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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
        return saches.size();
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
        Button itemMHQLSP_btnSua;

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
            itemMHQLSP = itemView.findViewById(R.id.itemMHQLSP1);
            itemMHQLSP_btnSua = itemView.findViewById(R.id.itemMHQLSP_btnSua);


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

    public void setOnItemClickListener(SachRecyclerViewAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
