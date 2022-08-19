package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ImageButton trainingButton, exercisesEncyclopediaButton, workoutPlansHistoryButton;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        Serializable object = intent.getSerializableExtra("data");
        if (object != null) {
            data = (Data) object;
        }
        else
        {
            // Application data initializing
            data = new Data();

            // Esercise creation
            Exercise planche = new Exercise("Planche",BodyPart.Shoulders, Difficulty.Hard,"Innanzitutto, la posizione corretta dei gomiti durante la planche è in blocco articolare. Per quanto riguarda la posizione delle scapole, esse dovranno essere protratte e depresse. Il consiglio è quello di pensare pensare prima di tutto a spingersi via dal pavimento a gomiti tesi allontanando le scapole tra loro. Il secondo passo, è quello di allontanare le spalle dalle orecchie, ricercando una stabilizzazione dal gran dorsale;La posizione corretta di planche è in spinta. Il bacino si porta in retroversione e bisogna contrarre i glutei, pensa ad estendere le anche e concentrati più sulla messa in tensione della parete addominale data dal trasverso dell’addome ");

            // Exercise adding
            data.addPredefinedExercise(planche);



            // TEST ITEMS (WILL BE REMOVED SOON)
            ExerciseOfPlan exerciseOfPlan = new ExerciseOfPlan(planche, 2, 2, 2, ExerciseUnity.Reps, RestUnity.Minutes);
            List<ExerciseOfPlan> exerciseList = new ArrayList<>();
            exerciseList.add(exerciseOfPlan);
            WorkoutPlan workoutPlan = new WorkoutPlan("esercizio", "descrizione", exerciseList);
            ExerciseState exerciseState = new ExerciseState(exerciseOfPlan, true);
            List<ExerciseState> exerciseStateList = new ArrayList<>();
            exerciseStateList.add(exerciseState);
            Workout workout = new Workout(workoutPlan, exerciseStateList);
            ArrayList<Workout> workoutList = new ArrayList<>();
            data.addTraining(workout);
            // END OF TEST ITEMS
        }

        // Variables link
        trainingButton = findViewById(R.id.trainingButton);
        exercisesEncyclopediaButton = findViewById(R.id.exercisesEncyclopediaButton);
        workoutPlansHistoryButton = findViewById(R.id.trainingHistoryButton);

        // Set click listener
        trainingButton.setOnClickListener(v -> {
            Intent openWorkoutPlanSelection = new Intent(HomeActivity.this, WorkoutPlanSelectionActivity.class);
            startActivity(openWorkoutPlanSelection);
        });

        exercisesEncyclopediaButton.setOnClickListener(v -> {
            Intent openExercisesEncyclopedia = new Intent(HomeActivity.this, ExercisesEncyclopedia.class);
            startActivity(openExercisesEncyclopedia);
        });

        workoutPlansHistoryButton.setOnClickListener(v -> {
            Intent openWorkoutPlansHistory = new Intent(HomeActivity.this, WorkoutPlansHistoryActivity.class);
            openWorkoutPlansHistory.putExtra("data", data);
            startActivity(openWorkoutPlansHistory);
        });
        }

}