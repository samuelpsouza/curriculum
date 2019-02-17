package br.samuelpsouza.matrizcurricular;

import br.samuelpsouza.matrizcurricular.model.Matrix;
import br.samuelpsouza.matrizcurricular.model.Semester;
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
    private SemesterRepository semesterRepository;

    @Autowired
    private MatrixRepository matrixRepository;

    private Semester semester;
    private Matrix matrix;

    @Test
    public void contextLoads() {
        assertNotNull(semesterRepository);
        assertNotNull(matrixRepository);
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
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.data", anything()));
    }

    @After
    public void cleanDatabaseUp() {
        this.semesterRepository.deleteAll();
    }
}
