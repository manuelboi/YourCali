package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class TrainingEndActivity extends AppCompatActivity {

    Button goBackHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_end);

        goBackHomeButton = findViewById(R.id.goBackHomeButton);
        goBackHomeButton.setOnClickListener(v -> {
            Intent goBackHome = new Intent(TrainingEndActivity.this, HomeActivity.class);
            startActivity(goBackHome);
        });
    }

}