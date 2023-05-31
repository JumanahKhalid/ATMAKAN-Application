package com.example.atmakan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.atmakan.chat.ChatItem;
import com.example.atmakan.messages.Messages2Adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TherapistContact extends AppCompatActivity {
    ImageView imageButton2;
    LinearLayout homeLayout, settingLayout, profileLayout;
    RecyclerView userListRecyclerView;
    List<User> userList = new ArrayList<>();
    List<User> users = new ArrayList<>();
    private int selecttabmenu = 0;
    Messages2Adapter messagesAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_contact);
        homeLayout = findViewById(R.id.homeLayout);
        settingLayout = findViewById(R.id.settingLayout);
        profileLayout = findViewById(R.id.profileLayout);
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 1) {
                    Intent intent = new Intent(TherapistContact.this, TherapistHomepage.class);
                    startActivity(intent);
                    selecttabmenu = 1;
                }
            }
        });
        settingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 2) {
                    Intent intent = new Intent(TherapistContact.this, TherapistSetting.class);
                    startActivity(intent);
                    selecttabmenu = 2;
                }
            }
        });
        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 3) {
                    Intent intent = new Intent(TherapistContact.this, TherapistProfilePage.class);
                    startActivity(intent);
                    selecttabmenu = 3;
                }
            }
        });
        userListRecyclerView = findViewById(R.id.userListRecyclerView);

        userListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        userListRecyclerView.setHasFixedSize(true);

        imageButton2 = findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(TherapistContact.this, TherapistHomepage.class);
                startActivity(intent);
            }
        });

        getAllUser();
    }


    private void getAllUser() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = dataSnapshot.getValue(User.class);
                    Log.d("testttt", "test 3");
                    userList.add(user);
                }

                getUserChating();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getUserChating() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chat");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ChatItem item = dataSnapshot.getValue(ChatItem.class);
                    Log.d("testttt", item.getReceiveId() + "");
                    Log.d("testttt", item.getSenderID() + "");
                    Log.d("testttt", FirebaseAuth.getInstance().getCurrentUser().getUid() + " UserID");
                    if (item.getSenderID().equals(FirebaseAuth.getInstance().getCurrentUser().getUid() + "")) {
                        for (User user : userList) {
                            if (user.getID().equals(item.getReceiveId())) {
                                if (!users.contains(user)) {
                                    Log.d("testttt", "test 1");
                                    users.add(user);
                                }
                            }
                        }
                    } else if (item.getReceiveId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid() + "")) {
                        for (User user : userList) {
                            if (user.getID().equals(item.getSenderID())) {
                                if (!users.contains(user)) {
                                    Log.d("testttt", "test 2");
                                    users.add(user);
                                }
                            }
                        }
                    }
                }

                messagesAdapter = new Messages2Adapter(users, TherapistContact.this, "therapist");
                userListRecyclerView.setAdapter(messagesAdapter);
                messagesAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}