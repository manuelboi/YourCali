package com.example.yourcali;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.Serializable;
import java.util.Objects;

public class ExerciseCreationActivity extends AppCompatActivity {

    Toolbar myToolbar;
    EditText exerciseNamePlain, helpGuidePlain;
    Button gallery, saveExerciseButton;
    Spinner spinnerZona, spinnerDifficolta;
    Data data;
    ImageView imageView;
    Uri selectedImage;
    TextView imageError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_creation);
        myToolbar = findViewById(R.id.exerciseCreationToolbar);
        imageError = findViewById(R.id.imageError);
        imageView = findViewById(R.id.imageView);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Creazione esercizio");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent getData = getIntent();
        Serializable object = getData.getSerializableExtra("data");
        if (object != null) {
            data = (Data) object;
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

        saveExerciseButton = findViewById(R.id.saveExerciseButton);
        saveExerciseButton.setOnClickListener(v -> {
            if(!errorCheck()){
                data.addPersonalExercise(createExercise());
                displayToast(v);
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

    private Exercise createExercise() {
        String exerciseName = exerciseNamePlain.getText().toString();

        String bodyPartString = spinnerZona.getSelectedItem().toString();
        BodyPart bodyPart;
        switch (bodyPartString) {
            case "Braccia": bodyPart = BodyPart.Arms;break;
            case "Spalle": bodyPart = BodyPart.Shoulders;break;
            case "Petto": bodyPart = BodyPart.Chest;break;
            case "Addominali": bodyPart = BodyPart.Abs;break;
            case "Dorso": bodyPart = BodyPart.Back;break;
            default: bodyPart = BodyPart.Legs;break;
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

        return new Exercise(exerciseName, bodyPart, difficulty, helpGuide, imageString);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        goBack();
        return true;
    }

    private void goBack() {
        Intent intent = new Intent(ExerciseCreationActivity.this, ExercisesEncyclopediaActivity.class);
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
            imageError.setVisibility(View.VISIBLE);

        }

        return error;
    }

    public void displayToast(View v){
        Toast.makeText(ExerciseCreationActivity.this, "Esercizio Creato", Toast.LENGTH_LONG).show();
    }
}