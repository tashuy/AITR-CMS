package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.FacultyResearchPaper;
import CMSAIML.example.CMSAIML.repository.FacultyResearchPaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyResearchPaperService {

    @Autowired
    private FacultyResearchPaperRepository facultyResearchPaperRepository;

    public List<FacultyResearchPaper> getAllResearchPapers() {
        return facultyResearchPaperRepository.findAll();
    }

    public FacultyResearchPaper getResearchPaperById(Long id) {
        return facultyResearchPaperRepository.findById(id).orElse(null);
    }

    public FacultyResearchPaper saveResearchPaper(FacultyResearchPaper researchPaper) {
        return facultyResearchPaperRepository.save(researchPaper);
    }

    public void deleteResearchPaper(Long id) {
        facultyResearchPaperRepository.deleteById(id);
    }
}
