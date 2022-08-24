package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PredefinedExerciseFragment extends Fragment implements RecyclerViewInterface{

    Button createExerciseButton;
    RecyclerView predefinedExerciseRecycler;
    ArrayList<Exercise> predefinedExerciseList = new ArrayList<>();
    ArrayList<Exercise> predefinedExerciseListCopy;
    Data data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_predefined_exercise_encyclopedia, container, false);

        assert getArguments() != null;
        data = (Data) getArguments().getSerializable("data");

        predefinedExerciseRecycler=view.findViewById(R.id.predefinedExerciseRecycler);
        predefinedExerciseListCopy = new ArrayList<>(data.getPredefinedExercises());
        PredefinedExerciseAdapter adapter = new PredefinedExerciseAdapter(predefinedExerciseListCopy, getContext());
        predefinedExerciseRecycler.setAdapter(adapter);
        predefinedExerciseRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), WorkoutPlanOverviewActivity.class);
        intent.putExtra("predefinedExercise", data.getPersonalExercises().get(position));
        intent.putExtra("firstStart", true);
        intent.putExtra("data", data);
        startActivity(intent);
    }

}
