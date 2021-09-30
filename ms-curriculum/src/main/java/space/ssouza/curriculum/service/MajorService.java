package space.ssouza.curriculum.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import space.ssouza.curriculum.model.Major;
import space.ssouza.curriculum.payload.ApiResponse;
import space.ssouza.curriculum.repository.MajorRepository;

import java.time.LocalDateTime;

@Service
public class MajorService {
    private static final Logger log = LoggerFactory.getLogger(MajorService.class);
    private final MajorRepository majorRepository;

    @Autowired
    public MajorService(final MajorRepository majorRepository) {
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
