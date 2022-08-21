package com.example.yourcali;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Workout implements Serializable {

    WorkoutPlan workoutPlan;
    ArrayList<ExerciseState> workoutState;
    Date startingTime;


    // Constructor

    public Workout(WorkoutPlan workoutPlan, ArrayList<ExerciseState> workoutState) {
        // Link to the workout plan, to eventually get name, description
        this.workoutPlan = workoutPlan;
        this.workoutState = workoutState;
        this.startingTime = new Date(System.currentTimeMillis());
    }


    // Getter and setter

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
            if (item.exerciseOfPlan == exerciseOfPlan) {
                item.done = true;
            }
        }
    }

    public void setExerciseAsNotDone(Exercise exercise) {
        for (ExerciseState item : this.workoutState) {
            if (item.exerciseOfPlan.exercise == exercise) {
                item.done = false;
            }
        }
    }

    public ExerciseOfPlan getNextExercise(ExerciseOfPlan exerciseOfPlan) {
        for (ExerciseState item : this.workoutState) {
            if (item.exerciseOfPlan == exerciseOfPlan) {
                int index = workoutState.indexOf(item);
                index++;
                if (index < workoutState.size()) {
                    return workoutState.get(index).exerciseOfPlan;
                }
            }
        }
        return null;
    }

    public int calculatePercentage() {
        int counter = 0;
        for (ExerciseState item : this.workoutState) {
            if (item.done) {
                counter++;
            }
        }
        return (counter*100 / workoutState.size());
    }

}