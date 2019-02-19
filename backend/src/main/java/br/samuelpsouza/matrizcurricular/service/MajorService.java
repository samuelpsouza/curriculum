package br.samuelpsouza.matrizcurricular.service;

import br.samuelpsouza.matrizcurricular.model.Major;
import br.samuelpsouza.matrizcurricular.payload.ApiResponse;
import br.samuelpsouza.matrizcurricular.repository.MajorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
public class MajorService {
    private final MajorRepository majorRepository;

    @Autowired
    public MajorService(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    @Transactional(readOnly = true)
    public ApiResponse getMajors(Pageable page) {
        ApiResponse response = new ApiResponse(true, "Majors fetched");
        response.setData(this.majorRepository.findAll(page));
        return response;
    }

    @Transactional
    public ApiResponse saveMajor(Major major) {
        ApiResponse response = new ApiResponse(true, "Major saved");
        response.setData(this.majorRepository.save(major));
        return response;
    }

    @Transactional
    public ApiResponse deleteMajor(Long id) {
        ApiResponse response = new ApiResponse(true, "Major removed");
        this.majorRepository.deleteById(id);
        log.info("Major " + id + " removed at " + LocalDateTime.now());
        return response;
    }

    public ApiResponse getSingleMajor(Long id) {
        ApiResponse response = new ApiResponse(true, "Major %s fetched", id);
        response.setData(this.majorRepository.findById(id));
        return response;
    }
}
