package com.example.nhasachonline.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.data_model.NhanVien;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.ItemNhanVien;
import com.example.nhasachonline.tools.SharePreferences;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.checkerframework.checker.units.qual.A;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ThemNhanVienActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    ArrayList<ItemNhanVien> nhanViens = new ArrayList<>();

    EditText MHTNV_edtMaNhanVien, MHTNV_edtTenNhanVien, MHTNV_edtChucVu, MHTNV_edtTaiKhoan, MHTNV_edtMatKhau, MHTNV_edtEmail, MHTNV_edtDiaChi, MHTNV_edtSoDienThoai, MHTNV_edtCMND, MHTNV_edtLuongCoBan;
    ImageView MHTNV_imgHinhNhanVien;
    Button MHTNV_btnLamMoi, MHTNV_btnThemNhanVien;

    private Uri uri;
    private final int PICK_IMAGE_REQUEST = 71;
    private final int CAMERA_PIC_REQUEST = 1337;
    private String chonAnh = "Thư viện";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_themnhanvien_layout);
        FirebaseStorage storage;
        StorageReference storageReference;
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        MHTNV_edtMaNhanVien = findViewById(R.id.MHTNV_edtMaNhanVien);
        MHTNV_edtTenNhanVien = findViewById(R.id.MHTNV_edtTenNhanVien);
        MHTNV_edtChucVu = findViewById(R.id.MHTNV_edtChucVu);
        MHTNV_edtTaiKhoan = findViewById(R.id.MHTNV_edtTaiKhoanNhanVien);
        MHTNV_edtMatKhau = findViewById(R.id.MHTNV_edtMatKhauNhanVien);
        MHTNV_edtEmail = findViewById(R.id.MHTNV_edtEmailNhanVien);
        MHTNV_edtDiaChi = findViewById(R.id.MHTNV_edtDiaChiNhanVien);
        MHTNV_edtSoDienThoai = findViewById(R.id.MHTNV_edtSoDienThoaiNhanVien);
        MHTNV_edtCMND = findViewById(R.id.MHTNV_edtCMNDNhanVien);
        MHTNV_edtLuongCoBan = findViewById(R.id.MHTNV_edtLuongNhanVien);
        MHTNV_imgHinhNhanVien = findViewById(R.id.MHTNV_imgAnhNhanVien);
        MHTNV_btnLamMoi = findViewById(R.id.MHTNV_btnNhapMoi);
        MHTNV_btnThemNhanVien = findViewById(R.id.MHTNV_btnThemNhanVien);

        MHTNV_btnThemNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ThemNhanVienActivity.this);
                b.setTitle("CẢNH BÁO");
                b.setMessage("Bạn có muốn thêm nhân viên vào cty không?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fireBase.themNhanVien(
                               "nv" + MHTNV_edtMaNhanVien.getText().toString(),
                                "nhanvien" + MHTNV_edtMaNhanVien.getText() + ".png",
                                MHTNV_edtTenNhanVien.getText().toString(),
                                MHTNV_edtCMND.getText().toString(),
                                MHTNV_edtDiaChi.getText().toString(),
                                MHTNV_edtEmail.getText().toString(),
                                MHTNV_edtLuongCoBan.getText().toString(),
                                MHTNV_edtMatKhau.getText().toString(),
                                MHTNV_edtChucVu.getText().toString(),
                                MHTNV_edtSoDienThoai.getText().toString(),
                                MHTNV_edtTaiKhoan.getText().toString()
                        );
                        // Tai anh len storage
                        ghiAnh(uri, MHTNV_edtMaNhanVien.getText().toString());
                    };
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

        MHTNV_btnLamMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nhanViens.clear();
            }
        });

        MHTNV_imgHinhNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ThemNhanVienActivity.this);
                b.setTitle("THÊM HÌNH NHÂN VIÊN");
                String[] ca = {"Chọn từ thư viên", "Chụp ảnh"};
                b.setSingleChoiceItems(ca, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                chonAnh = "Thư viện";
                                break;
                            case 1:
                                chonAnh = "Camera";
                                break;
                        }
                    }
                });
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (chonAnh.equalsIgnoreCase("Thư viện")) {
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intent, PICK_IMAGE_REQUEST);
                        } else if (chonAnh.equalsIgnoreCase("Camera")) {
                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
                        }
                        chonAnh = "Thư viện";
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK){
            uri  =  data.getData();
            MHTNV_imgHinhNhanVien.setImageURI(uri);
        }
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(ThemNhanVienActivity.this.getContentResolver(), photo, "Title", null);
            uri = Uri.parse(path);
            MHTNV_imgHinhNhanVien.setImageURI(uri);
        }
    }

    private void ghiAnh(Uri filePath, String maNhanVien){
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            StorageReference ref = FirebaseStorage.getInstance().getReference().child(maNhanVien + ".png");
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    //Kiem tra ma nhan vien

}
