package br.samuelpsouza.matrizcurricular.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
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
    @OneToMany
    private List<Matrix> matrixList;

    public Major(@NotNull @NotBlank @NotEmpty String code, @NotNull @NotBlank @NotEmpty String title) {
        this.code = code;
        this.title = title;
    }
}
