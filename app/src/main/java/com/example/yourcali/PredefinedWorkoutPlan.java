package com.example.yourcali;

import java.util.List;

public class PredefinedWorkoutPlan extends WorkoutPlan {

    private Difficulty difficulty;


    public PredefinedWorkoutPlan(String name, List<ExerciseOfPlan> exerciseList, Difficulty difficulty) {
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