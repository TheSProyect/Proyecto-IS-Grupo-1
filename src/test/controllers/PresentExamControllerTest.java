package test.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import main.controllers.PresentExamController;
import main.models.Exam;

public class PresentExamControllerTest {
    
    private PresentExamController presentExam;
    private Exam exam;

    @BeforeEach
    public void setUp() {
        presentExam = new PresentExamController(); 
        exam = new Exam(); 
        exam.setNameExam("ExamName");
        exam.setNameCourse("CourseName");
        exam.setResultExam(15);
        exam.setNumberQuestions(20);
        exam.setNameTeacher("TeacherName");
        }

    @Test
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
