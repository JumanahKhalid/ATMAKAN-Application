package com.example.atmakan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Goodjob extends AppCompatActivity {
    ImageButton backbutton;
    MediaPlayer clapvoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodjob);
        clapvoice = MediaPlayer.create(Goodjob.this, R.raw.claps);
        clapvoice.start();
        backbutton = findViewById(R.id.imageButton41);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(Goodjob.this, lerningmode.class);
                startActivity(intent);
                clapvoice.pause();
            }
        });
    }
}