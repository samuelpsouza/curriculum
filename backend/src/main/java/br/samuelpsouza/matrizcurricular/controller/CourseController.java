package br.samuelpsouza.matrizcurricular.controller;

import br.samuelpsouza.matrizcurricular.model.Course;
import br.samuelpsouza.matrizcurricular.payload.ApiResponse;
import br.samuelpsouza.matrizcurricular.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ApiResponse> getSingleCourse(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.courseService.getSingleCourse(id));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> addCourse(@RequestBody @Valid Course course) {
        return ResponseEntity.ok(this.courseService.saveCourse(course));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> updateMajor(@RequestBody @Valid Course course) {
        return ResponseEntity.ok(this.courseService.saveCourse(course));
    }
}
