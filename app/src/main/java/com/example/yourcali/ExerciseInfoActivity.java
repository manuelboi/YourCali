package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class ExerciseInfoActivity extends AppCompatActivity {

    Exercise exercise;
    TextView exerciseName, bodyPart, difficultly, helpGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_info);

        // Get data from intent
        Intent intent = getIntent();
        Serializable exerciseObject = intent.getSerializableExtra("exercise");

        if (exerciseObject != null) {
            exercise = (Exercise) exerciseObject;
        }

        exerciseName = findViewById(R.id.exerciseName);
        bodyPart = findViewById(R.id.bodyPart);
        difficultly = findViewById(R.id.difficultly);
        helpGuide = findViewById(R.id.helpGuide);
    }

}