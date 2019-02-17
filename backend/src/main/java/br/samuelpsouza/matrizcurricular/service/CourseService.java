package br.samuelpsouza.matrizcurricular.service;

import br.samuelpsouza.matrizcurricular.model.Course;
import br.samuelpsouza.matrizcurricular.payload.ApiResponse;
import br.samuelpsouza.matrizcurricular.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional(readOnly = true)
    public ApiResponse getSingleCourse(Long id) {
        ApiResponse response = new ApiResponse(true, "Course %s fetched", id);
        response.setData(this.courseRepository.findById(id));
        return response;
    }

    @Transactional
    public ApiResponse saveCourse(Course course) {
        ApiResponse response = new ApiResponse(true, "Course saved");
        response.setData(this.courseRepository.save(course));
        return response;
    }
}
