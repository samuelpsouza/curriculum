package space.ssouza.curriculum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import space.ssouza.curriculum.model.Program;
import space.ssouza.curriculum.service.ProgramService;

@RestController
@RequestMapping(value = "programs", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ProgramController {
	private final ProgramService programService;

	@Autowired
	public ProgramController(final ProgramService majorService) {
		this.programService = majorService;
	}

	@GetMapping
	public ResponseEntity<Page<Program>> getPrograms(@PageableDefault final Pageable page) {
		return ResponseEntity.ok(programService.getPrograms(page));
	}

	@PostMapping
	public ResponseEntity<Program> addProgram(@RequestBody @Valid final Program program) {
		return ResponseEntity.ok(programService.saveMajor(program));
	}

	@PutMapping
	public ResponseEntity<Program> updateProgram(@RequestBody @Valid final Program major) {
		return ResponseEntity.ok(programService.saveMajor(major));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProgram(@PathVariable("id") final Integer id) {
		programService.deleteMajor(id);
		return ResponseEntity.ok("removed");
	}

	@GetMapping("/{id}")
	public ResponseEntity<Program> getSingleProgram(@PathVariable("id") final Integer id) {
		return ResponseEntity.ok(programService.getSingleMajor(id).orElseGet(null));
	}
}
