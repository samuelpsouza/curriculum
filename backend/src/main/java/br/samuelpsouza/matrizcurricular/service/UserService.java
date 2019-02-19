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
        ApiResponse response = new ApiResponse(true, "Users fetched");
        response.setData(this.userRepository.findAll());
        return response;
    }

    public ApiResponse saveUser(User user) {
        ApiResponse response = new ApiResponse(true, "User " + user.getUsername() + " saved");
        log.info("User " + user.getUsername() + " added");
        return response;
    }

    public ApiResponse deleteUser(Long id) {
        ApiResponse response = new ApiResponse(true, "User " + id + " removed");
        log.info("User " + id + "removed");
        return response;
    }
}
