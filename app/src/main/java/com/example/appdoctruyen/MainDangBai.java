package com.example.appdoctruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appdoctruyen.database.Databasedoctruyen;
import com.example.appdoctruyen.model.Truyen;

public class MainDangBai extends AppCompatActivity {
    private EditText edtTenTruyen, edtNoiDung,edtHinhAnh;
    private Button btndangbai,btnhuy;
    private Databasedoctruyen databasedoctruyen;
    private ImageView mImageView;
    private Button mChooseBtn;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_bai);
        anhXa();
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainDangBai.this,MainAdmin.class);
                startActivity(intent);
            }
        });
        btndangbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TenTruyen =edtTenTruyen.getText().toString();
                String NoiDung = edtNoiDung.getText().toString();
                String HinhAnh = edtHinhAnh.getText().toString();
                Truyen truyen = Createtruyen();

                if(TenTruyen.equals("")||NoiDung.equals("")||HinhAnh.equals("")){
                    Toast.makeText(MainDangBai.this,"Yêu cầu nhập đủ thông tin ",Toast.LENGTH_SHORT).show();
                    Log.e("Error ","Không thể tạo mới truyện");
                }else {
                    databasedoctruyen.AddTruyen(truyen);
                    Intent i = new Intent(MainDangBai.this,MainAdmin.class);

                    Toast.makeText(MainDangBai.this,"Tạo truyện thành công",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(i);
                }

            }
        });
        //handle button click
        mChooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check runtime permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED){
                        //permission not granted, request it.
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show popup for runtime permission
                        requestPermissions(permissions, PERMISSION_CODE);

                    }
                    else {
                        //permission already granted
                        pickImageFromGallery();
                    }
                }
                else {
                    //system os is less then marshmallow
                    pickImageFromGallery();
                }

            }
        });
    }

    private String pickImageFromGallery() {
        //intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
        return null;
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    //permission was granted
                    pickImageFromGallery();
                } else {
                    //permission was denied
                    Toast.makeText(this, "Permission denied...!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //handle result of picked image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            //set image to image view
            mImageView.setImageURI(data.getData());
            mImageView.setVisibility(View.VISIBLE);

        }
    }



    private void anhXa() {
        edtHinhAnh = findViewById(R.id.edtha);
        mImageView = findViewById(R.id.image_view);
        mChooseBtn = findViewById(R.id.choose_image_btn);
        edtNoiDung = (EditText) findViewById(R.id.edtdbnoidung);
        edtTenTruyen = (EditText) findViewById(R.id.edtdbtentr);
        btndangbai = (Button) findViewById(R.id.btnsave);
        btnhuy=(Button)findViewById(R.id.btncancel);
        databasedoctruyen = new Databasedoctruyen(this);
    }

    private Truyen Createtruyen() {
        String TenTruyen = edtTenTruyen.getText().toString();
        String NoiDung = edtNoiDung.getText().toString();
       // String HinhAnh = edtHinhAnh.getText().toString();
        String HinhAnh =pickImageFromGallery();
        Intent i = getIntent();
        int id = i.getIntExtra("Id",0);
        Truyen truyen = new Truyen(TenTruyen,NoiDung,HinhAnh,id);
        return truyen;

    }


}