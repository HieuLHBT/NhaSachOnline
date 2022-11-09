package com.example.nhasachonline.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharePreferences {

    // Hieu SharePreferences
    // Đơn hàng
    public void themMaDonHang(Context context, String maDonHang) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dulieu", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("maDonHang", maDonHang);
        editor.commit();
    }

    public String layMaDonHang(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dulieu", context.MODE_PRIVATE);
        return sharedPreferences.getString("maDonHang", null);
    }

    public void xoaMaDonHang(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dulieu", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("maDonHang");
        editor.commit();
    }

    ////////////////////////////////
    public void setKhachHang(String uid, Context context, String maKhachHang) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dulieu", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("maNguoiDung", maKhachHang);
        editor.commit();
    }
    public String getKhachHang(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dulieu", context.MODE_PRIVATE);
        return sharedPreferences.getString("maNguoiDung", null);
    }
}
