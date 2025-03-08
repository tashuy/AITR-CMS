package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.FacultyResearchPaper;
import CMSAIML.example.CMSAIML.Service.FacultyResearchPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty-research-papers")
public class FacultyResearchPaperController {

    @Autowired
    private FacultyResearchPaperService facultyResearchPaperService;

    @GetMapping
    public List<FacultyResearchPaper> getAllResearchPapers() {
        return facultyResearchPaperService.getAllResearchPapers();
    }

    @GetMapping("/{id}")
    public FacultyResearchPaper getResearchPaperById(@PathVariable Long id) {
        return facultyResearchPaperService.getResearchPaperById(id);
    }

    @PostMapping
    public FacultyResearchPaper createResearchPaper(@RequestBody FacultyResearchPaper researchPaper) {
        return facultyResearchPaperService.saveResearchPaper(researchPaper);
    }

    @PutMapping("/{id}")
    public FacultyResearchPaper updateResearchPaper(@PathVariable Long id, @RequestBody FacultyResearchPaper researchPaper) {
        researchPaper.setId(id);
        return facultyResearchPaperService.saveResearchPaper(researchPaper);
    }

    @DeleteMapping("/{id}")
    public void deleteResearchPaper(@PathVariable Long id) {
        facultyResearchPaperService.deleteResearchPaper(id);
    }
}
