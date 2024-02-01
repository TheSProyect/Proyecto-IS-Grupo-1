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

    public void setAnswersQuestions(String answer, String justification, int i){
            answers[i]= new Answers();
            answers[i].setAnswers(answer, justification, answers[i]);
    }

    public void setIsCorrectQuestions(Boolean isCorrect, int i){
        answers[i].setIsCorrect(isCorrect,answers[i]);
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
//
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
