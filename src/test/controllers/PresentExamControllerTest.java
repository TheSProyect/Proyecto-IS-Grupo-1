package test.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
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
        List<String> description = new ArrayList<String>();
        description.add("Description");
        currentExam.setDescripcion(description);
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

        List<String> result = presentExam.readInformation(expectedCourseName, currentExam);
        assertEquals(expectedInformation.size(), result.size(), "Las listas no tienen la misma longitud");

        for (int i = 0; i < expectedInformation.size(); i++) {
            assertEquals(expectedInformation.get(i), result.get(i), "Los elementos de la lista no coinciden");
        }
    }
    @Test
    public void testReadInformation_NullCurrentExam() throws IOException {
        String expectedCourseName = "CourseName";
        Exam currentExam = null;
        assertThrows( NullPointerException.class, () -> {presentExam.readInformation(expectedCourseName, currentExam);});

    }
    @Test
    public void testExamFinished() throws IOException{
        assertThrows( NullPointerException.class, () -> {presentExam.examFinished();});
    }   
}
