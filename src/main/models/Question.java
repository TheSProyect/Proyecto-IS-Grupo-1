package main.models;

import main.models.Answer;
import main.models.Line;

public class Question {
    private String domain;
    private Line[] lines;
    private Answer[] answers;
    
    public String getDomain(){
        return domain;
    }
    public Line[] getLines(){
        return lines;
    }
    public Answer[] getAnswers(){
        return answers;
    }
}
