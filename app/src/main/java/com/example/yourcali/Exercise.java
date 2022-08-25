package com.example.yourcali;

import java.io.Serializable;


public class Exercise implements Serializable {

    private String name;
    private BodyPart bodyPart;
    private Difficulty difficulty;
    private String helpGuide;
    private String image;


    public Exercise(String name, BodyPart bodyPart, Difficulty difficulty, String helpGuide, String image) {
        this.name = name;
        this.bodyPart = bodyPart;
        this.difficulty = difficulty;
        this.helpGuide = helpGuide;
        this.image = image;
    }


    public String getName() {
        return this.name;
    }

    public BodyPart getBodyPart() {
        return this.bodyPart;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public String getHelpGuide() {
        return this.helpGuide;
    }

    public String getImage() {
        return image;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setBodyPart(BodyPart bodyPart) {
        this.bodyPart = bodyPart;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setHelpGuide(String helpGuide) {
        this.helpGuide = helpGuide;
    }

    public void setImage(String image) {
        this.image = image;
    }
}