package test.controllers;
import org.junit.*;

import main.controllers.RequestCertificateController;
import main.models.Certificate;
import main.utils.Directory;
import main.utils.UserData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RequestCertificateControllerTest {
    RequestCertificateController controller = new RequestCertificateController();
    UserData currentUser = UserData.instance();
    String course;
    String directory = System.getProperty("user.dir");
    
    //

    String nameFolderStudent = "Usuario";
    //String nameFolderStudent = currentUser.getUsername();

    @Test
    public  void readStudentDataTest(){
        
        directory = directory+File.separator+"src"+File.separator+"data"+File.separator+"Users"+File.separator+"Students"+File.separator + nameFolderStudent;
        File searchedFolder = new File(directory);
        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();
            if (files != null) {
            for (File fileExam : files) {
                if(!(fileExam.getName().equals("Password.txt")) && !(fileExam.getName().startsWith(nameFolderStudent))){
                    course=fileExam.getName();
                } 
                
            }
        }}

        directory = directory + File.separator + course ;      
        
        controller.readStudentData(directory);
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            // nombre del curso
            Assert.assertEquals(br.readLine(), controller.getNameCourseController());
            //nombre del examen
            Assert.assertEquals(br.readLine(), course);
            //Assert.assertEquals(br.readLine(), controller.getNameExamController());
            //resultado de examen
            Assert.assertEquals(Integer.parseInt(br.readLine()), controller.getResultAnswersController());
            //numero de preguntas
            Assert.assertEquals(Integer.parseInt(br.readLine()), controller.getQuestionsExamController());
            //nombre profe
            Assert.assertEquals(br.readLine(), controller.getNameTeacherController());

            br.close();     
            } catch (IOException e) {
                e.printStackTrace();
        }
    }

    @Test
    public  void showCertificatesTest(){

    }

    public static void test(){
        RequestCertificateControllerTest pru = new RequestCertificateControllerTest();
        pru.readStudentDataTest();
    }
}
