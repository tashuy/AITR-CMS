package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.FacultyAward;
import CMSAIML.example.CMSAIML.repository.FacultyAwardRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyAwardService {

    private final FacultyAwardRepository facultyAwardRepository;

    public FacultyAwardService(FacultyAwardRepository facultyAwardRepository) {
        this.facultyAwardRepository = facultyAwardRepository;
    }

    public List<FacultyAward> getAllAwards() {
        return facultyAwardRepository.findAll();
    }

    public FacultyAward getAwardById(Long id) {
        return facultyAwardRepository.findById(id).orElse(null);
    }

    public FacultyAward saveAward(FacultyAward award) {
        return facultyAwardRepository.save(award);
    }

    // ✅ New method to handle form + file upload
    public FacultyAward saveAward(
            String facultyName,
            String awardName,
            String awardedBy,
            LocalDate awardDate,
            String category,
            String recognitionType,
            String eventName,
            String description,
            MultipartFile certificatePdf
    ) throws IOException {
        FacultyAward award = new FacultyAward();
        award.setFacultyName(facultyName);
        award.setAwardName(awardName);
        award.setAwardedBy(awardedBy);
        award.setAwardDate(awardDate);
        award.setCategory(category);
        award.setRecognitionType(recognitionType);
        award.setEventName(eventName);
        award.setDescription(description);
        award.setCertificatePdf(certificatePdf.getBytes());

        return facultyAwardRepository.save(award);
    }

    public FacultyAward updateAward(Long id, FacultyAward updatedAward) {
        Optional<FacultyAward> existing = facultyAwardRepository.findById(id);
        if (existing.isPresent()) {
            updatedAward.setId(id); // Ensure correct ID
            return facultyAwardRepository.save(updatedAward);
        }
        return null;
    }

    public void deleteAward(Long id) {
        facultyAwardRepository.deleteById(id);
    }
}
