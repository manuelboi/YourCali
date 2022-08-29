package com.example.yourcali;

import static com.example.yourcali.BodyPart.*;
import static com.example.yourcali.BodyPart.Arms;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.Serializable;
import java.util.Objects;

public class ExerciseEditingActivity extends AppCompatActivity {

    Toolbar myToolbar;
    EditText exerciseNamePlain, helpGuidePlain;
    Button gallery, saveExerciseButton;
    Spinner spinnerZona, spinnerDifficolta;
    Data data;
    Uri selectedImage;
    Exercise exercise;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_editing);
        myToolbar = findViewById(R.id.exerciseEditingToolbar);
        imageView = findViewById(R.id.imageView);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Modifica esercizio");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent getData = getIntent();
        Serializable object = getData.getSerializableExtra("data");
        Serializable exerciseObject = getData.getSerializableExtra("exercise");
        if (object != null) {
            data = (Data) object;
        }
        if (exerciseObject != null) {
            exercise = (Exercise) exerciseObject;
        }

        gallery = findViewById(R.id.gallery);
        gallery.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 3);
        });

        spinnerZona = findViewById(R.id.spinnerZona);
        String[] zonaEs = getResources().getStringArray(R.array.zona);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, zonaEs);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerZona.setAdapter(adapter2);

        spinnerDifficolta = findViewById(R.id.spinnerDifficolta);
        String[] difficoltaEs = getResources().getStringArray(R.array.difficolta);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, difficoltaEs);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficolta.setAdapter(adapter1);

        exerciseNamePlain = findViewById(R.id.exerciseNamePlain);
        helpGuidePlain = findViewById(R.id.helpGuidePlain);

        exerciseNamePlain.setText(exercise.getName());
        helpGuidePlain.setText(exercise.getHelpGuide());

        setData();

        saveExerciseButton = findViewById(R.id.saveExerciseButton);
        saveExerciseButton.setOnClickListener(v -> {
            if(!errorCheck()){
                editExercise();
                goBack();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            selectedImage = data.getData();
            imageView.setImageURI(selectedImage);
        }
    }

    private void setData() {
        exerciseNamePlain.setText(exercise.getName());

        int index;
        switch (exercise.getBodyPart()) {
            case Arms: index = 0;break;
            case Shoulders: index = 1;break;
            case Chest: index = 2;break;
            case Abs: index = 3 ;break;
            case Back: index = 4 ;break;
            default: index = 5;break;
        }
        spinnerZona.setSelection(index);

        switch (exercise.getDifficulty()) {
            case Easy: index = 0;break;
            case Medium: index = 1;break;
            default: index = 2;break;
        }
        spinnerDifficolta.setSelection(index);

        helpGuidePlain.setText(exercise.getHelpGuide());

        selectedImage = Uri.parse(exercise.getImage());
        imageView.setImageURI(selectedImage);

    }

    private void editExercise() {
        String exerciseName = exerciseNamePlain.getText().toString();

        String bodyPartString = spinnerZona.getSelectedItem().toString();
        BodyPart bodyPart;
        switch (bodyPartString) {
            case "Braccia": bodyPart = Arms;break;
            case "Spalle": bodyPart = Shoulders;break;
            case "Petto": bodyPart = Chest;break;
            case "Addominali": bodyPart = Abs;break;
            case "Dorso": bodyPart = Back;break;
            default: bodyPart = Legs;break;
        }

        String difficultyString = spinnerDifficolta.getSelectedItem().toString();
        Difficulty difficulty;
        switch (difficultyString) {
            case "Facile": difficulty = Difficulty.Easy;break;
            case "Intermedio": difficulty = Difficulty.Medium;break;
            default: difficulty = Difficulty.Hard;break;
        }

        String helpGuide = helpGuidePlain.getText().toString();

        String imageString = selectedImage.toString();

        Exercise exercise = new Exercise(exerciseName, bodyPart, difficulty, helpGuide, imageString);
        data.editExercisePersonal(exercise);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        goBack();
        return true;
    }

    private void goBack() {
        Intent intent = new Intent(ExerciseEditingActivity.this, ExercisesEncyclopediaActivity.class);
        intent.putExtra("data", data);
        startActivity(intent);
    }

    public boolean errorCheck(){
        boolean error=false;

        if(exerciseNamePlain.getText().toString().length()==0){
            error=true;
            exerciseNamePlain.setError("Attenzione, nome esercizio mancante");
        }

        if(helpGuidePlain.getText().toString().length()==0){
            error=true;
            helpGuidePlain.setError("Attenzione, descrizione esercizio mancante");
        }
        if(imageView==null){
            error=true;
            //imageError.setVisibility(View.VISIBLE);

        }
        return error;
    }
}