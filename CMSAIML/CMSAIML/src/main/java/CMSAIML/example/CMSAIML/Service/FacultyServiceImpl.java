package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.Faculty;
import CMSAIML.example.CMSAIML.dto.FacultyDTO;
import CMSAIML.example.CMSAIML.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    @Override
    public Optional<Faculty> getFacultyById(Long id) {
        return facultyRepository.findById(id);
    }
    @Override
    public void createFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = new Faculty();
        faculty.setName(facultyDTO.getName());
        faculty.setEmail(facultyDTO.getEmail());
        faculty.setDepartment(facultyDTO.getDepartment());
        faculty.setMobile_no(facultyDTO.getMobile_no());
        faculty.setYears_Of_Experience(facultyDTO.getYears_Of_Experience());
        faculty.setDesignation(facultyDTO.getDesignation());
        facultyRepository.save(faculty);
    }


    @Override
    public Faculty updateFaculty(Long id, Faculty faculty) {
        Optional<Faculty> existingFaculty = facultyRepository.findById(id);
        if (existingFaculty.isPresent()) {
            faculty.setId(id);
            return facultyRepository.save(faculty);
        }
        return null;
    }

    @Override
    public void deleteFaculty(Long id) {
        Optional<Faculty> existingFaculty = facultyRepository.findById(id);
        if (existingFaculty.isPresent()) {
            facultyRepository.deleteById(id);
        }
    }
}
