package com.example.nhasachonline.activity;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class QuenMatKhauActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        {
            super.onCreate(savedInstanceState);
            EditText layoutQMK_edtNhapTaiKhoan = findViewById(R.id.layoutQMK_edtNhapTaiKhoan);
            EditText layoutQMK_edtNhapEmail = findViewById(R.id.layoutQMK_edtNhapEmail);
            Button layoutQMK_btnLayLaiMK = findViewById(R.id.layoutQMK_btnLayLaiMK);
            Button layoutQMK_btnQuayLai = findViewById(R.id.layoutQMK_btnQuayLai);
            setContentView(R.layout.quenmatkhau_layout);
            layoutQMK_btnLayLaiMK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    String emailAddress = "snappro33@gmail.com";

                    auth.sendPasswordResetEmail(emailAddress)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "Email sent.");
                                    }
                                }
                            });
                }
            });
        }
    }
}
