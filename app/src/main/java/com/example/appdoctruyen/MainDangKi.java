package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdoctruyen.database.Databasedoctruyen;
import com.example.appdoctruyen.model.TaiKhoan;

public class MainDangKi extends AppCompatActivity {
    EditText edtDKTaiKhoan,edtDKMatKhau,edtDKEmail;
    Button btnDKDangNhap, btnDKDangKy;
    Databasedoctruyen databasedoctruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_ki);
        databasedoctruyen = new Databasedoctruyen(this);
        AnhXa();
        // trở về đăng nhập
        btnDKDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();;

            }
        });
        btnDKDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = edtDKTaiKhoan.getText().toString();
                String matkhau = edtDKMatKhau.getText().toString();
                String email = edtDKEmail.getText().toString();
                TaiKhoan taiKhoan = CreateTaiKhoan();
            if(taiKhoan.equals("")||matkhau.equals("")||email.equals("")){
            Log.e("Thông báo ","Thiếu thông tin");
            }else {
               databasedoctruyen.AddTaiKhoan(taiKhoan);

                Toast.makeText(MainDangKi.this,"Đăng kí thành công",Toast.LENGTH_LONG).show();
            }

            }
        });
    }
//    phương thức tạo taigf khoản
    private TaiKhoan CreateTaiKhoan(){
        String taikhoan = edtDKTaiKhoan.getText().toString();
        String matkhau = edtDKMatKhau.getText().toString();
        String email = edtDKEmail.getText().toString();
        int phanquyen = 1;
        TaiKhoan tk = new TaiKhoan(taikhoan,matkhau,email,phanquyen);
        return tk;
    }
    private void AnhXa(){
        edtDKEmail=(EditText)findViewById(R.id.dkemail);
        edtDKTaiKhoan =(EditText)findViewById(R.id.dktaikhoan);
        edtDKMatKhau =(EditText)findViewById(R.id.dkmatkhau);
        btnDKDangKy=(Button)findViewById(R.id.dkdangki);
        btnDKDangNhap=(Button)findViewById(R.id.dkdangnhap);

    }
}