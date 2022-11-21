package com.example.nhasachonline.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.data_model.KhachHang;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.tools.SharePreferences;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DangKyActivity extends AppCompatActivity {
    private SharePreferences sharePreferences = new SharePreferences();
    private ArrayList<com.example.nhasachonline.data_model.KhachHang> khachHangsModel = new ArrayList<>();

    private Button
            layoutDK_btnTaoTaiKhoan;
    private ImageButton
            layoutDK_btnBack;
    private EditText
            layoutDK_edtNhapTaiKhoan,
            layoutDK_edtEmail,
            layoutDK_edtNhapMatKhau,
            layoutDK_edtNhapLaiMatKhau,
            layoutDK_edtHoTen,
            layoutDK_edtSDT,
            layoutDK_edtDiaChi;
    FireBaseNhaSachOnline
            fireBaseNhaSachOnline = new FireBaseNhaSachOnline();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky_layout);
        setControl();
        setEvent();

    }

    private void setEvent() {
        //BTN tao tai khoan - dang ky
        layoutDK_btnTaoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateRegister()) {
                    KhachHang khachHang = new KhachHang("khachhang",
                            "",
                            layoutDK_edtDiaChi.getText().toString(),
                            layoutDK_edtEmail.getText().toString(),
                            layoutDK_edtNhapMatKhau.getText().toString(),
                            "",
                            layoutDK_edtSDT.getText().toString(),
                            "",
                            layoutDK_edtNhapTaiKhoan.getText().toString(),
                            layoutDK_edtHoTen.getText().toString());
                    fireBaseNhaSachOnline.dangKy(DangKyActivity.this, khachHang);
                }
            }
        });

        //BTN back
        layoutDK_btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void setControl() {
        layoutDK_btnTaoTaiKhoan = findViewById(R.id.layoutDK_btnTaoTaiKhoan);
        layoutDK_btnBack = findViewById(R.id.layoutDK_btnBack);
        layoutDK_edtNhapTaiKhoan = findViewById(R.id.layoutDK_edtNhapTaiKhoan);
        layoutDK_edtEmail = findViewById(R.id.layoutDK_edtEmail);
        layoutDK_edtNhapMatKhau = findViewById(R.id.layoutDK_edtNhapMatKhau);
        layoutDK_edtNhapLaiMatKhau = findViewById(R.id.layoutDK_edtNhapLaiMatKhau);
        layoutDK_edtHoTen = findViewById(R.id.layoutDK_edtHoTen);
        layoutDK_edtSDT = findViewById(R.id.layoutDK_edtSDT);
        layoutDK_edtDiaChi = findViewById(R.id.layoutDK_edtDiaChi);
    }

    //Minh
    protected boolean validateRegister() {

        //Tu 6 den 20 ky tu khong khoang trang va ky tu dac biet
        Pattern patternAccount = Pattern.compile("^[A-Za-z][A-Za-z0-9]{5,20}$");
        Matcher matcherAccount = patternAccount.matcher(layoutDK_edtNhapTaiKhoan.getText().toString());

        //email regex
        Pattern patternEmail = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcherEmail = patternEmail.matcher(layoutDK_edtEmail.getText().toString());

        //More than 8 char, required at least: 1 char, 1 number, 1 special char.
        Pattern patternPassword = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
        Matcher matcherPassword = patternPassword.matcher(layoutDK_edtNhapMatKhau.getText().toString());

        //Ten tieng viet hoac tieng anh, yeu cau viet hoa dau tu
        Pattern patternName = Pattern.compile("^[A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*(?:[ ][A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*)*$");
        Matcher matcherName = patternName.matcher(layoutDK_edtHoTen.getText().toString());

        //So dien thoai Viet Nam
        Pattern patternPhone = Pattern.compile("(84|0[3|5|7|8|9])+([0-9]{8})\\b");
        Matcher matcherPhone = patternPhone.matcher(layoutDK_edtSDT.getText().toString());

        boolean boolAccount = matcherAccount.find();
        boolean boolEmail = matcherEmail.find();
        boolean boolPassword = matcherPassword.find();
        boolean boolRePassword = layoutDK_edtNhapMatKhau.getText().toString().equals(layoutDK_edtNhapLaiMatKhau.getText().toString());
        boolean boolName = matcherName.find();
        boolean boolPhone = matcherPhone.find();
        boolean boolAddress = !layoutDK_edtDiaChi.getText().toString().isEmpty();

        if (!boolAccount) {
            layoutDK_edtNhapTaiKhoan.setError("Tên đăng nhập từ 6 đến 20 ký tự, không khoảng trắng và ký tự đặc biệt");
        }
        if (!boolEmail) {
            layoutDK_edtEmail.setError("Vui lòng nhập email hợp lệ");
        }
        if (!boolPassword) {
            layoutDK_edtNhapMatKhau.setError("Mật khẩu cần ít nhất 8 ký tự, một chữ cái, một chữ số và một ký tự đặc biệt");
        }
        if (!boolRePassword) {
            layoutDK_edtNhapLaiMatKhau.setError("Mật khẩu và xác nhận mật khẩu không giống nhau");
        }
        if (!boolName) {
            layoutDK_edtHoTen.setError("Tên Tiếng Việt hoặc Tiếng Anh, viết hoa đầu từ");
        }
        if (!boolPhone) {
            layoutDK_edtSDT.setError("Số điện thoại bạn nhập không hợp lệ");
        }
        if (!boolAddress) {
            layoutDK_edtDiaChi.setError("Không được bỏ trống ");
        }
        return boolAccount && boolEmail && boolPassword && boolRePassword && boolName && boolPhone && boolAddress;
    }

}
