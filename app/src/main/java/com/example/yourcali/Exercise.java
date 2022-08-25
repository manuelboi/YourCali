package com.example.yourcali;

import android.media.Image;
import android.net.Uri;

import java.io.Serializable;
import java.net.URI;


public class Exercise implements Serializable {

    private String name;
    private BodyPart bodyPart;
    private Difficulty difficulty;
    private String helpGuide;


    public Exercise(String name, BodyPart bodyPart, Difficulty difficulty, String helpGuide) {
        this.name = name;
        this.bodyPart = bodyPart;
        this.difficulty = difficulty;
        this.helpGuide = helpGuide;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BodyPart getBodyPart() {
        return this.bodyPart;
    }

    public void setBodyPart(BodyPart bodyPart) {
        this.bodyPart = bodyPart;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getHelpGuide() {
        return this.helpGuide;
    }

    public void setHelpGuide(String helpGuide) {
        this.helpGuide = helpGuide;
    }

}