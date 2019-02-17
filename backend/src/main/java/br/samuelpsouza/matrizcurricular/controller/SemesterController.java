package br.samuelpsouza.matrizcurricular.controller;

import br.samuelpsouza.matrizcurricular.model.Semester;
import br.samuelpsouza.matrizcurricular.payload.ApiResponse;
import br.samuelpsouza.matrizcurricular.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "semesters")
public class SemesterController {
    private final SemesterService semesterService;

    @Autowired
    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ApiResponse> deleteSemester(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.semesterService.deleteSemester(id));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> addSemester(@RequestBody @Valid Semester semester) {
        return ResponseEntity.ok(this.semesterService.saveSemester(semester));
    }
}
