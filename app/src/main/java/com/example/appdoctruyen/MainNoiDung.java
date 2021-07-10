package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class MainNoiDung extends AppCompatActivity {
    private TextView txtTenTruyen,txtNoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_noi_dung);
   txtNoiDung= (TextView)  findViewById(R.id.tvnoidung);
   txtTenTruyen=(TextView)findViewById(R.id.tvtentruyen);
        Intent i = getIntent();
//        lay du lieu tu main activity
        String tentruyen =i.getStringExtra("ten truyen");
        String noidung = i.getStringExtra("noi dung");
        txtTenTruyen.setText(tentruyen);
        txtNoiDung.setText(noidung);
//        cho phep cuon noi dung truyen
        txtNoiDung.setMovementMethod(new ScrollingMovementMethod());

    }
}