package br.samuelpsouza.matrizcurricular.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "majors")
public class MajorController {
    @GetMapping
    @ResponseBody
    public ResponseEntity<String> getMajors() {
        return ResponseEntity.ok("RequestBody");
    }
}
