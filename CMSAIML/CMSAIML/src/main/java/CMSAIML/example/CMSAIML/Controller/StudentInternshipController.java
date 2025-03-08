package CMSAIML.example.CMSAIML.Controller;


import CMSAIML.example.CMSAIML.Entity.StudentInternship;
import CMSAIML.example.CMSAIML.Service.StudentInternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internships")
public class StudentInternshipController {

    @Autowired
    private StudentInternshipService studentInternshipService;

    @GetMapping
    public List<StudentInternship> getAllInternships() {
        return studentInternshipService.getAllInternships();
    }

    @GetMapping("/{id}")
    public StudentInternship getInternshipById(@PathVariable Long id) {
        return studentInternshipService.getInternshipById(id);
    }

    @PostMapping
    public StudentInternship addInternship(@RequestBody StudentInternship internship) {
        return studentInternshipService.saveInternship(internship);
    }

    @PutMapping("/{id}")
    public StudentInternship updateInternship(@PathVariable Long id, @RequestBody StudentInternship internship) {
        internship.setId(id);
        return studentInternshipService.saveInternship(internship);
    }

    @DeleteMapping("/{id}")
    public void deleteInternship(@PathVariable Long id) {
        studentInternshipService.deleteInternship(id);
    }
}
