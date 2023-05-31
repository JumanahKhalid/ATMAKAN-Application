package com.example.atmakan;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Interactive extends AppCompatActivity {
    ImageButton Letters, Numbers, backimage;
    MediaPlayer interactive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactive);
        interactive = MediaPlayer.create(Interactive.this, R.raw.interactive);
        interactive.start();
        backimage = findViewById(R.id.imageButton42);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(Interactive.this, lerningmode.class);
                interactive.pause();
                startActivity(intent);
            }
        });
        Letters = findViewById(R.id.imageButton5);
        Letters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(Interactive.this, letters.class);
                interactive.pause();
                startActivity(intent);
            }
        });
        Numbers = findViewById(R.id.imageButton4);
        Numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(Interactive.this, numbers.class);
                interactive.pause();
                startActivity(intent);
            }
        });
    }
}