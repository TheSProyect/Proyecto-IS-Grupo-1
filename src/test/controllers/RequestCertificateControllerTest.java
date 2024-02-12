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
    Directory currentDirectory = Directory.instance();
    UserData currentUser = UserData.instance();
    Certificate currentCertificate = new Certificate();
    String course = "Java SE";
    String directory = System.getProperty("user.dir");
    
    //

    String nameFolderStudent = "Usuario";
    //String nameFolderStudent = currentUser.getUsername();

    @Test
    public  void readStudentDataTest(){
        //controller.searchFolderStudent(course);
        directory = directory+File.separator+"src"+File.separator+"data"+File.separator+"Users"+File.separator+"Students";
        directory = directory + File.separator + nameFolderStudent + File.separator + course + ".txt";
        controller.readStudentData(directory);
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            // nombre del curso
            Assert.assertEquals(br.readLine(), course);
            //nombre del examen
            //Assert.assertEquals(br.readLine(), course);
            //Assert.assertEquals(br.readLine(), currentCertificate.getNameExam());
            Assert.assertEquals(br.readLine(), controller.getNameExamController());
            //resultado de examen
            Assert.assertEquals(Integer.parseInt(br.readLine()), controller.getResultAnswersController());
            //numero de preguntas
            Assert.assertEquals(Integer.parseInt(br.readLine()), controller.getQuestionsExamController());
            //Assert.assertEquals(Integer.parseInt(br.readLine()), controller.getResultAnswersController());
            //nombre profe
            Assert.assertEquals(br.readLine(), controller.getNameTeacherController());
            //currentCertificate.setNameTeacherCertificate(br.readLine());

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
