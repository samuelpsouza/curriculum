package br.samuelpsouza.matrizcurricular.controller;

import br.samuelpsouza.matrizcurricular.payload.ApiResponse;
import br.samuelpsouza.matrizcurricular.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "semesters")
public class SemesterController {
    private final SemesterService semesterService;

    @Autowired
    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSemester(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.semesterService.deleteSemester(id));
    }
}
