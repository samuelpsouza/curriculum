package space.ssouza.curriculum.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Role implements Serializable {
	private static final long serialVersionUID = 2846646894947469978L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	public Role() {
		// Empty constructor
	}

	public String getName() {
		return name;
	}
}
