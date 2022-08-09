package com.example.yourcali;

import java.util.List;

public class PersonalExerciseSchedule extends ExerciseSchedule {

    public PersonalExerciseSchedule(String name, String description, List<ExerciseOfSchedule> exerciseList) {
        super(name, description, exerciseList);
    }


    public void addExercise(ExerciseOfSchedule exerciseOfSchedule) {
        super.exerciseList.add(exerciseOfSchedule);
    }

    public void removeExercise(ExerciseOfSchedule exerciseOfSchedule) {
        super.exerciseList.remove(exerciseOfSchedule);
    }

    public void editExercise (ExerciseOfSchedule exerciseOfSchedule) {
        for (ExerciseOfSchedule item : super.exerciseList) {
            if (item == exerciseOfSchedule) {
                item.setSets(exerciseOfSchedule.getSets());
                item.setReps(exerciseOfSchedule.getReps());
                item.setExerciseUnity(exerciseOfSchedule.getExerciseUnity());
                item.setRest(exerciseOfSchedule.getRest());
                item.setRestUnity(exerciseOfSchedule.getRestUnity());
            }
        }
    }

    // DA RISOLVERE IL PROBLEMA CHE NELLA SCHEDA CI POSSONO ESSERE PIU' ESERCIZI UGUALI (FORSE DA RISOLVERE CON ID), CHE PUO' CREARE PROBLEMI QUANDO SI VA A MODIFICARE L'ESERCIZIO
}
