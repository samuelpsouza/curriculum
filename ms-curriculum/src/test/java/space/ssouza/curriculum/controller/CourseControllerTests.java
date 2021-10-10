package space.ssouza.curriculum.controller;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static space.ssouza.curriculum.TestUtil.convertObjectToJsonBytes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import space.ssouza.curriculum.model.Course;
import space.ssouza.curriculum.repository.CourseRepository;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
class CourseControllerTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CourseRepository courseRepository;

    private Course course;

    @Test
    void contextLoads() {
        assertNotNull(courseRepository);
    }

    @Test
    void shouldCreateACourseObject() {
        course = new Course("CC001FP001", "Fundamentos de Programação");
        assertNotNull(course);
        assertNotNull(course.getCode());
    }

    @Test
    void shouldCreateAndPersistACourseObject() {
        course = new Course("CC001FP001", "Fundamentos de Programação");
        Course persistedCourse = this.courseRepository.save(course);
        assertEquals(persistedCourse.getCode(), course.getCode());
    }

    @Test
    void shouldNotPersistACourseObjectWithSameCode() {
        assertThrows(org.springframework.dao.DataIntegrityViolationException.class, () -> {
            course = new Course("CC001FP001", "Fundamentos de Programação");
            this.courseRepository.save(course);
            Course newCourse = new Course("CC001FP001", "POO");
            this.courseRepository.save(newCourse);

        });
    }

    @Test
    void shouldRequestASingleCourseAndReceiveApiResponseJson() throws Exception {
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
    void shouldAddANewCourseAndReceiveApiResponseJson() throws Exception {
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
    void shouldUpdateACourseAndReceiveApiResponseJson() throws Exception {
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
    void shouldDeleteACourseAndReceiveApiResponseJson() throws Exception {
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

    @AfterEach
    void cleanDatabaseUp() {
        this.courseRepository.deleteAll();
    }
}

