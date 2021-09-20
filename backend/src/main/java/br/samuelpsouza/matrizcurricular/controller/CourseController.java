package br.samuelpsouza.matrizcurricular.controller;

import br.samuelpsouza.matrizcurricular.model.Course;
import br.samuelpsouza.matrizcurricular.payload.ApiResponse;
import br.samuelpsouza.matrizcurricular.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "courses", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(final CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('COORDENADOR')")
    public ResponseEntity<ApiResponse> getSingleCourse(@PathVariable("id") Long id) {
        return ResponseEntity.ok(courseService.getSingleCourse(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('COORDENADOR')")
    public ResponseEntity<ApiResponse> getCourses(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(courseService.getCourses(pageable));
    }

    @PostMapping
    @PreAuthorize("hasRole('COORDENADOR')")
    public ResponseEntity<ApiResponse> addCourse(@RequestBody @Valid Course course) {
        return ResponseEntity.ok(courseService.saveCourse(course));
    }

    @PutMapping
    @PreAuthorize("hasRole('COORDENADOR')")
    public ResponseEntity<ApiResponse> updateCourse(@RequestBody @Valid Course course) {
        return ResponseEntity.ok(courseService.saveCourse(course));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('COORDENADOR')")
    public ResponseEntity<ApiResponse> deleteCourse(@PathVariable("id") Long id) {
        return ResponseEntity.ok(courseService.deleteCourse(id));
    }
}
