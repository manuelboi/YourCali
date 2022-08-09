package com.example.yourcali;

import android.media.Image;


public class Exercise {

    String name;
    Difficulty difficulty;
    String helpGuide;
    Image image;


    public Exercise(String name, Difficulty difficulty, String helpGuide, Image image) {
        this.name = name;
        this.difficulty = difficulty;
        this.helpGuide = helpGuide;
        this.image = image;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}