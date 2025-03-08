package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.StudentSports;
import CMSAIML.example.CMSAIML.Service.StudentSportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sports")
public class StudentSportsController {

    @Autowired
    private StudentSportsService studentSportsService;

    @GetMapping
    public List<StudentSports> getAllSports() {
        return studentSportsService.getAllSports();
    }

    @GetMapping("/{id}")
    public StudentSports getSportsById(@PathVariable Long id) {
        return studentSportsService.getSportsById(id);
    }

    @PostMapping
    public StudentSports addSports(@RequestBody StudentSports sports) {
        return studentSportsService.saveSports(sports);
    }

    @PutMapping("/{id}")
    public StudentSports updateSports(@PathVariable Long id, @RequestBody StudentSports sports) {
        sports.setId(id);
        return studentSportsService.saveSports(sports);
    }

    @DeleteMapping("/{id}")
    public void deleteSports(@PathVariable Long id) {
        studentSportsService.deleteSports(id);
    }
}
