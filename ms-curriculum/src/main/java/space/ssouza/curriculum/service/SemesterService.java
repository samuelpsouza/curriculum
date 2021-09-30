package space.ssouza.curriculum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import space.ssouza.curriculum.model.Semester;
import space.ssouza.curriculum.payload.ApiResponse;
import space.ssouza.curriculum.repository.SemesterRepository;

@Service
public class SemesterService {
    private final SemesterRepository semesterRepository;

    @Autowired
    public SemesterService(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    @Transactional
    public ApiResponse deleteSemester(final Long id) {
        this.semesterRepository.deleteById(id);
        return new ApiResponse(true, "Semester removed");
    }

    @Transactional
    public ApiResponse saveSemester(final Semester semester) {
        final Semester persistedSemester = this.semesterRepository.save(semester);
        return new ApiResponse(true, "Semester saved", persistedSemester);
    }
}
