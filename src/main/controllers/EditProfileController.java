package main.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import main.models.UserData;

public class EditProfileController {

    public EditProfileController(){
        
    

    }


    public boolean setNewUsername(String newUsername) throws IOException{
        UserData currentUser = new UserData();
        String directory = System.getProperty("user.dir");
        if(currentUser.isAdmin()){
		directory = System.getProperty("user.dir");
        directory = directory+File.separator+"src"+File.separator;
        directory = directory+"data"+File.separator+"Users"+File.separator;
        directory = directory+"Teachers"+File.separator+ newUsername +".txt";
        File adminfile = new File(directory);
        
        if(adminfile.exists()){
            return false;
        } else {
            adminfile.createNewFile();
            currentUser.setUsername(newUsername);
            return true;
        }
        
        } else {
            directory = System.getProperty("user.dir");
            directory = directory+File.separator+"src"+File.separator;
            directory = directory+"data"+File.separator+"Users"+File.separator;
            directory = directory+"Studens"+File.separator+ currentUser.getUsername() +".txt";
            File userfile = new File(directory);
            if(userfile.exists()){
                return false;
            } else { 
                userfile.createNewFile();
                currentUser.setUsername(newUsername);
                return true;
            }
        }

    }

    public void setNewUserInfo(String newMail, String newPassword) throws IOException{
        UserData currentUser = new UserData();
        currentUser.setMail(newMail);
        currentUser.setPassword(newPassword);

        String directory = System.getProperty("user.dir");
        String folder;
        if(currentUser.isAdmin()){
            folder = "Teachers";
        } else {
            folder = "Students";
        }
        
        directory = directory+File.separator+"src"+File.separator;
        directory = directory+"data"+File.separator+"Users"+File.separator;
        directory = directory+folder+File.separator+ currentUser.getUsername() +".txt";

        BufferedWriter writer = new BufferedWriter(new FileWriter(directory, true));
        writer.write(newPassword + "\n" + newMail);
        writer.close();

    }
    
}

