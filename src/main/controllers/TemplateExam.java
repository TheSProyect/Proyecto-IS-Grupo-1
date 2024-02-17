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
    public void readAnswersAndJustifications(BufferedReader br, int counter){
        String line;
        List<List<String>> answers = new ArrayList<>(), justifications = new ArrayList<>();
        try{
            for (int i=0; ((line = br.readLine()) != null); i++) {
                int amountLines = Integer.parseInt(line);
                List<String> answer = new ArrayList<String>(), justification = new ArrayList<String>();
                for(int j=0; j < amountLines; j++){
                    if ((line = br.readLine()) != null && j==0 && line.substring(0, 1).equalsIgnoreCase("v")) {
                        answer.add(line.substring(1));
                        currentExam.setIsCorrectExam(true, i, counter);
                        currentExam.setNumCorrectAsnwersExam(counter);
                        } else if(j==0 && line.substring(0, 1).equalsIgnoreCase("f")){
                            answer.add(line.substring(1));
                            currentExam.setIsCorrectExam(false, i, counter);
                        } else if( j>0 && line != null ){
                            answer.add(line);    
                    }
                }
                answers.add(answer);
                for(int j=0 ; j < amountLines; j++){
                    justification.add(br.readLine());
                }
                justifications.add(justification);
                currentExam.setAnswersExam(answers.get(i),justifications.get(i), i, counter);
                currentExam.setNumberAnswers(counter, i+1);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readQuestion(String directory, int readings, int counter, int stop){
        String line = null;
        Boolean[] hasCode={false};
        List<String> code = new ArrayList<String>(), questionStatement = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            currentExam.setQuestionsExam((readInformationQuestion(br, questionStatement, Integer.parseInt(br.readLine()))),(br.readLine()),counter);
            if(!((line=br.readLine()).equals("No"))){
                readCode(br, code, Integer.parseInt(line),hasCode);
                currentExam.setCode(code, counter);
                currentExam.setHasCode(hasCode[0], counter);
            }
            if(br.readLine().equals("Si")){
                currentExam.setImageQuestion(true, counter);
            }
            readAnswersAndJustifications(br, counter);
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
            String statement = "";
            for(int k=0; k<currentExam.getQuestionsExam(i).size(); k++){
                statement= statement + currentExam.getQuestionsExam(i).get(k) + "\n";
            }
            questionsString.add(statement);    
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
            hasCode.add(currentExam.getHasCodeExam(i));
        }
        return hasCode;
    }

    public List<List<String>> getCode(){
        int j=currentExam.getNumberQuestions();
        List<List<String>> code = new ArrayList<List<String>>();
        for(int i=0; i<j; i++){ 
            code.add(new ArrayList<String>());
            String statement= "";
            if(currentExam.getHasCodeExam(i)){
                for(int k=0 ; k<currentExam.getCodeExam(i).size(); k++){
                    statement = statement + currentExam.getCodeExam(i).get(k) + "\n";
                }
            }
            code.get(i).add(statement); 
        }
        return code;
    }

    public List<String> getDirectoryImage(){
        int j=currentExam.getNumberQuestions();
        List<String> directoryImage = new ArrayList<String>();
        for(int i=0; i<j; i++){
            if(currentExam.isImage(i)){
                int currentQuestion = i + 1;
                directoryImage.add(currentDirectory.getDirectoryExams()+ File.separator + currentExam.getNameCourse()+ File.separator+ currentExam.getNameExam()+File.separator+ "Pregunta"+ currentQuestion + ".jpg");
                directoryImage.get(i).replace(" ", "-");
            } else{
                directoryImage.add(null);
            }
        }
        return directoryImage;
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
                String statement= "";
                for(int l=0 ; l<currentExam.getOptionsExam(i,k).size(); l++){
                    statement = statement + currentExam.getOptionsExam(i,k).get(l) + "\n";
                }
                options.get(i).add(statement);
            }
        }
        return options;
    }

    public int getDuracion(){
        return currentExam.getDuration();
    }

    public int getNumCorrectAnswersController(int counter){
        return currentExam.getNumCorrectAnswersExam(counter);
    }
    
    public Boolean isCorrect(int indexQuestion, int indexSelectedAnswer){
        return currentExam.getIsCorrectExam(indexQuestion, indexSelectedAnswer);
    }

    public void setResultExamC(float numCorrectQuestions){
        currentExam.setResultExam(numCorrectQuestions);
    }  
}
