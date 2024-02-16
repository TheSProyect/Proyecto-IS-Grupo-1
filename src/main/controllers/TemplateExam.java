package main.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
    public List<String> readInformationQuestion(BufferedReader br, List<String> data, int sizeData){
        try{
            for(int i=0; i < sizeData; i++){
                data.add(br.readLine());
            }
            } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    public List<String> readCode(BufferedReader br, List<String> code, int sizeCode, Boolean[] hasCode){
        try{
            for(int i=0; i < sizeCode; i++){
                String line=br.readLine();
                if(!line.isEmpty()){
                    hasCode[0]=true;
                    code.add(line);
                }
            }
            } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
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
}
