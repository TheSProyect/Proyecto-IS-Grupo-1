package main.controllers;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import main.models.Exam;

public class PresentExamController extends TemplateExam{

    public PresentExamController(){

    }
    public static void main(String[] args) throws IOException{
        PresentExamController p = new PresentExamController();
        String[] examid= {"Prueba 1","Ayudarnos"};
        p.searchFolder(examid);
    }
    public List<String> getInstructions(String [] informationsExam) {
        int INDEX_FOR_NAME_EXAM = 0, INDEX_FOR_NAME_COURSE = 1, INDEX_FOR_DURATION = 4;
        List<String> instrucionsInformation = new ArrayList<String>();
        String line, duration = "0", directory = currentDirectory.getDirectoryExams() + File.separator + "Instructions.txt";
        try (BufferedReader bl = new BufferedReader(new FileReader(currentDirectory.getDirectoryExams() + File.separator + informationsExam[INDEX_FOR_NAME_COURSE]+ File.separator + informationsExam[INDEX_FOR_NAME_EXAM]+ File.separator + informationsExam[INDEX_FOR_NAME_EXAM]+ ".txt"))){
            for(int i = 0; i<=INDEX_FOR_DURATION; i++){
                duration = bl.readLine();
                }
            bl.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            instrucionsInformation.add(informationsExam[INDEX_FOR_NAME_EXAM]);
            instrucionsInformation.add(duration);
            while ((line = br.readLine()) != null) {
                instrucionsInformation.add(line);
            }
            br.close();     
            } catch (IOException e) {
               e.printStackTrace();
        }
        return instrucionsInformation;
    }

    public void examFinished(){
        String directory = verifyAdmin(); 
        File verifyFile = new File(directory+ File.separator + (currentExam.getNameCourse())+".txt");
        if (verifyFile.exists()) {
            verifyFile.delete();                
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
                                                examsInformation.add(readInformation(file.getName(), currentExam));
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
    
    public List<String> readInformation(String nameCourse, Exam currentExam){
        List<String> examInformation = new ArrayList<String>();
        examInformation.add(currentExam.getNameExam());
        examInformation.add((currentExam.getDescription()));
        examInformation.add((currentExam.getType()));
        String duration = Integer.toString(currentExam.getDuration());
        examInformation.add(duration);
        examInformation.add((currentExam.getNameTeacher()));
        examInformation.add(currentExam.getNameCourse());
        return examInformation;
    }
    private void readAnswersAndJustifications(BufferedReader br, int counter){
        String line;
        List<List<String>> answers = new ArrayList<>(), justifications = new ArrayList<>();
        try{
            for (int i=0; ((line = br.readLine()) != null); i++) {
                int amountLines = Integer.parseInt(line);
                List<String> answer = new ArrayList<String>(), justification = new ArrayList<String>();
                for(int j=0; j < amountLines; j++){
                    if ((line = br.readLine()) != null && j==0 && line.substring(0, 1).equalsIgnoreCase("v")) {
                        answer.add(line.substring(1));
                        currentExam.setIsCorrectExam(true, i, counter);
                        currentExam.setNumCorrectAsnwers(counter);
                        } else if(j==0 && line.substring(0, 1).equalsIgnoreCase("f")){
                            answer.add(line.substring(1));
                            currentExam.setIsCorrectExam(false, i, counter);
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
    private void readQuestion(String directory, int readings, int counter, int stop){
        String line;
        List<String> code = new ArrayList<String>(), questionStatement = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            currentExam.setQuestionsExam((readInformationQuestion(br, questionStatement, Integer.parseInt(br.readLine()))),(br.readLine()),counter);
            if((line=br.readLine())!= "No"){
                readInformationQuestion(br, code, Integer.parseInt(line));
                currentExam.setCode(code);
            }
            if(br.readLine().equals("Si")){
                currentExam.setImageQuestion(true, counter);
            }
            readAnswersAndJustifications(br, counter);
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
    
    public void readExam(String directory, String nameFolder){
        int numberQuestions, duration;
        directory = directory +File.separator+ nameFolder+File.separator+nameFolder+".txt";
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            currentExam.setNameExam(br.readLine());
            currentExam.setType(br.readLine());  
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
        int INDEX_FOR_NAME_EXAM = 0;
        int INDEX_FOR_NAME_COURSE = 1;
        int questionsRead = 1;
        String nameFolder = informationsExam[INDEX_FOR_NAME_EXAM];
        String directory = (currentDirectory.getDirectoryExams())+File.separator+informationsExam[INDEX_FOR_NAME_COURSE];
        File searchedFolder = new File(directory);
        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();
            if (files != null) {
                int stop = getNumberQuestion(directory, nameFolder);
                int counter = 0;
                readExam(directory,nameFolder);
                for (File file : files) {
                    if (file.isDirectory() && file.getName().equals(nameFolder)) {
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
            hasCode.add(currentExam.getHasCodeExam(i));
        }
        return hasCode;
    }

    public List<List<String>> getCode(){
        int j=currentExam.getNumberQuestions();
        List<List<String>> code = new ArrayList<List<String>>();
        for(int i=0; i<j; i++){ 
            code.add(new ArrayList<String>());
            for(int k=0; k<currentExam.getNumberAnswersExam(i); k++){
                String statement= "";
                for(int l=0 ; l<currentExam.getOptionsExam(i,k).size(); l++){
                    statement = statement + currentExam.getOptionsExam(i,k).get(l) + "\n";
                }
                code.get(i).add(statement);
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

    public Boolean isCorrect(int indexQuestion, int indexSelectedAnswer){
        return currentExam.getIsCorrectExam(indexQuestion, indexSelectedAnswer);
    }

    public void setResultExamC(int numCorrectQuestions){
        currentExam.setResultExam(numCorrectQuestions);
    }

    public void computeResultQuestion(int numCorrectQuestions){
        //hay que cambiarlo a float
        float result=currentExam.getNumberAnswersExam(numCorrectQuestions)/currentExam.getNumCorrectAnswersExam(numCorrectQuestions);
        currentExam.setResultExam(result);
    }
}
