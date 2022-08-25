package com.example.yourcali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Objects;

public class ExerciseInfoEncyclopediaActivity extends AppCompatActivity {
    Data data;
    Exercise exercise;
    TextView exerciseName, bodyPart, difficulty, helpGuide;
    Toolbar myToolbar;
    ImageView exerciseImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_info);

        myToolbar = findViewById(R.id.myToolbar);

        // Get data from intent
        Intent intent = getIntent();
        Serializable exerciseObject = intent.getSerializableExtra("exercise");
        Serializable dataObject = intent.getSerializableExtra("data");

        if (exerciseObject != null) {
            exercise = (Exercise) exerciseObject;
        }
        if (dataObject != null){
            data = (Data) dataObject;
        }

        exerciseName = findViewById(R.id.exerciseName);
        bodyPart = findViewById(R.id.bodyPart);
        difficulty = findViewById(R.id.difficulty);
        helpGuide = findViewById(R.id.helpGuide);
        exerciseImage = findViewById(R.id.exerciseImage);

        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Informazioni Esercizio");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        exerciseName.setText(exercise.getName());
        bodyPart.setText(exercise.getBodyPart().toString());
        difficulty.setText(exercise.getDifficulty().toString());
        helpGuide.setText(exercise.getHelpGuide());

        Uri image = Uri.parse(exercise.getImage());
        exerciseImage.setImageURI(image);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent goBack = new Intent(ExerciseInfoEncyclopediaActivity.this, ExercisesEncyclopediaActivity.class);
        goBack.putExtra("data", data);
        startActivity(goBack);
        return true;
    }
}