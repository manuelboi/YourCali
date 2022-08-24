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

    Button createExerciseButton;
    RecyclerView personalExerciseRecycler;
    ArrayList<ExercisePersonal> personalExerciseList = new ArrayList<>();
    ArrayList<ExercisePersonal> personalExerciseListCopy;
    Data data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_personal_exercise_encyclopedia, container, false);

        assert getArguments() != null;
        data = (Data) getArguments().getSerializable("data");

        createExerciseButton = view.findViewById(R.id.createExerciseButton);
        createExerciseButton.setOnClickListener(v -> {
            Intent openWorkoutPlanCreation = new Intent(getActivity(), ExerciseCreationActivity.class);
            openWorkoutPlanCreation.putExtra("data", data);
            startActivity(openWorkoutPlanCreation);
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
