package br.samuelpsouza.matrizcurricular.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
public class User implements Serializable {
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
    private String password;

    @ManyToMany
    private List<Role> roleList;

    public User(@Size(min = 8) @NotNull @NotBlank @NotEmpty String username, @NotNull @NotBlank @NotEmpty String password, List<Role> roleList) {
        this.username = username;
        this.password = password;
        this.roleList = roleList;
    }
}