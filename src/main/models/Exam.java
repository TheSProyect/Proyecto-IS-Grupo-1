package main.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import main.controllers.PresentExamController;
import main.models.Question;
import main.models.Result;
import main.utils.Directory;
import main.models.Name;
import java.util.ArrayList;
import java.util.List;

public class Exam extends Course{
    private Course course = new Course();
    private Name name;
    private String type, instructions, description;
    private int numberQuestions, duration;
    private Result results;
    private List<Question> questions = new ArrayList<>();
    
    public Exam(){
        Directory currentDirectory = Directory.instance();
        String directory = currentDirectory.getDirectoryExams()+File.separator+"Instructions.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            instructions = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
        }
    }
    
    public String getInstructions(){
        return instructions;
    }
   
    public void setQuestionsExam(String statement, String domain, int counter){
        questions.add(new Question());
        questions.get(counter).setQuestions(statement,domain,questions.get(counter));
    }
    public void setAnswersExam(String answer, String justification, int i, int counter){
        questions.get(counter).setAnswersQuestions(answer, justification, i);
    }
    public void setIsCorrectExam(Boolean isCorrect, int i, int counter){
        questions.get(counter).setIsCorrectQuestions(isCorrect, i);
    }

    public void setNumberAnswers(int counter, int number){
        questions.get(counter).setNumberAnswers(number);
    }
    public void setResultExam(int numCorrectQuestions){
        results = new Result();
        results.setScore(numCorrectQuestions);
    }
    public int getResultExam(){
        return results.getScore();
    }
    public String getQuestionsExam(int counter){
        return questions.get(counter).getQuestions();
    }
    public String getJustificationExam(int counter, int i){
        return questions.get(counter).getJustificationQuestion(i);
    }
    public String getDomainExam(int counterQuestion){
        return questions.get(counterQuestion).getDomain();
    }
    public String getOptionsExam(int counterQuestion, int counterAnswer){
        return questions.get(counterQuestion).getOptionsQuestion(counterAnswer);
    }
    public int getNumberAnswersExam(int counter){
        return questions.get(counter).getNumberAnswers();
    }

    public String getAnswersQuestionExam(int counter, int i){
        return questions.get(counter).getAnswersQuestions(i);
    }

    public Boolean getIsCorrectExam(int counter, int i){
        return questions.get(counter).getIsCorrectQuestion(i);
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

    public void setNameTeacher(String name){
        course.setNameTeacher(name);
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
}