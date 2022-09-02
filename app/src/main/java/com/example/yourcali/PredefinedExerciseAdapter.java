package com.example.yourcali;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PredefinedExerciseAdapter extends RecyclerView.Adapter<PredefinedExerciseAdapter.ViewHolder> implements Filterable {

    private final ArrayList<Exercise> predefinedExerciseList, predefinedExerciseListAll;
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    Exercise exercise;

    public PredefinedExerciseAdapter(ArrayList<Exercise> predefinedExerciseList, Context context, RecyclerViewInterface recyclerViewInterface){  // La lista di utenti e lo user attuale vengono passati con il costruttore
        this.predefinedExerciseList = predefinedExerciseList;
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;


        predefinedExerciseListAll = new ArrayList<>();
        predefinedExerciseListAll.addAll(predefinedExerciseList);
    }

    @NonNull
    @Override
    public PredefinedExerciseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise_encyclopedia,parent,false);  // Viene creata la View a partire dal template card_utente.xml

        return new ViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull PredefinedExerciseAdapter.ViewHolder holder, int position) {
        Exercise exercise;
        String name;

        exercise = predefinedExerciseList.get(position);
        holder.exercise = exercise;
        name = predefinedExerciseList.get(position).getName();
        Uri image = Uri.parse(exercise.getImage());
        holder.image.setImageURI(image);
        holder.nomeEsercizio.setText(name);

    }

    @Override
    public int getItemCount() {
        return predefinedExerciseList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        Exercise exercise;
        TextView nomeEsercizio;
        ImageView image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeEsercizio = itemView.findViewById(R.id.nameExerciseEncyclopedia);
            image = itemView.findViewById(R.id.imageExerciseEncyclopedia);

            itemView.setOnClickListener(view -> {
                if(recyclerViewInterface != null){
                    int position=getAdapterPosition();

                    if(position!=RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClick(position);
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
                exercisesFiltered.addAll(predefinedExerciseListAll);
            } else {
                for (Exercise workout : predefinedExerciseList) {
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
            predefinedExerciseList.clear();
            predefinedExerciseList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

}
