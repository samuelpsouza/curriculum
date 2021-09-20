package br.samuelpsouza.matrizcurricular;

import br.samuelpsouza.matrizcurricular.model.Role;
import br.samuelpsouza.matrizcurricular.model.User;
import br.samuelpsouza.matrizcurricular.payload.LoginRequest;
import br.samuelpsouza.matrizcurricular.repository.RoleRepository;
import br.samuelpsouza.matrizcurricular.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static br.samuelpsouza.matrizcurricular.TestUtil.convertObjectToJsonBytes;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
class UserTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    void shouldCreateAndPersistAUserObject() {
        List<Role> roles = this.roleRepository.findByName("ROLE_COORDENADOR");
        User user = new User("samuel123", "12345678", roles);
        User persistedUser = this.userRepository.save(user);
        assertEquals(persistedUser.getUsername(), user.getUsername());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldRequestUsersAndReceiveApiResponseJson() throws Exception {
        mvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", notNullValue()))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.data", anything()));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldRequestRolesAndReceiveApiResponseJson() throws Exception {
        mvc.perform(get("/users/roles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", notNullValue()))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.data", anything()));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldAddANewUserAndReceiveApiResponseJson() throws Exception {
        List<Role> roles = this.roleRepository.findByName("ROLE_COORDENADOR");
        User user = new User("samuelsouza", "abc12345678", roles);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(user)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.data", nullValue()));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldDeleteAUserAndReceiveApiResponseJson() throws Exception {
        List<Role> roles = this.roleRepository.findByName("ROLE_COORDENADOR");
        User user = new User("samuel123", "12345678", roles);
        User persistedUser = this.userRepository.save(user);

        mvc.perform(delete("/users/" + persistedUser.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.data", anything()));
    }

    @Test
    @WithMockUser
    void shouldRequestUserInfoAndReceiveApiResponseJson() throws Exception {
        List<Role> roles = this.roleRepository.findByName("ROLE_COORDENADOR");
        User user = new User("samuel123", "12345678", roles);
        this.userRepository.save(user);

        mvc.perform(get("/users/info")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.data", anything()));
    }

    @Test
    void shouldRequestInitializeAUserForDemoAndReceiveApiResponseJson() throws Exception {
        mvc.perform(get("/init")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.data", anything()));

        LoginRequest loginRequest = new LoginRequest("administrator", "12345678");
        mvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.data", notNullValue()));
    }


    @AfterEach
    public void cleanDatabaseUp() {
        this.userRepository.deleteAll();
    }
}
