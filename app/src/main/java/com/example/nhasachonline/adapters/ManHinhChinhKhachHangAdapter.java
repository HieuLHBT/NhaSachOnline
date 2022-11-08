package com.example.nhasachonline.adapters;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonline.R;
import com.example.nhasachonline.activity.GioHangActivity;
import com.example.nhasachonline.item.ItemSanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.io.Resources;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class ManHinhChinhKhachHangAdapter extends RecyclerView.Adapter<ManHinhChinhKhachHangAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<ItemSanPham> sanPhams;
    private ManHinhChinhKhachHangAdapter.OnItemClickListener onItemClickListener;

    public ManHinhChinhKhachHangAdapter(Activity context, int resource, ArrayList<ItemSanPham> sanPhams) {
        this.context = context;
        this.resource = resource;
        this.sanPhams = sanPhams;
    }

    public void setFilteredList(ArrayList<ItemSanPham> filteredList){
        this.sanPhams = filteredList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        final int pos = position;
        ItemSanPham sanpham = sanPhams.get(pos);
        holder.itemMHCKH_tvTenSanPham.setText(sanpham.getTenSanPham());
        holder.itemMHCKH_tvGia.setText(formatter.format(sanpham.getGiaSanPham()) + " VNĐ");
        holder.itemMHCKH_edtSoLuong.setText(sanpham.getSoLuong() + "");
        holder.itemMHCKH_tvSoLuongDanhGia.setText( " ");

        holder.itemMHCKH_edtSoLuong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count != 0) {
                    if (s.charAt(0) == '0') {
                        AlertDialog.Builder b = new AlertDialog.Builder(context);
                        b.setTitle("CẢNH BÁO!");
                        b.setMessage("Bạn không được nhập số 0");
                        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                                holder.itemMHCKH_edtSoLuong.setBackgroundColor(context.getResources().getColor(R.color.red, context.getTheme()));
                            }
                        });
                        AlertDialog al = b.create();
                        al.show();
                    } else {
                        holder.itemMHCKH_edtSoLuong.setBackgroundColor(context.getResources().getColor(R.color.white, context.getTheme()));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        if(sanpham.getMaSanPham().contains("s")){
            holder.itemMHCKH_tvTacGia.setVisibility(View.VISIBLE);
            holder.itemMHCKH_tvXuatXu.setVisibility(View.GONE);
            holder.itemMHCKH_tvDuLieu.setText(sanpham.getTacGia());
        }else if(sanpham.getMaSanPham().contains("vpp")){
            holder.itemMHCKH_tvTacGia.setVisibility(View.GONE);
            holder.itemMHCKH_tvXuatXu.setVisibility(View.VISIBLE);
            holder.itemMHCKH_tvDuLieu.setText(sanpham.getXuatXu());
        }

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(sanpham.getHinhSanPham());
        try {
            File file = null;
            if (sanpham.getHinhSanPham().contains("png")) {
                file = File.createTempFile(sanpham.getHinhSanPham().substring(0,sanpham.getHinhSanPham().length()-4), "png");
            } else if (sanpham.getHinhSanPham().contains("jpg")) {
                file = File.createTempFile(sanpham.getHinhSanPham().substring(0,sanpham.getHinhSanPham().length()-4), "jpg");
            }
            final File fileHinh = file;
            ((StorageReference) storageReference).getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    holder.itemMHCKH_imgHinhSanPham.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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

        // Event processing
        holder.onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClickListener(pos, holder.itemView);
                }
            }
        };
    }

    @Override
    public int getItemCount() {
        return sanPhams.size();
    }

    @Override
    public int getItemViewType(int position) {
        return resource;
    }

    // ViewHolder definition
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemMHCKH_tvTenSanPham;
        TextView itemMHCKH_tvTacGia;
        TextView itemMHCKH_tvXuatXu;
        TextView itemMHCKH_tvDuLieu;
        TextView itemMHCKH_tvGia;
        TextView itemMHCKH_tvSoLuongDanhGia;
        EditText itemMHCKH_edtSoLuong;
        ImageView itemMHCKH_imgHinhSanPham;
        ImageView itemMHCKH_img1Sao;
        ImageView itemMHCKH_img2Sao;
        ImageView itemMHCKH_img3Sao;
        ImageView itemMHCKH_img4Sao;
        ImageView itemMHCKH_img5Sao;
        ImageButton itemMHCKH_btnThemVaoGioHang;
        ImageButton itemMHCKH_btnLogout;
        View.OnClickListener onClickListener;
        LinearLayout itemMHCKH_llCardView;
        CardView itemMHCKH;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemMHCKH_tvTenSanPham = itemView.findViewById(R.id.itemMHCKH_tvTenSanPham);
            itemMHCKH_tvTacGia = itemView.findViewById(R.id.itemMHCKH_tvTacGia);
            itemMHCKH_tvXuatXu = itemView.findViewById(R.id.itemMHCKH_tvXuatXu);
            itemMHCKH_tvDuLieu = itemView.findViewById(R.id.itemMHCKH_tvDL);
            itemMHCKH_tvGia = itemView.findViewById(R.id.itemMHCKH_tvGiaTien);
            itemMHCKH_tvSoLuongDanhGia = itemView.findViewById(R.id.itemMHCKH_tvSLDanhGia);
            itemMHCKH_edtSoLuong = itemView.findViewById(R.id.itemMHCKH_edtSoLuong);
            itemMHCKH_imgHinhSanPham = itemView.findViewById(R.id.itemMHCKH_imgAnhSanPham);
            itemMHCKH_img1Sao = itemView.findViewById(R.id.itemMHCKH_img1Sao);
            itemMHCKH_img2Sao = itemView.findViewById(R.id.itemMHCKH_img2Sao);
            itemMHCKH_img3Sao = itemView.findViewById(R.id.itemMHCKH_img3Sao);
            itemMHCKH_img4Sao = itemView.findViewById(R.id.itemMHCKH_img4Sao);
            itemMHCKH_img5Sao = itemView.findViewById(R.id.itemMHCKH_img5Sao);
            itemMHCKH_btnThemVaoGioHang = itemView.findViewById(R.id.itemMHCKH_btnThemGioHang);
            itemMHCKH_llCardView = itemView.findViewById(R.id.itemMHCKH_llCardView);
            itemMHCKH = itemView.findViewById(R.id.itemManHinhChinhKH);
            itemMHCKH_btnLogout = itemView.findViewById(R.id.layoutMHCKH_btnLogout);

            // Set event processing
            itemMHCKH_btnThemVaoGioHang.setOnClickListener(this);
            //  itemMHCKH_btnLogout.setOnClickListener(this);
            itemMHCKH_tvTenSanPham.setOnClickListener(this);
        }

        public void onClick(View view) {
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }
    // Interface for event processing
    public interface OnItemClickListener {
        void onItemClickListener(int position, View view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
