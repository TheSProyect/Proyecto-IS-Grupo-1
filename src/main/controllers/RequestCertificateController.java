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
            currentCertificate.setNameExam(br.readLine());
            String answersCorrects = br.readLine();
            currentCertificate.setResultExam(Integer.parseInt(answersCorrects));
            String questionsExam =  br.readLine();
            currentCertificate.setQuestionsExam(Integer.parseInt(questionsExam));
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
                    }
                }
            }    
        }
    }

    public void createPDF(){
        List<String> informationToPDF = new ArrayList<String>();
        informationToPDF.add(currentUser.getUsername());
        informationToPDF.add(String.valueOf(currentCertificate.getResultExam()));
        informationToPDF.add(String.valueOf(currentCertificate.getQuestionsExam()));
        informationToPDF.add(currentCertificate.getNameCourse());
        informationToPDF.add(currentCertificate.getNameTeacher());
        GeneratePDFFile creatingPDF = new GeneratePDFFile();
        //creatingPDF.fillPDF(informationToPDF);
    }
    public String getNameStudentController(){
        return currentUser.getUsername();
    }
    public String getNameCourseController(){
        return currentCertificate.getNameCourse();
    }
    public int getResultAnswersController(){
        return currentCertificate.getResultExam();
    }
    public int getQuestionsExamController(){
        return currentCertificate.getQuestionsExam();
    }
    public String getNameTeacherController(){
        return currentCertificate.getNameTeacher();
    }
}
