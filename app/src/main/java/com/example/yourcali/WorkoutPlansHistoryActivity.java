package com.example.yourcali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;
import java.util.ArrayList;

public class WorkoutPlansHistoryActivity extends AppCompatActivity {

    Toolbar myToolbar;
    ArrayList<Training> trainingList,trainingListCopy;
    TrainingAdapter adapter;
    RecyclerView trainingRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plans_history);
        myToolbar = findViewById(R.id.myToolbarStorico);
        trainingRecycler=findViewById(R.id.listaSchedeStorico);
        trainingRecycler.setHasFixedSize(true);
        trainingRecycler.setLayoutManager(new LinearLayoutManager(this));

        MoveLists();

        trainingListCopy=new ArrayList<>(trainingList);
        adapter=new TrainingAdapter(trainingListCopy,this);
        trainingRecycler.setAdapter(adapter);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Storico Schede");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }



    public void MoveLists() {

        Intent intent = getIntent();
        Serializable object = intent.getSerializableExtra("list");

        if (object != null) {
            trainingList = (ArrayList<Training>) object;
        }
    }
}