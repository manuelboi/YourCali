package com.example.yourcali;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Workout implements Serializable {

    private WorkoutPlan workoutPlan;
    private ArrayList<ExerciseState> workoutState;
    private Date startingTime;


    // Constructor

    public Workout(WorkoutPlan workoutPlan, ArrayList<ExerciseState> workoutState) {
        // Link to the workout plan, to eventually get name, description
        this.workoutPlan = workoutPlan;
        this.workoutState = workoutState;
        this.startingTime = new Date(System.currentTimeMillis());
    }


    // Getter and setter


    public WorkoutPlan getWorkoutPlan() {
        return workoutPlan;
    }

    public void setWorkoutPlan(WorkoutPlan workoutPlan) {
        this.workoutPlan = workoutPlan;
    }

    public List<ExerciseState> getWorkoutState() {
        return this.workoutState;
    }

    public void setWorkoutState(ArrayList<ExerciseState> workoutState) {
        this.workoutState = workoutState;
    }

    public Date getStartingTime() {
        return this.startingTime;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }


    // Generic methods

    public void setExerciseAsDone(ExerciseOfPlan exerciseOfPlan) {
        for (ExerciseState item : this.workoutState) {
            if (item.getExerciseOfPlan().getExercise().getName().equals(exerciseOfPlan.getExercise().getName())) {
                item.setDone(true);
            }
        }
    }

    public ExerciseOfPlan getNextExercise(ExerciseOfPlan exerciseOfPlan) {
        for (ExerciseState item : this.workoutState) {
            if (Objects.equals(item.getExerciseOfPlan().getExercise().getName(), exerciseOfPlan.getExercise().getName())) {
                int index = workoutState.indexOf(item);
                index++;
                if (index < workoutState.size()) {
                    if (workoutState.get(index).getDone())
                        // If the exercise is already done, we will search for the next one recursively.
                        return getNextExercise(workoutState.get(index).getExerciseOfPlan());
                    else
                        // Otherwise, this exercise is returned
                        return workoutState.get(index).getExerciseOfPlan();
                }
            }
        }
        return null;
    }

    public int calculatePercentage() {
        int counter = 0;
        for (ExerciseState item : this.workoutState) {
            if (item.getDone()) {
                counter++;
            }
        }
        return (counter*100 / workoutState.size());
    }

    public ExerciseState getExerciseState(ExerciseOfPlan exerciseOfPlan) {
        for (ExerciseState exerciseState : workoutState) {
            if (exerciseState.getExerciseOfPlan().getExercise().getName().equals(exerciseOfPlan.getExercise().getName()))
                return exerciseState;
        }
        return null;
    }

}