package com.example.yourcali;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PredefinedExerciseAdapter extends RecyclerView.Adapter<PredefinedExerciseAdapter.ViewHolder> {

    private final ArrayList<Exercise> predefinedExerciseList, predefinedExerciseListAll;
    Context context;
    Exercise exercise;

    public PredefinedExerciseAdapter(ArrayList<Exercise> predefinedExerciseList, Context context){  // La lista di utenti e lo user attuale vengono passati con il costruttore
        this.predefinedExerciseList = predefinedExerciseList;
        this.context = context;


        predefinedExerciseListAll = new ArrayList<>();
        predefinedExerciseListAll.addAll(predefinedExerciseList);
    }

    @NonNull
    @Override
    public PredefinedExerciseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise_encyclopedia,parent,false);  // Viene creata la View a partire dal template card_utente.xml

        return new ViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull PredefinedExerciseAdapter.ViewHolder holder, int position) {
        Exercise exercise;
        String name;

        exercise = predefinedExerciseList.get(position);
        holder.exercise = exercise;
        name=predefinedExerciseList.get(position).getName();
        holder.nomeEsercizio.setText(name);

    }

    @Override
    public int getItemCount() {
        return predefinedExerciseList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        Exercise exercise;
        TextView nomeEsercizio;
        View image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeEsercizio = itemView.findViewById(R.id.nameExerciseEncyclopedia);
            image = itemView.findViewById(R.id.imageExerciseEncyclopedia);

        }
    }

}
