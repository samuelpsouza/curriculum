package space.ssouza.curriculum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import space.ssouza.curriculum.model.User;
import space.ssouza.curriculum.payload.ApiResponse;
import space.ssouza.curriculum.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("users")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getUsers() {
        return ResponseEntity.ok(this.userService.getUsers());
    }

    @GetMapping("/roles")
    public ResponseEntity<ApiResponse> getRoles() {
        return ResponseEntity.ok(this.userService.getRoles());
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addUser(@RequestBody @Valid User user) {
        return ResponseEntity.ok(this.userService.saveUser(user));
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateUser(@RequestBody @Valid User user) {
        return ResponseEntity.ok(this.userService.saveUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.userService.deleteUser(id));
    }

    @GetMapping("/info")
    public ResponseEntity<ApiResponse> getInfo(Principal principal){
        return ResponseEntity.ok(this.userService.getInfo(principal));
    }
}
