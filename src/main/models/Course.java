package main.models;

import main.models.Name;
import main.models.Result;

public class Course {
    private  Name name;
    private Result summary;
    private Name teacher;

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
}
