

public class UserData {
	private String Username, Password;
	private boolean isAdmin = true;
	
	public UserData(){
		
		
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
	
	public String getUsername(){
		return Username;
	}
	
	public String getPassword(){
		return Password;
	}
	
	public boolean isAdmin(){
		return isAdmin;
	}
}