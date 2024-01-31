package main.models;

import main.models.Answer;
import main.models.Line;

public class Question {
    private String statement;
    private String domain;
    private Line[] lines;
    private int numberAnswers;
    private Answer[] answers= new Answer[10];
    private int counter=0;
    
    public void setQuestions(String statement, String domain, Question question){
        question.statement=statement;
        question.domain=domain;
    }

    public void setAnswersQuestions(String answer, String justification, Question question, int i){
        //for(int j=0 ; j<i+1 ; j++){
            answers[i]= new Answer();
            answers[i].setAnswers(answer, justification, answers[i]);
        //}
    }

    public String getQuestions(){
        return statement;
    }

    public int getNumberAnswers(){
        return numberAnswers;
    }
    
    public String getAnswersQuestions(int counter){
        //return questions2.getQuestions();
        return answers[counter].getAnswers();
    }
    public String getOptionsQuestion(int counter){
        //return questions2.getQuestions();
        return answers[counter].getAnswers();
    }

    public String getCode(int counter){
        //return questions2.getQuestions();
        return answers[counter].getAnswers();
    }
    public String getDomain(){
        return domain;
    }

    public void setNumberAnswers(int number){
        numberAnswers=number;
    }
}
