package main.controllers;

import java.io.File;

import main.models.Answers;
import main.models.Questions;
import main.models.Result;
import main.models.Exam;
import javax.swing.*;

public class PresentExamController {
    //private void setResult(Option){}
    //private void chooseExam(Exam_Name){}
    private void startExam(){}
    private Answers getAnswers(){}
    private Questions getQuestions(){}

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
                // Itera sobre los archivos para buscar el archivo
                for (File file : files) {
                    if (file.isFile() && file.getName().equals(question)) {
                        // El archivo fue encontrado
                        searchFile(numberQuestion(question), nameFile, files);
                        return;
                    }
                }
                // archivo no encontrado
        }
    }   

    public void searchFolder() {
        String directory = "/ruta/del/directorio";
        //get para obtener nombre del examen
        String nameFolder = "nombreCarpeta";
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
