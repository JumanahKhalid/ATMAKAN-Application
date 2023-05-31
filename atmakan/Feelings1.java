package com.example.atmakan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class Feelings1 extends AppCompatActivity {
    MediaPlayer happyvoice, sadvoice, angryvoice, tiredvoice, feeling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings1);
        feeling = MediaPlayer.create(Feelings1.this, R.raw.feelingsv);
        feeling.start();
        happyvoice = MediaPlayer.create(Feelings1.this, R.raw.h);
        sadvoice = MediaPlayer.create(Feelings1.this, R.raw.s);
        angryvoice = MediaPlayer.create(Feelings1.this, R.raw.a);
        tiredvoice = MediaPlayer.create(Feelings1.this, R.raw.t);
    }

    public void back(View view) {
        feeling.pause();
        startActivity(new Intent(Feelings1.this, Emotions.class));
    }

    public void next(View view) {
        feeling.pause();
        startActivity(new Intent(Feelings1.this, Feelings2.class));
    }

    public void happyIT(View v) {
        feeling.pause();
        happyvoice.start();
    }

    public void sadIT(View v) {
        feeling.pause();
        sadvoice.start();
    }

    public void angryIT(View v) {
        feeling.pause();
        angryvoice.start();

    }

    public void tiredIT(View v) {
        feeling.pause();
        tiredvoice.start();
    }
}