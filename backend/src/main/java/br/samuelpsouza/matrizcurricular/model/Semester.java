package br.samuelpsouza.matrizcurricular.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> courseList = new ArrayList<>();

    public Semester(@NotNull String description) {
        this.description = description;
    }
}
