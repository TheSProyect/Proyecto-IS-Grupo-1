package test.models;
import org.junit.*;

import main.models.Question;

public class QuestionTest {
    @Test
    public void test(){
        Question prueba = new Question();
        Assert.assertEquals("hello", prueba.sayHi());
    }
}
