package br.samuelpsouza.matrizcurricular.controller;

import br.samuelpsouza.matrizcurricular.model.Semester;
import br.samuelpsouza.matrizcurricular.payload.ApiResponse;
import br.samuelpsouza.matrizcurricular.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "semesters")
@CrossOrigin
public class SemesterController {
    private final SemesterService semesterService;

    @Autowired
    public SemesterController(final SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('COORDENADOR')")
    public ResponseEntity<ApiResponse> deleteSemester(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.semesterService.deleteSemester(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('COORDENADOR')")
    public ResponseEntity<ApiResponse> addSemester(@RequestBody @Valid Semester semester) {
        return ResponseEntity.ok(this.semesterService.saveSemester(semester));
    }

    @PutMapping
    @PreAuthorize("hasRole('COORDENADOR')")
    public ResponseEntity<ApiResponse> updateSemester(@RequestBody @Valid Semester semester) {
        // Usando o mesmo metodo pq os reposit´rios conseguem identificar se o objeto já existe no banco e atualizam.
        // Se não existir, é persistido um novo objeto
        return ResponseEntity.ok(this.semesterService.saveSemester(semester));
    }
}
