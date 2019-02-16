package br.samuelpsouza.matrizcurricular.repository;

import br.samuelpsouza.matrizcurricular.model.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {
}
