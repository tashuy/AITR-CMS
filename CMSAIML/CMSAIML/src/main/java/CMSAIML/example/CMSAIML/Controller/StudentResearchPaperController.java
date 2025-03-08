package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.StudentResearchPaper;
import CMSAIML.example.CMSAIML.Service.StudentResearchPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/research-papers")
public class StudentResearchPaperController {

    @Autowired
    private StudentResearchPaperService studentResearchPaperService;

    @GetMapping
    public List<StudentResearchPaper> getAllResearchPapers() {
        return studentResearchPaperService.getAllResearchPapers();
    }

    @GetMapping("/{id}")
    public StudentResearchPaper getResearchPaperById(@PathVariable Long id) {
        return studentResearchPaperService.getResearchPaperById(id);
    }

    @PostMapping
    public StudentResearchPaper addResearchPaper(@RequestBody StudentResearchPaper researchPaper) {
        return studentResearchPaperService.saveResearchPaper(researchPaper);
    }

    @PutMapping("/{id}")
    public StudentResearchPaper updateResearchPaper(@PathVariable Long id, @RequestBody StudentResearchPaper researchPaper) {
        researchPaper.setId(id);
        return studentResearchPaperService.saveResearchPaper(researchPaper);
    }

    @DeleteMapping("/{id}")
    public void deleteResearchPaper(@PathVariable Long id) {
        studentResearchPaperService.deleteResearchPaper(id);
    }
}
