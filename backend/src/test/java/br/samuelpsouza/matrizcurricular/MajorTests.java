package br.samuelpsouza.matrizcurricular;

import br.samuelpsouza.matrizcurricular.model.Major;
import br.samuelpsouza.matrizcurricular.repository.MajorRepository;
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

    @After
    public void cleanDatabaseUp() {
        this.majorRepository.deleteAll();
    }
}
