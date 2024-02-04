package main.controllers;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.models.UserData;

public class LoginController {
	UserData currentUser = new UserData();
	public LoginController(){
		
		
	}
	
	public static void main(String[] args) throws IOException{
        
	}
	
	
	
	public boolean searchUser(String Username){
		
		try(BufferedReader Reader = new BufferedReader(new FileReader(searchedDirectory("Teachers")+Username))){
			currentUser.setUsername(Username);
			String currentPassword = Reader.readLine();

			currentUser.setPassword(currentPassword);
			
			currentUser.setIsAdmin(true);
			return true;
			
		} catch (IOException e){
			
			try(BufferedReader userReader = new BufferedReader(new FileReader(searchedDirectory("Students")))){
			currentUser.setUsername(Username);
			
			String currentPassword = userReader.readLine();
			currentPassword = userReader.readLine();
			currentPassword = userReader.readLine();
			currentUser.setPassword(currentPassword);
			
			currentUser.setIsAdmin(false);
			return true;

			} catch (IOException ee){
				
			return false;
			}
		}
		
		
	}

	public String searchedDirectory(String folder){
		String directory = System.getProperty("user.dir");
		directory = directory+File.separator+"src"+File.separator+"data"+File.separator+folder;
		directory = directory+File.separator;
		return directory;
	}
	
	public boolean verifyPassWord(String AttemptedPassword){
		
		if((currentUser.getPassword()).equals(AttemptedPassword)){
			return true;
		}
		return false;
	}
	
	public boolean isAdmin(){
		return currentUser.isAdmin();
	}
	
}
