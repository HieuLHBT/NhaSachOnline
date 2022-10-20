package com.example.nhasachonline.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nhasachonline.R;
import com.example.nhasachonline.doituong.Sach;

import java.util.ArrayList;


public class ManHinhChinhKhachHangAdapter extends ArrayAdapter<Sach> {
    private Activity context;
    private int resource;
    final ArrayList<Sach> listSach;

    public ManHinhChinhKhachHangAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<Sach> listSach) {
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
