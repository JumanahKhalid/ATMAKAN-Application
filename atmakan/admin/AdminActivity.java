package com.example.atmakan.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.atmakan.AddTherapest;
import com.example.atmakan.ForgetPassword;
import com.example.atmakan.R;
import com.example.atmakan.Signin;

public class AdminActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        textView = (TextView) findViewById(R.id.textView35);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(AdminActivity.this, Signin.class);
                startActivity(intent);
            }
        });
    }

    public void userSettingsOnClick(View view) {
        Intent intent = new Intent(AdminActivity.this, UserSettingsActivity.class);
        startActivity(intent);
    }

    public void statisticalReportOnClick(View view) {
        Intent intent = new Intent(AdminActivity.this, StatisticalReportActivity.class);
        startActivity(intent);
    }

    public void createNewtherapistOnClick(View view) {

        Intent intent = new Intent(AdminActivity.this, AddTherapest.class);
        startActivity(intent);

    }
}