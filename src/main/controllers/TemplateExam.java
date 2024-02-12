package main.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.models.Exam;
import main.utils.Directory;
import main.utils.UserData;

public class TemplateExam {
    public Directory currentDirectory = Directory.instance();
    public Exam currentExam = new Exam();
    public UserData currentUser = UserData.instance();

    public String verifyAdmin(){
        if(currentUser.isAdmin()){
            return (currentDirectory.getDirectoryTeachers())+ File.separator + currentUser.getUsername();
            } else {
                return (currentDirectory.getDirectoryStudents())+ File.separator + currentUser.getUsername();
        }   
    }

    public int getNumberQuestion(String directory, String nameFolder){
        int LINE_NUMBER_QUESTION = 2;
        int numberQuestion = 0;
        directory = directory +File.separator+nameFolder+File.separator+nameFolder+".txt";
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            for(int i=0; i<=LINE_NUMBER_QUESTION; i++){
                br.readLine();
                if(i==LINE_NUMBER_QUESTION-1){
                    numberQuestion = Integer.parseInt((br.readLine()));
                    }
                }
            return numberQuestion;
            } catch (IOException e) {
                    e.printStackTrace();
        }
        return numberQuestion;
    }
    
    public String changeDirectory(String directory){
        int REMOVE_QUESTION_FROM_DIRECTORY = 13;
        int REMOVE_FILE_TYPE = 4;
        String question = (directory.substring(directory.length() - REMOVE_QUESTION_FROM_DIRECTORY));
        question = question.substring(0, question.length() - REMOVE_FILE_TYPE);
        directory = directory.substring(0, directory.length() - REMOVE_QUESTION_FROM_DIRECTORY);
        return (directory +File.separator+changeNumberQuestion(question)+".txt");
    }
    
    public String changeNumberQuestion(String question){
        int REMOVE_NUMBER_QUESTION = 1;
        if (question != null && question.length() > 0) {
            char lastCharacter = question.charAt(question.length() - REMOVE_NUMBER_QUESTION);
            if (Character.isDigit(lastCharacter)) {
                int number = Character.getNumericValue(lastCharacter);
                number++;
                char newCharacter = Character.forDigit(number, 10);
                return question.substring(0, question.length() - REMOVE_NUMBER_QUESTION) + newCharacter; 
            }
        }
        return question;
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
        for(int i=0; i<j; i++){ 
            code.add(new ArrayList<String>());
            for(int k=0; k<currentExam.getNumberAnswersExam(i); k++){
                code.get(i).add(currentExam.getOptionsExam(i,k));
            }
        }
        return code;
    }

    public List<List<String>> getJustification(){
        int j=currentExam.getNumberQuestions();
        List<List<String>> justification = new ArrayList<List<String>>();
        for(int i=0; i<j; i++){ 
            justification.add(new ArrayList<String>());
            for(int k=0; k<currentExam.getNumberAnswersExam(i); k++){
                String isCorrect;
                if(currentExam.getIsCorrectExam(i, k)){
                    isCorrect= "Correcto";
                }else{
                    isCorrect="Incorrecto";
                }
                justification.get(i).add("Opcion "+(k+1)+"<br>"+isCorrect+"<br>"+currentExam.getJustificationExam(i,k));
            }
        }
        return justification;
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
        return currentExam.getDuration();
    }
}
