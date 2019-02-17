package br.samuelpsouza.matrizcurricular.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Matrix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Major major;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Semester> semesterList = new ArrayList<>();
}
