
package com.example.nhasachonline.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.item.KhachHang;
import com.example.nhasachonline.tools.SharePreferences;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class DangNhapActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap_layout);
        Button btnDangNhap = findViewById(R.id.layoutDN_bntDangNhap);
        Button btnDangKy = findViewById(R.id.layoutDN_btnDangKy);
        Button btnQuenMatKhau = findViewById(R.id.layoutDN_btnQuenMatKhau);
        RadioButton rdbQuanLy = findViewById(R.id.layoutDN_rdbQuanLy);
        RadioButton rdbNhanVien = findViewById(R.id.layoutDN_rdbNhanVien);
        RadioButton rdbKhachHang = findViewById(R.id.layoutDN_rdbKhachHang);
        EditText edtTaiKhoan = findViewById(R.id.layoutDN_edtTaiKhoan);
        EditText edtMatKhau = findViewById(R.id.layoutDN_edtNhapMatKhau);
        CheckBox cbNhoMatKhau = findViewById(R.id.layoutDN_cbNhoMatKHau);
        btnQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DangNhapActivity.this, QuenMatKhauActivity.class));
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DangNhapActivity.this, DangKyActivity.class));
            }
        });
        btnDangNhap.setOnClickListener(view -> {
            Task<AuthResult> task = FirebaseAuth.getInstance().signInWithEmailAndPassword(edtTaiKhoan.getText().toString(), edtMatKhau.getText().toString());
            task.addOnSuccessListener(o -> {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (rdbKhachHang.isChecked()) {
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    SharePreferences sharePreferences = new SharePreferences();

                    DatabaseReference ngDungDatabase = firebaseDatabase.getReference("NGUOIDUNG").child("khachhang");




                    ngDungDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snapshotChild : snapshot.getChildren()){
                                KhachHang khachHang = snapshotChild.getValue(KhachHang.class);

                                assert khachHang != null;

                                assert user != null;
                                if(khachHang.getEmail().contains(user.getEmail())){
                                    sharePreferences.setKhachHang(user.getUid(),DangNhapActivity.this, khachHang.getMaKH());
                                    startActivity(new Intent(DangNhapActivity.this, ManHinhChinhKhachHangActivity.class));
                                    return;
                                }
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                } else if (rdbNhanVien.isChecked()) {
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference ngDungDatabase = firebaseDatabase.getReference("NGUOIDUNG").child("nhanvien").child(user.getUid());
                    ngDungDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            KhachHang khachHang = snapshot.getValue(KhachHang.class);

                            Log.d("nhan_vien", "onDataChange: => " + khachHang.toString());
                            Toast.makeText(DangNhapActivity.this, "dang nhap nhan vien thanh cong", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(DangNhapActivity.this,ManHinhChinhKhachHangActivity.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }).addOnFailureListener(e -> Toast.makeText(this, "fail " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });
    }

    @Nullable
    @Override
    protected void onStart() {
        super.onStart();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            Toast.makeText(this, "Khong Co Tai Khoan", Toast.LENGTH_SHORT).show();
        } else {
//            startActivity(new Intent(DangNhapActivity.this,ManHinhChinhKhachHangActivity.class));
        }
    }
}
