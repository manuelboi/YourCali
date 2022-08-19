package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class WorkoutEndActivity extends AppCompatActivity {

    Button goBackHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_end);

        goBackHomeButton = findViewById(R.id.goBackHomeButton);
        goBackHomeButton.setOnClickListener(v -> {
            Intent goBackHome = new Intent(WorkoutEndActivity.this, HomeActivity.class);
            startActivity(goBackHome);
        });
    }

}