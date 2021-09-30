package space.ssouza.curriculum.model;

import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = -6729383496407298521L;

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
    @Transient
    private String password;

    @ManyToMany
    private List<Role> roleList;

    public User(){
        // Empty constructor
    }

    public User(@Size(min = 8) @NotNull @NotBlank @NotEmpty String username, @NotNull @NotBlank @NotEmpty String password, List<Role> roleList) {
        this.username = username;
        this.password = password;
        this.roleList = roleList;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Role> getRoleList() {
        return roleList;
    }
}