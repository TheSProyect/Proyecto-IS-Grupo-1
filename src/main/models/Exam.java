package main.models;

import java.io.IOException;

import main.controllers.PresentExamController;
import main.models.Questions;
import main.models.Result;

public class Exam {
    private String name;
    private String instructions;
    private int numberQuestions;
    private int[] correctOptions;
    private Questions[] questions = new Questions[10];
    
    private Questions getQuestion(){
        return this.questions[0];
    }
    private String getInstructions(){
        return instructions;
    }
   
    public void setQuestionsExam(String statement, String domain, int counter){
        questions[counter]=new Questions();
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
    public String getDomainExam(int counter){
        //return questions2.getQuestions();
        return questions[counter].getQuestions();
    }
    public int getNumberQuestionExam(int counter){
        return questions[counter].getNumberQuestion();
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
