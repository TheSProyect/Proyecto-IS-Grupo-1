package main.models;

import main.models.Questions;
import main.models.Result;

public class Exam {
    private String name;
    private String instructions;
    private int[] correctOptions;
    private Questions[] questions;
    //private Questions getQuestion(){}
    private String getInstructions(){
        return instructions;
    }
}
