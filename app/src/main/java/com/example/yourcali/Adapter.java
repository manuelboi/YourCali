package com.example.yourcali;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private final ArrayList<Esercizio> eserciziScheda, eserciziSchedaTutti;
    Esercizio esercizio;

    public Adapter (ArrayList<Esercizio> eserciziScheda, Esercizio esercizioAttuale){  // La lista di utenti e lo user attuale vengono passati con il costruttore
        this.eserciziScheda = eserciziScheda;
        this.esercizio = esercizioAttuale;

        eserciziSchedaTutti = new ArrayList<>();
        eserciziSchedaTutti.addAll(eserciziScheda);
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.esercizio_scheda,parent,false);  // Viene creata la View a partire dal template card_utente.xml
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Esercizio esercizio = eserciziScheda.get(position);
        holder.esercizio = esercizio;
        String name=eserciziScheda.get(position).getNome();
        Integer serie=eserciziScheda.get(position).getSerie();
        Integer ripetizione=eserciziScheda.get(position).getRipetizioni();

        holder.nomeEsercizio.setText(name);
        holder.ripetizioni.setText(String.valueOf(ripetizione));
        holder.serie.setText(String.valueOf(serie));

    }

    @Override
    public int getItemCount() {
        return eserciziScheda.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        Esercizio esercizio;
        TextView nomeEsercizio, serie, ripetizioni;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeEsercizio = itemView.findViewById(R.id.nomeEsercizio);
            serie = itemView.findViewById(R.id.serie);
            ripetizioni = itemView.findViewById(R.id.ripetizioni);

        }
    }
}
