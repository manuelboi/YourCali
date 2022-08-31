package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.Serializable;
import java.util.Objects;

public class ExerciseConfigurationEditingActivity extends AppCompatActivity {

    Toolbar myToolbar;
    TextView exerciseNameLabel;
    Button saveExerciseConfigurationButton;
    EditText repsOrTimePLain, setsPlain, restTimePlain;
    RadioGroup repsOrTimeRadioGroup;
    RadioGroup restTimeRadioGroup;
    RadioButton repsRadioButton, minutesRadioButton, secondsRadioButton, minutesRadioButton2, secondsRadioButton2;

    ExerciseOfPlan exerciseOfPlan;
    PersonalWorkoutPlan personalWorkoutPlan;
    ComeFrom2 comeFrom2;
    Data data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_configuration_creation);

        myToolbar = findViewById(R.id.exerciseCreationToolbar);
        exerciseNameLabel = findViewById(R.id.exerciseNameLabel);
        repsOrTimePLain = findViewById(R.id.repsOrTimePlain);
        setsPlain = findViewById(R.id.setsPlain);
        restTimePlain = findViewById(R.id.restTimePlain);
        saveExerciseConfigurationButton = findViewById(R.id.saveExerciseConfigurationButton);
        repsOrTimeRadioGroup = findViewById(R.id.repsOrTimeRadioGroup);
        restTimeRadioGroup = findViewById(R.id.restTimeRadioGroup);
        repsRadioButton = findViewById(R.id.repsRadioButton);
        minutesRadioButton = findViewById(R.id.minutesRadioButton);
        secondsRadioButton = findViewById(R.id.secondsRadioButton);
        minutesRadioButton2 = findViewById(R.id.minutesRadioButton2);
        secondsRadioButton2 = findViewById(R.id.secondsRadioButton2);

        // Get data from intent
        Intent intent = getIntent();
        Serializable exerciseOfPlanObject = intent.getSerializableExtra("exercise");
        Serializable personaWorkoutPlanObject = intent.getSerializableExtra("personalWorkoutPlan");
        Serializable comeFrom2Object = intent.getSerializableExtra("comeFrom2");
        Serializable dataObject = intent.getSerializableExtra("data");
        if (exerciseOfPlanObject != null)
            exerciseOfPlan = (ExerciseOfPlan) exerciseOfPlanObject;
        if (personaWorkoutPlanObject != null)
            personalWorkoutPlan = (PersonalWorkoutPlan) personaWorkoutPlanObject;
        if (comeFrom2Object != null)
            comeFrom2 = (ComeFrom2) comeFrom2Object;
        if (dataObject != null)
            data = (Data) dataObject;

        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Configurazione esercizio");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setData();

        saveExerciseConfigurationButton.setOnClickListener(v -> {
            if(!errorCheck()){
                editExerciseOfPlan();
                Intent editExercise;
                if (comeFrom2 == ComeFrom2.ExerciseConfiguration) {
                    editExercise = new Intent(ExerciseConfigurationEditingActivity.this, ExerciseConfigurationActivity.class);
                    editExercise.putExtra("exerciseOfPlan", exerciseOfPlan);
                }
                else
                    editExercise = new Intent(ExerciseConfigurationEditingActivity.this, WorkoutPlanCreationActivity.class);

                editExercise.putExtra("personalWorkoutPlan", personalWorkoutPlan);
                editExercise.putExtra("data", data);
                startActivity(editExercise);
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        Intent goBack = new Intent(ExerciseConfigurationEditingActivity.this, WorkoutPlanCreationActivity.class);
        goBack.putExtra("personalWorkoutPlan", personalWorkoutPlan);
        goBack.putExtra("data", data);
        startActivity(goBack);
        return true;
    }

    private void setData() {
        exerciseNameLabel.setText(exerciseOfPlan.getExercise().getName());

        repsOrTimePLain.setText(String.valueOf(exerciseOfPlan.getRepsOrTime()));
        setsPlain.setText(String.valueOf(exerciseOfPlan.getSets()));
        restTimePlain.setText(String.valueOf(exerciseOfPlan.getRest()));

        switch (exerciseOfPlan.getExerciseUnity()) {
            case Reps: repsRadioButton.toggle();break;
            case Minutes: minutesRadioButton.toggle();break;
            default: secondsRadioButton.toggle();;break;
        }

        if (exerciseOfPlan.getRestUnity() == RestUnity.Minutes)
            minutesRadioButton2.toggle();
        else
            secondsRadioButton2.toggle();
    }

    private void editExerciseOfPlan() {
        int sets = Integer.parseInt(setsPlain.getText().toString());

        int repsOrTime = Integer.parseInt(repsOrTimePLain.getText().toString());

        int restTime = Integer.parseInt(restTimePlain.getText().toString());

        ExerciseUnity exerciseUnity;
        int selectedId = repsOrTimeRadioGroup.getCheckedRadioButtonId();
        RadioButton repsOrTimeRadioButton = findViewById(selectedId);
        switch (repsOrTimeRadioButton.getText().toString()) {
            case "Ripetizioni": exerciseUnity = ExerciseUnity.Reps;break;
            case "Minuti": exerciseUnity = ExerciseUnity.Minutes;break;
            default: exerciseUnity = ExerciseUnity.Seconds;break;
        }

        RestUnity restUnity;
        selectedId = restTimeRadioGroup.getCheckedRadioButtonId();
        RadioButton restTimeRadioButton = findViewById(selectedId);
        if (restTimeRadioButton.getText().toString().equals("Minuti"))
            restUnity = RestUnity.Minutes;
        else
            restUnity = RestUnity.Seconds;

        personalWorkoutPlan.editExercise(new ExerciseOfPlan(exerciseOfPlan.getExercise(), sets, repsOrTime, restTime, exerciseUnity, restUnity));
    }

    public boolean errorCheck(){
        boolean error=false;

        if(repsOrTimeRadioGroup.getCheckedRadioButtonId()==-1){
            error=true;
            repsOrTimePLain.setError("Attenzione! Campo mancante o tipologia non selezionta");
        }

        if(restTimeRadioGroup.getCheckedRadioButtonId()==-1){
            error=true;
            restTimePlain.setError("Attenzione! Campo mancante o tipologia non selezionata");
        }

        if(setsPlain.getText().toString().isEmpty()){
            error=true;
            setsPlain.setError("Attenzione! Numero di serie mancate");
        }


        return error;
    }

}