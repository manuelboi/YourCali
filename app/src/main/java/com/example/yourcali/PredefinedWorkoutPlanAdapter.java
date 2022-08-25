package com.example.yourcali;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PredefinedWorkoutPlanAdapter extends RecyclerView.Adapter<PredefinedWorkoutPlanAdapter.ViewHolder> implements Filterable {
    ArrayList<PredefinedWorkoutPlan> predefinedWorkoutPlanList, predefinedWorkoutPlanListTutti;
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;

    public PredefinedWorkoutPlanAdapter(ArrayList<PredefinedWorkoutPlan> workoutPlanList, Context context, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.predefinedWorkoutPlanList = workoutPlanList;
        this.recyclerViewInterface = recyclerViewInterface;
        predefinedWorkoutPlanListTutti = new ArrayList<>();
        predefinedWorkoutPlanListTutti.addAll(workoutPlanList);
    }


    @NonNull
    @Override
    public PredefinedWorkoutPlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workout_selection_list,parent,false);  // Viene creata la View a partire dal template card_utente.xml
        return new PredefinedWorkoutPlanAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PredefinedWorkoutPlanAdapter.ViewHolder holder, int position) {
        SimpleDateFormat date=new SimpleDateFormat("dd/MM/yyyy");
        PredefinedWorkoutPlan predefinedWorkoutPlan = predefinedWorkoutPlanList.get(position);
        holder.nome.setText(predefinedWorkoutPlan.getName());
        holder.nEsercizi.setText(String.valueOf(predefinedWorkoutPlan.getExerciseList().size()));
        holder.difficolta.setText(predefinedWorkoutPlan.getDifficulty().toString());
        if(predefinedWorkoutPlan.getDifficulty()==Difficulty.Easy){
            holder.difficolta.setTextColor(ContextCompat.getColor(context,R.color.teal_700));
        }
        else if(predefinedWorkoutPlan.getDifficulty()==Difficulty.Medium) {
            holder.difficolta.setTextColor(ContextCompat.getColor(context, R.color.DarkOrange));
        }
        else
            holder.difficolta.setTextColor(ContextCompat.getColor(context, R.color.Cranberry));


    }

    @Override
    public int getItemCount() {
        return predefinedWorkoutPlanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nome;
        TextView nEsercizi;
        TextView difficolta;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome=itemView.findViewById(R.id.nameW);
            nEsercizi=itemView.findViewById(R.id.numberW);
            difficolta=itemView.findViewById(R.id.diffW);

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
            ArrayList<PredefinedWorkoutPlan> workoutsFiltered= new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                workoutsFiltered.addAll(predefinedWorkoutPlanListTutti);
            } else {
                for (PredefinedWorkoutPlan workout : predefinedWorkoutPlanList) {
                    if (workout.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        workoutsFiltered.add(workout);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = workoutsFiltered;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            predefinedWorkoutPlanList.clear();
            predefinedWorkoutPlanList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

}
