package com.example.yourcali;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PredefinedWorkoutPlanAdapter extends RecyclerView.Adapter<PredefinedWorkoutPlanAdapter.ViewHolder> {
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
        holder.nEsercizi.setText(String.valueOf(getItemCount()));
        //holder.difficolta.setText(workoutPlan.d);

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



}
