package com.example.yourcali;

import java.util.Date;
import java.util.List;

public class Training {

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
