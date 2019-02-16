package br.samuelpsouza.matrizcurricular.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class Course {
    @NotNull
    private String title;
}
