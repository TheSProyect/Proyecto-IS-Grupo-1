package main.utils;

public class UserData {
	private static UserData currentUser;
	private String Username, Password, Mail;
	private boolean isAdmin;	
	
	public UserData(){}

	public static UserData instance(){
		if(currentUser == null){
			currentUser = new UserData();
		}
		return currentUser;
	}
	
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
