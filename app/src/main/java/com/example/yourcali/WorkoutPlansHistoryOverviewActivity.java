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

public class WorkoutPlansHistoryOverviewActivity extends AppCompatActivity {

    Data data;
    Toolbar myToolbar;
    Training training,trainingCopy;
    ListAdapter adapter;
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

        MoveTraining();

        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Storico Schede");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        trainingCopy = training;
        //adapter=new ListAdapter(training.workoutPlan.exerciseList);
        exerciseRecycler.setAdapter(adapter);

        nome.setText(training.workoutPlan.getName());
        SimpleDateFormat date=new SimpleDateFormat("dd/MM//yyyy");
        SimpleDateFormat time=new SimpleDateFormat("HH:mm:ss");
        this.date.setText(date.format(training.getStartingTime()));
        orario.setText(time.format(training.getStartingTime()));
        completamento.setText(training.calculatePercentage()+ "%");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent goBack = new Intent(WorkoutPlansHistoryOverviewActivity.this, WorkoutPlansHistoryActivity.class);
        goBack.putExtra("data", data);
        startActivity(goBack);
        return true;
    }

    public void MoveTraining(){
        Intent intent = getIntent();
        Serializable trainingObject = intent.getSerializableExtra("HistoryOverview");
        Serializable dataObject = intent.getSerializableExtra("data");
        if (trainingObject != null){
            training = (Training) trainingObject;
        }
        if (trainingObject != null){
            data = (Data) dataObject;
        }
    }

}