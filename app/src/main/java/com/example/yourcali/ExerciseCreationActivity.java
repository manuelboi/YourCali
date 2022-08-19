package com.example.yourcali;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class ExerciseCreationActivity extends AppCompatActivity {

    private Spinner spinnerDifficolta;
    private Spinner spinnerZona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);

        Button gallery = findViewById(R.id.gallery);
        gallery.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 3);
        });

        spinnerDifficolta = findViewById(R.id.spinnerDifficolta);
        String[] difficoltaEs = getResources().getStringArray(R.array.difficolta);
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, difficoltaEs);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficolta.setAdapter(adapter1);

        spinnerZona = findViewById(R.id.spinnerZona);
        String[] zonaEs = getResources().getStringArray(R.array.zona);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, zonaEs);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerZona.setAdapter(adapter2);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data!=null){
         Uri selectedImage = data.getData();
         ImageView imageView = findViewById(R.id.imageView);
         imageView.setImageURI(selectedImage);
        }
    }

}