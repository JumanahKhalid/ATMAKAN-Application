package com.example.atmakan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ShadowAnimals_L2_1 extends AppCompatActivity {
    ImageButton tiggerimage, sharkimage, giraffeimage;
    ImageView home;
    MediaPlayer mp, Animals;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadow_animals_l21);
        Animals = MediaPlayer.create(ShadowAnimals_L2_1.this, R.raw.shadowanimalsv);
        Animals.start();
        home = findViewById(R.id.imageView105);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(ShadowAnimals_L2_1.this, Entertainment.class);
                Animals.pause();
                startActivity(intent);
            }
        });
        tiggerimage = findViewById(R.id.imageButton43);
        tiggerimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(ShadowAnimals_L2_1.this, R.raw.correct1);
                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        Animals.pause();
                        mp.start();
                    }
                });
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mp.release();
                        Intent intent = new
                                Intent(ShadowAnimals_L2_1.this, ShadowAnimals_L2_2.class);
                        startActivity(intent);
                    }
                });
            }
        });
        sharkimage = findViewById(R.id.imageButton47);
        sharkimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(ShadowAnimals_L2_1.this, R.raw.wrong);
                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        Animals.pause();
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
        giraffeimage = findViewById(R.id.imageButton44);
        giraffeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(ShadowAnimals_L2_1.this, R.raw.wrong);
                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        Animals.pause();
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