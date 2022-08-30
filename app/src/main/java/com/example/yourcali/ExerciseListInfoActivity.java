package com.example.yourcali;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.Serializable;
import java.util.Objects;

public class ExerciseListInfoActivity extends AppCompatActivity {
    Exercise exercise;
    PersonalWorkoutPlan personalWorkoutPlan;
    Data data;
    TextView exerciseName, bodyPart, difficulty, helpGuide;
    Toolbar myToolbar;
    ImageView exerciseImage;
    Button addButton;
    String bodyPartString, difficultyString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list_info);

        myToolbar = findViewById(R.id.myToolbar);

        // Get data from intent
        Intent intent = getIntent();
        Serializable exerciseObject = intent.getSerializableExtra("exercise");
        Serializable personalWorkoutPlanObject = intent.getSerializableExtra("personalWorkoutPlan");
        Serializable dataObject = intent.getSerializableExtra("data");

        if (exerciseObject != null) {
            exercise = (Exercise) exerciseObject;
        }
        if (personalWorkoutPlanObject != null) {
            personalWorkoutPlan = (PersonalWorkoutPlan) personalWorkoutPlanObject;
        }
        if (dataObject != null) {
            data = (Data) dataObject;
        }

        exerciseName = findViewById(R.id.exerciseConfigurationInfoName);
        bodyPart = findViewById(R.id.bodyPartConfigurationInfo);
        difficulty = findViewById(R.id.difficultyConfigurationInfo);
        helpGuide = findViewById(R.id.helpGuideConfigurationInfo);
        exerciseImage = findViewById(R.id.exerciseConfigurationInfoImage);
        addButton = findViewById(R.id.addButtonConfigurationInfo);

        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Informazioni esercizio");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        exerciseName.setText(exercise.getName());

        switch (exercise.getBodyPart()) {
            case Arms: bodyPartString = "Braccia";break;
            case Shoulders: bodyPartString = "Spalle";break;
            case Chest: bodyPartString = "Petto";break;
            case Abs: bodyPartString = "Addominali";break;
            case Back: bodyPartString = "Dorso";break;
            default: bodyPartString = "Gambe";break;
        }
        bodyPart.setText(bodyPartString);

        switch (exercise.getDifficulty()) {
            case Easy: difficultyString = "Facile";break;
            case Medium: difficultyString = "Intermedio";break;
            default: difficultyString = "Difficile";break;
        }
        difficulty.setText(difficultyString);

        helpGuide.setText(exercise.getHelpGuide());

        Uri image = Uri.parse(exercise.getImage());
        exerciseImage.setImageURI(image);

        addButton.setOnClickListener(v -> {
            Intent goToConfiguration = new Intent(ExerciseListInfoActivity.this, ExerciseConfigurationCreationActivity.class);
            goToConfiguration.putExtra("exercise", exercise);
            goToConfiguration.putExtra("personalWorkoutPlan", personalWorkoutPlan);
            goToConfiguration.putExtra("data", data);
            startActivity(goToConfiguration);
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent goBack = new Intent(ExerciseListInfoActivity.this, ExerciseListActivity.class);
        goBack.putExtra("personalWorkoutPlan", personalWorkoutPlan);
        goBack.putExtra("data", data);
        startActivity(goBack);
        return true;
    }

}