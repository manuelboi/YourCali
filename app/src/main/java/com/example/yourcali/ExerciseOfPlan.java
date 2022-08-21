package com.example.yourcali;

import java.io.Serializable;

public class ExerciseOfPlan implements Serializable {

    private Exercise exercise;
    private int sets, repsOrTime, rest;
    private ExerciseUnity exerciseUnity;
    private RestUnity restUnity;


    public ExerciseOfPlan(Exercise exercise, int sets, int reps, int rest, ExerciseUnity exerciseUnity, RestUnity restUnity) {
        this.exercise = exercise;
        this.sets = sets;
        this.repsOrTime = reps;
        this.rest = rest;
        this.exerciseUnity = exerciseUnity;
        this.restUnity = restUnity;
    }


    public Exercise getExercise() {
        return this.exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public int getSets() {
        return this.sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getRepsOrTime() {
        return this.repsOrTime;
    }

    public void setRepsOrTime(int repsOrTime) {
        this.repsOrTime = repsOrTime;
    }

    public int getRest() {
        return this.rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    public ExerciseUnity getExerciseUnity() {
        return exerciseUnity;
    }

    public void setExerciseUnity(ExerciseUnity exerciseUnity) {
        this.exerciseUnity = exerciseUnity;
    }

    public RestUnity getRestUnity() {
        return restUnity;
    }

    public void setRestUnity(RestUnity restUnity) {
        this.restUnity = restUnity;
    }

}