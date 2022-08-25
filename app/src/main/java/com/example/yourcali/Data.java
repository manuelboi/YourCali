package com.example.yourcali;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Data implements Serializable {

    private final List<Exercise> predefinedExercises = new ArrayList<>();
    private final List<Exercise> personalExercises = new ArrayList<>();
    private final List<PredefinedWorkoutPlan> predefinedWorkoutPlans = new ArrayList<>();
    private final List<PersonalWorkoutPlan> personalWorkoutPlans = new ArrayList<>();
    private final List<Workout> workoutList = new ArrayList<>();



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

    public List<Workout> getworkoutList() {
        return workoutList;
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

    public void addWorkout (Workout workout) {
        workoutList.add(workout);
    }


    public void editExercisePersonal(Exercise exercise) {
        for (Exercise exerciseOfList: personalExercises) {
            if (exerciseOfList.getName().equals(exercise.getName())) {
                exerciseOfList.setName(exercise.getName());
                exerciseOfList.setBodyPart(exercise.getBodyPart());
                exerciseOfList.setDifficulty(exercise.getDifficulty());
                exerciseOfList.setHelpGuide(exercise.getHelpGuide());
                exerciseOfList.setImage(exercise.getImage());
            }
        }
    }

}
