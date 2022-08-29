package com.example.yourcali;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.io.Serializable;
import java.util.Objects;

public class ExerciseConfigurationActivity extends AppCompatActivity {

    ExerciseOfPlan exerciseOfPlan;
    PersonalWorkoutPlan personalWorkoutPlan;
    Data data;

    TextView exerciseName, sets, repsOrTime, restTime, repsOrTimeLabel;
    ImageView exerciseImage;
    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_configuration);

        myToolbar = findViewById(R.id.myToolbar);

        // Get data from intent
        Intent intent = getIntent();
        Serializable exerciseOfPlanObject = intent.getSerializableExtra("exercise");
        Serializable workoutPlanObject = intent.getSerializableExtra("personalWorkoutPlan");
        Serializable dataObject = intent.getSerializableExtra("data");

        if (exerciseOfPlanObject != null)
            exerciseOfPlan = (ExerciseOfPlan) exerciseOfPlanObject;
        if (dataObject != null)
            data = (Data) dataObject;
        if (workoutPlanObject != null)
            personalWorkoutPlan = (PersonalWorkoutPlan) workoutPlanObject;


            setSupportActionBar(myToolbar);
            Objects.requireNonNull(getSupportActionBar()).setTitle("Configurazione esercizio");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            exerciseName = findViewById(R.id.exerciseName);
            sets = findViewById(R.id.sets);
            repsOrTime = findViewById(R.id.repsOrTime);
            restTime = findViewById(R.id.restTime);
            repsOrTimeLabel = findViewById(R.id.repsOrTimeLabel);

            exerciseName.setText(exerciseOfPlan.getExercise().getName());

            sets.setText(String.valueOf(exerciseOfPlan.getSets()));
            if (exerciseOfPlan.getExerciseUnity() != ExerciseUnity.Reps) {
                repsOrTimeLabel.setText(R.string.tempo);
                if (exerciseOfPlan.getExerciseUnity() == ExerciseUnity.Seconds)
                    repsOrTime.setText(exerciseOfPlan.getRepsOrTime() + " secondi");
                else
                    repsOrTime.setText(exerciseOfPlan.getRepsOrTime() + " minuti");
            } else
                repsOrTime.setText(String.valueOf(exerciseOfPlan.getRepsOrTime()));

            if (exerciseOfPlan.getRestUnity() == RestUnity.Seconds)
                restTime.setText(exerciseOfPlan.getRest() + " secondi");
            else
                restTime.setText(exerciseOfPlan.getRest() + " minuti");

            Uri image = Uri.parse(exerciseOfPlan.getExercise().getImage());
            exerciseImage.setImageURI(image);


        }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        Intent goBack = new Intent(ExerciseConfigurationActivity.this, WorkoutPlanCreationActivity.class);
        goBack.putExtra("exerciseOfPlan", exerciseOfPlan);
        goBack.putExtra("personalWorkoutPlan", personalWorkoutPlan);
        goBack.putExtra("data", data);
        startActivity(goBack);
        return true;
    }

}