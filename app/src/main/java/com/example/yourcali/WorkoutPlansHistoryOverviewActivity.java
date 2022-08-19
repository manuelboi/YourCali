package com.example.yourcali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;

public class WorkoutPlansHistoryOverviewActivity extends AppCompatActivity {

    Toolbar myToolbar;
    Training training,trainingCopy;
    ListAdapter adapter;
    TextView nome;
    TextView data;
    TextView orario;
    TextView completamento;
    RecyclerView exerciseRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plans_history_overview);
        myToolbar = findViewById(R.id.myToolbarStorico);
        nome=findViewById(R.id.nomeSchedaStoricoPanoramica);
        data=findViewById(R.id.dataStoricoPanoramica);
        orario=findViewById(R.id.orarioStoricoPanoramica);
        completamento=findViewById(R.id.completamentoStoricoPanoramica);
        exerciseRecycler=findViewById(R.id.listaEserciziStorico);
        exerciseRecycler.setHasFixedSize(true);
        exerciseRecycler.setLayoutManager(new LinearLayoutManager(this));

        MoveTraining();

        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Storico Schede");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        trainingCopy=training;
        //adapter=new ListAdapter(training.workoutPlan.exerciseList);
        exerciseRecycler.setAdapter(adapter);

        nome.setText(training.workoutPlan.getName());
        SimpleDateFormat date=new SimpleDateFormat("dd/mm//yyyy");
        SimpleDateFormat time=new SimpleDateFormat("HH:mm:ss");
        data.setText(date.format(training.getStartingTime()));
        orario.setText(time.format(training.getStartingTime()));
        completamento.setText(training.calculatePercentage()+ "%");

    }

    public void MoveTraining(){
        Intent intent=getIntent();
        Serializable object=intent.getSerializableExtra("HistoryOverview");

        if (object!=null){
            training = (Training) object;
        }
    }
}