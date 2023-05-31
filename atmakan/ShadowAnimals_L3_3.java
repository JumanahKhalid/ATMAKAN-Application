package com.example.atmakan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ShadowAnimals_L3_3 extends AppCompatActivity {
    ImageButton backimage, wolfimage, dolfenimage;
    ImageView home;
    MediaPlayer mp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadow_animals_l33);
        home = findViewById(R.id.imageView105);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(ShadowAnimals_L3_3.this, Entertainment.class);
                startActivity(intent);
            }
        });
        backimage = (ImageButton) findViewById(R.id.imageButton4);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(ShadowAnimals_L3_3.this, ShadowAnimals_L3_2.class);
                startActivity(intent);
            }
        });
        wolfimage = findViewById(R.id.imageButton47);
        wolfimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(ShadowAnimals_L3_3.this, R.raw.correct1);
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
                                Intent(ShadowAnimals_L3_3.this, ShadowAnimals_L3_4.class);
                        startActivity(intent);
                    }
                });
            }
        });
        dolfenimage = findViewById(R.id.imageButton44);
        dolfenimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(ShadowAnimals_L3_3.this, R.raw.wrong);
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
    }
}