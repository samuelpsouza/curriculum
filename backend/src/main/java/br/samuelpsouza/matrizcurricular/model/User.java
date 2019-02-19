package br.samuelpsouza.matrizcurricular.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 8)
    @NotNull
    @NotBlank
    @NotEmpty
    private String username;
    @NotNull
    @NotBlank
    @NotEmpty
    @org.springframework.data.annotation.Transient
    @JsonIgnore
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Role> roleList;
}