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
        setUp();
        String expectedCourseName = "CourseName";
        List<String> expectedInformation = new ArrayList<>();
        expectedInformation.add("ExamName");
        expectedInformation.add("Description ");
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
        currentExam.setNameExam("Prueba 1");
        String directory = System.getProperty("user.dir");
        directory = directory+File.separator+"src"+File.separator+"data"+File.separator+"Exams"+File.separator+"Ayudarnos"+File.separator + currentExam.getNameExam()+File.separator + "Pregunta1.txt";
        currentExam.setNumberQuestions(3);
        presentExam.readQuestion(directory, 1, 0, currentExam.getNumberQuestions());
    }
    
    public void fillIsCorrect(List<List<Boolean>> isCorrecList){
        List<Boolean> options = new ArrayList<>();
        String directoryTest = System.getProperty("user.dir");
        final int INDEX_INFORMATION_ANSWERS = 8; 
        directoryTest = directoryTest+File.separator+"src"+File.separator+"data"+File.separator+"Exams"+File.separator+"Ayudarnos"+File.separator + "Prueba 1";
        File searchedFolder = new File(directoryTest);
        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();
            if (files != null) {
                for (File fileExam : files) {
                    if(!(fileExam.getName().equals(currentExam.getNameExam()+ ".txt"))){
                        try (BufferedReader br = new BufferedReader(new FileReader(directoryTest+File.separator+fileExam.getName()))){
                            String line="";
                            for(int i=1 ; i<INDEX_INFORMATION_ANSWERS; i++){
                                line=br.readLine();
                            }
                            for (int i=0; ((line = br.readLine()) != null); i++){
                                if((i%3==0)&&line.substring(0, 1).equalsIgnoreCase("v")){
                                    options.add(true);
                                }else if((i%3==0)&&line.substring(0, 1).equalsIgnoreCase("f")){
                                    options.add(false);
                                }
                            }   
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    isCorrecList.add(options);
                    }     
                }
            }
        }
    }
    public int[] countNumCorrectAnswers(){
        String directoryTest = System.getProperty("user.dir");
        int counterQuestion=0;
        int[] numCorrectAnswers= new int[20]; 
        final int INDEX_INFORMATION_ANSWERS = 8; 
        directoryTest = directoryTest+File.separator+"src"+File.separator+"data"+File.separator+"Exams"+File.separator+"Ayudarnos"+File.separator + "Prueba 1";
        File searchedFolder = new File(directoryTest);
        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();
            if (files != null) {
                for (File fileExam : files) {
                    if(!(fileExam.getName().equals(currentExam.getNameExam()+ ".txt"))){
                        int counter=0;
                        try (BufferedReader br = new BufferedReader(new FileReader(directoryTest+File.separator+fileExam.getName()))){
                            String line="";
                            for(int i=1 ; i<INDEX_INFORMATION_ANSWERS; i++){
                                line=br.readLine();
                            }
                            for (int i=0; ((line = br.readLine()) != null); i++){
                                if((i%3==0)&&line.substring(0, 1).equalsIgnoreCase("v")){
                                    counter++;
                                }
                            }   
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    numCorrectAnswers[counterQuestion]=counter;
                    counterQuestion++;
                    }     
                }
            }
        }
        return numCorrectAnswers;
    }
    public void fillOptionsList(List<List<Boolean>> selectedOptions){ 
        int[] numAnswers = {4,4,5};
        for(int i=0; i<currentExam.getNumberQuestions();i++){
            List<Boolean> options = new ArrayList<>();
            for(int j=0 ; j<numAnswers[i];j++){
                if(i==2&&j==2){
                    options.add(true);
                }else if(i==2&&j!=2){
                    options.add(false);
                }else{
                    if(j%2==0){
                        options.add(true);
                    }else{
                        options.add(false);  
                }}}
            selectedOptions.add(options);
        }
    }
    public String caculateResultTest(List<List<Boolean>> selectedOptions,List<List<Boolean>> isCorrectList){
        float numCorrectAnswers = 0,resultTest=0;
        int[] numAnswers = {2,3,1};
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
        setUp();
        createExam();
        int[] numCorrectAnswers= new int[20];
        int decimals=2;
        numCorrectAnswers=countNumCorrectAnswers();
        float resultTest, resultController;
        for(int i=0; i<currentExam.getNumberQuestions(); i++){
            Assert.assertEquals(numCorrectAnswers[i], presentExam.getNumCorrectAnswersController(i));
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
        setUp();
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
