package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class WorkoutPlanOverviewActivity extends AppCompatActivity implements RecyclerViewInterface{

    Toolbar myToolbar;
    TextView nome;
    Button startWorkout;
    Workout workout, workoutCopy;
    RecyclerView exerciseRecycler;
    WorkoutExerciseStateAdapter adapter;
    Data data;
    WorkoutPlan workoutPlan;
    ExerciseOfPlan exerciseOfPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan_overview);
        myToolbar = findViewById(R.id.myToolbarAllenamento);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Vista scheda");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        nome = findViewById(R.id.nomeSchedaOverview);
        startWorkout = findViewById(R.id.buttonSchedaOverview);
        exerciseRecycler = findViewById(R.id.recyclerSchedaOverview);
        exerciseRecycler.setHasFixedSize(true);
        exerciseRecycler.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        Serializable workoutPlanObject = intent.getSerializableExtra("workoutPlan");
        Serializable workoutObject = intent.getSerializableExtra("workout");
        Serializable firstStart = intent.getSerializableExtra("firstStart");
        Serializable exerciseOfPlanObject = intent.getSerializableExtra("exerciseOfPlan");
        Serializable object = intent.getSerializableExtra("data");
        if (workoutPlanObject != null) {
            workoutPlan = (WorkoutPlan) workoutPlanObject;
            workout = workoutPlan.createWorkout();
        }
        else
            workout = (Workout) workoutObject;
        if (firstStart != null)
            startWorkout.setText("Inizia allenamento");
        if (exerciseOfPlanObject != null)
            exerciseOfPlan = (ExerciseOfPlan) exerciseOfPlanObject;
        else
            exerciseOfPlan = workout.getWorkoutPlan().getExerciseList().get(0);
        if (object != null) {
            data = (Data) object;
        }

        workoutCopy = workout;
        adapter = new WorkoutExerciseStateAdapter((ArrayList<ExerciseState>) workout.getWorkoutState(), this, this,true);
        exerciseRecycler.setAdapter(adapter);

        nome.setText(workout.getWorkoutPlan().getName());

        startWorkout.setOnClickListener(v -> {
            Intent startResume;
            if (exerciseOfPlan.getExerciseUnity() == ExerciseUnity.Reps)
                startResume = new Intent(WorkoutPlanOverviewActivity.this, WorkoutWithRepsActivity.class);
            else
                startResume = new Intent(WorkoutPlanOverviewActivity.this, WorkoutWithTimeActivity.class);
            startResume.putExtra("workout", workout);
            startResume.putExtra("exerciseOfPlan", exerciseOfPlan);
            startResume.putExtra("data", data);
            startActivity(startResume);
        });
    }


    @Override
    public void onItemClick(int position) {
        Intent intent;
        ExerciseOfPlan exerciseOfPlan = workout.getWorkoutState().get(position).getExerciseOfPlan();
        if(workout.getWorkoutState().get(position).getExerciseOfPlan().getExerciseUnity()==ExerciseUnity.Reps){
            intent = new Intent(WorkoutPlanOverviewActivity.this,WorkoutWithRepsActivity.class);

        }
        else {
            intent = new Intent(WorkoutPlanOverviewActivity.this,WorkoutWithTimeActivity.class);
        }
        intent.putExtra("workout", workout);
        intent.putExtra("exerciseOfPlan", exerciseOfPlan);
        intent.putExtra("data", data);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent goBack = new Intent(WorkoutPlanOverviewActivity.this, WorkoutPlanSelectionActivity.class);
        data.addWorkout(workout);
        goBack.putExtra("data", data);
        startActivity(goBack);
        return true;
    }

}