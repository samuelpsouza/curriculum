package space.ssouza.curriculum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import space.ssouza.curriculum.model.Major;
import space.ssouza.curriculum.payload.ApiResponse;
import space.ssouza.curriculum.service.MajorService;

import javax.validation.Valid;


@RestController
@CrossOrigin
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
    @PreAuthorize("hasRole('COORDENADOR')")
    public ResponseEntity<ApiResponse> addMajor(@RequestBody @Valid Major major) {
        return ResponseEntity.ok(this.majorService.saveMajor(major));
    }

    @PutMapping
    @PreAuthorize("hasRole('COORDENADOR')")
    public ResponseEntity<ApiResponse> updateMajor(@RequestBody @Valid Major major) {
        return ResponseEntity.ok(this.majorService.saveMajor(major));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('COORDENADOR')")
    public ResponseEntity<ApiResponse> deleteMajor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.majorService.deleteMajor(id));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('COORDENADOR')")
    public ResponseEntity<ApiResponse> getSingleMajor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.majorService.getSingleMajor(id));
    }
}
