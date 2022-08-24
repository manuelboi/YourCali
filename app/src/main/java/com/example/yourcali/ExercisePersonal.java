package com.example.yourcali;

import java.util.ArrayList;

public class ExercisePersonal extends Exercise{

    public ExercisePersonal (String name, BodyPart bodyPart, Difficulty difficulty, String helpGuide){
        super(name, bodyPart, difficulty, helpGuide);
    }

    public void modifiedPersonalExerciseName(String name){
        this.setName(name);
    }

    public void modifiedPersonalExerciseHelpGuide(String helpGuide){
        this.setHelpGuide(helpGuide);
    }

    public void removePersonalExercise(ArrayList<ExercisePersonal> list){
        list.remove(this);
    }


}
