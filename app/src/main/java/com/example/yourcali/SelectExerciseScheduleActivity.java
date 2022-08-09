package com.example.yourcali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectExerciseScheduleActivity extends AppCompatActivity {
    Toolbar myToolbar;
    Button bottone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_exercise_schedule);
        myToolbar = findViewById(R.id.myToolbar);
        bottone = findViewById(R.id.button);

        setSupportActionBar(myToolbar);
        bottone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showResult = new Intent(SelectExerciseScheduleActivity.this, CreateExerciseSchedule.class);
                startActivity(showResult);

            }
        });

    }
}