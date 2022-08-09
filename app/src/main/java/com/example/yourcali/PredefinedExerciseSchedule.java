package com.example.yourcali;

import java.util.List;

public class PredefinedExerciseSchedule extends ExerciseSchedule {

    Difficulty difficulty;


    public PredefinedExerciseSchedule(String name, String description, List<ExerciseOfSchedule> exerciseList, Difficulty difficulty) {
        super(name, description, exerciseList);
        this.difficulty = difficulty;
    }

}
