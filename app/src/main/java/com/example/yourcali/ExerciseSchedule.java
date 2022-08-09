package com.example.yourcali;

import java.util.List;

public class ExerciseSchedule {

    protected String name, description;
    protected List<ExerciseOfSchedule> exerciseList;


    public ExerciseSchedule(String name, String description, List<ExerciseOfSchedule> exerciseList) {
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

    public List<ExerciseOfSchedule> getExerciseList() {
        return this.exerciseList;
    }

    public void setExerciseList(List<ExerciseOfSchedule> exerciseList) {
        this.exerciseList = exerciseList;
    }
}