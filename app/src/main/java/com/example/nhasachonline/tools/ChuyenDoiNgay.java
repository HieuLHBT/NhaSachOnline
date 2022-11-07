package com.example.nhasachonline.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChuyenDoiNgay {
    public long ChuyenStringThanhMiliGiay(String ngay) {
        long miliSeconds = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = sdf.parse(ngay);
            miliSeconds = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return miliSeconds;
    }
    public String ChuyenMiliGiayThanhString(long miliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(new Date(miliSeconds));
    }
}
