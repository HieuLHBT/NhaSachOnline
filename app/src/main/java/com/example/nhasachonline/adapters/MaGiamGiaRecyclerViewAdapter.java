package com.example.nhasachonline.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.item.MaGiamGia;

import java.util.ArrayList;

public class MaGiamGiaRecyclerViewAdapter extends RecyclerView.Adapter<MaGiamGiaRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<MaGiamGia> maGiamGias;
    private OnItemClickListener onItemClickListener;

    public MaGiamGiaRecyclerViewAdapter(Activity context, int resource, ArrayList<MaGiamGia> maGiamGias){
        this.context = context;
        this.resource = resource;
        this.maGiamGias = maGiamGias;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MaGiamGiaRecyclerViewAdapter.MyViewHolder holder, int position) {
        final int pos = position;
        MaGiamGia maGiamGia = maGiamGias.get(pos);
        holder.itemMGG_txtTieuDe.setText(maGiamGia.getTieuDeMaGiamGia());
        holder.itemMGG_txtTienGiamGia.setText(maGiamGia.getTienGiamGia());
    }

    @Override
    public int getItemCount() {
        return maGiamGias.size();
    }

    public int getItemViewType(int position){
        return  resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemMGG_txtTieuDe;
        TextView itemMGG_txtTienGiamGia;
        LinearLayout itemMGG_llCardView;
        CardView itemMGG;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemMGG_txtTieuDe = itemView.findViewById(R.id.itemMGG_txtTieuDe);
            itemMGG_txtTienGiamGia = itemView.findViewById(R.id.itemMGG_txtTienGiamGia);
            itemMGG_llCardView = itemView.findViewById(R.id.layoutMGG_rvMaGiamGia);
            itemMGG = itemView.findViewById(R.id.itemMGG);

        }
    }

    // Interface for event processing
    public interface OnItemClickListener{
        void onItemClickListener(int position, View view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
