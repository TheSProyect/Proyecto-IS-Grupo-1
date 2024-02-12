package test.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import main.models.Exam;

public class ExamTest {
    private Exam exam;

    @Test (expected = NullPointerException.class)
    public void testExam() {
        exam = new Exam();
        assertEquals(null, exam.getNameTeacher());
        // assertThrows(Throwable.class, exam.getNameExam());
    }
}
