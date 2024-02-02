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
        LoginController loginC = new LoginController();
	}
	
	
	
	public boolean searchUser(String Username){
		String directory = System.getProperty("user.dir");
        directory = directory+File.separator+"src"+File.separator+"data"+File.separator+"Users"+File.separator+"Teachers"+File.separator+ Username +".txt";
		
		try(BufferedReader adminReader = new BufferedReader(new FileReader(directory))){
			currentUser.setUsername(Username);
			String currentPassword = adminReader.readLine();
			currentPassword = adminReader.readLine();
			currentPassword = adminReader.readLine();

			currentUser.setPassword(currentPassword);
			
			currentUser.setIsAdmin(true);
			return true;
			
		} catch (IOException e){
			directory = System.getProperty("user.dir");
			directory = directory+File.separator+"src"+File.separator+"data"+File.separator+"Users"+File.separator+"Students"+File.separator+ Username +".txt";
			try(BufferedReader userReader = new BufferedReader(new FileReader(directory))){
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
