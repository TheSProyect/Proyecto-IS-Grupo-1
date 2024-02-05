package main.utils;

import java.io.File;

public class Directory {
    private static Directory currentDirectory;
    String directory = System.getProperty("user.dir");
    String directorySubstitute;
    public Directory(){}
    
    public static Directory instance(){
        if(currentDirectory == null){
            currentDirectory = new Directory();
        }
        return currentDirectory;
    }
        
    public String getDirectoryUsers(){
        directorySubstitute = directory+File.separator+"src"+File.separator+"data"+File.separator+"Users";
        return directorySubstitute;
    }
    public String getDirectoryExams(){
        directorySubstitute = directory+File.separator+"src"+File.separator+"data"+File.separator+"Exams";
        return directorySubstitute;
    }
     public String getDirectoryStudents(){
        directorySubstitute = directory+File.separator+"src"+File.separator+"data"+File.separator+"Users"+File.separator+"Students";
        return directorySubstitute;
    }
    public String getDirectoryTeachers(){            
        directorySubstitute = directory+File.separator+"src"+File.separator+"data"+File.separator+"Users"+File.separator+"Teachers";
        return directorySubstitute;
    }      
}
