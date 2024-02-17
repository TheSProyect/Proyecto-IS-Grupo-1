package main.controllers;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import main.models.Exam;

public class PresentExamController extends TemplateExam{

    public PresentExamController(){}

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
                writer.write((currentExam.getNameCourse()) + "\n" + (currentExam.getNameExam()) + "\n");
                writer.write((String.valueOf(currentExam.getResultExam())) + "\n" + currentExam.getNumberQuestions()+ "\n");
                writer.write(currentExam.getNameTeacher() + "\n" + currentExam.getUsernameTeacher());
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
        String description = "";
        examInformation.add(currentExam.getNameExam());
        for(int i=0; i < currentExam.getDescription().size(); i++){
            description += currentExam.getDescription().get(i) + " ";
        }
        examInformation.add(description);
        examInformation.add((currentExam.getType()));
        String duration = Integer.toString(currentExam.getDuration());
        examInformation.add(duration);
        examInformation.add((currentExam.getNameTeacher()));
        examInformation.add(currentExam.getNameCourse());
        return examInformation;
    }
    
    public void readExam(String directory, String nameFolder){
        int numberQuestions, duration, sizeDescription;
        List<String> description = new ArrayList<String>();
        directory = directory +File.separator+ nameFolder+File.separator+nameFolder+".txt";
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            currentExam.setNameExam(br.readLine());
            currentExam.setType(br.readLine());  
            numberQuestions = Integer.parseInt((br.readLine()));
            currentExam.setNumberQuestions(numberQuestions);
            currentExam.setNameTeacher(br.readLine());
            duration = Integer.parseInt((br.readLine()));
            currentExam.setDuration(duration);
            sizeDescription = Integer.parseInt(br.readLine());
            for(int i=0; i < sizeDescription; i++){
                description.add(br.readLine());
            }
            currentExam.setDescripcion(description);
            currentExam.setNameCourse(br.readLine());
            currentExam.setUsernameTeacher(br.readLine());
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
    
    public float caculateResult(List<List<Boolean>> selectedOptions) {
        float result=0;
        int numQuestions = currentExam.getNumberQuestions(), decimals=2;
        for (int i = 0; i < numQuestions; i++) {
            float numCorrectAnswers = 0;
            float numSelectedOptions = 0;
            for (int j = 0; j < selectedOptions.get(i).size(); j++) {
                if (selectedOptions.get(i).get(j)) {
                    numSelectedOptions++;
                    if (isCorrect(i, j)) {
                        numCorrectAnswers++;
                    }
                }
            }
            if (numSelectedOptions == selectedOptions.get(i).size()) {
                numCorrectAnswers = 0;
            }
            result =  result + computeResultQuestion(i, numCorrectAnswers, numSelectedOptions);
        }
        BigDecimal bd = new BigDecimal(Float.toString(result));
        bd = bd.setScale(decimals, RoundingMode.HALF_UP);
        float resultF = bd.floatValue();
        return resultF;
    }

    public float computeResultQuestion(int numQuestion, float numCorrectAnswers, float selectedOptions){
        //float maxAmountCorrectAnswers = currentExam.getNumCorrectAnswersExam(numQuestion);
        float maxAmountCorrectAnswers = getNumCorrectAnswersController(numQuestion);
        
        if (numCorrectAnswers != 0 && numCorrectAnswers < selectedOptions) {
            float penalization = selectedOptions - numCorrectAnswers;
            numCorrectAnswers = numCorrectAnswers - penalization;
            if (numCorrectAnswers < 0) {
                numCorrectAnswers = 0;
            }
        }

        float result = numCorrectAnswers / maxAmountCorrectAnswers;
        currentExam.setResultExam(result);
        return result;
    }
}
