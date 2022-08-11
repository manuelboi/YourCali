package com.example.yourcali;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Training implements Serializable {

    List<ExerciseState> trainingState;
    Date startingTime;


    public Training (List<ExerciseState> trainingState) {
        this.trainingState = trainingState;
        this.startingTime = new Date(System.currentTimeMillis());
    }


    public List<ExerciseState> getTrainingState() {
        return this.trainingState;
    }

    public void setTrainingState(List<ExerciseState> trainingState) {
        this.trainingState = trainingState;
    }

    public Date getStartingTime() {
        return this.startingTime;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }


    public void setExerciseAsDone(ExerciseOfPlan exerciseOfPlan) {
        for (ExerciseState item : this.trainingState) {
            if (item.exerciseOfPlan == exerciseOfPlan) {
                item.done = true;
            }
        }
    }

    public void setExerciseAsNotDone(Exercise exercise) {
        for (ExerciseState item : this.trainingState) {
            if (item.exerciseOfPlan.exercise == exercise) {
                item.done = false;
            }
        }
    }

    public ExerciseOfPlan getNextExercise(ExerciseOfPlan exerciseOfPlan) {
        for (ExerciseState item : this.trainingState) {
            if (item.exerciseOfPlan == exerciseOfPlan) {
                int index = trainingState.indexOf(item);
                index++;
                if (index < trainingState.size()) {
                    return trainingState.get(index).exerciseOfPlan;
                }
            }
        }
        return null;
    }

    public int calculatePercentage() {
        int counter = 0;
        for (ExerciseState item : this.trainingState) {
            if (item.done) {
                counter++;
            }
        }
        return (counter / trainingState.size());
    }

}