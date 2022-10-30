//package com.example.nhasachonline.activity;
//
//import android.app.Application;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.nhasachonline.R;
//import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
//import com.example.nhasachonline.item.KhachHang;
//import com.example.nhasachonline.tools.SharePreferences;
//
//import java.util.ArrayList;
//
//public class DangKyActivity extends AppCompatActivity {
//    private String maKhangHang;
//    private SharePreferences sharePreferences = new SharePreferences();
//
//    private ArrayList<KhachHang> khachHangs = new ArrayList<>();
//    private ArrayList<com.example.nhasachonline.data_model.KhachHang> khachHangsModel = new ArrayList<>();
//    private EditText edtTaiKhoan, edtEmail, edtMatKhau, edtNhapLaiMatKhau,edtHoTen,edtNgaySinh,edtSDT,edtDiaChi;
//    private Spinner spGioiTinh;
//    private Button btnback,btnTaoTaiKhoan;
//
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.dangky_layout);
//        sharePreferences.setKhachHang("nguoidung",this,maKhangHang);
//
//    }
//}
