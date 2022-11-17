package com.example.nhasachonline.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.nhasachonline.R;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuenMatKhauActivity extends AppCompatActivity {
    EditText
            _edtNhapTaiKhoan,
            _edtSDT,
            _edtNhapEmail;
    Button
            layoutQMK_btnLayLaiMK,
            layoutQMK_btnQuayLai;
    String
            email = "",
            phone = "",
            account = "";
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();

    private static final int REQ = 99;
    private static final String LOG_TAG = "LayLaiMatKhauActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quenmatkhau_layout);
        setControl();
        setEvent();
        askPermission();

    }

    private boolean checkValidateField() {

        //email regex
        Pattern patternEmail = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcherEmail = patternEmail.matcher(_edtNhapEmail.getText().toString());

        //Tu 6 den 20 ky tu khong khoang trang va ky tu dac biet
        Pattern patternAccount = Pattern.compile("^[A-Za-z][A-Za-z0-9]{5,20}$");
        Matcher matcherAccount = patternAccount.matcher(_edtNhapTaiKhoan.getText().toString());

        boolean boolAccount = matcherAccount.find();
        boolean boolEmail = matcherEmail.find();
        boolean boolPhone = !_edtSDT.getText().toString().isEmpty();
        if (!boolAccount) {
            _edtNhapTaiKhoan.setError("Tên đăng nhập từ 6 đến 20 ký tự, không khoảng trắng và ký tự đặc biệt");
        }
        if (!boolEmail) {
            _edtNhapEmail.setError("Vui lòng nhập email hợp lệ");
        }
        if (!boolPhone) {
            _edtSDT.setError("Vui lòng nhập số điện thoại");
        }

        return boolAccount && boolEmail;
    }

    private void setEvent() {
        layoutQMK_btnLayLaiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidateField()) {
                    account = _edtNhapTaiKhoan.getText().toString();
                    email = _edtNhapEmail.getText().toString();
                    phone = _edtSDT.getText().toString();
                    fireBaseNhaSachOnline.sendEmailResetPassword(QuenMatKhauActivity.this, email, account, phone);
                }
                Log.d("Test", "Value put in resetpas(): " + account + "; " + email + "; " + phone +"");

            }
        });


        layoutQMK_btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setControl() {
        _edtNhapTaiKhoan = findViewById(R.id.layoutQMK_edtNhapTaiKhoan);
        _edtNhapEmail = findViewById(R.id.layoutQMK_edtNhapEmail);
        _edtSDT = findViewById(R.id.layoutQMK_edtNhapSDT);
        layoutQMK_btnLayLaiMK = findViewById(R.id.layoutQMK_btnLayLaiMK);
        layoutQMK_btnQuayLai = findViewById(R.id.layoutQMK_btnQuayLai);
    }

    private void askPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int sendSmsPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

            if(sendSmsPermission != PackageManager.PERMISSION_GRANTED || permissionCheck!= PackageManager.PERMISSION_GRANTED){
                this.requestPermissions(
                        new String[]{Manifest.permission.SEND_SMS,Manifest.permission.READ_PHONE_STATE},REQ
                );
            }
            return;
        }


    }

    public void sendSMS_By_smsManager(String phoneNum, String message){
        try {
            //get default instance
            SmsManager smsManager = SmsManager.getDefault();
            // send mess
            smsManager.sendTextMessage(phoneNum,null, message, null, null);
            //Log.i( LOG_TAG,"Your sms has successfully sent!");

        }catch (Exception ex){
            Log.e( LOG_TAG,"Your sms has failed...", ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQ: {

                // Note: If request is cancelled, the result arrays are empty.
                // Permissions granted (SEND_SMS).
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.i( LOG_TAG,"Permission granted!");
                    Toast.makeText(this, "Permission granted!", Toast.LENGTH_LONG).show();


                }
                // Cancelled or denied.
                else {
                    Log.i( LOG_TAG,"Permission denied!");
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ) {
            if (resultCode == RESULT_OK) {
                // Do something with data (Result returned).
                Toast.makeText(this, "Action OK", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Action canceled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Action Failed", Toast.LENGTH_LONG).show();
            }
        }
    }
}
