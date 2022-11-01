package com.example.nhasachonline.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharePreferences {

    public void setKhachHang(String tenFile, Context context, String maKhachHang) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(tenFile, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("maNguoiDung", maKhachHang);
        editor.commit();
        Log.d("test", "Thành công" + maKhachHang);
    }

    public String getKhachHang(String tenFile, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(tenFile, context.MODE_PRIVATE);
        return sharedPreferences.getString("maNguoiDung", null);
    }

    public void themMaDonHang(Context context, String maDonHang) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("donHang", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("maDonHang", maDonHang);
        editor.commit();
    }
    public String layMaDonHang(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("donHang", context.MODE_PRIVATE);
        return sharedPreferences.getString("maDonHang", null);
    }

    public void setSanPham(String tenFile, Context context, String maSanPham) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(tenFile, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("maSanPham", maSanPham);
        editor.commit();
        Log.d("test", "Thành công" + maSanPham);
    }

    public String getSanPham(String tenFile, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(tenFile, context.MODE_PRIVATE);
        return sharedPreferences.getString("maSanPham", null);
    }
}
