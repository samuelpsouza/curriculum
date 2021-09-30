package space.ssouza.curriculum.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotNull
    @NotEmpty
    @NotBlank
    private String code;
    @NotNull
    @NotEmpty
    @NotBlank
    private String description;
    @OneToOne
    private Semester semester;

    public Course(@NotNull @NotEmpty @NotBlank String code, @NotNull @NotEmpty @NotBlank String description) {
        this.code = code;
        this.description = description;
    }

    public Course() {
        // Empty constructor
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}