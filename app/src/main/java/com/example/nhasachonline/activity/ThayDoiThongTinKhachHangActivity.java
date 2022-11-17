package com.example.nhasachonline.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.data_model.KhachHang;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.tools.SharePreferences;

public class ThayDoiThongTinKhachHangActivity extends AppCompatActivity {

    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();
    private String maKhachHang = "kh1";
    private KhachHang khachHang = new KhachHang();

    EditText layoutCSTTKH_edtHoTen, layoutCSTTKH_edtEmail, layoutCSTTKH_edtSoDienThoai, layoutCSTTKH_edtTenNganHang, layoutCSTTKH_edtSTKNganHang,layoutCSTTKH_edtDiaChi;
    Button layoutCSTTKH_btnLuu;
    ImageButton layoutCSTTKH_btnTroVe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thaydoithongtin_layout);
        sharePreferences.setKhachHang1(this,maKhachHang);
        maKhachHang = sharePreferences.getKhachHang1(this);

        // Ánh xạ
        layoutCSTTKH_edtHoTen = findViewById(R.id.layoutCSTTKH_edtHoTen);
        layoutCSTTKH_edtEmail = findViewById(R.id.layoutCSTTKH_edtEmail);
        layoutCSTTKH_edtSoDienThoai = findViewById(R.id.layoutCSTTKH_edtSoDienThoai);
        layoutCSTTKH_edtTenNganHang = findViewById(R.id.layoutCSTTKH_edtTenNganHang);
        layoutCSTTKH_edtSTKNganHang = findViewById(R.id.layoutCSTTKH_edtSTKNganHang);
        layoutCSTTKH_edtDiaChi = findViewById(R.id.layoutCSTTKH_edtDiaChi);
        layoutCSTTKH_btnTroVe = findViewById(R.id.layoutCSTTKH_btnTroVe);
        layoutCSTTKH_btnLuu = findViewById(R.id.layoutCSTTKH_btnLuu);

        //Hiển thị dữ liệu
        layoutCSTTKH_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fireBase.thayDoiThongTinKhachHang(maKhachHang, khachHang,ThayDoiThongTinKhachHangActivity.this);

        layoutCSTTKH_btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutCSTTKH_edtHoTen.getText().toString().equals("") || layoutCSTTKH_edtEmail.getText().toString().equals("")
                        || layoutCSTTKH_edtSoDienThoai.getText().toString().equals("") || layoutCSTTKH_edtTenNganHang.getText().toString().equals("")
                        || layoutCSTTKH_edtSTKNganHang.getText().toString().equals("") || layoutCSTTKH_edtDiaChi.getText().toString().equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ThayDoiThongTinKhachHangActivity.this);
                    builder.setTitle("Thông Báo");
                    builder.setMessage("Không được để trống thông tin !!!");
                    builder.setPositiveButton("oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();
                } else {
                    fireBase.suaThongTinKhachHang(
                            "",
                            layoutCSTTKH_edtHoTen.getText().toString(),
                            layoutCSTTKH_edtEmail.getText().toString(),
                            layoutCSTTKH_edtSoDienThoai.getText().toString(),
                            layoutCSTTKH_edtSTKNganHang.getText().toString(),
                            layoutCSTTKH_edtTenNganHang.getText().toString(),
                            layoutCSTTKH_edtDiaChi.getText().toString()
                    );
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(ThayDoiThongTinKhachHangActivity.this);
                    builder1.setTitle("Thông Báo");
                    builder1.setMessage("Cập nhật thành công, \n" +
                            "Vui lòng cập nhật lại thông tin tại màn hình danh sách khách hàng !!!");
                    builder1.setPositiveButton("oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ThayDoiThongTinKhachHangActivity.this);
                            builder.setTitle("Thông Báo");
                            builder.setMessage("Mời bạn quay lại màn hình thông tin khách hàng!!!");
                            builder.setPositiveButton("oke", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                            builder.show();
                        }
                    });
                    builder1.show();
                }
            }
        });
    }
    public void thongTinKhachHang(){
        layoutCSTTKH_edtHoTen.setText(khachHang.getTenKhachHang());
        layoutCSTTKH_edtEmail.setText(khachHang.getEmail());
        layoutCSTTKH_edtSoDienThoai.setText(khachHang.getSoDienThoai());
        layoutCSTTKH_edtTenNganHang.setText(khachHang.getNganHang());
        layoutCSTTKH_edtSTKNganHang.setText(khachHang.getSoTaiKhoan());
        layoutCSTTKH_edtDiaChi.setText(khachHang.getDiaChi());
    }

}
