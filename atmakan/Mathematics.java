package com.example.atmakan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Mathematics extends AppCompatActivity {
    String expct;
    EditText answer;
    TextView question;
    ImageButton backimage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathematics);
        backimage = (ImageButton) findViewById(R.id.imageButton46);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(Mathematics.this, lerningmode.class);
                startActivity(intent);
            }
        });
        answer = findViewById(R.id.editText);
        question = findViewById(R.id.textView30);
        setQuestion();
    }

    public void submitButton(View v) {
        if (answer.getText().toString().isEmpty())
        {
            Toast.makeText(this, "الرجاء ادخال الأجابة", Toast.LENGTH_SHORT).show();
        }else if (answer.getText().toString().equals(expct)) {
            Toast.makeText(this, "اجابه صحيحة", Toast.LENGTH_SHORT).show();
            Intent intent = new
                    Intent(Mathematics.this, HomePage.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "اجابه خاطئة", Toast.LENGTH_SHORT).show();
            Intent intent = new
                    Intent(Mathematics.this, lerningmode.class);
            startActivity(intent);
        }
        answer.setText("");
    }

    public void setQuestion() {
        Random rand = new Random();
        int num1 = ThreadLocalRandom.current().nextInt(0, 10);
        int num2 = ThreadLocalRandom.current().nextInt(0, 10);
        int randomquestion = rand.nextInt(3);
        if (randomquestion == 1) {
            question.setText(num1 + "  +  " + num2 + "  =");
            expct = "" + (num1 + num2);
        } else if (randomquestion == 2) {
            question.setText(num1 + "  -  " + num2 + "  =");
            expct = "" + (num1 - num2);
        } else {
            question.setText(num1 + "  *  " + num2 + "  =");
            expct = "" + (num1 * num2);
        }
    }
}