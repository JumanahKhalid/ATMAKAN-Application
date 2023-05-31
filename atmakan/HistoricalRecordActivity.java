package com.example.atmakan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoricalRecordActivity extends AppCompatActivity {
    FirebaseUser user;
    DatabaseReference databaseReference, databaseReferencelevel;
    FirebaseDatabase database;
    String userid;
    TextView fullname, Age, Level;
    ImageView backimage;

    RecyclerView histiricalRecordRecyclerView;
    List<User> userList = new ArrayList<>();
    List<Report> reportList = new ArrayList<>();

    String userType = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_record);
        database = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        databaseReference = database.getReference("users");
        databaseReferencelevel = database.getReference("Score");

        userType = getIntent().getStringExtra("userType");

        if(userType!=null)
        {

        } else {
          userid = user.getUid();
        }

        if (userType.equals("therapist")) {
            userid = getIntent().getStringExtra("userid");
        } else {
            userid = user.getUid();
        }

        fullname = (TextView) findViewById(R.id.editTextTextPersonName);
        Age = findViewById(R.id.editTextAge);
        Level = findViewById(R.id.editTextlevel);

        backimage = findViewById(R.id.imageView21);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HistoricalRecordActivity.super.onBackPressed();
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String fnameUser = snapshot.child(userid).child("FirstName").getValue(String.class);
                String lnameUser = snapshot.child(userid).child("LastName").getValue(String.class);
                String age = snapshot.child(userid).child("Age").getValue(String.class);

                fullname.setText(fnameUser + " " + lnameUser);
                Age.setText(age);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReferencelevel.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String level = snapshot.child(userid).child("level").getValue(String.class);

                Level.setText(level);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        histiricalRecordRecyclerView = findViewById(R.id.histiricalRecordRecyclerView);
        histiricalRecordRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        histiricalRecordRecyclerView.setHasFixedSize(true);

        getReport();

    }


    private void getAllTherapist() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Thirapest");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User therapist = dataSnapshot.getValue(User.class);
                    userList.add(therapist);
                }

                HistiricalRecordAdapter adapter = new HistiricalRecordAdapter(
                        HistoricalRecordActivity.this,
                        reportList, userList);
                histiricalRecordRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getReport() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Report");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reportList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Report report = dataSnapshot.getValue(Report.class);
                    if (report.getUserID().equals(userid + "")) {
                        reportList.add(report);
                    }
                }

                getAllTherapist();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}