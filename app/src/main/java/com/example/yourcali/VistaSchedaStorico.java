package com.example.yourcali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class VistaSchedaStorico extends AppCompatActivity {

    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_scheda_storico);
        myToolbar = findViewById(R.id.myToolbarStorico);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Storico Schede");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}