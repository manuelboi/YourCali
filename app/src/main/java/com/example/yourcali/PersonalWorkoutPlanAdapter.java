package com.example.yourcali;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonalWorkoutPlanAdapter extends RecyclerView.Adapter<PersonalWorkoutPlanAdapter.ViewHolder> implements Filterable {
    ArrayList<PersonalWorkoutPlan> personalWorkoutPlanList, personalWorkoutPlanListTutti;
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    private ArrayList<PersonalWorkoutPlanAdapter.ViewHolder> holderList = new ArrayList<>();

    public PersonalWorkoutPlanAdapter(ArrayList<PersonalWorkoutPlan> workoutPlanList, Context context, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.personalWorkoutPlanList = workoutPlanList;
        this.recyclerViewInterface = recyclerViewInterface;
        personalWorkoutPlanListTutti = new ArrayList<>();
        personalWorkoutPlanListTutti.addAll(workoutPlanList);
    }

    @NonNull
    @Override
    public PersonalWorkoutPlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personal_workout_selection_list,parent,false);  // Viene creata la View a partire dal template card_utente.xml
        return new PersonalWorkoutPlanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalWorkoutPlanAdapter.ViewHolder holder, int position) {
        PersonalWorkoutPlan predefinedWorkoutPlan = personalWorkoutPlanList.get(position);
        holder.nome.setText(predefinedWorkoutPlan.getName());
        holder.nEsercizi.setText(String.valueOf(getItemCount()));
        holderList.add(holder);
    }

    public void onClickManageButton(){
        for (PersonalWorkoutPlanAdapter.ViewHolder holder: holderList) {
            holder.modification.setVisibility(View.VISIBLE);
            holder.delete.setVisibility(View.VISIBLE);
        }
    }

    public void onClickCancelButton() {
        for (PersonalWorkoutPlanAdapter.ViewHolder holder: holderList) {
            holder.modification.setVisibility(View.GONE);
            holder.delete.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return personalWorkoutPlanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nome;
        TextView nEsercizi;
        ImageButton modification, delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.personalNameW);
            nEsercizi = itemView.findViewById(R.id.personalNumberW);
            modification = itemView.findViewById(R.id.modifiedPersonalWorkoutPlan);
            delete = itemView.findViewById(R.id.deletePersonalWorkoutPlan);

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
            ArrayList<PersonalWorkoutPlan> workoutsFiltered= new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                workoutsFiltered.addAll(personalWorkoutPlanListTutti);
            } else {
                for (PersonalWorkoutPlan workout : personalWorkoutPlanList) {
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
            personalWorkoutPlanList.clear();
            personalWorkoutPlanList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
}
