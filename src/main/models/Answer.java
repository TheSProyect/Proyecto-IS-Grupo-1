package main.models;

import java.util.List;
import java.util.ArrayList;

public class Answer {
    private boolean correct_option;
    private List<String> text = new ArrayList<>();
    private List<String> justification = new ArrayList<>();
    private Line[] lines;

    public void setAnswers(List<String> answer, List<String> justification, Answer answers){
        answers.text=answer;
        answers.justification= justification;
    }
    public List<String> getAnswers(){
        return text;
    }

    public void setIsCorrect(Boolean isCorrect, Answer answers){
        answers.correct_option=isCorrect;
    }

    public Boolean getIsCorrect(){
        return correct_option;
    }

    public List<String> getJustification(){
        return justification;
    }
}
