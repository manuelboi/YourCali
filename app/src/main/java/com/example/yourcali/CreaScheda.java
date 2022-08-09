package com.example.yourcali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class CreaScheda extends AppCompatActivity {
    RecyclerView recyclerView;
    Toolbar myToolbar;
    ArrayList<Esercizio> eserciziScheda = new ArrayList<>();
    ArrayList<Esercizio> eserciziSchedaCopia;
    private Adapter adapter;
    Esercizio esercizio;
    Esercizio primo, secondo, terzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_scheda);
        myToolbar = findViewById(R.id.myToolbar);
        primo = new Esercizio("pull-up", 10, 5);
        secondo = new Esercizio("push-up", 15, 5);
        terzo = new Esercizio("diamond push-up", 7, 5);
        eserciziScheda.add(primo);
        eserciziScheda.add(secondo);
        eserciziScheda.add(terzo);


        recyclerView = findViewById(R.id.recylcerView);

        eserciziSchedaCopia = new ArrayList<>(eserciziScheda);  // Viene passato all'adapter una copia della lista che poi sarà eventualmente filtrata
        adapter = new Adapter(eserciziSchedaCopia, esercizio);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Crea Scheda");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}