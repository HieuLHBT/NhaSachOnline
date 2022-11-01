package com.example.nhasachonline.activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.item.KhachHang;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;

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
//    private String maKhangHang;
    private SharePreferences sharePreferences = new SharePreferences();

    private ArrayList<com.example.nhasachonline.data_model.KhachHang> khachHangsModel = new ArrayList<>();
//    private EditText edtTaiKhoan, edtEmail, edtMatKhau, edtNhapLaiMatKhau,edtHoTen,edtNgaySinh,edtSDT,edtDiaChi;
//    private Spinner spGioiTinh;
////    private Button btnback,btnTaoTaiKhoan;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky_layout);
        Button btnTaoTaiKhoan = findViewById(R.id.layoutDK_btnTaoTaiKhoan);
        ImageButton btnBack = findViewById(R.id.layoutDK_btnBack);
        EditText edtTaiKhoan = findViewById(R.id.layoutDK_edtNhapTaiKhoan);
        EditText edtEmail = findViewById(R.id.layoutDK_edtEmail);
        EditText edtMatKhau = findViewById(R.id.layoutDK_edtNhapMatKhau);
        EditText edtNhapLaiMatKhau = findViewById(R.id.layoutDK_edtNhapLaiMatKhau);
        EditText edtHoTen = findViewById(R.id.layoutDK_edtHoTen);
        EditText edtNgaySinh = findViewById(R.id.layoutDK_edtNgaySinh);
        EditText edtSDT = findViewById(R.id.layoutDK_edtSDT);
        EditText edtDiaChi = findViewById(R.id.layoutDK_edtDiaChi);
        Spinner spGioiTinh = findViewById(R.id.layoutDK_spGioiTinh);
        btnBack.setOnClickListener(view -> {
            finish();
        });
        btnTaoTaiKhoan.setOnClickListener(view ->{
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(edtEmail.getText().toString(), edtMatKhau.getText().toString())
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            KhachHang khachHang = new KhachHang();
                            boolean isNam = spGioiTinh.getSelectedItemPosition() == 0;
                            boolean isNu = spGioiTinh.getSelectedItemPosition() == 1;
                            assert user != null;

                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                            DatabaseReference ngDungDatabase = firebaseDatabase.getReference("NGUOIDUNG").child("khachhang");

                            ngDungDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    int counter = (int) snapshot.getChildrenCount() + 1;

                                    khachHang.setEmail(user.getEmail());
                                    khachHang.setMaKH("kh" + counter);
                                    khachHang.setDiaChi(edtDiaChi.getText().toString());
                                    khachHang.setHoTen(edtHoTen.getText().toString());
                                    khachHang.setMatKhau(edtMatKhau.getText().toString());
                                    khachHang.setSDT(edtSDT.getText().toString());
                                    khachHang.setNgaySinh(edtNgaySinh.getText().toString());
                                    khachHang.setTaiKhoan(edtTaiKhoan.getText().toString());
                                    khachHang.setsTKNganHang("");
                                    khachHang.setTenNganHang("");
                                    Log.d(TAG, "onDataChange: thanhcong"+ khachHang.toString());

//                            khachHang.setGioiTinh(spGioiTinh.getSelectedItemId(isNu,isNam));
                                    new FireBaseNhaSachOnline().taoKhachHang(khachHang);

                                    startActivity(new Intent(DangKyActivity.this,DangNhapActivity.class));
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });



                            ///updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        });



    }
}
