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
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonline.item.ItemSach;
import com.example.nhasachonline.item.ItemVanPhongPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class NhapKhoSanPhamVPPActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

//    ArrayList<ItemSanPham> sanPhams = new ArrayList<>();

    EditText MHNKSP_VPP_edtMaVPP, MHNKSP_VPP_edtTenVPP, MHNKSP_VPP_edtNhaPhanPhoi, MHNKSP_VPP_edtXuatXu,
            MHNKSP_VPP_edtDonVi
            , MHNKSP_VPP_edtGiaTien,MHNKSP_VPP_edtSoLuongKho;
    ImageView MHNKSP_VPP_imgHinhVPP;
    Button MHNKSP_VPP_btnNhapKho;
    TextView MHNKSP_VPP_btnBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nhapkho_vpp_layout);
        MHNKSP_VPP_edtMaVPP = findViewById(R.id.MHNKSP_VPP_edtMaVPP);
        MHNKSP_VPP_edtTenVPP = findViewById(R.id.MHNKSP_VPP_edtTenVPP);
        MHNKSP_VPP_edtNhaPhanPhoi = findViewById(R.id.MHNKSP_VPP_edtNhaPhanPhoi);
        MHNKSP_VPP_edtXuatXu = findViewById(R.id.MHNKSP_VPP_edtXuatXu);
        MHNKSP_VPP_edtDonVi = findViewById(R.id.MHNKSP_VPP_edtDonVi);
        MHNKSP_VPP_edtGiaTien = findViewById(R.id.MHNKSP_VPP_edtGiaTien);
        MHNKSP_VPP_edtSoLuongKho = findViewById(R.id.MHNKSP_VPP_edtSoLuongKho);
        MHNKSP_VPP_imgHinhVPP = findViewById(R.id.MHNKSP_VPP_imgHinhVPP);
        MHNKSP_VPP_btnNhapKho = findViewById(R.id.MHNKSP_VPP_btnNhapKho);
        MHNKSP_VPP_btnBack = findViewById(R.id.MHNKSP_VPP_btnBack);
        ItemVanPhongPham vPPItem = (ItemVanPhongPham) getIntent().getSerializableExtra("vpp");
        if(vPPItem!=null){
            thongTinSanPham(vPPItem);
        }
        MHNKSP_VPP_btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        MHNKSP_VPP_btnNhapKho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(NhapKhoSanPhamVPPActivity.this);
                b.setTitle("CẢNH BÁO");
                b.setMessage("Bạn có muốn Nhập thêm số lượng sản phẩm Sản Phẩm không?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String maVPP =  MHNKSP_VPP_edtMaVPP.getText().toString();
                        String hinhVPP = maVPP.replaceAll("s","sach");
                        fireBase.suaVPP(NhapKhoSanPhamVPPActivity.this,
//                                "" +
//                                        "",
//                                MHSSP_Sach_imgHinhSach.getResources().toString(),
                                maVPP,
                                hinhVPP + ".png",
                                MHNKSP_VPP_edtTenVPP.getText().toString(),
                                MHNKSP_VPP_edtNhaPhanPhoi.getText().toString(),
                                MHNKSP_VPP_edtXuatXu.getText().toString(),
                                MHNKSP_VPP_edtDonVi.getText().toString(),
                                MHNKSP_VPP_edtGiaTien.getText().toString(),
                                MHNKSP_VPP_edtSoLuongKho.getText().toString()
                        );
                        //Kiểm tra các trường bỏ trống
                        if(MHNKSP_VPP_edtMaVPP.getTouchables().isEmpty() || MHNKSP_VPP_edtTenVPP.getTouchables().isEmpty() || MHNKSP_VPP_edtNhaPhanPhoi.getTouchables().isEmpty() ||
                                MHNKSP_VPP_edtXuatXu.getTouchables().isEmpty() || MHNKSP_VPP_edtDonVi.getTouchables().isEmpty() || MHNKSP_VPP_edtGiaTien.getTouchables().isEmpty() ||
                                MHNKSP_VPP_edtSoLuongKho.getTouchables().isEmpty()){
                            Toast.makeText(NhapKhoSanPhamVPPActivity.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
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
    public void thongTinSanPham(ItemVanPhongPham vanPhongPham){

//        Log.d(TAG, "onDataChange:" + vanPhongPham.getNamSanXuat());

        MHNKSP_VPP_edtMaVPP.setText(vanPhongPham.getMaVanPhongPham());
        MHNKSP_VPP_edtTenVPP.setText(vanPhongPham.getTenVanPhongPham());
        MHNKSP_VPP_edtNhaPhanPhoi.setText(vanPhongPham.getNhaPhanPhoi());
        MHNKSP_VPP_edtXuatXu.setText(vanPhongPham.getXuatXu());
        MHNKSP_VPP_edtDonVi.setText(vanPhongPham.getDonVi());
//        MHSSP_Sach_edtGiaTien.setText(sach.getGiaTien());
//        MHSSP_Sach_edtSoLuongKho.setText(sach.getSoLuongKho());
        MHNKSP_VPP_edtGiaTien.setText(String.valueOf(vanPhongPham.getGiaTien()));
        MHNKSP_VPP_edtSoLuongKho.setText(String.valueOf(vanPhongPham.getSoLuongKho()));

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(vanPhongPham.getAnhVanPhongPham());
        try {
            File file = null;
            if (vanPhongPham.getAnhVanPhongPham().contains("png")) {
                file = File.createTempFile(vanPhongPham.getAnhVanPhongPham().substring(0,vanPhongPham.getAnhVanPhongPham().length()-4), "png");
            } else if (vanPhongPham.getAnhVanPhongPham().contains("jpg")) {
                file = File.createTempFile(vanPhongPham.getAnhVanPhongPham().substring(0,vanPhongPham.getAnhVanPhongPham().length()-4), "jpg");
            }
            final File fileHinh = file;
            ((StorageReference) storageReference).getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    MHNKSP_VPP_imgHinhVPP.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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
