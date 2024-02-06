package main.models;

import main.models.Name;
import main.models.Exam;

public class Certificate {
    private Name student;
    private Exam course = new Exam();
    private int questionsExam;

    public void setQuestionsExam(int questionsExam){
        this.questionsExam = questionsExam;
    }
    public int getQuestionsExam(){
        return questionsExam;
    }
    public void setNameStudentCertificate(String name){
        student = new Name();
        student.setName(name);
    }
    public String getNameStudentCertificate(){
        return student.getName();
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
