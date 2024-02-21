package main.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import main.utils.Directory;

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
        }
        
        this.newUsername = Username;

        File newUserFile = new File(FileName);

        System.out.println(this.newUsername);

        if(newUserFile.mkdir()){
            newUserFile = new File(FileName+File.separator+"Password.txt");
            try {
                newUserFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            newUserFile = new File(FileName+File.separator+"Name.txt");
            try {
                newUserFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return true;
        } else {
            return false;
        }
    }
    }

    public void saveSignatureImg(String Path, String adminName){
         String destinationFolderDirectory = Directory.instance().getDirectoryTeachers()+File.separator+adminName;
        try {
            Path origin = Paths.get(Path);
            Path destination = Paths.get(destinationFolderDirectory + File.separator + "Signature.png"); 
            Files.copy(origin, destination);
        } catch (IOException e) {
            System.err.println("Ocurrió un error al copiar la imagen: " + e.getMessage());
        }
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
