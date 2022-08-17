package com.example.yourcali;

import java.util.List;

public class Data {

    List<Exercise> predefinedExercises;
    List<Exercise> personalExercises;
    List<PredefinedWorkoutPlan> predefinedWorkoutPlans;
    List<PersonalWorkoutPlan> personalWorkoutPlans;

    public void addExercise(Exercise exercise, boolean predefined) {
        if (predefined) predefinedExercises.add(exercise);
        else personalExercises.add(exercise);
    }

    public void addWorkoutPlan (WorkoutPlan workoutPlan, boolean predefined) {
        if (workoutPlan instanceof PredefinedWorkoutPlan) predefinedWorkoutPlans.add((PredefinedWorkoutPlan) workoutPlan);
        else personalWorkoutPlans.add((PersonalWorkoutPlan) workoutPlan);
    }

}
