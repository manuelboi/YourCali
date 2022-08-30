package com.example.yourcali;

import java.util.ArrayList;

public class PredefinedWorkoutPlan extends WorkoutPlan {

    private Difficulty difficulty;


    public PredefinedWorkoutPlan(String name, ArrayList<ExerciseOfPlan> exerciseList, Difficulty difficulty) {
        super(name, exerciseList);
        this.difficulty = difficulty;
    }


    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}