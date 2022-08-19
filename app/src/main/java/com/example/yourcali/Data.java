package com.example.yourcali;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Data implements Serializable {

    private final List<Exercise> predefinedExercises = new ArrayList<>();
    private final List<Exercise> personalExercises = new ArrayList<>();
    private final List<PredefinedWorkoutPlan> predefinedWorkoutPlans = new ArrayList<>();
    private final List<PersonalWorkoutPlan> personalWorkoutPlans = new ArrayList<>();
    private final List<Training> trainingList = new ArrayList<>();


    public List<Exercise> getPredefinedExercises() {
        return predefinedExercises;
    }

    public List<Exercise> getPersonalExercises() {
        return personalExercises;
    }

    public List<PredefinedWorkoutPlan> getPredefinedWorkoutPlans() {
        return predefinedWorkoutPlans;
    }

    public List<PersonalWorkoutPlan> getPersonalWorkoutPlans() {
        return personalWorkoutPlans;
    }

    public List<Training> getTrainingList() {
        return trainingList;
    }

    public void addPredefinedExercise(Exercise exercise) {
        predefinedExercises.add(exercise);
    }

    public void addPersonalExercise(Exercise exercise) {
       personalExercises.add(exercise);
    }

    public void addPredefinedWorkoutPlan (PredefinedWorkoutPlan workoutPlan) {
        predefinedWorkoutPlans.add(workoutPlan);
    }

    public void addPersonalWorkoutPlan (PersonalWorkoutPlan workoutPlan) {
        personalWorkoutPlans.add(workoutPlan);
    }

    public void addTraining (Training training) {
        trainingList.add(training);
    }

}
