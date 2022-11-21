package com.example.nhasachonline.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SuaNhanVienActivity extends AppCompatActivity {

    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    ArrayList<ItemNhanVien> nhanViens = new ArrayList<>();

    EditText MHSNV_edtMaNhanVien, MHSNV_edtTenNhanVien, MHSNV_edtChucVu, MHSNV_edtTaiKhoan, MHSNV_edtMatKhau, MHSNV_edtEmail, MHSNV_edtDiaChi, MHSNV_edtSoDienThoai, MHSNV_edtCMND, MHSNV_edtLuongCoBan;
    ImageView MHSNV_imgHinhNhanVien;
    Button MHSNV_btnLamMoi, MHSNV_btnSuaNhanVien;

    private Uri uri;
    private final int PICK_IMAGE_REQUEST = 71;
    private final int CAMERA_PIC_REQUEST = 1337;
    private String chonAnh = "Thư viện";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_suanhanvien_layout);
        FirebaseStorage storage;
        StorageReference storageReference;
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

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
                                "nhanvien" + MHSNV_edtMaNhanVien.getText() + ".png",
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
                        // Tai anh len storage
                        ghiAnh(uri, MHSNV_edtMaNhanVien.getText().toString());
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

        MHSNV_imgHinhNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(SuaNhanVienActivity.this);
                b.setTitle("SỬA HÌNH NHÂN VIÊN");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK){
            uri  =  data.getData();
            MHSNV_imgHinhNhanVien.setImageURI(uri);
        }
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(SuaNhanVienActivity.this.getContentResolver(), photo, "Title", null);
            uri = Uri.parse(path);
            MHSNV_imgHinhNhanVien.setImageURI(uri);
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

}
