package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Data data = new Data();
        Exercise planche = new Exercise("Planche",BodyPart.Shoulders, Difficulty.Hard,"Innanzitutto, la posizione corretta dei gomiti durante la planche è in blocco articolare. Per quanto riguarda la posizione delle scapole, esse dovranno essere protratte e depresse. Il consiglio è quello di pensare pensare prima di tutto a spingersi via dal pavimento a gomiti tesi allontanando le scapole tra loro. Il secondo passo, è quello di allontanare le spalle dalle orecchie, ricercando una stabilizzazione dal gran dorsale;La posizione corretta di planche è in spinta. Il bacino si porta in retroversione e bisogna contrarre i glutei, pensa ad estendere le anche e concentrati più sulla messa in tensione della parete addominale data dal trasverso dell’addome ");
        data.addPredefinedExercise(planche);



        }

}