package main.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import main.utils.Directory;
import java.util.ArrayList;
import java.util.List;

public class Exam extends Course{
    private Course course = new Course();
    private Name name;
    private String type, instructions;
    private int numberQuestions, duration;
    private Result results;
    private List<Question> questions = new ArrayList<>();
    private List<String> description = new ArrayList<String>();
    
    public Exam(){
        Directory currentDirectory = Directory.instance();
        String directory = currentDirectory.getDirectoryExams()+File.separator+"Instructions.txt", line="";
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            while ((line = br.readLine()) != null) {
                instructions += line;
            }
            br.close(); 
            } catch (IOException e) {
                e.printStackTrace();
        }
    }
    public void setCode(List<String> code, int counter){
        questions.get(counter).setCode(code);
    }
    public String getInstructions(){
        return instructions;
    }
    public void setImageQuestion(boolean image, int counter){
        questions.get(counter).setImage(image);;
    }
    public boolean isImage(int counter){
        return questions.get(counter).isImage();
    }
    public void setHasCode(Boolean code, int counter){
        questions.get(counter).setHasCode(code);
    }
    public void setQuestionsExam(List<String> statement, String domain, int counter){
        questions.add(new Question());
        questions.get(counter).setQuestions(statement,domain);
    }
    public void setAnswersExam(List<String> answer, List<String> justification, int i, int counter){
        questions.get(counter).setAnswersQuestions(answer, justification, i);
    }
    
    public void setIsCorrectExam(Boolean isCorrect, int i, int counter){
        questions.get(counter).setIsCorrectQuestions(isCorrect, i);
    }

    public void setNumberAnswers(int counter, int number){
        questions.get(counter).setNumberAnswers(number);
    }
    public void setNumCorrectAsnwersExam(int counter){
        questions.get(counter).setNumCorrectAsnwers();
    }
    public void setResultExam(float numCorrectQuestions){
        results = new Result();
        results.setScore(numCorrectQuestions);
    }
   
    public float getResultExam(){
        return results.getScore();
    }
    public Boolean getHasCodeExam(int counter){
        return questions.get(counter).getHasCode();
    }
    public List<String> getQuestionsExam(int counter){
        return questions.get(counter).getQuestions();
    }
    
    public List<String> getJustificationExam(int counter, int i){
        return questions.get(counter).getJustificationQuestion(i);
    }
    public String getDomainExam(int counterQuestion){
        return questions.get(counterQuestion).getDomain();
    }
    
    public List<String> getOptionsExam(int counterQuestion, int counterAnswer){
        return questions.get(counterQuestion).getOptionsQuestion(counterAnswer);
    }
    public List<String> getCodeExam(int counterQuestion){
        return questions.get(counterQuestion).getCodeQuestion();
    }
    
    public int getNumCorrectAnswersExam(int counter){
        return questions.get(counter).getNumCorrectAnswers();
    }
    public int getNumberAnswersExam(int counter){
        return questions.get(counter).getNumberAnswers();
    }

    public List<String> getAnswersQuestionExam(int counter, int i){
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

    public void setType(String type){
        this.type=type;
    }

    public String getType(){
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

    public void setDescripcion(List<String> description){
        this.description=description;
    }
    
    public List<String> getDescription(){
        return description;
    }
}