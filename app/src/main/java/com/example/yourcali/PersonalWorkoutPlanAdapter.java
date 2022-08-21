package com.example.yourcali;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonalWorkoutPlanAdapter extends RecyclerView.Adapter<PersonalWorkoutPlanAdapter.ViewHolder> {
    ArrayList<PersonalWorkoutPlan> personalWorkoutPlanList, personalWorkoutPlanListTutti;
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;

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
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personal_workout_selection_list,parent,false);  // Viene creata la View a partire dal template card_utente.xml
        return new PersonalWorkoutPlanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalWorkoutPlanAdapter.ViewHolder holder, int position) {
        PersonalWorkoutPlan predefinedWorkoutPlan = personalWorkoutPlanList.get(position);
        holder.nome.setText(predefinedWorkoutPlan.getName());
        holder.nEsercizi.setText(String.valueOf(getItemCount()));
    }

    @Override
    public int getItemCount() {
        return personalWorkoutPlanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nome;
        TextView nEsercizi;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome=itemView.findViewById(R.id.personalNameW);
            nEsercizi=itemView.findViewById(R.id.personalNumberW);

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
}
