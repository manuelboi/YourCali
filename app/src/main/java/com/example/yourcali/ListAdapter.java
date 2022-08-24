package com.example.yourcali;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private final ArrayList<Exercise> eserciziScheda, eserciziSchedaTutti;
    private final Integer tipo;
    Context context;
    Exercise exercise;

    public ListAdapter(ArrayList<Exercise> eserciziScheda, Integer tipo){  // La lista di utenti e lo user attuale vengono passati con il costruttore
        this.eserciziScheda = eserciziScheda;
        this.tipo = tipo;

        eserciziSchedaTutti = new ArrayList<>();
        eserciziSchedaTutti.addAll(eserciziScheda);
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (tipo){
            case 1:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise,parent,false);  // Viene creata la View a partire dal template card_utente.xml
            case 2:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_exercise_workout,parent,false);  // Viene creata la View a partire dal template card_utente.xml
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tipo);
        }
        return new ViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        Exercise exercise;
        String name;
        switch (tipo){
            case 1:
                exercise = eserciziScheda.get(position);
                holder.exercise = exercise;
                name=eserciziScheda.get(position).getName();
                /*
                Integer serie=eserciziScheda.get(position).getSets();
                Integer ripetizione=eserciziScheda.get(position).getReps();
                holder.nomeEsercizio.setText(name);
                holder.ripetizioni.setText(String.valueOf(ripetizione));
                holder.serie.setText(String.valueOf(serie));
                 */
            case 2:
                exercise = eserciziScheda.get(position);
                holder.exercise = exercise;
                name=eserciziScheda.get(position).getName();
                holder.nomeEsercizio.setText(name);

        }

    }

    @Override
    public int getItemCount() {
        return eserciziScheda.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        Exercise exercise;
        TextView nomeEsercizio, serie, ripetizioni;
        View image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            switch (tipo){
                case 1:
                    nomeEsercizio = itemView.findViewById(R.id.nomeEsercizio);
                    serie = itemView.findViewById(R.id.serie);
                    ripetizioni = itemView.findViewById(R.id.ripetizioni);
                case 2:
                    nomeEsercizio = itemView.findViewById(R.id.exerciseName);
                    image = itemView.findViewById(R.id.exerciseImage);
            }
        }
    }

}