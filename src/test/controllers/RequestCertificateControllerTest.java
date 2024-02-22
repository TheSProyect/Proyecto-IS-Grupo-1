package test.controllers;
import org.junit.*;

import main.controllers.RequestCertificateController;
import main.models.UserData;

import java.util.List;
import java.util.ArrayList;
import java.io.*;


public class RequestCertificateControllerTest {
    String course;
    String directory = System.getProperty("user.dir");
    String nameFolderStudent = "Usuario";
    RequestCertificateController controller;
    
    @Before
    public void setUp() {
        UserData.instance().setUsername(nameFolderStudent);
        controller = new RequestCertificateController();
    }

    @Test
    public  void readStudentDataTest(){
        directory = directory+File.separator+"src"+File.separator+"data"+File.separator+"Users"+File.separator+"Students"+File.separator + nameFolderStudent;
        File searchedFolder = new File(directory);
        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();
            if (files != null) {
            for (File fileExam : files) {
                if(!(fileExam.getName().equals("Password.txt")) && !(fileExam.getName().startsWith(nameFolderStudent)) && !(fileExam.getName().startsWith("Name"))){
                    course=fileExam.getName();
                } 
                
            }
        }}
        directory = directory + File.separator + course ;      
        controller.readStudentData(directory);
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            Assert.assertEquals(br.readLine(), controller.getNameCourseController());
            Assert.assertEquals(br.readLine(), controller.getNameExamController());
            Assert.assertEquals(Float.parseFloat(br.readLine()), controller.getResultAnswersController(), 0.001);
            Assert.assertEquals(Integer.parseInt(br.readLine()), controller.getQuestionsExamController());
            Assert.assertEquals(br.readLine(), controller.getNameTeacherController());
//
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
                    if(!(fileCertificate.getName().equals("Password.txt")) && !(fileCertificate.getName().startsWith(nameFolderStudent))&& !(fileCertificate.getName().equals("Name.txt"))){
                        courseName=fileCertificate.getName();
                        courseName = courseName.substring(0, courseName.length()-4);
                        
                        Assert.assertEquals(courseName, certificate.get(i));
                        i++;
                    }
                    
                }
            }
        }
    }

}
