package com.example.nhasachonline.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.data_model.GiamGia;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MaGiamGiaRecyclerViewAdapter extends RecyclerView.Adapter<MaGiamGiaRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<GiamGia> giamGias;
    private OnItemClickListener onItemClickListener;
    DecimalFormat formatter = new DecimalFormat("#,###,###");

    public MaGiamGiaRecyclerViewAdapter(Activity context, int resource, ArrayList<GiamGia> giamGias){
        this.context = context;
        this.resource = resource;
        this.giamGias = giamGias;
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
        GiamGia giamGia = giamGias.get(pos);
        holder.itemMGG_txtTieuDe.setText(giamGia.getTieuDe());
        holder.itemMGG_txtTienGiamGia.setText(formatter.format(Integer.valueOf(giamGia.getTienGiamGia())) + " VNĐ");

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

    @Override
    public int getItemCount() {
        return giamGias.size();
    }

    public int getItemViewType(int position){
        return  resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemMGG_txtTieuDe;
        TextView itemMGG_txtTienGiamGia;
        ImageView itemMGG_imgHinhGiamGia;
        LinearLayout itemMGG_llCardView;
        View.OnClickListener onClickListener;
        CardView itemMGG;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemMGG_txtTieuDe = itemView.findViewById(R.id.itemMGG_txtTieuDe);
            itemMGG_txtTienGiamGia = itemView.findViewById(R.id.itemMGG_txtTienGiamGia);
            itemMGG_imgHinhGiamGia = itemView.findViewById(R.id.itemMGG_imgHinhGiamGia);
            itemMGG_llCardView = itemView.findViewById(R.id.layoutMGG_rvMaGiamGia);
            itemMGG = itemView.findViewById(R.id.itemMGG);

            itemMGG.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onClickListener != null) {
                onClickListener.onClick(v);
            }
        }
    }
    // Interface for event processing
    public interface OnItemClickListener {
        void onItemClickListener(int position, View view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
