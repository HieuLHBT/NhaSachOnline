package com.example.nhasachonline.activity;

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

import com.example.nhasachonline.data_model.Sach;
import com.example.nhasachonline.data_model.VanPhongPham;
import com.example.nhasachonline.firebase.FireBaseNhaSachOnline;

import com.example.nhasachonline.item.ItemSach;
import com.example.nhasachonline.item.ItemSanPham;
import com.example.nhasachonline.item.ItemVanPhongPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SuaSanPhamVPPActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    ArrayList<ItemSanPham> sanPhams = new ArrayList<>();
    EditText MHSSP_VPP_edtMaVPP, MHSSP_VPP_edtTenVPP, MHSSP_VPP_edtNhanPhanPhoi, MHSSP_VPP_edtXuatXu, MHSSP_VPP_edtDonVi
            , MHSSP_VPP_edtGiaTien, MHSSP_VPP_edtSoLuongKho;
    ImageView MHSSP_VPP_imgHinhSP;
    Button MHSSP_VPP_btnNhapMoi, MHSSP_VPP_btnSuaSach;
    TextView MHSSP_VPP_btnBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_suasanpham_vpp_layout);
        MHSSP_VPP_edtMaVPP = findViewById(R.id.MHSSP_VPP_edtMaVPP);
        MHSSP_VPP_edtTenVPP = findViewById(R.id.MHSSP_VPP_edtTenVPP);
        MHSSP_VPP_edtNhanPhanPhoi = findViewById(R.id.MHSSP_VPP_edtNhanPhanPhoi);
        MHSSP_VPP_edtXuatXu = findViewById(R.id.MHSSP_VPP_edtXuatXu);
        MHSSP_VPP_edtDonVi = findViewById(R.id.MHSSP_VPP_edtDonVi);
        MHSSP_VPP_edtGiaTien = findViewById(R.id.MHSSP_VPP_edtGiaTien);
        MHSSP_VPP_edtSoLuongKho = findViewById(R.id.MHSSP_VPP_edtSoLuongKho);
        MHSSP_VPP_imgHinhSP = findViewById(R.id.MHSSP_VPP_imgHinhSP);
//        MHSSP_VPP_btnNhapMoi = findViewById(R.id.MHSSP_VPP_btnNhapMoi);
        MHSSP_VPP_btnSuaSach = findViewById(R.id.MHSSP_VPP_btnSuaSach);
        MHSSP_VPP_btnBack = findViewById(R.id.MHSSP_VPP_btnBack);
        ItemVanPhongPham vPPItem = (ItemVanPhongPham) getIntent().getSerializableExtra("vpp");
        if(vPPItem!=null){
            thongTinSanPhamVPP(vPPItem);
        }
        MHSSP_VPP_btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        MHSSP_VPP_btnSuaSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(SuaSanPhamVPPActivity.this);
                b.setTitle("C???NH B??O");
                b.setMessage("B???n c?? mu???n s???a th??ng tin S???n Ph???m kh??ng?");
                b.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String maVPP =  MHSSP_VPP_edtMaVPP.getText().toString();
                        String hinhVPP = maVPP.replaceAll("vpp","vanphongpham");
                        fireBase.suaVPP(SuaSanPhamVPPActivity.this,
                                maVPP,
                                hinhVPP + ".png",
                                MHSSP_VPP_edtTenVPP.getText().toString(),
                                MHSSP_VPP_edtNhanPhanPhoi.getText().toString(),
                                MHSSP_VPP_edtXuatXu.getText().toString(),
                                MHSSP_VPP_edtDonVi.getText().toString(),
                                MHSSP_VPP_edtGiaTien.getText().toString(),
                                MHSSP_VPP_edtSoLuongKho.getText().toString()
                        );
                        //Ki???m tra c??c tr?????ng b??? tr???ng
                        if(MHSSP_VPP_edtMaVPP.getTouchables().isEmpty() || MHSSP_VPP_edtTenVPP.getTouchables().isEmpty() || MHSSP_VPP_edtNhanPhanPhoi.getTouchables().isEmpty() ||
                                MHSSP_VPP_edtXuatXu.getTouchables().isEmpty() || MHSSP_VPP_edtDonVi.getTouchables().isEmpty() ||
                                MHSSP_VPP_edtGiaTien.getTouchables().isEmpty() || MHSSP_VPP_edtSoLuongKho.getTouchables().isEmpty()){
                            Toast.makeText(SuaSanPhamVPPActivity.this, "??i???n ?????y ????? th??ng tin", Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }
                });
                b.setNegativeButton("Kh??ng ?????ng ??", new DialogInterface.OnClickListener() {
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
    public void thongTinSanPhamVPP(ItemVanPhongPham vanPhongPham){
        //disable edit
        MHSSP_VPP_edtMaVPP.setEnabled(false);
        MHSSP_VPP_edtSoLuongKho.setEnabled(false);
        MHSSP_VPP_edtMaVPP.setText(vanPhongPham.getMaVanPhongPham());
        MHSSP_VPP_edtTenVPP.setText(vanPhongPham.getTenVanPhongPham());
        MHSSP_VPP_edtNhanPhanPhoi.setText(vanPhongPham.getNhaPhanPhoi());
        MHSSP_VPP_edtXuatXu.setText(vanPhongPham.getXuatXu());
        MHSSP_VPP_edtDonVi.setText(vanPhongPham.getDonVi());
        MHSSP_VPP_edtGiaTien.setText(String.valueOf(vanPhongPham.getGiaTien()));
        MHSSP_VPP_edtSoLuongKho.setText(String.valueOf(vanPhongPham.getSoLuongKho()));

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
                    MHSSP_VPP_imgHinhSP.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("onCancelled", "L???i!" + e.getMessage());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
