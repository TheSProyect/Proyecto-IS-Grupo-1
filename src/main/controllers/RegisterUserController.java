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
    String newUsername;
    
    public RegisterUserController() throws IOException{
        
        
    }

    public boolean RegisterNewUser(String Username, boolean isAdmin) throws IOException{
        String FileName;
        
        if(isAdmin){
        FileName = Directory.instance().getDirectoryTeachers()+File.separator+Username;
        this.isAdmin = true;
        } else{
        FileName = Directory.instance().getDirectoryStudents()+File.separator+Username;
        this.isAdmin = false;
        
    }

        File newUserFile = new File(FileName);
        
        if(newUserFile.mkdir()){
            newUserFile = new File(FileName+File.separator+"Password.txt");
            newUserFile.createNewFile();

            newUserFile = new File(FileName+File.separator+"Name.txt");
            newUserFile.createNewFile();

            return true;
        } else {
            return false;
        }
    }

    public void setSignature(){
        
    }

    public void setNewUserPassword(String newUserPassword){
        String FileName;
        if(isAdmin){
            FileName = Directory.instance().getDirectoryTeachers()+File.separator+newUsername;
            this.isAdmin = true;
        } else{
            FileName = Directory.instance().getDirectoryStudents()+File.separator+newUsername;
            this.isAdmin = false;
        }
        writeFile(FileName, newUserPassword);

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
		
		try(BufferedReader adminR = new BufferedReader(new FileReader(searchedDirectory("Teachers", Username)))){

			return true;
			
		} catch (IOException e){
			
			try(BufferedReader userR = new BufferedReader(new FileReader(searchedDirectory("Students",Username)))){

			return true;

			} catch (IOException ee){
				
			return false;
			}
		}
		
		
	}

    public String searchedDirectory(String Folder, String Username){
		Directory currentDirectory = Directory.instance();
		String directory = currentDirectory.getDirectoryUsers()+File.separator+Folder+File.separator+Username;
		return directory;
	}
	

}
