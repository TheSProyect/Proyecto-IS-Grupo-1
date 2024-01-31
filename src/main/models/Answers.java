package main.models;
import main.models.Lines;

public class Answers {
    private int numOption;
    private boolean correct_option;
    private String name;
    private String justification;
    private Lines[] lines;

    public void setAnswers(String answer, String justification, Answers answers){
        answers.name=answer;
        answers.justification= justification;
    }

    public String getAnswers(){
        return justification;
    }
}