package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonalWorkoutPlanSelectionFragment extends Fragment implements RecyclerViewPersonalInterface {

    Button manageWorkoutPlansButton, cancelManagingWorkoutPlansButton;
    ImageButton createWorkoutPlanButton;
    RecyclerView personalWorkoutRecycler;
    ArrayList<PersonalWorkoutPlan> personalWorkoutListCopy;
    PersonalWorkoutPlanAdapter adapter;
    TextView emptyList;
    Data data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_personal_workout_plan_selection, container, false);
        emptyList = view.findViewById(R.id.emptypersonalworkout);
        personalWorkoutRecycler=view.findViewById(R.id.personalWorkoutRecycler);

        assert getArguments() != null;
        data = (Data) getArguments().getSerializable("data");

        manageWorkoutPlansButton = view.findViewById(R.id.manageWorkoutPlansButton);
        createWorkoutPlanButton = view.findViewById(R.id.createWorkoutPlanButton);
        cancelManagingWorkoutPlansButton = view.findViewById(R.id.cancelManagingWorkoutPlansButton);

        manageWorkoutPlansButton.setOnClickListener(v -> {
            manageWorkoutPlansButton.setVisibility(View.GONE);
            createWorkoutPlanButton.setVisibility(View.VISIBLE);
            cancelManagingWorkoutPlansButton.setVisibility(View.VISIBLE);
            adapter.onClickManageButton();
        });

        createWorkoutPlanButton.setOnClickListener(v -> {
            Intent openWorkoutPlanCreation = new Intent(getActivity(), WorkoutPlanCreationActivity.class);
            openWorkoutPlanCreation.putExtra("data", data);
            startActivity(openWorkoutPlanCreation);
        });

        cancelManagingWorkoutPlansButton.setOnClickListener(v -> {
            manageWorkoutPlansButton.setVisibility(View.VISIBLE);
            createWorkoutPlanButton.setVisibility(View.GONE);
            cancelManagingWorkoutPlansButton.setVisibility(View.GONE);
            adapter.onClickCancelButton();
        });

        checkEmpty();

        personalWorkoutListCopy = new ArrayList<>(data.getPersonalWorkoutPlans());
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(personalWorkoutRecycler.getContext(),
                DividerItemDecoration.VERTICAL);
        personalWorkoutRecycler.addItemDecoration(mDividerItemDecoration);
        adapter = new PersonalWorkoutPlanAdapter(personalWorkoutListCopy, getContext(), this);
        personalWorkoutRecycler.setAdapter(adapter);
        personalWorkoutRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onItemClick(int position) {
        if(createWorkoutPlanButton.getVisibility()==View.GONE){
            Intent intent = new Intent(getActivity(), WorkoutPlanOverviewActivity.class);
            intent.putExtra("workoutPlan", data.getPersonalWorkoutPlans().get(position));
            intent.putExtra("firstStart", true);
            intent.putExtra("data", data);
            startActivity(intent);
        }
    }

    @Override
    public void onItemClickModifiedButton(int position){
        Intent modifiedExerciseIntent = new Intent(getActivity(), WorkoutPlanEditingActivity.class);
        modifiedExerciseIntent.putExtra("data", data);
        modifiedExerciseIntent.putExtra("personalWorkoutPlan", data.getPersonalWorkoutPlans().get(position));
        startActivity(modifiedExerciseIntent);
    }

    @Override
    public void onItemClickDeleteButton(int position){
        data.removePersonalWorkoutPlan(data.getPersonalWorkoutPlans().get(position));
        checkEmpty();
    }

    public void doSearch(String query){
        adapter.getFilter().filter(query);
    }

    public void checkEmpty() {
        if (!data.getPersonalWorkoutPlans().isEmpty()){
            emptyList.setVisibility(View.GONE);
            personalWorkoutRecycler.setVisibility(View.VISIBLE);
        }
        else {
            emptyList.setVisibility(View.VISIBLE);
            personalWorkoutRecycler.setVisibility(View.GONE);
        }
    }

}