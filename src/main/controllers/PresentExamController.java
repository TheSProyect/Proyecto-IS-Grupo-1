package main.controllers;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import main.models.Answers;
import main.models.Questions;
import main.models.Result;
import main.models.Exam;
import javax.swing.*;

public class PresentExamController {
    //private void setResult(Option){}
    //private void chooseExam(Exam_Name){}
    
    Exam currentExam = new Exam();

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
        System.out.println("esta en readquestion");
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
                System.out.println(currentExam.getAnswersQuestionExam(counter, i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
            if(stop==1){
                return;
            } else {
            question = (directory.substring(directory.length() - 13));
            question = question.substring(0, question.length() - 4);
            System.out.println((directorySub +"\\"+numberQuestion(question)+".txt"));
            readQuestion((directorySub +"\\"+numberQuestion(question)+".txt"), directorySub,stop+1, counter);
        }
    }
    

    private String numberQuestion(String question){
        if (question != null && question.length() > 0) {
            char lastCharacter = question.charAt(question.length() - 1);
            if (Character.isDigit(lastCharacter)) {
                int number = Character.getNumericValue(lastCharacter);
                number++;
                char newCharacter = Character.forDigit(number, 10);
                return question.substring(0, question.length() - 1) + newCharacter; 
            }
        }
        return question;
    }  

    public void searchFolder() {
        String question, directorySub;
        String directory = "C:\\Users\\sergio\\Documents";
        //get para obtener nombre del examen
        String nameFolder = "Proyecto-IS-Grupo-1";
        File searchedFolder = new File(directory);

        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            int counter=0;
            File[] files = searchedFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory() && file.getName().equals(nameFolder)) {
                        //carpeta encontrad
                        question = "Pregunta1.txt";
                        directorySub = directory+ "\\"+ nameFolder;
                        directory = directory + "\\"+ nameFolder + "\\"+ question;
                        readQuestion(directory, directorySub,0,counter);
                        counter++;
                        return;
                    }
                }
                // carpeta no encontrada
           }    
        }
    }
}
