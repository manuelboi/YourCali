package com.example.yourcali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class WorkoutPlansHistoryActivity extends AppCompatActivity implements RecyclerViewInterface {

    Toolbar myToolbar;
    ArrayList<Workout> workoutListCopy;
    WorkoutAdapter adapter;
    RecyclerView workoutRecycler;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plans_history);
        myToolbar = findViewById(R.id.myToolbarStorico);
        workoutRecycler=findViewById(R.id.listaSchedeStorico);
        workoutRecycler.setHasFixedSize(true);
        workoutRecycler.setLayoutManager(new LinearLayoutManager(this));

        MoveLists();

        workoutListCopy = new ArrayList<>(data.getworkoutList());
        adapter = new WorkoutAdapter(workoutListCopy,this,this);
        workoutRecycler.setAdapter(adapter);

        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Storico Schede");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void MoveLists() {
        Intent intent = getIntent();
        Serializable object = intent.getSerializableExtra("data");
        if (object != null) {
            data = (Data) object;
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(WorkoutPlansHistoryActivity.this,WorkoutPlansHistoryOverviewActivity.class);
        intent.putExtra("HistoryOverview", data.getworkoutList().get(position));
        intent.putExtra("data", data);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent goBack = new Intent(WorkoutPlansHistoryActivity.this, HomeActivity.class);
        goBack.putExtra("data", data);
        startActivity(goBack);
        return true;
    }

}