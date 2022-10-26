package com.example.nhasachonline.firebase;

import android.content.Context;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nhasachonline.activity.GioHangActivity;
import com.example.nhasachonline.adapters.GioHangRecyclerViewAdapter;
import com.example.nhasachonline.data_model.GioHang;
import com.example.nhasachonline.data_model.Sach;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FireBaseNhaSachOnline {
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
}
