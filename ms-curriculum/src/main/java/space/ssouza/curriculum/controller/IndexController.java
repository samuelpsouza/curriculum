package space.ssouza.curriculum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import space.ssouza.curriculum.payload.ApiResponse;
import space.ssouza.curriculum.service.UserService;

@RestController
@RequestMapping(value = "/")
@CrossOrigin
public class IndexController {
    private final UserService userService;

    @Autowired
    public IndexController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> index() {
        return ResponseEntity.ok(new ApiResponse(true, "No donuts for you"));
    }

    @GetMapping("/init")
    public ResponseEntity<ApiResponse> init() {
        return ResponseEntity.ok(userService.init());
    }
}
