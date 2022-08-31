package com.example.yourcali;

import java.util.ArrayList;
import java.util.Objects;

public class PersonalWorkoutPlan extends WorkoutPlan {

    public PersonalWorkoutPlan(String name, ArrayList<ExerciseOfPlan> exerciseList) {
        super(name, exerciseList);
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setExerciseList(ArrayList<ExerciseOfPlan> exerciseList) {
        this.exerciseList = exerciseList;
    }


    public void addExercise(ExerciseOfPlan exerciseOfPlan) {
        for (ExerciseOfPlan item : super.exerciseList) {
            if (Objects.equals(item.getExercise().getName(), exerciseOfPlan.getExercise().getName())) {
                return;
            }
        }
        exerciseList.add(exerciseOfPlan);
    }

    public void removeExercise(ExerciseOfPlan exerciseOfPlan) {
        super.exerciseList.remove(exerciseOfPlan);
    }

    public void editExercise (ExerciseOfPlan exerciseOfPlan) {
        for (ExerciseOfPlan item : super.exerciseList) {
            if (Objects.equals(item.getExercise().getName(), exerciseOfPlan.getExercise().getName())) {
                item.setSets(exerciseOfPlan.getSets());
                item.setRepsOrTime(exerciseOfPlan.getRepsOrTime());
                item.setExerciseUnity(exerciseOfPlan.getExerciseUnity());
                item.setRest(exerciseOfPlan.getRest());
                item.setRestUnity(exerciseOfPlan.getRestUnity());
            }
        }
    }

}

// NON SI POSSONO INSERIRE PIU' ESERCIZI UGUALI NELLA STESSA SCHEDA
// I METODI SONO DA PROVARE