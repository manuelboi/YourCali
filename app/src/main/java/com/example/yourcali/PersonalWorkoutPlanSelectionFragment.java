package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Space;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonalWorkoutPlanSelectionFragment extends Fragment implements RecyclerViewInterface{

    Button manageWorkoutPlansButton, cancelManagingWorkoutPlansButton;
    AppCompatImageButton createWorkoutPlanButton;
    RecyclerView personalWorkoutRecycler;
    ArrayList<PersonalWorkoutPlan> personalWorkoutList = new ArrayList<>();
    ArrayList<PersonalWorkoutPlan> personalWorkoutListCopy;
    PersonalWorkoutPlanAdapter adapter;
    TextView emptyList;
    Data data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_personal_workout_plan_selection, container, false);
        emptyList=view.findViewById(R.id.emptypersonalworkout);
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

        if(!data.getPersonalWorkoutPlans().isEmpty()){
            emptyList.setVisibility(View.GONE);
            personalWorkoutRecycler.setVisibility(View.VISIBLE);
        }
        else{
            emptyList.setVisibility(View.VISIBLE);
            personalWorkoutRecycler.setVisibility(View.GONE);
        }

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
        Intent intent = new Intent(getActivity(), WorkoutPlanOverviewActivity.class);
        intent.putExtra("WorkoutPlan", data.getPersonalWorkoutPlans().get(position));
        intent.putExtra("firstStart", true);
        intent.putExtra("data", data);
        startActivity(intent);
    }

    public void doSearch(String query){
        adapter.getFilter().filter(query);
    }

}