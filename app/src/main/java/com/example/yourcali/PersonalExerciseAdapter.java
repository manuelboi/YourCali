package com.example.yourcali;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment;

import java.util.ArrayList;

public class PersonalExerciseAdapter extends RecyclerView.Adapter<PersonalExerciseAdapter.ViewHolder> implements Filterable {

    private final ArrayList<Exercise> personalExerciseList, personalExerciseListAll;
    private final RecyclerViewPersonalInterface recyclerViewInterface;
    Context context;
    Exercise exercise;
    private ArrayList<PersonalExerciseAdapter.ViewHolder> holderList = new ArrayList<>();


    public PersonalExerciseAdapter(ArrayList<Exercise> personalExerciseList, Context context, RecyclerViewPersonalInterface recyclerViewInterface){  // La lista di utenti e lo user attuale vengono passati con il costruttore
        this.personalExerciseList = personalExerciseList;
        this.recyclerViewInterface = recyclerViewInterface;
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
        Exercise exercisePersonal;
        String name;

        exercisePersonal = personalExerciseList.get(position);
        holder.exercise = exercisePersonal;
        name = personalExerciseList.get(position).getName();
        holder.nomeEsercizio.setText(name);
        Uri image = Uri.parse(exercisePersonal.getImage());
        holder.image.setImageURI(image);
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
        ImageView image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeEsercizio = itemView.findViewById(R.id.nameExerciseEncyclopedia);
            image = itemView.findViewById(R.id.imageExerciseEncyclopedia);
            modification = itemView.findViewById(R.id.modifiedExercise);
            delete = itemView.findViewById(R.id.deleteExercise);

            itemView.setOnClickListener(view -> {
                if(recyclerViewInterface != null){
                    int position=getAdapterPosition();

                    if(position!=RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClick(position);
                    }
                }
            });

            modification.setOnClickListener(view -> {
                if(recyclerViewInterface != null){
                    int position=getAdapterPosition();

                    if(position!=RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClickModifiedButton(position);
                    }
                }
            });
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Exercise> exercisesFiltered= new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                exercisesFiltered.addAll(personalExerciseListAll);
            } else {
                for (Exercise workout : personalExerciseList) {
                    if (workout.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        exercisesFiltered.add(workout);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = exercisesFiltered;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            personalExerciseList.clear();
            personalExerciseList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

}
