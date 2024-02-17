package test.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.nio.file.Files;

import main.controllers.RegisterUserController;
import main.utils.Directory;

public class RegisterUserControllerTest{

    private RegisterUserController RegisterControl;
    private String usedUserName, unusedUserName, usedAdminName, directory;

    @BeforeEach
        public void setUp(){
        RegisterControl = new RegisterUserController();
        usedUserName = "Usuario";
        unusedUserName = "Usuario2";
        usedAdminName = "Profesor";
        directory = Directory.instance().getDirectoryTeachers();
    }

    @Test
    public void testSearchUser(){
        assertTrue(RegisterControl.searchUser(usedUserName));
        assertTrue(RegisterControl.searchUser(usedAdminName));
        assertFalse(RegisterControl.searchUser(unusedUserName));
    }


    @Test
    public void testWriteFile(){

        assertDoesNotThrow(() -> {RegisterControl.writeFile(directory ,null );;});

        assertDoesNotThrow(() -> {RegisterControl.writeFile(directory ,"expectedWriteString" );;});

        String expectedFolder = null;
        assertThrows(NullPointerException.class, () -> {RegisterControl.writeFile(expectedFolder ,"expectedWriteString" );;});
        
    }

    @Test
    public void testRegisterNewUser_usedUsername(){
        assertFalse(RegisterControl.RegisterNewUser(usedUserName, true));
        assertFalse(RegisterControl.RegisterNewUser(usedUserName, false));

        assertFalse(RegisterControl.RegisterNewUser(usedAdminName, true));
        assertFalse(RegisterControl.RegisterNewUser(usedAdminName, false));


        
    }

    @Test
    public void testRegisterNewUser_unusedUsername(){
    assertTrue(RegisterControl.RegisterNewUser(unusedUserName, true));
    cleanup();
    }

    /*@AfterAll*/
    public void cleanup(){
        File unusedDirectory = new File(directory+File.separator+unusedUserName);
        if(unusedDirectory.isDirectory()){
        
        File[] contents = unusedDirectory.listFiles();
        
        for (File f : contents) {
            if (! Files.isSymbolicLink(f.toPath())) {
                f.delete();
            }
        }

        unusedDirectory.delete();
        
        }
    }

}