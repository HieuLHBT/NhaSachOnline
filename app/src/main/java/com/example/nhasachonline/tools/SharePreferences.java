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
    public void dangNhap(Context context, String maNguoiDung) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dulieu", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("maNguoiDung", maNguoiDung);
        editor.commit();
    }

    public String layMa(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dulieu", context.MODE_PRIVATE);
        return sharedPreferences.getString("maNguoiDung", null);
    }

    public void saveLoginInfo(Context context, String taikhoan, String matKhau, boolean checkBox) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("loginInfo", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("account", taikhoan);
        editor.putString("matkhau", matKhau);
        editor.putBoolean("checkbox", checkBox);
        editor.commit();
    }
}
