package space.ssouza.curriculum;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import space.ssouza.curriculum.model.Major;
import space.ssouza.curriculum.repository.MajorRepository;

import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static space.ssouza.curriculum.TestUtil.convertObjectToJsonBytes;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureEmbeddedDatabase
@FlywayTest
class MajorTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private MajorRepository majorRepository;

    private Major major;

    @Test
    void shouldCreateAMajorObject() {
        major = new Major("CC001", "Ciencia da Computação");
        assertNotNull(major);
        assertNotNull(major.getTitle());
        assertNotNull(major.getCode());
    }

    @Test
    void shouldCreateAndPersistAMajorObject() {
        major = new Major("CC001", "Ciencia da Computação");
        Major persistedMajor = this.majorRepository.save(major);
        assertEquals(persistedMajor.getCode(), major.getCode());
    }

    @Test
    void shouldRequestMajorsAndHaveStatus200()
            throws Exception {

        mvc.perform(get("/majors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldRequestMajorAndReceiveApiResponseJsonWithContent() throws Exception {
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
    void shouldRequestMajorAndReceiveApiResponseJson() throws Exception {
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
    void shouldAddANewMajorAndReceiveApiResponseJson() throws Exception {
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
    void shouldUpdateAMajorAndReceiveApiResponseJson() throws Exception {
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
    void shouldDeleteAMajorAndReceiveApiResponseJson() throws Exception {
        major = new Major("CC001", "Ciencia da Computação");
        major = this.majorRepository.save(major);

        mvc.perform(delete("/majors/" + major.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.data", anything()));
    }

    @Test
    void shouldRequestASingleMajorAndReceiveApiResponseJson() throws Exception {
        major = new Major("CC001", "Ciencia da Computação");
        major = this.majorRepository.save(major);

        mvc.perform(get("/majors/" + major.getId())
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
        this.majorRepository.deleteAll();
    }
}
