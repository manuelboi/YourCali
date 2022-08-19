package com.example.yourcali;

import java.util.ArrayList;
import java.util.List;

public class Data {

    List<Exercise> predefinedExercises = new ArrayList<>();
    List<Exercise> personalExercises = new ArrayList<>();
    List<PredefinedWorkoutPlan> predefinedWorkoutPlans = new ArrayList<>();
    List<PersonalWorkoutPlan> personalWorkoutPlans = new ArrayList<>();

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

}
