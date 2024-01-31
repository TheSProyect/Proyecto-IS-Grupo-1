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

    private void readQuestion(String directory, String directorySub, int stop, int counter){
        int removeQuestionFromDirectory = 13;
        int removeFyleType = 4;
        String questionStatement, question, line, domain;
        String[] answer = new String[10];
        String[] justification = new String[10];
        boolean answerCorrect = true; 
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            line = "_";
            questionStatement = br.readLine();
            domain = br.readLine();
            //ahhhhhhh
            currentExam.setQuestionsExam(questionStatement,domain,counter);

            for (int i =0, j=0; ((line = br.readLine()) != null); i++,j++) {
                if (line != null && line.length() > 0 && answerCorrect && line.substring(0, 1).equalsIgnoreCase("v")) {
                    answer[i]= line.substring(1);
                    justification[i]= br.readLine();
                    } else {
                        answer[i]= line;
                        justification[i]= br.readLine();
                        }
                currentExam.setAnswersExam(answer[i],justification[i], i, counter);
                }
                    } catch (IOException e) {
                        e.printStackTrace();
            }
            //get con el archivo del examen para obtener cantidad de preguntas
            if(stop==1) {
                return;
                } else {
                    question = (directory.substring(directory.length() - removeQuestionFromDirectory));
                    question = question.substring(0, question.length() - removeFyleType);
                    System.out.println((directorySub +"\\"+numberQuestion(question)+".txt"));
                    readQuestion((directorySub +"\\"+numberQuestion(question)+".txt"), directorySub,stop+1, counter);
        }
    }
    

    private String numberQuestion(String question){
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
        String question, directorySub;
        //String directory = "C:\\Users\\sergio\\Documents";
        //String directory ="C:\\Users\\sergio\\Documents\\Proyecto-IS-Grupo-1";
        String directory = System.getProperty("user.dir");
        //directory = directory+"\\"+"Exams";
        //get para obtener nombre del examen
        String nameFolder = "Exams";
        
        File searchedFolder = new File(directory);

        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory() && file.getName().equals(nameFolder)) {
                        question = "Pregunta1.txt";
                        directorySub = directory+ "\\"+ nameFolder;
                        directory = directory + "\\"+ nameFolder + "\\"+ question;
                        readQuestion(directory, directorySub,0,counter);
                        counter++;
                        return;
                    }
                }
           }    
        }
    }

    public List<String> getQuestionsStrings(){
        int j=1;
        //List<String> questionsString = new ArrayList<String>();
        // List<String> domain = new ArrayList<String>();
        //List<Boolean> hasCode = new ArrayList<Boolean>();
        //List<List<String>> code = new ArrayList<List<String>>();
        //List<List<String>> options = new ArrayList<List<String>>();
        // prueba {
        //options.add(new ArrayList<String>());
        List<String> questionsString = new ArrayList<String>();
        //System.out.println("entro en controller");
        for(int i=0; i<j; i++){ 
            System.out.println(currentExam.getQuestionsExam(i)+"te oodio");
            questionsString.add(currentExam.getQuestionsExam(i));
        }

        return questionsString;
    }
}
