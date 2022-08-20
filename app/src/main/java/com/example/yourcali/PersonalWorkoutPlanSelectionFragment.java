package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

public class PersonalWorkoutPlanSelectionFragment extends Fragment {

    Button createWorkoutPlanButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_personal_workout_plan_selection, container, false);

        assert getArguments() != null;
        Data data = (Data) getArguments().getSerializable("data");

        createWorkoutPlanButton = view.findViewById(R.id.createWorkoutPlanButton);
        createWorkoutPlanButton.setOnClickListener(v -> {
            Intent openWorkoutPlanCreation = new Intent(getActivity(), WorkoutPlanCreationActivity.class);
            openWorkoutPlanCreation.putExtra("data", data);
            startActivity(openWorkoutPlanCreation);
        });

        return view;
    }

}