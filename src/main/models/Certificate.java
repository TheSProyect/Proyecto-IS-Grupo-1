package main.models;

public class Certificate {
    private Name name;
    private Exam course = new Exam();
    private int questionsExam;

    public void setUsernameTeacher(String username){
        course.setUsernameTeacher(username);
    }

    public String getUsernameTeacher(){
        return course.getUsernameTeacher();
    }

    public void setQuestionsExam(int questionsExam){
        this.questionsExam = questionsExam;
    }
    public int getQuestionsExam(){
        return questionsExam;
    }
    public void setFirstNameStudent(String first){
        name = new Name();
        name.setFirstName(first);
    }
    public String getFirstNameStudent(){
        return name.getFirstName();
    }
    public void setLastNameStudent(String last){
        name.setLastName(last);
    }
    public String getLastNameStudent(){
        return name.getLastName();
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
    public void setResultExam(float Summary){
        course.setResultExam(Summary);
    }
    public float getResultExam(){
        return course.getResultExam();
    }
    public void setNameExam(String nameExam){
        course.setNameExam(nameExam);
    }
    public String getNameExam(){
        return course.getNameExam();
    }
}
