package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.FacultyAward;
import CMSAIML.example.CMSAIML.repository.FacultyAwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyAwardService {

    @Autowired
    private FacultyAwardRepository facultyAwardRepository;

    public List<FacultyAward> getAllAwards() {
        return facultyAwardRepository.findAll();
    }

    public FacultyAward getAwardById(Long id) {
        return facultyAwardRepository.findById(id).orElse(null);
    }

    public FacultyAward saveAward(FacultyAward award) {
        return facultyAwardRepository.save(award);
    }

    public void deleteAward(Long id) {
        facultyAwardRepository.deleteById(id);
    }
}
