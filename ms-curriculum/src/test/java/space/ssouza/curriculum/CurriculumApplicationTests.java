package space.ssouza.curriculum;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import space.ssouza.curriculum.controller.CourseController;
import space.ssouza.curriculum.controller.ProgramController;

@SpringBootTest
class CurriculumApplicationTests {

	@Autowired
	private ProgramController programController;
	@Autowired
	private CourseController courseController;

	@Test
	void contextLoads() {
		assertThat(programController).isNotNull();
		assertThat(courseController).isNotNull();
	}

}
