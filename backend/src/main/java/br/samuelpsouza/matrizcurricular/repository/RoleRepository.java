package br.samuelpsouza.matrizcurricular.repository;

import br.samuelpsouza.matrizcurricular.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findByName(String admin);
}
