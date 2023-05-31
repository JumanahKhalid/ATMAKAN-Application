package com.example.atmakan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HomePage extends AppCompatActivity {
    ImageView home, game;
    LinearLayout homeLayout, contactLayout, settingLayout, profileLayout;
    ImageButton contactbutton, profilebutton, settingbutton, donatebutton;
    private int selecttabnumber = 1;
    private int selecttabmenu = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        homeLayout = findViewById(R.id.homeLayout);
        contactLayout = findViewById(R.id.contactLayout);
        settingLayout = findViewById(R.id.settingLayout);
        profileLayout = findViewById(R.id.profileLayout);

        home = findViewById(R.id.homebage);
        game = findViewById(R.id.lerningmode);

        contactbutton = findViewById(R.id.imageButton6);
        contactbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, Chat.class);
                startActivity(intent);
            }
        });
        profilebutton = findViewById(R.id.imageButton3);
        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, ProfilePage.class);
                startActivity(intent);
            }
        });
        donatebutton = findViewById(R.id.imageButton7);
        donatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, donate.class);
                startActivity(intent);
            }
        });
        settingbutton = findViewById(R.id.imageButton2);
        settingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, SettingPage.class);
                startActivity(intent);
            }
        });
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecttab(1);

            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecttab(2);
            }
        });

        contactLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 2) {
                    Intent intent = new Intent(HomePage.this, Chat.class);
                    startActivity(intent);
                    selecttabmenu = 2;
                }
            }
        });
        settingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 3) {
                    Intent intent = new Intent(HomePage.this, SettingPage.class);
                    startActivity(intent);
                    selecttabmenu = 3;
                }
            }
        });
        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 4) {
                    Intent intent = new Intent(HomePage.this, ProfilePage.class);
                    startActivity(intent);
                    selecttabmenu = 4;
                }
            }
        });
    }

    private void selecttab(int tabnumber) {
        ImageView selectedimageview;

        if (tabnumber == 1) {
            selectedimageview = game;
            Intent intent = new
                    Intent(HomePage.this, lerningmode.class);
            startActivity(intent);
        } else {
            selectedimageview = home;
        }

        float slide = (tabnumber - selecttabnumber) * selectedimageview.getWidth();
        TranslateAnimation translateAnimation = new TranslateAnimation(0, slide, 0, 0);
        translateAnimation.setDuration(100);
        if (selecttabnumber == 1) {

            game.startAnimation(translateAnimation);
        } else {

            home.startAnimation(translateAnimation);

        }
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                selectedimageview.setBackgroundResource(R.drawable.round_back_white_100);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}