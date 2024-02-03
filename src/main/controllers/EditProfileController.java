package main.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import main.models.UserData;

public class EditProfileController {

    public EditProfileController(){
        
    

    }

    public boolean setNewUsername(String newUsername){
        UserData currentUser = new UserData();
		String directory = System.getProperty("user.dir");
        directory = directory+File.separator+"src"+File.separator+"data"+File.separator+"Users"+File.separator+"Teachers"+File.separator+ newUsername +".txt";
        try(BufferedReader adminReader = new BufferedReader(new FileReader(directory))){
			return false;
			
		} catch (IOException e){
            try(BufferedReader adminReader = new BufferedReader(new FileReader(directory))){
            return true;
            } catch (IOException e){
            currentUser.setUsername(newUsername);
            return true;
            }
        }
        

    }
    public void setNewMail(String newMail){
        UserData currentUser = new UserData();
        currentUser.setMail(newMail);

    }

    public void setNewPassword(String newPassword){
        UserData currentUser = new UserData();
        currentUser.setPassword(newPassword);

    }

}
