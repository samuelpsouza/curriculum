package space.ssouza.curriculum.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(unique = true)
    @NotNull
    @NotEmpty
    @NotBlank
    private String code;

    @NotNull
    @NotEmpty
    @NotBlank
    private String description;

    @ManyToMany(mappedBy = "courses")
    @JsonIgnoreProperties("courses")
    private Set<Program> programs;

    public Course(@NotNull @NotEmpty @NotBlank String code, @NotNull @NotEmpty @NotBlank String description) {
        this.code = code;
        this.description = description;
    }

    public Course() {
        // Empty constructor
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Set<Program> getPrograms() {
        return programs;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
