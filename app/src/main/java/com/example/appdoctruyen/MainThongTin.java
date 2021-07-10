package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.view.View;
import android.widget.TextView;

public class MainThongTin extends AppCompatActivity {
        TextView txtthongtin,txtfb,txtem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thong_tin);
        txtthongtin = (TextView)findViewById(R.id.tvthongtin);
        txtfb=findViewById(R.id.tvfb);
        txtem=findViewById(R.id.tvemail);
        String thongtin ="Ứng dụng được làm bởi nhóm pineapple \n\n"+
                         " Độ tin cậy 100% \n\n"+
                         " Phát hành vào năm 2021 \n\n"+
                         " Mọi ý kiến đóng góp gửi về DiemMy@gmail.com ";
        txtthongtin.setText(thongtin);
        txtfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=100008815335270"));
                startActivity(i);

            }
        });
        txtem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("https://mail.google.com/mail/u/0/?tab=rm&ogbl#inbox"));
                        startActivity(i);

            }
        });
    }
}