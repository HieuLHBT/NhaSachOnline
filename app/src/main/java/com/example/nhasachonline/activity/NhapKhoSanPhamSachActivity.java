package com.example.nhasachonline.activity;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
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
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.ItemSach;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class NhapKhoSanPhamSachActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

//    ArrayList<ItemSanPham> sanPhams = new ArrayList<>();

    EditText MHNKSP_Sach_edtMaSach, MHNKSP_Sach_edtTenSach, MHNKSP_Sach_edtTheLoai, MHNKSP_Sach_edtTacGia, MHNKSP_Sach_edtNhaXuatBan
            , MHNKSP_Sach_edtNgayXuatBan, MHNKSP_Sach_edtGiaTien,MHNKSP_Sach_edtSoLuongKho;
    ImageView MHNKSP_Sach_imgAnhSanPham;
    Button MHNKSP_Sach_btnNhapKho;
    TextView MHNKSP_Sach_btnBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nhapkho_sach_layout);
        MHNKSP_Sach_edtMaSach = findViewById(R.id.MHNKSP_Sach_edtMaSach);
        MHNKSP_Sach_edtTenSach = findViewById(R.id.MHNKSP_Sach_edtTenSach);
        MHNKSP_Sach_edtTheLoai = findViewById(R.id.MHNKSP_Sach_edtTheLoai);
        MHNKSP_Sach_edtTacGia = findViewById(R.id.MHNKSP_Sach_edtTacGia);
        MHNKSP_Sach_edtNhaXuatBan = findViewById(R.id.MHNKSP_Sach_edtNhaXuatBan);
        MHNKSP_Sach_edtNgayXuatBan = findViewById(R.id.MHNKSP_Sach_edtNgayXuatBan);
        MHNKSP_Sach_edtGiaTien = findViewById(R.id.MHNKSP_Sach_edtGiaTien);
        MHNKSP_Sach_edtSoLuongKho = findViewById(R.id.MHNKSP_Sach_edtSoLuongKho);
        MHNKSP_Sach_imgAnhSanPham = findViewById(R.id.MHNKSP_Sach_imgAnhSanPham);
        MHNKSP_Sach_btnNhapKho = findViewById(R.id.MHNKSP_Sach_btnNhapKho);
        MHNKSP_Sach_btnBack = findViewById(R.id.MHNKSP_Sach_btnBack);
        ItemSach sachItem = (ItemSach) getIntent().getSerializableExtra("sach");
        if(sachItem!=null){
            thongTinSanPham(sachItem);
        }
        MHNKSP_Sach_btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        MHNKSP_Sach_btnNhapKho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(NhapKhoSanPhamSachActivity.this);
                b.setTitle("CẢNH BÁO");
                b.setMessage("Bạn có muốn Nhập thêm số lượng sản phẩm Sản Phẩm không?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String maSach =  MHNKSP_Sach_edtMaSach.getText().toString();
                        String hinhSach = maSach.replaceAll("s","sach");
                        fireBase.suaSach(NhapKhoSanPhamSachActivity.this,
//                                "" +
//                                        "",
//                                MHSSP_Sach_imgHinhSach.getResources().toString(),
                                maSach,
                                hinhSach + ".png",
                                MHNKSP_Sach_edtTenSach.getText().toString(),
                                MHNKSP_Sach_edtTheLoai.getText().toString(),
                                MHNKSP_Sach_edtTacGia.getText().toString(),
                                MHNKSP_Sach_edtNhaXuatBan.getText().toString(),
                                MHNKSP_Sach_edtNgayXuatBan.getText().toString(),
                                MHNKSP_Sach_edtGiaTien.getText().toString(),
                                MHNKSP_Sach_edtSoLuongKho.getText().toString()
                        );
                        //Kiểm tra các trường bỏ trống
                        if(MHNKSP_Sach_edtMaSach.getTouchables().isEmpty() || MHNKSP_Sach_edtTenSach.getTouchables().isEmpty() || MHNKSP_Sach_edtTheLoai.getTouchables().isEmpty() ||
                                MHNKSP_Sach_edtTacGia.getTouchables().isEmpty() || MHNKSP_Sach_edtNhaXuatBan.getTouchables().isEmpty() || MHNKSP_Sach_edtNgayXuatBan.getTouchables().isEmpty() ||
                                MHNKSP_Sach_edtGiaTien.getTouchables().isEmpty() || MHNKSP_Sach_edtSoLuongKho.getTouchables().isEmpty()){
                            Toast.makeText(NhapKhoSanPhamSachActivity.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        }
                        finish();
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

        MHNKSP_Sach_edtMaSach.setText(sach.getMaSach());
        MHNKSP_Sach_edtTenSach.setText(sach.getTenSach());
        MHNKSP_Sach_edtTheLoai.setText(sach.getTheLoai());
        MHNKSP_Sach_edtTacGia.setText(sach.getTacGia());
        MHNKSP_Sach_edtNhaXuatBan.setText(sach.getNhaXuatBan());
        MHNKSP_Sach_edtNgayXuatBan.setText(sach.getNamSanXuat());
//        MHSSP_Sach_edtGiaTien.setText(sach.getGiaTien());
//        MHSSP_Sach_edtSoLuongKho.setText(sach.getSoLuongKho());
        MHNKSP_Sach_edtGiaTien.setText(String.valueOf(sach.getGiaTien()));
        MHNKSP_Sach_edtSoLuongKho.setText(String.valueOf(sach.getSoLuongKho()));

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
                    MHNKSP_Sach_imgAnhSanPham.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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
