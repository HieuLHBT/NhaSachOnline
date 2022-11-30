package com.example.nhasachonline.activity;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.InputType;
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
                        int sLkhoTong;
                        sLkhoTong = Integer.parseInt(MHNKSP_Sach_edtSoLuongKho.getText().toString()) + sLCuKho;
                        String maSach =  MHNKSP_Sach_edtMaSach.getText().toString();
                        String hinhSach = maSach.replaceAll("s","sach");
                        fireBase.NhapKho(NhapKhoSanPhamSachActivity.this,
                                maSach,
                                hinhSach + ".png",
                                MHNKSP_Sach_edtTenSach.getText().toString(),
                                MHNKSP_Sach_edtTheLoai.getText().toString(),
                                MHNKSP_Sach_edtTacGia.getText().toString(),
                                MHNKSP_Sach_edtNhaXuatBan.getText().toString(),
                                MHNKSP_Sach_edtNgayXuatBan.getText().toString(),
                                MHNKSP_Sach_edtGiaTien.getText().toString(),
//                                MHNKSP_Sach_edtSoLuongKho.getText().toString()
                                ""+sLkhoTong
                        );
                        //Kiểm tra các trường bỏ trống
                        if( MHNKSP_Sach_edtSoLuongKho.getTouchables().isEmpty()){
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
    private int sLCuKho = 0;
    public void thongTinSanPham(ItemSach sach){

        Log.d(TAG, "onDataChange:" + sach.getNamSanXuat());
//      // mờ trường edit
        MHNKSP_Sach_edtMaSach.setEnabled(false);
        MHNKSP_Sach_edtTenSach.setEnabled(false);
        MHNKSP_Sach_edtTheLoai.setEnabled(false);
        MHNKSP_Sach_edtTacGia.setEnabled(false);
        MHNKSP_Sach_edtNhaXuatBan.setEnabled(false);
        MHNKSP_Sach_edtNgayXuatBan.setEnabled(false);
        MHNKSP_Sach_edtGiaTien.setEnabled(false);

        MHNKSP_Sach_edtMaSach.setText(sach.getMaSach());
        MHNKSP_Sach_edtTenSach.setText(sach.getTenSach());
        MHNKSP_Sach_edtTheLoai.setText(sach.getTheLoai());
        MHNKSP_Sach_edtTacGia.setText(sach.getTacGia());
        MHNKSP_Sach_edtNhaXuatBan.setText(sach.getNhaXuatBan());
        MHNKSP_Sach_edtNgayXuatBan.setText(sach.getNamSanXuat());
//        MHSSP_Sach_edtGiaTien.setText(sach.getGiaTien());
//        MHSSP_Sach_edtSoLuongKho.setText(sach.getSoLuongKho());
        MHNKSP_Sach_edtGiaTien.setText(String.valueOf(sach.getGiaTien()));
        MHNKSP_Sach_edtSoLuongKho.setText("0");
        sLCuKho =sach.getSoLuongKho();


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
