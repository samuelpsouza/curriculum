package br.samuelpsouza.matrizcurricular;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatrizCurricularApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
    public void shouldCreateACourseObject(){
	    Course course = new Course("Ciencia da Computação");
        Assert.assertTrue(course != null);
    }
}

