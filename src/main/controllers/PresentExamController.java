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
    private void startExam(){}
    private Answers getAnswers(){

        return this.getAnswers();
    }
    private Questions getQuestions(){
        return this.getQuestions();
    }

    private void readQuestion(String question){
        String[] answer = new String[10];
        String[] justification = new String[10];
        boolean answerCorrect = true; 

        try (BufferedReader br = new BufferedReader(new FileReader(question))) {
            String line = "_";
            String questionStatement = br.readLine();
            for (int i =0, j=0; (line = br.readLine()) != null; i++,j++) {
                if (line != null && line.length() > 0 && answerCorrect && line.substring(0, 1).equalsIgnoreCase("v")) {
                    answer[i]= line.substring(1);
                    justification[j]= br.readLine();
                    } else {
                        answer[i]= line;
                        justification[j]= br.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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

    private void searchFile(String question, String nameFile, File[] files){
        //llamada recursiva    
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().equals(question)) {
                    // El archivo fue encontrado
                    readQuestion(question);
                    searchFile(numberQuestion(question), nameFile, files);
                    return;
                }
            }
            // archivo no encontrado
        }
    }   

    public void searchFolder() {
        String directory = "C:\\Users\\sergio\\Documents";
        //get para obtener nombre del examen
        String nameFolder = "Proyecto-IS-Grupo-1";
        File searchedFolder = new File(directory);

        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory() && file.getName().equals(nameFolder)) {
                        //carpeta encontrad
                        String question = "Pregunta1";
                        searchFile(question, nameFolder, files);
                        return;
                    }
                }
                // carpeta no encontrada
           }    
        }
    }
}
