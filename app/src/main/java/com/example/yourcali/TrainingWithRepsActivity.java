package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class TrainingWithRepsActivity extends AppCompatActivity {

    Button doneButton;

    // Extras from intent
    Training training;
    ExerciseOfPlan exerciseOfPlan;
    int remainingSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_with_reps);

        // Get data from intent
        Intent intent = getIntent();
        Serializable trainingObject = intent.getSerializableExtra("training");
        Serializable exerciseOfPlanObject  = intent.getSerializableExtra("exerciseOfPlan");
        Serializable remainingSetsObject = intent.getSerializableExtra("remainingSets");
        if (trainingObject != null) {
            training = (Training) trainingObject;
        }
        if (exerciseOfPlanObject != null) {
            exerciseOfPlan = (ExerciseOfPlan) exerciseOfPlanObject;
        }
        if (remainingSetsObject != null) {
            remainingSets = (int) remainingSetsObject;
        }
        else {
            // If no sets in there, it's the first set of the next exercise
            remainingSets = exerciseOfPlan.sets;
        }


        doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(v -> {
            // We have done a set now
            remainingSets--;
            // If sets have finished
            if (remainingSets == 0) {
                // We find the next exercise
                ExerciseOfPlan nextExercise = training.getNextExercise(exerciseOfPlan);
                // If there is a next exercise
                if (nextExercise != null)
                {
                    Intent goToNextExercise;
                    // If it is a reps exercise
                    if (nextExercise.exerciseUnity == ExerciseUnity.Reps)
                    {
                        goToNextExercise = new Intent(TrainingWithRepsActivity.this, TrainingWithRepsActivity.class);
                    }
                    // If it is a time exercise
                    else {
                        goToNextExercise = new Intent(TrainingWithRepsActivity.this, TrainingWithTimeActivity.class);
                    }
                    goToNextExercise.putExtra("training", training);
                    goToNextExercise.putExtra("exerciseOfPlan", nextExercise);
                    startActivity(goToNextExercise);
                }
                // If there is not a next exercise, training has ended
                else {
                    Intent goToEnd = new Intent(TrainingWithRepsActivity.this, TrainingEndActivity.class);
                    goToEnd.putExtra("training", training);
                    startActivity(goToEnd);
                }
            }
            // If we still have sets to do, we go resting
            else {
                training.setExerciseAsDone(exerciseOfPlan);
                Intent goRest = new Intent(TrainingWithRepsActivity.this, TrainingRestActivity.class);
                goRest.putExtra("training", training);
                goRest.putExtra("exerciseOfPlan", exerciseOfPlan);
                goRest.putExtra("remainingSets", remainingSets);
                startActivity(goRest);
            }
        });
    }

}