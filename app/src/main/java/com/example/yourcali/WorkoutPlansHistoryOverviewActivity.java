package com.example.yourcali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

public class WorkoutPlansHistoryOverviewActivity extends AppCompatActivity implements RecyclerViewInterface {

    Data data;
    Toolbar myToolbar;
    Workout workout, workoutCopy;
    WorkoutExerciseStateAdapter adapter;
    TextView nome;
    TextView date;
    TextView orario;
    TextView completamento;
    RecyclerView exerciseRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plans_history_overview);
        myToolbar = findViewById(R.id.myToolbarStorico);
        nome=findViewById(R.id.nomeSchedaStoricoPanoramica);
        date =findViewById(R.id.dataStoricoPanoramica);
        orario=findViewById(R.id.orarioStoricoPanoramica);
        completamento=findViewById(R.id.completamentoStoricoPanoramica);
        exerciseRecycler=findViewById(R.id.listaEserciziStorico);
        exerciseRecycler.setHasFixedSize(true);
        exerciseRecycler.setLayoutManager(new LinearLayoutManager(this));

        Moveworkout();

        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Vista generale");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        workoutCopy = workout;
        adapter = new WorkoutExerciseStateAdapter((ArrayList<ExerciseState>) workout.getWorkoutState(), this,this,false);
        exerciseRecycler.setAdapter(adapter);

        nome.setText(workout.getWorkoutPlan().getName());
        SimpleDateFormat date=new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat time=new SimpleDateFormat("HH:mm:ss");
        this.date.setText("Data: " + date.format(workout.getStartingTime()));
        orario.setText("Orario inizio: " + time.format(workout.getStartingTime()));
        completamento.setText("Completamento: " + workout.calculatePercentage()+ "%");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent goBack = new Intent(WorkoutPlansHistoryOverviewActivity.this, WorkoutPlansHistoryActivity.class);
        goBack.putExtra("data", data);
        startActivity(goBack);
        return true;
    }

    public void Moveworkout(){
        Intent intent = getIntent();
        Serializable workoutObject = intent.getSerializableExtra("HistoryOverview");
        Serializable dataObject = intent.getSerializableExtra("data");
        if (workoutObject != null){
            workout = (Workout) workoutObject;
        }
        if (workoutObject != null){
            data = (Data) dataObject;
        }
    }

    @Override
    public void onItemClick(int position) {
        //NULLA
    }
}