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

public class TrainingWithTimeActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    long timeLeftInMillis;

    // Layout links
    Button resetTimerButton, goRestingButton, skipExerciseButton;
    ImageButton backButton, infoButton, pauseButton, playButton;
    TextView exerciseName, timer;
    ImageView exerciseImage;

    // Extras from intent
    Training training;
    ExerciseOfPlan exerciseOfPlan;
    int remainingSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_with_time);

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


        // Variables linking
        resetTimerButton = findViewById(R.id.resetTimerButton);
        goRestingButton = findViewById(R.id.goRestingButton);
        skipExerciseButton = findViewById(R.id.skipExerciseButton);
        backButton = findViewById(R.id.backButton);
        infoButton = findViewById(R.id.infoButton);
        pauseButton = findViewById(R.id.pauseButton);
        playButton = findViewById(R.id.playButton);
        exerciseName = findViewById(R.id.exerciseName);
        timer = findViewById(R.id.timer);
        exerciseImage = findViewById(R.id.exerciseImage);

        // Values setting
        exerciseName.setText(exerciseOfPlan.exercise.name);

        setTimer();
        startTimer();

        skipExerciseButton.setOnClickListener(v -> prepareIntentForNewExercise());

        backButton.setOnClickListener(v -> {
            Intent goToPlanView = new Intent(TrainingWithTimeActivity.this, WorkoutPlanOverviewActivity.class);
            goToPlanView.putExtra("training", training);
            startActivity(goToPlanView);
        });

        infoButton.setOnClickListener(v -> {
            Intent getInfo = new Intent(TrainingWithTimeActivity.this, ExerciseInfoActivity.class);
            getInfo.putExtra("exercise", exerciseOfPlan.exercise);
            startActivity(getInfo);
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

        resetTimerButton.setOnClickListener(v -> {
            setTimer();
            updateCountDownText();
        });

        goRestingButton.setOnClickListener(v -> {
            // We have done a set now
            remainingSets--;
            // If sets have finished
            if (remainingSets == 0) {
                // We set this exercise as done
                training.setExerciseAsDone(exerciseOfPlan);
                // The next activity is launched
                prepareIntent();
            }
            // If we still have sets to do, we go resting
            else {
                Intent goRest = new Intent(TrainingWithTimeActivity.this, TrainingRestActivity.class);
                goRest.putExtra("training", training);
                goRest.putExtra("exerciseOfPlan", exerciseOfPlan);
                goRest.putExtra("remainingSets", remainingSets);
                startActivity(goRest);
            }
        });
    }

    void prepareIntentForNewExercise() {
        // We find the next exercise
        ExerciseOfPlan nextExercise = training.getNextExercise(exerciseOfPlan);
        // If there is a next exercise
        if (nextExercise != null)
        {
            Intent goToNextExercise;
            // If it is a reps exercise
            if (nextExercise.exerciseUnity == ExerciseUnity.Reps)
            {
                goToNextExercise = new Intent(TrainingWithTimeActivity.this, TrainingWithRepsActivity.class);
            }
            // If it is a time exercise
            else {
                goToNextExercise = new Intent(TrainingWithTimeActivity.this, TrainingWithTimeActivity.class);
            }
            goToNextExercise.putExtra("training", training);
            goToNextExercise.putExtra("exerciseOfPlan", nextExercise);
            startActivity(goToNextExercise);
        }
        // If there is not a next exercise, training has ended
        else {
            Intent goToEnd = new Intent(TrainingWithTimeActivity.this, TrainingEndActivity.class);
            goToEnd.putExtra("training", training);
            startActivity(goToEnd);
        }
    }

    void prepareIntentForNextSet() {
        Intent startNow;
        if (exerciseOfPlan.exerciseUnity == ExerciseUnity.Reps) {
            startNow = new Intent(TrainingWithTimeActivity.this, TrainingWithRepsActivity.class);
        }
        else {
            startNow = new Intent(TrainingWithTimeActivity.this, TrainingWithTimeActivity.class);
        }
        startNow.putExtra("training", training);
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

    void setTimer() {
        timeLeftInMillis = exerciseOfPlan.repsOrTime * 1000L;
        if (exerciseOfPlan.exerciseUnity == ExerciseUnity.Minutes)
            timeLeftInMillis *= 60;
    }

    void prepareIntent() {
        // We find the next exercise
        ExerciseOfPlan nextExercise = training.getNextExercise(exerciseOfPlan);
        // If there is a next exercise
        if (nextExercise != null)
        {
            Intent goToNextExercise;
            // If it is a reps exercise
            if (nextExercise.exerciseUnity == ExerciseUnity.Reps)
            {
                goToNextExercise = new Intent(TrainingWithTimeActivity.this, TrainingWithRepsActivity.class);
            }
            // If it is a time exercise
            else {
                goToNextExercise = new Intent(TrainingWithTimeActivity.this, TrainingWithTimeActivity.class);
            }
            goToNextExercise.putExtra("training", training);
            goToNextExercise.putExtra("exerciseOfPlan", nextExercise);
            startActivity(goToNextExercise);
        }
        // If there is not a next exercise, training has ended
        else {
            Intent goToEnd = new Intent(TrainingWithTimeActivity.this, TrainingEndActivity.class);
            goToEnd.putExtra("training", training);
            startActivity(goToEnd);
        }
    }

}