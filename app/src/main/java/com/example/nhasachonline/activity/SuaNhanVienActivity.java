package com.example.nhasachonline.activity;

import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.data_model.NhanVien;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.ItemNhanVien;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SuaNhanVienActivity extends AppCompatActivity {

    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    ArrayList<ItemNhanVien> nhanViens = new ArrayList<>();

    EditText MHSNV_edtMaNhanVien, MHSNV_edtTenNhanVien, MHSNV_edtChucVu, MHSNV_edtTaiKhoan, MHSNV_edtMatKhau, MHSNV_edtEmail, MHSNV_edtDiaChi, MHSNV_edtSoDienThoai, MHSNV_edtCMND, MHSNV_edtLuongCoBan;
    ImageView MHSNV_imgHinhNhanVien;
    Button MHSNV_btnLamMoi, MHSNV_btnSuaNhanVien;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_suanhanvien_layout);

        MHSNV_edtMaNhanVien = findViewById(R.id.MHSNV_edtMaNhanVien);
        MHSNV_edtTenNhanVien = findViewById(R.id.MHSNV_edtTenNhanVien);
        MHSNV_edtChucVu = findViewById(R.id.MHSNV_edtChucVu);
        MHSNV_edtTaiKhoan = findViewById(R.id.MHSNV_edtTaiKhoanNhanVien);
        MHSNV_edtMatKhau = findViewById(R.id.MHSNV_edtMatKhauNhanVien);
        MHSNV_edtEmail = findViewById(R.id.MHSNV_edtEmailNhanVien);
        MHSNV_edtDiaChi = findViewById(R.id.MHSNV_edtDiaChiNhanVien);
        MHSNV_edtSoDienThoai = findViewById(R.id.MHSNV_edtSoDienThoaiNhanVien);
        MHSNV_edtCMND = findViewById(R.id.MHSNV_edtCMNDNhanVien);
        MHSNV_edtLuongCoBan = findViewById(R.id.MHSNV_edtLuongNhanVien);
        MHSNV_imgHinhNhanVien = findViewById(R.id.MHSNV_imgHinhNhanVien);
        MHSNV_btnLamMoi = findViewById(R.id.MHSNV_btnNhapMoi);
        MHSNV_btnSuaNhanVien = findViewById(R.id.MHSNV_btnThemNhanVien);

        MHSNV_btnSuaNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(SuaNhanVienActivity.this);
                b.setTitle("CẢNH BÁO");
                b.setMessage("Bạn có muốn sửa thông tin nhân viên không?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fireBase.suaNhanVien(SuaNhanVienActivity.this,
                                "",
                                MHSNV_imgHinhNhanVien.getResources().toString(),
                                MHSNV_edtTenNhanVien.getText().toString(),
                                MHSNV_edtCMND.getText().toString(),
                                MHSNV_edtDiaChi.getText().toString(),
                                MHSNV_edtEmail.getText().toString(),
                                MHSNV_edtLuongCoBan.getText().toString(),
                                MHSNV_edtMatKhau.getText().toString(),
                                MHSNV_edtChucVu.getText().toString(),
                                MHSNV_edtSoDienThoai.getText().toString(),
                                MHSNV_edtTaiKhoan.getText().toString()
                                );
                        //Kiểm tra các trường bỏ trống
                        if(MHSNV_edtMaNhanVien.getTouchables().isEmpty() || MHSNV_edtTenNhanVien.getTouchables().isEmpty() || MHSNV_edtChucVu.getTouchables().isEmpty() ||
                                MHSNV_edtTaiKhoan.getTouchables().isEmpty() || MHSNV_edtMatKhau.getTouchables().isEmpty() || MHSNV_edtEmail.getTouchables().isEmpty() ||
                                MHSNV_edtDiaChi.getTouchables().isEmpty() || MHSNV_edtSoDienThoai.getTouchables().isEmpty() || MHSNV_edtCMND.getTouchables().isEmpty() || MHSNV_edtLuongCoBan.getTouchables().isEmpty()){
                            Toast.makeText(SuaNhanVienActivity.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog al = b.create();
                al.show();
            }
        });
    }

    public void thongTinNhanVien(NhanVien nhanVien){
        MHSNV_edtMaNhanVien.setText(nhanVien.getMaNhanVien());
        MHSNV_edtTenNhanVien.setText(nhanVien.getTenNhanVien());
        MHSNV_edtChucVu.setText(nhanVien.getNguoiDung());
        MHSNV_edtTaiKhoan.setText(nhanVien.getTaiKhoan());
        MHSNV_edtMatKhau.setText(nhanVien.getMatKhau());
        MHSNV_edtEmail.setText(nhanVien.getEmail());
        MHSNV_edtDiaChi.setText(nhanVien.getDiaChi());
        MHSNV_edtSoDienThoai.setText(nhanVien.getSoDienThoai());
        MHSNV_edtCMND.setText(nhanVien.getCmnd());
        MHSNV_edtLuongCoBan.setText(nhanVien.getLuong());
        StorageReference storageReference = FirebaseStorage.getInstance().getReference(nhanVien.getHinhNhanVien());
        try {
            File file = null;
            if (nhanVien.getHinhNhanVien().contains("png")) {
                file = File.createTempFile(nhanVien.getHinhNhanVien().substring(0,nhanVien.getHinhNhanVien().length()-4), "png");
            } else if (nhanVien.getHinhNhanVien().contains("jpg")) {
                file = File.createTempFile(nhanVien.getHinhNhanVien().substring(0,nhanVien.getHinhNhanVien().length()-4), "jpg");
            }
            final File fileHinh = file;
            ((StorageReference) storageReference).getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    MHSNV_imgHinhNhanVien.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("onCancelled", "Lỗi!" + e.getMessage());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
