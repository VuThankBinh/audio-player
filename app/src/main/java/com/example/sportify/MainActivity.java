package com.example.sportify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LinearLayout bottom_layout;

    public static ArrayList<BaiHat> danhSachBaiHat = new ArrayList<>();
    public static ArrayList<BaiHat> danhSachBaiHatDefault = new ArrayList<>();
    static TextView tenbai;
    static TextView tentg;
    AppCompatButton playlist;
    ImageView play1,play2,play3,play4,play_pause,nextbai;
    public static int sttbai;
    public static MediaPlayer mp = new MediaPlayer();
    public static AudioManager audioManager;
    public static int currentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tạo và thêm các đối tượng BaiHat vào danh sách
        BaiHat baiHat1 = new BaiHat(1, "Ai ố xì mà", "Nguyễn Đức Phương", "ai_o_si_ma.mp3","img_1");
        BaiHat baiHat2 = new BaiHat(2, "Nàng thơ", "Hoàng Dũng", "nang_tho.mp3","img_6");
        BaiHat baiHat3 = new BaiHat(3, "Thằng điên", "Justatee, Phương Ly", "thang_dien.mp3","img_8");
        BaiHat baiHat4 = new BaiHat(4, "Lối nhỏ", "Đen vâu", "loi_nho.mp3","img_7");

        danhSachBaiHat.add(baiHat1);
        danhSachBaiHat.add(baiHat2);
        danhSachBaiHat.add(baiHat3);
        danhSachBaiHat.add(baiHat4);
        bottom_layout=findViewById(R.id.bottom_layout);
        tenbai=findViewById(R.id.tenbaihat);
        tentg=findViewById(R.id.tentacgia);
        play1=findViewById(R.id.play1);
        play2=findViewById(R.id.play2);
        play3=findViewById(R.id.play3);
        play4=findViewById(R.id.play4);
        play_pause=findViewById(R.id.play);
        playlist=findViewById(R.id.playlist);
        final boolean[] baml1 = {false};
        playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playlist.getText().toString().trim().toUpperCase().compareTo("PLAY")!=0){
                    if(baml1[0] ==false){
                        phatbai(1);
                        sttbai=1;
                        baml1[0] =true;
                    }
                    else {
                        pauseAudio();
                    }
                    playlist.setText("PAUSE");
                    play_pause.setImageResource(R.drawable.baseline_pause_24);

                }
                else {
                    pauseAudio();
                    playlist.setText("PLAY");
                    play_pause.setImageResource(R.drawable.play_5);
                }

            }
        });
        play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseAudio();
            }
        });
        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                phatbai(1);
                sttbai=1;
            }
        });
        play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                phatbai(2);
                sttbai=2;
            }
        });
        play3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                phatbai(3);
                sttbai=3;
            }
        });
        play4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                phatbai(4);
                sttbai=4;
            }
        });
        nextbai=findViewById(R.id.nextsong);
        nextbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextbai1();

            }
        });
        bottom_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PhatNhac.class));
            }
        });
        tenbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,PhatNhac.class));
            }
        });
        tentg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,PhatNhac.class));
            }
        });


    }
    private String millisecondsToMMSS(int milliseconds) {
        int seconds = (milliseconds / 1000) % 60;
        int minutes = (milliseconds / (1000 * 60)) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
    static void LoadBaiHienTai() {
        tenbai.setText(danhSachBaiHat.get(sttbai-1).getTenBaiHat());
        tentg.setText(danhSachBaiHat.get(sttbai-1).getCaSi());


        int currentPos = mp.getCurrentPosition();
        int duration = mp.getDuration();
        int remainingTime = duration - currentPos;

    }
    public void  nextbai1(){
        if(sttbai<4){
            phatbai(sttbai+1);
            sttbai+=1;
        }
        else {
            phatbai(1);
            sttbai=1;
        }
    }
    public void phatbai(int stt){
//        Toast.makeText(MainActivity.this, "bài số"+sttbai, Toast.LENGTH_SHORT).show();
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        tenbai.setText(danhSachBaiHat.get(stt-1).getTenBaiHat());
        tentg.setText(" ("+danhSachBaiHat.get(stt-1).getCaSi()+")");
        try {
            mp.reset();
            switch (stt){
                case 1:{
                    mp.setDataSource(getResources().openRawResourceFd(R.raw.ai_o_si_ma));
                    break;
                }
                case 2:{
                    mp.setDataSource(getResources().openRawResourceFd(R.raw.nang_tho));
                    break;
                }
                case 3:{
                    mp.setDataSource(getResources().openRawResourceFd(R.raw.thang_dien));
                    break;
                }
                case 4:{
                    mp.setDataSource(getResources().openRawResourceFd(R.raw.loi_nho));
                    break;
                }
            }
            // Sử dụng getResource() để lấy đường dẫn đúng
            mp.prepare();
            mp.start();

            play_pause.setImageResource(R.drawable.baseline_pause_24);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if(sttbai<4){
                        phatbai(sttbai+1);
                        sttbai+=1;
                    }
                    else {
                        phatbai(1);
                        sttbai=1;
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void stopAudio() {
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }
    public void pauseAudio() {
        if (mp != null && mp.isPlaying()) {
            mp.pause();
            currentPosition = mp.getCurrentPosition();
            play_pause.setImageResource(R.drawable.play_5);
        }
        else {
            if(currentPosition == 0) {
                phatbai(1);
                sttbai=1;
            }
            else {
                mp.seekTo(currentPosition);
                mp.start();
                play_pause.setImageResource(R.drawable.baseline_pause_24);
            }
        }

    }
}