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
import java.util.ArrayList;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ViewHolder> implements Filterable {

    private final ArrayList<Training> trainingList, trainingListTutti;
    Context context;

    public TrainingAdapter(ArrayList<Training> trainingList, Context context){
        this.context=context;
        this.trainingList = trainingList;;
        trainingListTutti = new ArrayList<>();
        trainingListTutti.addAll(trainingList);
    }

    @NonNull
    @Override
    public TrainingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_schede,parent,false);  // Viene creata la View a partire dal template card_utente.xml
        return new TrainingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Training training = trainingList.get(position);
        holder.nome.setText(training.workoutPlan.getName());
        holder.data.setText((CharSequence) training.getStartingTime());
        holder.done.setText(training.calculatePercentage());

    }

    @Override
    public int getItemCount() {
        return trainingList.size();
    }


    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Training> trainingsFiltered= new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                trainingsFiltered.addAll(trainingListTutti);
            } else {
                for (Training training: trainingListTutti) {
                    if (training.workoutPlan.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        trainingsFiltered.add(training);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = trainingsFiltered;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            trainingList.clear();
            trainingList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nome;
        TextView data;
        TextView done;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome=itemView.findViewById(R.id.nomeSchedaStorico);
            data=itemView.findViewById(R.id.dataStorico);
            done=itemView.findViewById(R.id.completamentoStorico);

        }
    }
}