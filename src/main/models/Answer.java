package main.models;
import main.models.Lines;

public class Answer {
    private int numOption;
    private boolean correct_option;
    private String name;
    private String justification;
    private Lines[] lines;

    public void setAnswers(String answer, String justification, Answer answers){
        answers.name=answer;
        answers.justification= justification;
    }
    public String getAnswers(){
        return name;
    }

    public void setIsCorrect(Boolean isCorrect, Answer answers){
        answers.correct_option=isCorrect;
    }

    public Boolean getIsCorrect(){
        return correct_option;
    }

    public String getJustification(){
        return justification;
    }
}
