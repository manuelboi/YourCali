package com.example.yourcali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Objects;

public class ExerciseListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Toolbar myToolbar;
    ArrayList<Exercise> eserciziScheda = new ArrayList<>();
    ArrayList<Exercise> eserciziSchedaCopia;
    Exercise primo, secondo, terzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);
        myToolbar = findViewById(R.id.myToolbar);

        // TEST ITEMS
        primo = new Exercise("pull-up", BodyPart.Shoulders, Difficulty.Medium, "");
        secondo = new Exercise("push-up", BodyPart.Arms, Difficulty.Easy, "");
        terzo = new Exercise("diamond push-up", BodyPart.Shoulders, Difficulty.Medium, "");
        eserciziScheda.add(primo);
        eserciziScheda.add(secondo);
        eserciziScheda.add(terzo);
        // END OF TEST ITEMS

        recyclerView = findViewById(R.id.recylcerView);
        eserciziSchedaCopia = new ArrayList<>(eserciziScheda);  // Viene passato all'adapter una copia della lista che poi sarà eventualmente filtrata
        ListAdapter adapter = new ListAdapter(eserciziScheda, 2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Crea Scheda");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent goBack = new Intent(ExerciseListActivity.this, WorkoutPlanCreationActivity.class);
        startActivity(goBack);
        return true;
    }

}