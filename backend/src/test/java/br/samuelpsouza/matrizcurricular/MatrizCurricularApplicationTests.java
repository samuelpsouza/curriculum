package br.samuelpsouza.matrizcurricular;

import br.samuelpsouza.matrizcurricular.model.Course;
import br.samuelpsouza.matrizcurricular.repository.CourseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatrizCurricularApplicationTests {
    @Autowired
    private CourseRepository courseRepository;
    private Course course;

    @Test
    public void contextLoads() {
    }

    @Test
    public void shouldCreateACourseObject() {
        course = new Course("CC001", "Ciencia da Computação");
        assertNotNull(course);
        assertNotNull(course.getTitle());
        assertNotNull(course.getCode());
    }

    @Test
    public void shouldCreateAndPersistAObject() {
        course = new Course("CC001", "Ciencia da Computação");
        Course persistedCourse = this.courseRepository.save(course);
        assertTrue(persistedCourse.getCode().equals(course.getCode()));
    }
}

