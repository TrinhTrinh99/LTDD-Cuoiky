package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appdoctruyen.adapter.adapterTruyen;
import com.example.appdoctruyen.database.Databasedoctruyen;
import com.example.appdoctruyen.model.Truyen;

import java.util.ArrayList;

public class MainAdmin extends AppCompatActivity {
     private ListView listView;
     private Button btnthem;
     ArrayList<Truyen> TruyenArrayList;
     adapterTruyen adaptertruyen;
    private Databasedoctruyen databasedoctruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        listView =(ListView)findViewById(R.id.lvadmin);
        btnthem =(Button)findViewById(R.id.btnthemtr);
        initList();
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lay id tai khoan de biet tai khoan admin nao vao chinh sua
                Intent intent1 = getIntent();
                int id = intent1.getIntExtra("Id", 0);
                //tiep tuc gui id qua man hinh them truyen

                Intent intent = new Intent(MainAdmin.this, MainDangBai.class);

                intent.putExtra("Id", id);
                startActivity(intent);
            }
        });
        // click long cho item

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int idtruyen =TruyenArrayList.get(position).getID();
                //xoa du lieu
                databasedoctruyen.Delete(idtruyen);
                Intent intent =new Intent(MainAdmin.this,MainAdmin.class);
                //cap nhat lai activity
                finish();
                startActivity(intent);
                Toast.makeText(MainAdmin.this,"xóa truyện thành công",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }




    private void initList() {
        TruyenArrayList = new ArrayList<>();
         databasedoctruyen = new Databasedoctruyen(this);
        Cursor cursor1 = databasedoctruyen.getData2();
        while (cursor1.moveToNext()) {

            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String hinhanh = cursor1.getString(3);
            int id_tk= cursor1.getInt(4);
            
            TruyenArrayList.add(new Truyen(id,tentruyen,noidung,hinhanh,id_tk));
            
            adaptertruyen = new adapterTruyen(getApplicationContext(),TruyenArrayList);
            listView.setAdapter(adaptertruyen);
        }
        cursor1.moveToFirst();
        cursor1.close();

    }
    }
