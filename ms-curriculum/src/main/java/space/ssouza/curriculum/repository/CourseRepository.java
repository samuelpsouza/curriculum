package space.ssouza.curriculum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import space.ssouza.curriculum.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
