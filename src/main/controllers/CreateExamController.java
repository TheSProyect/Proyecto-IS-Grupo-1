package main.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateExamController extends TemplateExam{
    int questionsCount = 0;
    File folder;

    public void saveExam(List<String> examData, int duration){
        int INDEX_FOR_NAME_EXAM=0, INDEX_FOR_TYPE=1,INDEX_FOR_NAME_COURSE=2, INDEX_FOR_DESCRIPTION=3;
        File courseFolder = new File(currentDirectory.getDirectoryExams()+ File.separator+ examData.get(INDEX_FOR_NAME_COURSE));
        if (!courseFolder.exists()) {
            courseFolder.mkdir();
        }
        folder = new File(currentDirectory.getDirectoryExams() + File.separator+ examData.get(INDEX_FOR_NAME_COURSE)+ File.separator + examData.get(INDEX_FOR_NAME_EXAM));
        if (!folder.exists()) {
            folder.mkdir();
        }
        fillExamInformation(examData.get(INDEX_FOR_NAME_COURSE), examData.get(INDEX_FOR_NAME_EXAM));   
        File file = new File(folder, examData.get(INDEX_FOR_NAME_EXAM) + ".txt");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);

            writer.write(examData.get(INDEX_FOR_NAME_EXAM) + "\n" + examData.get(INDEX_FOR_TYPE) + "\n" + questionsCount + "\n" + readNameTeacher() + "\n" + duration + "\n" + examData.get(INDEX_FOR_DESCRIPTION) + "\n"+ examData.get(INDEX_FOR_NAME_COURSE));
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readNameTeacher(){
        String nameTeacher = null;
        try (BufferedReader br = new BufferedReader(new FileReader(currentDirectory.getDirectoryTeachers()+ File.separator + currentUser.getUsername()+ File.separator + "Name.txt"))) {
            nameTeacher = br.readLine() + " " + br.readLine();
            br.close();     
            } catch (IOException e) {
                e.printStackTrace();
        }
            return nameTeacher;
    }
    private void fillExamInformation(String nameCourse, String nameExam){
        currentExam.setNameCourse(nameCourse);
        currentExam.setNameExam(nameExam);
    }
    public void replaceQuestionCount(){
        String directory = currentDirectory.getDirectoryExams()+ File.separator + currentExam.getNameCourse() + File.separator + currentExam.getNameExam()+ File.separator + currentExam.getNameExam() + ".txt";
        try {
            File file = new File(directory);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String content = "", line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                count++;
                if (count == 2) {
                    content += String.valueOf(questionsCount)+ "\n";
                    } else {
                        content += line + "\n";
                    }
                }
            br.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(content);
            bw.close();
            } catch (IOException e) {
        }
    }
   
    public void saveQuestion(String questionText, List<String> domain, String code, List<String> answers){
        questionsCount++;
        File file = new File(folder, "Pregunta"+ questionsCount + ".txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            writer.write(questionText + "\n");
            for (String text : domain) {
                writer.write(text + ", ");
            }

            if(!code.equals("")){
                writer.write(code + "\n");
                } else {
                    writer.write("No" + "\n");
            }
            for (String text : answers) {
                writer.write(text + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void showQuestion(String directory, int readings, int counter, int stop){
        String line;
        List<String> answer = new ArrayList<String>();
        List<String> justification = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            currentExam.setQuestionsExam((br.readLine()),(br.readLine()),counter);
            for (int i =0; ((line = br.readLine()) != null); i++) {
                if (line != null && line.length() > 0 && line.substring(0, 1).equalsIgnoreCase("v")) {
                    answer.add(line.substring(1));
                    justification.add(br.readLine());
                    } else {
                        answer.add(line);
                        justification.add(br.readLine());
                        }
                    currentExam.setAnswersExam(answer.get(i),justification.get(i), i, counter);
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

