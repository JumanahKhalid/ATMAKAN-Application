package com.example.atmakan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.atmakan.admin.AdminActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Signin extends AppCompatActivity {
    TextView textView;
    Button button;
    EditText Text, pass;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    boolean isLoginByUser = false;

    List<User> userList = new ArrayList<>();
    List<User> therapistList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        getUsers();
        getTherapists();

        textView = (TextView) findViewById(R.id.textView10);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(Signin.this, Signup.class);
                startActivity(intent);
            }
        });
        textView = (TextView) findViewById(R.id.textView8);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(Signin.this, ForgetPassword.class);
                startActivity(intent);
            }
        });
        Text = (EditText) findViewById(R.id.editTextTextEmailAddress2);
        pass = (EditText) findViewById(R.id.editTextTextPassword);
        button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = new ProgressDialog(Signin.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                String email = Text.getText().toString();
                String password = pass.getText().toString();

                if (Text.getText().toString().equals("admin") && pass.getText().toString().equals("admin")) {
                    progressDialog.dismiss();
                    Intent intent = new
                            Intent(Signin.this, AdminActivity.class);
                    startActivity(intent);
                } else {

                    if (email.isEmpty() && password.isEmpty()) {
                        Text.setError("الحقل مطلوب ");
                        pass.setError("الحقل مطلوب ");
                        progressDialog.dismiss();
                    } else if (email.isEmpty()) {
                        Text.setError("الحقل مطلوب ");
                        progressDialog.dismiss();
                    } else if (!email.contains("@")) {
                        Text.setError("البريد الإلكتروني غير صالح ");
                        progressDialog.dismiss();
                    } else if (password.isEmpty()) {
                        pass.setError("الحقل مطلوب ");
                        progressDialog.dismiss();
                    } else {
                        firebaseAuth.signInWithEmailAndPassword(Text.getText().toString(), pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    if (firebaseAuth.getCurrentUser().isEmailVerified()) {

                                        for (User user : userList) {
                                            if (user.getEmail().equals(Text.getText().toString())) {

                                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
                                                reference.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                                            User user1 = dataSnapshot.getValue(User.class);
                                                            if (user1.getEmail().equals(Text.getText().toString())) {

                                                                isLoginByUser = true;
                                                                switch (user1.getUserStatus()) {
                                                                    case "true":
                                                                        databaseReference.child("Score").child(firebaseAuth.getCurrentUser().getUid()).child("result").
                                                                                addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                    @Override
                                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                                        if (snapshot.exists() && Integer.parseInt(snapshot.getValue().toString()) > 0) {
                                                                                            progressDialog.dismiss();
                                                                                            Intent i = new Intent(Signin.this, lerningmode.class);
                                                                                            startActivity(i);
                                                                                            finish();
                                                                                            Toast.makeText(Signin.this, "اهلًا بك", Toast.LENGTH_LONG).show();

                                                                                        } else {
                                                                                            progressDialog.dismiss();
                                                                                            Intent i = new Intent(Signin.this, Startq.class);
                                                                                            startActivity(i);
                                                                                            finish();
                                                                                            Toast.makeText(Signin.this, "اهلًا بك", Toast.LENGTH_LONG).show();
                                                                                        }
                                                                                    }

                                                                                    @Override
                                                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                                                    }
                                                                                });

                                                                        break;

                                                                    case "false":

                                                                        progressDialog.dismiss();
                                                                        Toast.makeText(Signin.this, "في إنتظار الموافقة على الحساب من طرف الأدمن", Toast.LENGTH_LONG).show();

                                                                        break;

                                                                    case "reject":

                                                                        progressDialog.dismiss();
                                                                        Toast.makeText(Signin.this, "لقد تم رفض طلب تسجيلك", Toast.LENGTH_LONG).show();

                                                                        break;
                                                                }
                                                            }
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });

                                            }
                                        }

                                        for (User user : therapistList) {
                                            if (user.getEmail().equals(Text.getText().toString())) {
                                                progressDialog.dismiss();
                                                Intent intent = new Intent(Signin.this, TherapistHomepage.class);
                                                startActivity(intent);
                                                Toast.makeText(Signin.this, "اهلًا بك", Toast.LENGTH_LONG).show();

                                            }
                                        }

                                    } else {

                                        progressDialog.dismiss();
                                        Toast.makeText(Signin.this, "وثق البريد الإلكتروني الخاص بك قبل الدخول", Toast.LENGTH_SHORT).show();

                                    }
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(Signin.this, "الرجاء التأكد من البريد اللإلكتروني او كلمة المرور ", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    private void getUsers() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.size();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    userList.add(dataSnapshot.getValue(User.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getTherapists() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Thirapest");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                therapistList.size();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    therapistList.add(dataSnapshot.getValue(User.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}