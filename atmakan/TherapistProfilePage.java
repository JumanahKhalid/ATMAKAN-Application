package com.example.atmakan;

import androidx.appcompat.app.AppCompatActivity;
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
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import android.os.Bundle;

public class TherapistProfilePage extends AppCompatActivity {
    FirebaseUser user;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    String userid;
    TextView fullname, email, date, id;
    LinearLayout homeLayout, settingLayout, profileLayout;
    private int selecttabmenu = 3;

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_profile_page);
        database = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = database.getReference("Thirapest");
        userid = user.getUid();
        fullname = (TextView) findViewById(R.id.editTextTextPersonName4);
        email = (TextView) findViewById(R.id.editTextTextEmailAddress3);
        date = findViewById(R.id.editTextTextPersonName5);
        id = findViewById(R.id.editTextNumber2);

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
        settingLayout = findViewById(R.id.settingLayout);
        profileLayout = findViewById(R.id.profileLayout);

        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 1) {
                    Intent intent = new Intent(TherapistProfilePage.this, TherapistHomepage.class);
                    startActivity(intent);
                    selecttabmenu = 1;
                }
            }
        });
        settingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 2) {
                    Intent intent = new Intent(TherapistProfilePage.this, TherapistSetting.class);
                    startActivity(intent);
                    selecttabmenu = 2;
                }
            }
        });

        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 3) {
                    Intent intent = new Intent(TherapistProfilePage.this, ProfilePage.class);
                    startActivity(intent);
                    selecttabmenu = 3;
                }
            }
        });
    }
}