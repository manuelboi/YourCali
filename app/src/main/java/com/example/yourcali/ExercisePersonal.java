package com.example.yourcali;

import android.net.Uri;

public class ExercisePersonal extends Exercise {

    String image;

    public ExercisePersonal (String name, BodyPart bodyPart, Difficulty difficulty, String helpGuide,String image){
        super(name, bodyPart, difficulty, helpGuide);
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
