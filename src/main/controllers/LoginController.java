package main.controllers;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import main.utils.Directory;
import main.utils.UserData;

public class LoginController {
	public UserData currentUser = UserData.instance();
	public LoginController(){}

	public boolean searchUser(String Username){
		
		try(BufferedReader adminR = new BufferedReader(new FileReader(searchedDirectory("Teachers", Username, "Password")))){
			currentUser.setUsername(Username);
			String currentPassword = adminR.readLine();

			currentUser.setPassword(currentPassword);
			
			currentUser.setIsAdmin(true);

			try(BufferedReader FullnameR = new BufferedReader(new FileReader(searchedDirectory("Teachers", Username, "Name")))){
				currentUser.setFullname(FullnameR.readLine(), FullnameR.readLine());
			} catch (IOException e){}
			
			return true;
			
		} catch (IOException e){
			
			try(BufferedReader userR = new BufferedReader(new FileReader(searchedDirectory("Students",Username, "Password")))){
			currentUser.setUsername(Username);
			
			String currentPassword = userR.readLine();
			currentUser.setPassword(currentPassword);

			String currentMail = userR.readLine();
			currentUser.setMail(currentMail);
			
			currentUser.setIsAdmin(false);
			
			try(BufferedReader FullnameR = new BufferedReader(new FileReader(searchedDirectory("Teachers", Username, "Name")))){
				currentUser.setFullname(FullnameR.readLine(), FullnameR.readLine());
				System.out.println(currentUser.getFullname());
			} catch (IOException ee){}

			return true;

			} catch (IOException ee){
				
			return false;
			}
		}
		
		
	}

	

	public String searchedDirectory(String Folder, String Username,String Field){
		Directory currentDirectory = Directory.instance();
		String directory = currentDirectory.getDirectoryUsers()+File.separator+Folder+File.separator+Username+File.separator+Field+".txt";
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

	public void setFullname(){

	}
	
}
