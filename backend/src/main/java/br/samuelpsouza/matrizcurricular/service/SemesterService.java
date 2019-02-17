package br.samuelpsouza.matrizcurricular.service;

import br.samuelpsouza.matrizcurricular.model.Semester;
import br.samuelpsouza.matrizcurricular.payload.ApiResponse;
import br.samuelpsouza.matrizcurricular.repository.SemesterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
public class SemesterService {
    private final SemesterRepository semesterRepository;

    @Autowired
    public SemesterService(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    @Transactional
    public ApiResponse deleteSemester(Long id) {
        ApiResponse response = new ApiResponse(true, "Semester removed");
        this.semesterRepository.deleteById(id);
        log.info("Semester %s removed at %s", id, LocalDateTime.now());
        return response;
    }

    @Transactional
    public ApiResponse saveSemester(Semester semester) {
        ApiResponse response = new ApiResponse(true, "Semester saved");
        response.setData(this.semesterRepository.save(semester));
        return response;
    }
}
