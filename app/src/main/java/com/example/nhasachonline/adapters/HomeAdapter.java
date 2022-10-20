package com.example.nhasachonline.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nhasachonline.R;
import com.example.nhasachonline.Sach;

import java.util.ArrayList;


public class HomeAdapter extends ArrayAdapter<Sach> {
    private Activity context;
    private int resource;
    ArrayList<Sach> listSach;

    public HomeAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<Sach> listSach) {
        super(context, resource, listSach);
        this.context = context;
        this.resource = resource;
        this.listSach = listSach;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        MyViewHolder viewHolder;
        if(convertView == null){
            view =context.getLayoutInflater().inflate(resource, parent,false);
            viewHolder = new MyViewHolder();
            //get view item from view object
            viewHolder.tvTenSP = view.findViewById(R.id.tv_tensp);
            viewHolder.tvTacGia = view.findViewById(R.id.tv_tenTacGia);
            viewHolder.tvGia = view.findViewById(R.id.tv_gia);
            viewHolder.tvGKM = view.findViewById(R.id.tv_khuyenmai);
      //      viewHolder.tvSoLuong = view.findViewById(R.id.tv);
            //get data from data soure (persons)
            Sach sach = listSach.get(position);
            viewHolder.tvTenSP.setText(sach.getTenSanPham());
            viewHolder.tvTacGia.setText(sach.getTacGia());
            viewHolder.tvGia.setText((int) sach.getGia());
            viewHolder.tvGKM.setText((int) sach.getKhuyenMai());
        }

        return view;

    }
    public static class MyViewHolder{
        TextView tvTenSP;
        TextView tvTacGia;
        TextView tvGia;
        TextView tvKM;
        TextView tvGKM;
        TextView tvSoLuong;

    }
}
