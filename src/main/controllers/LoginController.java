package main.controllers;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import main.utils.Directory;
import main.utils.UserData;

public class LoginController {
	public UserData currentUser = UserData.instance();
	public LoginController(){
		
		
	}
	
	public static void main(String[] args) throws IOException{
        
	}

	
	public boolean searchUser(String Username){
		
		try(BufferedReader adminR = new BufferedReader(new FileReader(searchedDirectory("Teachers", Username)))){
			currentUser.setUsername(Username);
			String currentPassword = adminR.readLine();

			currentUser.setPassword(currentPassword);
			
			currentUser.setIsAdmin(true);
			return true;
			
		} catch (IOException e){
			
			try(BufferedReader userR = new BufferedReader(new FileReader(searchedDirectory("Students",Username)))){
			currentUser.setUsername(Username);
			
			String currentPassword = userR.readLine();
			currentUser.setPassword(currentPassword);
			
			currentUser.setIsAdmin(false);
			return true;

			} catch (IOException ee){
				
			return false;
			}
		}
		
		
	}

	public String searchedDirectory(String Folder, String Username){
		Directory currentDirectory = Directory.instance();
		String directory = currentDirectory.getDirectoryUsers()+File.separator+Folder+File.separator+Username+File.separator+"Password.txt";
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
