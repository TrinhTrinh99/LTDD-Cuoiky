package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdoctruyen.database.Databasedoctruyen;

public class MainDangNhap extends AppCompatActivity {
   private EditText edtTaiKhoan,edtMatKhau;
   private Button btnDangNhap, btnDangKy;
  private   Databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_nhap);
        AnhXa();
//        dối tượng database doc truyen
        databasedoctruyen = new Databasedoctruyen(this);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                gan cho các biến là giá trị nhập vào từ edt
                String tentaikhoan = edtTaiKhoan.getText().toString();
                String matkhau = edtMatKhau.getText().toString();
//                Sử dụng con trỏ để lấy dữ liệu
                Cursor cursor = databasedoctruyen.getData();
//                Thực hiệnvòng lặp để cursor lấy dữ liệu với move tonext di chuyển tiếp
                while (cursor.moveToNext()){
//                Lấy dữ liệu và gán vào biến, dữ liệu tài khoản ở số 1, mk 2,idtaikhoan 0, email 3,phân quyền 4
                    String databasetaikhoan = cursor.getString(1);
                    String databasematkhau = cursor.getString(2);
//                Nếu tkvà mk khớp với database
                    if(databasetaikhoan.equals(tentaikhoan)&&databasematkhau.equals(matkhau)){
//                        Lấy dữ liệu phân quyền và id
                        int idd = cursor.getInt(0);
                        String tentk = cursor.getString(1);
                        String email = cursor.getString(3);
                        int phanquyen = cursor.getInt(4);

// chuyen man hinh
                        Intent intent = new Intent(MainDangNhap.this,MainActivity.class);
                        Log.e("Thông báo ","Đăng Nhập thành công");


//    gui du lieu qua mainactivity
                        intent.putExtra("idd",idd);
                        intent.putExtra("phan quyen",phanquyen);
                        intent.putExtra("email",email);
                        intent.putExtra("tentaikhoan",tentk);
                        startActivity(intent);
                    }

                }
//                thực hiện trả cursor về đầu và admin đóng khi không đúng
                cursor.moveToFirst();
                cursor.close();
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainDangNhap.this,MainDangKi.class);
                startActivity(intent);

            }
        });
    }
    private void AnhXa(){
        edtTaiKhoan =(EditText)findViewById(R.id.taikhoan);
        edtMatKhau =(EditText)findViewById(R.id.matkhau);
        btnDangKy=(Button)findViewById(R.id.dangki);
        btnDangNhap=(Button)findViewById(R.id.dangnhap);

    }
}