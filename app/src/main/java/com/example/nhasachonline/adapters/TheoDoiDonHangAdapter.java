package com.example.nhasachonline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nhasachonline.R;
import com.example.nhasachonline.doituong.TheoDoiDonHang;

import java.util.ArrayList;

public class TheoDoiDonHangAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<TheoDoiDonHang> data;
    public TheoDoiDonHangAdapter(@NonNull Context context, int resource, @NonNull ArrayList<TheoDoiDonHang> data){
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        TextView txtMaDonHang = convertView.findViewById(R.id.txtMaDonHang);
        TextView txtTenNVGiaoHang = convertView.findViewById(R.id.txtTenNVGiaoHang);
        TextView txtThoiGianDuKienGiao = convertView.findViewById(R.id.txtThoiGianDuKienGiao);
        TextView txtThoiGianDat= convertView.findViewById(R.id.txtThoiGianDat);
        TextView txtTongTienThanhToan = convertView.findViewById(R.id.txtTongTienThanhToan);
        TheoDoiDonHang theoDoiDonHang = data.get(position);
        txtMaDonHang.setText(theoDoiDonHang.getMaDonHang());
        txtTenNVGiaoHang.setText(theoDoiDonHang.getTenNVGiaoHang());
        txtThoiGianDuKienGiao.setText((CharSequence) theoDoiDonHang.getThoiGianDuKienGiao());
        txtThoiGianDat.setText((CharSequence) theoDoiDonHang.getThoiGianDat());
        txtTongTienThanhToan.setText(theoDoiDonHang.getTongTienThanhToan());
        return convertView;
    }
}
