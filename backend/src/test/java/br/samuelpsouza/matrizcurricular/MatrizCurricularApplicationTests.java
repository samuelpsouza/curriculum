package br.samuelpsouza.matrizcurricular;

import br.samuelpsouza.matrizcurricular.model.Course;
import br.samuelpsouza.matrizcurricular.model.Major;
import br.samuelpsouza.matrizcurricular.model.Semester;
import br.samuelpsouza.matrizcurricular.repository.MajorRepository;
import br.samuelpsouza.matrizcurricular.repository.SemesterRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatrizCurricularApplicationTests {
    @Autowired
    private MajorRepository majorRepository;
    @Autowired
    private SemesterRepository semesterRepository;

    private Major major;
    private Semester semester;
    private Course course;

    @Test
    public void contextLoads() {
        assertNotNull(majorRepository);
        assertNotNull(semesterRepository);
    }

    @Test
    public void shouldCreateAMajorObject() {
        major = new Major("CC001", "Ciencia da Computação");
        assertNotNull(major);
        assertNotNull(major.getTitle());
        assertNotNull(major.getCode());
    }

    @Test
    public void shouldCreateAndPersistAMajorObject() {
        major = new Major("CC001", "Ciencia da Computação");
        Major persistedMajor = this.majorRepository.save(major);
        assertEquals(persistedMajor.getCode(), major.getCode());
    }

    @Test
    public void shouldCreateASemesterObject() {
        semester = new Semester("Semestre I");
        assertNotNull(semester);
        assertNotNull(semester.getDescription());
    }

    @Test
    public void shouldCreateAndPersistASemesterObject() {
        semester = new Semester("Semestre I");
        Semester persistedSemester = this.semesterRepository.save(semester);
        assertEquals(persistedSemester.getDescription(), semester.getDescription());
    }

    @Test
    public void shouldCreateACourseObject() {
        course = new Course("CC001FP001", "Fundamentos de Programação");
        assertNotNull(course);
        assertNotNull(course.getCode());
    }

    @Test
    public void shouldCreateAndPersistACourseObject() {
        course = new Course("CC001FP001", "Fundamentos de Programação");
        Course persistedCourse = this.courseRepository.save(course);
        assertEquals(persistedCourse.getCode(), course.getCode());
    }

}

