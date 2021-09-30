package space.ssouza.curriculum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import space.ssouza.curriculum.model.Semester;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
}
