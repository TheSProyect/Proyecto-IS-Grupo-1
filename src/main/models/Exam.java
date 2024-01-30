package main.models;

import java.util.List;

import main.models.Question;
import main.models.Result;

public class Exam {
    private String name;
    private String instructions;
    private int[] correctOptions;
    private Question[] questions;

    public Exam(String name, String instructions, int[] correctOptions, Question[] questions){
        this.name = name;
        this.instructions = instructions;
        this.correctOptions = correctOptions;
        this.questions = questions;
    }
    //private Questions getQuestion(){}
    public String getName(){
        return name;
    }
    public String getInstructions(){
        return instructions;
    }
    public int[] getcorrectOPtions(){
        return correctOptions;
    }
    public Question[] getQuestions(){   
        return questions;
    }

}
