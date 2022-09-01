package com.example.yourcali;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Data implements Serializable {

    private final ArrayList<Exercise> predefinedExercises = new ArrayList<>();
    private final ArrayList<Exercise> personalExercises = new ArrayList<>();

    private final ArrayList<PredefinedWorkoutPlan> predefinedWorkoutPlans = new ArrayList<>();
    private final ArrayList<PersonalWorkoutPlan> personalWorkoutPlans = new ArrayList<>();

    private final ArrayList<Workout> workoutList = new ArrayList<>();




    // EXERCISES

    // Predefined exercises
    public List<Exercise> getPredefinedExercises() {
        return predefinedExercises;
    }
    public void adspredefinedExercise(Exercise exercise) {
        predefinedExercises.add(exercise);
    }

    // Personal exercises
    public List<Exercise> getPersonalExercises() {
        return personalExercises;
    }
    public void adspersonalExercise(Exercise exercise) {
        personalExercises.add(exercise);
    }
    public void removePersonalExercise(Exercise exercise) {
        personalExercises.remove(exercise);
    }
    public void editExercisePersonal(Exercise oldExercise, Exercise newExercise) {
        for (Exercise exerciseOfList: personalExercises) {
            if (exerciseOfList.getName().equals(oldExercise.getName())) {
                exerciseOfList.setName(newExercise.getName());
                exerciseOfList.setBodyPart(newExercise.getBodyPart());
                exerciseOfList.setDifficulty(newExercise.getDifficulty());
                exerciseOfList.setHelpGuide(newExercise.getHelpGuide());
                exerciseOfList.setImage(newExercise.getImage());
            }
        }
    }




    // WORKOUT PLANS

    // Predefined workout plans
    public List<PredefinedWorkoutPlan> getPredefinedWorkoutPlans() {
        return predefinedWorkoutPlans;
    }
    public void adspredefinedWorkoutPlan (PredefinedWorkoutPlan workoutPlan) {
        predefinedWorkoutPlans.add(workoutPlan);
    }

    // Personal workout plans
    public List<PersonalWorkoutPlan> getPersonalWorkoutPlans() {
        return personalWorkoutPlans;
    }
    public void adspersonalWorkoutPlan (PersonalWorkoutPlan workoutPlan) {
        personalWorkoutPlans.add(workoutPlan);
    }
    public void editPersonalWorkoutPlan (PersonalWorkoutPlan oldWorkoutPlan, PersonalWorkoutPlan newWorkoutPlan) {
        for (PersonalWorkoutPlan personalWorkoutPlan: personalWorkoutPlans) {
            if (personalWorkoutPlan.getName().equals(oldWorkoutPlan.getName())) {
                personalWorkoutPlan.setName(newWorkoutPlan.getName());
                ArrayList<ExerciseOfPlan> exerciseOfPlanList = new ArrayList<>(newWorkoutPlan.getExerciseList());
                personalWorkoutPlan.setExerciseList(exerciseOfPlanList);
            }
        }
    }
    public void removePersonalWorkoutPlan(PersonalWorkoutPlan workoutPlan) {
        personalWorkoutPlans.remove(workoutPlan);
    }




    // WORKOUT HISTORY

    public List<Workout> getWorkoutList() {
        return workoutList;
    }
    public void addWorkout (Workout workout) {
        workoutList.add(workout);
    }

}
