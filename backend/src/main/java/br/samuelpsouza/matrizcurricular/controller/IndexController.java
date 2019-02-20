package br.samuelpsouza.matrizcurricular.controller;

import br.samuelpsouza.matrizcurricular.payload.ApiResponse;
import br.samuelpsouza.matrizcurricular.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
@CrossOrigin
public class IndexController {
    private final UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> index() {
        return ResponseEntity.ok(new ApiResponse(true, "No donuts for you"));
    }

    @GetMapping("/init")
    @ResponseBody
    public ResponseEntity<ApiResponse> init() {
        return ResponseEntity.ok(this.userService.init());
    }
}
