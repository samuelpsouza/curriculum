package br.samuelpsouza.matrizcurricular;

import br.samuelpsouza.matrizcurricular.model.Role;
import br.samuelpsouza.matrizcurricular.model.User;
import br.samuelpsouza.matrizcurricular.repository.RoleRepository;
import br.samuelpsouza.matrizcurricular.repository.UserRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static br.samuelpsouza.matrizcurricular.TestUtil.convertObjectToJsonBytes;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
@AutoConfigureEmbeddedDatabase
@FlywayTest
public class UserTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void shouldCreateAndPersistAUserObject() {
        List<Role> roles = this.roleRepository.findByName("ROLE_COORDENADOR");
        User user = new User("samuel123", "12345678", roles);
        User persistedUser = this.userRepository.save(user);
        assertEquals(persistedUser.getUsername(), user.getUsername());
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
    @WithMockUser(roles = "ADMIN")
    public void shouldRequestUsersAndReceiveApiResponseJson() throws Exception {
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
    public void shouldRequestRolesAndReceiveApiResponseJson() throws Exception {
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
    public void shouldAddANewUserAndReceiveApiResponseJson() throws Exception {
        List<Role> roles = this.roleRepository.findByName("ROLE_COORDENADOR");
        User user = new User("samuelsouza", "12345678", roles);
        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(user)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.data", notNullValue()));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldDeleteAUserAndReceiveApiResponseJson() throws Exception {
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
    public void shouldRequestUserInfoAndReceiveApiResponseJson() throws Exception {
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

    @After
    public void cleanDatabaseUp() {
        this.userRepository.deleteAll();
    }
}
