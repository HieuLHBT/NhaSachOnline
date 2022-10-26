package com.example.nhasachonline.firebase;

import android.content.Context;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.nhasachonline.activity.GioHangActivity;
import com.example.nhasachonline.adapters.GioHangRecyclerViewAdapter;
import com.example.nhasachonline.data_model.GioHang;
import com.example.nhasachonline.data_model.Sach;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.ChildEventListener;
import com.example.nhasachonline.data_model.VanPhongPham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FireBaseNhaSachOnline {
    public void hienThiGioHang(String maKhachHang, ArrayList<com.example.nhasachonline.item.GioHang> gioHangItem, GioHangRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference gioHangDatabase = firebaseDatabase.getReference("GIOHANG");
        ArrayList<GioHang> gioHangDataModel = new ArrayList<>();
        gioHangDatabase.child(maKhachHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    for (DataSnapshot sanPhamSnapshot : snapshot.getChildren()) {
                        gioHangDataModel.add(sanPhamSnapshot.getValue(GioHang.class));
                    }
                    for (int i = 0; i < gioHangDataModel.size(); i++) {
                        if (gioHangDataModel.get(i).getMaSanPham().contains("s")) {
                            getSachDatabase(i ,gioHangDataModel, gioHangItem, adapter, context);
                        } else if (gioHangDataModel.get(i).getMaSanPham().contains("vpp")) {
                            getVanPhongPhamDatabase(i ,gioHangDataModel, gioHangItem, adapter, context);
                        }
                    }

                }
                else {
                    Log.d("onDataChange", "Không có dữ liệu!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    private void getSachDatabase(int i, ArrayList<GioHang> gioHangDataModel, ArrayList<com.example.nhasachonline.item.GioHang> gioHangItem, GioHangRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference sachDatabase = firebaseDatabase.getReference("SACH");
        sachDatabase.child(gioHangDataModel.get(i).getMaSanPham()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    Sach sach = snapshot.getValue(Sach.class);
                    gioHangItem.add(new com.example.nhasachonline.item.GioHang(sach.getTenSach(),Integer.valueOf(sach.getGiaTien()),Integer.valueOf(sach.getKhuyenMai()),Integer.valueOf(gioHangDataModel.get(i).getSoLuong())));
//                    Log.d("onDataChange",  gioHangItem.size() + "");
                    adapter.notifyDataSetChanged();
                    ((GioHangActivity)context).TongTienThanhToan();
                }
                else {
                    Log.d("onDataChange", "Không có dữ liệu!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getVanPhongPhamDatabase(int i, ArrayList<GioHang> gioHangDataModel, ArrayList<com.example.nhasachonline.item.GioHang> gioHangItem, GioHangRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference vanPhongPhamDatabase = firebaseDatabase.getReference("VANPHONGPHAM");
        vanPhongPhamDatabase.child(gioHangDataModel.get(i).getMaSanPham()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    VanPhongPham vanPhongPham = snapshot.getValue(VanPhongPham.class);
                    gioHangItem.add(new com.example.nhasachonline.item.GioHang(vanPhongPham.getTenVanPhongPham(),Integer.valueOf(vanPhongPham.getGiaTien()),Integer.valueOf(vanPhongPham.getKhuyenMai()),Integer.valueOf(gioHangDataModel.get(i).getSoLuong())));
//                    Log.d("onDataChange", gioHangItem.size()  + "");
                    adapter.notifyDataSetChanged();
                    ((GioHangActivity)context).TongTienThanhToan();
                }
                else {
                    Log.d("onDataChange", "Không có dữ liệu!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

//    public void test(Context context, GioHangRecyclerViewAdapter adapter, ArrayList<GioHang> gioHangs, Sach sach) {
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference databaseReference = firebaseDatabase.getReference("SACH");
//
    public void ghiSach(Context context, GioHangRecyclerViewAdapter adapter, ArrayList<GioHang> gioHangs, Sach sach) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("SACH");

//        Sach sach = new Sach("s4", 60000, "hinhsach4", 0, "20/04/2021", "Tuổi Trẻ", 15, "Nguyễn Nhật Ánh", "Kính Vạn Hoa", "Thiếu Nhi")
//        databaseReference.child(sach.getMaSach()).child("giatien").setValue(sach.getGiaTien(), new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                ((GioHangActivity)context).xyz(sachs);
//            }
//        });

//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        adapter.notifyDataSetChanged();
//    }

//    public void ghiSach(Sach sach) {
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference databaseReference = firebaseDatabase.getReference("SACH");
//        databaseReference.child("s5").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (task.getResult().getValue() != null) {
//                    Log.d("test", "có dữ liệu " + String.valueOf(task.getResult().getValue()));
//                }
//                else {
//                    Log.d("test", "không có dữ liệu");
//                }
//            }
//        });
//
//        databaseReference.child("s4").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.getValue() != null) {
//                    Sach sachtest = snapshot.getValue(Sach.class);
//                    Log.d("test", "có dữ liệu " + sachtest.getMaSach());
//                }
//                else {
//                    Log.d("test", "không có dữ liệu");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.d("onCancelled", "Lỗi!" + error.getMessage());
//            }
//        });
//    }
=======
//        })

        databaseReference.child("s4").child("giatien").setValue("60000");
        databaseReference.child("s4").child("hinhsach").setValue("hinhsach4");
        databaseReference.child("s4").child("khuyenmai").setValue("0");
        databaseReference.child("s4").child("ngayxuatban").setValue("20/04/2021");
        databaseReference.child("s4").child("nhaxuatban").setValue("Tuổi Trẻ");
        databaseReference.child("s4").child("soluongkho").setValue("15");
        databaseReference.child("s4").child("tacgia").setValue("Nguyễn Nhật Ánh");
        databaseReference.child("s4").child("tensach").setValue("Kính Vạn Hoa");
        databaseReference.child("s4").child("theloai").setValue("Thiếu Nhi");

        adapter.notifyDataSetChanged();



    }
>>>>>>> af982c5 (Firebase)
}
