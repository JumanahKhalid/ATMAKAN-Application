package com.example.atmakan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.atmakan.chat.ChatAdapter;
import com.example.atmakan.chat.ChatItem;
import com.example.atmakan.chat.ChatParent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class NotesActivity extends AppCompatActivity {

    Button submit, cancel;
    EditText et_feedback;
    String userID;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        userID = getIntent().getStringExtra("userID");

        submit = findViewById(R.id.submit);
        cancel = findViewById(R.id.cancel);
        et_feedback = findViewById(R.id.et_feedback);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feedback = et_feedback.getText().toString().trim();
                if (!feedback.isEmpty() && feedback != null) {
                    sendReport(feedback);
                    Intent intent = new Intent(NotesActivity.this, TherapistHomepage.class);
                    startActivity(intent);
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotesActivity.super.onBackPressed();
                finish();
            }
        });

    }

    private void sendReport(String feedback) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Report");

        Date currentTime = Calendar.getInstance().getTime();

        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("feedback", feedback + "");
        hashMap.put("therapistID", FirebaseAuth.getInstance().getCurrentUser().getUid() + "");
        hashMap.put("userID", userID + "");
        hashMap.put("date", currentTime.toString() + "");

        reference.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                deleteMessages();
            }
        });

    }


    private void deleteMessages() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chat");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ChatItem item = dataSnapshot.getValue(ChatItem.class);
                    if ((item.getSenderID().equals(FirebaseAuth.getInstance().getCurrentUser().getUid() + "")
                            && item.getReceiveId().equals(userID + "")) ||
                            (item.getSenderID().equals(userID + "")
                                    && item.getReceiveId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid() + ""))) {

                        FirebaseDatabase.getInstance().getReference("Chat")
                                .child(dataSnapshot.getKey())
                                .removeValue();

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}