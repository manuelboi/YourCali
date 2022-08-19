package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;

public class WorkoutPlanOverviewActivity extends AppCompatActivity {

    TextView nome;
    Button riprendi;
    Training training,trainingCopy;
    RecyclerView exerciseRecycler;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan_overview);
        nome=findViewById(R.id.nomeSchedaOverview);
        riprendi=findViewById(R.id.buttonSchedaOverview);
        exerciseRecycler=findViewById(R.id.recyclerSchedaOverview);
        exerciseRecycler.setHasFixedSize(true);
        exerciseRecycler.setLayoutManager(new LinearLayoutManager(this));

        MoveTraining();

        trainingCopy=training;
        //adapter = new ListAdapter(training.workoutPlan.exerciseList)
        exerciseRecycler.setAdapter(adapter);

        nome.setText(training.workoutPlan.getName());

    }

    public void MoveTraining(){
        Intent intent=getIntent();
        Serializable object=intent.getSerializableExtra("HistoryOverview");

        if (object!=null){
            training = (Training) object;
        }
    }

}