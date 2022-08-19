package com.example.yourcali;

import java.io.Serializable;

public class ExerciseState implements Serializable {

    ExerciseOfPlan exerciseOfPlan;
    boolean done;

    public ExerciseState (ExerciseOfPlan exerciseOfPlan, boolean done) {
        this.exerciseOfPlan = exerciseOfPlan;
        this.done = done;
    }

}