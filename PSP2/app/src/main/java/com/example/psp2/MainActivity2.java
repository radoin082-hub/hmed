package com.example.psp2;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ArrayList<String> className = new ArrayList<>(), location = new ArrayList<>(), profName = new ArrayList<>(), dayOfWeek = new ArrayList<>(), timeSlot = new ArrayList<>(), type = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView T1S1 = findViewById(R.id.T1S1);
        TextView T1S2 = findViewById(R.id.T1S2);
        TextView T1S3 = findViewById(R.id.T1S3);
        TextView T1S4 = findViewById(R.id.T1S4);
        TextView T1S5 = findViewById(R.id.T1S5);
        TextView T2S1 = findViewById(R.id.T2S1);
        TextView T2S2 = findViewById(R.id.T2S2);
        TextView T2S3 = findViewById(R.id.T2S3);
        TextView T2S4 = findViewById(R.id.T2S4);
        TextView T2S5 = findViewById(R.id.T2S5);
        TextView T3S1 = findViewById(R.id.T3S1);
        TextView T3S2 = findViewById(R.id.T3S2);
        TextView T3S3 = findViewById(R.id.T3S3);
        TextView T3S4 = findViewById(R.id.T3S4);
        TextView T3S5 = findViewById(R.id.T3S5);
        TextView T4S1 = findViewById(R.id.T4S1);
        TextView T4S2 = findViewById(R.id.T4S2);
        TextView T4S3 = findViewById(R.id.T4S3);
        TextView T4S4 = findViewById(R.id.T4S4);
        TextView T4S5 = findViewById(R.id.T4S5);
        TextView T5S1 = findViewById(R.id.T5S1);
        TextView T5S2 = findViewById(R.id.T5S2);
        TextView T5S3 = findViewById(R.id.T5S3);
        TextView T5S4 = findViewById(R.id.T5S4);
        TextView T5S5 = findViewById(R.id.T5S5);
        TextView[][] textViews = {
                {T1S1, T1S2, T1S3, T1S4, T1S5},
                {T2S1, T2S2, T2S3, T2S4, T2S5},
                {T3S1, T3S2, T3S3, T3S4, T3S5},
                {T4S1, T4S2, T4S3, T4S4, T4S5},
                {T5S1, T5S2, T5S3, T5S4, T5S5}
        };

        className = getIntent().getStringArrayListExtra("class_name");
        location = getIntent().getStringArrayListExtra("location");
        profName = getIntent().getStringArrayListExtra("prof_name");
        dayOfWeek = getIntent().getStringArrayListExtra("day_of_week");
        timeSlot = getIntent().getStringArrayListExtra("time_slot");
        type = getIntent().getStringArrayListExtra("type");

        for (int i = 0; i < className.toArray().length; i++) {
            String data = type.get(i) + ": " + className.get(i) + "\n" + profName.get(i) + "\n" + location.get(i);
            textViews[parseInt(dayOfWeek.get(i)) - 1][parseInt(timeSlot.get(i)) - 1].setText(data);
            switch (type.get(i)) {
                case "Cours":
                    textViews[parseInt(dayOfWeek.get(i)) - 1][parseInt(timeSlot.get(i)) - 1].setBackgroundColor(Color.parseColor("#FADBD8"));
                    break;
                case "TD":
                    textViews[parseInt(dayOfWeek.get(i)) - 1][parseInt(timeSlot.get(i)) - 1].setBackgroundColor(Color.parseColor("#ABEBC6"));
                    break;
                case "TP":
                    textViews[parseInt(dayOfWeek.get(i)) - 1][parseInt(timeSlot.get(i)) - 1].setBackgroundColor(Color.parseColor("#AED6F1"));
                    break;
            }
        }

    }

    int parseInt(String str) {
        return Integer.parseInt(str);
    }
}