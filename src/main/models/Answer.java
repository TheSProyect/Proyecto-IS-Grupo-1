package main.models;
import main.models.Line;

public class Answer {
    private int numOption;
    private boolean correct_option;
    private String name;
    private String justification;
    private Line[] lines;

    public void setAnswers(String answer, String justification, Answer answers){
        answers.name=answer;
        answers.justification= justification;
    }

    public String getAnswers(){
        return name;
    }
}
