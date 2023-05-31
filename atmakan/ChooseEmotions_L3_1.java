package com.example.atmakan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ChooseEmotions_L3_1 extends AppCompatActivity {
    ImageButton angryimage, scaryimage;
    ImageView home;
    MediaPlayer mp, playvoice, chooseemaotions;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_emotions_l31);
        chooseemaotions = MediaPlayer.create(ChooseEmotions_L3_1.this, R.raw.chooseemotionsv);
        chooseemaotions.start();
        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(ChooseEmotions_L3_1.this, Emotions.class);
                chooseemaotions.pause();
                startActivity(intent);
            }
        });
        angryimage = findViewById(R.id.imageButton37);
        angryimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(ChooseEmotions_L3_1.this, R.raw.correct1);
                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        chooseemaotions.pause();
                        mp.start();
                    }
                });
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mp.release();
                        Intent intent = new
                                Intent(ChooseEmotions_L3_1.this, ChooseEmotions_L3_2.class);
                        startActivity(intent);
                    }
                });
            }
        });
        scaryimage = findViewById(R.id.imageButton36);
        scaryimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(ChooseEmotions_L3_1.this, R.raw.wrong);
                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        chooseemaotions.pause();
                        mp.start();
                    }
                });
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mp.release();
                    }
                });
            }
        });
    }

    public void playIT(View v) {
        chooseemaotions.pause();
        playvoice = MediaPlayer.create(ChooseEmotions_L3_1.this, R.raw.angry);
        playvoice.start();
    }
}
