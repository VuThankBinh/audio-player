package com.example.sportify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tenbai,tentg;
    AppCompatButton playlist;
    ImageView play1,play2,play3,play4;
    int sttbai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<BaiHat> danhSachBaiHat = new ArrayList<>();

        // Tạo và thêm các đối tượng BaiHat vào danh sách
        BaiHat baiHat1 = new BaiHat(1, "Ai ố xì mà", "Nguyễn Đức Phương", "ai_o_si_ma.mp3","img_1");
        BaiHat baiHat2 = new BaiHat(2, "Nàng thơ", "Hoàng Dũng", "nang_tho.mp3","img_2");
        BaiHat baiHat3 = new BaiHat(3, "Thằng điên", "Justatee, Phương Ly", "thang_dien.mp3","img_3");
        BaiHat baiHat4 = new BaiHat(4, "Lối nhỏ", "Đen vâu", "loi_nho.mp3","img_4");

        danhSachBaiHat.add(baiHat1);
        danhSachBaiHat.add(baiHat2);
        danhSachBaiHat.add(baiHat3);
        danhSachBaiHat.add(baiHat4);
        tenbai=findViewById(R.id.tenbaihat);
        tentg=findViewById(R.id.tentacgia);
        play1=findViewById(R.id.play1);
        play2=findViewById(R.id.play2);
        play3=findViewById(R.id.play3);
        play4=findViewById(R.id.play4);

        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tenbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PhatNhac.class));
            }
        });
        tentg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PhatNhac.class));
            }
        });

    }

}