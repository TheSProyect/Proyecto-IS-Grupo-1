package main.models;

import java.util.List;

import java.util.ArrayList;

public class Question {
    private List<String> statement = new ArrayList<>();
    private String domain;
    private Line[] lines;
    private int numberAnswers;
    private int numCorrectAnswers=0;
    private List<Answer> answers = new ArrayList<>();
    private boolean image = false;
    private boolean hasCode =false;

    public void setImage(boolean image){
        this.image=image;
    }
    public boolean isImage(){
        return image;
    }
    
    public void setQuestions(List<String> statement, String domain, Question question){
        //question.statement=statement;
        //question.domain=domain;
        this.domain=domain;
        this.statement=statement;
    }

    public void setAnswersQuestions(List<String> answer, List<String> justification, int i){
        answers.get(i).setAnswers(answer, justification, answers.get(i));
    }

    public void setIsCorrectQuestions(Boolean isCorrect, int i){        
        answers.add(new Answer());
        answers.get(i).setIsCorrect(isCorrect,answers.get(i));
    }
    public List<String> getQuestions(){
        return statement;
    }

    public int getNumberAnswers(){
        return numberAnswers;
    }
    public int getNumCorrectAnswers(){
        return numCorrectAnswers;
    }
    
    public List<String> getAnswersQuestions(int counter){
        return answers.get(counter).getAnswers();
    }
    public List<String> getJustificationQuestion(int counter){
        return answers.get(counter).getJustification();
    }
    public Boolean getIsCorrectQuestion(int counter){
        return answers.get(counter).getIsCorrect();
    }
    public List<String> getOptionsQuestion(int counter){
        //return questions2.getQuestions();
        return answers.get(counter).getAnswers();
    }
    public List<String> getCode(int counter){
        //return questions2.getQuestions();
        return answers.get(counter).getAnswers();
    }
    public String getDomain(){
        return domain;
    }

    public void setNumberAnswers(int number){
        numberAnswers=number;
    }

    public void setNumCorrectAsnwers(){
        numCorrectAnswers++;
    }
    public Boolean getHasCode(){
        return hasCode;
    }
    public void setHasCode(Boolean hasCode){
        this.hasCode=hasCode;
    }
}
