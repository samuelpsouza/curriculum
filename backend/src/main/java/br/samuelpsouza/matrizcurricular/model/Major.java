package br.samuelpsouza.matrizcurricular.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    @NotEmpty
    private String code;
    @NotNull
    @NotBlank
    @NotEmpty
    private String title;

    public Major(@NotNull @NotBlank @NotEmpty String code, @NotNull @NotBlank @NotEmpty String title) {
        this.code = code;
        this.title = title;
    }
}
