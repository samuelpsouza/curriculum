package space.ssouza.curriculum.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import space.ssouza.curriculum.model.Role;
import space.ssouza.curriculum.model.User;
import space.ssouza.curriculum.payload.ApiResponse;
import space.ssouza.curriculum.repository.RoleRepository;
import space.ssouza.curriculum.repository.UserRepository;

import java.security.Principal;
import java.util.List;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public ApiResponse getUsers() {
        ApiResponse response = new ApiResponse(true, "Users fetched");
        response.setData(this.userRepository.findAll());
        return response;
    }

    @Transactional
    public ApiResponse saveUser(User user) {
        this.userRepository.save(user);
        ApiResponse response = new ApiResponse(true, "User " + user.getUsername() + " saved");
        log.info("User " + user.getUsername() + " added");
        return response;
    }

    @Transactional
    public ApiResponse deleteUser(Long id) {
        this.userRepository.deleteById(id);
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
        User user = this.userRepository.findByUsername("administrator").orElse(null);

        if (user != null)
            return new ApiResponse(false, "Usuário Demo já existe");
        else {
            List<Role> roles = this.roleRepository.findByName("ADMIN");
            User newUser = new User("administrator", passwordEncoder.encode("12345678"), roles);
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
