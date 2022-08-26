package com.example.yourcali;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class WorkoutExerciseStateAdapter extends RecyclerView.Adapter<WorkoutExerciseStateAdapter.ViewHolder>{

    private final RecyclerViewInterface recyclerViewInterface;
    ArrayList<ExerciseState> exerciseList;
    Context context;
    boolean click;

    public WorkoutExerciseStateAdapter(ArrayList<ExerciseState> exerciseList, Context context,RecyclerViewInterface recyclerViewInterface, boolean clicakble){
        this.recyclerViewInterface=recyclerViewInterface;
        this.context = context;
        this.exerciseList = exerciseList;
        click = clicakble;

    }

    @NonNull
    @Override
    public WorkoutExerciseStateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise_list,parent,false);  // Viene creata la View a partire dal template card_utente.xml
        return new WorkoutExerciseStateAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExerciseState exerciseState = exerciseList.get(position);
        holder.nome.setText(exerciseState.getExerciseOfPlan().getExercise().getName());
        Uri image = Uri.parse(exerciseState.getExerciseOfPlan().getExercise().getImage());
        holder.immagine.setImageURI(image);
        holder.done.setChecked(exerciseState.getDone());
        holder.done.setOnCheckedChangeListener((compoundButton, b) -> exerciseState.setDone(!exerciseState.getDone()));
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nome;
        ImageView immagine;
        CheckBox done;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nomeEsercizioVista);
            immagine = itemView.findViewById(R.id.imageView);
            done = itemView.findViewById(R.id.checkbox);

            itemView.setOnClickListener(view -> {
                if(recyclerViewInterface != null){
                    int position = getAdapterPosition();

                    if(position!=RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClick(position);
                    }
                }
            });

            if(!click){
                done.setFocusable(false);
                done.setClickable(false);
                done.setBackgroundColor(0);
            }
        }
    }

}

