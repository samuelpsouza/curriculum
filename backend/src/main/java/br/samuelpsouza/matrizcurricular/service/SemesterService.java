package br.samuelpsouza.matrizcurricular.service;

import br.samuelpsouza.matrizcurricular.model.Semester;
import br.samuelpsouza.matrizcurricular.payload.ApiResponse;
import br.samuelpsouza.matrizcurricular.repository.SemesterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class SemesterService {
    private static final Logger log = LoggerFactory.getLogger(SemesterService.class);
    private final SemesterRepository semesterRepository;

    @Autowired
    public SemesterService(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    @Transactional
    public ApiResponse deleteSemester(Long id) {
        ApiResponse response = new ApiResponse(true, "Semester removed");
        this.semesterRepository.deleteById(id);
        log.info("Semester " + id + " removed at " + LocalDateTime.now());
        return response;
    }

    @Transactional
    public ApiResponse saveSemester(Semester semester) {
        ApiResponse response = new ApiResponse(true, "Semester saved");
        response.setData(this.semesterRepository.save(semester));
        return response;
    }
}
