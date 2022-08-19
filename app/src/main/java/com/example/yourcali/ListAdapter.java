package com.example.yourcali;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private final ArrayList<Exercise> eserciziScheda, eserciziSchedaTutti;


    public ListAdapter(ArrayList<Exercise> eserciziScheda){
        this.eserciziScheda = eserciziScheda;

        eserciziSchedaTutti = new ArrayList<>();
        eserciziSchedaTutti.addAll(eserciziScheda);
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise,parent,false);  // Viene creata la View a partire dal template card_utente.xml
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        Exercise exercise = eserciziScheda.get(position);
        holder.exercise = exercise;
        String name=eserciziScheda.get(position).getName();
        /*
        Integer serie=eserciziScheda.get(position).getSets();
        Integer ripetizione=eserciziScheda.get(position).getReps();
        holder.nomeEsercizio.setText(name);
        holder.ripetizioni.setText(String.valueOf(ripetizione));
        holder.serie.setText(String.valueOf(serie));
         */
    }

    @Override
    public int getItemCount() {
        return eserciziScheda.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        Exercise exercise;
        TextView nomeEsercizio, serie, ripetizioni;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeEsercizio = itemView.findViewById(R.id.nomeEsercizio);
            serie = itemView.findViewById(R.id.serie);
            ripetizioni = itemView.findViewById(R.id.ripetizioni);

        }
    }

}