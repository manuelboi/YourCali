package com.example.yourcali;

import java.util.List;

public class PredefinedWorkoutPlan extends WorkoutPlan {

    Difficulty difficulty;


    public PredefinedWorkoutPlan(String name, String description, List<ExerciseOfPlan> exerciseList, Difficulty difficulty) {
        super(name, description, exerciseList);
        this.difficulty = difficulty;
    }

}
