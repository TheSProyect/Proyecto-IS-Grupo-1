package test.models;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import main.models.Exam;
import main.utils.Directory;
public class ExamTest {
    private Exam exam;
    private String instructions;

    @Before
    public void setUp() throws IOException {
        String instructionPath = Directory.instance().getDirectoryExams()+File.separator+"Instructions.txt";
        File file = new File(instructionPath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String st;
            instructions = "";
            boolean first = true;
            while ((st = br.readLine()) != null) {
                instructions += st;
            }
        }

        exam = new Exam();
        
    }

    @Test 
    public void testExam() {
        Assertions.assertEquals(instructions, exam.getInstructions());
    }

    @Test
    public void testExamIsEmpty() {
        assertThrows(NullPointerException.class, () -> {exam.getNameExam();});
        assertThrows(NullPointerException.class, () -> {exam.getNameCourse();});
        assertNull(exam.getType());
        assertThrows(NullPointerException.class, () -> {exam.getNameTeacher();});
    }
}
