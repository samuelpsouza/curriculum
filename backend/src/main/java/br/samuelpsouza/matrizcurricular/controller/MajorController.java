package br.samuelpsouza.matrizcurricular.controller;

import br.samuelpsouza.matrizcurricular.model.Major;
import br.samuelpsouza.matrizcurricular.payload.ApiResponse;
import br.samuelpsouza.matrizcurricular.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "majors", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class MajorController {
    private final MajorService majorService;

    @Autowired
    public MajorController(MajorService majorService) {
        this.majorService = majorService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> getMajors(@PageableDefault Pageable page) {
        return ResponseEntity.ok(this.majorService.getMajors(page));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> addMajor(@RequestBody @Valid Major major) {
        return ResponseEntity.ok(this.majorService.addMajor(major));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> updateMajor(@RequestBody @Valid Major major) {
        return ResponseEntity.ok(this.majorService.addMajor(major));
    }
}
