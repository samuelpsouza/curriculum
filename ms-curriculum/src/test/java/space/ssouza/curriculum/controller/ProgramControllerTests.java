package space.ssouza.curriculum.controller;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static space.ssouza.curriculum.TestUtil.convertObjectToJsonBytes;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import space.ssouza.curriculum.model.Program;
import space.ssouza.curriculum.repository.ProgramRepository;

@WebMvcTest(ProgramController.class)
class ProgramControllerTests {
	private static final String PROGRAMS_PATH = "/programs";

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ProgramRepository majorRepository;

	private Program program;

	@Test
	void shouldCreateAMajorObject() {
		program = new Program("CC001", "Ciencia da Computação");
		assertNotNull(program);
		assertNotNull(program.getTitle());
		assertNotNull(program.getCode());
	}

	@Test
	void shouldCreateAndPersistAMajorObject() {
		program = new Program("CC001", "Ciencia da Computação");
		final Program persistedMajor = majorRepository.save(program);
		assertEquals(persistedMajor.getCode(), program.getCode());
	}

	@Test
	void shouldRequestMajorsAndHaveStatus200() throws Exception {

		mvc.perform(get(PROGRAMS_PATH).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	void shouldRequestMajorAndReceiveApiResponseJsonWithContent() throws Exception {
		mvc.perform(get(PROGRAMS_PATH).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.success", is(true))).andExpect(jsonPath("$.message", notNullValue()))
				.andExpect(jsonPath("$.data.content", isA(ArrayList.class)));
	}

	@Test
	void shouldRequestMajorAndReceiveApiResponseJson() throws Exception {
		mvc.perform(get(PROGRAMS_PATH).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.success", notNullValue())).andExpect(jsonPath("$.message", notNullValue()))
				.andExpect(jsonPath("$.data", anything()));
	}

	@Test
	void shouldAddANewMajorAndReceiveApiResponseJson() throws Exception {
		program = new Program("CC001", "Ciencia da Computação");
		mvc.perform(
				post(PROGRAMS_PATH).contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(program)))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.success", is(true))).andExpect(jsonPath("$.message", notNullValue()))
				.andExpect(jsonPath("$.data", notNullValue()));
	}

	@Test
	void shouldUpdateAMajorAndReceiveApiResponseJson() throws Exception {
		program = new Program("CC001", "Ciencia da Computação");
		program = majorRepository.save(program);

		program.setTitle("Introdução a Ciencia da Computação");

		mvc.perform(
				put(PROGRAMS_PATH).contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(program)))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.success", is(true))).andExpect(jsonPath("$.message", notNullValue()))

				// No retorno, o spring está convertendo o Long para inteiro
				// Deveria ser 56L, mas está retornando 56.
				.andExpect(jsonPath("$.data.id", is(program.getId().intValue())));
	}

	@Test
	void shouldDeleteAMajorAndReceiveApiResponseJson() throws Exception {
		program = new Program("CC001", "Ciencia da Computação");
		program = this.majorRepository.save(program);

		mvc.perform(delete(PROGRAMS_PATH.concat(program.getId().toString())).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.success", is(true))).andExpect(jsonPath("$.message", notNullValue()))
				.andExpect(jsonPath("$.data", anything()));
	}

	@Test
	void shouldRequestASingleMajorAndReceiveApiResponseJson() throws Exception {
		program = new Program("CC001", "Ciencia da Computação");
		program = this.majorRepository.save(program);

		mvc.perform(get(PROGRAMS_PATH.concat(program.getId().toString())).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.success", is(true))).andExpect(jsonPath("$.message", notNullValue()))
				.andExpect(jsonPath("$.data", anything()));
	}

}
