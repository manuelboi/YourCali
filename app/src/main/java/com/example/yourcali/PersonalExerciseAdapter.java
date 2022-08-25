package com.example.yourcali;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment;

import java.util.ArrayList;

public class PersonalExerciseAdapter extends RecyclerView.Adapter<PersonalExerciseAdapter.ViewHolder> {

    private final ArrayList<ExercisePersonal> personalExerciseList, personalExerciseListAll;
    Context context;
    Exercise exercise;
    private ArrayList<PersonalExerciseAdapter.ViewHolder> holderList = new ArrayList<>();


    public PersonalExerciseAdapter(ArrayList<ExercisePersonal> personalExerciseList, Context context){  // La lista di utenti e lo user attuale vengono passati con il costruttore
        this.personalExerciseList = personalExerciseList;
        this.context = context;


        personalExerciseListAll = new ArrayList<>();
        personalExerciseListAll.addAll(personalExerciseList);
    }

    @NonNull
    @Override
    public PersonalExerciseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise_personal_enyclopedia,parent,false);  // Viene creata la View a partire dal template card_utente.xml
        return new ViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalExerciseAdapter.ViewHolder holder, int position) {
        Exercise exercise;
        String name;

        exercise = personalExerciseList.get(position);
        holder.exercise = exercise;
        name = personalExerciseList.get(position).getName();
        holder.nomeEsercizio.setText(name);
        holderList.add(holder);
    }

    public void onClickManageButton(){
        for (PersonalExerciseAdapter.ViewHolder holder: holderList) {
            holder.modification.setVisibility(View.VISIBLE);
            holder.delete.setVisibility(View.VISIBLE);
        }
    }

    public void onClickCancelButton() {
        for (PersonalExerciseAdapter.ViewHolder holder: holderList) {
            holder.modification.setVisibility(View.GONE);
            holder.delete.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return personalExerciseList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        Exercise exercise;
        TextView nomeEsercizio;
        ImageButton modification, delete;
        Button manage, cancel;
        View image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeEsercizio = itemView.findViewById(R.id.nameExerciseEncyclopedia);
            image = itemView.findViewById(R.id.imageExerciseEncyclopedia);
            modification = itemView.findViewById(R.id.modifiedExercise);
            delete = itemView.findViewById(R.id.deleteExercise);
        }
    }

}
