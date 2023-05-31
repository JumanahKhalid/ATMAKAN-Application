package com.example.atmakan.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.atmakan.Chat;
import com.example.atmakan.HistoricalRecordActivity;
import com.example.atmakan.MemoryData;
import com.example.atmakan.NotesActivity;
import com.example.atmakan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ChatParent extends AppCompatActivity {
    ImageView sendbtn;
    TextView nameTV;
    final List<ChatItem> chatItemList = new ArrayList<>();
    EditText messageedittext;
    String getUserEmail = "";
    private RecyclerView chattingRecyclerview;
    ChatAdapter chatAdapter;
    String receiveId, userType;
    Button cancelbutton;
    AlertDialog.Builder builder;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_parent);
        builder = new AlertDialog.Builder(this);

        cancelbutton = findViewById(R.id.cancelbutton1);
        nameTV = findViewById(R.id.name);
        messageedittext = findViewById(R.id.messageedittxt);
        sendbtn = findViewById(R.id.sendbtn);
        chattingRecyclerview = findViewById(R.id.chattingRecyclerview);

        String getfname = getIntent().getStringExtra("FirstName");
        String getlname = getIntent().getStringExtra("LastName");

        receiveId = getIntent().getStringExtra("receiveId");
        userType = getIntent().getStringExtra("userType");

        getUserEmail = MemoryData.getData(ChatParent.this);

        nameTV.setText(String.format("%s %s", getfname, getlname));

        chattingRecyclerview.setHasFixedSize(true);
        chattingRecyclerview.setLayoutManager(new LinearLayoutManager(ChatParent.this));

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getTxtMessage = messageedittext.getText().toString();
                if (!getTxtMessage.isEmpty() && getTxtMessage != null) {
                    sendMessage(getTxtMessage);
                }
            }
        });
        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userType.equals("user")) {
                    builder.setTitle("    ")
                            .setMessage("هل انت متأكد من الخروج من الجلسة ؟")
                            .setIcon(R.drawable.logo).setCancelable(false)
                            .setNegativeButton("لا", null)
                            .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new
                                            Intent(ChatParent.this, Chat.class);
                                    startActivity(intent);
                                }
                            }).create().show();
                } else {
                    Intent intent = new Intent(ChatParent.this, NotesActivity.class);
                    intent.putExtra("userID", receiveId);
                    startActivity(intent);
                }
            }
        });

        getMessage();
    }

    private void getMessage() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chat");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatItemList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ChatItem item = dataSnapshot.getValue(ChatItem.class);
                    if ((item.getSenderID().equals(FirebaseAuth.getInstance().getCurrentUser().getUid() + "")
                            && item.getReceiveId().equals(receiveId + "")) ||
                            (item.getSenderID().equals(receiveId + "")
                                    && item.getReceiveId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid() + ""))) {
                        chatItemList.add(item);
                    }
                }

                chatAdapter = new ChatAdapter(chatItemList, ChatParent.this, FirebaseAuth.getInstance().getCurrentUser().getUid() + "");
                chattingRecyclerview.setAdapter(chatAdapter);
                chatAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void sendMessage(String getTxtMessage) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chat");

        Date currentTime = Calendar.getInstance().getTime();

        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("message", getTxtMessage + "");
        hashMap.put("receiveId", receiveId + "");
        hashMap.put("senderID", FirebaseAuth.getInstance().getCurrentUser().getUid() + "");
        hashMap.put("date", currentTime.toString() + "");

        reference.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                messageedittext.setText("");
            }
        });

    }
    public void profileUserOnClick(View view) {
        if (!userType.equals("user")) {
            Intent intent = new Intent(ChatParent.this, HistoricalRecordActivity.class);
            intent.putExtra("userType", "therapist");
            intent.putExtra("userid", receiveId);
            startActivity(intent);
        }
    }
}