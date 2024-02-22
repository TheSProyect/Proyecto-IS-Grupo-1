package test.controllers;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import main.controllers.PresentExamController;
import main.models.Exam;
import main.utils.UserData;

public class PresentExamControllerTest {

    private PresentExamController presentExam;
    private Exam currentExam;

    @BeforeEach
    public void setUp() {
        presentExam = new PresentExamController();
        currentExam = new Exam(); 
        
        currentExam.setNameExam("ExamName");
        currentExam.setType("Type");
        List<String> description = new ArrayList<String>();
        description.add("Description");
        currentExam.setDescripcion(description);
        currentExam.setNameTeacher("TeacherName");
        currentExam.setNameCourse("CourseName");
        currentExam.setResultExam(15);
        currentExam.setNumberQuestions(20);
        currentExam.setDuration(30);
        UserData currentUser = UserData.instance();
        currentUser.setUsername("Usuario");
        currentUser.setIsAdmin(false);
       
        }

    @Test
    public void testReadIndformation(){
        String expectedCourseName = "CourseName";
        List<String> expectedInformation = new ArrayList<>();
        expectedInformation.add("ExamName");
        expectedInformation.add("Description");
        expectedInformation.add("Type");
        expectedInformation.add("30");
        expectedInformation.add("TeacherName");
        expectedInformation.add(expectedCourseName);
        Assert.assertEquals("TeacherName", currentExam.getNameTeacher());
        List<String> result = presentExam.readInformation(expectedCourseName, currentExam);
        assertEquals(expectedInformation.size(), result.size(), "Las listas no tienen la misma longitud");

        for (int i = 0; i < expectedInformation.size(); i++) {
            assertEquals(expectedInformation.get(i), result.get(i), "Los elementos de la lista no coinciden");
        }
    }
    @Test
    public void testReadInformation_NullCurrentExam() throws IOException {
        String expectedCourseName = "CourseName";
        Exam currentExam = null;
        assertThrows( NullPointerException.class, () -> {presentExam.readInformation(expectedCourseName, currentExam);});

    }
    @Test
    public void testExamFinished() throws IOException{
        assertThrows( NullPointerException.class, () -> {presentExam.examFinished();});
    }  

    @BeforeEach
    public void createExam(){ 
        presentExam = new PresentExamController();
        currentExam = new Exam(); 
        currentExam.setNameExam("Conocimientos en POO y Java");
        String directory = System.getProperty("user.dir");
        directory = directory+File.separator+"src"+File.separator+"data"+File.separator+"Exams"+File.separator+"Java"+File.separator + currentExam.getNameExam()+File.separator + "Pregunta1.txt";
        currentExam.setNumberQuestions(4);
        presentExam.readQuestion(directory, 1, 0, currentExam.getNumberQuestions());
    }
    public void continueReading(BufferedReader br, String line){
        int size=Integer.parseInt(line);
        try {
            for(int i=0; i<size;i++){
                line=br.readLine();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
    public String changeNameQuestion(String name, int k){
        return name.substring(name.length()-5);

    }
    public void fillIsCorrect(List<List<Boolean>> isCorrecList){
        String nameQuestion="Pregunta1.txt";
        int k=0;
        String directoryTest = System.getProperty("user.dir");
        final int INDEX_INFORMATION_ANSWERS = 4; 
        directoryTest = directoryTest+File.separator+"src"+File.separator+"data"+File.separator+"Exams"+File.separator+"Java"+File.separator + "Conocimientos en POO y Java";
        File searchedFolder = new File(directoryTest);
        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();
            if (files != null) {
                for (File fileExam : files) {
                    if(!(fileExam.getName().equals(currentExam.getNameExam()+ ".txt")) && !(fileExam.getName().substring(9).equals(".jpg"))){ 
                        List<Boolean> options = new ArrayList<>();
                        try (BufferedReader br = new BufferedReader(new FileReader(directoryTest+File.separator+fileExam.getName()))){
                            String line="";
                            for(int i=0 ; i<INDEX_INFORMATION_ANSWERS; i++){
                                line=br.readLine();
                                if(i==3){
                                    if((line!="Si")&&(line!="No")){
                                        continueReading(br,line);
                                    }
                                    br.readLine();
                                    br.readLine();
                                }
                            }
                            for (int i=0; ((line = br.readLine()) != null); i++){
                                if((i%3==0)&&line.substring(0, 1).equalsIgnoreCase("v")){
                                    options.add(true);
                                }else if((i%3==0)&&line.substring(0, 1).equalsIgnoreCase("f")){
                                    options.add(false);
                                }
                            }   
                            isCorrecList.add(options);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    
                    }     
                }
            }
        }
    }
    public int[] countNumCorrectAnswers(){
        String directoryTest = System.getProperty("user.dir");
        int counterQuestion=0;
        int[] numCorrectAnswers= new int[20]; 
        final int INDEX_INFORMATION_ANSWERS = 4;
        directoryTest = directoryTest+File.separator+"src"+File.separator+"data"+File.separator+"Exams"+File.separator+"Java"+File.separator + "Conocimientos en POO y Java";
        File searchedFolder = new File(directoryTest);
        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();
            if (files != null) {
                for (File fileExam : files) { 
                    if(!(fileExam.getName().equals(currentExam.getNameExam()+ ".txt")) && !(fileExam.getName().endsWith(".jpg"))){ 
                        int counter=0;
                        try (BufferedReader br = new BufferedReader(new FileReader(directoryTest+File.separator+fileExam.getName()))){
                            String line="";
                            for(int i=0 ; i<INDEX_INFORMATION_ANSWERS; i++){
                                line=br.readLine();
                                if(i==3){
                                    if((line!="Si")&&(line!="No")){
                                        continueReading(br,line);
                                    }
                                    line=br.readLine();
                                    line=br.readLine();
                                }
                            }
                            for (int i=0; ((line = br.readLine()) != null); i++){
                                if((i%3==0)){
                                    if(line.substring(0, 1).equalsIgnoreCase("v")){
                                        counter++;
                                    }
                                    
                                }
                            }
                            numCorrectAnswers[counterQuestion]=counter;
                        counterQuestion++;   
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    
                    }     
                }
            }
        }
        return numCorrectAnswers;
    }
    public void fillOptionsList(List<List<Boolean>> selectedOptions){ 
        //int[] numAnswers = {4,4,4,4,5,5,4,4,6,4};
        int[] numAnswers = {4,4,4,4};

        for(int i=0; i<4;i++){
            List<Boolean> options = new ArrayList<>();
            for(int j=0 ; j<numAnswers[i];j++){
                if((i>=0&&i<=3&&j==0) || (i>=6&&i<=8&&j==0)){
                    options.add(true);
                }
                if(j>0){
                    options.add(false);
                }
                    if((i==4||i==5||i==9)&&j==0){
                        options.add(true);
                    }}
            selectedOptions.add(options);
        }
    }
    public String caculateResultTest(List<List<Boolean>> selectedOptions,List<List<Boolean>> isCorrectList){
        float numCorrectAnswers = 0,resultTest=0;
        //int[] numAnswers = {1,1,1,1,2,2,1,1,1,3};
        int[] numAnswers = {1,1,1,1};
        //aqui uso la lista correct que lee del archivo y el controller usa los getiscorrect
        for(int i=0 ; i<selectedOptions.size();i++){
            for(int j=0 ; j<selectedOptions.get(i).size();j++){
                if (selectedOptions.get(i).get(j)&&isCorrectList.get(i).get(j)) {
                    numCorrectAnswers++;
                }
            }
            resultTest =  resultTest + computeResult(numCorrectAnswers,numAnswers[i]);
        }
        BigDecimal bd = new BigDecimal(Float.toString(resultTest));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        float resultF = bd.floatValue();
        return Float.toString(resultF);
    }
    public Float computeResult(float result, int numCorrectAnswers){
        return (float)result/numCorrectAnswers;
    }
    @Test 
    public void calculateResultQuestionTest()throws IOException{
        createExam();
        int[] numCorrectAnswers= new int[20];
        int decimals=2;
        numCorrectAnswers=countNumCorrectAnswers();
        float resultTest, resultController;
        for(int i=0; i<currentExam.getNumberQuestions(); i++){
            for(int j=0; j<presentExam.getNumCorrectAnswersController(i);j++){
                resultTest=(float)j/numCorrectAnswers[i];
                BigDecimal bdTest = new BigDecimal(Float.toString(resultTest));
                bdTest = bdTest.setScale(decimals, RoundingMode.HALF_UP);

                resultController = presentExam.computeResultQuestion(i, j, 0);
                BigDecimal bdController = new BigDecimal(Float.toString(resultController));
                bdController = bdController.setScale(decimals, RoundingMode.HALF_UP);

                Assert.assertEquals(bdTest,bdController);
            }
        }
    }
    @Test
    public void calculateResultExamTest()throws IOException{
        createExam();
        List<List<Boolean>> selectedOptions= new ArrayList<List<Boolean>>();
        List<List<Boolean>> isCorrectList = new ArrayList<List<Boolean>>();
        fillOptionsList(selectedOptions);
        fillIsCorrect(isCorrectList);
        String resultTest=caculateResultTest(selectedOptions, isCorrectList);
        float resultController=presentExam.caculateResult(selectedOptions);
        Assert.assertEquals(resultTest, Float.toString(resultController));
        

    }
}
