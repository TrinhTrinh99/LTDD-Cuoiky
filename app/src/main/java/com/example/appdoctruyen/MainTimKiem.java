package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.appdoctruyen.adapter.adapterTruyen;
import com.example.appdoctruyen.database.Databasedoctruyen;
import com.example.appdoctruyen.model.Truyen;

import java.util.ArrayList;

public class MainTimKiem extends AppCompatActivity {
   private ListView lvtimkiem;
   private EditText edttimkiem;
   ArrayList<Truyen> TruyenArrayList;
   private Databasedoctruyen databasedoctruyen;
   private ArrayList<Truyen> arrayList;
   adapterTruyen adapterTruyen;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tim_kiem);
        lvtimkiem =(ListView)findViewById(R.id.lvtimkiem);
        edttimkiem=(EditText)findViewById(R.id.searchtr);
        initList();
        lvtimkiem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i  = new Intent(MainTimKiem.this,MainNoiDung.class);
                String tent = arrayList.get(position).getTenTruyen();
                String noidungt = arrayList.get(position).getNoiDung();
                i.putExtra("ten truyen", tent);
                i.putExtra("noi dung", noidungt);
                startActivity(i);
            }
        });
        edttimkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
              filter((s.toString()));
            }
        });
    }
//    searrch
    private void filter(String text){
//        xoa du lieu mang
        arrayList.clear();
        ArrayList<Truyen> filteredList = new ArrayList<>();
        for(Truyen item : TruyenArrayList){
            if(item.getTenTruyen().toLowerCase().contains(text.toLowerCase())){
//                them item vao filtereslisst
                filteredList.add(item);
                arrayList.add(item);
            }
        }
        adapterTruyen.filterList(filteredList);
    }

    private void initList() {
        TruyenArrayList = new ArrayList<>();
     arrayList = new ArrayList<>();
        databasedoctruyen = new Databasedoctruyen(this);
        Cursor cursor = databasedoctruyen.getData2();
        while (cursor.moveToNext()) {
//                Lấy dữ liệu và gán vào biến, dữ liệu tài khoản ở số 1, mk 2,idtaikhoan 0, email 3,phân quyền 4
            int id = cursor.getInt(0);
            String tentruyen = cursor.getString(1);
            String hinhanh = cursor.getString(2);
            String noidung = cursor.getString(3);
            int id_tk = cursor.getInt(4);
            TruyenArrayList.add(new Truyen(id,tentruyen,hinhanh,noidung,id_tk));
            arrayList.add(new Truyen(id,tentruyen,hinhanh,noidung,id_tk));
            adapterTruyen = new adapterTruyen(getApplicationContext(),TruyenArrayList);
            lvtimkiem.setAdapter(adapterTruyen);
        }
        cursor.moveToFirst();
        cursor.close();

    }
}