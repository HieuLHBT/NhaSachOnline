package com.example.nhasachonline.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.item.ItemManHinhChinhNhanVien;

import java.util.ArrayList;

public class ManHinhChinhNhanVienRecyclerViewAdapter extends RecyclerView.Adapter<ManHinhChinhNhanVienRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<ItemManHinhChinhNhanVien> itemManHinhChinhNhanViens;

    public ManHinhChinhNhanVienRecyclerViewAdapter(Activity context, int resource, ArrayList<ItemManHinhChinhNhanVien> itemManHinhChinhNhanViens) {
        this.context = context;
        this.resource = resource;
        this.itemManHinhChinhNhanViens = itemManHinhChinhNhanViens;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ManHinhChinhNhanVienRecyclerViewAdapter.MyViewHolder holder, int position) {
        final int pos = position;
        ItemManHinhChinhNhanVien itemManHinhChinhNhanVien = itemManHinhChinhNhanViens.get(pos);
        holder.itemMHCNV_NV_txtHoTen.setText(itemManHinhChinhNhanVien.getTenNhanVien());
        holder.itemMHCNV_NV_txtMaNhanVien.setText(itemManHinhChinhNhanVien.getMaNhanVien());

        /*StorageReference storageReference = FirebaseStorage.getInstance().getReference(itemManHinhChinhNhanVien.getHinhNhanVien());
        try {
            File file = null;
            if (itemManHinhChinhNhanVien.getHinhNhanVien().contains("png")) {
                file = File.createTempFile(itemManHinhChinhNhanVien.getHinhNhanVien().substring(0, itemManHinhChinhNhanVien.getHinhNhanVien().length() - 4), "png");
            } else if (itemManHinhChinhNhanVien.getHinhNhanVien().contains("jpg")) {
                file = File.createTempFile(itemManHinhChinhNhanVien.getHinhNhanVien().substring(0, itemManHinhChinhNhanVien.getHinhNhanVien().length() - 4), "jpg");
            }
            final File fileHinh = file;
            storageReference.getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    holder.itemMHCNV_NV_imgHinhNhanVien.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("onCancelled", "Lỗi!" + e.getMessage());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }

    @Override
    public int getItemCount() {
        return itemManHinhChinhNhanViens.size();
    }

    @Override
    public int getItemViewType(int position) {
        return resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //ImageView itemMHCNV_NV_imgHinhNhanVien;
        TextView itemMHCNV_NV_txtHoTen;
        TextView itemMHCNV_NV_txtMaNhanVien;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //itemMHCNV_NV_imgHinhNhanVien = itemView.findViewById(R.id.itemMHCNV_NV_imgHinhNhanVien);
            itemMHCNV_NV_txtHoTen = itemView.findViewById(R.id.itemMHCNV_NV_txtHoTen);
            itemMHCNV_NV_txtMaNhanVien = itemView.findViewById(R.id.itemMHCNV_NV_txtMaNhanVien);

        }
    }
}