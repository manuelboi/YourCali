package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class PredefinedWorkoutPlanSelectionFragment extends Fragment implements RecyclerViewInterface{

    RecyclerView predefinedWorkoutRecycler;
    ArrayList<PredefinedWorkoutPlan> predefinedWorkoutList = new ArrayList<>();
    ArrayList<PredefinedWorkoutPlan> predefinedWorkoutListCopy;
    Data data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_predefined_workout_plan_selection, container, false);

        assert getArguments() != null;
        data = (Data) getArguments().getSerializable("data");

        predefinedWorkoutRecycler=view.findViewById(R.id.predefinedWorkoutRecycler);
        predefinedWorkoutListCopy = new ArrayList<>(data.getPredefinedWorkoutPlans());
        PredefinedWorkoutPlanAdapter adapter = new PredefinedWorkoutPlanAdapter(predefinedWorkoutListCopy, getContext(), this);
        predefinedWorkoutRecycler.setAdapter(adapter);
        predefinedWorkoutRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), WorkoutPlanOverviewActivity.class);
        intent.putExtra("PredefinedWorkoutOverview", data.getPredefinedWorkoutPlans().get(position));
        intent.putExtra("data", data);
        startActivity(intent);
    }

}