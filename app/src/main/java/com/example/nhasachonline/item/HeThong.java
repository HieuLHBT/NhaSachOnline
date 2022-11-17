package com.example.nhasachonline.item;

import com.google.firebase.auth.PhoneAuthProvider;

public class HeThong {
    private PhoneAuthProvider.ForceResendingToken mForceResendingToken;
    private String vertificationID;

    public PhoneAuthProvider.ForceResendingToken getmForceResendingToken() {
        return mForceResendingToken;
    }

    public String getVertificationID() {
        return vertificationID;
    }

    public void setmForceResendingToken(PhoneAuthProvider.ForceResendingToken mForceResendingToken) {
        this.mForceResendingToken = mForceResendingToken;
    }

    public void setVertificationID(String vertificationID) {
        this.vertificationID = vertificationID;
    }

    public HeThong() {
    }

    public HeThong(PhoneAuthProvider.ForceResendingToken mForceResendingToken, String vertificationID) {
        this.mForceResendingToken = mForceResendingToken;
        this.vertificationID = vertificationID;
    }
}
