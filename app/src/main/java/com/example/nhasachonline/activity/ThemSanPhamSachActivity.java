package com.example.nhasachonline.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
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
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.ItemSach;
import com.example.nhasachonline.item.ItemSanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ThemSanPhamSachActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();
    private ArrayList<ItemSach> sachs = new ArrayList<>();
    private Uri uri;
    private final int PICK_IMAGE_REQUEST = 71;
    private final int CAMERA_PIC_REQUEST = 1337;
    private String chonAnh = "Thư viện";

    EditText MHTSP_Sach_edtMaSach, MHTSP_Sach_edtTenSach, MHTSP_Sach_edtTheLoai, MHTSP_Sach_edtTacGia
            , MHTSP_Sach_edtNhaXuatBan, MHTSP_Sach_edtNgayXuatBan, MHTSP_Sach_edtGiaTien, MHTSP_Sach_edtSoLuongKho;
    ImageView MHTSP_Sach_imgAnhSanPham;
    Button MHTSP_Sach_btnLamMoi, MHTTSP_Sach_btnThemSanPham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_themsanpham_sach_layout);
        FirebaseStorage storage;
        StorageReference storageReference;
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        MHTSP_Sach_edtMaSach = findViewById(R.id.MHTSP_Sach_edtMaSach);
        MHTSP_Sach_edtTenSach = findViewById(R.id.MHTSP_Sach_edtTenSach);
        MHTSP_Sach_edtTheLoai = findViewById(R.id.MHTSP_Sach_edtTheLoai);
        MHTSP_Sach_edtTacGia = findViewById(R.id.MHTSP_Sach_edtTacGia);
        MHTSP_Sach_edtNhaXuatBan = findViewById(R.id.MHTSP_Sach_edtNhaXuatBan);
        MHTSP_Sach_edtNgayXuatBan = findViewById(R.id.MHTSP_Sach_edtNgayXuatBan);
        MHTSP_Sach_edtGiaTien = findViewById(R.id.MHTSP_Sach_edtGiaTien);
        MHTSP_Sach_edtSoLuongKho = findViewById(R.id.MHTSP_Sach_edtSoLuongKho);
        MHTSP_Sach_imgAnhSanPham = findViewById(R.id.MHTSP_Sach_imgAnhSanPham);
        MHTSP_Sach_btnLamMoi = findViewById(R.id.MHTTSP_Sach_btnNhapMoi);
        MHTTSP_Sach_btnThemSanPham = findViewById(R.id.MHTTSP_Sach_btnThemSanPham);

        MHTTSP_Sach_btnThemSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ThemSanPhamSachActivity.this);
                b.setTitle("CẢNH BÁO");
                b.setMessage("Bạn có muốn thêm Sản Phẩm vào Kho không?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fireBase.themSanPham_Sach(
                                "s" + MHTSP_Sach_edtMaSach.getText().toString(),
                                MHTSP_Sach_edtMaSach.getText() + ".png",
                                MHTSP_Sach_edtTenSach.getText().toString(),
                                MHTSP_Sach_edtTheLoai.getText().toString(),
                                MHTSP_Sach_edtTacGia.getText().toString(),
                                MHTSP_Sach_edtNhaXuatBan.getText().toString(),
                                MHTSP_Sach_edtNgayXuatBan.getText().toString(),
                                MHTSP_Sach_edtGiaTien.getText().toString(),
                                MHTSP_Sach_edtSoLuongKho.getText().toString()
                        );

                        //Kiểm tra các trường bỏ trống
                        if(MHTSP_Sach_edtMaSach.getTouchables().isEmpty() || MHTSP_Sach_edtTenSach.getTouchables().isEmpty() || MHTSP_Sach_edtTheLoai.getTouchables().isEmpty() ||
                                MHTSP_Sach_edtTacGia.getTouchables().isEmpty() || MHTSP_Sach_edtNhaXuatBan.getTouchables().isEmpty() || MHTSP_Sach_edtNgayXuatBan.getTouchables().isEmpty() ||
                                MHTSP_Sach_edtGiaTien.getTouchables().isEmpty() || MHTSP_Sach_edtSoLuongKho.getTouchables().isEmpty() ){
                            Toast.makeText(ThemSanPhamSachActivity.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        }
                        // Tai anh len storage
                        ghiAnh(uri, MHTSP_Sach_edtMaSach.getText().toString());
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
                Toast.makeText(ThemSanPhamSachActivity.this, "Thêm sách thành công", Toast.LENGTH_SHORT).show();
            }
        });

        MHTSP_Sach_btnLamMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sachs.clear();
            }
        });

        MHTSP_Sach_imgAnhSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ThemSanPhamSachActivity.this);
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
            MHTSP_Sach_imgAnhSanPham.setImageURI(uri);
        }
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(ThemSanPhamSachActivity.this.getContentResolver(), photo, "Title", null);
            uri = Uri.parse(path);
            MHTSP_Sach_imgAnhSanPham.setImageURI(uri);
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
