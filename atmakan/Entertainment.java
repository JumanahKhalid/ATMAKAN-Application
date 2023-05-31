package com.example.atmakan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Entertainment extends AppCompatActivity {
    ImageButton backimage, shadowanimals, matching;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    MediaPlayer entertainment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);
        entertainment = MediaPlayer.create(Entertainment.this, R.raw.entertainment);
        entertainment.start();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        backimage = findViewById(R.id.imageButton42);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(Entertainment.this, lerningmode.class);
                entertainment.pause();
                startActivity(intent);
            }
        });
        shadowanimals = findViewById(R.id.imageButton4);
        shadowanimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("Score").child(firebaseUser.getUid()).child("result").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                        if (Integer.parseInt(datasnapshot.getValue().toString()) < 30) {
                            Intent intent = new
                                    Intent(Entertainment.this, ShadowAnimals_L1_1.class);
                            entertainment.pause();
                            startActivity(intent);
                        } else if (Integer.parseInt(datasnapshot.getValue().toString()) < 60) {
                            Intent intent = new
                                    Intent(Entertainment.this, ShadowAnimals_L2_1.class);
                            entertainment.pause();
                            startActivity(intent);
                        } else {
                            Intent intent = new
                                    Intent(Entertainment.this, ShadowAnimals_L3_1.class);
                            entertainment.pause();
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        matching = findViewById(R.id.imageButton5);
        matching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("Score").child(firebaseUser.getUid()).child("result").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                        if (Integer.parseInt(datasnapshot.getValue().toString()) < 30) {
                            Intent intent = new
                                    Intent(Entertainment.this, Matching_L1.class);
                            entertainment.pause();
                            startActivity(intent);
                        } else if (Integer.parseInt(datasnapshot.getValue().toString()) < 60) {
                            Intent intent = new
                                    Intent(Entertainment.this, Matching_L2.class);
                            entertainment.pause();
                            startActivity(intent);
                        } else {
                            Intent intent = new
                                    Intent(Entertainment.this, Matching_L3.class);
                            entertainment.pause();
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}