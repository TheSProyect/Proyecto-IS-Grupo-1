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
import main.utils.Directory;

public class PresentExamController extends TemplateExam{
    Exam currentExam = new Exam();
    Directory currentDirectory = Directory.instance();

    public PresentExamController(){
    }
    private Answer getAnswers(){
        return this.getAnswers();
    }
    private Question getQuestions(){
        return this.getQuestions();
    }
    public static void main(String[] args) throws IOException{
        PresentExamController p = new PresentExamController();
    }
    private String nameExam(File file){
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String nameExam = br.readLine();
            br.close();
            return nameExam;
            } catch (IOException e) {
                e.printStackTrace();
        }
        return "-";
    }
    public List<String> showExams(){
        List<String> namesExams = new ArrayList<String>();
        String directory = currentDirectory.getDirectoryExams();
        File searchedFolder = new File(directory);
        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if((file.getName()).equals("Instructions.txt")){
                    } else {
                        String directorySub = directory + File.separator + file.getName();
                        File searchedFolderExam = new File(directorySub);
                        if (searchedFolderExam.exists() && searchedFolderExam.isDirectory()) {
                            File[] filesExams = searchedFolderExam.listFiles();
                                if (filesExams != null) {
                                    for (File fileExam : filesExams) {
                                        if (file.isDirectory() || !(file.getName().startsWith("Pregunta"))) {
                                            namesExams.add(nameExam(fileExam));
                                    }
                                }
                            }
                        }
                    }
                }
            }    
        }
        return namesExams;
    }

    public void examFinished(){
        UserData currentUser = UserData.instance();
        String directory = (currentDirectory.getDirectoryStudents())+File.separator+ currentUser.getUsername();
        File verifyFile = new File(directory+File.separator + (currentExam.getNameCourse())+".txt");
        if (verifyFile.exists()) {
            if (verifyFile.delete()) {                
            } 
        }
        try {
            File file = new File(directory, (currentExam.getNameCourse())+ ".txt");
            if (file.createNewFile()) {
                FileWriter writer = new FileWriter(file);               
                writer.write((currentExam.getNameCourse()) +"\n"+ (currentExam.getNameExam()) + "\n");
                writer.write((String.valueOf(currentExam.getResultExam()))+"\n"+ currentExam.getNumberQuestions()+ "\n");
                writer.write(currentExam.getNameTeacher());
                writer.close();
                }
           } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<List<String>> showExamsInformation(){
        List<List<String>> examsInformation = new ArrayList<List<String>>();
        String directory = currentDirectory.getDirectoryExams();
        File searchedFolder = new File(directory);
        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if((file.getName()).equals("Instructions.txt")){
                        } else {
                        String directorySub = directory + File.separator + file.getName();
                        File searchedFolderExam = new File(directorySub);
                        if (searchedFolderExam.exists() && searchedFolderExam.isDirectory()) {
                            File[] filesExams = searchedFolderExam.listFiles();
                                if (filesExams != null) {
                                    for (File fileExam : filesExams) {
                                        if((fileExam.getName().startsWith("Pregunta"))){
                                            } else {
                                                readExam(directorySub, fileExam.getName());
                                                examsInformation.add(readInformation(file.getName()));  
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
      return examsInformation;
    }    
    
    private List<String> readInformation(String nameCourse){
        List<String> examInformation = new ArrayList<String>();
        examInformation.add(currentExam.getNameExam());
        examInformation.add((currentExam.getDescription()));
        examInformation.add((currentExam.getTypeExam()));
        String duration = Integer.toString(currentExam.getDuration());
        examInformation.add(duration);
        examInformation.add((currentExam.getNameTeacher()));
        examInformation.add(currentExam.getNameCourse());
        return examInformation;
    }
    
    private void readQuestion(String directory, int readings, int counter, int stop){
        String line;
        String[] answer = new String[10];
        String[] justification = new String[10];
        boolean answerCorrect = true; 
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            currentExam.setQuestionsExam((br.readLine()),(br.readLine()),counter);
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
            readQuestion(changeDirectory(directory),readings+1, counter, stop);
        }   
    }
    
    private void readExam(String directory, String nameFolder){
        int numberQuestions, duration;
        directory = directory +File.separator+ nameFolder+File.separator+nameFolder+".txt";
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            currentExam.setNameExam(br.readLine());
            currentExam.setTipo(br.readLine());
            numberQuestions = Integer.parseInt((br.readLine()));
            currentExam.setNumberQuestions(numberQuestions);
            currentExam.setNameTeacher(br.readLine());
            duration = Integer.parseInt((br.readLine()));
            currentExam.setDuration(duration);
            currentExam.setDescripcion(br.readLine());
            currentExam.setNameCourse(br.readLine());
            }catch (IOException e) {
                e.printStackTrace();
        }
    }

    public void searchFolder(String [] informationsExam) {
        int indexForNameFolder = 0;
        int indexForNameCourse = 1;
        int questionsRead = 1;
        String nameFolder = informationsExam[indexForNameFolder];
        String directory = (currentDirectory.getDirectoryExams())+File.separator+informationsExam[indexForNameCourse];
        File searchedFolder = new File(directory);
        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();
            if (files != null) {
                int stop = getNumberQuestion(directory, nameFolder);
                int counter = 0;
                readExam(directory,nameFolder);
                for (File file : files) {
                    if (file.isDirectory() && file.getName().equals(nameFolder)) {
                        stop = getNumberQuestion(directory, nameFolder);
                        directory = directory + File.separator + nameFolder + File.separator+ "Pregunta1.txt";
                        readQuestion(directory,questionsRead,counter, stop);
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

    public Boolean isCorrect(int indexQuestion, int indexSelectedAnswer){
        return currentExam.getIsCorrectExam(indexQuestion, indexSelectedAnswer);
    }

    public void setResultExamC(int numCorrectQuestions){
        currentExam.setResultExam(numCorrectQuestions);
    }
}
