package test.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.text.BadLocationException;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import main.models.Exam;
import main.utils.Directory;
public class ExamTest {
    private Exam exam;
    private String instructions;

    @BeforeAll
    private void setUp() throws IOException {
        String instructionPath = Directory.instance().getDirectoryExams()+File.separator+"Instructions.txt";
        File file = new File(instructionPath);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        instructions = "";
        boolean first = true;
        while ((st = br.readLine()) != null) {
            if (first) {
                instructions = st;
                first = false;
            } else {
                instructions = instructions + '\n' + st;
            }
        }
        
    }

    @Test 
    public void testExam() throws IOException {
        setUp();
        exam = new Exam();
        assertThrows(NullPointerException.class, () -> {exam.getNameExam();});
        assertThrows(NullPointerException.class, () -> {exam.getNameCourse();});
        assertNull(exam.getTypeExam());
        assertThrows(NullPointerException.class, () -> {exam.getNameTeacher();});
        Assertions.assertEquals(instructions, exam.getInstructions());
    }
}
