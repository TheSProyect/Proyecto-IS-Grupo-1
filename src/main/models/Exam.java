package main.models;

import main.models.Questions;

public class Exam {
    private String name;
    private String instructions;
    private int[] correctOptions;
    //private Questions getQuestion(){}
    private String getInstructions(){
        return instructions;
    }
}
