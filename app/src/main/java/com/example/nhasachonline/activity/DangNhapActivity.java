
package com.example.nhasachonline.activity;



import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
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
import com.example.nhasachonline.data_model.KhachHang;
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

import java.util.ArrayList;



public class DangNhapActivity  extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.dangnhap_layout);
        Button layoutDN_bntDangNhap = findViewById(R.id.layoutDN_bntDangNhap);
        Button layoutDN_btnDangKy = findViewById(R.id.layoutDN_btnDangKy);
        Button layoutDN_btnQuenMatKhau = findViewById(R.id.layoutDN_btnQuenMatKhau);
        RadioButton layoutDN_rdbQuanLy = findViewById(R.id.layoutDN_rdbQuanLy);
        RadioButton layoutDN_rdbNhanVien = findViewById(R.id.layoutDN_rdbNhanVien);
        RadioButton layoutDN_rdbKhachHang = findViewById(R.id.layoutDN_rdbKhachHang);
        EditText layoutDN_edtTaiKhoan = findViewById(R.id.layoutDN_edtTaiKhoan);
        EditText layoutDN_edtNhapMatKhau = findViewById(R.id.layoutDN_edtNhapMatKhau);
        CheckBox layoutDN_cbNhoMatKHau = findViewById(R.id.layoutDN_cbNhoMatKHau);
        layoutDN_btnQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DangNhapActivity.this, QuenMatKhauActivity.class));
            }
        });
        layoutDN_bntDangNhap.setOnClickListener(view -> {
            Task<AuthResult> task = FirebaseAuth.getInstance().signInWithEmailAndPassword(layoutDN_edtTaiKhoan.getText().toString(), layoutDN_edtNhapMatKhau.getText().toString());
            task.addOnSuccessListener(o ->{
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (layoutDN_rdbKhachHang.isChecked()) {
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    SharePreferences sharePreferences = new SharePreferences();

                    DatabaseReference ngDungDatabase = firebaseDatabase.getReference("NGUOIDUNG").child("khachhang");


                    ngDungDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot snapshotChild : snapshot.getChildren()) {
                                KhachHang khachHang = snapshotChild.getValue(KhachHang.class);

                                assert khachHang != null;

                                assert user != null;
                                if (khachHang.getEmail().contains(user.getEmail())) {
                                    sharePreferences.setKhachHang(user.getUid(), DangNhapActivity.this, khachHang.getMaKhachHang());
                                    startActivity(new Intent(DangNhapActivity.this, ManHinhChinhKhachHangActivity.class));
                                    return;
                                }
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else if (layoutDN_rdbNhanVien.isChecked()) {
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
            }).addOnFailureListener(e-> Toast.makeText(this, "fail" + e.getMessage(), Toast.LENGTH_SHORT).show());
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            Toast.makeText(this, "Ban Chua Co Tai Khoan", Toast.LENGTH_SHORT).show();
        }
    }
}
