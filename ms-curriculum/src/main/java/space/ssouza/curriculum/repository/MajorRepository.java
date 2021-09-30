package space.ssouza.curriculum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import space.ssouza.curriculum.model.Major;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {
}
