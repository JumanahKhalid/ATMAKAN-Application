package com.example.atmakan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ProfilePage extends AppCompatActivity {
    FirebaseUser user;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    String userid;
    TextView fullname, email, date, id;
    LinearLayout homeLayout, contactLayout, settingLayout, profileLayout;
    ImageView backimage;
    Button historical_record_button;
    private int selecttabmenu = 4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        database = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = database.getReference("users");
        userid = user.getUid();
        fullname = (TextView) findViewById(R.id.editTextTextPersonName4);
        email = (TextView) findViewById(R.id.editTextTextEmailAddress3);
        date = findViewById(R.id.editTextTextPersonName5);
        id = findViewById(R.id.editTextNumber2);
        historical_record_button = findViewById(R.id.signup_button);

        historical_record_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfilePage.this, HistoricalRecordActivity.class);
                intent.putExtra("userType","user");
                startActivity(intent);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String fnameUser = snapshot.child(userid).child("FirstName").getValue(String.class);
                String lnameUser = snapshot.child(userid).child("LastName").getValue(String.class);
                String emailuser = snapshot.child(userid).child("Email").getValue(String.class);
                String iduser = snapshot.child(userid).child("identity").getValue(String.class);
                String ageuser = snapshot.child(userid).child("Age").getValue(String.class);


                fullname.setText(fnameUser + " " + lnameUser);
                email.setText(emailuser);
                id.setText(iduser);
                date.setText(ageuser);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        homeLayout = findViewById(R.id.homeLayout);
        contactLayout = findViewById(R.id.contactLayout);
        settingLayout = findViewById(R.id.settingLayout);
        profileLayout = findViewById(R.id.profileLayout);

        backimage = findViewById(R.id.imageView21);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(ProfilePage.this, HomePage.class);
                startActivity(intent);
            }
        });
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 1) {
                    Intent intent = new Intent(ProfilePage.this, HomePage.class);
                    startActivity(intent);
                    selecttabmenu = 1;
                }
            }
        });
        contactLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 2) {
                    Intent intent = new Intent(ProfilePage.this, Chat.class);
                    startActivity(intent);
                    selecttabmenu = 2;
                }
            }
        });
        settingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 3) {
                    Intent intent = new Intent(ProfilePage.this, SettingPage.class);
                    startActivity(intent);
                    selecttabmenu = 3;
                }
            }
        });
    }
}