package space.ssouza.curriculum.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import space.ssouza.curriculum.util.Period;

@Entity
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @Enumerated(EnumType.STRING)
    private Period period;
    private String duration;
    private String registrationNumber;
    @OneToOne(cascade = CascadeType.ALL)
    private Matrix matrix = new Matrix();

    public Major(@NotNull @NotBlank @NotEmpty String code, @NotNull @NotBlank @NotEmpty String title) {
        this.code = code;
        this.title = title;
    }

    public Major(@NotNull @NotBlank @NotEmpty String code, @NotNull @NotBlank @NotEmpty String title, String description, Period period, String duration, String registrationNumber, Matrix matrix) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.period = period;
        this.duration = duration;
        this.registrationNumber = registrationNumber;
        this.matrix = matrix;
    }

    public Long getId() {
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
}
