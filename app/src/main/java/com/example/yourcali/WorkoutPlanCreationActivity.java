package com.example.yourcali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class WorkoutPlanCreationActivity extends AppCompatActivity {

    Data data;

    RecyclerView recyclerView;
    Toolbar myToolbar;
    ArrayList<Exercise> eserciziScheda = new ArrayList<>();
    ArrayList<Exercise> eserciziSchedaCopia;
    private ListAdapter adapter;
    Exercise exercise;
    Exercise primo, secondo, terzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan_creation);

        Intent intent = getIntent();
        Serializable object = intent.getSerializableExtra("data");
        if (object != null) {
            data = (Data) object;
        }

        myToolbar = findViewById(R.id.myToolbar);
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


        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Creazione scheda");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent goBack = new Intent(WorkoutPlanCreationActivity.this, WorkoutPlanSelectionActivity.class);
        goBack.putExtra("data", data);
        startActivity(goBack);
        return true;
    }

}