package main.controllers;

import main.models.Answer;
import main.models.Exam;
import main.models.Lines;
import main.models.Question;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CreateExamController {

    int questionsCount = 0;
    File folder;

    public void createExam(String name, String examType, int questionsCount, String teacherName, int duration, String description ){
        //crear carpetas
        String directory = System.getProperty("user.dir");
        folder = new File(directory+ "\\exams\\"+ name);
        if (!folder.exists()) {
            folder.mkdir();
        }   
        //crear archivo .txt
        File file = new File(folder, name + ".txt");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            // Escribir en el archivo .txt
            FileWriter writer = new FileWriter(file);

            writer.write(name + "\n" + examType + "\n" + questionsCount + "\n" + teacherName + "\n" + duration + "\n" + description );
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void createQuestion(String enunciado, String domain, String[] answers, String[] justification){
        questionsCount++;
        File file = new File(folder, "Pregunta"+ questionsCount + ".txt");
        
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);

            writer.write(enunciado + "\n" + domain + "\n");

            for(int i = 0; i< answers.length; i++) writer.write(answers[i] + "\n" + justification[i] + "\n");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setExam(){
        
    }
    public void setQuestions(){}
    public void setAnswers(){}
    public void completeExamCreation(){}
}

