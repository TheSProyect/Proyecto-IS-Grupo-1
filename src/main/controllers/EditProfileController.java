package main.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import main.utils.UserData;
import main.utils.Directory;

public class EditProfileController {
    UserData currentUser = UserData.instance();

    public EditProfileController() throws IOException{
        
        
    }

    public boolean setNewUsername(String newUsername) throws IOException{
        

        if(newUsername == currentUser.getUsername()){
            return true;
        }

        if(searchUser(newUsername)){
            return false;
        }

        File newFile = new File(Directory.instance().getDirectoryStudents()+File.separator+newUsername);
        File oldFile = new File(Directory.instance().getDirectoryStudents()+File.separator+currentUser.getUsername());
        
        oldFile.renameTo(newFile);

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
}

