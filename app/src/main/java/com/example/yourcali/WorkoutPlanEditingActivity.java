package com.example.yourcali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class WorkoutPlanEditingActivity extends AppCompatActivity implements RecyclerViewPersonalInterface {

    Data data;
    PersonalWorkoutPlan personalWorkoutPlan;
    PersonalWorkoutPlan personalWorkoutPlanCopy;

    RecyclerView recyclerView;
    Toolbar myToolbar;
    Button savePersonalWorkoutPlanButton;
    ImageButton aggiungiEsercizio;
    ArrayList<ExerciseOfPlan> eserciziSchedaCopia;
    EditText workoutPlanName;
    TextView emptyRecycler;
    ComeFrom2 comeFrom2 = ComeFrom2.WorkoutPlanEditing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan_editing);

        myToolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Modifica scheda");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        aggiungiEsercizio = findViewById(R.id.aggiungiEsercizio);
        workoutPlanName = findViewById(R.id.workoutPlanName);
        emptyRecycler = findViewById(R.id.emptyworkoutplancreation);
        savePersonalWorkoutPlanButton = findViewById(R.id.savePersonalWorkoutPlanButton);
        recyclerView = findViewById(R.id.recyclerView);

        Intent intent = getIntent();
        Serializable object = intent.getSerializableExtra("data");
        Serializable personalWorkoutPlanObject = intent.getSerializableExtra("personalWorkoutPlan");
        if (object != null) {
            data = (Data) object;
        }
        // If we are coming back from exercise adding or configuration activities
        if (personalWorkoutPlanObject != null)  {
            personalWorkoutPlan = (PersonalWorkoutPlan) personalWorkoutPlanObject;
            personalWorkoutPlanCopy = new PersonalWorkoutPlan(personalWorkoutPlan.getName(), personalWorkoutPlan.getExerciseList());
            setData();
        }
        else {
            ArrayList<ExerciseOfPlan> exerciseList = new ArrayList<>();
            personalWorkoutPlan = new PersonalWorkoutPlan("", exerciseList);
        }

        workoutPlanName.setText(personalWorkoutPlan.getName());

        eserciziSchedaCopia = new ArrayList<>(personalWorkoutPlan.getExerciseList());  // Viene passato all'adapter una copia della lista che poi sarà eventualmente filtrata
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(mDividerItemDecoration);
        WorkoutPlanCreationAdapter adapter = new WorkoutPlanCreationAdapter(eserciziSchedaCopia, this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        checkEmpty();

        aggiungiEsercizio.setOnClickListener(v -> {
            updateWorkoutPlan();
            Intent addExcercise = new Intent(WorkoutPlanEditingActivity.this, ExerciseListActivity.class);
            addExcercise.putExtra("data", data);
            addExcercise.putExtra("personalWorkoutPlan", personalWorkoutPlan);
            addExcercise.putExtra("comeFrom2", comeFrom2);
            startActivity(addExcercise);
        });

        savePersonalWorkoutPlanButton.setOnClickListener(v -> {
            if (!errorCheck()) {
                updateWorkoutPlan();
                data.editPersonalWorkoutPlan(personalWorkoutPlanCopy, personalWorkoutPlan);
                goBack();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        alertBox();
        return true;
    }

    @Override
    public void onItemClick(int position){
        updateWorkoutPlan();
        Intent intent = new Intent(WorkoutPlanEditingActivity.this, ExerciseConfigurationActivity.class);
        intent.putExtra("exerciseOfPlan", personalWorkoutPlan.getExerciseList().get(position));
        intent.putExtra("personalWorkoutPlan", personalWorkoutPlan);
        intent.putExtra("data", data);
        intent.putExtra("comeFrom2", comeFrom2);
        startActivity(intent);
    }

    public void onItemClickDeleteButton(int position){
        personalWorkoutPlan.removeExercise(personalWorkoutPlan.exerciseList.get(position));
        checkEmpty();
    }

    public void onItemClickModifiedButton(int position){
        Intent modifiedExerciseIntent = new Intent(WorkoutPlanEditingActivity.this, ExerciseConfigurationEditingActivity.class);
        modifiedExerciseIntent.putExtra("comeFrom2", comeFrom2);
        modifiedExerciseIntent.putExtra("data", data);
        modifiedExerciseIntent.putExtra("personalWorkoutPlan", personalWorkoutPlan);
        modifiedExerciseIntent.putExtra("exercise", personalWorkoutPlan.getExerciseList().get(position));
        startActivity(modifiedExerciseIntent);
    }

    void setData() {
        workoutPlanName.setText(personalWorkoutPlan.name);
    }

    private void goBack() {
        Intent goBack = new Intent(WorkoutPlanEditingActivity.this, WorkoutPlanSelectionActivity.class);
        goBack.putExtra("data", data);
        startActivity(goBack);
    }

    private boolean errorCheck(){
        boolean error = false;

        if(workoutPlanName.getText().toString().length()==0){
            error = true;
            workoutPlanName.setError("Attenzione, nome scheda mancante");
        }

        if(personalWorkoutPlan.getExerciseList().isEmpty()){
            error = true;
            displayToast();
        }
        return error;
    }

    private void checkEmpty () {
        if (!personalWorkoutPlan.getExerciseList().isEmpty()){
            emptyRecycler.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        else {
            emptyRecycler.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    private void displayToast(){
        Toast toast = Toast.makeText(WorkoutPlanEditingActivity.this, "Inserire almeno un esercizio prima di salvare!", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 500);
        View view = toast.getView();
        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(Color.RED);
        toast.show();
    }

    private void updateWorkoutPlan () {
        personalWorkoutPlan.setName(workoutPlanName.getText().toString());
    }

    public void alertBox(){
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("ATTENZIONE");
        alert.setMessage("Vuoi tornare indietro? Perderai tutti i dati non salvati");
        alert.setButton(AlertDialog.BUTTON_POSITIVE, "Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                goBack();
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