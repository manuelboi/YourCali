package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class WorkoutWithRepsActivity extends AppCompatActivity {

    // Layout links
    Button doneButton, skipExerciseButton;
    ImageButton backButton, infoButton;
    TextView exerciseName, reps;
    ImageView exerciseImage;

    // Extras from intent
    Workout workout;
    ExerciseOfPlan exerciseOfPlan;
    int remainingSets;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_with_reps);

        // Get data from intent
        Intent intent = getIntent();
        Serializable workoutObject = intent.getSerializableExtra("workout");
        Serializable exerciseOfPlanObject  = intent.getSerializableExtra("exerciseOfPlan");
        Serializable remainingSetsObject = intent.getSerializableExtra("remainingSets");
        Serializable dataObject = intent.getSerializableExtra("data");
        if (workoutObject != null) {
            workout = (Workout) workoutObject;
        }
        if (exerciseOfPlanObject != null) {
            exerciseOfPlan = (ExerciseOfPlan) exerciseOfPlanObject;
        }
        if (remainingSetsObject != null) {
            remainingSets = (int) remainingSetsObject;
        }
        else {
            // If no sets in there, it's the first set of the next exercise
            remainingSets = exerciseOfPlan.getSets();
        }
        if (dataObject != null) {
            data = (Data) dataObject;
        }


        // Variables linking
        doneButton = findViewById(R.id.doneButton);
        skipExerciseButton = findViewById(R.id.skipExerciseButton);
        backButton = findViewById(R.id.backButton);
        infoButton = findViewById(R.id.infoButton);
        exerciseName = findViewById(R.id.exerciseName);
        reps = findViewById(R.id.reps);
        exerciseImage = findViewById(R.id.exerciseImage);

        // Values setting
        exerciseName.setText(exerciseOfPlan.getExercise().getName());
        reps.setText(exerciseOfPlan.getSets() + " Ripetizioni");
        // exerciseImage.setImageResource();

        doneButton.setOnClickListener(v -> {
            // We have done a set now
            remainingSets--;
            // If sets have finished
            if (remainingSets == 0) {
                // We set this exercise as done
                workout.setExerciseAsDone(exerciseOfPlan);
                // The next activity is launched
                prepareIntent();
            }
            // If we still have sets to do, we go resting
            else {
                Intent goRest = new Intent(WorkoutWithRepsActivity.this, WorkoutRestActivity.class);
                goRest.putExtra("workout", workout);
                goRest.putExtra("exerciseOfPlan", exerciseOfPlan);
                goRest.putExtra("remainingSets", remainingSets);
                goRest.putExtra("data", data);
                startActivity(goRest);
            }
        });

        skipExerciseButton.setOnClickListener(v -> prepareIntent());

        backButton.setOnClickListener(v -> {
            Intent goToPlanView = new Intent(WorkoutWithRepsActivity.this, WorkoutPlanOverviewActivity.class);
            goToPlanView.putExtra("workout", workout);
            goToPlanView.putExtra("exerciseOfPlan", exerciseOfPlan);
            goToPlanView.putExtra("data", data);
            startActivity(goToPlanView);
        });

        infoButton.setOnClickListener(v -> {
            Intent getInfo = new Intent(WorkoutWithRepsActivity.this, ExerciseInfoActivity.class);
            getInfo.putExtra("exerciseOfPlan", exerciseOfPlan);
            getInfo.putExtra("workout", workout);
            getInfo.putExtra("comeFrom", ComeFrom.Reps);
            getInfo.putExtra("data", data);
            startActivity(getInfo);
        });
    }

    void prepareIntent() {
            // We find the next exercise
            ExerciseOfPlan nextExercise = workout.getNextExercise(exerciseOfPlan);
            // If there is a next exercise
            if (nextExercise != null)
            {
                Intent goToNextExercise;
                // If it is a reps exercise
                if (nextExercise.getExerciseUnity() == ExerciseUnity.Reps)
                {
                    goToNextExercise = new Intent(WorkoutWithRepsActivity.this, WorkoutWithRepsActivity.class);
                }
                // If it is a time exercise
                else {
                    goToNextExercise = new Intent(WorkoutWithRepsActivity.this, WorkoutWithTimeActivity.class);
                }
                goToNextExercise.putExtra("workout", workout);
                goToNextExercise.putExtra("exerciseOfPlan", nextExercise);
                goToNextExercise.putExtra("data", data);
                startActivity(goToNextExercise);
            }
            // If there is not a next exercise, workout has ended
            else {
                Intent goToEnd = new Intent(WorkoutWithRepsActivity.this, WorkoutEndActivity.class);
                goToEnd.putExtra("workout", workout);
                goToEnd.putExtra("data", data);
                startActivity(goToEnd);
            }
    }

}