package com.example.yourcali;

import java.util.List;

public class PredefinedWorkoutPlan extends WorkoutPlan {

    private Difficulty difficulty;


    public PredefinedWorkoutPlan(String name, String description, List<ExerciseOfPlan> exerciseList, Difficulty difficulty) {
        super(name, description, exerciseList);
        this.difficulty = difficulty;
    }


    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}