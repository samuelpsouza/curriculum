package br.samuelpsouza.matrizcurricular.service;

import br.samuelpsouza.matrizcurricular.model.Role;
import br.samuelpsouza.matrizcurricular.model.User;
import br.samuelpsouza.matrizcurricular.payload.ApiResponse;
import br.samuelpsouza.matrizcurricular.repository.RoleRepository;
import br.samuelpsouza.matrizcurricular.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    public ApiResponse getUsers() {
        ApiResponse response = new ApiResponse(true, "Users fetched");
        response.setData(this.userRepository.findAll());
        return response;
    }

    @Transactional
    public ApiResponse saveUser(User user) {
        ApiResponse response = new ApiResponse(true, "User " + user.getUsername() + " saved");
        log.info("User " + user.getUsername() + " added");
        return response;
    }

    @Transactional
    public ApiResponse deleteUser(Long id) {
        ApiResponse response = new ApiResponse(true, "User " + id + " removed");
        log.info("User " + id + "removed");
        return response;
    }

    @Transactional(readOnly = true)
    public ApiResponse getRoles() {
        ApiResponse response = new ApiResponse(true, "Roles fetched");
        response.setData(this.roleRepository.findAll());
        return response;
    }

    @Transactional
    public ApiResponse init() {
        User user = this.userRepository.findByUsername("demo").orElse(null);

        if (user != null)
            return new ApiResponse(false, "Usuário Demo já existe");
        else {
            List<Role> roles = this.roleRepository.findByName("ADMIN");
            User newUser = new User("demo", "demo", roles);
            this.userRepository.save(newUser);
            return new ApiResponse(true, "Usuário Demo inicializado com sucessp");
        }
    }

    @Transactional(readOnly = true)
    public ApiResponse getInfo(Principal principal) {
        ApiResponse response = new ApiResponse(true, "User info fetched");
        response.setData(this.userRepository.findByUsername(principal.getName()));
        return response;
    }
}
