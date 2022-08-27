package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonalExerciseFragment extends Fragment implements RecyclerViewPersonalInterface{

    Button createExerciseButton, cancelManagingExerciseButton, manageExercisesButton;
    RecyclerView personalExerciseRecycler;
    ArrayList<Exercise> personalExerciseList = new ArrayList<>();
    ArrayList<Exercise> personalExerciseListCopy;
    PersonalExerciseAdapter adapter;
    TextView emptyList;
    Data data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_personal_exercise_encyclopedia, container, false);
        emptyList=view.findViewById(R.id.emptypersonalexercise);
        personalExerciseRecycler=view.findViewById(R.id.personalExerciseRecycler);

        assert getArguments() != null;
        data = (Data) getArguments().getSerializable("data");

        checkEmpty();

        personalExerciseListCopy = new ArrayList<>(data.getPersonalExercises());
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(personalExerciseRecycler.getContext(),
                DividerItemDecoration.VERTICAL);
        personalExerciseRecycler.addItemDecoration(mDividerItemDecoration);
        adapter = new PersonalExerciseAdapter(personalExerciseListCopy, getContext(), this);
        personalExerciseRecycler.setAdapter(adapter);
        personalExerciseRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        manageExercisesButton = view.findViewById(R.id.manageExercisesButton);
        createExerciseButton = view.findViewById(R.id.createExerciseButton);
        cancelManagingExerciseButton = view.findViewById(R.id.cancelManagingExercisesButton);

        manageExercisesButton.setOnClickListener(v -> {
            manageExercisesButton.setVisibility(View.GONE);
            createExerciseButton.setVisibility(View.VISIBLE);
            cancelManagingExerciseButton.setVisibility(View.VISIBLE);
            adapter.onClickManageButton();
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
            adapter.onClickCancelButton();
        });

        return view;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), ExerciseInfoEncyclopediaActivity.class);
        intent.putExtra("exercise", data.getPersonalExercises().get(position));
        intent.putExtra("firstStart", true);
        intent.putExtra("data", data);
        startActivity(intent);
    }

    @Override
    public void onItemClickModifiedButton(int position){
        Intent intent = new Intent(getActivity(), ExerciseEditingActivity.class);
        intent.putExtra("exercise", data.getPersonalExercises().get(position));
        intent.putExtra("data", data);
        startActivity(intent);
    }

    @Override
    public void onItemClickDeleteButton(int position){
        data.removePersonalExercise(data.getPersonalExercises().get(position));
        checkEmpty();
    }

    public void doSearch(String query){
        adapter.getFilter().filter(query);
    }

    public void checkEmpty () {
        if(!data.getPersonalExercises().isEmpty()){
            emptyList.setVisibility(View.GONE);
            personalExerciseRecycler.setVisibility(View.VISIBLE);
        }
        else{
            emptyList.setVisibility(View.VISIBLE);
            personalExerciseRecycler.setVisibility(View.GONE);
        }
    }

}
