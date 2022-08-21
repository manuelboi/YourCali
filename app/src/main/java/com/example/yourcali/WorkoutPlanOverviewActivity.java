package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class WorkoutPlanOverviewActivity extends AppCompatActivity {

    TextView nome;
    Button riprendi;
    Workout workout, workoutCopy;
    RecyclerView exerciseRecycler;
    WorkoutExerciseStateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan_overview);
        nome=findViewById(R.id.nomeSchedaOverview);
        riprendi=findViewById(R.id.buttonSchedaOverview);
        exerciseRecycler=findViewById(R.id.recyclerSchedaOverview);
        exerciseRecycler.setHasFixedSize(true);
        exerciseRecycler.setLayoutManager(new LinearLayoutManager(this));

        MoveWorkout();

        workoutCopy = workout;
        adapter = new WorkoutExerciseStateAdapter((ArrayList<ExerciseState>) workout.getWorkoutState(),this);
        exerciseRecycler.setAdapter(adapter);

        nome.setText(workout.getWorkoutPlan().getName());

    }

    public void MoveWorkout(){
        Intent intent=getIntent();
        Serializable object=intent.getSerializableExtra("HistoryOverview");

        if (object!=null){
            workout = (Workout) object;
        }
    }

}