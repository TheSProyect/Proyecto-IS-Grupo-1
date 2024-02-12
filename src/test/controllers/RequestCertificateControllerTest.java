package test.controllers;
import org.junit.*;

import main.controllers.RequestCertificateController;
import main.utils.UserData;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Paths;

public class RequestCertificateControllerTest {
    RequestCertificateController controller = new RequestCertificateController();
    String course;
    String directory = System.getProperty("user.dir");
    String nameFolderStudent = "Usuario";
    

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
            //Assert.assertEquals(br.readLine(), course);
            Assert.assertEquals(br.readLine(), controller.getNameExamController());
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

        List<String> certificate = new ArrayList<String>();
        certificate = controller.showCertificates();

        directory = directory+File.separator+"src"+File.separator+"data"+File.separator+"Users"+File.separator+"Students"+File.separator + nameFolderStudent;
        File searchedFolder = new File(directory);
        String courseName;
        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();
            if (files != null) {
                int i=0;
                for (File fileCertificate : files) {
                    if(!(fileCertificate.getName().equals("Password.txt")) && !(fileCertificate.getName().startsWith(nameFolderStudent))){
                        courseName=fileCertificate.getName();
                        courseName = courseName.substring(0, courseName.length()-4);
                        Assert.assertEquals(courseName, certificate.get(i));
                    }
                    i++;
                }
            }
        }
    }

    //public static void test(){
        //RequestCertificateControllerTest pru = new RequestCertificateControllerTest();
       // pru.readStudentDataTest();
    //}
}
