package com.example.atmakan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Emotions extends AppCompatActivity {
    ImageButton chooseemotions, feelings, backimage;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    MediaPlayer emotions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotions);
        emotions = MediaPlayer.create(Emotions.this, R.raw.emotions);
        emotions.start();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        backimage = findViewById(R.id.imageButton42);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(Emotions.this, lerningmode.class);
                emotions.pause();
                startActivity(intent);
            }
        });
        chooseemotions = findViewById(R.id.imageButton5);
        chooseemotions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("Score").child(firebaseUser.getUid()).child("result").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                        if (Integer.parseInt(datasnapshot.getValue().toString()) < 30) {
                            Intent intent = new
                                    Intent(Emotions.this, ChooseEmotions_L1_1.class);
                            emotions.pause();
                            startActivity(intent);
                        } else if (Integer.parseInt(datasnapshot.getValue().toString()) < 60) {
                            Intent intent = new
                                    Intent(Emotions.this, ChooseEmotions_L2_1.class);
                            emotions.pause();
                            startActivity(intent);
                        } else {
                            Intent intent = new
                                    Intent(Emotions.this, ChooseEmotions_L3_1.class);
                            emotions.pause();
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        feelings = findViewById(R.id.imageButton4);
        feelings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(Emotions.this, Feelings1.class);
                emotions.pause();
                startActivity(intent);
            }
        });
    }
}
