package com.example.yourcali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class WorkoutPlansHistoryActivity extends AppCompatActivity implements RecyclerViewInterface {

    Toolbar myToolbar;
    ArrayList<Workout> workoutListCopy;
    WorkoutAdapter adapter;
    RecyclerView workoutRecycler;
    TextView emptyRecycler;
    Button deleteListButton;

    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plans_history);
        myToolbar = findViewById(R.id.myToolbarStorico);
        workoutRecycler = findViewById(R.id.listaSchedeStorico);
        emptyRecycler = findViewById(R.id.emptyhistory);
        deleteListButton = findViewById(R.id.deleteListButton);
        workoutRecycler.setHasFixedSize(true);
        workoutRecycler.setLayoutManager(new LinearLayoutManager(this));

        MoveLists();

        isEmpty();

        workoutListCopy = new ArrayList<>(data.getWorkoutList());
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(workoutRecycler.getContext(), DividerItemDecoration.VERTICAL);
        workoutRecycler.addItemDecoration(mDividerItemDecoration);
        adapter = new WorkoutAdapter(workoutListCopy,this,this);
        workoutRecycler.setAdapter(adapter);

        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Storico allenamenti");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        deleteListButton.setOnClickListener(v -> {
            alertBox();
        });

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
        intent.putExtra("HistoryOverview", data.getWorkoutList().get(position));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem mSearch = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) mSearch.getActionView();
        searchView.setQueryHint("Cerca scheda");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                adapter.getFilter().filter(query);
                return false;
            }
        });

        return true;
    }

    private void isEmpty() {
        if (!data.getWorkoutList().isEmpty()){
            emptyRecycler.setVisibility(View.GONE);
            workoutRecycler.setVisibility(View.VISIBLE);
            deleteListButton.setVisibility(View.VISIBLE);
        }
        else {
            emptyRecycler.setVisibility(View.VISIBLE);
            workoutRecycler.setVisibility(View.GONE);
            deleteListButton.setVisibility(View.GONE);
        }
    }

    public void alertBox(){
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("ATTENZIONE");
        alert.setMessage("Sei sicuro di voler eliminare il contenuto dello storico? Perderai tutti i dati");
        alert.setButton(AlertDialog.BUTTON_POSITIVE, "Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                data.resetWorkout();
                isEmpty();
            }
        });
        alert.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#FD813C"));
                alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#FD813C"));
            }
        });
        alert.show();
    }

}