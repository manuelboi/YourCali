package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class ExerciseInfoActivity extends AppCompatActivity {

    ComeFrom comeFrom;
    ExerciseOfPlan exerciseOfPlan;
    Workout workout;
    Data data;
    TextView exerciseName, bodyPart, difficulty, helpGuide;
    ImageButton backButton;
    int remainingSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_info);

        // Get data from intent
        Intent intent = getIntent();
        Serializable exerciseOfPlanObject = intent.getSerializableExtra("exerciseOfPlan");
        Serializable workoutObject = intent.getSerializableExtra("workout");
        Serializable remainingSetsObject = intent.getSerializableExtra("remainingSets");
        Serializable comeFromObject = intent.getSerializableExtra("comeFrom");
        Serializable dataObject = intent.getSerializableExtra("data");

        if (exerciseOfPlanObject != null) {
            exerciseOfPlan = (ExerciseOfPlan) exerciseOfPlanObject;
        }
        if (workoutObject != null) {
            workout = (Workout) workoutObject;
        }
        if (remainingSetsObject != null)
            remainingSets = (int) remainingSetsObject;
        if (comeFromObject != null) {
            comeFrom = (ComeFrom) comeFromObject;
        }
        if (dataObject != null) {
            data = (Data) dataObject;
        }

        exerciseName = findViewById(R.id.exerciseName);
        bodyPart = findViewById(R.id.bodyPart);
        difficulty = findViewById(R.id.difficulty);
        helpGuide = findViewById(R.id.helpGuide);
        backButton = findViewById(R.id.backButton);


        exerciseName.setText(exerciseOfPlan.getExercise().getName());
        bodyPart.setText(exerciseOfPlan.getExercise().getBodyPart().toString());
        difficulty.setText(exerciseOfPlan.getExercise().getDifficulty().toString());
        helpGuide.setText(exerciseOfPlan.getExercise().getHelpGuide());

        backButton.setOnClickListener(v -> {
            Intent goBack;
            switch (comeFrom) {
                case Reps: goBack = new Intent(ExerciseInfoActivity.this, WorkoutWithRepsActivity.class);break;
                case Time: goBack = new Intent(ExerciseInfoActivity.this, WorkoutWithTimeActivity.class);break;
                default: goBack = new Intent(ExerciseInfoActivity.this, WorkoutRestActivity.class);break;
            }
            goBack.putExtra("workout", workout);
            goBack.putExtra("exerciseOfPlan", exerciseOfPlan);
            goBack.putExtra("remainingSets", remainingSets);
            goBack.putExtra("data", data);
            startActivity(goBack);
        });
    }

}