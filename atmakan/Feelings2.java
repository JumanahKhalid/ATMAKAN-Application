package com.example.atmakan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Feelings2 extends AppCompatActivity {
    MediaPlayer boredvoice, shyvoice, afraidvoice, exitedvoice;
    ImageView home;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings2);
        boredvoice = MediaPlayer.create(Feelings2.this, R.raw.b);
        shyvoice = MediaPlayer.create(Feelings2.this, R.raw.sh);
        afraidvoice = MediaPlayer.create(Feelings2.this, R.raw.af);
        exitedvoice = MediaPlayer.create(Feelings2.this, R.raw.e);
        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Feelings2.this, Emotions.class);
                startActivity(intent);
            }
        });
    }

    public void back(View view) {
        startActivity(new Intent(Feelings2.this, Feelings1.class));

    }

    public void boredIT(View v) {

        boredvoice.start();

    }

    public void shyIT(View v) {

        shyvoice.start();
    }

    public void afraidIT(View v) {
        afraidvoice.start();

    }

    public void exitedIT(View v) {
        exitedvoice.start();
    }
}