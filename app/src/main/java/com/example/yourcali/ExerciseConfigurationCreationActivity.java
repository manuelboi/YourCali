package com.example.yourcali;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.Serializable;
import java.util.Objects;

public class ExerciseConfigurationCreationActivity extends AppCompatActivity {

    Toolbar myToolbar;
    TextView exerciseNameLabel;
    Button saveExerciseConfigurationButton;
    EditText repsOrTimePLain, setsPlain, restTimePlain;
    RadioGroup repsOrTimeRadioGroup;
    RadioGroup restTimeRadioGroup;
    ComeFrom2 comeFrom2;

    Exercise exercise;
    PersonalWorkoutPlan personalWorkoutPlan;
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


        // Get data from intent
        Intent intent = getIntent();
        Serializable exerciseObject = intent.getSerializableExtra("exercise");
        Serializable personaWorkoutPlanObject = intent.getSerializableExtra("personalWorkoutPlan");
        Serializable dataObject = intent.getSerializableExtra("data");
        Serializable comeFromObject = intent.getSerializableExtra("comeFrom2");

        if (exerciseObject != null)
            exercise = (Exercise) exerciseObject;
        if (personaWorkoutPlanObject != null)
            personalWorkoutPlan = (PersonalWorkoutPlan) personaWorkoutPlanObject;
        if (dataObject != null)
            data = (Data) dataObject;
        if (comeFromObject != null)
            comeFrom2 = (ComeFrom2) comeFromObject;

        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Configurazione esercizio");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        exerciseNameLabel.setText(exercise.getName());

        saveExerciseConfigurationButton.setOnClickListener(v -> {
            if(!errorCheck()){
                createExerciseOfPlan();
                Intent addExercise = new Intent(ExerciseConfigurationCreationActivity.this, ExerciseListActivity.class);
                addExercise.putExtra("personalWorkoutPlan", personalWorkoutPlan);
                addExercise.putExtra("data", data);
                addExercise.putExtra("comeFrom2", comeFrom2);
                displayToast(v);
                startActivity(addExercise);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        Intent goBack = new Intent(ExerciseConfigurationCreationActivity.this, ExerciseListActivity.class);
        goBack.putExtra("personalWorkoutPlan", personalWorkoutPlan);
        goBack.putExtra("data", data);
        goBack.putExtra("comeFrom2", comeFrom2);
        startActivity(goBack);
        return true;
    }

    private void createExerciseOfPlan() {
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

        //Se esco e rientro dalla schermata "Aggiunta esercizio" e provo a mettere un nuovo esercizio si rompe
        personalWorkoutPlan.addExercise(new ExerciseOfPlan(exercise, sets, repsOrTime, restTime, exerciseUnity, restUnity));
    }

    public boolean errorCheck(){
        boolean error = false;

        if(repsOrTimeRadioGroup.getCheckedRadioButtonId()==-1){
            error = true;
            repsOrTimePLain.setError("Attenzione! Campo mancante o tipologia non selezionta");
        }

        if(restTimeRadioGroup.getCheckedRadioButtonId()==-1){
            error = true;
            restTimePlain.setError("Attenzione! Campo mancante o tipologia non selezionata");
        }

        if(setsPlain.getText().toString().isEmpty()){
            error = true;
            setsPlain.setError("Attenzione! Numero di serie mancate");
        }
        return error;
    }
    public void displayToast(View v){
        Toast.makeText(ExerciseConfigurationCreationActivity.this, "Esercizio aggiunto", Toast.LENGTH_LONG).show();
    }
}