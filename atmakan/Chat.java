package com.example.atmakan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.atmakan.messages.Messages2Adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity {

    List<User> userList = new ArrayList<>();
    RecyclerView messagRecycleview;
    Messages2Adapter messagesAdapter;
    LinearLayout homeLayout, contactLayout, settingLayout, profileLayout;
    ImageView backimage;
    private int selecttabmenu = 2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        homeLayout = findViewById(R.id.homeLayout);
        contactLayout = findViewById(R.id.contactLayout);
        settingLayout = findViewById(R.id.settingLayout);
        profileLayout = findViewById(R.id.profileLayout);

        backimage = findViewById(R.id.imageView21);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(Chat.this, HomePage.class);
                startActivity(intent);
            }
        });
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 1) {
                    Intent intent = new Intent(Chat.this, HomePage.class);
                    startActivity(intent);
                    selecttabmenu = 1;
                }
            }
        });
        settingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 3) {
                    Intent intent = new Intent(Chat.this, SettingPage.class);
                    startActivity(intent);
                    selecttabmenu = 3;
                }
            }
        });
        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 4) {
                    Intent intent = new Intent(Chat.this, ProfilePage.class);
                    startActivity(intent);
                    selecttabmenu = 4;
                }
            }
        });

        messagRecycleview = findViewById(R.id.messagesRecyclerview);

        messagRecycleview.setLayoutManager(new LinearLayoutManager(this));
        messagRecycleview.setHasFixedSize(true);

        getAllTherapist();
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

                messagesAdapter = new Messages2Adapter(userList, Chat.this, "user");
                messagRecycleview.setAdapter(messagesAdapter);
                messagesAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}