package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class WorkoutEndActivity extends AppCompatActivity {

    Button goBackHomeButton;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_end);

        Intent intent = getIntent();
        Serializable dataObject = intent.getSerializableExtra("data");
        if (dataObject != null)
            data = (Data) dataObject;

        goBackHomeButton = findViewById(R.id.goBackHomeButton);
        goBackHomeButton.setOnClickListener(v -> {
            Intent goBackHome = new Intent(WorkoutEndActivity.this, HomeActivity.class);
            goBackHome.putExtra("data", data);
            startActivity(goBackHome);
        });
    }

}