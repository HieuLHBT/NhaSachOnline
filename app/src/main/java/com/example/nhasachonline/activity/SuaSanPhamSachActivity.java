package com.example.nhasachonline.activity;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;
import com.example.nhasachonline.data_model.NhanVien;
import com.example.nhasachonline.data_model.Sach;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;

import com.example.nhasachonline.item.ItemSach;
import com.example.nhasachonline.item.ItemSanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SuaSanPhamSachActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

//    ArrayList<ItemSanPham> sanPhams = new ArrayList<>();

    EditText MHSSP_Sach_edtMaSach, MHSSP_Sach_edtTenSach, MHSSP_Sach_edtTheLoai, MHSSP_Sach_edtTacGia, MHSSP_Sach_edtNhaXuatBan
            , MHSSP_Sach_edtNgayXuatBan, MHSSP_Sach_edtGiaTien, MHSSP_Sach_edtSoLuongKho;
    ImageView MHSSP_Sach_imgHinhSach;
    Button MHSSP_Sach_btnNhapMoi,
            MHSSP_Sach_btnSuaSach;
    TextView MHSSP_Sach_btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_suasanpham_sach_layout);
        MHSSP_Sach_edtMaSach = findViewById(R.id.MHSSP_Sach_edtMaSach);
        MHSSP_Sach_edtTenSach = findViewById(R.id.MHSSP_Sach_edtTenSach);
        MHSSP_Sach_edtTheLoai = findViewById(R.id.MHSSP_Sach_edtTheLoai);
        MHSSP_Sach_edtTacGia = findViewById(R.id.MHSSP_Sach_edtTacGia);
        MHSSP_Sach_edtNhaXuatBan = findViewById(R.id.MHSSP_Sach_edtNhaXuatBan);
        MHSSP_Sach_edtNgayXuatBan = findViewById(R.id.MHSSP_Sach_edtNgayXuatBan);
        MHSSP_Sach_edtGiaTien = findViewById(R.id.MHSSP_Sach_edtGiaTien);
        MHSSP_Sach_edtSoLuongKho = findViewById(R.id.MHSSP_Sach_edtSoLuongKho);
        MHSSP_Sach_imgHinhSach = findViewById(R.id.MHSSP_Sach_imgHinhSP);
        MHSSP_Sach_btnNhapMoi = findViewById(R.id.MHSSP_Sach_btnNhapMoi);
        MHSSP_Sach_btnSuaSach = findViewById(R.id.MHSSP_Sach_btnSuaSach);
        MHSSP_Sach_btnBack = findViewById(R.id.MHSSP_Sach_btnBack);
        ItemSach sachItem = (ItemSach) getIntent().getSerializableExtra("sach");
        if(sachItem!=null){
            thongTinSanPham(sachItem);
        }

        MHSSP_Sach_btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        MHSSP_Sach_btnSuaSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(SuaSanPhamSachActivity.this);
                b.setTitle("CẢNH BÁO");
                b.setMessage("Bạn có muốn sửa thông tin Sản Phẩm không?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fireBase.suaSach(SuaSanPhamSachActivity.this,
                                "" +
                                        "",
                                MHSSP_Sach_imgHinhSach.getResources().toString(),
                                MHSSP_Sach_edtTenSach.getText().toString(),
                                MHSSP_Sach_edtTheLoai.getText().toString(),
                                MHSSP_Sach_edtTacGia.getText().toString(),
                                MHSSP_Sach_edtNhaXuatBan.getText().toString(),
                                MHSSP_Sach_edtNgayXuatBan.getText().toString(),
                                MHSSP_Sach_edtGiaTien.getText().toString(),
                                MHSSP_Sach_edtSoLuongKho.getText().toString()
                        );
                        //Kiểm tra các trường bỏ trống
                        if(MHSSP_Sach_edtMaSach.getTouchables().isEmpty() || MHSSP_Sach_edtTenSach.getTouchables().isEmpty() || MHSSP_Sach_edtTheLoai.getTouchables().isEmpty() ||
                                MHSSP_Sach_edtTacGia.getTouchables().isEmpty() || MHSSP_Sach_edtNhaXuatBan.getTouchables().isEmpty() || MHSSP_Sach_edtNgayXuatBan.getTouchables().isEmpty() ||
                                MHSSP_Sach_edtGiaTien.getTouchables().isEmpty() || MHSSP_Sach_edtSoLuongKho.getTouchables().isEmpty()){
                            Toast.makeText(SuaSanPhamSachActivity.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
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
    public void thongTinSanPham(ItemSach sach){

        Log.d(TAG, "onDataChange:" + sach.getNamSanXuat());

        MHSSP_Sach_edtMaSach.setText(sach.getMaSach());
        MHSSP_Sach_edtTenSach.setText(sach.getTenSach());
        MHSSP_Sach_edtTheLoai.setText(sach.getTheLoai());
        MHSSP_Sach_edtTacGia.setText(sach.getTacGia());
        MHSSP_Sach_edtNhaXuatBan.setText(sach.getNhaXuatBan());
        MHSSP_Sach_edtNgayXuatBan.setText(sach.getNamSanXuat());
//        MHSSP_Sach_edtGiaTien.setText(sach.getGiaTien());
//        MHSSP_Sach_edtSoLuongKho.setText(sach.getSoLuongKho());
        MHSSP_Sach_edtGiaTien.setText(String.valueOf(sach.getGiaTien()));
        MHSSP_Sach_edtSoLuongKho.setText(String.valueOf(sach.getSoLuongKho()));

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(sach.getAnhSach());
        try {
            File file = null;
            if (sach.getAnhSach().contains("png")) {
                file = File.createTempFile(sach.getAnhSach().substring(0,sach.getAnhSach().length()-4), "png");
            } else if (sach.getAnhSach().contains("jpg")) {
                file = File.createTempFile(sach.getAnhSach().substring(0,sach.getAnhSach().length()-4), "jpg");
            }
            final File fileHinh = file;
            ((StorageReference) storageReference).getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    MHSSP_Sach_imgHinhSach.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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
