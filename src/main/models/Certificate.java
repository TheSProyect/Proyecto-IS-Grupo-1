package main.models;

import main.models.Name;
import main.models.Exam;

public class Certificate {
    private Name firstNameStudent;
    private Name lastNameStudent;
    private Exam course = new Exam();
    private int questionsExam;

    public void setQuestionsExam(int questionsExam){
        this.questionsExam = questionsExam;
    }
    public int getQuestionsExam(){
        return questionsExam;
    }
    public void setFirstNameStudent(String name){
        firstNameStudent = new Name();
        firstNameStudent.setFirstName(name);
    }
    public String getFirstNameStudent(){
        return firstNameStudent.getFirstName();
    }
    public void setLastNameStudent(String name){
        lastNameStudent = new Name();
        lastNameStudent.setLastName(name);
    }
    public String getLastNameStudent(){
        return lastNameStudent.getLastName();
    }
    public void setNameTeacherCertificate(String name){
        course.setNameTeacher(name);
    }
    public String getNameTeacher(){
        return course.getNameTeacher();
    }
    public void setNameCourse(String nameCourse){
        course.setNameCourse(nameCourse);
    }
    public String getNameCourse(){
        return course.getNameCourse();
    }
    public void setResultExam(int Summary){
        course.setResultExam(Summary);
    }
    public int getResultExam(){
        return course.getResultExam();
    }
    public void setNameExam(String nameExam){
        course.setNameExam(nameExam);
    }
    public String getNameExam(){
        return course.getNameExam();
    }
}
