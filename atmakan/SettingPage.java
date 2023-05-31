package com.example.atmakan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SettingPage extends AppCompatActivity {
    LinearLayout homeLayout, contactLayout, settingLayout, profileLayout;
    ImageView backimage;
    ImageButton supportimage, resetpassword, control;
    Button logout;
    AlertDialog.Builder builder;
    private FirebaseAuth mauth;
    private int selecttabmenu = 3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);
        homeLayout = findViewById(R.id.homeLayout);
        contactLayout = findViewById(R.id.contactLayout);
        settingLayout = findViewById(R.id.settingLayout);
        profileLayout = findViewById(R.id.profileLayout);

        builder = new AlertDialog.Builder(this);
        mauth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.cancelbutton1);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("    ")
                        .setMessage("هل انت متأكد من تسجيل الخروج ؟")
                        .setIcon(R.drawable.logo).setCancelable(false)
                        .setNegativeButton("لا", null)
                        .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mauth.signOut();
                                Intent intent = new Intent(SettingPage.this, Signin.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(SettingPage.this, "تم تسجيل الخروج", Toast.LENGTH_SHORT).show();
                            }
                        }).create().show();
            }
        });
        supportimage = findViewById(R.id.imageButton10);
        supportimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(SettingPage.this, support.class);
                startActivity(intent);
            }
        });
        resetpassword = findViewById(R.id.imageButtonc);
        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(SettingPage.this, ResetPassword.class);
                startActivity(intent);
            }
        });
        backimage = findViewById(R.id.imageView21);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(SettingPage.this, HomePage.class);
                startActivity(intent);
            }
        });
        control = findViewById(R.id.imageButton);
        control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(SettingPage.this, ControlPage.class);
                startActivity(intent);
            }
        });

        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 1) {
                    Intent intent = new Intent(SettingPage.this, HomePage.class);
                    startActivity(intent);
                    selecttabmenu = 1;
                }
            }
        });
        contactLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 2) {
                    Intent intent = new Intent(SettingPage.this, Chat.class);
                    startActivity(intent);
                    selecttabmenu = 2;
                }
            }
        });
        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 4) {
                    Intent intent = new Intent(SettingPage.this, ProfilePage.class);
                    startActivity(intent);
                    selecttabmenu = 4;
                }
            }
        });
    }
}