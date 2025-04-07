package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.FacultyAward;
import CMSAIML.example.CMSAIML.Service.FacultyAwardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty-awards")
public class FacultyAwardController {

    private final FacultyAwardService facultyAwardService;

    public FacultyAwardController(FacultyAwardService facultyAwardService) {
        this.facultyAwardService = facultyAwardService;
    }

    @GetMapping
    public ResponseEntity<List<FacultyAward>> getAllAwards() {
        return ResponseEntity.ok(facultyAwardService.getAllAwards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyAward> getAwardById(@PathVariable Long id) {
        FacultyAward award = facultyAwardService.getAwardById(id);
        return award != null ? ResponseEntity.ok(award) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<FacultyAward> createAward(@RequestBody FacultyAward award) {
        FacultyAward savedAward = facultyAwardService.saveAward(award);
        return ResponseEntity.ok(savedAward);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultyAward> updateAward(@PathVariable Long id, @RequestBody FacultyAward award) {
        award.setId(id);
        FacultyAward updatedAward = facultyAwardService.saveAward(award);
        return ResponseEntity.ok(updatedAward);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAward(@PathVariable Long id) {
        facultyAwardService.deleteAward(id);
        return ResponseEntity.noContent().build();
    }
}
