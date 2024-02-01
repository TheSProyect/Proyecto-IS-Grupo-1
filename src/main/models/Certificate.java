package main.models;

import main.models.Name;
import main.models.Course;

public class Certificate {
    private Name student;
    private Exam course;

    public void setNameStudentCertificate(String name){
        student = new Name();
        student.setName(name);
    }
    public String getNameStudentCertificate(){
        return student.getName();
    }
    public void setNameTeacherCertificate(String name){
        course = new Exam();
        course.setNameTeacherCourse(name);
    }
    public String getNameCourse(){
        return course.getNameCourse();
    }
    public String getNameTeacher(){
        return course.getNameTeacher();
    }
    public String getSummary(){
        return course.getSummary();
    }
}
