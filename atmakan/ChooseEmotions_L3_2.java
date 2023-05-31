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

public class ChooseEmotions_L3_2 extends AppCompatActivity {
    ImageButton backimage, sadimage, happyimage;
    MediaPlayer mp, playvoice;
    ImageView home;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_emotions_l32);
        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(ChooseEmotions_L3_2.this, Emotions.class);
                startActivity(intent);
            }
        });
        backimage = findViewById(R.id.imageButton4);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(ChooseEmotions_L3_2.this, ChooseEmotions_L3_3.class);
                startActivity(intent);
            }
        });
        playvoice = MediaPlayer.create(ChooseEmotions_L3_2.this, R.raw.sad);
        happyimage = findViewById(R.id.imageButton37);
        happyimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(ChooseEmotions_L3_2.this, R.raw.wrong);
                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
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
        sadimage = findViewById(R.id.imageButton36);
        sadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(ChooseEmotions_L3_2.this, R.raw.correct1);
                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mp.start();
                    }
                });
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mp.release();
                        Intent intent = new
                                Intent(ChooseEmotions_L3_2.this, ChooseEmotions_L3_3.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    public void playIT(View v) {
        playvoice.start();
    }
}
