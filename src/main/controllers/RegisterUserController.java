package main.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import main.utils.UserData;
import main.utils.Directory;

import java.io.IOException;

public class RegisterUserController {
    boolean isAdmin;
    private String newUsername;
    
    public RegisterUserController(){
        
        
    }

    public boolean RegisterNewUser(String Username, boolean isAdmin){

        if(searchUser(Username)){
            return false;
        } else {

        String FileName;
        
        if(isAdmin){
        FileName = Directory.instance().getDirectoryTeachers()+File.separator+Username;
        this.isAdmin = true;
        } else{
        FileName = Directory.instance().getDirectoryStudents()+File.separator+Username;
        this.isAdmin = false;

        this.newUsername = Username;
        
        }
        File newUserFile = new File(FileName);
        
        if(newUserFile.mkdir()){
            newUserFile = new File(FileName+File.separator+"Password.txt");
            try {
                newUserFile.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            newUserFile = new File(FileName+File.separator+"Name.txt");
            try {
                newUserFile.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return true;
        } else {
            return false;
        }
    }
    }

    public void setSignature(){
        
    }


    public void setNewUserPassword(String newUserPassword, String newMail){
        String FileName;
        if(isAdmin){
            FileName = Directory.instance().getDirectoryTeachers()+File.separator+newUsername;
            this.isAdmin = true;
        } else{
            FileName = Directory.instance().getDirectoryStudents()+File.separator+newUsername;
            this.isAdmin = false;
        }
        FileName = FileName+File.separator+"Password.txt";
        writeFile(FileName, newUserPassword+"\n"+newMail);

    }

    public void setNewUserName(String newUserFirstname, String newUserLastname){
        String FileName;
        if(isAdmin){
            FileName = Directory.instance().getDirectoryTeachers()+File.separator+newUsername;
            this.isAdmin = true;
        } else{
            FileName = Directory.instance().getDirectoryStudents()+File.separator+newUsername;
            this.isAdmin = false;
        }
        FileName = FileName+File.separator+"Name.txt";
        writeFile(FileName, newUserFirstname+"\n"+newUserLastname);

    }

    public void writeFile(String Filename, String writeString){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Filename, true));
            writer.write(writeString);
            writer.close();
            
            } catch (IOException e) {
              e.printStackTrace();
            }
    }

    public boolean searchUser(String Username){
        if(new File(searchedDirectory("Students", Username)).isDirectory()){
            return true;
        } else if (new File(searchedDirectory("Teachers", Username)).isDirectory()){
            return true;
        } else {
            return false;
        }
		
	}

    public String searchedDirectory(String Folder, String Username){
		Directory currentDirectory = Directory.instance();
		String directory = currentDirectory.getDirectoryUsers()+File.separator+Folder+File.separator+Username;
        
		return directory;
	}
	

}
