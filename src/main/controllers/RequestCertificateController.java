package main.controllers;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.models.GeneratePDFFile;
import main.utils.Directory;
import main.utils.UserData;
import main.models.Certificate;
import main.models.Course;


public class RequestCertificateController {
    /*private void requestCertificate(Course_Name){}
    private String getSummary(){}
    private Certificate generateCertificate(Summary){}*/
    Directory currentDirectory = Directory.instance();
    Certificate currentCertificate = new Certificate();
    UserData currentUser = UserData.instance();
    public RequestCertificateController(){
       // this.searchFolderStudent();
    }
    
    public static void main(String[] args) throws IOException{
        RequestCertificateController p = new RequestCertificateController();
        UserData.instance().setUsername("Usuario");
        UserData.instance().setPassword("Contrasenia");
        //p.searchFolderStudent();
        p.showCertificates();   
    }
    private String nameCourses(File file){
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String nameCourse = br.readLine();
            br.close();
            return nameCourse;
            } catch (IOException e) {
                e.printStackTrace();
        }
        return "-";
    }

    public List<String> showCertificates(){
        List<String> namesCourses = new ArrayList<String>();
        String directory = currentDirectory.getDirectoryStudents()+File.separator+(currentUser.getUsername());
        File searchedFolder = new File(directory);
        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory() || (!(file.getName().equals("Password.txt")) && file.getName().endsWith(".txt"))) {
                        namesCourses.add(nameCourses(file));
                    }
                }
            }    
        }
        return namesCourses;
    }

    private void readStudentData(String directory){
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            currentCertificate.setNameStudentCertificate(currentUser.getUsername());
            currentCertificate.setNameCourse(br.readLine());
            //int result=Integer.parseInt((br.readLine()));
            //currentCertificate.setSummary(result);
            currentCertificate.setSummary(br.readLine());
            //arreglar aqui, pq summary es int y lo necesitamos string
            currentCertificate.setNameTeacherCertificate(br.readLine());
            br.close();     
            } catch (IOException e) {
                e.printStackTrace();
        }
    }

    public void searchFolderStudent(String nameCourse) {
        String directory = currentDirectory.getDirectoryStudents();
        String nameFolderStudent = currentUser.getUsername();
        File searchedFolder = new File(directory);
        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory() && file.getName().equals(nameFolderStudent)) {
                        directory = directory + File.separator + nameFolderStudent + File.separator + nameCourse + ".txt";
                        readStudentData(directory);
                        return;
                    }
                }
            }    
        }
    }

    public void createPDF(){
        GeneratePDFFile creatingPDF = new GeneratePDFFile();
        creatingPDF.crearPlantilla();
    }
    public String getNameStudentController(){
        return currentUser.getUsername();
    }
    public String getNameCourseController(){
        return currentCertificate.getNameCourse();
    }
    public String getResultExamController(){
        return currentCertificate.getSummary();
    }
    public String getNameTeacherController(){
        return currentCertificate.getNameTeacher();
    }
}
