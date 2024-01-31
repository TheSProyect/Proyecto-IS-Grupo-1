package main.models;

import java.util.List;
import java.io.IOException;

import main.controllers.PresentExamController;
import main.models.Result;

public class Exam {
    private String name;
    private String instructions;
    private int numberQuestions;
    private int[] correctOptions;
    private Question[] questions = new Question[10];
    
    private Question getQuestion(){
        return this.questions[0];
    }
    private String getInstructions(){
        return instructions;
    }
   
    public void setQuestionsExam(String statement, String domain, int counter){
        questions[counter]=new Question();
        questions[counter].setQuestions(statement,domain,questions[counter]);
    }

    public void setAnswersExam(String answer, String justification, int i, int counter){
        questions[counter].setAnswersQuestions(answer, justification, questions[counter],i);
    }

    public void setNumberAnswers(int counter, int number){
        questions[counter].setNumberAnswers(number);
    }

    public String getQuestionsExam(int counter){
        //return questions2.getQuestions();
        return questions[counter].getQuestions();
    }
    public String getDomainExam(int counterQ){
        return questions[counterQ].getDomain();
    }
    public String getOptionsExam(int counterQuestion, int counterAnswer){
        return questions[counterQuestion].getOptionsQuestion(counterAnswer);
    }
    public int getNumberAnswersExam(int counter){
        return questions[counter].getNumberAnswers();
    }

    public String getAnswersQuestionExam(int counter, int i){
        return questions[counter].getAnswersQuestions(i);
    }

    public void setNumberQuestions(int number){
        numberQuestions=number;
    }

    public int getNumberQuestions(){
        return numberQuestions;
    }
}
