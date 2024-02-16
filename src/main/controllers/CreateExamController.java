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
                writer.write("No" + "\n");
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
            for (String text : domain) {
                if(domain.size() > 1){
                    writer.write(text + ", ");
                    }else {
                        writer.write(text);
                }
            }
            saveCode(code, writer);
            if(directoryImage.equals("")){
                directoryImage = "No";
                } else {
                    copyImage(directoryImage);
                    directoryImage = "Si";
            } 
            writer.write(directoryImage + "\n");
            //writer.write("\n"+directoryImage + "\n");
            saveInformationQuestion(answers, justifications, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showAnswersAndJustifications(BufferedReader br, int counter){
        String line;
        List<List<String>> answers = new ArrayList<>(), justifications = new ArrayList<>();
        try{
            for (int i=0; ((line = br.readLine()) != null); i++) {
                int amountLines = Integer.parseInt(line);
                List<String> answer = new ArrayList<String>(), justification = new ArrayList<String>();
                for(int j=0; j < amountLines; j++){
                    if ((line = br.readLine()) != null && j==0 && line.substring(0, 1).equalsIgnoreCase("v")) {
                        answer.add(line.substring(1));
                        } else if(j==0 && line.substring(0, 1).equalsIgnoreCase("f")){
                            answer.add(line.substring(1));
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
    private void showQuestion(String directory, int readings, int counter, int stop){
        String line;
        List<String> code = new ArrayList<String>(), questionStatement = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            currentExam.setQuestionsExam((readInformationQuestion(br, questionStatement, Integer.parseInt(br.readLine()))),(br.readLine()),counter);
            if((line=br.readLine())!= "No"){
                readInformationQuestion(br, code, Integer.parseInt(line));
                currentExam.setCode(code,counter);
            }
            if(br.readLine().equals("Si")){
                currentExam.setImageQuestion(true, counter);
            }
            showAnswersAndJustifications(br, counter);
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
            hasCode.add(true);
        }
        return hasCode;
    }
    public List<List<String>> getCode(){
        int j=currentExam.getNumberQuestions();
        List<List<String>> code = new ArrayList<List<String>>();
        for(int i=0; i<j; i++){ 
            code.add(new ArrayList<String>());
            String statement= "";
            for(int k=0 ; k<currentExam.getCodeExam(i).size(); k++){
                statement = statement + currentExam.getCodeExam(i).get(k) + "\n";
            }
            code.get(i).add(statement); 
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
}

