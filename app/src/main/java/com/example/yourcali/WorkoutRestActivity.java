package com.example.yourcali;

import android.content.Intent;
import android.net.Uri;
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
    boolean timerRunning;

    // Layout links
    Button startNowButton, skipExerciseButton;
    ImageButton backButton, infoButton, pauseButton, playButton;
    TextView exerciseName, timer, remainingSetsLabel;
    ImageView exerciseImage;

    // Extras from intent
    Workout workout;
    ExerciseOfPlan exerciseOfPlan;
    int remainingSets;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_rest);

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
        if (dataObject != null)
            data = (Data) dataObject;



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
        exerciseName.setText(exerciseOfPlan.getExercise().getName());
        remainingSetsLabel.setText(remainingSets + " serie rimanenti");

        Uri image = Uri.parse(exerciseOfPlan.getExercise().getImage());
        exerciseImage.setImageURI(image);
        
        timerRunning = false;

        setTimer();
        startTimer();

        skipExerciseButton.setOnClickListener(v -> prepareIntentForNewExercise());

        backButton.setOnClickListener(v -> {
            if (timerRunning)
                countDownTimer.cancel();
            Intent goToPlanView = new Intent(WorkoutRestActivity.this, WorkoutPlanOverviewActivity.class);
            goToPlanView.putExtra("workout", workout);
            goToPlanView.putExtra("exerciseOfPlan", exerciseOfPlan);
            goToPlanView.putExtra("data", data);
            startActivity(goToPlanView);
        });

        infoButton.setOnClickListener(v -> {
            if (timerRunning)
                countDownTimer.cancel();
            Intent getInfo = new Intent(WorkoutRestActivity.this, ExerciseInfoActivity.class);
            getInfo.putExtra("exerciseOfPlan", exerciseOfPlan);
            getInfo.putExtra("workout", workout);
            getInfo.putExtra("remainingSets", remainingSets);
            getInfo.putExtra("comeFrom", ComeFrom.Rest);
            getInfo.putExtra("data", data);
            startActivity(getInfo);
        });

        startNowButton.setOnClickListener(v -> prepareIntentForNextSet());

        pauseButton.setOnClickListener(v -> {
            countDownTimer.cancel();
            timerRunning = false;
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
        if (timerRunning)
            countDownTimer.cancel();
        // We find the next exercise
        ExerciseOfPlan nextExercise = workout.getNextExercise(exerciseOfPlan);
        // If there is a next exercise
        if (nextExercise != null)
        {
            Intent goToNextExercise;
            // If it is a reps exercise
            if (nextExercise.getExerciseUnity() == ExerciseUnity.Reps)
            {
                goToNextExercise = new Intent(WorkoutRestActivity.this, WorkoutWithRepsActivity.class);
            }
            // If it is a time exercise
            else {
                goToNextExercise = new Intent(WorkoutRestActivity.this, WorkoutWithTimeActivity.class);
            }
            goToNextExercise.putExtra("workout", workout);
            goToNextExercise.putExtra("exerciseOfPlan", nextExercise);
            goToNextExercise.putExtra("data", data);
            startActivity(goToNextExercise);
        }
        // If there is not a next exercise, workout has ended
        else {
            Intent goToEnd = new Intent(WorkoutRestActivity.this, WorkoutEndActivity.class);
            goToEnd.putExtra("workout", workout);
            goToEnd.putExtra("data", data);
            startActivity(goToEnd);
        }
    }

    void prepareIntentForNextSet() {
        if (timerRunning)
            countDownTimer.cancel();
        Intent startNow;
        if (exerciseOfPlan.getExerciseUnity() == ExerciseUnity.Reps) {
            startNow = new Intent(WorkoutRestActivity.this, WorkoutWithRepsActivity.class);
        }
        else {
            startNow = new Intent(WorkoutRestActivity.this, WorkoutWithTimeActivity.class);
        }
        startNow.putExtra("workout", workout);
        startNow.putExtra("exerciseOfPlan", exerciseOfPlan);
        startNow.putExtra("remainingSets", remainingSets);
        startNow.putExtra("data", data);
        startActivity(startNow);
    }

    void setTimer() {
        timeLeftInMillis = exerciseOfPlan.getRest() * 1000L;
        if (exerciseOfPlan.getRestUnity() == RestUnity.Minutes)
            timeLeftInMillis *= 60;
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
        timerRunning = true;
    }

    void updateCountDownText() {
        int minutes = (int) ((timeLeftInMillis / 1000) / 60);
        int seconds = (int) ((timeLeftInMillis / 1000) % 60);
        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        timer.setText(timeLeftFormatted);
    }

}