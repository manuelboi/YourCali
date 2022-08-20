package com.example.yourcali;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PredefinedWorkoutPlanSelectionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_predefined_workout_plan_selection, container, false);

        assert getArguments() != null;
        Data data = (Data) getArguments().getSerializable("data");

        return view;
    }

}