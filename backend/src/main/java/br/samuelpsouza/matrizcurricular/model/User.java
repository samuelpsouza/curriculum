package br.samuelpsouza.matrizcurricular.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    public User(@Size(min = 8) @NotNull @NotBlank @NotEmpty String username, @NotNull @NotBlank @NotEmpty String password, List<Role> roleList) {
        this.username = username;
        this.password = password;
        this.roleList = roleList;
    }
}