package com.example.nhasachonline.tools;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonline.R;

import java.io.ByteArrayOutputStream;

public class Test extends AppCompatActivity {
    private static final int CAMERA_PIC_REQUEST = 1337;
    Button upload;
    String imgString;
    ImageView imgView;
    SharePreferences sharePreferences = new SharePreferences();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chupanh);
        upload = findViewById(R.id.btnChupAnh);
        imgView = findViewById(R.id.imgAnhChup);
//        Bitmap photo = BitmapFactory.decodeResource(this.getResources(), R.drawable.anhdemo);
//       imgView.setImageBitmap(photo);
//        String test = toBase64(photo);
//        imgView.setImageBitmap(StringToBitMap(test));
        upload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imgString = toBase64(photo);
            imgView.setImageBitmap(photo);
        }
    }

    public String toBase64(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.NO_WRAP);
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
