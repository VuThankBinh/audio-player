package com.example.sportify;

import static com.example.sportify.MainActivity.currentPosition;
import static com.example.sportify.MainActivity.danhSachBaiHat;
import static com.example.sportify.MainActivity.mp;
import static com.example.sportify.MainActivity.sttbai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.ImageViewCompat;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class PhatNhac extends AppCompatActivity {
    ImageButton btn_back;
    ImageView btn_play,btn_previous_song,btn_next_song;
    ImageView img,repeat,shuffle;
    TextView txt_song_name, txt_artist_name, txt_current_time, txt_remaining_time;
    private Handler mHandler;
    private Runnable mRunnable;
    private SeekBar seekBar;
    boolean checkrepeat=false, checkshuffle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phat_nhac);
        btn_back = findViewById(R.id.btn_back);
        btn_play = findViewById(R.id.btn_play);
        btn_previous_song=findViewById(R.id.btn_previous_song);
        btn_next_song=findViewById(R.id.btn_next_song);
        txt_song_name = findViewById(R.id.txt_song_name);
        seekBar = findViewById(R.id.seekbar);
        txt_artist_name = findViewById(R.id.txt_artist_name);
        txt_current_time = findViewById(R.id.txt_current_time);
        txt_remaining_time = findViewById(R.id.txt_remaining_time);
        repeat=findViewById(R.id.btn_repeat);
        shuffle=findViewById(R.id.btn_suffer);
        img=findViewById(R.id.img);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.xoaytron);
        img.startAnimation(animation);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(checkrepeat){
                    mp.start();
                }
                else {
                    if(checkshuffle) {
                        int randomNumber;
                        Random random = new Random();
                        do {
                            randomNumber = random.nextInt(4 - 1 + 1) + 1;
                        } while (randomNumber == sttbai);
                        phatbai(randomNumber);
                        sttbai = randomNumber;
                    }
                    else {
                        if(sttbai<4){
                            phatbai(sttbai+1);
                            sttbai+=1;
                        }
                        else {
                            phatbai(1);
                            sttbai=1;
                        }
                    }
                }
            }
        });
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkrepeat=!checkrepeat;
                if(checkrepeat){
                    Drawable drawable = ContextCompat.getDrawable(PhatNhac.this, R.drawable.repeat);

                    // Tạo màu mới từ tài nguyên color
                    int newTintColor = ContextCompat.getColor(PhatNhac.this, R.color.timmongmo);

                    // Thay đổi màu của Drawable
                    DrawableCompat.setTint(drawable, newTintColor);

                    // Đặt Drawable đã được thay đổi màu làm background cho ImageView
                    repeat.setImageDrawable(drawable);
                }
                else {
                    Drawable drawable = ContextCompat.getDrawable(PhatNhac.this, R.drawable.repeat);

                    // Tạo màu mới từ tài nguyên color
                    int newTintColor = ContextCompat.getColor(PhatNhac.this, R.color.new_tint_color);

                    // Thay đổi màu của Drawable
                    DrawableCompat.setTint(drawable, newTintColor);

                    // Đặt Drawable đã được thay đổi màu làm background cho ImageView
                    repeat.setImageDrawable(drawable);
                }
            }
        });
        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkshuffle=!checkshuffle;
                if(checkshuffle){
                    Drawable drawable = ContextCompat.getDrawable(PhatNhac.this, R.drawable.shuffle);

                    // Tạo màu mới từ tài nguyên color
                    int newTintColor = ContextCompat.getColor(PhatNhac.this, R.color.timmongmo);

                    // Thay đổi màu của Drawable
                    DrawableCompat.setTint(drawable, newTintColor);

                    // Đặt Drawable đã được thay đổi màu làm background cho ImageView
                    shuffle.setImageDrawable(drawable);
                }
                else {
                    Drawable drawable = ContextCompat.getDrawable(PhatNhac.this, R.drawable.shuffle);

                    // Tạo màu mới từ tài nguyên color
                    int newTintColor = ContextCompat.getColor(PhatNhac.this, R.color.new_tint_color);

                    // Thay đổi màu của Drawable
                    DrawableCompat.setTint(drawable, newTintColor);

                    // Đặt Drawable đã được thay đổi màu làm background cho ImageView
                    shuffle.setImageDrawable(drawable);
                }
            }
        });
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseAudio();
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhatNhac.this.finish(); MainActivity.LoadBaiHienTai();
            }
        });
        btn_previous_song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backbai1();
            }
        });
        btn_next_song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextbai1();
            }
        });
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                updateSeekBar();
                updateCurrentTime();
                mHandler.postDelayed(this, 1000); // Cập nhật mỗi giây
            }
        };
        // Thiết lập sự kiện khi tua SeekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mp.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        LoadBaiHienTai();
    }
    public void phatbai(int stt){
        MainActivity.audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        txt_song_name.setText(danhSachBaiHat.get(stt-1).getTenBaiHat());
        txt_artist_name.setText(danhSachBaiHat.get(stt-1).getCaSi());
        String fileName = danhSachBaiHat.get(stt-1).getFileanh(); // Lấy tên tệp ảnh từ đối tượng baiHat
        int resId = getResources().getIdentifier(fileName, "drawable", getPackageName()); // Tìm ID tài nguyên dựa trên tên tệp ảnh
        if (resId != 0) {
            img.setImageResource(resId); // Thiết lập hình ảnh cho ImageView
        } else {
            // Xử lý trường hợp không tìm thấy tệp ảnh
        }
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

            btn_play.setImageResource(R.drawable.baseline_pause_24);
//            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                    if(checkrepeat){
//                        mp.start();
//                    }
//                    else {
//                        if(checkshuffle) {
//                            int randomNumber;
//                            Random random = new Random();
//                            do {
//                                randomNumber = random.nextInt(4 - 1 + 1) + 1;
//                            } while (randomNumber == sttbai);
//                            phatbai(randomNumber);
//                            sttbai = randomNumber;
//                        }
//                        else {
//                            if(sttbai<2){
//                                phatbai(4);
//                                sttbai=4;
//                            }
//                            else {
//                                phatbai(sttbai-1);
//                                sttbai-=1;
//                            }
//                        }
//                    }
//                }
//            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void LoadBaiHienTai() {
        txt_song_name.setText(danhSachBaiHat.get(sttbai-1).getTenBaiHat());
        txt_artist_name.setText(danhSachBaiHat.get(sttbai-1).getCaSi());

        String fileName = danhSachBaiHat.get(sttbai-1).getFileanh(); // Lấy tên tệp ảnh từ đối tượng baiHat
        int resId = getResources().getIdentifier(fileName, "drawable", getPackageName()); // Tìm ID tài nguyên dựa trên tên tệp ảnh
        if (resId != 0) {
            img.setImageResource(resId); // Thiết lập hình ảnh cho ImageView
        } else {
            // Xử lý trường hợp không tìm thấy tệp ảnh
        }

        int currentPos = mp.getCurrentPosition();
        int duration = mp.getDuration();
        int remainingTime = duration - currentPos;
        String currentPositionStr = millisecondsToMMSS(currentPos);
        String remainingTimeStr = millisecondsToMMSS(remainingTime);
        txt_current_time.setText(currentPositionStr);
        txt_remaining_time.setText(remainingTimeStr);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable, 1000); // Bắt đầu cập nhật khi Activity được hiển thị
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable); // Dừng cập nhật khi Activity tạm dừng
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mRunnable);
    }

    private void updateSeekBar() {
        int currentPosition = mp.getCurrentPosition();
        int duration = mp.getDuration();

        // Cập nhật vị trí của SeekBar
        seekBar.setMax(duration);
        seekBar.setProgress(currentPosition);
    }
    private void updateCurrentTime() {
        // Kiểm tra xem MediaPlayer đã được khởi tạo và đang phát nhạc chưa
        if (mp != null && mp.isPlaying()) {
            // Lấy thời gian hiện tại và còn lại từ MediaPlayer
            int currentPos = mp.getCurrentPosition();
            int duration = mp.getDuration();
            int remainingTime = duration - currentPos;

            // Chuyển đổi sang định dạng mm:ss
            String currentPositionStr = millisecondsToMMSS(currentPos);
            String remainingTimeStr = millisecondsToMMSS(remainingTime);

            // Cập nhật giao diện người dùng
            // Ví dụ: cập nhật TextView
            txt_current_time.setText(currentPositionStr);
            txt_remaining_time.setText(remainingTimeStr);

            txt_song_name.setText(danhSachBaiHat.get(sttbai-1).getTenBaiHat());
            txt_artist_name.setText(danhSachBaiHat.get(sttbai-1).getCaSi());
        }
    }
    public void pauseAudio() {
        if (mp != null && mp.isPlaying()) {
            mp.pause();
            currentPosition = mp.getCurrentPosition();
            btn_play.setImageResource(R.drawable.play4);
        }
        else {
            if(currentPosition == 0) {
                phatbai(1);
                sttbai=1;
            }
            else {
                mp.seekTo(currentPosition);
                mp.start();
                btn_play.setImageResource(R.drawable.baseline_pause_24);
            }
        }

    }
    private String millisecondsToMMSS(int milliseconds) {
        int seconds = (milliseconds / 1000) % 60;
        int minutes = (milliseconds / (1000 * 60)) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
    public void  nextbai1(){
        if(checkrepeat){
            mp.start();
        }
        else {
            if(checkshuffle) {
                int randomNumber;
                Random random = new Random();
                do {
                    randomNumber = random.nextInt(4 - 1 + 1) + 1;
                } while (randomNumber == sttbai);
                phatbai(randomNumber);
                sttbai = randomNumber;
            }
            else {
                if(sttbai<4){
                    phatbai(sttbai+1);
                    sttbai+=1;
                }
                else {
                    phatbai(1);
                    sttbai=1;
                }
            }
        }
    }
    public void  backbai1(){
        if(checkrepeat){
            mp.start();
        }
        else {
            if(checkshuffle) {
                int randomNumber;
                Random random = new Random();
                do {
                    randomNumber = random.nextInt(4 - 1 + 1) + 1;
                } while (randomNumber == sttbai);
                phatbai(randomNumber);
                sttbai = randomNumber;
            }
            else {
                if(sttbai<2){
                    phatbai(4);
                    sttbai=4;
                }
                else {
                    phatbai(sttbai-1);
                    sttbai-=1;
                }
            }
        }
    }
}