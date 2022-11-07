package com.example.nhasachonline.adapters;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.item.LichLamViec;

import java.util.ArrayList;

public class LichLamViecRecyclerViewAdapter extends RecyclerView.Adapter<LichLamViecRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<LichLamViec> danhSachNgay;
    private OnItemClickListener onItemClickListener;

    public LichLamViecRecyclerViewAdapter(Activity context, int resource, ArrayList<LichLamViec> danhSachNgay) {
        this.context = context;
        this.resource = resource;
        this.danhSachNgay = danhSachNgay;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(R.layout.lichlamviec_item, parent, false);
        ViewGroup.LayoutParams layoutParams = viewItem.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final int pos = position;
        final LichLamViec lichLamViec = danhSachNgay.get(pos);
        holder.itemLLV_tvNgay.setText(lichLamViec.getNgay());
        Drawable mauHienTai = holder.itemLLV.getBackground();
        if (lichLamViec.getTrangThai().equalsIgnoreCase("")) {
            if (lichLamViec.getNgay().equalsIgnoreCase("")) {
                holder.itemLLV.setVisibility(View.GONE);
            }
            holder.itemLLV.setEnabled(false);
            holder.itemLLV_tvNgay.setTextColor(context.getResources().getColor(R.color.black, context.getTheme()));
        } else if (lichLamViec.getTrangThai().equalsIgnoreCase("Đang chọn")) {
            holder.itemLLV.setVisibility(View.VISIBLE);
            holder.itemLLV.setEnabled(true);
            mauHienTai.setColorFilter(context.getResources().getColor(R.color.dangchon, context.getTheme()), PorterDuff.Mode.MULTIPLY);
            holder.itemLLV.setBackground(mauHienTai);
            holder.itemLLV_tvNgay.setTextColor(context.getResources().getColor(R.color.white, context.getTheme()));
        } else if (lichLamViec.getTrangThai().equalsIgnoreCase("Đăng ký")) {
            holder.itemLLV.setVisibility(View.VISIBLE);
            holder.itemLLV.setEnabled(true);
            mauHienTai.setColorFilter(context.getResources().getColor(R.color.dangky, context.getTheme()), PorterDuff.Mode.MULTIPLY);
            holder.itemLLV.setBackground(mauHienTai);
            holder.itemLLV_tvNgay.setTextColor(context.getResources().getColor(R.color.white, context.getTheme()));
        } else if (lichLamViec.getTrangThai().equalsIgnoreCase("Chờ duyệt")) {
            holder.itemLLV.setVisibility(View.VISIBLE);
            holder.itemLLV.setEnabled(true);
            mauHienTai.setColorFilter(context.getResources().getColor(R.color.choduyet, context.getTheme()), PorterDuff.Mode.MULTIPLY);
            holder.itemLLV.setBackground(mauHienTai);
            holder.itemLLV_tvNgay.setTextColor(context.getResources().getColor(R.color.white, context.getTheme()));
        } else if (lichLamViec.getTrangThai().equalsIgnoreCase("Làm việc")) {
            holder.itemLLV.setVisibility(View.VISIBLE);
            holder.itemLLV.setEnabled(true);
            mauHienTai.setColorFilter(context.getResources().getColor(R.color.lamviec, context.getTheme()), PorterDuff.Mode.MULTIPLY);
            holder.itemLLV.setBackground(mauHienTai);
            holder.itemLLV_tvNgay.setTextColor(context.getResources().getColor(R.color.white, context.getTheme()));
        } else if (lichLamViec.getTrangThai().equalsIgnoreCase("Không phép")) {
            holder.itemLLV.setVisibility(View.VISIBLE);
            holder.itemLLV.setEnabled(true);
            mauHienTai.setColorFilter(context.getResources().getColor(R.color.nghikhongphep, context.getTheme()), PorterDuff.Mode.MULTIPLY);
            holder.itemLLV.setBackground(mauHienTai);
            holder.itemLLV_tvNgay.setTextColor(context.getResources().getColor(R.color.white, context.getTheme()));
        } else if (lichLamViec.getTrangThai().equalsIgnoreCase("Có phép")) {
            holder.itemLLV.setVisibility(View.VISIBLE);
            holder.itemLLV.setEnabled(true);
            mauHienTai.setColorFilter(context.getResources().getColor(R.color.nghicophep, context.getTheme()), PorterDuff.Mode.MULTIPLY);
            holder.itemLLV.setBackground(mauHienTai);
            holder.itemLLV_tvNgay.setTextColor(context.getResources().getColor(R.color.white, context.getTheme()));
        }

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
        return danhSachNgay.size();
    }

    @Override
    public int getItemViewType(int position) {
        return resource;
    }

    // ViewHolder definition
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemLLV_tvNgay;
        CardView itemLLV;
        View.OnClickListener onClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemLLV_tvNgay = itemView.findViewById(R.id.itemLLV_tvNgay);
            itemLLV = itemView.findViewById(R.id.itemLLV);

            // Set event processing
            itemLLV.setOnClickListener(this);
        }

        @Override
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

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
