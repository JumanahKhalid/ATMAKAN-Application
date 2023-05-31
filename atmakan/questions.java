package com.example.atmakan;

import androidx.appcompat.app.AlertDialog;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class questions extends AppCompatActivity {
    TextView tv1, numquestion, cancel;
    Button nextbutton;
    RadioGroup radio_g;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    AlertDialog.Builder builder;
    String[] question = {
            "هل يعاني طفلك ضعف في التواصل البصري؟",
            "هل يفتقر طفلك للابتسامة الاجتماعية؟",
            "هل طفلك غالبًا يبقى وحيدًا؟",
            "هل طفلك يتقبل الاخرين في اغلب الاوقات؟",
            "هل طفلك غير قادر على مواصلة التفاعل الاجتماعي؟",
            "هل يظهر طفلك استجابات انفعالية غير مناسبة؟",
            "هل لدى طفلك صعوبات في استخدام اللغة غير اللفظية والايماءات؟",
            "هل ينشغل طفلك بتعبيرات لغوية وحركات نمطية ذات طابع تكراري؟",
            "هل طفلك عاجز عن البدء والاستمرار بمحادثة مع الاخرين؟",
            "هل طفلك غير قادر على فهم مضمون الكلام(المعنى الحقيقي)؟",
            "هل طفلك يظهر سلوك عدواني؟",
            "هل طفلك يحدق لفترات طويلة بشيء محدد؟",
            "هل طفلك يعاني من انتباه وتركيز مشتت؟",
            "هل طفلك لدية ذاكرة خارقة في مجال ما؟",
            "هل طفلك يصر على الروتين ويرفض التغير؟",
    };
    String[] opt = {"دائمًا", "غالبًا", "كثيرًا", "احيانًا", "نادرًا"};

    int flag = 0;
    public static int marks;
    public static String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        builder = new AlertDialog.Builder(this);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        nextbutton = (Button) findViewById(R.id.button3);

        tv1 = (TextView) findViewById(R.id.textView10);
        numquestion = (TextView) findViewById(R.id.textView12);
        cancel = (TextView) findViewById(R.id.textView14);


        radio_g = (RadioGroup) findViewById(R.id.radioGroup);
        rb1 = (RadioButton) findViewById(R.id.radioButton14);
        rb2 = (RadioButton) findViewById(R.id.radioButton15);
        rb3 = (RadioButton) findViewById(R.id.radioButton16);
        rb4 = (RadioButton) findViewById(R.id.radioButton17);
        rb5 = (RadioButton) findViewById(R.id.radioButton18);
        tv1.setText(question[flag]);
        rb1.setText(opt[0]);
        rb2.setText(opt[1]);
        rb3.setText(opt[2]);
        rb4.setText(opt[3]);
        rb5.setText(opt[4]);

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radio_g.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "الرجاء اختيار اجابة ", Toast.LENGTH_SHORT).show();
                    return;

                } else {

                    RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                    String ansText = uans.getText().toString();
                    if (ansText.equals(opt[0])) {
                        marks = marks + 5;
                    } else if (ansText.equals(opt[1])) {
                        marks = marks + 4;
                    } else if (ansText.equals(opt[2])) {
                        marks = marks + 3;
                    } else if (ansText.equals(opt[3])) {
                        marks = marks + 2;
                    } else {
                        marks = marks + 1;
                    }

                    flag++;
                    if (flag < question.length) {
                        tv1.setText(question[flag]);
                        rb1.setText(opt[0]);
                        rb2.setText(opt[1]);
                        rb3.setText(opt[2]);
                        rb4.setText(opt[3]);
                        rb5.setText(opt[4]);
                        numquestion.setText(flag + 1 + "/15");
                    }
                    if (marks < 30) {
                        level = "مستوى طيف التوحد بسيط";
                    } else if (marks < 60) {
                        level = "مستوى طيف التوحد متوسط";
                    } else {
                        level = "مستوى طيف التوحد شديد";
                    }

                    if (flag >= 15) {
                        Intent in = new Intent(getApplicationContext(), Result.class);
                        startActivity(in);
                    }
                }
                radio_g.clearCheck();
                databaseReference.child("Score").child(firebaseUser.getUid()).child("result").setValue(marks);
                databaseReference.child("Score").child(firebaseUser.getUid()).child("level").setValue(level);

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("    ")
                        .setMessage("هل انت متأكد من الغاء الإختبار ؟")
                        .setIcon(R.drawable.logo).setCancelable(false)
                        .setNegativeButton("لا", null)
                        .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new
                                        Intent(questions.this, Startq.class);
                                startActivity(intent);
                                databaseReference.child("Score").child(firebaseUser.getUid()).child("result").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists()) {
                                            marks -= Integer.parseInt(snapshot.getValue().toString());
                                        }
                                        snapshot.getRef().setValue(marks);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }
                        }).create().show();
            }
        });
    }
}