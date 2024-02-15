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
    private void readQuestion(String directory, int readings, int counter, int stop){
        String line, lineImage;
        List<String> answer = new ArrayList<String>(), justification = new ArrayList<String>(), code = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            currentExam.setQuestionsExam((br.readLine()),(br.readLine()),counter);
            int sizeCode = Integer.parseInt(br.readLine());
            for(int i=1; i < sizeCode; i++){
                code.add(br.readLine());
            }
            currentExam.setCode(code);
            lineImage = br.readLine();
            if(lineImage.equals("Si")){
                //metodo para buscar y mostrar la ruta de la imagen
            }
            for (int i =0; ((line = br.readLine()) != null); i++) {
                if (line != null && line.length() > 0 && line.substring(0, 1).equalsIgnoreCase("v")) {
                    answer.add(line.substring(1));
                    justification.add(br.readLine());
                    currentExam.setIsCorrectExam(true, i, counter);
                    currentExam.setNumCorrectAsnwers(counter);
                    } else {
                        answer.add(line.substring(1));
                        justification.add(br.readLine());
                        currentExam.setIsCorrectExam(false, i, counter);
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

    public void computeResultQuestion(int numCorrectQuestions){
        //hay que cambiarlo a float
        int result=currentExam.getNumberAnswersExam(numCorrectQuestions)/currentExam.getNumCorrectAnswersExam(numCorrectQuestions);
        currentExam.setResultExam(result);
    }
}
