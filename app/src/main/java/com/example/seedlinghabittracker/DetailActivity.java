package com.example.seedlinghabittracker;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    public TextView detailTitle;
    public ImageView detailImage;
    public TextView detailStreakCount;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailTitle = findViewById(R.id.detailTitle);
        detailImage = findViewById(R.id.detailImage);
        detailStreakCount = findViewById(R.id.detailStreakCount);
        detailTitle.setText(getIntent().getStringExtra("DetailTitle"));
        detailStreakCount.setText(getIntent().getStringExtra("DetailStreakCount"));
    }
}
