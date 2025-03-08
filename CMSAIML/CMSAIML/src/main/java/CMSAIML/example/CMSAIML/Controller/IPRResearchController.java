package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.IPRResearch;
import CMSAIML.example.CMSAIML.repository.IPRResearchRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/ipr-research")
public class IPRResearchController {

    private final IPRResearchRepository repository;

    public IPRResearchController(IPRResearchRepository repository) {
        this.repository = repository;
    }

    // ✅ Get all research projects
    @GetMapping
    public ResponseEntity<List<IPRResearch>> getAllResearch() {
        List<IPRResearch> researchList = repository.findAll();
        if (researchList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(researchList);
    }

    // ✅ Get research projects by funding agency (Better API structure)
    @GetMapping("/agency/{agencyName}")
    public ResponseEntity<List<IPRResearch>> getByFundingAgency(@PathVariable String agencyName) {
        List<IPRResearch> projects = repository.findByFundingAgency(agencyName);
        if (projects.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(projects);
    }

    // ✅ Add a new research project (With validation)
    @PostMapping
    public ResponseEntity<IPRResearch> addResearch(@Valid @RequestBody IPRResearch research) {
        IPRResearch savedResearch = repository.save(research);
        return ResponseEntity.ok(savedResearch);
    }
}
