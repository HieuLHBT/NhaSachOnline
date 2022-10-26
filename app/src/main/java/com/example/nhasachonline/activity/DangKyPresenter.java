package com.example.nhasachonline.activity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class DangKyPresenter {
    private DangKyContract.View view;
    private FirebaseAuth mauth;
    private FirebaseFirestore mFirebase;
    private String TAG = "SIMPLE_TAG";
    public DangKyPresenter(DangKyContract.View view){
        this.view = view;
        this.mauth = FirebaseAuth.getInstance();
        this.mFirebase = FirebaseFirestore.getInstance();
    }



}
