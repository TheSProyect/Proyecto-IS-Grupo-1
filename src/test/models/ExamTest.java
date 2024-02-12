package test.models;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import main.models.Exam;

public class ExamTest {
    private Exam exam;

    @Test (expected = NullPointerException.class)
    public void testEmptyExam() {
        exam = new Exam();
        exam.getNameExam();
        exam.getNameCourse();
        exam.getTypeExam();
        exam.getNameTeacher();
    }

    @Test
    public void testExam() {
        exam = new Exam();
        Assertions.assertNotEquals(null, exam.getInstructions());
    }
}
