package br.samuelpsouza.matrizcurricular.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Matrix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> courseList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<Semester> semesterList = new ArrayList<>();
}
