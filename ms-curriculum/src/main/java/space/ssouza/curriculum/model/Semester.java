package space.ssouza.curriculum.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String description;

    public Semester(@NotNull String description) {
        this.description = description;
    }

    public Semester() {
        // Empty constructor
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}