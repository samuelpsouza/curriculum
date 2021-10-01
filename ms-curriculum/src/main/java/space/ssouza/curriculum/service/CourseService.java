package space.ssouza.curriculum.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import space.ssouza.curriculum.model.Course;
import space.ssouza.curriculum.repository.CourseRepository;

@Service
public class CourseService {
	private final CourseRepository courseRepository;

	@Autowired
	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Transactional(readOnly = true)
	public Optional<Course> getSingleCourse(final Integer id) {
		return courseRepository.findById(id);
	}

	@Transactional
	public Course saveCourse(final Course course) {
		return courseRepository.save(course);
	}

	@Transactional
	public void deleteCourse(final Integer id) {
		courseRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Page<Course> getCourses(Pageable pageable) {
		return courseRepository.findAll(pageable);
	}
}
