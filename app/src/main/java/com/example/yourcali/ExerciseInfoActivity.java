package com.example.yourcali;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.Serializable;
import java.util.Objects;

public class ExerciseInfoActivity extends AppCompatActivity {

    ComeFrom comeFrom;
    ExerciseOfPlan exerciseOfPlan;
    Workout workout;
    Data data;
    TextView exerciseName, bodyPart, difficulty, helpGuide;
    Toolbar myToolbar;
    ImageButton backButton;
    ImageView exerciseImage;
    String bodyPartString, difficultyString;
    int remainingSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_info);

        myToolbar = findViewById(R.id.myToolbar);

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
        exerciseImage = findViewById(R.id.exerciseImage);

        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Informazioni esercizio");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        exerciseName.setText(exerciseOfPlan.getExercise().getName());

        switch (exerciseOfPlan.getExercise().getBodyPart()) {
            case Arms: bodyPartString = "Braccia";break;
            case Shoulders: bodyPartString = "Spalle";break;
            case Chest: bodyPartString = "Petto";break;
            case Abs: bodyPartString = "Addominali";break;
            case Back: bodyPartString = "Dorso";break;
            default: bodyPartString = "Gambe";break;
        }
        bodyPart.setText(bodyPartString);

        switch (exerciseOfPlan.getExercise().getDifficulty()) {
            case Easy: difficultyString = "Facile";break;
            case Medium: difficultyString = "Intermedio";break;
            default: difficultyString = "Difficile";break;
        }
        difficulty.setText(difficultyString);

        helpGuide.setText(exerciseOfPlan.getExercise().getHelpGuide());

        Uri image = Uri.parse(exerciseOfPlan.getExercise().getImage());
        exerciseImage.setImageURI(image);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
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
        return true;
    }

}