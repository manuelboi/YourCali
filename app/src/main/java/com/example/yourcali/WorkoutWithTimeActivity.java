package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.Locale;

public class WorkoutWithTimeActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    long timeLeftInMillis;

    // Layout links
    Button resetTimerButton, goRestingButton, skipExerciseButton;
    ImageButton backButton, infoButton, pauseButton, playButton;
    TextView exerciseName, timer;
    ImageView exerciseImage;
    Space space;

    // Extras from intent
    Workout workout;
    ExerciseOfPlan exerciseOfPlan;
    int remainingSets;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_with_time);

        // Get data from intent
        Intent intent = getIntent();
        Serializable workoutObject = intent.getSerializableExtra("workout");
        Serializable exerciseOfPlanObject = intent.getSerializableExtra("exerciseOfPlan");
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
        } else {
            // If no sets in there, it's the first set of the next exercise
            remainingSets = exerciseOfPlan.getSets();
        }
        if (dataObject != null) {
            data = (Data) dataObject;
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
        space = findViewById(R.id.space);

        // Values setting
        exerciseName.setText(exerciseOfPlan.getExercise().getName());
        if (remainingSets <= 1) {
            goRestingButton.setVisibility(View.GONE);
            space.setVisibility(View.VISIBLE);
        }

        setTimer();

        skipExerciseButton.setOnClickListener(v -> goToNextExercise());

        backButton.setOnClickListener(v -> {
            Intent goToPlanView = new Intent(WorkoutWithTimeActivity.this, WorkoutPlanOverviewActivity.class);
            goToPlanView.putExtra("workout", workout);
            goToPlanView.putExtra("exerciseOfPlan", exerciseOfPlan);
            goToPlanView.putExtra("data", data);
            startActivity(goToPlanView);
        });

        infoButton.setOnClickListener(v -> {
            Intent getInfo = new Intent(WorkoutWithTimeActivity.this, ExerciseInfoActivity.class);
            getInfo.putExtra("exerciseOfPlan", exerciseOfPlan);
            getInfo.putExtra("workout", workout);
            getInfo.putExtra("remainingSets", remainingSets);
            getInfo.putExtra("comeFrom", ComeFrom.Time);
            getInfo.putExtra("data", data);
            startActivity(getInfo);
        });

        pauseButton.setOnClickListener(v -> pause());

        playButton.setOnClickListener(v -> {
            startTimer();
            pauseButton.setVisibility(View.VISIBLE);
            playButton.setVisibility(View.GONE);
        });

        resetTimerButton.setOnClickListener(v -> {
            pause();
            setTimer();
            updateCountDownText();
        });

        goRestingButton.setOnClickListener(v -> goResting());
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
                goResting();
            }
        }.start();
    }

    void updateCountDownText() {
        int minutes = (int) ((timeLeftInMillis / 1000) / 60);
        int seconds = (int) ((timeLeftInMillis / 1000) % 60);
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timer.setText(timeLeftFormatted);
    }

    void setTimer() {
        timeLeftInMillis = exerciseOfPlan.getRepsOrTime() * 1000L;
        if (exerciseOfPlan.getExerciseUnity() == ExerciseUnity.Minutes)
            timeLeftInMillis *= 60;
    }

    void pause() {
        countDownTimer.cancel();
        pauseButton.setVisibility(View.GONE);
        playButton.setVisibility(View.VISIBLE);
    }

    void goResting() {
        // We have done a set now
        remainingSets--;
        // If sets are finished
        if (remainingSets == 0) {
            // We set this exercise as done
            workout.setExerciseAsDone(exerciseOfPlan);
            // The next activity is launched
            goToNextExercise();
        }
        // If we still have sets to do, we go resting
        else {
            Intent goResting = new Intent(WorkoutWithTimeActivity.this, WorkoutRestActivity.class);
            goResting.putExtra("workout", workout);
            goResting.putExtra("remainingSets", remainingSets);
            goResting.putExtra("exerciseOfPlan", exerciseOfPlan);
            goResting.putExtra("data", data);
            startActivity(goResting);
        }
    }

    void goToNextExercise() {
        // We find the next exercise
        ExerciseOfPlan nextExercise = workout.getNextExercise(exerciseOfPlan);
        Intent intent;
        // If there is a next exercise
        if (nextExercise != null)
        {
            // If it is a reps exercise
            if (nextExercise.getExerciseUnity() == ExerciseUnity.Reps)
            {
                intent = new Intent(WorkoutWithTimeActivity.this, WorkoutWithRepsActivity.class);
            }
            // If it is a time exercise
            else {
                intent = new Intent(WorkoutWithTimeActivity.this, WorkoutWithTimeActivity.class);
            }
            intent.putExtra("exerciseOfPlan", nextExercise);
        }
        // If there is not a next exercise, workout has ended
        else {
            intent = new Intent(WorkoutWithTimeActivity.this, WorkoutEndActivity.class);
        }
        intent.putExtra("data", data);
        intent.putExtra("workout", workout);
        startActivity(intent);
    }

}