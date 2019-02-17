package br.samuelpsouza.matrizcurricular;

import br.samuelpsouza.matrizcurricular.model.Course;
import br.samuelpsouza.matrizcurricular.model.Major;
import br.samuelpsouza.matrizcurricular.model.Matrix;
import br.samuelpsouza.matrizcurricular.model.Semester;
import br.samuelpsouza.matrizcurricular.repository.CourseRepository;
import br.samuelpsouza.matrizcurricular.repository.MajorRepository;
import br.samuelpsouza.matrizcurricular.repository.MatrixRepository;
import br.samuelpsouza.matrizcurricular.repository.SemesterRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static br.samuelpsouza.matrizcurricular.TestUtil.convertObjectToJsonBytes;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MatrizCurricularApplicationTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private MajorRepository majorRepository;
    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private MatrixRepository matrixRepository;

    private Major major;
    private Semester semester;
    private Course course;
    private Matrix matrix;

    @Test
    public void contextLoads() {
        assertNotNull(majorRepository);
        assertNotNull(semesterRepository);
        assertNotNull(courseRepository);
        assertNotNull(matrixRepository);
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

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void shouldNotPersistACourseObjectWithSameCode() {
        course = new Course("CC001FP001", "Fundamentos de Programação");
        this.courseRepository.save(course);
        Course newCourse = new Course("CC001FP001", "POO");
        this.courseRepository.save(newCourse);
    }

    @Test
    public void shouldCreateAMatrixObject() {
        matrix = new Matrix();
        assertNotNull(matrix);
    }

    @Test
    public void shouldCreateAndPersistAMatrixObject() {
        matrix = new Matrix();
        Matrix persistedMatrix = this.matrixRepository.save(matrix);
        assertNotNull(persistedMatrix.getId());
        assertNotNull(matrix.getSemesterList());
    }

    @Test
    public void shouldRequestMajorsAndHaveStatus200()
            throws Exception {

        mvc.perform(get("/majors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldRequestRootAndReceiveApiResponseJson() throws Exception {
        mvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", is("No donuts for you")))
                .andExpect(jsonPath("$.data", anything()));
    }

    @Test
    public void shouldRequestMajorAndReceiveApiResponseJson() throws Exception {
        mvc.perform(get("/majors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", notNullValue()))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.data", anything()));
    }

    @Test
    public void shouldRequestMajorAndReceiveApiResponseJsonWithContent() throws Exception {
        mvc.perform(get("/majors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.data.content", isA(ArrayList.class)));
    }

    @Test
    public void shouldAddANewMajorAndReceiveApiResponseJson() throws Exception {
        major = new Major("CC001", "Ciencia da Computação");
        mvc.perform(post("/majors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(major)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.data", notNullValue()));
    }

    @Test
    public void shouldUpdateAMajorAndReceiveApiResponseJson() throws Exception {
        major = new Major("CC001", "Ciencia da Computação");
        major = this.majorRepository.save(major);

        major.setTitle("Introdução a Ciencia da Computação");
        mvc.perform(put("/majors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(major)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", notNullValue()))

                // No retorno, o spring está convertendo o Long para inteiro
                // Deveria ser 56L, mas está retornando 56.
                .andExpect(jsonPath("$.data.id", is(major.getId().intValue())));
    }

    @Test
    public void shouldDeleteAMajorAndReceiveApiResponseJson() throws Exception {
        major = new Major("CC001", "Ciencia da Computação");
        major = this.majorRepository.save(major);

        mvc.perform(delete("/majors/" + major.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(major)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.data", anything()));
    }

    @Test
    public void shouldRequestASingleMajorAndReceiveApiResponseJson() throws Exception {
        major = new Major("CC001", "Ciencia da Computação");
        major = this.majorRepository.save(major);

        mvc.perform(get("/majors/" + major.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(major)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.data", anything()));
    }

    @Test
    public void shouldAddANewSemesterAndReceiveApiResponseJson() throws Exception {
        semester = new Semester("Semestre I");
        mvc.perform(post("/semesters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(semester)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.data", notNullValue()));
    }

    @Test
    public void shouldUpdateASemesterAndReceiveApiResponseJson() throws Exception {
        semester = new Semester("Semestre I");
        semester = this.semesterRepository.save(semester);

        semester.setDescription("Optativas");
        mvc.perform(put("/semesters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(semester)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.data.id", is(semester.getId().intValue())))
                .andExpect(jsonPath("$.data.description", is(semester.getDescription())));
    }

    @Test
    public void shouldDeleteASemesterAndReceiveApiResponseJson() throws Exception {
        semester = new Semester("Semestre I");
        semester = this.semesterRepository.save(semester);

        mvc.perform(delete("/semesters/" + semester.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(major)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.data", anything()));
    }

    @After
    public void cleanDatabaseUp() {
        this.courseRepository.deleteAll();
        this.majorRepository.deleteAll();
        this.semesterRepository.deleteAll();
    }
}
