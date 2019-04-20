package com.example.csfinalproject;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Random random = new Random();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // randomly pick a recommendation to be used
        Resources res = getResources();
        String[] recommendations = res.getStringArray(R.array.recommendations);
        TextView recommendation = findViewById(R.id.recommend);
        recommendation.setText(recommendations[random.nextInt(recommendations.length)]);



    }
}
