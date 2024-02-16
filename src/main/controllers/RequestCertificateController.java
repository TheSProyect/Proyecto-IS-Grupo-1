package main.controllers;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.models.Certificate;
import main.utils.UserData;


public class RequestCertificateController extends TemplateExam{
    Certificate currentCertificate = new Certificate();

    public static void main(String[] args) throws IOException{
        RequestCertificateController r= new RequestCertificateController();
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
        String directory = verifyAdmin();
        File searchedFolder = new File(directory);
        if (searchedFolder.exists() && searchedFolder.isDirectory()) {
            File[] files = searchedFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory() || (!(file.getName().equals("Password.txt")) && file.getName().endsWith(".txt"))) {
                        if((file.getName().equals("Name.txt"))){
                            } else {
                                namesCourses.add(nameCourses(file));
                        }
                    }
                }
            }    
        }
        return namesCourses;
    }

    public void readStudentData(String directory){
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
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
    private void readNameStudent(String directory){
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            currentCertificate.setFirstNameStudent(br.readLine());
            currentCertificate.setLastNameStudent(br.readLine());
            br.close();     
            } catch (IOException e) {
                e.printStackTrace();
        }
    }

    public void searchFolderStudent(String nameCourse) {
        String directory = verifyAdmin();
        readNameStudent(directory+ File.separator + "Name.txt");
        readStudentData(directory+ File.separator + nameCourse + ".txt");
    }
                
    public void createPDF(){
        List<String> informationToPDF = new ArrayList<String>();
        informationToPDF.add(currentCertificate.getFirstNameStudent()+" "+currentCertificate.getLastNameStudent());
        informationToPDF.add(String.valueOf(this.getResultAnswersController()));
        informationToPDF.add(String.valueOf(currentCertificate.getQuestionsExam()));
        informationToPDF.add(currentCertificate.getNameCourse());
        informationToPDF.add(currentCertificate.getNameTeacher());
        GeneratePDFFile creatingPDF = new GeneratePDFFile(informationToPDF);
    }
    public String getNameStudentController(){
        return currentCertificate.getFirstNameStudent()+" "+currentCertificate.getLastNameStudent();
    }
    public String getNameExamController(){
        return currentCertificate.getNameExam();
    }
    public String getNameCourseController(){
        return currentCertificate.getNameCourse();
    }
    public float getResultAnswersController(){
        return currentCertificate.getResultExam();
    }
    public int getQuestionsExamController(){
        return currentCertificate.getQuestionsExam();
    }
    public String getNameTeacherController(){
        return currentCertificate.getNameTeacher();
    }
}
