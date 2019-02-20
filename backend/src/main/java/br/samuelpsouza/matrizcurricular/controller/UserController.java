package br.samuelpsouza.matrizcurricular.controller;

import br.samuelpsouza.matrizcurricular.model.User;
import br.samuelpsouza.matrizcurricular.payload.ApiResponse;
import br.samuelpsouza.matrizcurricular.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> getUsers() {
        return ResponseEntity.ok(this.userService.getUsers());
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> getRoles() {
        return ResponseEntity.ok(this.userService.getRoles());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> addUser(@RequestBody @Valid User user) {
        return ResponseEntity.ok(this.userService.saveUser(user));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> updateUser(@RequestBody @Valid User user) {
        return ResponseEntity.ok(this.userService.saveUser(user));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.userService.deleteUser(id));
    }
}
