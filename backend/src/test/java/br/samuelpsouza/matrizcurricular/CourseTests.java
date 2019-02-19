package br.samuelpsouza.matrizcurricular;

import br.samuelpsouza.matrizcurricular.model.Course;
import br.samuelpsouza.matrizcurricular.repository.CourseRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
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
public class CourseTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CourseRepository courseRepository;

    private Course course;

    @Test
    public void contextLoads() {
        assertNotNull(courseRepository);
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
    @WithMockUser(roles="COORDENADOR")
    public void shouldRequestASingleCourseAndReceiveApiResponseJson() throws Exception {
        course = new Course("CC001WD001", "Web Development");
        course = this.courseRepository.save(course);

        mvc.perform(get("/courses/" + course.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.data", anything()));
    }

    @Test
    @WithMockUser(roles="COORDENADOR")
    public void shouldAddANewCourseAndReceiveApiResponseJson() throws Exception {
        course = new Course("CC001WD001", "Web Development");
        mvc.perform(post("/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(course)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.data", notNullValue()));
    }

    @Test
    @WithMockUser(roles="COORDENADOR")
    public void shouldUpdateACourseAndReceiveApiResponseJson() throws Exception {
        course = new Course("CC001WD001", "Web Development");
        course = this.courseRepository.save(course);

        course.setDescription("Web Development with Java");
        mvc.perform(put("/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(course)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.data.id", is(course.getId().intValue())));
    }

    @Test
    @WithMockUser(roles="COORDENADOR")
    public void shouldDeleteACourseAndReceiveApiResponseJson() throws Exception {
        course = new Course("CC001WD001", "Web Development");
        course = this.courseRepository.save(course);

        mvc.perform(delete("/courses/" + course.getId())
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
        this.courseRepository.deleteAll();
    }
}

