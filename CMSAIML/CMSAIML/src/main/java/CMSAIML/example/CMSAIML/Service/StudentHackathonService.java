package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.StudentHackathon;
import CMSAIML.example.CMSAIML.repository.StudentHackathonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentHackathonService {

    @Autowired
    private StudentHackathonRepository studentHackathonRepository;

    public List<StudentHackathon> getAllHackathons() {
        return studentHackathonRepository.findAll();
    }

    public StudentHackathon getHackathonById(Long id) {
        return studentHackathonRepository.findById(id).orElse(null);
    }

    public StudentHackathon saveHackathon(StudentHackathon hackathon) {
        return studentHackathonRepository.save(hackathon);
    }

    public void deleteHackathon(Long id) {
        studentHackathonRepository.deleteById(id);
    }
}
