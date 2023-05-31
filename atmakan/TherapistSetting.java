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

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class TherapistSetting extends AppCompatActivity {
    ImageButton supportimage, resetpassword, control;
    Button logout;
    LinearLayout homeLayout, settingLayout, profileLayout;
    AlertDialog.Builder builder;
    private FirebaseAuth mauth;
    private int selecttabmenu = 2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_setting);
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
                                Intent intent = new Intent(TherapistSetting.this, Signin.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(TherapistSetting.this, "تم تسجيل الخروج", Toast.LENGTH_SHORT).show();
                            }
                        }).create().show();
            }
        });
        homeLayout = findViewById(R.id.homeLayout);
        settingLayout = findViewById(R.id.settingLayout);
        profileLayout = findViewById(R.id.profileLayout);

        supportimage = findViewById(R.id.imageButton10);
        supportimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(TherapistSetting.this, TherapistSupport.class);
                startActivity(intent);
            }
        });
        control = findViewById(R.id.imageButton);
        control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TherapistSetting.this, TherapistControl.class);// التحكم
                startActivity(intent);
            }
        });
        resetpassword = findViewById(R.id.imageButtonc);
        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TherapistSetting.this, ResetPaswwordTherapist.class);// تغيير رقم السري
                startActivity(intent);
            }
        });
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 1) {
                    Intent intent = new Intent(TherapistSetting.this, TherapistHomepage.class);
                    startActivity(intent);
                    selecttabmenu = 1;
                }
            }
        });
        settingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 2) {
                    Intent intent = new Intent(TherapistSetting.this, TherapistSetting.class);
                    startActivity(intent);
                    selecttabmenu = 2;
                }
            }
        });
        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 3) {
                    Intent intent = new Intent(TherapistSetting.this, TherapistProfilePage.class);
                    startActivity(intent);
                    selecttabmenu = 3;
                }
            }
        });
    }
}