package com.example.yourcali;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> implements Filterable {

    private final RecyclerViewInterface recyclerViewInterface;
    ArrayList<Workout> workoutList, workoutListTutti;
    Context context;

    public WorkoutAdapter(ArrayList<Workout> workoutList, Context context, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.workoutList = workoutList;;
        this.recyclerViewInterface = recyclerViewInterface;
        workoutListTutti = new ArrayList<>();
        workoutListTutti.addAll(workoutList);
    }

    @NonNull
    @Override
    public WorkoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_schede,parent,false);  // Viene creata la View a partire dal template card_utente.xml
        return new WorkoutAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        Workout workout = workoutList.get(position);
        holder.nome.setText(workout.getWorkoutPlan().getName());
        holder.data.setText("Data: " + date.format(workout.getStartingTime()));
        holder.done.setText("Completamento: " + workout.calculatePercentage() + "%");

    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }


    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Workout> workoutsFiltered= new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                workoutsFiltered.addAll(workoutListTutti);
            } else {
                for (Workout workout : workoutListTutti) {
                    if (workout.getWorkoutPlan().getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
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
            workoutList.clear();
            workoutList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nome;
        TextView data;
        TextView done;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nomeSchedaStorico);
            data = itemView.findViewById(R.id.dataStorico);
            done = itemView.findViewById(R.id.completamentoStorico);

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