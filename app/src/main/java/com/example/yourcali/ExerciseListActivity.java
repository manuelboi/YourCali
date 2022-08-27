package com.example.yourcali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class ExerciseListActivity extends AppCompatActivity implements RecyclerViewPersonalInterface{

    Data data;
    PersonalWorkoutPlan personalWorkoutPlan;

    RecyclerView recyclerView;
    Toolbar myToolbar;
    ArrayList<Exercise> eserciziScheda;
    ArrayList<Exercise> eserciziSchedaCopia;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);

        Intent intent = getIntent();
        Serializable object = intent.getSerializableExtra("data");
        Serializable workoutPlanObject = intent.getSerializableExtra("personalWorkoutPlan");
        if (object != null) {
            data = (Data) object;
        }
        if (workoutPlanObject != null)
            personalWorkoutPlan = (PersonalWorkoutPlan) workoutPlanObject;

        myToolbar = findViewById(R.id.myToolbar);

        eserciziScheda = new ArrayList<>(data.getPredefinedExercises());
        eserciziScheda.addAll(data.getPersonalExercises());
        eserciziScheda.sort(Comparator.comparing(Exercise::getName));

        recyclerView = findViewById(R.id.recylcerView);
        eserciziSchedaCopia = new ArrayList<>(eserciziScheda);  // Viene passato all'adapter una copia della lista che poi sarà eventualmente filtrata
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(mDividerItemDecoration);
        adapter = new ListAdapter(eserciziScheda, 2);
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
        goBack.putExtra("data", data);
        goBack.putExtra("personalWorkoutPlan", personalWorkoutPlan);
        startActivity(goBack);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem mSearch = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) mSearch.getActionView();
        searchView.setQueryHint("Cerca esercizio");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                adapter.getFilter().filter(query);
                return false;
            }
        });

        return true;
    }

    @Override
    public void onItemClick(int position){
        Intent intent = new Intent(ExerciseListActivity.this, ExerciseInfoEncyclopediaActivity.class);
        intent.putExtra("exercise", data.getPersonalExercises().get(position));
        intent.putExtra("firstStart", true);
        intent.putExtra("data", data);
        startActivity(intent);
    }

    @Override
    public void onItemClickModifiedButton(int position){

    }

    @Override
    public void onItemClickDeleteButton(int position){

    }

}