package br.samuelpsouza.matrizcurricular;

import br.samuelpsouza.matrizcurricular.model.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatrizCurricularApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
    public void shouldCreateACourseObject(){
	    Course course = new Course("Ciencia da Computação");
        assertNotNull(course);
        assertNotNull(course.getTitle());
    }


}

