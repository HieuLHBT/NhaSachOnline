package com.example.nhasachonline.activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.ItemKhachHang;
import com.example.nhasachonline.tools.SharePreferences;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DangKyActivity extends AppCompatActivity {
    private SharePreferences sharePreferences = new SharePreferences();
    private ArrayList<com.example.nhasachonline.data_model.KhachHang> khachHangsModel = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky_layout);
        init();
    }
    private void init(){
        Button layoutDK_btnTaoTaiKhoan = findViewById(R.id.layoutDK_btnTaoTaiKhoan);
        ImageButton layoutDK_btnBack = findViewById(R.id.layoutDK_btnBack);
        EditText layoutDK_edtNhapTaiKhoan = findViewById(R.id.layoutDK_edtNhapTaiKhoan);
        EditText layoutDK_edtEmail = findViewById(R.id.layoutDK_edtEmail);
        EditText layoutDK_edtNhapMatKhau = findViewById(R.id.layoutDK_edtNhapMatKhau);
        EditText layoutDK_edtNhapLaiMatKhau = findViewById(R.id.layoutDK_edtNhapLaiMatKhau);
        EditText layoutDK_edtHoTen = findViewById(R.id.layoutDK_edtHoTen);
        EditText layoutDK_edtNgaySinh = findViewById(R.id.layoutDK_edtNgaySinh);
        EditText layoutDK_edtSDT = findViewById(R.id.layoutDK_edtSDT);
        EditText layoutDK_edtDiaChi = findViewById(R.id.layoutDK_edtDiaChi);
        Spinner layoutDK_spGioiTinh = findViewById(R.id.layoutDK_spGioiTinh);
        layoutDK_btnBack.setOnClickListener(view -> {
            finish();
        });
        layoutDK_btnTaoTaiKhoan.setOnClickListener(view -> {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(layoutDK_edtEmail.getText().toString(),layoutDK_edtNhapMatKhau.getText().toString()).
                    addOnCompleteListener(this,task -> {
                        if (task.isSuccessful()){
                            Log.d(TAG, "init: ");
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            ItemKhachHang itemKhachHang = new ItemKhachHang();
                            boolean isNam = layoutDK_spGioiTinh.getSelectedItemPosition() == 0;
                            assert user != null;
                            assert itemKhachHang != null;
                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                            DatabaseReference ngDungDatabase = firebaseDatabase.getReference("NGUOIDUNG").child("khachhang");
                            ngDungDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    int counter = (int) snapshot.getChildrenCount() + 1;
                                    itemKhachHang.setEmail(user.getEmail());
                                    itemKhachHang.setMaKhachHang("kh" + counter);
                                    itemKhachHang.setDiaChi(layoutDK_edtDiaChi.getText().toString());
                                    itemKhachHang.setHoTen(layoutDK_edtHoTen.getText().toString());
                                    itemKhachHang.setMatKhau(layoutDK_edtNhapMatKhau.getText().toString());
                                    itemKhachHang.setSDT(layoutDK_edtSDT.getText().toString());
                                    itemKhachHang.setNgaySinh(layoutDK_edtNgaySinh.getText().toString());
                                    itemKhachHang.setTaiKhoan(layoutDK_edtNhapTaiKhoan.getText().toString());
                                    itemKhachHang.setsTKNganHang("");
                                    itemKhachHang.setTenNganHang("");
                                    Log.d(TAG, "onDataChange: thanhcong"+ itemKhachHang.toString());

//                            khachHang.setGioiTinh(spGioiTinh.getSelectedItemId(isNu,isNam));
                                    new FireBaseNhaSachOnline().taoKhachHang(itemKhachHang);

                                    startActivity(new Intent(DangKyActivity.this,DangNhapActivity.class));
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    });
        });
    }

}
