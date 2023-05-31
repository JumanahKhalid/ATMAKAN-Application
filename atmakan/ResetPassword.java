package com.example.atmakan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
    ImageView back;
    EditText txtemail;
    Button button;
    String email;
    FirebaseAuth auth;
    LinearLayout homeLayout, contactLayout, settingLayout, profileLayout;
    private int selecttabmenu = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        auth = FirebaseAuth.getInstance();

        homeLayout = findViewById(R.id.homeLayout);
        contactLayout = findViewById(R.id.contactLayout);
        settingLayout = findViewById(R.id.settingLayout);
        profileLayout = findViewById(R.id.profileLayout);

        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 1) {
                    Intent intent = new Intent(ResetPassword.this, HomePage.class);
                    startActivity(intent);
                    selecttabmenu = 1;
                }
            }
        });
        contactLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 2) {
                    Intent intent = new Intent(ResetPassword.this, Chat.class);
                    startActivity(intent);
                    selecttabmenu = 2;
                }
            }
        });
        settingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 3) {
                    Intent intent = new Intent(ResetPassword.this, SettingPage.class);
                    startActivity(intent);
                    selecttabmenu = 3;
                }
            }
        });
        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 4) {
                    Intent intent = new Intent(ResetPassword.this, ProfilePage.class);
                    startActivity(intent);
                    selecttabmenu = 4;
                }
            }
        });

        back = findViewById(R.id.backarrow2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(ResetPassword.this, SettingPage.class);
                startActivity(intent);
            }
        });
        txtemail = findViewById(R.id.editTextTextPersonName6);
        button = findViewById(R.id.button9);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = txtemail.getText().toString();
                if (email.isEmpty()) {
                    txtemail.setError("الحقل مطلوب ");
                } else {
                    auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ResetPassword.this, "الرجاء التحقق من البريد الالكتروني ", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ResetPassword.this, SettingPage.class));
                                finish();
                            } else {
                                Toast.makeText(ResetPassword.this, "البريد الالكتروني غير موجود ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}