package main.models;

import java.io.IOException;

import main.controllers.PresentExamController;
import main.models.Questions;
import main.models.Result;

public class Exam {
    private String name, tipo, instructions, teacherName, descripcion;
    private int numberQuestions, duracion;
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
        questions[counter].setAnswersQuestions(answer, justification,i);
    }
    public void setIsCorrectExam(Boolean isCorrect, int i, int counter){
        questions[counter].setIsCorrectQuestions(isCorrect, i);

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
//
    public void setName(String nameExam){
        name=nameExam;
    }

    public String getName(){
        return name;
    }

    public void setTipo(String tipoExam){
        tipo=tipoExam;
    }

    public String getTipo(){
        return tipo;
    }

    public void setTeacherName(String name){
        teacherName=name;
    }
    public String getTeacherName(){
        return teacherName;
    }

    public void setDuracion(int tiempo){
        duracion=tiempo;
    }
    public int getDuracion(){
        return duracion;
    }

    public void setDescripcion(String descripcionExam){
        descripcion=descripcionExam;
    }
    public String getDescripcion(){
        return descripcion;
    }
    
}

