package main.models;

import main.models.Name;
import main.models.Exam;

public class Certificate {
    private Name student;
    private Exam course = new Exam();

    public void setNameStudentCertificate(String name){
        student = new Name();
        student.setName(name);
    }
    public String getNameStudentCertificate(){
        return student.getName();
    }
    public void setNameTeacherCertificate(String name){
        course.setNameTeacherCourse(name);
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
    public void setSummary(int Summary){
        course.setResultExam(Summary);
    }
    public String getSummary(){
        return course.getSummary();
    }
}
