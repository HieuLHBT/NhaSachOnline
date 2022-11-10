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
import com.example.nhasachonline.item.ItemVanPhongPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ThemSanPhamVPPActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();
    private ArrayList<ItemVanPhongPham> vanPhongPhams = new ArrayList<>();
    private Uri uri;
    private final int PICK_IMAGE_REQUEST = 71;
    private final int CAMERA_PIC_REQUEST = 1337;
    private String chonAnh = "Thư viện";

    EditText MHTSP_VPP_edtMaVPP, MHTSP_VPP_edtTenVPP, MHTSP_VPP_edtNhaPhanPhoi, MHTSP_VPP_edtXuatXu
            , MHTSP_VPP_edtDonVi, MHTSP_VPP_edtGiaTien, MHTSP_VPP_edtSoLuongKho;
    ImageView MHTSP_VPP_imgHinhVPP;
    Button MHTTSP_VPP_btnNhapMoi, MHTTSP_VPP_btnThemSanPham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_themsanpham_vpp_layout);
        FirebaseStorage storage;
        StorageReference storageReference;
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        MHTSP_VPP_edtMaVPP = findViewById(R.id.MHTSP_VPP_edtMaVPP);
        MHTSP_VPP_edtTenVPP = findViewById(R.id.MHTSP_VPP_edtTenVPP);
        MHTSP_VPP_edtNhaPhanPhoi = findViewById(R.id.MHTSP_VPP_edtNhaPhanPhoi);
        MHTSP_VPP_edtXuatXu = findViewById(R.id.MHTSP_VPP_edtXuatXu);
        MHTSP_VPP_edtDonVi = findViewById(R.id.MHTSP_VPP_edtDonVi);
        MHTSP_VPP_edtGiaTien = findViewById(R.id.MHTSP_VPP_edtGiaTien);
        MHTSP_VPP_edtSoLuongKho = findViewById(R.id.MHTSP_VPP_edtSoLuongKho);
        MHTSP_VPP_imgHinhVPP = findViewById(R.id.MHTSP_VPP_imgHinhVPP);
        MHTTSP_VPP_btnNhapMoi = findViewById(R.id.MHTTSP_VPP_btnNhapMoi);
        MHTTSP_VPP_btnThemSanPham = findViewById(R.id.MHTTSP_VPP_btnThemSanPham);

        MHTTSP_VPP_btnThemSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ThemSanPhamVPPActivity.this);
                b.setTitle("CẢNH BÁO");
                b.setMessage("Bạn có muốn thêm Sản Phẩm vào Kho không?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fireBase.themSanPham_vpp(
                                "vpp" + MHTSP_VPP_edtMaVPP.getText().toString(),
                                MHTSP_VPP_edtMaVPP.getText() + ".png",
                                MHTSP_VPP_edtTenVPP.getText().toString(),
                                MHTSP_VPP_edtNhaPhanPhoi.getText().toString(),
                                MHTSP_VPP_edtXuatXu.getText().toString(),
                                MHTSP_VPP_edtDonVi.getText().toString(),
                                MHTSP_VPP_edtGiaTien.getText().toString(),
                                MHTSP_VPP_edtSoLuongKho.getText().toString()
                        );

                        //Kiểm tra các trường bỏ trống
                        if(MHTSP_VPP_edtMaVPP.getTouchables().isEmpty() || MHTSP_VPP_edtTenVPP.getTouchables().isEmpty() || MHTSP_VPP_edtNhaPhanPhoi.getTouchables().isEmpty() ||
                                MHTSP_VPP_edtXuatXu.getTouchables().isEmpty() || MHTSP_VPP_edtDonVi.getTouchables().isEmpty() ||
                                MHTSP_VPP_edtGiaTien.getTouchables().isEmpty() || MHTSP_VPP_edtSoLuongKho.getTouchables().isEmpty() ){
                            Toast.makeText(ThemSanPhamVPPActivity.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        }
                        // Tai anh len storage
                        ghiAnh(uri, MHTSP_VPP_edtMaVPP.getText().toString());
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
                Toast.makeText(ThemSanPhamVPPActivity.this, "Thêm sách thành công", Toast.LENGTH_SHORT).show();
            }
        });

        MHTTSP_VPP_btnNhapMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vanPhongPhams.clear();
            }
        });

        MHTSP_VPP_imgHinhVPP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ThemSanPhamVPPActivity.this);
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
            MHTSP_VPP_imgHinhVPP.setImageURI(uri);
        }
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(ThemSanPhamVPPActivity.this.getContentResolver(), photo, "Title", null);
            uri = Uri.parse(path);
            MHTSP_VPP_imgHinhVPP.setImageURI(uri);
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
