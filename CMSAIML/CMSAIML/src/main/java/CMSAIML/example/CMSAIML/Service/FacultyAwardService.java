package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.FacultyAward;
import CMSAIML.example.CMSAIML.repository.FacultyAwardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Faculty Awards.
 */
@Service
public class FacultyAwardService {

    private final FacultyAwardRepository facultyAwardRepository;

    public FacultyAwardService(FacultyAwardRepository facultyAwardRepository) {
        this.facultyAwardRepository = facultyAwardRepository;
    }

    /**
     * Retrieves all faculty awards.
     */
    public List<FacultyAward> getAllAwards() {
        return facultyAwardRepository.findAll();
    }

    /**
     * Retrieves a specific faculty award by ID.
     */
    public FacultyAward getAwardById(Long id) {
        return facultyAwardRepository.findById(id).orElse(null);
    }

    /**
     * Saves or creates a new faculty award.
     */
    public FacultyAward saveAward(FacultyAward award) {
        return facultyAwardRepository.save(award);
    }

    /**
     * Updates an existing faculty award by ID.
     */
    public FacultyAward updateAward(Long id, FacultyAward updatedAward) {
        Optional<FacultyAward> existing = facultyAwardRepository.findById(id);
        if (existing.isPresent()) {
            updatedAward.setId(id); // Ensure correct ID
            return facultyAwardRepository.save(updatedAward);
        }
        return null; // Or throw exception if you prefer
    }

    /**
     * Deletes a faculty award by ID.
     */
    public void deleteAward(Long id) {
        facultyAwardRepository.deleteById(id);
    }
}
