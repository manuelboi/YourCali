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

public class PersonalWorkoutPlanSelectionFragment extends Fragment implements RecyclerViewInterface{

    Button createWorkoutPlanButton;
    RecyclerView personalWorkoutRecycler;
    ArrayList<PersonalWorkoutPlan> personalWorkoutList = new ArrayList<>();
    ArrayList<PersonalWorkoutPlan> personalWorkoutListCopy;
    Data data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_personal_workout_plan_selection, container, false);

        assert getArguments() != null;
        data = (Data) getArguments().getSerializable("data");

        createWorkoutPlanButton = view.findViewById(R.id.createWorkoutPlanButton);
        createWorkoutPlanButton.setOnClickListener(v -> {
            Intent openWorkoutPlanCreation = new Intent(getActivity(), WorkoutPlanCreationActivity.class);
            openWorkoutPlanCreation.putExtra("data", data);
            startActivity(openWorkoutPlanCreation);
        });

        personalWorkoutRecycler=view.findViewById(R.id.personalWorkoutRecycler);
        personalWorkoutListCopy = new ArrayList<>(data.getPersonalWorkoutPlans());
        PersonalWorkoutPlanAdapter adapter = new PersonalWorkoutPlanAdapter(personalWorkoutListCopy, getContext(), this);
        personalWorkoutRecycler.setAdapter(adapter);
        personalWorkoutRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), WorkoutPlanOverviewActivity.class);
        intent.putExtra("workoutPlan", data.getPersonalWorkoutPlans().get(position));
        intent.putExtra("firstStart", true);
        intent.putExtra("data", data);
        startActivity(intent);
    }

}