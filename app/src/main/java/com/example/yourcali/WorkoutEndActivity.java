package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class WorkoutEndActivity extends AppCompatActivity {

    Toolbar myToolbar;
    Button goBackHomeButton;
    TextView encouragementPhrase;
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

        myToolbar = findViewById(R.id.toolbar);
        encouragementPhrase = findViewById(R.id.encouragementPhrase);
        goBackHomeButton = findViewById(R.id.goBackHomeButton);

        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Allenamento");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        encouragementPhrase.setText(extractPhrase());

        goBackHomeButton.setOnClickListener(v -> {
            data.addWorkout(workout);
            Intent goBackHome = new Intent(WorkoutEndActivity.this, HomeActivity.class);
            goBackHome.putExtra("data", data);
            startActivity(goBackHome);
        });
    }

    private String extractPhrase() {
        String[] phraseList = new String[3];
        phraseList[0] = "Ben fatto!";
        phraseList[1] = "Congratulazioni! Hai completato l'allenamento";
        phraseList[2] = "Ottimo lavoro!";
        Random random = new Random();
        int index = random.nextInt(3);
        return phraseList[index];
    }

}