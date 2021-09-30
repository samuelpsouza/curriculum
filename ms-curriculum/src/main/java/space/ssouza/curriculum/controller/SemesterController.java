package space.ssouza.curriculum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import space.ssouza.curriculum.model.Semester;
import space.ssouza.curriculum.payload.ApiResponse;
import space.ssouza.curriculum.service.SemesterService;

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
    public ResponseEntity<ApiResponse> deleteSemester(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.semesterService.deleteSemester(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addSemester(@RequestBody @Valid Semester semester) {
        return ResponseEntity.ok(this.semesterService.saveSemester(semester));
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateSemester(@RequestBody @Valid Semester semester) {
        // Usando o mesmo metodo pq os reposit´rios conseguem identificar se o objeto já existe no banco e atualizam.
        // Se não existir, é persistido um novo objeto
        return ResponseEntity.ok(this.semesterService.saveSemester(semester));
    }
}
