package space.ssouza.curriculum.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import space.ssouza.curriculum.util.Period;

import java.util.Set;

@Entity
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull
    @NotBlank
    @NotEmpty
    @Column(unique = true)
    private String code;

    @NotNull
    @NotBlank
    @NotEmpty
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private Period period;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "programs_courses",
            joinColumns = {@JoinColumn(name = "program_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    @JsonIgnoreProperties("programs")
    private Set<Course> courses;

    public Program(final String code, final String title) {
        this.code = code;
        this.title = title;
    }

    public Program(final String code, final String title, final String description, final Period period) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.period = period;
    }

    public Program() {
        // Empty Constructor
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Period getPeriod() {
        return period;
    }

    public Set<Course> getCourses() {
        return courses;
    }
}
