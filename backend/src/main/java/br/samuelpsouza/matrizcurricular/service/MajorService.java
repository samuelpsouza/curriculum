package br.samuelpsouza.matrizcurricular.service;

import br.samuelpsouza.matrizcurricular.payload.ApiResponse;
import br.samuelpsouza.matrizcurricular.repository.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MajorService {
    private final MajorRepository majorRepository;

    @Autowired
    public MajorService(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    public ApiResponse getMajors(Pageable page) {
        ApiResponse response = new ApiResponse();
        response.setSuccess(true);
        response.setMessage("Majors fetched");
        response.setData(this.majorRepository.findAll(page));
        return response;
    }
}
