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

import com.example.nhasachonline.activity.SuaSanPhamVPPActivity;
import com.example.nhasachonline.item.ItemSach;
import com.example.nhasachonline.item.ItemVanPhongPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class VPPRecyclerViewAdapter extends RecyclerView.Adapter<VPPRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<ItemVanPhongPham> vanPhongPhams;
    private VPPRecyclerViewAdapter.OnItemClickListener onItemClickListener;

    public VPPRecyclerViewAdapter(Activity context, int resource, ArrayList<ItemVanPhongPham> vanPhongPhams) {
        this.context = context;
        this.resource = resource;
        this.vanPhongPhams = vanPhongPhams;
    }
    public void setFilteredList2(ArrayList<ItemVanPhongPham> filteredList2){
        this.vanPhongPhams = filteredList2;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public VPPRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull VPPRecyclerViewAdapter.MyViewHolder holder, int position) {

        DecimalFormat formatter = new DecimalFormat("#,###,###");
        final int pos = position;
        ItemVanPhongPham vanPhongPham = vanPhongPhams.get(pos);
        holder.itemMHQLSP2_tvMaVPP.setText(vanPhongPham.getMaVanPhongPham());
        holder.itemMHQLSP2_tvTenVPP.setText(vanPhongPham.getTenVanPhongPham());
        holder.itemMHQLSP2_tvNhaPhanPhoi.setText(vanPhongPham.getNhaPhanPhoi());
        holder.itemMHQLSP2_tvXuatXu.setText(vanPhongPham.getXuatXu());
        holder.itemMHQLSP2_tvDonVi.setText(vanPhongPham.getDonVi());
        holder.itemMHQLSP2_tvGiaTien.setText(String.valueOf(vanPhongPham.getGiaTien()));
        holder.itemMHQLSP2_tvSoLuongKho.setText(String.valueOf(vanPhongPham.getSoLuongKho()));

        holder.itemMHQLSP2_btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: haha");
                Intent intent = new Intent(context, SuaSanPhamVPPActivity.class);
                intent.putExtra("vpp", vanPhongPham);
                context.startActivity(intent);
            }
        });
//        loadImage(vanPhongPham,holder);
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
//    private  void loadImage(ItemVanPhongPham vanPhongPham, VPPRecyclerViewAdapter.MyViewHolder holder){
//        try {
//            StorageReference storageReference = FirebaseStorage.getInstance().getReference(vanPhongPham.getAnhVanPhongPham());
//            File file = null;
//            if (vanPhongPham.getAnhVanPhongPham().contains("png")) {
//                file = File.createTempFile(vanPhongPham.getAnhVanPhongPham().substring(0,vanPhongPham.getAnhVanPhongPham().length()-4), "png");
//            } else if (vanPhongPham.getAnhVanPhongPham().contains("jpg")) {
//                file = File.createTempFile(vanPhongPham.getAnhVanPhongPham().substring(0,vanPhongPham.getAnhVanPhongPham().length()-4), "jpg");
//            }
//            final File fileHinh = file;
//            ((StorageReference) storageReference).getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                    holder.itemMHQLSP2_anhSanPham.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Log.d("onCancelled", "Lỗi!" + e.getMessage());
//                }
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    @Override
    public int getItemCount() {
        return vanPhongPhams.size();
    }

    @Override
    public int getItemViewType(int position) {
        return resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemMHQLSP2_tvMaVPP;
        TextView itemMHQLSP2_tvTenVPP;
        TextView itemMHQLSP2_tvNhaPhanPhoi;
        TextView itemMHQLSP2_tvXuatXu;
        TextView itemMHQLSP2_tvDonVi;
        TextView itemMHQLSP2_tvGiaTien;
        TextView itemMHQLSP2_tvSoLuongKho;
        ImageView itemMHQLSP2_anhSanPham;
        View.OnClickListener onClickListener;
        CardView itemMHQLSP2;
        Button itemMHQLSP2_btnSua;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemMHQLSP2_tvMaVPP = itemView.findViewById(R.id.itemMHQLSP2_tvMaVPP);
            itemMHQLSP2_tvTenVPP = itemView.findViewById(R.id.itemMHQLSP2_tvTenVPP);
            itemMHQLSP2_tvNhaPhanPhoi = itemView.findViewById(R.id.itemMHQLSP2_tvXuatXu);
            itemMHQLSP2_tvXuatXu = itemView.findViewById(R.id.itemMHQLSP2_tvXuatXu);
            itemMHQLSP2_tvDonVi = itemView.findViewById(R.id.itemMHQLSP2_tvDonVi);
            itemMHQLSP2_tvGiaTien = itemView.findViewById(R.id.itemMHQLSP2_tvGiaTien);
            itemMHQLSP2_tvSoLuongKho = itemView.findViewById(R.id.itemMHQLSP2_tvSoLuongKho);


            itemMHQLSP2_anhSanPham = itemView.findViewById(R.id.itemMHQLSP2_anhSanPham);
            itemMHQLSP2 = itemView.findViewById(R.id.itemMHQLSP2);
            itemMHQLSP2_btnSua = itemView.findViewById(R.id.itemMHQLSP2_btnSua);

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

    public void setOnItemClickListener(VPPRecyclerViewAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
