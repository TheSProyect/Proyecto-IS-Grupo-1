package main.controllers;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import main.views.components.GeneratePDFFile;
import main.models.Certificate;
import main.models.Course;
import main.models.UserData;


public class RequestCertificateController {
    /*private void requestCertificate(Course_Name){}
    private String getSummary(){}
    private Certificate generateCertificate(Summary){}*/
    Certificate currentCertificate = new Certificate();
    public RequestCertificateController(){
        this.searchFolderStudent();
        this.searchFolderTeacher();
    }

    
    public static void main(String[] args) throws IOException{
        RequestCertificateController p = new RequestCertificateController();
        //p.searchFolderStudent();
        //p.searchFolderTeacher(); 
        p.createPDF();   
    }

    private void readStudentData(String directory){
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            currentCertificate.setNameStudentCertificate(br.readLine());
                br.close();     
            } catch (IOException e) {
                e.printStackTrace();
        }
    }

    private void readTeacherData(String directory){
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            currentCertificate.setNameTeacherCertificate(br.readLine());
            br.close();     
            } catch (IOException e) {
                e.printStackTrace();
        }
    }
    public void searchFolderTeacher() {
        String directory = System.getProperty("user.dir");
        directory = directory+File.separator+"src"+File.separator+"data"+File.separator+"Users"+File.separator+"Teachers";
        //get para obtener nombre del profesor
        String nameFolderTeacher = "Profesor";
        File searchedFolder = new File(directory);
        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory() && file.getName().equals(nameFolderTeacher)) {
                        directory = directory + File.separator + nameFolderTeacher + File.separator + nameFolderTeacher + ".txt";
                        readTeacherData(directory);
                        return;
                    }
                }
            }    
        }
    }

    public void searchFolderStudent() {
        String directory = System.getProperty("user.dir");
        directory = directory+File.separator+"src"+File.separator+"data"+File.separator+"Users"+File.separator+"Students";
        UserData data = new UserData(); 
        String nameFolderStudent = data.getUsername();
        File searchedFolder = new File(directory);
        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory() && file.getName().equals(nameFolderStudent)) {
                        directory = directory + File.separator + nameFolderStudent + File.separator + nameFolderStudent + ".txt";
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
        return currentCertificate.getNameStudentCertificate();
    }
    public String getCourseController(){
        return currentCertificate.getNameCourse();
    }
    public String getNameTeacherController(){
        return currentCertificate.getNameTeacher();
    }
}
