package com.example.yourcali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class WorkoutPlanCreationActivity extends AppCompatActivity {

    Data data;
    PersonalWorkoutPlan personalWorkoutPlan;

    RecyclerView recyclerView;
    Toolbar myToolbar;
    Button aggiungiEsercizio;
    ArrayList<Exercise> eserciziScheda = new ArrayList<>();
    ArrayList<Exercise> eserciziSchedaCopia;
    private ListAdapter adapter;
    Exercise exercise;
    Exercise primo, secondo, terzo;
    EditText workoutPlanName, workoutPlanDescription;
    ArrayList<ExerciseOfPlan> exerciseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan_creation);

        myToolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Creazione scheda");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        aggiungiEsercizio = findViewById(R.id.aggiungiEsercizio);
        workoutPlanName = findViewById(R.id.workoutPlanName);
        workoutPlanDescription = findViewById(R.id.workoutPlanDescription);

        Intent intent = getIntent();
        Serializable object = intent.getSerializableExtra("data");
        Serializable personalWorkoutPlanObject = intent.getSerializableExtra("personalWorkoutPlan");
        if (object != null) {
            data = (Data) object;
        }
        // If we are coming back from exercise adding activity
        if (personalWorkoutPlanObject != null)  {
            personalWorkoutPlan = (PersonalWorkoutPlan) personalWorkoutPlanObject;
            setData();
        }

        /*
        primo = new ExerciseOfPlan("pull-up", 10, 5);
        secondo = new ExerciseOfPlan("push-up", 15, 5);
        terzo = new ExerciseOfPlan("diamond push-up", 7, 5);
        eserciziScheda.add(primo);
        eserciziScheda.add(secondo);
        eserciziScheda.add(terzo);


        recyclerView = findViewById(R.id.recylcerView);

        eserciziSchedaCopia = new ArrayList<>(eserciziScheda);  // Viene passato all'adapter una copia della lista che poi sarà eventualmente filtrata
        adapter = new ListAdapter(eserciziSchedaCopia, exercise);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         */

        aggiungiEsercizio.setOnClickListener(v -> {
            personalWorkoutPlan = new PersonalWorkoutPlan(workoutPlanName.getText().toString(), workoutPlanDescription.getText().toString(), exerciseList);
            Intent addExcercise = new Intent(WorkoutPlanCreationActivity.this, ExerciseListActivity.class);
            addExcercise.putExtra("data", data);
            addExcercise.putExtra("personalWorkoutPlan", personalWorkoutPlan);
            startActivity(addExcercise);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent goBack = new Intent(WorkoutPlanCreationActivity.this, WorkoutPlanSelectionActivity.class);
        goBack.putExtra("data", data);
        startActivity(goBack);
        return true;
    }

    void setData() {
        workoutPlanName.setText(personalWorkoutPlan.name);
        workoutPlanDescription.setText(personalWorkoutPlan.description);
        //exerciseList.addAll(personalWorkoutPlan.exerciseList);
    }

}