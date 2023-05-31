package com.example.atmakan.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.atmakan.R;
import com.example.atmakan.TherapistControl;
import com.example.atmakan.TherapistSetting;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class StatisticalReportActivity extends AppCompatActivity {


    PieChart pieChart;

    int emotionsNumber, interactiveNumber, entertainmentNumber;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Statistical").child("Report");

    ImageView back ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistical_report);
        pieChart = findViewById(R.id.pieChart);
        back = findViewById(R.id.image2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(StatisticalReportActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
        getStatisticalReport();


    }

    private void getStatisticalReport() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    StatisticalReport item = snapshot.getValue(StatisticalReport.class);

                    emotionsNumber = item.getEmotions();
                    interactiveNumber = item.getInteractive();
                    entertainmentNumber = item.getEntertainment();

                    ArrayList<PieEntry> dataVal = new ArrayList<>();
                    dataVal.add(new PieEntry(interactiveNumber, "تفاعلي"));
                    dataVal.add(new PieEntry(entertainmentNumber, "ترفيهي"));
                    dataVal.add(new PieEntry(emotionsNumber, "مشاعري"));

                    int[] colorClassArray = new int[]{getResources().getColor(R.color.red),
                            getResources().getColor(R.color.yallow),
                            getResources().getColor(R.color.blue)};

                    PieDataSet pieDataSet = new PieDataSet(dataVal, "");
                    pieDataSet.setColors(colorClassArray);

                    PieData pieData = new PieData(pieDataSet);
                    pieChart.setData(pieData);

                } else {

                    HashMap<String, Object> hashMap = new HashMap<>();

                    hashMap.put("emotions", 0);
                    hashMap.put("interactive", 0);
                    hashMap.put("entertainment", 0);

                    databaseReference.setValue(hashMap);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}