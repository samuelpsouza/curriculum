package br.samuelpsouza.matrizcurricular;

import br.samuelpsouza.matrizcurricular.model.Major;
import br.samuelpsouza.matrizcurricular.repository.MajorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static br.samuelpsouza.matrizcurricular.TestUtil.convertObjectToJsonBytes;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:test.properties")
public class MajorTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private MajorRepository majorRepository;

    private Major major;

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
    public void shouldRequestMajorsAndHaveStatus200()
            throws Exception {

        mvc.perform(get("/majors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
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
    @WithMockUser(roles="COORDENADOR")
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
    @WithMockUser(roles="COORDENADOR")
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
    @WithMockUser(roles="COORDENADOR")
    public void shouldDeleteAMajorAndReceiveApiResponseJson() throws Exception {
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
    public void shouldRequestASingleMajorAndReceiveApiResponseJson() throws Exception {
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
