package main.models;

import main.models.Answers;
import main.models.Lines;

public class Questions {
    private String statement;
    private String domain;
    private Lines[] lines;
    private int numberAnswers;
    private Answers[] answers= new Answers[10];
    private int counter=0;
    
    public void setQuestions(String statement, String domain, Questions question){
        question.statement=statement;
        question.domain=domain;
    }

    public void setAnswersQuestions(String answer, String justification, Questions question, int i){
        for(int j=0 ; j<i+1 ; j++){
            answers[j]= new Answers();
            answers[j].setAnswers(answer, justification, answers[j]);
        }
    }

    public String getQuestions(){
        return statement;
    }

    public int getNumberQuestion(){
        return 0;
    }
    
    public String getAnswersQuestions(int counter){
        //return questions2.getQuestions();
        return answers[counter].getAnswers();
    }

    public void setNumberAnswers(int number){
        numberAnswers=number;
    }
}
