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

public class PersonalExerciseFragment extends Fragment implements RecyclerViewInterface{

    Button createExerciseButton, cancelManagingExerciseButton, manageExercisesButton;
    RecyclerView personalExerciseRecycler;
    ArrayList<ExercisePersonal> personalExerciseList = new ArrayList<>();
    ArrayList<ExercisePersonal> personalExerciseListCopy;
    Data data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_personal_exercise_encyclopedia, container, false);

        assert getArguments() != null;
        data = (Data) getArguments().getSerializable("data");

        manageExercisesButton = view.findViewById(R.id.mamageExercisesButton);
        createExerciseButton = view.findViewById(R.id.createExerciseButton);
        cancelManagingExerciseButton = view.findViewById(R.id.cancelManagingExercisesButton);

        manageExercisesButton.setOnClickListener(v -> {
            manageExercisesButton.setVisibility(View.GONE);
            createExerciseButton.setVisibility(View.VISIBLE);
            cancelManagingExerciseButton.setVisibility(View.VISIBLE);
        });

        createExerciseButton.setOnClickListener(v -> {
            Intent openExerciseCreation = new Intent(getActivity(), ExerciseCreationActivity.class);
            openExerciseCreation.putExtra("data", data);
            startActivity(openExerciseCreation);
        });

        cancelManagingExerciseButton.setOnClickListener(v -> {
            manageExercisesButton.setVisibility(View.VISIBLE);
            createExerciseButton.setVisibility(View.GONE);
            cancelManagingExerciseButton.setVisibility(View.GONE);
        });

        personalExerciseRecycler=view.findViewById(R.id.personalExerciseRecycler);
        personalExerciseListCopy = new ArrayList<>(data.getPersonalExercises());
        PersonalExerciseAdapter adapter = new PersonalExerciseAdapter(personalExerciseListCopy, getContext());
        personalExerciseRecycler.setAdapter(adapter);
        personalExerciseRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), WorkoutPlanOverviewActivity.class);
        intent.putExtra("personalExercise", data.getPersonalExercises().get(position));
        intent.putExtra("firstStart", true);
        intent.putExtra("data", data);
        startActivity(intent);
    }

}
