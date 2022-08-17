package com.example.yourcali;

import java.util.List;

public class Data {

    List<Exercise> predefinedExercises;
    List<Exercise> personalExercises;
    List<PredefinedWorkoutPlan> predefinedWorkoutPlans;
    List<PersonalWorkoutPlan> personalWorkoutPlans;

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
