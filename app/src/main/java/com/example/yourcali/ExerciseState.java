package com.example.yourcali;

import java.io.Serializable;

public class ExerciseState implements Serializable {

    private ExerciseOfPlan exerciseOfPlan;
    private boolean done;

    public ExerciseState (ExerciseOfPlan exerciseOfPlan, boolean done) {
        this.exerciseOfPlan = exerciseOfPlan;
        this.done = done;
    }

    public ExerciseOfPlan getExerciseOfPlan() {
        return exerciseOfPlan;
    }

    public boolean getDone() {
        return done;
    }

    public void setExerciseOfPlan(ExerciseOfPlan exerciseOfPlan) {
        this.exerciseOfPlan = exerciseOfPlan;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}