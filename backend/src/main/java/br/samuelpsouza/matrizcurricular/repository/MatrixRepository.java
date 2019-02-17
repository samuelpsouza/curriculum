package br.samuelpsouza.matrizcurricular.repository;

import br.samuelpsouza.matrizcurricular.model.Matrix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatrixRepository extends JpaRepository<Matrix, Long> {
}
