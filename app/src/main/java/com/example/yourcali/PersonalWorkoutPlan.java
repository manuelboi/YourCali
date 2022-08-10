package com.example.yourcali;

import java.util.List;

public class PersonalWorkoutPlan extends WorkoutPlan {

    public PersonalWorkoutPlan(String name, String description, List<ExerciseOfPlan> exerciseList) {
        super(name, description, exerciseList);
    }


    public boolean addExercise(ExerciseOfPlan exerciseOfPlan) {
        for (ExerciseOfPlan item : super.exerciseList) {
            if (item == exerciseOfPlan) {
                return false;
            }
        }
        super.exerciseList.add(exerciseOfPlan);
        return true;
    }

    public void removeExercise(ExerciseOfPlan exerciseOfPlan) {
        super.exerciseList.remove(exerciseOfPlan);
    }

    public void editExercise (ExerciseOfPlan exerciseOfPlan) {
        for (ExerciseOfPlan item : super.exerciseList) {
            if (item == exerciseOfPlan) {
                item.setSets(exerciseOfPlan.getSets());
                item.setReps(exerciseOfPlan.getReps());
                item.setExerciseUnity(exerciseOfPlan.getExerciseUnity());
                item.setRest(exerciseOfPlan.getRest());
                item.setRestUnity(exerciseOfPlan.getRestUnity());
            }
        }
    }

}

// MOMENTANEAMENTE NON SI POSSONO INSERIRE PIU' ESERCIZI UGUALI NELLA STESSA SCHEDA
