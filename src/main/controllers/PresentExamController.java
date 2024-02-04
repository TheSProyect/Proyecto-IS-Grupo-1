package main.controllers;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.models.Answer;
import main.models.Question;
import main.models.Result;
import main.utils.UserData;
import main.models.Exam;
import javax.swing.*;

public class PresentExamController {
    //private void setResult(Option){}
    //private void chooseExam(Exam_Name){}
    Exam currentExam = new Exam();

    public PresentExamController(){
        this.searchFolder();
    }
    private void startExam(){}
    private Answer getAnswers(){
        return this.getAnswers();
    }
    private Question getQuestions(){
        return this.getQuestions();
    }
    public static void main(String[] args) throws IOException{
        PresentExamController p = new PresentExamController();
        p.searchFolder();    
    }

    public void examFinished(){
        UserData data = new UserData(); 
        String nameFolderStudent = data.getUsername();
        String directory = System.getProperty("user.dir");
        directory = directory+File.separator+"src"+File.separator+"data"+File.separator+"Users"+File.separator+"Students"+ File.separator+nameFolderStudent;
        //String nameCourse = currentExam.getNameCourse();
        //String nameExam = currentExam.getNameExam();
        String nameCourse = "Course1.txt";
        String nameExam = "Exam1";
        try {
            File file = new File(directory, nameCourse);
            if (file.createNewFile()) {
                FileWriter writer = new FileWriter(file);
                writer.write(nameCourse+"\n"+ nameExam+ "\n");
                writer.write(currentExam.getSummary());
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showExamsInformation(){
        List<List<String>> examsInformation = new ArrayList<List<String>>();
        String directory = System.getProperty("user.dir");
        directory = directory+File.separator+"src"+File.separator+"data"+File.separator+"Exams";
        File searchedFolder = new File(directory);
        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    directory = directory + File.separator + file.getName();
                    File searchedFolderExam = new File(directory);
                    if (searchedFolder.exists() && searchedFolder.isDirectory()) {
                        File[] filesExams = searchedFolderExam.listFiles();
                        if (files != null) {
                            for (File fileExam : filesExams) {
                                readExam(directory, fileExam.getName());
                                examsInformation.add(new ArrayList<String>());
                                examsInformation.add(readInformation());
                            }
                        }
                    }
                }
            }
        }
    }    
    
    private List<String> readInformation(){
        List<String> examInformation = new ArrayList<String>();
        examInformation.add((currentExam.getNameCourse()));
        examInformation.add((currentExam.getDescription()));
        examInformation.add((currentExam.getTypeExam()));
        String duration = Integer.toString(currentExam.getDuration());
        examInformation.add(duration);
        examInformation.add((currentExam.getNameTeacher()));
        return examInformation;
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
                    currentExam.setIsCorrectExam(true, i, counter);
                    } else {
                        answer[i]= line;
                        justification[i]= br.readLine();
                        currentExam.setIsCorrectExam(false, i, counter);
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
        int numberQuestions, duracion;
        directory = directory +File.separator+ nameFolder+File.separator+nameFolder+".txt";
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            currentExam.setNameExam(br.readLine());
            currentExam.setTipo(br.readLine());
            numberQuestions = Integer.parseInt((br.readLine()));
            currentExam.setNumberQuestions(numberQuestions);
            currentExam.setTeacherName(br.readLine());
            duracion = Integer.parseInt((br.readLine()));
            currentExam.setDuration(duracion);
            currentExam.setDescripcion(br.readLine());
            }catch (IOException e) {
                e.printStackTrace();
        }
    }

    private int getNumberQuestion(String directory, String nameFolder){
        int lineNumberQuestion = 2;
        int numberQuestion = 0;
        directory = directory +File.separator+nameFolder+File.separator+nameFolder+".txt";
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
        return (directory +File.separator+changeNumberQuestion(question)+".txt");
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
        //String nameCourse = currentExam.getNameCourse();
        //String nameFolder = currentExam.getNameExam();
        String nameCourse = "Course1";
        String nameFolder = "Examen1";
        directory = directory+File.separator+"src"+File.separator+"data"+File.separator+"Exams"+File.separator+nameCourse;
        File searchedFolder = new File(directory);
        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();
            if (files != null) {
                int stop = getNumberQuestion(directory, nameFolder);
                int counter =0;
                readExam(directory,nameFolder);
                for (File file : files) {
                    if (file.isDirectory() && file.getName().equals(nameFolder)) {
                        stop = getNumberQuestion(directory, nameFolder);
                        directory = directory + File.separator + nameFolder + File.separator+ "Pregunta1.txt";
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
        return currentExam.getDuration();
    }

    public Boolean isCorrect(int indexQuestion, int indexSelectedAnswer){
        return currentExam.getIsCorrectExam(indexQuestion, indexSelectedAnswer);
    }
    public void setResultExamC(int numCorrectQuestions){
        currentExam.setResultExam(numCorrectQuestions);
        // esa impresion seria lo que hay que escribir como string en el .txt
        //System.out.println(currentExam.getResultExam()+ "/"+currentExam.getNumberQuestions());
        //System.out.println(currentExam.getUsuario());
    }
}
