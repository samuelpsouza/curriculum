package space.ssouza.curriculum.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import space.ssouza.curriculum.util.Period;

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
}
