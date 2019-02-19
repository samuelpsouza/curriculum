package br.samuelpsouza.matrizcurricular.service;

import br.samuelpsouza.matrizcurricular.model.User;
import br.samuelpsouza.matrizcurricular.payload.ApiResponse;
import br.samuelpsouza.matrizcurricular.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ApiResponse getUsers() {
        return null;
    }

    public ApiResponse saveUser(User user) {
        return null;
    }

    public ApiResponse deleteUser(Long id) {
        return null;
    }
}
