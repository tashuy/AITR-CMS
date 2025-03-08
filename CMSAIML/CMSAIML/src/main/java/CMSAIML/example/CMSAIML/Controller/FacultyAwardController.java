package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.FacultyAward;
import CMSAIML.example.CMSAIML.Service.FacultyAwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty-awards")
public class FacultyAwardController {

    @Autowired
    private FacultyAwardService facultyAwardService;

    @GetMapping
    public List<FacultyAward> getAllAwards() {
        return facultyAwardService.getAllAwards();
    }

    @GetMapping("/{id}")
    public FacultyAward getAwardById(@PathVariable Long id) {
        return facultyAwardService.getAwardById(id);
    }

    @PostMapping
    public FacultyAward createAward(@RequestBody FacultyAward award) {
        return facultyAwardService.saveAward(award);
    }

    @PutMapping("/{id}")
    public FacultyAward updateAward(@PathVariable Long id, @RequestBody FacultyAward award) {
        award.setId(id);
        return facultyAwardService.saveAward(award);
    }

    @DeleteMapping("/{id}")
    public void deleteAward(@PathVariable Long id) {
        facultyAwardService.deleteAward(id);
    }
}
