package com.example.appdoctruyen;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.appdoctruyen.adapter.adapterTruyen;
import com.example.appdoctruyen.adapter.adapterchuyenmuc;
import com.example.appdoctruyen.adapter.adapterthongtin;
import com.example.appdoctruyen.database.Databasedoctruyen;

import com.example.appdoctruyen.model.Itemmenu;
import com.example.appdoctruyen.model.TaiKhoan;
import com.example.appdoctruyen.model.Truyen;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
   private ListView listView, listViewnew, listViewthongtin;
    DrawerLayout drawerLayout;
    ArrayList<Itemmenu> chuyenmucArrayList;
    ArrayList<TaiKhoan> taiKhoanArrayList;

    adapterthongtin adapterthongtin;
    adapterchuyenmuc adapterchuyenmuc;

    ArrayList<Truyen> TruyenArrayList;
    adapterTruyen adapterTruyen;
   private Databasedoctruyen databasedoctruyen;
    String tentaikhoan;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//     Nhan dữ liệu từ main đăng nhập gửi tới
        databasedoctruyen = new Databasedoctruyen(this);
        Intent pq = getIntent();

         email = pq.getStringExtra("email");
        tentaikhoan = pq.getStringExtra("tentaikhoan");
        anhXa();
        actionBar();
        actionViewFlipper();

      listViewnew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Intent intent= new Intent(MainActivity.this,MainNoiDung.class);
              String tent = TruyenArrayList.get(position).getTenTruyen();
              String noidungt = TruyenArrayList.get(position).getNoiDung();
              intent.putExtra("ten truyen", tent);
              intent.putExtra("noi dung", noidungt);
              startActivity(intent);
          }
      });
    //  bat click item cho list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
//                    Đăng bài

                        Intent intent =new Intent(MainActivity.this,MainAdmin.class);
//                        gui idtai khoan qua admin

                        startActivity(intent);

                }
                    else if (position==1) {
                    Intent intent =new Intent(MainActivity.this,MainThongTin.class);
                    startActivity(intent);


                }
//                    đăng xuất
                    else if (position==2) {
                        finish();
                }
            }
        });
    }



    private void actionBar() {
//        hàm hỗ trợ toorbar
        setSupportActionBar(toolbar);
//        set nút cho actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//      tạo icon chotoorbar
        toolbar.setNavigationIcon(R.drawable.ic_menu);
//        tạo sự kiện nhấn nút
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    //  phuươg thức chạy quảng cáo với viewflipper
    private void actionViewFlipper() {
//        Tạo mảng chuứa ảnh cho quảng cáo
        ArrayList<String> mangquangcao = new ArrayList<>();
//        thêm ảnh vào mảng
        mangquangcao.add("http://hanoimoi.com.vn/Uploads/image/News/Thumbnails/2012/6/Thumbnails1943201206432019-Sach.jpg");
        mangquangcao.add("https://bloganchoi.com/wp-content/uploads/2019/05/sach-thieu-nhi-hay-7.jpg");
        mangquangcao.add("https://i.pinimg.com/originals/40/81/a8/4081a82e844ef5073b18f51798279d0d.jpg");
        mangquangcao.add("https://i.pinimg.com/originals/4c/a8/66/4ca86645a111d3bb69d5924875479f29.jpg");
        mangquangcao.add("https://i.vdoc.vn/data/image/2020/05/14/truyen-ngan-y-nghia-6.jpg");
//        tạo vòng for gán ảnh vào imageview để hiển thị lên app
        for(int i=0;i<mangquangcao.size();i++){
//            tạo mới imageview và sử dụng getapplicationcontext để lấy dữ liệu cập nhật trong bài
            ImageView imageView =  new ImageView(getApplicationContext());
//            sử dụng hàm thư viện picasso lấy ảnh và tải ảnhtrong mẢNG QUẢNG CÁO sử dụng biến i để lấy ảnh lên imageview
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
//            chỉnh ảnh vừa khung quảng cáo đã tạo
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            lấy ảnh từ imageview đổ lên viêw fplitêrr
            viewFlipper.addView(imageView);

        }
// thiết lập tự động chạy cho ảnh 4000=4 giây
        viewFlipper.setFlipInterval(4000);
//        chạy viewFliper
        viewFlipper.setAutoStart(true);
//        gọi animation chovào và ra
        Animation ani_slide_in_r = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation ani_slide_out_r = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
//        ép animation vào flipper
        viewFlipper.setInAnimation(ani_slide_in_r);
        viewFlipper.setInAnimation(ani_slide_out_r);
        
    }

    private void anhXa(){
        toolbar= findViewById(R.id.toolbarhinhchinh);
        viewFlipper=findViewById(R.id.viewflipper);
        listViewnew= findViewById(R.id.lvnew);
        listView=findViewById(R.id.listviewmanhinhchinh);
        listViewthongtin=findViewById(R.id.lvthongtin);
        navigationView=findViewById(R.id.navigationview);
        drawerLayout=findViewById(R.id.drawerlayout);
        TruyenArrayList = new ArrayList<>();
        Cursor cursor1 = databasedoctruyen.getData1();
        
        while (cursor1.moveToNext()) {
//                Lấy dữ liệu và gán vào biến, dữ liệu tài khoản ở số 1, mk 2,idtaikhoan 0, email 3,phân quyền 4
            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String hinhanh = cursor1.getString(2);
            String noidung = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);
            TruyenArrayList.add(new Truyen(id,tentruyen,hinhanh,noidung,id_tk));
            adapterTruyen = new adapterTruyen(getApplicationContext(),TruyenArrayList);
            listViewnew.setAdapter(adapterTruyen);
        }
        cursor1.moveToFirst();
        cursor1.close();
//        thong tin
        taiKhoanArrayList=new ArrayList<>();
        taiKhoanArrayList.add(new TaiKhoan(tentaikhoan,email));
        adapterthongtin = new adapterthongtin(this,R.layout.navigation_thongtin,taiKhoanArrayList);
        listViewthongtin.setAdapter(adapterthongtin);
//        chuyen muc
        chuyenmucArrayList = new ArrayList<>();
        chuyenmucArrayList.add(new Itemmenu("Đăng Bài",R.drawable.ic_baseline_post_add_24));
        chuyenmucArrayList.add(new Itemmenu("Thông Tin",R.drawable.ic_baseline_face_24));
        chuyenmucArrayList.add(new Itemmenu("Đăng Xuất",R.drawable.ic_baseline_login_24));
        adapterchuyenmuc = new adapterchuyenmuc(this,R.layout.itemchuyenmmuc,chuyenmucArrayList);
        listView.setAdapter(adapterchuyenmuc);

       }
//   napj môỵ menu tìmkiếm vào actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.menu1:
                Intent intent = new Intent(MainActivity.this,MainTimKiem.class);
                startActivity(intent);
            break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }



}