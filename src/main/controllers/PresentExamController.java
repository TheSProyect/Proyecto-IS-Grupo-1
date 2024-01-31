package main.controllers;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.models.Answers;
import main.models.Questions;
import main.models.Result;
import main.models.Exam;
import javax.swing.*;

public class PresentExamController {
    //private void setResult(Option){}
    //private void chooseExam(Exam_Name){}
    private int counter=0;
    Exam currentExam = new Exam();

    public PresentExamController(){
        
        this.searchFolder();
    }
    private void startExam(){}
    private Answers getAnswers(){
        return this.getAnswers();
    }
    private Questions getQuestions(){
        return this.getQuestions();
    }
    public static void main(String[] args) throws IOException{
        PresentExamController p = new PresentExamController();
        p.searchFolder();    
    }

    private void readQuestion(String directory, int readings, int counter, int stop){
        String questionStatement, line, domain;
        String[] answer = new String[10];
        String[] justification = new String[10];
        boolean answerCorrect = true; 
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            questionStatement = br.readLine();
            domain = br.readLine();
            currentExam.setQuestionsExam(questionStatement,domain,counter);
            
            for (int i =0; ((line = br.readLine()) != null); i++) {
                if (line != null && line.length() > 0 && answerCorrect && line.substring(0, 1).equalsIgnoreCase("v")) {
                    answer[i]= line.substring(1);
                    justification[i]= br.readLine();
                    } else {
                        answer[i]= line;
                        justification[i]= br.readLine();
                        }
                    currentExam.setAnswersExam(answer[i],justification[i], i, counter);
                    currentExam.setNumberAnswers(counter, i);
                    }
                    br.close();     
                } catch (IOException e) {
                    e.printStackTrace();
            }
                if(readings==stop) {
                    return;
                    } else {
                        counter++;
                    readQuestion(changeDirectory(directory),readings+1, counter, stop);
        }   
    }
    private void readExam(String directory, String nameFolder){
        String examName, tipoExam, teacherName,descripcion;
        int numberQuestions, duracion;
        directory = directory +"\\"+ nameFolder+"\\"+nameFolder+".txt";
        
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            examName = br.readLine();
            currentExam.setName(examName);

            tipoExam = br.readLine();
            currentExam.setTipo(tipoExam);
            
            numberQuestions = Integer.parseInt((br.readLine()));
            currentExam.setNumberQuestions(numberQuestions);

            teacherName = br.readLine();
            currentExam.setTeacherName(teacherName);

            duracion = Integer.parseInt((br.readLine()));
            currentExam.setDuracion(duracion);

            descripcion = br.readLine();
            currentExam.setDescripcion(descripcion);

        }catch (IOException e) {
                e.printStackTrace();
        }
            
            
       // currentExam.setNumberQuestions(stop);

    }

    private int getNumberQuestion(String directory, String nameFolder){
        int lineNumberQuestion = 2;
        int numberQuestion = 0;
        directory = directory +"\\"+ nameFolder+"\\"+nameFolder+".txt";
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            for(int i=0; i<=lineNumberQuestion; i++){
                br.readLine();
                if(i==lineNumberQuestion-1){
                    numberQuestion = Integer.parseInt((br.readLine()));
                    }
                }
            return numberQuestion;
            } catch (IOException e) {
                    e.printStackTrace();
        }
        return numberQuestion;
    }
    
    private String changeDirectory(String directory){
        int removeQuestionFromDirectory = 13;
        int removeFyleType = 4;
        String question = (directory.substring(directory.length() - removeQuestionFromDirectory));
        question = question.substring(0, question.length() - removeFyleType);
        directory = directory.substring(0, directory.length() - removeQuestionFromDirectory);
            return (directory +"\\"+changeNumberQuestion(question)+".txt");
    }
    
    private String changeNumberQuestion(String question){
        int removeNumberOfQuestion = 1;
        if (question != null && question.length() > 0) {
            char lastCharacter = question.charAt(question.length() - removeNumberOfQuestion);
            if (Character.isDigit(lastCharacter)) {
                int number = Character.getNumericValue(lastCharacter);
                number++;
                char newCharacter = Character.forDigit(number, 10);
                return question.substring(0, question.length() - removeNumberOfQuestion) + newCharacter; 
            }
        }
        return question;
    }  

    public void searchFolder() {
        String directory = System.getProperty("user.dir");
        directory = directory+"\\"+"Exams";
        //get para obtener nombre del examen
        String nameFolder = "Examen1";
        File searchedFolder = new File(directory);
        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();
            if (files != null) {
                int stop = getNumberQuestion(directory, nameFolder);
                    readExam(directory,nameFolder);
                for (File file : files) {
                    if (file.isDirectory() && file.getName().equals(nameFolder)) {
                        stop = getNumberQuestion(directory, nameFolder);
                        String question = "Pregunta1.txt";
                        directory = directory + "\\"+ nameFolder + "\\"+ question;
                        readQuestion(directory,1,counter, stop);
                        return;
                    }
                }
           }    
        }
    }

    public List<String> getQuestionsStrings(){
        int j=currentExam.getNumberQuestions();
        List<String> questionsString = new ArrayList<String>();
        for(int i=0; i<j; i++){ 
            questionsString.add(currentExam.getQuestionsExam(i));
        }
        return questionsString;
    }

    public List<String> getDomain(){
        int j=currentExam.getNumberQuestions();
        List<String> domain = new ArrayList<String>();
        for(int i=0; i<j; i++){ 
                domain.add(currentExam.getDomainExam(i));
        }
        return domain;
    }

    public List<Boolean> getHasCode(){
        int j=currentExam.getNumberQuestions();
        List<Boolean> hasCode = new ArrayList<Boolean>();
        //falta arreglar
        for(int i=0; i<j; i++){ 
            hasCode.add(true);
        }
        return hasCode;
    }
    public List<List<String>> getCode(){
        int j=currentExam.getNumberQuestions();
        List<List<String>> code = new ArrayList<List<String>>();
        //falta arreglar
        for(int i=0; i<j; i++){ 
            code.add(new ArrayList<String>());
            for(int k=0; k<currentExam.getNumberAnswersExam(i); k++){
                code.get(i).add(currentExam.getOptionsExam(i,k));
            }
        }
        return code;
    }
    
    public List<List<String>> getOptions(){
        int j=currentExam.getNumberQuestions();
        List<List<String>> options = new ArrayList<List<String>>();
        for(int i=0; i<j; i++){ 
            options.add(new ArrayList<String>());
            for(int k=0; k<currentExam.getNumberAnswersExam(i); k++){
                options.get(i).add(currentExam.getOptionsExam(i,k));
            }
        }
        return options;
    }

    public int getDuracion(){
        return currentExam.getDuracion();
    }
}
