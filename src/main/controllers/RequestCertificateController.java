package main.controllers;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import main.views.components.GeneratePDFFile;
import main.models.Certificate;
import main.models.Course;


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
    }

    private void readStudentData(String directory){
        String name;
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            name = br.readLine();
            currentCertificate.setNameStudentCertificate(name);
                br.close();     
            } catch (IOException e) {
                e.printStackTrace();
        }
    }

    private void readTeacherData(String directory){
        String name;
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            name = br.readLine();
            currentCertificate.setNameTeacherCertificate(name);
            br.close();     
            } catch (IOException e) {
                e.printStackTrace();
        }
    }

    public void searchFolderTeacher() {
        String directory = System.getProperty("user.dir");
        directory = directory+"\\Users\\Teachers";
        //get para obtener nombre del profesor
        String nameFolderTeacher = "Profesor";
        File searchedFolder = new File(directory);
        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory() && file.getName().equals(nameFolderTeacher)) {
                        directory = directory + "\\" + nameFolderTeacher + "\\" + nameFolderTeacher + ".txt";
                        readTeacherData(directory);
                        return;
                    }
                }
            }    
        }
    }
    public void searchFolderStudent() {
        String directory = System.getProperty("user.dir");
        directory = directory+"\\Users\\Students";
        //get para obtener nombre del estudiante
        String nameFolderStudent = "Usuario";
        File searchedFolder = new File(directory);
        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory() && file.getName().equals(nameFolderStudent)) {
                        directory = directory + "\\" + nameFolderStudent + "\\" + nameFolderStudent + ".txt";
                        readStudentData(directory);
                        return;
                    }
                }
            }    
        }
    }

    public void createPDF(){
        GeneratePDFFile creatingPDF = new GeneratePDFFile();
        
    }
}
