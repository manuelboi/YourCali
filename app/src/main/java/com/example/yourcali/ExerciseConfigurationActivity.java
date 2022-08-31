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

public class ExerciseConfigurationActivity extends AppCompatActivity {

    ExerciseOfPlan exerciseOfPlan;
    PersonalWorkoutPlan personalWorkoutPlan;
    Data data;

    TextView exerciseName, sets, repsOrTime, restTime, repsOrTimeLabel;
    ImageView exerciseImage;
    Button editExercise;
    Toolbar myToolbar;
    ComeFrom2 comeFrom2;
    ComeFrom2 comeFromEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_configuration);

        myToolbar = findViewById(R.id.myToolbar);

        // Get data from intent
        Intent intent = getIntent();
        Serializable exerciseOfPlanObject = intent.getSerializableExtra("exerciseOfPlan");
        Serializable workoutPlanObject = intent.getSerializableExtra("personalWorkoutPlan");
        Serializable dataObject = intent.getSerializableExtra("data");
        Serializable comeFromObject = intent.getSerializableExtra("comeFrom2");

        if (exerciseOfPlanObject != null)
            exerciseOfPlan = (ExerciseOfPlan) exerciseOfPlanObject;
        if (dataObject != null)
            data = (Data) dataObject;
        if (workoutPlanObject != null)
            personalWorkoutPlan = (PersonalWorkoutPlan) workoutPlanObject;
        if(comeFromObject != null){
            comeFrom2 = (ComeFrom2) comeFromObject;
        }


            setSupportActionBar(myToolbar);
            Objects.requireNonNull(getSupportActionBar()).setTitle("Configurazione esercizio");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            exerciseName = findViewById(R.id.exerciseName);
            sets = findViewById(R.id.sets);
            repsOrTime = findViewById(R.id.repsOrTime);
            restTime = findViewById(R.id.restTime);
            repsOrTimeLabel = findViewById(R.id.repsOrTimeLabel);
            exerciseImage = findViewById(R.id.exerciseImage);
            editExercise = findViewById(R.id.editConfigurationButton);

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

            editExercise.setOnClickListener(v -> {
                Intent editIntent = new Intent(ExerciseConfigurationActivity.this, ExerciseConfigurationEditingActivity.class);
                editIntent.putExtra("data", data);
                editIntent.putExtra("comeFrom2", comeFrom2);
                editIntent.putExtra("personalWorkoutPlan", personalWorkoutPlan);
                editIntent.putExtra("exercise", exerciseOfPlan);
                startActivity(editIntent);
            });
        }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        Intent goBack;
        if(comeFrom2 == ComeFrom2.WorkoutPlanEditing){
            goBack = new Intent(ExerciseConfigurationActivity.this, WorkoutPlanEditingActivity.class);
        }else
            goBack = new Intent(ExerciseConfigurationActivity.this, WorkoutPlanCreationActivity.class);

        goBack.putExtra("personalWorkoutPlan", personalWorkoutPlan);
        goBack.putExtra("data", data);
        startActivity(goBack);
        return true;
    }

}