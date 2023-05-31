package com.example.atmakan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.atmakan.admin.StatisticalReport;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class lerningmode extends AppCompatActivity {
    ImageButton Emotions, interactive, entertainment;
    ImageView home, game;
    MediaPlayer hellovoice;
    private int selecttabnumber = 1;

    int emotionsNumber, interactiveNumber, entertainmentNumber;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Statistical").child("Report");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lerningmode);
        home = findViewById(R.id.homebage);
        game = findViewById(R.id.lerningmode);

        getStatisticalReport();

        hellovoice = MediaPlayer.create(lerningmode.this, R.raw.hello);
        hellovoice.start();
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
        Emotions = findViewById(R.id.imageButton3);
        Emotions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, Object> hashMap = new HashMap<>();

                hashMap.put("emotions", emotionsNumber + 1);
                hashMap.put("interactive", interactiveNumber);
                hashMap.put("entertainment", entertainmentNumber);

                databaseReference.setValue(hashMap);

                Intent intent = new
                        Intent(lerningmode.this, Emotions.class);
                hellovoice.pause();
                startActivity(intent);
            }
        });
        interactive = findViewById(R.id.imageButton);
        interactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, Object> hashMap = new HashMap<>();

                hashMap.put("emotions", emotionsNumber);
                hashMap.put("interactive", interactiveNumber + 1);
                hashMap.put("entertainment", entertainmentNumber);

                databaseReference.setValue(hashMap);

                Intent intent = new
                        Intent(lerningmode.this, Interactive.class);
                hellovoice.pause();
                startActivity(intent);
            }
        });
        entertainment = findViewById(R.id.imageButton2);
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, Object> hashMap = new HashMap<>();

                hashMap.put("emotions", emotionsNumber);
                hashMap.put("interactive", interactiveNumber);
                hashMap.put("entertainment", entertainmentNumber + 1);

                databaseReference.setValue(hashMap);


                Intent intent = new
                        Intent(lerningmode.this, Entertainment.class);
                hellovoice.pause();
                startActivity(intent);
            }
        });
    }

    private void getStatisticalReport() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    StatisticalReport item = snapshot.getValue(StatisticalReport.class);

                    emotionsNumber = item.getEmotions();
                    interactiveNumber = item.getInteractive();
                    entertainmentNumber = item.getEntertainment();


                } else {

                    HashMap<String, Object> hashMap = new HashMap<>();

                    hashMap.put("emotions", 0);
                    hashMap.put("interactive", 0);
                    hashMap.put("entertainment", 0);

                    databaseReference.setValue(hashMap);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void selecttab(int tabnumber) {
        ImageView selectedimageview;

        if (tabnumber == 1) {
            selectedimageview = game;
        } else {
            selectedimageview = home;
            Intent intent = new
                    Intent(lerningmode.this, Mathematics.class);
            hellovoice.pause();
            startActivity(intent);
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