package com.example.yourcali;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WorkoutPlan implements Serializable {

    protected String name;
    protected ArrayList<ExerciseOfPlan> exerciseList;


    public WorkoutPlan(String name, ArrayList<ExerciseOfPlan> exerciseList) {
        this.name = name;
        this.exerciseList = exerciseList;
    }


    public String getName() {
        return this.name;
    }

    public List<ExerciseOfPlan> getExerciseList() {
        return this.exerciseList;
    }


    // Create new unitilized workout from a workout plan
    public Workout createWorkout(){
        ArrayList<ExerciseState> workoutList = new ArrayList<>();

        for (ExerciseOfPlan item:this.exerciseList) {
            workoutList.add(new ExerciseState(item, false));
        }

        return new Workout(this, workoutList);
    }

}