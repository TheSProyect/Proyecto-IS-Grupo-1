package main.models;

public class Course {
    private  Name name;
    private Name teacher;
    private Name usernameTeacher;

    public void setNameTeacher(String name){
        teacher = new Name();
        teacher.setName(name);
    }
    public void setNameCourse(String nameCourse){
        name = new Name();
        name.setName(nameCourse);
    }
    public String getNameCourse(){
        return name.getName();
    }
    public String getNameTeacher(){
        return teacher.getName();
    }
    public void setUsernameTeacher(String name){
        usernameTeacher = new Name();
        usernameTeacher.setName(name);
    }
    public String getUsernameTeacher(){
        return usernameTeacher.getName();
    }
}
