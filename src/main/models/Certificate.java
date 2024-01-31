package main.models;

import main.models.Name;
import main.models.Course;

public class Certificate {
    private Name student;
    private Course course;

    public void setNameStudentCertificate(String name){
        student = new Name();
        student.setName(name);
    }
    public void setNameTeacherCertificate(String name){
        course = new Course();
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
