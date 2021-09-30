package space.ssouza.curriculum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import space.ssouza.curriculum.model.Matrix;

@Repository
public interface MatrixRepository extends JpaRepository<Matrix, Long> {
}
