package com.example.yourcali;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class ExerciseCreationActivity extends AppCompatActivity {

    EditText exerciseNamePlain, helpGuidePlain;
    Button gallery, saveExerciseButton;
    Data data;
    Uri selectedImage;
    String imageString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_creation);

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

        Spinner spinnerDifficolta = findViewById(R.id.spinnerDifficolta);
        String[] difficoltaEs = getResources().getStringArray(R.array.difficolta);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, difficoltaEs);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficolta.setAdapter(adapter1);

        Spinner spinnerZona = findViewById(R.id.spinnerZona);
        String[] zonaEs = getResources().getStringArray(R.array.zona);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, zonaEs);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerZona.setAdapter(adapter2);

        exerciseNamePlain = findViewById(R.id.exerciseNamePlain);
        helpGuidePlain = findViewById(R.id.helpGuidePlain);

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

        saveExerciseButton = findViewById(R.id.saveExerciseButton);
        saveExerciseButton.setOnClickListener(v -> {
            String exerciseName = exerciseNamePlain.getText().toString();
            String helpGuide = helpGuidePlain.getText().toString();
            imageString = selectedImage.toString();
            ExercisePersonal exercisePersonal = new ExercisePersonal(exerciseName, bodyPart, difficulty, helpGuide, imageString);
            data.addPersonalExercise(exercisePersonal);
            goBack();
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            selectedImage = data.getData();
            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageURI(selectedImage);
        }
    }

    private void goBack() {
        Intent intent = new Intent(ExerciseCreationActivity.this, ExercisesEncyclopediaActivity.class);
        intent.putExtra("data", data);
        startActivity(intent);
    }

}