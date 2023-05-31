package com.example.atmakan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class TherapistHomepage extends AppCompatActivity {

    LinearLayout homeLayout, settingLayout, profileLayout;
    ImageButton contactbutton, donatebutton;
    private int selecttabmenu = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_homepage);
        homeLayout = findViewById(R.id.homeLayout);
        settingLayout = findViewById(R.id.settingLayout);
        profileLayout = findViewById(R.id.profileLayout);


        contactbutton = (ImageButton) findViewById(R.id.imageButton6);
        contactbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TherapistHomepage.this, TherapistContact.class);
                startActivity(intent);
            }
        });

        donatebutton = findViewById(R.id.imageButton7);
        donatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TherapistHomepage.this, TherapistDonate.class);
                startActivity(intent);
            }
        });

        settingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 2) {
                    Intent intent = new Intent(TherapistHomepage.this, TherapistSetting.class);
                    startActivity(intent);
                    selecttabmenu = 2;
                }
            }
        });
        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 3) {
                    Intent intent = new Intent(TherapistHomepage.this, TherapistProfilePage.class);
                    startActivity(intent);
                    selecttabmenu = 3;
                }
            }
        });
    }
}