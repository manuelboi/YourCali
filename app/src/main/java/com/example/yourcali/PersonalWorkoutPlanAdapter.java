package com.example.yourcali;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
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
    private final RecyclerViewPersonalInterface recyclerViewInterface;
    Context context;
    private ArrayList<PersonalWorkoutPlanAdapter.ViewHolder> holderList = new ArrayList<>();

    public PersonalWorkoutPlanAdapter(ArrayList<PersonalWorkoutPlan> workoutPlanList, Context context, RecyclerViewPersonalInterface recyclerViewInterface){
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
        PersonalWorkoutPlan personalWorkoutPlan = personalWorkoutPlanList.get(position);
        holder.nome.setText(personalWorkoutPlan.getName());
        holder.nEsercizi.setText(String.valueOf(personalWorkoutPlan.getExerciseList().size()));
        holderList.add(holder);
    }

    public void onClickManageButton(){
        for (PersonalWorkoutPlanAdapter.ViewHolder holder: holderList) {
            holder.edit.setVisibility(View.VISIBLE);
            holder.delete.setVisibility(View.VISIBLE);
        }
    }

    public void onClickCancelButton() {
        for (PersonalWorkoutPlanAdapter.ViewHolder holder: holderList) {
            holder.edit.setVisibility(View.GONE);
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
        ImageButton edit, delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.personalNameW);
            nEsercizi = itemView.findViewById(R.id.personalNumberW);
            edit = itemView.findViewById(R.id.modifiedPersonalWorkoutPlan);
            delete = itemView.findViewById(R.id.deletePersonalWorkoutPlan);

            itemView.setOnClickListener(view -> {
                if(recyclerViewInterface != null){
                    int position=getAdapterPosition();

                    if(position!=RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClick(position);
                    }
                }
            });

            edit.setOnClickListener(view -> {
                if(recyclerViewInterface != null){
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClickModifiedButton(position);
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

    public void alertBox(int position){
        AlertDialog alert=new AlertDialog.Builder(this.context).create();
        alert.setTitle("ATTENZIONE");
        alert.setMessage("Sei sicuro di voler eliminare la scheda?");
        alert.setButton(AlertDialog.BUTTON_POSITIVE, "Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                recyclerViewInterface.onItemClickDeleteButton(position);
                personalWorkoutPlanList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, personalWorkoutPlanList.size());
            }
        });
        alert.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#FD813C"));
                alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#FD813C"));
            }
        });
        alert.show();
    }
}
