package space.ssouza.curriculum.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import space.ssouza.curriculum.model.Program;
import space.ssouza.curriculum.repository.ProgramRepository;

@Service
public class ProgramService {
	private final ProgramRepository programRepository;

	@Autowired
	public ProgramService(final ProgramRepository programRepository) {
		this.programRepository = programRepository;
	}

	@Transactional(readOnly = true)
	public Page<Program> getPrograms(final Pageable page) {
		return programRepository.findAll(page);
	}

	@Transactional
	public Program saveMajor(Program program) {
		return programRepository.save(program);
	}

	@Transactional
	public void deleteMajor(final Integer id) {
		programRepository.deleteById(id);
	}

	public Optional<Program> getSingleMajor(final Integer id) {
		return programRepository.findById(id);
	}
}
