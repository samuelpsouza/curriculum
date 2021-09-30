package space.ssouza.curriculum;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import space.ssouza.curriculum.model.Matrix;
import space.ssouza.curriculum.model.Semester;
import space.ssouza.curriculum.repository.MatrixRepository;
import space.ssouza.curriculum.repository.SemesterRepository;

import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.DOCKER;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static space.ssouza.curriculum.TestUtil.convertObjectToJsonBytes;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureEmbeddedDatabase(beanName = "dataSource3", provider = DOCKER)
@FlywayTest
public class CurriculumApplicationTests {
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
        assertNotNull(matrix.getCourseList());
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
    @WithMockUser(roles="COORDENADOR")
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
    @WithMockUser(roles="COORDENADOR")
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
    @WithMockUser(roles="COORDENADOR")
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

    @AfterEach
    public void cleanDatabaseUp() {
        this.semesterRepository.deleteAll();
    }
}
