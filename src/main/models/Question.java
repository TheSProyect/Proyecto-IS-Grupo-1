package main.models;

import java.util.List;

import java.util.ArrayList;
import main.models.Answer;
import main.models.Line;

public class Question {
    private String statement;
    private String domain;
    private Line[] lines;
    private int numberAnswers;
    private List<Answer> answers = new ArrayList<>(); 
    //private int counter=0;

    
    public void setQuestions(String statement, String domain, Question question){
        question.statement=statement;
        question.domain=domain;
    }

    public void setAnswersQuestions(String answer, String justification, int i){
        answers.get(i).setAnswers(answer, justification, answers.get(i));
    }

    public void setIsCorrectQuestions(Boolean isCorrect, int i){
        answers.add(new Answer());
        answers.get(i).setIsCorrect(isCorrect,answers.get(i));
}

    public String getQuestions(){
        return statement;
    }

    public int getNumberAnswers(){
        return numberAnswers;
    }
    
    public String getAnswersQuestions(int counter){
        return answers.get(counter).getAnswers();
    }
    public String getJustificationQuestion(int counter){
        return answers.get(counter).getJustification();
    }
    public Boolean getIsCorrectQuestion(int counter){
        return answers.get(counter).getIsCorrect();
    }
    public String getOptionsQuestion(int counter){
        //return questions2.getQuestions();
        return answers.get(counter).getAnswers();
    }
    public String getCode(int counter){
        //return questions2.getQuestions();
        return answers.get(counter).getAnswers();
    }
    public String getDomain(){
        return domain;
    }

    public void setNumberAnswers(int number){
        numberAnswers=number;
    }
}
