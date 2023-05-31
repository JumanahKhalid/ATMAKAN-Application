package com.example.atmakan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Result extends AppCompatActivity {
    Button button;
    TextView score, level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);
        button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(Result.this, lerningmode.class);
                startActivity(intent);
            }
        });
        score = (TextView) findViewById(R.id.textView15);
        level = (TextView) findViewById(R.id.textView16);
        StringBuffer sb = new StringBuffer();
        sb.append("نتيجة الإختبار : ").append(questions.marks).append("/75");
        score.setText(sb);
        StringBuffer sb2 = new StringBuffer();
        sb2.append(questions.level).append("\n");
        level.setText(sb2);
    }
}