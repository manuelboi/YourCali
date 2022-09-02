package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PredefinedExerciseFragment extends Fragment implements RecyclerViewInterface{

    Button createExerciseButton;
    RecyclerView predefinedExerciseRecycler;
    ArrayList<Exercise> predefinedExerciseList = new ArrayList<>();
    ArrayList<Exercise> predefinedExerciseListCopy;
    PredefinedExerciseAdapter adapter;
    Data data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_predefined_exercise_encyclopedia, container, false);

        assert getArguments() != null;
        data = (Data) getArguments().getSerializable("data");

        predefinedExerciseRecycler=view.findViewById(R.id.predefinedExerciseRecycler);
        predefinedExerciseListCopy = new ArrayList<>(data.getPredefinedExercises());
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(predefinedExerciseRecycler.getContext(),
                DividerItemDecoration.VERTICAL);
        predefinedExerciseRecycler.addItemDecoration(mDividerItemDecoration);
        adapter = new PredefinedExerciseAdapter(predefinedExerciseListCopy, getContext(), this);
        predefinedExerciseRecycler.setAdapter(adapter);
        predefinedExerciseRecycler.setLayoutManager(new LinearLayoutManager(getContext()));



        return view;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), ExerciseInfoEncyclopediaActivity.class);
        intent.putExtra("exercise", data.getPredefinedExercises().get(position));
        intent.putExtra("firstStart", true);
        intent.putExtra("data", data);
        startActivity(intent);
    }

    public void doSearch(String query){
        adapter.getFilter().filter(query);
    }


}
