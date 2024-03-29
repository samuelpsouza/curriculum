package space.ssouza.curriculum.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import space.ssouza.curriculum.service.CourseService;

@RestController
@RequestMapping(value = "courses", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(final CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getSingleCourse(@PathVariable("id") final Integer id) {
        return ResponseEntity.ok(courseService.getSingleCourse(id).orElseThrow(EntityNotFoundException::new));
    }

    @GetMapping
    public ResponseEntity<Page<Course>> getCourses(@PageableDefault final Pageable pageable) {
        return ResponseEntity.ok(courseService.getCourses(pageable));
    }

    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody @Valid final Course course) {
        return ResponseEntity.ok(courseService.saveCourse(course));
    }

    @PutMapping
    public ResponseEntity<Course> updateCourse(@RequestBody @Valid final Course course) {
        return ResponseEntity.ok(courseService.saveCourse(course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") final Integer id) {
    	courseService.deleteCourse(id);
        return ResponseEntity.ok(String.format("Course %s removed", id));
    }
}
