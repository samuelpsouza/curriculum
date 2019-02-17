package br.samuelpsouza.matrizcurricular.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Matrix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Semester> semesterList;
}
