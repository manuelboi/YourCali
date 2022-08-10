package com.example.yourcali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class VistaSchedaAllenamento extends AppCompatActivity {

    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_scheda_allenamento);
        myToolbar = findViewById(R.id.myToolbarStorico);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Scheda Varia");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}