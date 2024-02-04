package main.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import main.controllers.PresentExamController;
import main.models.Question;
import main.models.Result;
import main.models.Name;

public class Exam extends Course{
    private Course course = new Course();
    private Name name;
    private String type, instructions, description;
    private int numberQuestions, duration;
    private Result results;
    private int[] correctOptions;
    private Question[] questions = new Question[10];
    //private UserData usuario;
    
    public Exam(){
        String directory = System.getProperty("user.dir");
        directory = directory+File.separator+"src"+File.separator+"data"+File.separator+"Exams"+File.separator+"Instructions.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            instructions = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
        }
    }
    //public Exam(UserData user){
       // this.usuario=user;
    //}

    //public String getUsuario(){
     //   return usuario.getUsername();
    //}
    private Question getQuestion(){
        return this.questions[0];
    }
    
    public String getInstructions(){
        return instructions;
    }
   
    public void setQuestionsExam(String statement, String domain, int counter){
        questions[counter]=new Question();
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
    public void setResultExam(int numCorrectQuestions){
        results = new Result();
        results.setScore(numCorrectQuestions);
    }
    public int getResultExam(){
        return results.getScore();
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

    public Boolean getIsCorrectExam(int counter, int i){
        return questions[counter].getIsCorrectQuestion(i);
    }
    public void setNumberQuestions(int number){
        numberQuestions=number;
    }

    public int getNumberQuestions(){
        return numberQuestions;
    }

    public void setNameCourse(String nameCourse){
        course.setNameCourse(nameCourse);
    }

    public String getNameCourse(){
        return course.getNameCourse();
    }
    public void setNameExam(String nameExam){
        name = new Name();
        name.setName(nameExam);
    }
    public String getNameExam(){
        return name.getName();
    }

    public void setTipo(String type){
        this.type=type;
    }

    public String getTypeExam(){
        return type;
    }

    public void setTeacherName(String name){
        course.setNameTeacherCourse(name);
    }
    public String getNameTeacher(){
        return course.getNameTeacher();
    }

    public void setDuration(int duration){
        this.duration=duration;
    }
    public int getDuration(){
        return duration;
    }

    public void setDescripcion(String description){
        this.description=description;
    }
    public String getDescription(){
        return description;
    }
    public boolean isVisit(){
        return course.isVisit();
    }
    public void setVisit(boolean visit){
        course.setVisit(visit);
    }
}