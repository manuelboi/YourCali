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

public class WorkoutPlanOverviewActivity extends AppCompatActivity implements RecyclerViewInterface{

    TextView nome;
    Button riprendi;
    Workout workout, workoutCopy;
    RecyclerView exerciseRecycler;
    WorkoutExerciseStateAdapter adapter;
    Data data;
    PredefinedWorkoutPlan predefinedWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan_overview);
        nome=findViewById(R.id.nomeSchedaOverview);
        riprendi=findViewById(R.id.buttonSchedaOverview);
        exerciseRecycler=findViewById(R.id.recyclerSchedaOverview);
        exerciseRecycler.setHasFixedSize(true);
        exerciseRecycler.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        Serializable predefinedWorkoutObject = intent.getSerializableExtra("PredefinedWorkoutOverview");
        Serializable object = intent.getSerializableExtra("data");
        if (object != null) {
            data = (Data) object;
        }
        if(predefinedWorkoutObject != null) {
            predefinedWorkout = (PredefinedWorkoutPlan) predefinedWorkoutObject;
        }

        workout = (Workout) predefinedWorkout.createWorkout();


        workoutCopy = workout;
        adapter = new WorkoutExerciseStateAdapter((ArrayList<ExerciseState>) workout.getWorkoutState(),this,this);
        exerciseRecycler.setAdapter(adapter);

        nome.setText(workout.getWorkoutPlan().getName());

    }


    @Override
    public void onItemClick(int position) {
        if(workout.getWorkoutState().get(position).getExerciseOfPlan().getExerciseUnity()==ExerciseUnity.Reps){

            Intent intent = new Intent(WorkoutPlanOverviewActivity.this,WorkoutWithRepsActivity.class);
            //intent.putExtra("HistoryOverview", data.getworkoutList().get(position));
            intent.putExtra("data", data);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(WorkoutPlanOverviewActivity.this,WorkoutWithTimeActivity.class);
            //intent.putExtra("HistoryOverview", data.getworkoutList().get(position));
            intent.putExtra("data", data);
            startActivity(intent);
        }

    }
}