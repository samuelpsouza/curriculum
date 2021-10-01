package space.ssouza.curriculum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import space.ssouza.curriculum.model.Program;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer> {
}
