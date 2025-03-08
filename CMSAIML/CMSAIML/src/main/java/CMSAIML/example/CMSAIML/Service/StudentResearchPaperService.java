package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.StudentResearchPaper;
import CMSAIML.example.CMSAIML.repository.StudentResearchPaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentResearchPaperService {

    @Autowired
    private StudentResearchPaperRepository studentResearchPaperRepository;

    public List<StudentResearchPaper> getAllResearchPapers() {
        return studentResearchPaperRepository.findAll();
    }

    public StudentResearchPaper getResearchPaperById(Long id) {
        return studentResearchPaperRepository.findById(id).orElse(null);
    }

    public StudentResearchPaper saveResearchPaper(StudentResearchPaper researchPaper) {
        return studentResearchPaperRepository.save(researchPaper);
    }

    public void deleteResearchPaper(Long id) {
        studentResearchPaperRepository.deleteById(id);
    }
}
