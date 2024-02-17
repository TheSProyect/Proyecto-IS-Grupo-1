package main.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Paths;
import java.nio.file.Path;

public class CreateExamController extends TemplateExam{
    int questionsCount = 0;
    File folder;

    public void saveExam(List<String> examData,List<String> description, int duration){
        int INDEX_FOR_NAME_EXAM=0, INDEX_FOR_TYPE=1,INDEX_FOR_NAME_COURSE=2;
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
            writer.write(examData.get(INDEX_FOR_NAME_EXAM) + "\n" + examData.get(INDEX_FOR_TYPE) + "\n" + questionsCount + "\n" + readNameTeacher() + "\n" + duration + "\n");
            writer.write(description.size() + "\n");
            for(String text : description){
                writer.write(text + "\n");
            }
            writer.write(examData.get(INDEX_FOR_NAME_COURSE) + "\n" + currentUser.getUsername());  
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
                if (count == 3) {
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
    private void copyImage(String directoryImage){
        String destinationFolderDirectory = currentDirectory.getDirectoryExams() + File.separator+ currentExam.getNameCourse() + File.separator + currentExam.getNameExam() + File.separator;
        try {
            Path origin = Paths.get(directoryImage);
            Path destination = Paths.get(destinationFolderDirectory + "Pregunta" + questionsCount + ".jpg"); 
            Files.copy(origin, destination);
        } catch (IOException e) {
            System.err.println("Ocurrió un error al copiar la imagen: " + e.getMessage());
        }
    }
    private void saveInformationQuestion(List<List<String>> answers, List<List<String>> justifications, FileWriter writer){
        List<String> answer = new ArrayList<>(), justification = new ArrayList<>();
        for(int i =0; i< answers.size(); i++){
            answer = answers.get(i);
            justification = justifications.get(i);
            try {
                writer.write(answer.size()+ "\n");
                for(String text : answer){
                writer.write(text + "\n");
                }
                for(String text : justification){
                    writer.write(text + "\n");
                }
                } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void saveCode(List<String> code, FileWriter writer){
        int INDEX_CODE=0;
        try{
            if(code.get(INDEX_CODE)!= ""){
                writer.write("\n" + code.size() + "\n"); 
                for (String text : code) {            
                    writer.write(text + "\n");
                }
                } else { 
                writer.write("\n"+"No" + "\n");
            }
            } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveQuestion(List<List<String>> informationExam,List<List<List<String>>> informationQuestion, String directoryImage){
        questionsCount++;
        int INDEX_QUESTION_TEXT=0, INDEX_DOMAIN=1,INDEX_CODE=2, INDEX_ANSWERS=0, INDEX_JUSTIFICATION=1;
        List<String> questionText = informationExam.get(INDEX_QUESTION_TEXT), domain = informationExam.get(INDEX_DOMAIN), code = informationExam.get(INDEX_CODE);
        List<List<String>> answers = informationQuestion.get(INDEX_ANSWERS), justifications = informationQuestion.get(INDEX_JUSTIFICATION);
        File file = new File(folder, "Pregunta"+ questionsCount + ".txt");
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(questionText.size() + "\n");
            for (String text : questionText) {
                writer.write(text + "\n");
            }
            for(int i=0; i<domain.size();i++){
                if(i > 0){
                    writer.write("," +domain.get(i));
                    } else {
                        writer.write(domain.get(i));
                }
            }
            saveCode(code, writer);
            if(directoryImage == null){
                directoryImage = "No";
                } else {
                    copyImage(directoryImage);
                    directoryImage = "Si";
            } 
            writer.write(directoryImage + "\n");
            saveInformationQuestion(answers, justifications, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }
    public void showExam() {
        int counter = 0, questionsRead = 1, stop = getNumberQuestion(currentDirectory.getDirectoryExams() + File.separator + currentExam.getNameCourse(), currentExam.getNameExam());
        String directory = currentDirectory.getDirectoryExams() + File.separator + currentExam.getNameCourse() + File.separator + currentExam.getNameExam() +File.separator + "Pregunta1.txt";
        System.out.println(directory);
        readQuestion(directory,questionsRead,counter, stop);               
    }
}

