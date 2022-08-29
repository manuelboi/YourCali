package com.example.yourcali;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WorkoutPlanCreationAdapter extends RecyclerView.Adapter<WorkoutPlanCreationAdapter.ViewHolder> implements Filterable {

    private final ArrayList<ExerciseOfPlan> eserciziScheda, eserciziSchedaTutti;
    RecyclerViewPersonalInterface recyclerViewInterface;
    Context context;
    Exercise exercise;

    public WorkoutPlanCreationAdapter(ArrayList<ExerciseOfPlan> eserciziScheda, RecyclerViewPersonalInterface recylerViewInterface, Context context){  // La lista di utenti e lo user attuale vengono passati con il costruttore
        this.eserciziScheda = eserciziScheda;
        this.recyclerViewInterface = recylerViewInterface;
        this.context=context;
        eserciziSchedaTutti = new ArrayList<>();
        eserciziSchedaTutti.addAll(eserciziScheda);
    }

    @NonNull
    @Override
    public WorkoutPlanCreationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise,parent,false);  // Viene creata la View a partire dal template card_utente.xml

        return new ViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutPlanCreationAdapter.ViewHolder holder, int position) {
        ExerciseOfPlan exercise;
        String name;

        exercise = eserciziScheda.get(position);
        holder.exercise = exercise;
        name = eserciziScheda.get(position).getExercise().getName();
        Integer serie=eserciziScheda.get(position).getSets();
        Integer ripetizione=eserciziScheda.get(position).getRepsOrTime();
        holder.nomeEsercizio.setText(name);
        holder.ripetizioni.setText(String.valueOf(ripetizione));
        holder.serie.setText(String.valueOf(serie));

    }

    @Override
    public int getItemCount() {
        return eserciziScheda.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ExerciseOfPlan exercise;
        TextView nomeEsercizio, serie, ripetizioni;
        ImageButton delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeEsercizio = itemView.findViewById(R.id.nomeEsercizio);
            serie = itemView.findViewById(R.id.serie);
            ripetizioni = itemView.findViewById(R.id.ripetizioni);
            delete = itemView.findViewById(R.id.deleteExerciseWorkout);


            itemView.setOnClickListener(view -> {
                if(recyclerViewInterface != null){
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClick(position);
                    }
                }
            });

            delete.setOnClickListener(view -> {
                if(recyclerViewInterface != null){
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION){
                        alertBox(position);
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
            ArrayList<ExerciseOfPlan> exercisesFiltered= new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                exercisesFiltered.addAll(eserciziSchedaTutti);
            } else {
                for (ExerciseOfPlan exercise : eserciziSchedaTutti) {
                    if (exercise.getExercise().getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        exercisesFiltered.add(exercise);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = exercisesFiltered;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            eserciziScheda.clear();
            eserciziScheda.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

    public void alertBox(int position){
        AlertDialog alert=new AlertDialog.Builder(this.context).create();
        alert.setTitle("ATTENZIONE");
        alert.setMessage("Sei sicuro di voler eliminare l'esercizio?");
        alert.setButton(AlertDialog.BUTTON_POSITIVE, "Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                recyclerViewInterface.onItemClickDeleteButton(position);
                eserciziScheda.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, eserciziScheda.size());
            }
        });
        alert.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();
    }

}
