package com.example.yourcali;

public class ExerciseOfSchedule {

    Exercise exercise;
    int sets, reps, rest;
    ExerciseUnity exerciseUnity;
    RestUnity restUnity;


    public ExerciseOfSchedule(Exercise exercise, int sets, int reps, int rest, ExerciseUnity exerciseUnity, RestUnity restUnity) {
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
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

    public int getReps() {
        return this.reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
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
