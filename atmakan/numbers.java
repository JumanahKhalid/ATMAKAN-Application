package com.example.atmakan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class numbers extends AppCompatActivity {
    ImageButton backimage;
    MediaPlayer voice1, voice2, voice3, voice4, voice5, voice6, voice7, voice8, voice9, voice10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        backimage = (ImageButton) findViewById(R.id.imageButton39);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(numbers.this, Interactive.class);
                startActivity(intent);
            }
        });
        voice1 = MediaPlayer.create(numbers.this, R.raw.one);
        voice2 = MediaPlayer.create(numbers.this, R.raw.two);
        voice3 = MediaPlayer.create(numbers.this, R.raw.three);
        voice4 = MediaPlayer.create(numbers.this, R.raw.four);
        voice5 = MediaPlayer.create(numbers.this, R.raw.five);
        voice6 = MediaPlayer.create(numbers.this, R.raw.six);
        voice7 = MediaPlayer.create(numbers.this, R.raw.seven);
        voice8 = MediaPlayer.create(numbers.this, R.raw.eight);
        voice9 = MediaPlayer.create(numbers.this, R.raw.nine);
        voice10 = MediaPlayer.create(numbers.this, R.raw.ten);
    }

    public void oneIT(View v) {

        voice1.start();
    }

    public void twoIT(View v) {

        voice2.start();
    }

    public void threeIT(View v) {
        voice3.start();

    }

    public void fourIT(View v) {
        voice4.start();
    }

    public void fiveIT(View v) {

        voice5.start();
    }

    public void sixIT(View v) {

        voice6.start();
    }

    public void sevenIT(View v) {
        voice7.start();

    }

    public void eightIT(View v) {
        voice8.start();
    }

    public void nineIT(View v) {
        voice9.start();

    }

    public void tenIT(View v) {
        voice10.start();
    }
}