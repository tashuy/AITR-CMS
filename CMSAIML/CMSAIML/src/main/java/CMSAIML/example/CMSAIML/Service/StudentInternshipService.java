package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.StudentInternship;
import CMSAIML.example.CMSAIML.repository.StudentInternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentInternshipService {

    @Autowired
    private StudentInternshipRepository studentInternshipRepository;

    public List<StudentInternship> getAllInternships() {
        return studentInternshipRepository.findAll();
    }

    public StudentInternship getInternshipById(Long id) {
        return studentInternshipRepository.findById(id).orElse(null);
    }

    public StudentInternship saveInternship(StudentInternship internship) {
        return studentInternshipRepository.save(internship);
    }

    public void deleteInternship(Long id) {
        studentInternshipRepository.deleteById(id);
    }
}
