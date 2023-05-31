package com.example.atmakan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.atmakan.admin.AdminActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class AddTherapest extends AppCompatActivity {
    TextView tvSelectDate;
    Button button;
    EditText Text1, Text2, Text3, Text4, Text5, Text16, age;
    ImageView imageView;
    RadioGroup radio_g;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReferencethrapist;
    boolean identityStatus = false;
    boolean emailStatus = false;
    boolean passStatus = false;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_therapest);

        radio_g = (RadioGroup) findViewById(R.id.rgLanguages);
        imageView = (ImageView) findViewById(R.id.imageView7);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(AddTherapest.this, AdminActivity.class);
                startActivity(intent);
            }
        });
        tvSelectDate = (TextView) findViewById(R.id.editText_Date);
        age = (EditText) findViewById(R.id.age);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReferencethrapist = FirebaseDatabase.getInstance().getReference().child("Thirapest");
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        tvSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(AddTherapest.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        tvSelectDate.setText(date);
                        age.setText(Integer.toString(caculateAge(calendar.getTimeInMillis())));
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        Text1 = (EditText) findViewById(R.id.editTextTextPersonName2);
        Text2 = (EditText) findViewById(R.id.editTextTextPersonName3);
        Text3 = (EditText) findViewById(R.id.editTextNumber);
        Text4 = (EditText) findViewById(R.id.editTextTextEmailAddress);
        Text5 = (EditText) findViewById(R.id.editTextTextPassword5);
        Text16 = (EditText) findViewById(R.id.editTextTextPassword4);
        button = (Button) findViewById(R.id.button);

        Text3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                identityStatus = false;
                if (!editable.toString().isEmpty()) {
                    FirebaseDatabase.getInstance().getReference("users").orderByChild("identity").equalTo(editable.toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                Text3.setError("رقم الهوية موجود من قبل");
                                identityStatus = true;

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });

                    FirebaseDatabase.getInstance().getReference("Thirapest").orderByChild("identity").equalTo(editable.toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                Text3.setError("رقم الهوية موجود من قبل");
                                identityStatus = true;

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });

        Text4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                emailStatus = false;
                if (!editable.toString().isEmpty()) {
                    FirebaseDatabase.getInstance().getReference("users").orderByChild("Email").equalTo(editable.toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                Text4.setError("البريد الإلكتروني موجود من قبل ");
                                emailStatus = true;
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });

                    FirebaseDatabase.getInstance().getReference("Thirapest").orderByChild("Email").equalTo(editable.toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                Text4.setError("البريد الإلكتروني موجود من قبل ");
                                emailStatus = true;
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });

        Text5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                passStatus = false;
                String password = editable.toString();
                if (!password.isEmpty()) {
                    if (!password.matches(".*[A-Z].*") && !password.matches(".*[a-z].*")) {
                        Text5.setError("كلمة المرور ضعيفة");
                        passStatus = true;
                    } else if (password.length() <= 6) {
                        Text5.setError("كلمة المرور ضعيفة");
                        passStatus = true;
                    }
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(AddTherapest.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                String Fname = Text1.getText().toString();
                String Lname = Text2.getText().toString();
                String identity = Text3.getText().toString();
                String email = Text4.getText().toString();
                String password = Text5.getText().toString();
                String authpassword = Text16.getText().toString();
                String aage = age.getText().toString();

                if (Fname.isEmpty()) {
                    progressDialog.dismiss();
                    Text1.setError("الحقل مطلوب");
                } else if (Lname.isEmpty()) {
                    progressDialog.dismiss();
                    Text2.setError("الحقل مطلوب");
                } else if (identity.isEmpty()) {
                    progressDialog.dismiss();
                    Text3.setError("الحقل مطلوب");
                } else if (identity.length() != 10) {
                    progressDialog.dismiss();
                    Text3.setError("تأكد من رقم الهوية ");
                } else if (email.isEmpty()) {
                    progressDialog.dismiss();
                    Text4.setError("الحقل مطلوب");
                } else if (!email.contains("@")) {
                    progressDialog.dismiss();
                    Text4.setError("البريد الإلكتروني غير صالح ");
                } else if (password.isEmpty()) {
                    progressDialog.dismiss();
                    Text5.setError("الحقل مطلوب");
                } else if (authpassword.isEmpty()) {
                    progressDialog.dismiss();
                    Text16.setError("الحقل مطلوب");
                } else if (!password.equals(authpassword)) {
                    progressDialog.dismiss();
                    Text5.setError("كلمة المرور غير متطابقة ");
                    Text16.setError("كلمة المرور غير متطابقة ");
                } else if (TextUtils.isEmpty(tvSelectDate.getText().toString())) {
                    progressDialog.dismiss();
                    tvSelectDate.setError("الحقل مطلوب");
                    return;
                } else if (radio_g.getCheckedRadioButtonId() == -1) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "الرجاء اختيار الجنس ", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (!passStatus) {

                        if (!identityStatus) {

                            if (!emailStatus) {
                                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {

                                            FirebaseUser user = firebaseAuth.getCurrentUser();
                                            user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    progressDialog.dismiss();
                                                    Toast.makeText(AddTherapest.this, "تم إرسال البريد الإلكتروني للتحقق", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            firebaseUser = task.getResult().getUser();
                                            assert firebaseUser != null;
                                            DatabaseReference newUser = databaseReferencethrapist.child(firebaseUser.getUid());
                                            newUser.child("FirstName").setValue(Fname);
                                            newUser.child("LastName").setValue(Lname);
                                            newUser.child("ID").setValue(firebaseUser.getUid());
                                            newUser.child("identity").setValue(identity);
                                            newUser.child("Password").setValue(password);
                                            newUser.child("Email").setValue(email);
                                            newUser.child("Age").setValue(aage);
                                            newUser.child("userStatus").setValue("true");
                                            progressDialog.dismiss();
                                            Toast.makeText(AddTherapest.this, "تم إنشاء الحساب ", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(AddTherapest.this, AdminActivity.class);
                                            startActivity(intent);
                                        } else {
                                            progressDialog.dismiss();
                                            Toast.makeText(AddTherapest.this, "حدث خطأ", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(AddTherapest.this, "حدث خطأ", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(AddTherapest.this, "حدث خطأ", Toast.LENGTH_LONG).show();

                        }
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(AddTherapest.this, "حدث خطأ", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    int caculateAge(long date) {
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);

        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
            age--;
        }
        return age;
    }
}
