package main.models;

import main.views.pages.Frame;

public class UserData {
	private static UserData user;
	private String Username, Password, Mail;
	private boolean isAdmin = true;	
	
	public static UserData instace(){
		if (user == null){
			user = new UserData();
		}
		return user;
	}
	public UserData(){}
	
	public void setUsername(String Name){
		Username = Name;
	}
	
	public void setPassword(String Key){
		Password = Key;
	}
	
	public void setIsAdmin(boolean Admin){
		isAdmin = Admin;
	}
	
	public void setMail(String Email){
		Mail = Email;
	}

	public String getUsername(){
		return Username;
	}
	
	public String getPassword(){
		return Password;
	}
	
	public boolean isAdmin(){
		return isAdmin;
	}

	public String getMail(){
		return Mail;
	}

}
