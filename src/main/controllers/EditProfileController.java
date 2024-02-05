package main.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import main.utils.UserData;

public class EditProfileController {

    public EditProfileController(){
        
    
    }

    public boolean setNewUsername(String newUsername) throws IOException{
        UserData currentUser = UserData.instance();

        if(newUsername == currentUser.getUsername()){
            return true;
        }

        String directory;

        directory = searchedDirectory(currentUser.isAdmin(), currentUser.getUsername());

        File newFile = new File(directory);
        
        
        newFile.createNewFile();
        currentUser.setUsername(newUsername);
        return true;

    }

    public void setNewUserInfo(String newMail, String newPassword) throws IOException{
        UserData currentUser = UserData.instance();
        currentUser.setMail(newMail);
        currentUser.setPassword(newPassword);

        String directory;

        directory = searchedDirectory(currentUser.isAdmin(), currentUser.getUsername());

        BufferedWriter writer = new BufferedWriter(new FileWriter(directory, true));
        writer.write(newPassword + "\n" + newMail);
        writer.close();

    }

    public String searchedDirectory(boolean isAdmin, String Username){
		String directory = System.getProperty("user.dir");
        if(isAdmin){
		directory = directory+File.separator+"src"+File.separator+"data"+File.separator+"Users";
		directory = directory+File.separator+"Teachers"+File.separator+Username+File.separator+"Password.txt";
        } else {
            directory = directory+File.separator+"src"+File.separator+"data"+File.separator+"Users";
		    directory = directory+File.separator+"Users"+File.separator+Username+File.separator+"Password.txt";
        }
        return directory;
	}

    public boolean userAlreadyExists(){
        return false;
    }
    
}

