package main.controllers;

import main.models.Answer;
import main.models.Exam;
import main.models.Line;
import main.models.Question;
import main.utils.Directory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateExamController extends TemplateExam{
    int questionsCount = 0;
    File folder;

    public void createExam(){
        String nameExam = " ", examType = " ", nameTeacher = " ", description = " ", nameCourse = " ";
        int duration=0;
        String directory = currentDirectory.getDirectoryExams();
        folder = new File(directory+ File.separator+ nameCourse+ File.separator + nameExam);
        if (!folder.exists()) {
            folder.mkdir();
        }
        fillExamInformation(nameCourse, nameExam);   
        File file = new File(folder, nameExam + ".txt");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);

            writer.write(nameExam + "\n" + examType + "\n" + questionsCount + "\n" + nameTeacher + "\n" + duration + "\n" + description + "\n"+ nameCourse);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void fillExamInformation(String nameCourse, String nameExam){
        currentExam.setNameCourse(nameCourse);
        currentExam.setNameExam(nameExam);
    }
   
    public void createQuestion(){
        String enunciado = " ", domain = " ";
        String[] answers = new String[1];
        String[] justification = new String[1];
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
    private void showQuestion(String directory, int readings, int counter, int stop){
        String line;
        String[] answer = new String[10];
        String[] justification = new String[10];
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            currentExam.setQuestionsExam((br.readLine()),(br.readLine()),counter);
            for (int i =0; ((line = br.readLine()) != null); i++) {
                if (line != null && line.length() > 0 && line.substring(0, 1).equalsIgnoreCase("v")) {
                    answer[i]= line.substring(1);
                    justification[i]= br.readLine();
                    } else {
                        answer[i]= line;
                        justification[i]= br.readLine();
                        }
                    currentExam.setAnswersExam(answer[i],justification[i], i, counter);
                    currentExam.setNumberAnswers(counter, i+1);
                    }
                    br.close();     
                } catch (IOException e) {
                    e.printStackTrace();
            }
            if(readings==stop) {
                return;
                } else {
                    counter++;
                    showQuestion(changeDirectory(directory),readings+1, counter, stop);
        }   
    }
    public void showExam() {
        int counter = 0, questionsRead = 1;
        int stop = getNumberQuestion(currentDirectory.getDirectoryExams()+File.separator+currentExam.getNameCourse(), currentExam.getNameExam());
        String directory = folder.getAbsolutePath() + File.separator+ "Pregunta1.txt";
        showQuestion(directory,questionsRead,counter, stop);               
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

