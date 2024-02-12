package test.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import main.controllers.PresentExamController;
import main.models.Exam;
import main.utils.UserData;

public class PresentExamControllerTest {
    
    private PresentExamController presentExam;
    private Exam currentExam;

    @BeforeEach
    public void setUp() {
        presentExam = new PresentExamController(); 
        currentExam = new Exam(); 
        currentExam.setNameExam("ExamName");
        currentExam.setType("Type");
        currentExam.setDescripcion("Description");
        currentExam.setNameCourse("CourseName");
        currentExam.setResultExam(15);
        currentExam.setNumberQuestions(20);
        currentExam.setNameTeacher("TeacherName");
        currentExam.setDuration(30);
        UserData currentUser = UserData.instance();
        currentUser.setUsername("Usuario");
        currentUser.setIsAdmin(false);
        }

    @Test

    public void testReadIndformation(){
        String expectedCourseName = "CourseName";
        List<String> expectedInformation = new ArrayList<>();
        expectedInformation.add("ExamName");
        expectedInformation.add("Description");
        expectedInformation.add("Type");
        expectedInformation.add("30");
        expectedInformation.add("TeacherName");
        expectedInformation.add(expectedCourseName);

        List<String> result = presentExam.readInformation(expectedCourseName);
        assertEquals(expectedInformation.size(), result.size(), "Las listas no tienen la misma longitud");

        for (int i = 0; i < expectedInformation.size(); i++) {
            assertEquals(expectedInformation.get(i), result.get(i), "Los elementos de la lista no coinciden");
        }
    }

    public void testExamFinished() throws IOException {
        presentExam.examFinished();

        String expectedFileName = "CourseName.txt";
        String directory = presentExam.verifyAdmin();
        File verifyFile = new File(directory + File.separator + expectedFileName);
        assertTrue(verifyFile.exists(), "El archivo no se creó correctamente");

        Path filePath = Paths.get(directory, expectedFileName);
        assertTrue(Files.readString(filePath).contains("CourseName"));
        assertTrue(Files.readString(filePath).contains("ExamName"));
        assertTrue(Files.readString(filePath).contains("15"));
        assertTrue(Files.readString(filePath).contains("20"));
        assertTrue(Files.readString(filePath).contains("TeacherName"));

        verifyFile.delete();
    }   
}
