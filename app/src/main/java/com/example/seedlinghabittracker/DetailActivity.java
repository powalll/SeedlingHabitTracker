package com.example.seedlinghabittracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class DetailActivity extends AppCompatActivity {
    public TextView detailTitle;
    public ImageView detailImage;
    public TextView detailStreakCount;
    public Button detailButton;
    public int detailStreakCountInt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailTitle = findViewById(R.id.detailTitle);
        detailImage = findViewById(R.id.detailImage);
        detailStreakCount = findViewById(R.id.detailStreakCount);
        detailButton = findViewById(R.id.detailButton);
        detailTitle.setText(getIntent().getStringExtra("DetailTitle"));
        String frequency = getIntent().getStringExtra("DetailFrequency");
        final int daysBetween;
        if(frequency.equals("Daily"))
            daysBetween = 1;
        else if(frequency.equals("Weekly"))
            daysBetween = 7;
        else daysBetween = 2;
        final SharedPreferences sharedPref = getSharedPreferences("Detail", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        detailStreakCountInt = sharedPref.getInt(getIntent().getStringExtra("DetailTitle"), 0);
        detailStreakCount.setText(String.valueOf(detailStreakCountInt));
        detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int today = c.get(Calendar.DAY_OF_YEAR);
                int lastDay = sharedPref.getInt("lastDay", 0);
                if((today - lastDay <= daysBetween && today - lastDay > 0) || detailStreakCountInt == 0)
                {
                    detailStreakCountInt++;
                }
                else if(today - lastDay > daysBetween)
                {
                    detailStreakCountInt = 1;
                }
                editor.putInt("lastDay", today);
                editor.putInt(getIntent().getStringExtra("DetailTitle"), detailStreakCountInt);
                editor.apply();
                detailStreakCount.setText(String.valueOf(detailStreakCountInt));
            }
        });
    }
}
