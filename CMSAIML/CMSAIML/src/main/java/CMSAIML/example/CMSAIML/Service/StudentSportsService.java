package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.StudentSports;
import CMSAIML.example.CMSAIML.repository.StudentSportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentSportsService {

    @Autowired
    private StudentSportsRepository studentSportsRepository;

    public List<StudentSports> getAllSports() {
        return studentSportsRepository.findAll();
    }

    public StudentSports getSportsById(Long id) {
        return studentSportsRepository.findById(id).orElse(null);
    }

    public StudentSports saveSports(StudentSports sports) {
        return studentSportsRepository.save(sports);
    }

    public void deleteSports(Long id) {
        studentSportsRepository.deleteById(id);
    }
}
