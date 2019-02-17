package br.samuelpsouza.matrizcurricular.controller;

import br.samuelpsouza.matrizcurricular.payload.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "majors", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class MajorController {
    @GetMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> getMajors() {
        return ResponseEntity.ok(new ApiResponse());
    }
}
