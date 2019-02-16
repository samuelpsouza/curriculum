package br.samuelpsouza.matrizcurricular.repository;

import br.samuelpsouza.matrizcurricular.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
}
