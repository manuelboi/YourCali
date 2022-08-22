package com.example.yourcali;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkoutPlan implements Serializable {

    protected String name, description;
    protected List<ExerciseOfPlan> exerciseList;


    public WorkoutPlan(String name, String description, List<ExerciseOfPlan> exerciseList) {
        this.name = name;
        this.description = description;
        this.exerciseList = exerciseList;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ExerciseOfPlan> getExerciseList() {
        return this.exerciseList;
    }

    public void setExerciseList(List<ExerciseOfPlan> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public Workout createWorkout(){
        ArrayList<ExerciseState> workoutList = new ArrayList<>();

        for (ExerciseOfPlan item:this.exerciseList) {
            workoutList.add(new ExerciseState(item, false));
        }

        return new Workout(this, workoutList);
    }

}