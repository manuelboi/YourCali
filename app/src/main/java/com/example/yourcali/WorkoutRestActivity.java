package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.Serializable;
import java.util.Locale;

public class WorkoutRestActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    long timeLeftInMillis;

    // Layout links
    Button startNowButton, skipExerciseButton;
    ImageButton backButton, infoButton, pauseButton, playButton;
    TextView exerciseName, timer, remainingSetsLabel;
    ImageView exerciseImage;

    // Extras from intent
    Workout workout;
    ExerciseOfPlan exerciseOfPlan;
    int remainingSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_rest);

        // Get data from intent
        Intent intent = getIntent();
        Serializable workoutObject = intent.getSerializableExtra("workout");
        Serializable exerciseOfPlanObject  = intent.getSerializableExtra("exerciseOfPlan");
        Serializable remainingSetsObject = intent.getSerializableExtra("remainingSets");
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
            remainingSets = exerciseOfPlan.sets;
        }


        // Variables linking
        startNowButton = findViewById(R.id.startNowButton);
        skipExerciseButton = findViewById(R.id.skipExerciseButton);
        backButton = findViewById(R.id.backButton);
        infoButton = findViewById(R.id.infoButton);
        pauseButton = findViewById(R.id.pauseButton);
        playButton = findViewById(R.id.playButton);
        exerciseName = findViewById(R.id.exerciseName);
        remainingSetsLabel = findViewById(R.id.remainingSets);
        timer = findViewById(R.id.timer);
        exerciseImage = findViewById(R.id.exerciseImage);

        // Values setting
        exerciseName.setText(exerciseOfPlan.exercise.name);
        remainingSetsLabel.setText(remainingSets + " serie rimanenti");

        timeLeftInMillis = exerciseOfPlan.rest * 1000L;
        if (exerciseOfPlan.restUnity == RestUnity.Minutes)
            timeLeftInMillis *= 60;
        startTimer();

        skipExerciseButton.setOnClickListener(v -> prepareIntentForNewExercise());

        backButton.setOnClickListener(v -> {
            Intent goToPlanView = new Intent(WorkoutRestActivity.this, WorkoutPlanOverviewActivity.class);
            goToPlanView.putExtra("workout", workout);
            startActivity(goToPlanView);
        });

        infoButton.setOnClickListener(v -> {
            Intent getInfo = new Intent(WorkoutRestActivity.this, ExerciseInfoActivity.class);
            getInfo.putExtra("exercise", exerciseOfPlan.exercise);
            startActivity(getInfo);
        });

        startNowButton.setOnClickListener(v -> {
            prepareIntentForNextSet();
        });

        pauseButton.setOnClickListener(v -> {
            countDownTimer.cancel();
            pauseButton.setVisibility(View.GONE);
            playButton.setVisibility(View.VISIBLE);
        });

        playButton.setOnClickListener(v -> {
            startTimer();
            pauseButton.setVisibility(View.VISIBLE);
            playButton.setVisibility(View.GONE);
        });
    }

    void prepareIntentForNewExercise() {
        // We find the next exercise
        ExerciseOfPlan nextExercise = workout.getNextExercise(exerciseOfPlan);
        // If there is a next exercise
        if (nextExercise != null)
        {
            Intent goToNextExercise;
            // If it is a reps exercise
            if (nextExercise.exerciseUnity == ExerciseUnity.Reps)
            {
                goToNextExercise = new Intent(WorkoutRestActivity.this, WorkoutWithRepsActivity.class);
            }
            // If it is a time exercise
            else {
                goToNextExercise = new Intent(WorkoutRestActivity.this, WorkoutWithTimeActivity.class);
            }
            goToNextExercise.putExtra("workout", workout);
            goToNextExercise.putExtra("exerciseOfPlan", nextExercise);
            startActivity(goToNextExercise);
        }
        // If there is not a next exercise, workout has ended
        else {
            Intent goToEnd = new Intent(WorkoutRestActivity.this, WorkoutEndActivity.class);
            goToEnd.putExtra("workout", workout);
            startActivity(goToEnd);
        }
    }

    void prepareIntentForNextSet() {
        Intent startNow;
        if (exerciseOfPlan.exerciseUnity == ExerciseUnity.Reps) {
            startNow = new Intent(WorkoutRestActivity.this, WorkoutWithRepsActivity.class);
        }
        else {
            startNow = new Intent(WorkoutRestActivity.this, WorkoutWithTimeActivity.class);
        }
        startNow.putExtra("workout", workout);
        startNow.putExtra("exerciseOfPlan", exerciseOfPlan);
        startNow.putExtra("remainingSets", remainingSets);
        startActivity(startNow);
    }

    void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                prepareIntentForNextSet();
            }
        }.start();
    }

    void updateCountDownText() {
        int minutes = (int) ((timeLeftInMillis / 1000) / 60);
        int seconds = (int) ((timeLeftInMillis / 1000) % 60);
        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        timer.setText(timeLeftFormatted);
    }

}