package br.samuelpsouza.matrizcurricular;

import br.samuelpsouza.matrizcurricular.model.Major;
import br.samuelpsouza.matrizcurricular.model.Semester;
import br.samuelpsouza.matrizcurricular.repository.MajorRepository;
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
    private Major major;
    private Semester semester;

    @Test
    public void contextLoads() {
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
}

