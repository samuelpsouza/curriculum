package space.ssouza.curriculum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import space.ssouza.curriculum.model.Course;
import space.ssouza.curriculum.payload.ApiResponse;
import space.ssouza.curriculum.service.CourseService;

@RestController
@RequestMapping(value = "courses", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(final CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getSingleCourse(@PathVariable("id") Long id) {
        return ResponseEntity.ok(courseService.getSingleCourse(id));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getCourses(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(courseService.getCourses(pageable));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addCourse(@RequestBody @Valid Course course) {
        return ResponseEntity.ok(courseService.saveCourse(course));
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateCourse(@RequestBody @Valid Course course) {
        return ResponseEntity.ok(courseService.saveCourse(course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCourse(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(courseService.deleteCourse(id));
    }
}
