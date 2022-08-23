package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class WorkoutEndActivity extends AppCompatActivity {

    Button goBackHomeButton;
    Data data;
    Workout workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_end);

        Intent intent = getIntent();
        Serializable dataObject = intent.getSerializableExtra("data");
        Serializable workoutObject = intent.getSerializableExtra("workout");
        if (dataObject != null)
            data = (Data) dataObject;
        if (workoutObject != null)
            workout = (Workout) workoutObject;

        goBackHomeButton = findViewById(R.id.goBackHomeButton);
        goBackHomeButton.setOnClickListener(v -> {
            data.addWorkout(workout);
            Intent goBackHome = new Intent(WorkoutEndActivity.this, HomeActivity.class);
            goBackHome.putExtra("data", data);
            startActivity(goBackHome);
        });
    }

}